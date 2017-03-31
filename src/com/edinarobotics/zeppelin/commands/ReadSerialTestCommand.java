package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Command;

public class ReadSerialTestCommand extends Command {

	Drivetrain drivetrain;

	String temp = "";
	String oldstring = "";
	Integer x = 0;
	Integer y = 0;
	Integer fps = 0;

	public ReadSerialTestCommand() {
		super("readserialtestcommand");
		this.drivetrain = Components.getInstance().drivetrain;
		requires(drivetrain);
	}
	

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void execute() {
			drivetrain.readSerialXY();
		}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
