package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

public class SetShooterSpeedCommand extends Command{

	private Shooter shooter;
	private double lspeed, rspeed;
	
	public SetShooterSpeedCommand(double lspeed, double rspeed){
		super("setshooterspeedcommand");
		this.shooter = Components.getInstance().shooter;
		this.lspeed = lspeed;
		this.rspeed = rspeed;
		requires(shooter);
	}
	
	protected void initialize(){
		shooter.setShooterTalons(lspeed,rspeed);
	}
	
	@Override
	
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}
