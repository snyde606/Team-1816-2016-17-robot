package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleFieldCentricCommand extends Command{

	private Drivetrain drivetrain;
	
	public ToggleFieldCentricCommand(){
		super("togglefieldcentriccommand");
		this.drivetrain = Components.getInstance().drivetrain;
		requires(drivetrain);
	}
	
	protected void initialize(){
		drivetrain.setFieldCentric(!drivetrain.isFieldCentric());
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}
