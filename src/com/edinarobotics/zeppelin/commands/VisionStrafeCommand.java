package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.utils.gamepad.Gamepad;
import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.Controls;
import com.edinarobotics.zeppelin.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Command;

////////////////////////////////////////////
//										  //
//            ROTATING variant            //
//       used for H-drive or mecanum      //
//										  //
//										  //
////////////////////////////////////////////

public class VisionStrafeCommand extends Command {

	private long commandLength /*in nanoseconds*/ = 40*1000000000;
	
	private Drivetrain drivetrain;
	private Gamepad gamepad;
	private final int CAMERA_X_DIMENSION = 640;
	private final int SLOW_RANGE = 30;
	private final int ENDING_TOLERANCE = 3;
	private double directionalMultiplier = 1;
	private boolean driverInterruptable = true;
	private boolean lastOnTarget;
	private boolean finalFinished;
	
	private long startTime;
	private double addPower = 0.0;
	private boolean powerAdded = false;
	private double encoderStart;
	
	private int x = 0;
	private int y = 0;
	
	public VisionStrafeCommand(boolean driverInterruptable, Gamepad gamepad) {
		super("visionstrafecommand");
		drivetrain = Components.getInstance().drivetrain;
		this.driverInterruptable = driverInterruptable;
		if(driverInterruptable)
			this.gamepad = gamepad;
		requires(drivetrain);
	} 
		
	@Override
	protected void initialize() {
		System.out.println("VISION STRAFING");
		drivetrain.readSerial();
		drivetrain.getCenterTalon().enableBrakeMode(true);
		drivetrain.raiseCenterWheel();
		startTime = System.currentTimeMillis();
		addPower = 0;
		powerAdded = false;
		lastOnTarget = false;
		finalFinished = false;
		encoderStart = drivetrain.getCenterTalon().getEncPosition();
	}

	@Override 
	protected void execute() {
		
		drivetrain.readSerialXY();
		x = drivetrain.getVisionX();
		y = drivetrain.getVisionY();

		if(x>CAMERA_X_DIMENSION/2)
			directionalMultiplier = -1;
		else 
			directionalMultiplier = 1;
		
		double deltaVision = CAMERA_X_DIMENSION/2-x; 	//how many pixels the target is off center		//320 is the center of the camera screen (camera used for vision tracking) in X-coordinates	
		
//		double strafe = 1;
		double rotate = 0.4;
		
//		//xStrafe control
		if(System.currentTimeMillis() > startTime + 100)
			rotate = 0.30;
		//end xStrafe control
		
		System.out.println("Error: " + deltaVision);									//print how many pixels off the target is from the center
		
		drivetrain.setValues(0, 0, -rotate*directionalMultiplier);								//set the motors on the left and right and the center speed to the calculated speeds

		if(finished() && lastOnTarget){
			finalFinished = true;
		}
		lastOnTarget = finished();
		
	}

	public boolean finished(){
		if(driverInterruptable)  
			if(Math.abs(gamepad.getLeftJoystick().getX()) > 0.2)
				return true;
		return x>(CAMERA_X_DIMENSION/2-ENDING_TOLERANCE) && x<(CAMERA_X_DIMENSION/2+ENDING_TOLERANCE);
	}
	
	@Override
	protected boolean isFinished() {
//		if(System.nanoTime() - startTime > commandLength)
//			return true;
		return finalFinished;					//are encoder ticks past their target?
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
		
		drivetrain.getCenterTalon().enableBrakeMode(false);
	}

	@Override
	protected void interrupted() {
		end();
	}

}