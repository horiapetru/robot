package org.firstinspires.ftc.teamcode;


import android.annotation.SuppressLint;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
@SuppressWarnings({"unused", "FieldMayBeFinal"})


public class coco extends LinearOpMode {
    private DcMotor lr = null;
    private DcMotor lf = null;
    private DcMotor rr = null;
    private DcMotor rf = null;
    private ElapsedTime runtime = new ElapsedTime();

    void power(double lfp, double lrp, double rrp, double rfp){
        lf.setPower(lfp);
        lr.setPower(lrp);
        rf.setPower(rfp);
        rr.setPower(rrp);
    }


    @SuppressLint("SuspiciousIndentation")
    public void runOpMode(){
        lf = (DcMotor) hardwareMap.get("lf");
        lr = (DcMotor) hardwareMap.get("lr");
        rf = (DcMotor) hardwareMap.get("rf");
        rr = (DcMotor) hardwareMap.get("rr");
//        rr.setDirection(DcMotor.Direction.REVERSE);
        rf.setDirection(DcMotor.Direction.REVERSE);
        waitForStart();
        while(opModeIsActive()){
            power(0,0,0,0);
            double putere;
            double max;
            double axial   = -gamepad1.left_stick_y;  // Note: pushing stick forward gives negative value
            double lateral =  gamepad1.left_stick_x;
            double yaw     =  gamepad1.right_stick_x;
            double leftFrontPower  = axial + lateral + yaw;
            double rightFrontPower = axial - lateral - yaw;
            double leftBackPower   = axial - lateral + yaw;
            double rightBackPower  = axial + lateral - yaw;

            max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
            max = Math.max(max, Math.abs(leftBackPower));
            max = Math.max(max, Math.abs(rightBackPower));

            if (max > 1.0) {
                leftFrontPower  /= max;
                rightFrontPower /= max;
                leftBackPower   /= max;
                rightBackPower  /= max;
            }

            lf.setPower(leftFrontPower);
            rf.setPower(rightFrontPower);
            lr.setPower(leftBackPower);
            rr.setPower(rightBackPower);


                  if(gamepad1.dpad_up)
                    power(0.5,0.5,0.5,0.5);

                else if(gamepad1.dpad_right)
                    power(0.5,-0.5,0.5,-0.5);

                else if(gamepad1.dpad_left)
                    power(-0.5,0.5,-0.5,0.5);

                else if(gamepad1.dpad_down)
                    power(-0.5,-0.5,-0.5,-0.5);
                else if (gamepad1.left_bumper)
                    power(-0.5,-0.5,0.5,0.5);
                else if(gamepad1.right_bumper)
                    power(0.5,0.5,-0.5,-0.5);

                else power(0,0,0,0);
        }
    }

}
