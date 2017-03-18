package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

public class VisionRotateDegreesCommand extends Command{

	private Drivetrain drivetrain;
	private double startTime;
	private boolean lastOnTarget;
	private boolean finalFinished;
	private double directionalMultiplier;
	private double targetGyro;
	private double startGyro;
	private final int CAMERA_X_DIMENSION = 640;
	private final int ENDING_TOLERANCE = 3;
	
	private double x1,x2,x3,x4,x5;
	private double avgX;
	
	public VisionRotateDegreesCommand() {
		super("visionrotatedegreescommand");
		this.drivetrain=Components.getInstance().drivetrain;
		requires(drivetrain);
		// TODO Auto-generated constructor stub
	}

	protected void initialize(){
		System.out.println("VISION ROTATING");
		drivetrain.readSerial();
		drivetrain.getFrontLeftTalon().enableBrakeMode(true);
		drivetrain.getFrontRightTalon().enableBrakeMode(true);
		drivetrain.getBackLeftTalon().enableBrakeMode(true);
		drivetrain.getBackRightTalon().enableBrakeMode(true);
		drivetrain.raiseCenterWheel();
		startTime = System.currentTimeMillis();
		lastOnTarget = false;
		finalFinished = false;
		directionalMultiplier = 1;
		
		drivetrain.readSerialXY();
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		drivetrain.readSerialXY();
		x1 = drivetrain.getVisionX();
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		drivetrain.readSerialXY();
		x2 = drivetrain.getVisionX();
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		drivetrain.readSerialXY();
		x3 = drivetrain.getVisionX();
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		drivetrain.readSerialXY();
		x4 = drivetrain.getVisionX();
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		drivetrain.readSerialXY();
		x5 = drivetrain.getVisionX();
		
		avgX = (x1+x2+x3+x4+x5)/5.0;
		
		startGyro = drivetrain.getGyroAngle();
		targetGyro = startGyro - (320-avgX)*0.1125;
	}
	
	protected void execute(){

		if(targetGyro - drivetrain.getGyroAngle()>0)
			directionalMultiplier = -1;
		else 
			directionalMultiplier = 1;
		
		
		double rotate = 0.4;
		
		if(System.currentTimeMillis() > startTime + 100)
			rotate = 0.35;
		
		if (Math.abs(targetGyro - drivetrain.getGyroAngle()) < 15) {
			rotate*=0.75;
			if(Math.abs(targetGyro - drivetrain.getGyroAngle()) < 2)
				rotate = 0;
		}
		
		drivetrain.setValues(0, 0, -rotate*directionalMultiplier);								//set the motors on the left and right and the center speed to the calculated speeds

		if(finished() && lastOnTarget){
			finalFinished = true;
		}
		lastOnTarget = finished();
		
		System.out.println(targetGyro - drivetrain.getGyroAngle());
		System.out.println("xAvg: " + avgX);
	}
	
	public boolean finished(){
		return Math.abs(drivetrain.getGyroAngle() - targetGyro) < 2;
	}
	
	protected void end() {
		drivetrain.setValues(0.0, 0.0, 0.0);												//stop the drivetrain
	
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		drivetrain.getFrontLeftTalon().enableBrakeMode(false);
		drivetrain.getFrontRightTalon().enableBrakeMode(false);
		drivetrain.getBackLeftTalon().enableBrakeMode(false);
		drivetrain.getBackRightTalon().enableBrakeMode(false);	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return finalFinished;
	}

}
