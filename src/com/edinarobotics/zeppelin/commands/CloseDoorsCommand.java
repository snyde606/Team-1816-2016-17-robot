package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Collector;

import edu.wpi.first.wpilibj.command.Command;

public class CloseDoorsCommand extends Command{

	public Collector collector;
	
	public CloseDoorsCommand(){
		super("closedoorscommand");
		this.collector = Components.getInstance().collector;
		requires(collector);
	}
	
	protected void initialize(){
		collector.closeGearDoors();
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}
