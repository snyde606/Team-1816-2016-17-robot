package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Augers;

import edu.wpi.first.wpilibj.command.Command;

public class EndRightAugerButtonCommand extends Command{

	private Augers augers;
	
	public EndRightAugerButtonCommand(){
		super("endrightaugerbuttoncommand");
		this.augers = Components.getInstance().augers;
		requires(augers);
	}
	
	protected void initialize(){
		augers.setIsRightRunningButton(false);
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}
