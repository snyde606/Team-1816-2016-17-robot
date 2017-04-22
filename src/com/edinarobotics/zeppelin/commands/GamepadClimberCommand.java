package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.utils.gamepad.Gamepad;
import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;

public class GamepadClimberCommand extends Command{

	private Climber climber;
	private Gamepad gamepad;
	
	public GamepadClimberCommand(Gamepad gamepad){
		super("gamepadclimbercommand");
		this.climber = Components.getInstance().climber;
		this.gamepad = gamepad;
		requires(climber);
	}
	
	protected void execute(){
		double speed = gamepad.getRightJoystick().getY();
		climber.setClimberMotor(Math.abs(speed));
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
