package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;


public class RobotHardware {

    private boolean isInTake;
    private boolean isShoot;

    public DcMotor M1;
    public DcMotor M2;
    public DcMotor M3;
    public DcMotor M4;
    public DcMotor M5;
    public DcMotor M6;
    public DcMotor M7;
    public Servo S1;

    HardwareMap hwMap = null;
    private ElapsedTime period  = new ElapsedTime();


    public void init(HardwareMap ahwMap){

        hwMap = ahwMap;

        isInTake = false;
        isShoot = false;
        // Define and Initialize Motors
        M1  = hwMap.get(DcMotor.class, "M1");
        M2  = hwMap.get(DcMotor.class, "M2");
        M3  = hwMap.get(DcMotor.class, "M3");
        M4  = hwMap.get(DcMotor.class, "M4");
        M5  = hwMap.get(DcMotor.class, "M5");
        M6  = hwMap.get(DcMotor.class, "M6");
        M7  = hwMap.get(DcMotor.class, "M7");
        S1  = hwMap.get(Servo.class, "S1");
        S1.setPosition(1);



    }

    public void gamepadMoving(Gamepad Gamepad){
        Gamepad gamepad = Gamepad;


        /* ---------------Mechanum wheels moving----------------- */
        /*
                ROBOT  Model

      M1 0-----0 M3
         |     |
         |     |
         |     |
      M2 0-----0 M4

        To move forward motors M1 and M2 have to Spin in one direction(let it be 1) and motors M3 and M4 in another direction(-1)
        To move left motors M1 and m3 have to spin in one direction (1) and motors M2 and M4 in another one (-1)
        To spin all motors have to spin in one same direction
         */
        M1.setPower(-gamepad.right_stick_y +gamepad.right_stick_x + gamepad.left_stick_x);
        M2.setPower(-gamepad.right_stick_y-gamepad.right_stick_x + gamepad.left_stick_x);
        M3.setPower(gamepad.right_stick_y+gamepad.right_stick_x + gamepad.left_stick_x);
        M4.setPower(gamepad.right_stick_y-gamepad.right_stick_x + gamepad.left_stick_x);

    }
    public void resetEncoder(){


    M1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    M2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    M3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    M4.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


    M1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    M2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    M3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    M4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }


    public void shootServo(Gamepad Gamepad) {
        Gamepad gamepad = Gamepad;
    if(gamepad.right_bumper){
        S1.setPosition(0.7);

        synchronized(this) {
            try {
                wait(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        S1.setPosition(1);

    }
    }

    public void inTakeMotors(Gamepad Gamepad) {
        Gamepad gamepad = Gamepad;
        if(gamepad.left_bumper&&gamepad.x&&!isInTake){
            M5.setPower(1);
            isInTake = true;
        }else if(gamepad.left_bumper&&gamepad.x&&isInTake){
            M5.setPower(0);
            isInTake = false;
        }
    }

    public void shootMotors(Gamepad Gamepad) {
        Gamepad gamepad = Gamepad;
        if(gamepad.left_bumper&&gamepad.y&&!isShoot){
            M6.setPower(1);
            M7.setPower(-1);
            isShoot = true;
        }else if(gamepad.left_bumper&&gamepad.y&&isShoot){
            M6.setPower(0);
            M7.setPower(0);
            isShoot = false;
        }
    }


/*    public void moveForward(int number, double power){
        if (!M1.isBusy()&&!M2.isBusy()&&!M3.isBusy()&&!M4.isBusy()){

            resetEncoder();


            M1.setTargetPosition(number);
            M2.setTargetPosition(number);
            M3.setTargetPosition(number);
            M4.setTargetPosition(number);

            M1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            M2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            M3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            M4.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            M1.setPower(power);
            M2.setPower(power);
            M3.setPower(power);
            M4.setPower(power);

            while (M1.isBusy()&&M2.isBusy()&&M3.isBusy()&&M4.isBusy()){   }

            M1.setPower(0);
            M2.setPower(0);
            M3.setPower(0);
            M4.setPower(0);
    }}

    public void moveLeft(){

        M1.setTargetPosition(1120);
        M2.setTargetPosition(1120);
        M3.setTargetPosition(1120);
        M4.setTargetPosition(1120);
        M1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        M2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        M3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        M4.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        M1.setPower(0.5);
        M2.setPower(-0.5);
        M3.setPower(0.5);
        M4.setPower(-0.5);
    }*/

}
