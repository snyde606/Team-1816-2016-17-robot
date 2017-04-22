package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Augers;

import edu.wpi.first.wpilibj.command.Command;

public class RunLeftAugerCommand extends Command{

	private Augers augers;
	private double leftAugerSpeed;
	
	public RunLeftAugerCommand(double speed){
		super("runleftaugercommand");
		this.augers = Components.getInstance().augers;
		this.leftAugerSpeed = speed;
		requires(augers);
	}
	
	protected void initialize(){
		augers.setLeftAugerSpeed(leftAugerSpeed);
		augers.setIsLeftRunningButton(true);
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return !augers.getIsLeftRunningButton();
	}

}
