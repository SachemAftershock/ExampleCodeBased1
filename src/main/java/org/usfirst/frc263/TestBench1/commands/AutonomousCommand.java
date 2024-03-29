// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc263.TestBench1.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;
import org.usfirst.frc263.TestBench1.Robot;

/**
 *
 */
public class AutonomousCommand extends Command {

    private double currentAutoSpeed = 0.0;
    private final double rampTimeInSec = 5.0;
    private final double roborioIntervalInSec = 0.020;
    private final double numberRoborioIntervalsPerSpeedUpdate = rampTimeInSec / roborioIntervalInSec;
    private final double speedStepSize = (Robot.driveTrain.speedMax - Robot.driveTrain.speedMin) / numberRoborioIntervalsPerSpeedUpdate;
    private Timer autoTimer = new Timer();
    private boolean rampingUp = true;
    private long totalIterationsCompleted = 0;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public AutonomousCommand() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        currentAutoSpeed = 0.0;
        totalIterationsCompleted = 0;
        autoTimer.reset();
        autoTimer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        
            if (rampingUp) {
                if ((currentAutoSpeed += speedStepSize) >= Robot.driveTrain.speedMax) {
                    currentAutoSpeed = Robot.driveTrain.speedMax;
                    rampingUp = false;
                    System.out.println("Max Speed Acheived.");
                    totalIterationsCompleted++;
                }
            } else {
                if ((currentAutoSpeed -= speedStepSize) <= Robot.driveTrain.speedMin) {
                    currentAutoSpeed = Robot.driveTrain.speedMin;
                    rampingUp = true;
                    System.out.println("Min Speed Acheived.");
                }
            }
            Robot.driveTrain.currentSpeed = currentAutoSpeed;
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        if (totalIterationsCompleted >= 5) {
            autoTimer.stop();
            double d = autoTimer.get();
            System.out.println("Done Auto Seconds: " + Double.toString(d));
            Robot.driveTrain.currentSpeed = 0.0;
            return(true);
        } else {
            return(false);
        }
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        autoTimer.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        
    }
}
