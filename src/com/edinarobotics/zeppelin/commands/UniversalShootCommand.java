package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Anchors;
import com.edinarobotics.zeppelin.subsystems.Augers;
import com.edinarobotics.zeppelin.subsystems.Collector;
import com.edinarobotics.zeppelin.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

public class UniversalShootCommand extends Command{

	private Shooter shooter;
	private Augers augers;
	private Anchors anchors;
	private Collector collector;
	private boolean notShootingYet;
	
	private long startTime;
	
	public UniversalShootCommand(){
		super("universalshootcommand");
		this.shooter = Components.getInstance().shooter;
		this.augers = Components.getInstance().augers;
		this.anchors = Components.getInstance().anchors;
		this.collector = Components.getInstance().collector;
		requires(collector);
		requires(shooter);
		requires(augers);
		requires(anchors);
	}
	
	protected void initialize(){
		System.out.println("INITIALIZING UNIVERSAL SHOOT COMMAND");
		anchors.setUniversalShooting(true);
		anchors.anchorRobot();
		shooter.setShooterTalons(3300,3400);
		collector.setCollectorTalon(1.0);
		notShootingYet = true;
		startTime = System.nanoTime();
	}
	
	protected void execute(){
		if (System.nanoTime()>startTime + 1000000000 && notShootingYet){
			System.out.println("RUNNING AUGERS");
			augers.setAugerSpeed(0.6);
			notShootingYet = false;
		}
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return !anchors.isUniversalShooting();
	}

	protected void end(){
		shooter.setShooterTalons(0,0);
		augers.setAugerSpeed(0.0);
		anchors.unanchorRobot();
		collector.setCollectorTalon(0.0);
	}
	
}
