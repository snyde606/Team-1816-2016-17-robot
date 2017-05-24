package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Augers;

import edu.wpi.first.wpilibj.command.Command;

public class EndAugerButtonCommand extends Command{

	private Augers augers;
	
	public EndAugerButtonCommand(){
		super("endaugerbuttoncommand");
		this.augers = Components.getInstance().augers;
		requires(augers);
	}
	
	protected void initialize(){
		System.out.println("INIT END AUGER BUTTON");
		augers.setIsRunningButton(false);
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}
