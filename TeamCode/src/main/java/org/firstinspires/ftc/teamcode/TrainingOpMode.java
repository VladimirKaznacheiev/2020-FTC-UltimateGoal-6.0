


package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;




@TeleOp(name="TrainingOpMode", group="Linear Opmode")
//@Disabled
public class TrainingOpMode extends LinearOpMode {

    public DcMotor M1;

    private ElapsedTime runtime = new ElapsedTime();


    @Override
    public void runOpMode() {
        telemetry.addData(" ", "NoosphereRoboticsTeam");
        telemetry.update();

        RobotHardware robot = new RobotHardware();//creating object robot
        robot.init(hardwareMap);//variable initialization
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();


        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            robot.gamepadMoving(gamepad1);
            robot.inTakeMotors(gamepad1);
            robot.shootMotors(gamepad1);
            robot.shootServo(gamepad1);

            telemetry.update();
        }
    }
}
