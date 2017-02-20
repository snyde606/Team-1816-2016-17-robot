package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Anchors;

import edu.wpi.first.wpilibj.command.Command;

public class UnanchorRobotCommand extends Command{

	private Anchors anchors;
	
	public UnanchorRobotCommand(){
		super("unanchorrobotcommand");
		this.anchors = Components.getInstance().anchors;
		requires(anchors);
	}
	
	protected void initialize(){
		anchors.unanchorRobot();
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}
