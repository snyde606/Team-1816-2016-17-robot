package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Augers;

import edu.wpi.first.wpilibj.command.Command;

public class RunRightAugerCommand extends Command{

	private Augers augers;
	private double rightAugerSpeed;
	
	public RunRightAugerCommand(double speed){
		super("runrightaugercommand");
		this.augers = Components.getInstance().augers;
		this.rightAugerSpeed = speed;
		requires(augers);
	}
	
	protected void initialize(){
		augers.setRightAugerSpeed(rightAugerSpeed);
		augers.setIsRightRunningButton(true);
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return !augers.getIsRightRunningButton();
	}

}
