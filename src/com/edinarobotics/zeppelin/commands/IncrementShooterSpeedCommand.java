package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

public class IncrementShooterSpeedCommand extends Command{

	private Shooter shooter;
	private double inc;
	
	public IncrementShooterSpeedCommand(double inc){
		super("incrementshooterspeedcommand");
		this.shooter = Components.getInstance().shooter;
		this.inc = inc;
		requires(shooter);
	}
	
	protected void initialize(){
		shooter.addIncrementSpeed(inc);
		shooter.setShooterTalons(shooter.getIncrementedShooterSpeed()*1.03,shooter.getIncrementedShooterSpeed());
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}	
	
}
