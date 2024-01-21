package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class autonom extends LinearOpMode {
    private DcMotor lr = null;
    private DcMotor lf = null;
    private DcMotor rf = null;
    private DcMotor rr = null;
    private ElapsedTime runtime = new ElapsedTime();


    void mers(double lfp,double lrp, double rfp,double rrp, double timp) {
        while(opModeIsActive() && runtime.seconds() < timp) {
            lf.setPower(lfp);
            rf.setPower(rfp);
            lr.setPower(lrp);
            rr.setPower(rrp);
        }

        lf.setPower(0);
        lr.setPower(0);
        rf.setPower(0);
        rr.setPower(0);

    }

    public void runOpMode() {
        lf = hardwareMap.get(DcMotor.class, "lf");
        lr = hardwareMap.get(DcMotor.class, "lr");
        rf = hardwareMap.get(DcMotor.class, "rf");
        rr = hardwareMap.get(DcMotor.class, "rr");
        rf.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        runtime.reset();
        while(opModeIsActive()) {

            mers(0.5,0.5,0.5,0.5, 1.0);
            runtime.reset();
            mers(0.5,0.5,-0.5,-0.5, 0.5);
            runtime.reset();
            mers(0.5,0.5,0.5,0.5, 1.0);
            runtime.reset();
            mers(-0.5,-0.5,0.5,0.5, 1.0);
            runtime.reset();
            mers(-0.5,-0.5,-0.5,-0.5,1.0);
            runtime.reset();



    }

}
}
