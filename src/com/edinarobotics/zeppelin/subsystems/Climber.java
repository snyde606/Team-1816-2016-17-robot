package com.edinarobotics.zeppelin.subsystems;

import com.ctre.CANTalon;
import com.edinarobotics.utils.subsystems.Subsystem1816;

import edu.wpi.first.wpilibj.command.Command;

public class Climber extends Subsystem1816{//#maximumkaraoke

	private CANTalon climberTalon;
	private double climberSpeed;
	
	public Climber(int climberTalon){
		this.climberTalon = new CANTalon(climberTalon);
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		climberTalon.set(climberSpeed);
	}
	
	public void setClimberMotor(double speed){
		climberSpeed = speed;
		update();
	}
	
	@Override
	public void setDefaultCommand(Command command) {
		if (getDefaultCommand() != null) {
			super.getDefaultCommand().cancel();
		}
		super.setDefaultCommand(command);
	}

	public CANTalon getClimberTalon(){//pok pok karaoke pok
		return climberTalon;
	}
	
}
