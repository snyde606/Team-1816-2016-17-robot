package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Collector;

import edu.wpi.first.wpilibj.command.Command;

public class CloseGearCollectorCommand extends Command{

	private Collector collector;
	
	public CloseGearCollectorCommand(){
		super("closegearcollectorcommand");
		this.collector = Components.getInstance().collector;
		requires(collector);
	}
	
	protected void initialize(){
		collector.closeGearCollector();
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}
