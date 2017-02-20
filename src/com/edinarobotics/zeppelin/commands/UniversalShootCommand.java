package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Anchors;
import com.edinarobotics.zeppelin.subsystems.Augers;
import com.edinarobotics.zeppelin.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

public class UniversalShootCommand extends Command{

	private Shooter shooter;
	private Augers augers;
	private Anchors anchors;
	private boolean notShootingYet;
	
	private long startTime;
	
	public UniversalShootCommand(){
		super("universalshootcommand");
		this.shooter = Components.getInstance().shooter;
		this.augers = Components.getInstance().augers;
		this.anchors = Components.getInstance().anchors;
		requires(shooter);
		requires(augers);
		requires(anchors);
	}
	
	protected void initialize(){
		anchors.setUniversalShooting(true);
		anchors.anchorRobot();
		shooter.setShooterTalon(3350);
		notShootingYet = true;
		startTime = System.nanoTime();
	}
	
	protected void execute(){
		if (System.nanoTime()>startTime + 1000000000 && notShootingYet){
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
		shooter.setShooterTalon(0.0);
		augers.setAugerSpeed(0.0);
		anchors.unanchorRobot();
	}
	
}
