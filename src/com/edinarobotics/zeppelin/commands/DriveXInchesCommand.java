package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Drivetrain;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.Command;

public class DriveXInchesCommand extends Command {

	private Drivetrain drivetrain;
	
	private double velocity;
	private double initialPosition, target, ticks, initialAngle;
	private final double rampDownStart = 24;
	private final double rampDownValue;
	private double inchTarget;
	private double startTime;
	
	public DriveXInchesCommand(double inches, double velocity) {
		super("drivexinchescommand");
		this.velocity = velocity;
		this.inchTarget = inches;
		drivetrain = Components.getInstance().drivetrain;
//		ticks = (int)(((inches * 41.2*1.018) * 10) / 13);	//COMPETITION CARPET CONSTANT				//OG Zeppelin constants	
		ticks = (int)(((inches * 36*1.018) * 10) / 13);					//OG Zeppelin constants	
		rampDownValue = ((rampDownStart * 42) * 10) / 13;
		requires(drivetrain);
	}
	
	@Override
	protected void initialize() {
		System.out.println("DRIVING " + inchTarget + " INCHES");
		initialPosition = drivetrain.getFrontLeftTalon().getEncPosition();
		target = ticks + initialPosition;
		initialAngle = drivetrain.getGyroAngle();
		
		drivetrain.raiseCenterWheel();
		
		drivetrain.getBackLeftTalon().enableBrakeMode(true);
		drivetrain.getBackRightTalon().enableBrakeMode(true);
		drivetrain.getFrontLeftTalon().enableBrakeMode(true);
		drivetrain.getFrontRightTalon().enableBrakeMode(true);
	}

	@Override 
	protected void execute() {
		
		double deltaDegree = drivetrain.getGyroAngle() - initialAngle;
		
		double left, right;
		
		if (ticks>0)
			if ((target - drivetrain.getFrontLeftTalon().getEncPosition()) < rampDownValue) {				//poor
				right = (velocity*.95) * (((deltaDegree) / 50) + 1) / 1.4;								//man's
				left = velocity * (((-deltaDegree) / 50) + 1) /1.4;										//PID
			} else {																					//
				right = (velocity*.95) * (((deltaDegree) / 50) + 1);									//
				left = velocity * (((-deltaDegree) / 50) + 1);											//
			}																							//
		else
			if ((target - drivetrain.getFrontLeftTalon().getEncPosition()) > -rampDownValue) {				//poor
				right = (-velocity*.95) * (((deltaDegree) / 50) + 1) / 1.4;								//man's
				left = -velocity * (((-deltaDegree) / 50) + 1) / 1.4;										//PID
			} else {																					//
				right = (-velocity*.95) * (((deltaDegree) / 50) + 1);									//
				left = -velocity * (((-deltaDegree) / 50) + 1);											//
			}
		
//		System.out.println("Error: " + deltaDegree);
//		Github is best. Github is love. Github is dead. Long live Github.		
//		double right = (velocity*.95);
//		double left = velocity;

		drivetrain.setDrivetrainSides(left, right);
	}

	@Override
	protected boolean isFinished() {
//		System.out.println("Off by: " + (drivetrain.getFrontLeftTalon().getEncPosition() - target));
		if(ticks>0)
			return drivetrain.getFrontLeftTalon().getEncPosition() > target;
		else
			return drivetrain.getFrontLeftTalon().getEncPosition() < target;	
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
		
//		drivetrain.getBackLeftTalon().enableBrakeMode(false);
//		drivetrain.getBackRightTalon().enableBrakeMode(false);
//		drivetrain.getFrontLeftTalon().enableBrakeMode(false);
//		drivetrain.getFrontRightTalon().enableBrakeMode(false);
	}

	@Override
	protected void interrupted() {
		end();
	}

}
