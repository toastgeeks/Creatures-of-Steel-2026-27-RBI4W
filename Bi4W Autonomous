package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "Bi4W Auto", group = "Robot")
public class Auto extends OpMode {
    DcMotor frontLeftMotor;
    DcMotor frontRightMotor;
    DcMotor backLeftMotor;
    DcMotor backRightMotor;
    DcMotor slideMotor;


    ElapsedTime runtime = new ElapsedTime();
    boolean autoStarted = false;

    @Override
    public void init() {

        frontLeftMotor = hardwareMap.get(DcMotor.class, "front_left_motor");
        frontRightMotor = hardwareMap.get(DcMotor.class, "front_right_motor");
        backLeftMotor = hardwareMap.get(DcMotor.class, "back_left_motor");
        backRightMotor = hardwareMap.get(DcMotor.class, "back_right_motor");

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

        runtime.reset();
    }

    @Override
    public void loop() {

        // This runs only once at the beginning
        if (!autoStarted) {
            int targetPosition = 1000;

            // Set where each motor should go
            frontLeftMotor.setTargetPosition(targetPosition);
            frontRightMotor.setTargetPosition(targetPosition);
            backLeftMotor.setTargetPosition(targetPosition);
            backRightMotor.setTargetPosition(targetPosition);

            // Tell motors to move to their targets
            frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // Set movement speed
            frontLeftMotor.setPower(0.2);
            frontRightMotor.setPower(0.2);
            backLeftMotor.setPower(0.2);
            backRightMotor.setPower(0.2);

            autoStarted = true;
        }

        // Check whether the robot is still moving
        if (!frontLeftMotor.isBusy()
                && !frontRightMotor.isBusy()
                && !backLeftMotor.isBusy()
                && !backRightMotor.isBusy()) {

            // All motors reached their targets
            frontLeftMotor.setPower(0);
            frontRightMotor.setPower(0);
            backLeftMotor.setPower(0);
            backRightMotor.setPower(0);
        }

        telemetry.addData("Front Left", frontLeftMotor.getCurrentPosition());
        telemetry.addData("Front Right", frontRightMotor.getCurrentPosition());
        telemetry.addData("Back Left", backLeftMotor.getCurrentPosition());
        telemetry.addData("Back Right", backRightMotor.getCurrentPosition());

        telemetry.addData("FL Busy", frontLeftMotor.isBusy());
        telemetry.addData("FR Busy", frontRightMotor.isBusy());
        telemetry.addData("BL Busy", backLeftMotor.isBusy());
        telemetry.addData("BR Busy", backRightMotor.isBusy());

        telemetry.update();
    }
}
