package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

public class RotateToAngleCWFromStartCommand extends Command{

	private double angle;
	
	private Drivetrain drivetrain;
	private double directionMultiplier = 1;
	private double target;
	private double startAngle;
	
	public RotateToAngleCWFromStartCommand(double angle){
		super("rotatetoanglecwfromstartcommand");
		this.drivetrain = Components.getInstance().drivetrain;
		this.angle = angle;
		requires(drivetrain);
	}
	
	protected void initialize(){
		System.out.println("ROTATING " + angle + "DEGREES CW");
		this.target = (drivetrain.getAutoGyroZero() + angle);
		
		if(target - drivetrain.getGyroAngle() < 0)
			directionMultiplier = -1;
		else
			directionMultiplier = 1;
		
		startAngle = drivetrain.getGyroAngle();
		
		drivetrain.getBackLeftTalon().enableBrakeMode(true);
		drivetrain.getBackRightTalon().enableBrakeMode(true);
		drivetrain.getFrontLeftTalon().enableBrakeMode(true);
		drivetrain.getFrontRightTalon().enableBrakeMode(true);
	}
	
	protected void execute(){
		double rotation = 0.5;
		
		if(Math.abs(target-drivetrain.getGyroAngle())<20 || Math.abs(drivetrain.getGyroAngle()-startAngle)<5)
			rotation*=0.6;
		
		rotation*= directionMultiplier;
		
		drivetrain.setValues(0, 0, rotation);
		
		System.out.println(drivetrain.getGyroAngle());
	}
	
	protected boolean isFinished(){
		return Math.abs(target-drivetrain.getGyroAngle())<4;
	}
	
	protected void end(){
		drivetrain.setValues(0, 0, 0);
		
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
}
