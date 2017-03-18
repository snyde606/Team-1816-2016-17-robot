package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

public class SetGyroZeroCommand extends Command{

	private Drivetrain drivetrain;
	
	public SetGyroZeroCommand(){
		super("setgyrozerocommand");
		this.drivetrain = Components.getInstance().drivetrain;
		requires(drivetrain);
	}
	
	protected void initialize(){
		System.out.println("SETTING AUTO GYRO ZERO");
		drivetrain.setAutoGyroZero(drivetrain.getGyroAngle());
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}
