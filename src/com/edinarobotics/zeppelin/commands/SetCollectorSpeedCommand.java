package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Collector;

import edu.wpi.first.wpilibj.command.Command;

public class SetCollectorSpeedCommand extends Command{

	private Collector collector;
	private double collectorSpeed;
	
	public SetCollectorSpeedCommand(double cSpeed){
		super("setcollectorspeedcommand");
		this.collector = Components.getInstance().collector;
		collectorSpeed = cSpeed;
		requires(collector);
	}
	
	protected void initialize(){
		collector.setCollectorTalon(collectorSpeed);
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}
