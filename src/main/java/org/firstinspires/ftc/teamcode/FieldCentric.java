
        /* Copyright (c) 2025 FIRST. All rights reserved.
         *
         * Redistribution and use in source and binary forms, with or without modification,
         * are permitted (subject to the limitations in the disclaimer below) provided that
         * the following conditions are met:
         *
         * Redistributions of source code must retain the above copyright notice, this list
         * of conditions and the following disclaimer.
         *
         * Redistributions in binary form must reproduce the above copyright notice, this
         * list of conditions and the following disclaimer in the documentation and/or
         * other materials provided with the distribution.
         *
         * Neither the name of FIRST nor the names of its contributors may be used to endorse or
         * promote products derived from this software without specific prior written permission.
         *
         * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
         * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
         * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
         * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
         * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
         * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
         * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
         * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
         * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
         * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
         * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
         */

        package org.firstinspires.ftc.teamcode;

        import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
        import com.qualcomm.robotcore.eventloop.opmode.OpMode;
        import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.hardware.IMU;
        import com.qualcomm.robotcore.hardware.CRServo;

        import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

        @TeleOp(name = "Bi4W Field Centric", group = "Robot")

        public class FieldCentric extends OpMode {

            // This declares the four motors needed
            DcMotor frontLeftMotor;
            DcMotor frontRightMotor;
            DcMotor backLeftMotor;
            DcMotor backRightMotor;
            DcMotor slideMotor;

            // This declares the outtake servo
            CRServo outakeServo;

            // This declares the IMU needed to get the current direction the robot is facing
            IMU imu;

            @Override
            public void init() {
                frontLeftMotor = hardwareMap.get(DcMotor.class, "front_left_motor");
                frontRightMotor = hardwareMap.get(DcMotor.class, "front_right_motor");
                backLeftMotor = hardwareMap.get(DcMotor.class, "back_left_motor");
                backRightMotor = hardwareMap.get(DcMotor.class, "back_right_motor");
                slideMotor = hardwareMap.get(DcMotor.class, "slide_motor");

                // Initialize the outtake servo
                outakeServo = hardwareMap.get(CRServo.class, "outake_servo");

                // We set the left motors in reverse
                backLeftMotor.setDirection(DcMotor.Direction.REVERSE);
                frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);

                frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

                slideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

                imu = hardwareMap.get(IMU.class, "imu");

                RevHubOrientationOnRobot.LogoFacingDirection logoDirection =
                        RevHubOrientationOnRobot.LogoFacingDirection.RIGHT;

                RevHubOrientationOnRobot.UsbFacingDirection usbDirection =
                        RevHubOrientationOnRobot.UsbFacingDirection.UP;

                RevHubOrientationOnRobot orientationOnRobot = new
                        RevHubOrientationOnRobot(logoDirection, usbDirection);

                imu.initialize(new IMU.Parameters(orientationOnRobot));
            }

            @Override
            public void loop() {

                telemetry.addLine("Press A to reset Yaw");
                telemetry.addLine("The left joystick sets the robot direction");
                telemetry.addLine("Moving the right joystick left and right turns the robot");

                // Reset Yaw
                if (gamepad1.a) {
                    imu.resetYaw();
                }

                driveFieldRelative(-gamepad1.left_stick_y,gamepad1.left_stick_x,gamepad1.right_stick_x);

                // Slides
                if (gamepad1.left_trigger > 0.1) {
                    slideMotor.setPower(0.7);
                }
                else if (gamepad1.right_trigger > 0.1) {
                    slideMotor.setPower(-0.7);
                }
                else {
                    slideMotor.setPower(0);
                }

                // Intake servo
                if (gamepad1.left_bumper) {
                    outakeServo.setPower(-1.0);
                }
                else if (gamepad1.right_bumper) {
                    outakeServo.setPower(1);
                }
                else {
                    outakeServo.setPower(0);
                }
            }

            // This routine drives the robot field relative
            private void driveFieldRelative(double forward, double right, double rotate) {

                // Convert direction to polar coordinates
                double theta = Math.atan2(forward, right);
                double r = Math.hypot(right, forward);

                // Rotate angle by the angle the robot is pointing
                theta = AngleUnit.normalizeRadians(
                        theta - imu.getRobotYawPitchRollAngles()
                                .getYaw(AngleUnit.RADIANS)
                );

                // Convert back to cartesian
                double newForward = r * Math.sin(theta);
                double newRight = r * Math.cos(theta);

                // Drive
                drive(newForward, newRight, rotate);
            }

            // Drive routine
            public void drive(double forward, double right, double rotate) {

                double frontLeftPower = forward + right + rotate;
                double frontRightPower = forward - right - rotate;
                double backRightPower = forward + right - rotate;
                double backLeftPower = forward - right + rotate;

                double maxPower = 1.0;
                double maxSpeed = 1.0;

                // Make sure no wheel gets more than 1.0 power
                maxPower = Math.max(maxPower, Math.abs(frontLeftPower));
                maxPower = Math.max(maxPower, Math.abs(frontRightPower));
                maxPower = Math.max(maxPower, Math.abs(backRightPower));
                maxPower = Math.max(maxPower, Math.abs(backLeftPower));

                frontLeftMotor.setPower(maxSpeed * (frontLeftPower / maxPower));
                frontRightMotor.setPower(maxSpeed * (frontRightPower / maxPower));
                backLeftMotor.setPower(maxSpeed * (backLeftPower / maxPower));
                backRightMotor.setPower(maxSpeed * (backRightPower / maxPower));
            }
        }


