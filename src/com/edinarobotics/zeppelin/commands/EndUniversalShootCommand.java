package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Anchors;

import edu.wpi.first.wpilibj.command.Command;

public class EndUniversalShootCommand extends Command{

	private Anchors anchors;
	
	public EndUniversalShootCommand(){
		super("enduniversalshootcommand");
		this.anchors = Components.getInstance().anchors;
		requires(anchors);
	}

	protected void initialize(){
		anchors.setUniversalShooting(false);
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
