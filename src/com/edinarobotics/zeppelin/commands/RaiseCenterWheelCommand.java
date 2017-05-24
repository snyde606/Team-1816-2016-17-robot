package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

public class RaiseCenterWheelCommand extends Command{

	
	//////////////////////////////////////////////
	//								  			//
	//								  			//
	//			MAXIMUM STRAFE ENGAGED			//
	//								  			//
	//								  			//
	//////////////////////////////////////////////
	
	
	Drivetrain drivetrain;
	
	public RaiseCenterWheelCommand(){
		super("raisecenterwheelcommand");
		this.drivetrain = Components.getInstance().drivetrain;
		requires(drivetrain);
	}
	
	protected void initialize(){
		drivetrain.raiseCenterWheel();
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}
