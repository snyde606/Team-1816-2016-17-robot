package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Augers;

import edu.wpi.first.wpilibj.command.Command;

public class EndLeftAugerButtonCommand extends Command{

	private Augers augers;
	
	public EndLeftAugerButtonCommand(){
		super("endleftaugerbuttoncommand");
		this.augers = Components.getInstance().augers;
		requires(augers);
	}
	
	protected void initialize(){
		augers.setIsLeftRunningButton(false);
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}
