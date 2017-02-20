package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

public class ResetFieldCentricCommand extends Command{

	private Drivetrain drivetrain;
	
	public ResetFieldCentricCommand(){
		super("resetfieldcentriccommand");
		this.drivetrain = Components.getInstance().drivetrain;
		requires(drivetrain);
	}
	
	protected void initialize(){
		drivetrain.setGyroZero();
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}
