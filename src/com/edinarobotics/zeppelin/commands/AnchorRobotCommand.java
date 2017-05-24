package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Anchors;

import edu.wpi.first.wpilibj.command.Command;

public class AnchorRobotCommand extends Command{

	private Anchors anchors;
	
	public AnchorRobotCommand(){
		super("anchorrobotcommand");
		this.anchors = Components.getInstance().anchors;
		requires(anchors);
	}
	
	protected void initialize(){
		System.out.println("INIT ANCHOR ROBOT");
		anchors.anchorRobot();
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}
