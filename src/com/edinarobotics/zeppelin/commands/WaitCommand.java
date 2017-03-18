package com.edinarobotics.zeppelin.commands;

import edu.wpi.first.wpilibj.command.Command;

public class WaitCommand extends Command{

	private double seconds;
	
	public WaitCommand(double seconds) {
		super("waitcommand");
		this.seconds = seconds;
		// TODO Auto-generated constructor stub
	}
	
	protected void initialize(){
		try {
			Thread.sleep((long)(seconds*1000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}
