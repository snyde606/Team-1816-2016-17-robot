package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

public class LowerCenterWheelCommand extends Command{

	
	//////////////////////////////////////////////
	//								  			//
	//								  			//
	//		   MAXIMUM STRAFE DISENGAGED		//
	//								  			//
	//								  			//
	//////////////////////////////////////////////
	
	
	Drivetrain drivetrain;
	
	public LowerCenterWheelCommand(){
		super("lowercenterwheelcommand");
		this.drivetrain = Components.getInstance().drivetrain;
		requires(drivetrain);
	}
	
	protected void initialize(){
		drivetrain.lowerCenterWheel();
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}
