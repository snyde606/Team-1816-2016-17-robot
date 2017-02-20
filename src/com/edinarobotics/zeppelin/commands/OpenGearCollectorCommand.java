package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Collector;

import edu.wpi.first.wpilibj.command.Command;

public class OpenGearCollectorCommand extends Command{

	private Collector collector;
	
	public OpenGearCollectorCommand(){
		super("opengearcollectorcommand");
		this.collector = Components.getInstance().collector;
		requires(collector);
	}
	
	protected void initialize(){
		collector.openGearCollector();
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}
