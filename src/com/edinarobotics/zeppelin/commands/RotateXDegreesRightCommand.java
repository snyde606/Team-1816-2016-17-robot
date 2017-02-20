package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Drivetrain;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.Command;

public class RotateXDegreesRightCommand extends Command {
	
	private Drivetrain drivetrain;
	
	private double velocity;
	private double initialPosition, target, ticks;
	private double degree;
	private double startingLeft, startingRight;
	private final double rampDownStart = 24;
	private final double rampDownValue;
	private double startingGyro;
	
	public RotateXDegreesRightCommand(double degree, double velocity) {
		super("rotatexdegreescommand");
		this.velocity = velocity;
		drivetrain = Components.getInstance().drivetrain;
		this.degree = degree;
		rampDownValue = ((rampDownStart * 720) * 10) / 13;
		requires(drivetrain);
	}
	
	@Override
	protected void initialize() {
		initialPosition = drivetrain.getBackRightTalon().getEncPosition();
		target = drivetrain.getGyroAngle() + degree;
		
		startingLeft = drivetrain.getBackLeftTalon().getEncPosition();
		startingRight = drivetrain.getBackRightTalon().getEncPosition();
		
		drivetrain.getBackLeftTalon().enableBrakeMode(true);
		drivetrain.getBackRightTalon().enableBrakeMode(true);
		drivetrain.getFrontLeftTalon().enableBrakeMode(true);
		drivetrain.getFrontRightTalon().enableBrakeMode(true);
		
	}

	@Override 
	protected void execute() {
		
		System.out.println("Encoder Right: " + drivetrain.getBackRightTalon().getEncPosition());
		System.out.println("Encoder Left: " + drivetrain.getBackLeftTalon().getEncPosition());
		System.out.println("Target: " + target);
		
		double currentLeft = drivetrain.getBackLeftTalon().getEncPosition() - startingLeft;
		double currentRight = drivetrain.getBackRightTalon().getEncPosition() - startingRight;
		
		double left, right;
		
		if (target - drivetrain.getGyroAngle() < 15) {
			right = -(velocity*.95) * (((currentRight - currentLeft) / 5000000) + 1) / 4;
			left = (velocity * (((currentLeft - currentRight) / 5000000) + 1) / 4);
		} else {
			right = -(velocity*.95) * (((currentRight - currentLeft) / 5000000) + 1) / 2;
			left = (velocity * (((currentLeft - currentRight) / 5000000) + 1)) / 2;
		}
		
//		double right = (velocity*.95);
//		double left = velocity;

		drivetrain.setDrivetrainSides(left, right);
		
		System.out.println("Gyro: " + drivetrain.getGyroAngle());
	}

	@Override
	protected boolean isFinished() {
		return drivetrain.getGyroAngle() >= target;
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
	}

	@Override
	protected void interrupted() {
		end();
	}

}
