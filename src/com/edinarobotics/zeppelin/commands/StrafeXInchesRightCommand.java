package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Drivetrain;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.Command;

public class StrafeXInchesRightCommand extends Command {

	private Drivetrain drivetrain;
	
	private double directionalMultiplier = 1;
	private double initialPosition, target, ticks, initialAngle;
	private final double rampDownStart = 24;
	private final double rampDownValue;
	
	public StrafeXInchesRightCommand(double inches) {
		super("drivexinchescommand");
		drivetrain = Components.getInstance().drivetrain;
		ticks = (int)(inches*31.6923);					//OG Zeppelin constants	
		rampDownValue = rampDownStart*31.6923;
		requires(drivetrain);
	}
	
	@Override
	protected void initialize() {
		initialPosition = drivetrain.getCenterTalon().getEncPosition();
		target = ticks + initialPosition;
		initialAngle = drivetrain.getGyroAngle();
		
		if(ticks<0)
			directionalMultiplier = -1;
		else
			directionalMultiplier = 1;
		
		drivetrain.getBackLeftTalon().enableBrakeMode(true);
		drivetrain.getBackRightTalon().enableBrakeMode(true);
		drivetrain.getFrontLeftTalon().enableBrakeMode(true);
		drivetrain.getFrontRightTalon().enableBrakeMode(true);
		drivetrain.getCenterTalon().enableBrakeMode(true);
	}

	@Override 
	protected void execute() {
		
		double deltaDegree = drivetrain.getGyroAngle() - initialAngle;
		
		double strafeSpeed = 1.0;
		double rotationSpeed = 0;
		
		if (Math.abs(target - drivetrain.getCenterTalon().getEncPosition()) < rampDownValue || Math.abs(drivetrain.getCenterTalon().getEncPosition() - initialPosition) < rampDownValue) 	
			strafeSpeed*= 0.5;																				
		
		
		rotationSpeed = (int)deltaDegree/75.0;
		
		System.out.println("Error: " + deltaDegree);
		double quickerror = drivetrain.getCenterTalon().getEncPosition() - target;
		System.out.println("Center Encoder error: " + quickerror);
		
		drivetrain.setValues(strafeSpeed*directionalMultiplier, 0.0, rotationSpeed);
	}

	@Override
	protected boolean isFinished() {
		if(ticks>0)
			return drivetrain.getCenterTalon().getEncPosition() > target;
		else
			return drivetrain.getCenterTalon().getEncPosition() < target;	
	}

	@Override
	protected void end() {
		drivetrain.setValues(0.0, 0.0, 0.0);
		
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
