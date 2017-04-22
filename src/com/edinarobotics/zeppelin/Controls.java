package com.edinarobotics.zeppelin;

import java.util.ArrayList;
import java.util.List;

import com.edinarobotics.utils.gamepad.FilteredGamepad;
import com.edinarobotics.utils.gamepad.Gamepad;
import com.edinarobotics.utils.gamepad.gamepadfilters.DeadzoneFilter;
import com.edinarobotics.utils.gamepad.gamepadfilters.GamepadFilter;
import com.edinarobotics.utils.gamepad.gamepadfilters.GamepadFilterSet;
import com.edinarobotics.utils.gamepad.gamepadfilters.PowerFilter;
import com.edinarobotics.zeppelin.commands.AnchorRobotCommand;
import com.edinarobotics.zeppelin.commands.CloseGearCollectorCommand;
import com.edinarobotics.zeppelin.commands.EndAugerButtonCommand;
import com.edinarobotics.zeppelin.commands.EndLeftAugerButtonCommand;
import com.edinarobotics.zeppelin.commands.EndRightAugerButtonCommand;
import com.edinarobotics.zeppelin.commands.IncrementShooterSpeedCommand;
import com.edinarobotics.zeppelin.commands.LowerCenterWheelCommand;
import com.edinarobotics.zeppelin.commands.OpenGearCollectorCommand;
import com.edinarobotics.zeppelin.commands.PunchCollectorCommand;
import com.edinarobotics.zeppelin.commands.RaiseCenterWheelCommand;
import com.edinarobotics.zeppelin.commands.ReadSerialTestCommand;
import com.edinarobotics.zeppelin.commands.RunLeftAugerCommand;
import com.edinarobotics.zeppelin.commands.RunRightAugerCommand;
import com.edinarobotics.zeppelin.commands.SetAugerSpeedCommand;
import com.edinarobotics.zeppelin.commands.SetCollectorSpeedCommand;
import com.edinarobotics.zeppelin.commands.SetShooterSpeedCommand;
import com.edinarobotics.zeppelin.commands.SetSlowModeCommand;
import com.edinarobotics.zeppelin.commands.UnanchorRobotCommand;
import com.edinarobotics.zeppelin.commands.UnpunchCollectorCommand;

public class Controls {

	private static Controls instance;

	public Gamepad gamepad0;
	public Gamepad gamepad1;
	
	public Controls() {
		// TODO Auto-generated constructor stub
		List<GamepadFilter> gamepadFilter0 = new ArrayList<GamepadFilter>();
		gamepadFilter0.add(new DeadzoneFilter(0.05));
		gamepadFilter0.add(new PowerFilter(2));
		GamepadFilterSet driveGamepadFilterSet0 = new GamepadFilterSet(gamepadFilter0);
		
		gamepad0 = new FilteredGamepad(0, driveGamepadFilterSet0);
		gamepad1 = new FilteredGamepad(1, driveGamepadFilterSet0);
		
		
		//Jacob's Controls
			gamepad0.rightTrigger().whenPressed(new SetSlowModeCommand(true));
			gamepad0.rightTrigger().whenReleased(new SetSlowModeCommand(false));
		
			gamepad0.leftTrigger().whenPressed(new SetCollectorSpeedCommand(1.0));
			gamepad0.leftTrigger().whenReleased(new SetCollectorSpeedCommand(0.0));
		
			gamepad0.leftBumper().whenPressed(new RaiseCenterWheelCommand());
			gamepad0.leftBumper().whenReleased(new LowerCenterWheelCommand());
		
			gamepad0.rightBumper().whenPressed(new RaiseCenterWheelCommand());
			gamepad0.rightBumper().whenReleased(new LowerCenterWheelCommand());
			
			gamepad0.diamondDown().whenPressed(new AnchorRobotCommand());
			gamepad0.diamondDown().whenReleased(new UnanchorRobotCommand());
	
		//Aiden's Controls
			gamepad1.dPadUp().whenPressed(new SetCollectorSpeedCommand(-1.0));
			gamepad1.dPadUp().whenReleased(new SetCollectorSpeedCommand(0.0));
			
			gamepad1.dPadDown().whenPressed(new SetCollectorSpeedCommand(1.0));
			gamepad1.dPadDown().whenReleased(new SetCollectorSpeedCommand(0.0));
			
			gamepad1.dPadRight().whenPressed(new OpenGearCollectorCommand());
			gamepad1.dPadLeft().whenPressed(new CloseGearCollectorCommand());
			
			gamepad1.diamondRight().whenPressed(new AnchorRobotCommand());
			gamepad1.diamondRight().whenReleased(new UnanchorRobotCommand());
			
			gamepad1.rightTrigger().whenPressed(new AnchorRobotCommand());
			gamepad1.rightTrigger().whenReleased(new UnanchorRobotCommand());
			
			gamepad1.rightBumper().whenPressed(new SetShooterSpeedCommand(3300,3300));
			gamepad1.rightBumper().whenReleased(new SetShooterSpeedCommand(0,0));

			gamepad1.leftBumper().whenPressed(new SetAugerSpeedCommand(0.6,false));
			gamepad1.leftBumper().whenReleased(new EndAugerButtonCommand());
			
			gamepad1.middleLeft().whenPressed(new RunLeftAugerCommand(0.6));
			gamepad1.middleLeft().whenReleased(new EndLeftAugerButtonCommand());

			gamepad1.middleRight().whenPressed(new RunRightAugerCommand(0.6));
			gamepad1.middleRight().whenReleased(new EndRightAugerButtonCommand());
			
			gamepad1.diamondDown().whenPressed(new IncrementShooterSpeedCommand(-50));
			gamepad1.diamondUp().whenPressed(new IncrementShooterSpeedCommand(50));
			
			gamepad1.diamondLeft().whenPressed(new PunchCollectorCommand());
			gamepad1.diamondLeft().whenReleased(new UnpunchCollectorCommand());
		
	}

	public static Controls getInstance() {
		if (instance == null) {
			instance = new Controls();
		}
		return instance;
	}
	
}
