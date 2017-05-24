package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.utils.gamepad.Gamepad;
import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Augers;

import edu.wpi.first.wpilibj.command.Command;

public class GamepadAugersCommand extends Command{

	private Augers augers;
	private Gamepad gamepad1;
	
	public GamepadAugersCommand(Gamepad gamepad){
		super("gamepadaugerscommand");
		this.augers = Components.getInstance().augers;
		this.gamepad1 = gamepad;
		requires(augers);
	}
	
	protected void execute(){
		double speed = gamepad1.getLeftJoystick().getY();
		augers.setAugerSpeed(0.6*speed);
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
