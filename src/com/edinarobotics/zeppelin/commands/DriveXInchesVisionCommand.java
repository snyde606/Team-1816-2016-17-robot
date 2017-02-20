package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Command;

////////////////////////////////////////////
//										  //
//            Strafing variant            //
//       used for H-drive or mecanum      //
//										  //
//										  //
////////////////////////////////////////////

public class DriveXInchesVisionCommand extends Command {

	private Drivetrain drivetrain;
	private double initialPosition, target, ticks;
	private final double rampDownStart = 24;											//inches from the target at which the drivetrain slows down speed
	private double rampDownValue = 0;
	private double directionalMultiplier = 1;
	
	private final int CAMERA_X_DIMENSION = 640;
	private final int SLOW_RANGE_STRAFE = 30;
	private final int ENDING_TOLERANCE_STRAFE = 3;
	int x = 0;
	int y = 0;
	
	public DriveXInchesVisionCommand(double inches) {
		super("drivexinchescommand");
		drivetrain = Components.getInstance().drivetrain;
		ticks = -(int)inches*31.6923;										//conversion from inches to ticks
		rampDownValue = rampDownStart*31.6923;
		requires(drivetrain);
	} 
	
	@Override
	protected void initialize() {
		initialPosition = drivetrain.getFrontRightTalon().getEncPosition();
		target = ticks + initialPosition;
			
		drivetrain.getBackLeftTalon().enableBrakeMode(true);
		drivetrain.getBackRightTalon().enableBrakeMode(true);
		drivetrain.getFrontLeftTalon().enableBrakeMode(true);
		drivetrain.getFrontRightTalon().enableBrakeMode(true);
		drivetrain.getCenterTalon().enableBrakeMode(true);
		
	}
	
	@Override 
	protected void execute() {
		
		drivetrain.readSerialXY();
		x = drivetrain.getVisionX();
		y = drivetrain.getVisionY();
		
		if(x>CAMERA_X_DIMENSION)
			directionalMultiplier = -1;
		else 
			directionalMultiplier = 1;

		double deltaVision = CAMERA_X_DIMENSION/2-x; 	//how many pixels the target is off center		//320 is the center of the camera screen (camera used for vision tracking) in X-coordinates	
		
		double velocityForward = 0.4;
		double velocityStrafe = 0.3;
		
		//yStrafe control
		if (Math.abs(target - drivetrain.getFrontRightTalon().getEncPosition()) < rampDownValue || Math.abs(drivetrain.getFrontRightTalon().getEncPosition() - initialPosition) < 5*31.6923) 	//are we close enough to the target to slow down speed?
			velocityForward*=0.66;
		//end yStrafe control
		
		//xStrafe control
		if(Math.abs(deltaVision) < SLOW_RANGE_STRAFE){
			if(Math.abs(deltaVision)>ENDING_TOLERANCE_STRAFE)
				velocityStrafe *= 0.6;
			else
				velocityStrafe = 0;
		}
		//end xStrafe control
		
		System.out.println("Error: " + deltaVision);
		double quickerror = drivetrain.getFrontRightTalon().getEncPosition() - target;//print how many pixels off the target is from the center
		System.out.println("Forward remaining: " + quickerror);
		
		drivetrain.setDrivetrainSides(velocityForward, velocityForward, -velocityStrafe*directionalMultiplier);								//set the motors on the left and right and the center speed to the calculated speeds
	}

	@Override
	protected boolean isFinished() {
		return Math.abs(drivetrain.getFrontRightTalon().getEncPosition() - target) < 31*5;					//are encoder ticks past their target?
	}

	@Override
	protected void end() {
		drivetrain.setValues(0.0, 0.0, 0.0);												//stop the drivetrain
		
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		drivetrain.getBackLeftTalon().enableBrakeMode(false);
		drivetrain.getBackRightTalon().enableBrakeMode(false);
		drivetrain.getFrontLeftTalon().enableBrakeMode(false);
		drivetrain.getFrontRightTalon().enableBrakeMode(false);
		drivetrain.getCenterTalon().enableBrakeMode(false);
	}

	@Override
	protected void interrupted() {
		end();
	}

}