package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

public class DriveXRightYForwardInchesCommand extends Command{

	private Drivetrain drivetrain;
	
	private double initialForward, initialStrafe;
	private double targetForward, targetStrafe;
	private double inchesForward, inchesRight;
	
	private double initialAngle;
	
	private double directionalMultiplierForward = 1;
	private double directionalMultiplierStrafe = 1;
	
	public DriveXRightYForwardInchesCommand(double inchesRight, double inchesForward){
		super("drivexrightyforwardinchescommand");
		this.drivetrain = Components.getInstance().drivetrain;
		this.inchesForward = inchesForward;
		this.inchesRight = inchesRight;
		requires(drivetrain);
	}
	
	protected void initialize(){
		System.out.println("INIT DIAGONAL DRIVE");
		
		initialForward = drivetrain.getFrontLeftTalon().getEncPosition();
		initialStrafe = drivetrain.getCenterTalon().getEncPosition();
		targetForward = initialForward + (int)(inchesForward*31.6923);
		targetStrafe = initialStrafe + (int)(inchesRight*31.6923);
		
		initialAngle = drivetrain.getGyroAngle();
	}
	
	protected void execute(){
		double leftSpeed = 0.5;
		double rightSpeed = 0.5;
		double strafeSpeed = 0.5;
		
		double deltaDegree = drivetrain.getGyroAngle() - initialAngle;
		double leftRotationCorrect = deltaDegree / 50;
		double rightRotationCorrect = -deltaDegree / 50;
		
		if(targetForward < drivetrain.getFrontLeftTalon().getEncPosition())
			directionalMultiplierForward = -1;
		else 
			directionalMultiplierForward = 1;
		
		if(targetStrafe < drivetrain.getCenterTalon().getEncPosition())
			directionalMultiplierStrafe = -1;
		else
			directionalMultiplierStrafe = 1;
		
		if(Math.abs(targetForward - drivetrain.getFrontLeftTalon().getEncPosition()) < 6*31.6923){
			leftSpeed*=0.5;
			rightSpeed*=0.5;
			leftRotationCorrect*=0.7;
			rightRotationCorrect*=0.7;
		}
		
		if(Math.abs(targetStrafe - drivetrain.getCenterTalon().getEncPosition()) < 6*31.6923){
			strafeSpeed*=0.6;
			leftRotationCorrect*=0.7;
			rightRotationCorrect*=0.7;
		}
			
		drivetrain.setDrivetrainSides(leftSpeed*directionalMultiplierForward + leftRotationCorrect, rightSpeed*directionalMultiplierForward + rightRotationCorrect, strafeSpeed*directionalMultiplierStrafe);
		
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return Math.abs(targetForward-drivetrain.getFrontLeftTalon().getEncPosition()) < 0.75*31.6923 && Math.abs(targetStrafe-drivetrain.getCenterTalon().getEncPosition()) < 0.75*31.6923;
	}
	
	protected void end(){
		drivetrain.setValues(0, 0, 0);
	}

}
