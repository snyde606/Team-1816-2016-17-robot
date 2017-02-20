package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.utils.gamepad.Gamepad;
import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.Controls;
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

public class VisionStrafeCommand extends Command {

	private long commandLength /*in nanoseconds*/ = 5*1000000000;
	
	private Drivetrain drivetrain;
	private Gamepad gamepad;
	private final int CAMERA_X_DIMENSION = 640;
	private final int SLOW_RANGE = 30;
	private final int ENDING_TOLERANCE = 3;
	private double directionalMultiplier = 1;
	private boolean driverInterruptable = true;
	
	private long startTime;
	private double addPower = 0.0;
	private boolean powerAdded = false;
	private double encoderStart;
	
	private int x = 0;
	private int y = 0;
	
	public VisionStrafeCommand(boolean driverInterruptable) {
		super("visionstrafecommand");
		drivetrain = Components.getInstance().drivetrain;
		this.driverInterruptable = driverInterruptable;
		this.gamepad = Controls.getInstance().gamepad0;
		requires(drivetrain);
	} 
		
	@Override
	protected void initialize() {
		drivetrain.getCenterTalon().enableBrakeMode(true);
		drivetrain.lowerCenterWheel();
		startTime = System.nanoTime();
		addPower = 0;
		powerAdded = false;
		encoderStart = drivetrain.getCenterTalon().getEncPosition();
	}

	@Override 
	protected void execute() {
		
		if(System.nanoTime()-startTime > (long)1000000000 && !powerAdded && Math.abs(encoderStart - drivetrain.getCenterTalon().getEncPosition())<60){
			addPower+=0.25;
			powerAdded = true;
		}
		
		drivetrain.readSerialXY();
		x = drivetrain.getVisionX();
		y = drivetrain.getVisionY();

		if(x>CAMERA_X_DIMENSION)
			directionalMultiplier = -1;
		else 
			directionalMultiplier = 1;
		
		double deltaVision = CAMERA_X_DIMENSION/2-x; 	//how many pixels the target is off center		//320 is the center of the camera screen (camera used for vision tracking) in X-coordinates	
		
		double strafe = 0.75+addPower;
		
		//xStrafe control
		if(Math.abs(deltaVision)<SLOW_RANGE)
			strafe*=0.75;
		
		//end xStrafe control
		
		System.out.println("Error: " + deltaVision);									//print how many pixels off the target is from the center
		
		drivetrain.setDrivetrainSides(0.0, 0.0, -strafe*directionalMultiplier);								//set the motors on the left and right and the center speed to the calculated speeds
	}

	@Override
	protected boolean isFinished() {
		if(System.nanoTime() - startTime > commandLength)
			return true;
		if(driverInterruptable)
			return Math.abs(gamepad.getLeftJoystick().getX()) > 0.2 || x>(CAMERA_X_DIMENSION/2-ENDING_TOLERANCE) && x<(CAMERA_X_DIMENSION/2+ENDING_TOLERANCE);
		return x>(CAMERA_X_DIMENSION/2-ENDING_TOLERANCE) && x<(CAMERA_X_DIMENSION/2+ENDING_TOLERANCE);					//are encoder ticks past their target?
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