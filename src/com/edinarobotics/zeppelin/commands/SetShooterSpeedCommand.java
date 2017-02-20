package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

public class SetShooterSpeedCommand extends Command{

	private Shooter shooter;
	private double speed;
	
	public SetShooterSpeedCommand(double speed){
		super("setshooterspeedcommand");
		this.shooter = Components.getInstance().shooter;
		this.speed = speed;
		requires(shooter);
	}
	
	protected void initialize(){
		shooter.setShooterTalon(speed);
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}
