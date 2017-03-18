package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

public class ApproachLiftVisionCommand extends Command{

	private Drivetrain drivetrain;
	private double velocityForward;
	private double velocityStrafe;
	
	private final int CAMERA_X_DIMENSION = 640;
	private final int SLOW_RANGE_STRAFE = 30;
	private final int ENDING_TOLERANCE_STRAFE = 3;
	
	private final int ENDING_AREA = 32000;
	private final int SLOW_RANGE_AREA = 6000;
	private final int ENDING_TOLERANCE_AREA = 2000;
	
	double x = 0;
	double y = 0;
	double area = 0;
	
	public ApproachLiftVisionCommand(double velocityForward, double velocityStrafe){
		super("approachliftvisioncommand");
		this.drivetrain = Components.getInstance().drivetrain;
		this.velocityForward = velocityForward;
		this.velocityStrafe = velocityStrafe;
		requires(drivetrain);
	}
	
	protected void initialize(){
		drivetrain.getBackLeftTalon().enableBrakeMode(true);
		drivetrain.getBackRightTalon().enableBrakeMode(true);
		drivetrain.getFrontLeftTalon().enableBrakeMode(true);
		drivetrain.getFrontRightTalon().enableBrakeMode(true);
		drivetrain.getCenterTalon().enableBrakeMode(true);
		
	}
	
	protected void execute(){
		drivetrain.readSerialXY();
		x = drivetrain.getVisionX();
		y = drivetrain.getVisionY();
		area = drivetrain.getVisionArea();

		double lateralError = CAMERA_X_DIMENSION/2-x; 	//how many pixels the target is off center		//320 is the center of the camera screen (camera used for vision tracking) in X-coordinates	
		double areaError = ENDING_AREA-area;
		
		double left, right, strafe;
		
		//xStrafe control
			if(lateralError<SLOW_RANGE_STRAFE && lateralError>-SLOW_RANGE_STRAFE){
				if(lateralError>ENDING_TOLERANCE_STRAFE)
					strafe = velocityStrafe/1.5;
				else if(lateralError<-ENDING_TOLERANCE_STRAFE)
					strafe = -velocityStrafe/1.5;
				else
					strafe = 0;
			}
			else{
				if(lateralError>0)
					strafe = velocityStrafe;
				else
					strafe = -velocityStrafe;
			}
		//end xStrafe control
		
		//yStrafe control
			if(areaError<SLOW_RANGE_AREA){
				left = velocityForward/1.5;
				right = velocityForward/1.5;
			}
			else{
				left = velocityForward;
				right = velocityForward;
			}
		//end yStrafe control
		
		System.out.println("Lateral Error: " + lateralError + "\t\tArea Error: " + areaError);//print how many pixels off the target is from the center
		System.out.println("Area: " + area);
		
		drivetrain.setDrivetrainSides(left, right, -strafe);								//set the motors on the left and right and the center speed to the calculated speeds
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return area-ENDING_AREA>-ENDING_TOLERANCE_AREA;
	}
	
	protected void end(){
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
