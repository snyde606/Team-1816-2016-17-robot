package com.edinarobotics.zeppelin.subsystems;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.edinarobotics.utils.subsystems.Subsystem1816;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter extends Subsystem1816{

	private CANTalon rightShooterTalon, leftShooterTalon;
	private double shooterSpeed = 0.0;
	
	private final double P = 0.013;
	private final double I = 0.00012;
	private final double D = 2.0;
	private final double F = 0.0;
	
	public Shooter(int rightShooterTalon, int leftShooterTalon){
		this.rightShooterTalon = new CANTalon(rightShooterTalon);
		this.rightShooterTalon.changeControlMode(TalonControlMode.Speed);
		this.rightShooterTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		this.leftShooterTalon = new CANTalon(leftShooterTalon);
		this.leftShooterTalon.changeControlMode(TalonControlMode.Follower);
//		this.leftShooterTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
//		this.leftShooterTalon.setPID(P, I, D);
//		this.leftShooterTalon.setF(F);
		this.leftShooterTalon.set(rightShooterTalon);
		this.shooterSpeed = 0;
	}
	
	@Override
	public void update() {
		this.rightShooterTalon.setPID(SmartDashboard.getNumber("Shooter-P", P), SmartDashboard.getNumber("Shooter-I", I), SmartDashboard.getNumber("Shooter-D", D));
		this.rightShooterTalon.setF(SmartDashboard.getNumber("Shooter-F", F));
		rightShooterTalon.set(shooterSpeed);
//		leftShooterTalon.set(shooterSpeed);
//		int percentPower = (int)(leftShooterTalon.getOutputVoltage()/0.12);
//		System.out.println("Error: " + leftShooterTalon.getError() + "\t\tRPM: " + leftShooterTalon.getSpeed() + "\t\t%Power: " + percentPower + "\t\tTicks: " + leftShooterTalon.getEncPosition());
	}
	
	public void setShooterTalon(double sSpeed){
		shooterSpeed = sSpeed;
		update();
	}
	
	public double getShooterTalon() {
		return shooterSpeed;
	}
	
	public CANTalon getRightShooterTalon(){
		return rightShooterTalon;
	}
	public CANTalon getLeftShooterTalon(){
		return leftShooterTalon;
	}
	
}
