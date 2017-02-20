package com.edinarobotics.zeppelin.subsystems;

import com.ctre.CANTalon;
import com.edinarobotics.utils.subsystems.Subsystem1816;

import edu.wpi.first.wpilibj.command.Command;

public class Augers extends Subsystem1816{

	CANTalon leftAuger, rightAuger;
	private boolean isRunningButton = false;
	private double augerSpeeds = 0;
	
	public Augers(int leftAuger, int rightAuger){
		this.leftAuger = new CANTalon(leftAuger);
		this.rightAuger = new CANTalon(rightAuger);
		augerSpeeds = 0;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		leftAuger.set(augerSpeeds);
		rightAuger.set(augerSpeeds);
		
	}
	
	public void setAugerSpeed(double speed){
		augerSpeeds = speed;
		update();
	}
	
	public void setIsRunningButton(boolean runn){
		isRunningButton = runn;
	}
	public boolean getIsRunningButton(){
		return isRunningButton;
	}
	
	@Override
	public void setDefaultCommand(Command command) {
		if (getDefaultCommand() != null) {
			super.getDefaultCommand().cancel();
		}
		super.setDefaultCommand(command);
	}

}
