package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Command;

public class DrivingVisionTrackButtonCommand extends Command {

	private Drivetrain drivetrain;
	private double startTime = 0;
	
	private boolean stuckVision = false;
	
	int x = 0;
	int a = 0;
	
	public DrivingVisionTrackButtonCommand() {
		super("drivingvisiontrackbuttoncommand");
		drivetrain = Components.getInstance().drivetrain;
		requires(drivetrain);
	} 
	
	@Override
	protected void initialize() {
		System.out.println("INIT DRIVING VISION BUTTON");
		
		drivetrain.setVisionButtonPressed(false);
		
		drivetrain.getBackLeftTalon().enableBrakeMode(true);
		drivetrain.getBackRightTalon().enableBrakeMode(true);
		drivetrain.getFrontLeftTalon().enableBrakeMode(true);
		drivetrain.getFrontRightTalon().enableBrakeMode(true);
		drivetrain.getCenterTalon().enableBrakeMode(true);
		
		drivetrain.raiseCenterWheel();
		
		stuckVision = false;
		startTime = System.currentTimeMillis();
		
	}
	
	@Override 
	protected void execute() {
		
		stuckVision = drivetrain.readSerialXY();
		x = drivetrain.getVisionX();
		a = drivetrain.getVisionArea();
		
		if(a>20000)
			x=320;
		
		double deltaVision = 310-x; 	//how many pixels the target is off center		//320 is the center of the camera screen (camera used for vision tracking) in X-coordinates	
		
		double velocityForward = 0.275;
		
		System.out.println("\nError: " + deltaVision + "\n");
		
		if(Math.abs(startTime-System.currentTimeMillis())>50)
			drivetrain.setDrivetrainSides((velocityForward-(deltaVision/1500.0)), /*1.03* COMPETITION BOT RELIC?????*/(velocityForward+(deltaVision/1500.0)),0);		//comp: 800						//set the motors on the left and right and the center speed to the calculated speeds
	}

	@Override
	protected boolean isFinished() {
		return drivetrain.isVisionButtonPressed() || stuckVision;
	}

	@Override
	protected void end() {
		drivetrain.setValues(0.0, 0.0, 0.0);												//stop the drivetrain
		
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