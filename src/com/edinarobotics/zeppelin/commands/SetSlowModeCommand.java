package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

public class SetSlowModeCommand extends Command{

	private Drivetrain drivetrain;
	private boolean slowmode;
	
	public SetSlowModeCommand(boolean slowmode) {
		super("setslowmodecommand");
		drivetrain = Components.getInstance().drivetrain;
		this.slowmode = slowmode;
		requires(drivetrain);
	}
	
	protected void initialize(){
		drivetrain.setSlowMode(slowmode);
	}
	
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	protected void end() {

	}
	

}
