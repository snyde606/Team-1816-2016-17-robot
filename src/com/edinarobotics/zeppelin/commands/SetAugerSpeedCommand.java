package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Augers;

import edu.wpi.first.wpilibj.command.Command;

public class SetAugerSpeedCommand extends Command{

	private Augers augers;
	private double augerSpeed;
	private boolean button;
	
	public SetAugerSpeedCommand(double speed, boolean button){
		super("setaugerspeedcommand");
		this.augers = Components.getInstance().augers;
		this.augerSpeed = speed;
		this.button = button;
		requires(augers);
	}
	
	protected void initialize(){
		augers.setAugerSpeed(augerSpeed);
		augers.setIsRunningButton(true);
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return !augers.getIsRunningButton() || button;
	}

}
