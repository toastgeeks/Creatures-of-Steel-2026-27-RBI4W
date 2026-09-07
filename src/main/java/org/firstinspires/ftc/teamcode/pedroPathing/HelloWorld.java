package org.firstinspires.ftc.teamcode.pedroPathing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "HelloWorld")
public class HelloWorld extends OpMode
{
    @Override
    public void init() {
        String MyName = "Ethan Tran";
        telemetry.addData("Hello", MyName);

    }

    @Override
    public void loop() {

    }
}
