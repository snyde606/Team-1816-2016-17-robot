package com.edinarobotics.zeppelin.subsystems;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.edinarobotics.utils.subsystems.Subsystem1816;

import edu.wpi.first.wpilibj.Encoder;

public class Shooter extends Subsystem1816{

	private CANTalon rightShooterTalon, leftShooterTalon;
	private double leftShooterSpeed = 0.0;
	private double rightShooterSpeed = 0.0;	
	private double incrementedShooterSpeed = 3300;
	
	private final double P = 0.013;
	private final double I = 0.00012;
	private final double D = 2.0;
	private final double F = 0.0;
	
	public Shooter(int rightShooterTalon, int leftShooterTalon){
		this.rightShooterTalon = new CANTalon(rightShooterTalon);
		this.rightShooterTalon.changeControlMode(TalonControlMode.Speed);
		this.rightShooterTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		this.rightShooterTalon.setPID(P, I, D);
		this.rightShooterTalon.setF(F);
		this.leftShooterTalon = new CANTalon(leftShooterTalon);
		this.leftShooterTalon.changeControlMode(TalonControlMode.Speed);
//		this.leftShooterTalon.changeControlMode(TalonControlMode.PercentVbus);
		this.leftShooterTalon.setFeedbackDevice(FeedbackDevice.PulseWidth);
		this.leftShooterTalon.setPID(P, I, D);
		this.leftShooterTalon.setF(F);
		this.leftShooterSpeed = 0.0;
		this.rightShooterSpeed = 0.0;
		this.leftShooterTalon.reverseSensor(true);
	}
	
	public void addIncrementSpeed(double inc){
		incrementedShooterSpeed += inc;
	}
	
	@Override
	public void update() {
		rightShooterTalon.set(rightShooterSpeed);
		leftShooterTalon.set(leftShooterSpeed);
//		System.out.println(leftShooterSpeed);
	}
	
	public void setShooterTalons(double lSpeed, double rSpeed){
		leftShooterSpeed = lSpeed;
		rightShooterSpeed = rSpeed;
		update();
	}
	
	public double getIncrementedShooterSpeed(){
		return incrementedShooterSpeed;
	}
	
	public CANTalon getRightShooterTalon(){
		return rightShooterTalon;
	}
	public CANTalon getLeftShooterTalon(){
		return leftShooterTalon;
	}
	
}
