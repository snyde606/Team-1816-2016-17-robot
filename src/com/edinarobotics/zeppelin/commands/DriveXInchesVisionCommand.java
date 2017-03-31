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
	
	private boolean tooClose = false;
	private int wiggleCounter = 0;
	
	private final int CAMERA_X_DIMENSION = 640;
	private final int SLOW_RANGE_STRAFE = 30;
	private final int ENDING_TOLERANCE_STRAFE = 3;
	int x = 0;
	int a = 0;
	
	public DriveXInchesVisionCommand(double inches) {
		super("drivexinchescommand");
		drivetrain = Components.getInstance().drivetrain;
//		ticks = -(int)(((inches * 41.2*1.018) * 10) / 13); //COMPETITION CARPET CONSTANT										//conversion from inches to ticks
		ticks = -(int)(((inches * 36*1.018) * 10) / 13);										//conversion from inches to ticks
		rampDownValue = rampDownStart*31.6923;
		requires(drivetrain);
	} 
	
	@Override
	protected void initialize() {
		System.out.println("INIT");
		
		initialPosition = drivetrain.getFrontLeftTalon().getEncPosition();
		target = initialPosition - ticks; //switch when switching encoder direction
			
		drivetrain.getBackLeftTalon().enableBrakeMode(true);
		drivetrain.getBackRightTalon().enableBrakeMode(true);
		drivetrain.getFrontLeftTalon().enableBrakeMode(true);
		drivetrain.getFrontRightTalon().enableBrakeMode(true);
		drivetrain.getCenterTalon().enableBrakeMode(true);
		
		drivetrain.raiseCenterWheel();
		
		tooClose = false;
		wiggleCounter = 0;
		
	}
	
	@Override 
	protected void execute() {
		
		drivetrain.readSerialXY();
		x = drivetrain.getVisionX();
		a = drivetrain.getVisionArea();
		
		if(a>30000)
			x=320;
		
//UNTESTED	
//		if(a>30000)
//			tooClose = true;
//		if(tooClose)
//			x = 320;
//		
//		if(Math.abs(target - drivetrain.getFrontLeftTalon().getEncPosition()) < 170){
//			wiggleCounter+=2;
//			if((wiggleCounter%10)%2 == 0)
//				x = 480;
//			else
//				x = 160;
//		}
//UNTESTED
		
		double deltaVision = CAMERA_X_DIMENSION/2-x; 	//how many pixels the target is off center		//320 is the center of the camera screen (camera used for vision tracking) in X-coordinates	
		
		double velocityForward = 0.34;
		
		//yStrafe control
		if (Math.abs(target - drivetrain.getFrontLeftTalon().getEncPosition()) < rampDownValue || Math.abs(drivetrain.getFrontLeftTalon().getEncPosition() - initialPosition) < 5*31.6923) 	//are we close enough to the target to slow down speed?
			velocityForward*=0.66;
		//end yStrafe control
		
		System.out.println("Error: " + deltaVision);
		double quickerror = drivetrain.getFrontLeftTalon().getEncPosition() - target;//print how many pixels off the target is from the center
		System.out.println("Forward remaining: " + quickerror);
		
		System.out.println("\n" + velocityForward + "\n");
		drivetrain.setDrivetrainSides(velocityForward+(deltaVision/2000.0), 1.03*(velocityForward-(deltaVision/2000.0)),0);		//comp: 800						//set the motors on the left and right and the center speed to the calculated speeds
	}

	@Override
	protected boolean isFinished() {
		return Math.abs(drivetrain.getFrontLeftTalon().getEncPosition() - target) < 31*5;					//are encoder ticks past their target?
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