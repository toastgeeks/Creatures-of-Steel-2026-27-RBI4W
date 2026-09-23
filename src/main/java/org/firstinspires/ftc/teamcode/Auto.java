package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.ElapsedTime;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "Bi4W Auto", group = "Robot")
public class Auto extends OpMode {
    DcMotor frontLeftMotor;
    DcMotor frontRightMotor;
    DcMotor backLeftMotor;
    DcMotor backRightMotor;
    DcMotor slideMotor;
    CRServo outakeServo;

    ElapsedTime runtime = new ElapsedTime();
    ElapsedTime slideTimer = new ElapsedTime();

    boolean autoStarted = false;
    boolean slideTimerReset = false;
    double step = 0;
    double power = 0.65;

    @Override
    public void init() {
        frontLeftMotor = hardwareMap.get(DcMotor.class, "front_left_motor");
        frontRightMotor = hardwareMap.get(DcMotor.class, "front_right_motor");
        backLeftMotor = hardwareMap.get(DcMotor.class, "back_left_motor");
        backRightMotor = hardwareMap.get(DcMotor.class, "back_right_motor");
        slideMotor = hardwareMap.get(DcMotor.class, "slide_motor");

        outakeServo = hardwareMap.get(CRServo.class,"outake_servo");

        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);

        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        slideMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        runtime.reset();
    }

    @Override
    public void loop() {
        // This runs only once at the beginning
        if (!autoStarted) {
            // Back to the wall, strafes to the left
            frontLeftMotor.setTargetPosition(-1200);
            frontRightMotor.setTargetPosition(1200);
            backLeftMotor.setTargetPosition(1200);
            backRightMotor.setTargetPosition(-1200);

            frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            frontLeftMotor.setPower(power);
            frontRightMotor.setPower(power);
            backLeftMotor.setPower(power);
            backRightMotor.setPower(power);
            autoStarted = true;
        }

        if (step == 1) {
            // Forward, pushing the thingy
            frontLeftMotor.setTargetPosition(2000);
            frontRightMotor.setTargetPosition(4400);
            backLeftMotor.setTargetPosition(4400);
            backRightMotor.setTargetPosition(2000);

            frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            frontLeftMotor.setPower(power);
            frontRightMotor.setPower(power);
            backLeftMotor.setPower(power);
            backRightMotor.setPower(power);
        }

        if (step == 2) {
            // Backs up
            frontLeftMotor.setTargetPosition(1000);
            frontRightMotor.setTargetPosition(3400);
            backLeftMotor.setTargetPosition(3400);
            backRightMotor.setTargetPosition(1000);

            frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            frontLeftMotor.setPower(power);
            frontRightMotor.setPower(power);
            backLeftMotor.setPower(power);
            backRightMotor.setPower(power);
        }

        if (step == 3) {
            // Strafes to the wall
            frontLeftMotor.setTargetPosition(2400);
            frontRightMotor.setTargetPosition(2400);
            backLeftMotor.setTargetPosition(2400);
            backRightMotor.setTargetPosition(2400);

            frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            frontLeftMotor.setPower(power);
            frontRightMotor.setPower(power);
            backLeftMotor.setPower(power);
            backRightMotor.setPower(power);
        }

        if (step == 4) {
            // Forward to intake
            frontLeftMotor.setTargetPosition(4800);
            frontRightMotor.setTargetPosition(4800);
            backLeftMotor.setTargetPosition(4800);
            backRightMotor.setTargetPosition(4800);

            frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            frontLeftMotor.setPower(power);
            frontRightMotor.setPower(power);
            backLeftMotor.setPower(power);
            backRightMotor.setPower(power);
            outakeServo.setPower(-1);
        }

        if (step == 5) {
            // Scoring position
            frontLeftMotor.setTargetPosition(4000);
            frontRightMotor.setTargetPosition(4800);
            backLeftMotor.setTargetPosition(4800);
            backRightMotor.setTargetPosition(4000);

            frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            frontLeftMotor.setPower(power);
            frontRightMotor.setPower(power);
            backLeftMotor.setPower(power);
            backRightMotor.setPower(power);
            outakeServo.setPower(0);
        }

        if (step == 6) {
            // Turns toward thingy
            frontLeftMotor.setTargetPosition(2600);
            frontRightMotor.setTargetPosition(6200);
            backLeftMotor.setTargetPosition(3400);
            backRightMotor.setTargetPosition(5400);

            frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            frontLeftMotor.setPower(power);
            frontRightMotor.setPower(power);
            backLeftMotor.setPower(power);
            backRightMotor.setPower(power);
        }

        if (step == 7) {
            if (!slideTimerReset) {
                slideTimer.reset();
                slideTimerReset = true;
            }

            double timePassed = slideTimer.milliseconds();

            // Check intervals starting from 0 up to your target end time
            if (timePassed < 2000) {
                // 0 to 500ms: Slide goes up
                slideMotor.setPower(0.5);
            }

            else if (timePassed < 4000){
                slideMotor.setPower(0.25);
                outakeServo.setPower(1);
            }

            else if (timePassed < 4200) {
                // 500ms to 1500ms (lasts 1 second): Slide goes down
                slideMotor.setPower(-0.5);
            }
            else {
                // Past 1500ms: Stop everything and advance the step
                slideMotor.setPower(0.0);
                outakeServo.setPower(0);
                step++;
            }
        }

        if (step == 8) {
            // Turns back
            frontLeftMotor.setTargetPosition(4000);
            frontRightMotor.setTargetPosition(4800);
            backLeftMotor.setTargetPosition(4800);
            backRightMotor.setTargetPosition(4000);

            frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            frontLeftMotor.setPower(power);
            frontRightMotor.setPower(power);
            backLeftMotor.setPower(power);
            backRightMotor.setPower(power);
        }


        if (step != 7) {
            if (!frontLeftMotor.isBusy() && !frontRightMotor.isBusy() && !backLeftMotor.isBusy() && !backRightMotor.isBusy()) {
                step++;
            }
        }

        telemetry.addData("Current Step", step);
        telemetry.addData("Front Left", frontLeftMotor.getCurrentPosition());
        telemetry.addData("Front Right", frontRightMotor.getCurrentPosition());
        telemetry.addData("Back Left", backLeftMotor.getCurrentPosition());
        telemetry.addData("Back Right", backRightMotor.getCurrentPosition());
        telemetry.addData("outakeServo", outakeServo.getPower());
        telemetry.update();
    }
}
