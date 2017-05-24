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
import com.edinarobotics.zeppelin.commands.DrivingVisionTrackButtonCommand;
import com.edinarobotics.zeppelin.commands.EndAugerButtonCommand;
import com.edinarobotics.zeppelin.commands.EndDrivingVisionTrackButtonCommand;
import com.edinarobotics.zeppelin.commands.EndLeftAugerButtonCommand;
import com.edinarobotics.zeppelin.commands.EndRightAugerButtonCommand;
import com.edinarobotics.zeppelin.commands.IncrementShooterSpeedCommand;
import com.edinarobotics.zeppelin.commands.LowerCenterWheelCommand;
import com.edinarobotics.zeppelin.commands.OpenGearCollectorCommand;
import com.edinarobotics.zeppelin.commands.OpenThenPunchGearCommand;
import com.edinarobotics.zeppelin.commands.PunchCollectorCommand;
import com.edinarobotics.zeppelin.commands.RaiseCenterWheelCommand;
import com.edinarobotics.zeppelin.commands.RunLeftAugerCommand;
import com.edinarobotics.zeppelin.commands.RunRightAugerCommand;
import com.edinarobotics.zeppelin.commands.SetAugerSpeedCommand;
import com.edinarobotics.zeppelin.commands.SetCollectorSpeedCommand;
import com.edinarobotics.zeppelin.commands.SetShooterSpeedCommand;
import com.edinarobotics.zeppelin.commands.SetSlowModeCommand;
import com.edinarobotics.zeppelin.commands.UnanchorRobotCommand;
import com.edinarobotics.zeppelin.commands.UnpunchCollectorCommand;
import com.edinarobotics.zeppelin.commands.UnpunchThenCloseGearCommand;

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
		
		
		//Allison's Controls
		//Sorry Jacob while you were gone we decided I was a better driver and that I would be taking over for you for championships. I know this is hard to read but it's for the best.
			gamepad0.rightTrigger().whenPressed(new SetSlowModeCommand(true));
			gamepad0.rightTrigger().whenReleased(new SetSlowModeCommand(false));
		
			gamepad0.leftBumper().whenPressed(new LowerCenterWheelCommand());
			gamepad0.leftBumper().whenReleased(new RaiseCenterWheelCommand());
		
			gamepad0.rightBumper().whenPressed(new LowerCenterWheelCommand());
			gamepad0.rightBumper().whenReleased(new RaiseCenterWheelCommand());
			
			gamepad0.diamondUp().whenPressed(new AnchorRobotCommand());
			gamepad0.diamondUp().whenReleased(new UnanchorRobotCommand());
			
			gamepad0.leftTrigger().whenPressed(new PunchCollectorCommand());
			gamepad0.leftTrigger().whenReleased(new UnpunchCollectorCommand());
			
			gamepad0.diamondDown().whenPressed(new DrivingVisionTrackButtonCommand());
			gamepad0.diamondDown().whenPressed(new EndDrivingVisionTrackButtonCommand());
			
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
			
			gamepad1.rightBumper().whenPressed(new SetShooterSpeedCommand(3800,3400));
			gamepad1.rightBumper().whenReleased(new SetShooterSpeedCommand(0,0));

			gamepad1.leftBumper().whenPressed(new SetAugerSpeedCommand(0.4,false));
			gamepad1.leftBumper().whenReleased(new EndAugerButtonCommand());
		
			gamepad1.middleLeft().whenPressed(new RunLeftAugerCommand(0.4));
			gamepad1.middleLeft().whenReleased(new EndLeftAugerButtonCommand());

			gamepad1.middleRight().whenPressed(new RunRightAugerCommand(0.4));
			gamepad1.middleRight().whenReleased(new EndRightAugerButtonCommand());
			
			gamepad1.diamondDown().whenPressed(new IncrementShooterSpeedCommand(-50));
			gamepad1.diamondUp().whenPressed(new IncrementShooterSpeedCommand(50));
			
			gamepad1.diamondLeft().whenPressed(new OpenThenPunchGearCommand());
			gamepad1.diamondLeft().whenReleased(new UnpunchThenCloseGearCommand());
			
	}

	public static Controls getInstance() {
		if (instance == null) {
			instance = new Controls();
		}
		return instance;
	}
	
}
