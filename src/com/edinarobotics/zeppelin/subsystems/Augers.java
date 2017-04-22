package com.edinarobotics.zeppelin.subsystems;

import com.ctre.CANTalon;
import com.edinarobotics.utils.subsystems.Subsystem1816;

import edu.wpi.first.wpilibj.command.Command;

public class Augers extends Subsystem1816{

	CANTalon leftAuger, rightAuger;
	private boolean isRunningButton = false;
	private boolean isLeftRunningButton = false;
	private boolean isRightRunningButton = false;
	private double leftAugerSpeed = 0;
	private double rightAugerSpeed = 0;
	
	public Augers(int leftAuger, int rightAuger){
		this.leftAuger = new CANTalon(leftAuger);
		this.rightAuger = new CANTalon(rightAuger);
		rightAugerSpeed = 0;
		leftAugerSpeed = 0;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		leftAuger.set(leftAugerSpeed);
		rightAuger.set(rightAugerSpeed);
		
	}
	
	public void setAugerSpeed(double speed){
		leftAugerSpeed = speed;
		rightAugerSpeed = speed;
		update();
	}
	
	public void setLeftAugerSpeed(double speed){
		leftAugerSpeed = speed;
		update();
	}
	public void setRightAugerSpeed(double speed){
		rightAugerSpeed = speed;
		update();
	}
	
	public void setIsRunningButton(boolean runn){
		isRunningButton = runn;
	}
	public boolean getIsRunningButton(){
		return isRunningButton;
	}
	public void setIsLeftRunningButton(boolean runn){
		isLeftRunningButton = runn;
	}
	public boolean getIsLeftRunningButton(){
		return isLeftRunningButton;
	}
	public void setIsRightRunningButton(boolean runn){
		isRightRunningButton = runn;
	}
	public boolean getIsRightRunningButton(){
		return isRightRunningButton;
	}
	
	@Override
	public void setDefaultCommand(Command command) {
		if (getDefaultCommand() != null) {
			super.getDefaultCommand().cancel();
		}
		super.setDefaultCommand(command);
	}

}
