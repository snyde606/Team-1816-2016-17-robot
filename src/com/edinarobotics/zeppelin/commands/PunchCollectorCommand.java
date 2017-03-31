package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Collector;

import edu.wpi.first.wpilibj.command.Command;

public class PunchCollectorCommand extends Command{

	private Collector collector;
	
	public PunchCollectorCommand() {
		super("punchcollectorcommand");
		this.collector = Components.getInstance().collector;
		requires(collector);
	}
	
	protected void initialize(){
		collector.punchGearCollector();
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}
