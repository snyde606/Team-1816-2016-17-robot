package com.edinarobotics.zeppelin.commands;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousCommand extends CommandGroup {
	
	
	
	public AutonomousCommand(AutonomousMode mode) {
		
		switch(mode) {
			
			case CENTER_GEAR_AUTO_RIGHT_BOILER :
				//experimental end of 10k gear and shoot
				//5/21/17
				//ignore this line its a comment
				//part 2
				
				//Drive straight test auto
				
				/*
				addSequential(new DriveXInchesCommand(60,.9));
				addSequential(new RaiseCenterWheelCommand());
				*/
				
				addSequential(new SetGyroZeroCommand());
				addSequential(new SetShooterSpeedCommand(0,0));
				addSequential(new SetAugerSpeedCommand(0,true));
				addSequential(new SetCollectorSpeedCommand(0));
				addSequential(new DriveXInchesCommand(30,0.9));
				addSequential(new RotateToAngleCWFromStartCommand(0));
				addSequential(new DriveXInchesVisionCommand(47.5));

				addSequential(new WaitCommand(0.4));
				addSequential(new DriveXInchesCommand(-1.5,0.75));
				addSequential(new WaitCommand(0.15));

				addSequential(new OpenThenPunchGearCommand());
				addSequential(new WaitCommand(0.1));
				addSequential(new UnpunchCollectorCommand());
				addSequential(new WaitCommand(0.1));
				addSequential(new DriveXInchesCommand(-1.5,0.75));
				addSequential(new WaitCommand(0.75));
				
				addSequential(new OpenThenPunchGearCommand());
				addSequential(new WaitCommand(0.1));
				addSequential(new UnpunchCollectorCommand());
				addSequential(new WaitCommand(0.4));
				
				addSequential(new OpenThenPunchGearCommand());
				addSequential(new WaitCommand(0.1));
				
				addSequential(new DriveXInchesCommand(-17,0.75));
				addSequential(new WaitCommand(0.1));
				addSequential(new UnpunchCollectorCommand());
				
				addSequential(new UnpunchThenCloseGearCommand());
				addSequential(new DriveXInchesCommand(-20,0.85));
				addSequential(new UnpunchCollectorCommand());
				addSequential(new WaitCommand(0.15));
				addSequential(new RotateToAngleCWFromStartCommand(75));
				addSequential(new WaitCommand(0.15));
				addSequential(new SetCollectorSpeedCommand(1.0));
				addSequential(new DriveXInchesCommand(93,0.9));
				addSequential(new WaitCommand(0.15));
				addSequential(new RotateToAngleCWFromStartCommand(125));
				addSequential(new SetShooterSpeedCommand(3800,3800));
				addSequential(new WaitCommand(0.15));
				addSequential(new DriveXInchesCommand(10,0.85));
				addSequential(new SetAugerSpeedCommand(0.4,true));
//				addSequential(new DriveXInchesCommand(10,0.85));
				addSequential(new WaitCommand(3));
				addSequential(new DriveXInchesCommand(-40,0.95));
				addSequential(new RotateToAngleCWFromStartCommand(20));
				addSequential(new WaitCommand(.2));
				addSequential(new DriveXInchesCommand(200,0.95));

				break;
				
			case CENTER_GEAR_AUTO_LEFT_BOILER :
				//experimental end of 10k gear and shoot

				addSequential(new SetGyroZeroCommand());
				addSequential(new SetShooterSpeedCommand(0,0));
				addSequential(new SetAugerSpeedCommand(0,true));
				addSequential(new SetCollectorSpeedCommand(0));
				addSequential(new DriveXInchesCommand(30,0.9));
				addSequential(new RotateToAngleCWFromStartCommand(0));
				addSequential(new DriveXInchesVisionCommand(47.5));

				addSequential(new WaitCommand(0.4));
				addSequential(new DriveXInchesCommand(-1.5,0.75));
				addSequential(new WaitCommand(0.15));

				addSequential(new OpenThenPunchGearCommand());
				addSequential(new WaitCommand(0.1));
				addSequential(new UnpunchCollectorCommand());
				addSequential(new WaitCommand(0.1));
				addSequential(new DriveXInchesCommand(-1.5,0.75));
				addSequential(new WaitCommand(0.75));
				
				addSequential(new OpenThenPunchGearCommand());
				addSequential(new WaitCommand(0.1));
				addSequential(new UnpunchCollectorCommand());
				addSequential(new WaitCommand(0.4));
				
				addSequential(new OpenThenPunchGearCommand());
				addSequential(new WaitCommand(0.1));
				
				addSequential(new DriveXInchesCommand(-17,0.75));
				addSequential(new WaitCommand(0.1));
				addSequential(new UnpunchCollectorCommand());
				
				addSequential(new DriveXInchesCommand(-20,0.85));
				addSequential(new UnpunchCollectorCommand());
				addSequential(new WaitCommand(0.15));
				//
				addSequential(new UnpunchThenCloseGearCommand());
				addSequential(new RotateToAngleCWFromStartCommand(-75));
				addSequential(new WaitCommand(0.15));
				addSequential(new SetCollectorSpeedCommand(1.0));
				addSequential(new DriveXInchesCommand(93,0.9));
				addSequential(new WaitCommand(0.15));
				addSequential(new RotateToAngleCWFromStartCommand(-125));
				addSequential(new SetShooterSpeedCommand(3800,3800));
				addSequential(new WaitCommand(0.15));
				addSequential(new DriveXInchesCommand(10,0.85));
				addSequential(new SetAugerSpeedCommand(0.4,true));
//				addSequential(new DriveXInchesCommand(10,0.85));
				addSequential(new WaitCommand(3));
				addSequential(new DriveXInchesCommand(-40,0.95));
				addSequential(new RotateToAngleCWFromStartCommand(-20));
				addSequential(new WaitCommand(.3));
				addSequential(new DriveXInchesCommand(200,0.95));
				break;
				
			case LEFT_GEAR_AUTO:
				
				addSequential(new SetGyroZeroCommand());
				addSequential(new DriveXInchesCommand(75,0.75));
				addSequential(new RotateToAngleCWFromStartCommand(45));
//				addSequential(new DriveXInchesVisionCommand(53));
				addSequential(new DriveXInchesVisionCommand(61));
				
				addSequential(new WaitCommand(0.4));
				addSequential(new DriveXInchesCommand(-1.5,0.75));
				addSequential(new WaitCommand(0.15));

				addSequential(new OpenThenPunchGearCommand());
				addSequential(new WaitCommand(0.1));
				addSequential(new UnpunchCollectorCommand());
				addSequential(new WaitCommand(0.1));
				addSequential(new DriveXInchesCommand(-1.5,0.75));
				addSequential(new WaitCommand(0.75));
				
				addSequential(new OpenThenPunchGearCommand());
				addSequential(new WaitCommand(0.1));
				addSequential(new UnpunchCollectorCommand());
				addSequential(new WaitCommand(0.4));
				
				addSequential(new OpenThenPunchGearCommand());
				addSequential(new WaitCommand(0.1));
				
				addSequential(new UnpunchThenCloseGearCommand());
				addSequential(new DriveXInchesCommand(-20,0.75));
				addSequential(new WaitCommand(0.1));
				addSequential(new UnpunchCollectorCommand());
				
				
				break;
				
			case RIGHT_GEAR_AUTO:
				
				addSequential(new SetGyroZeroCommand());
				addSequential(new DriveXInchesCommand(75,0.75));
				addSequential(new RotateToAngleCWFromStartCommand(-45));
//				addSequential(new DriveXInchesVisionCommand(53));
				addSequential(new DriveXInchesVisionCommand(61));

				addSequential(new WaitCommand(0.4));
				addSequential(new DriveXInchesCommand(-1.5,0.75));
				addSequential(new WaitCommand(0.15));

				addSequential(new OpenThenPunchGearCommand());
				addSequential(new WaitCommand(0.1));
				addSequential(new UnpunchCollectorCommand());
				addSequential(new WaitCommand(0.1));
				addSequential(new DriveXInchesCommand(-1.5,0.75));
				addSequential(new WaitCommand(0.75));
				
				addSequential(new OpenThenPunchGearCommand());
				addSequential(new WaitCommand(0.1));
				addSequential(new UnpunchCollectorCommand());
				addSequential(new WaitCommand(0.4));
				
				addSequential(new OpenThenPunchGearCommand());
				addSequential(new WaitCommand(0.1));
				
				addSequential(new UnpunchThenCloseGearCommand());
				addSequential(new DriveXInchesCommand(-20,0.75));
				addSequential(new WaitCommand(0.1));
				addSequential(new UnpunchCollectorCommand());
				
				
				break;
				
			case RIGHT_GEAR_RIGHT_BOILER:
				
				addSequential(new SetGyroZeroCommand());
				addSequential(new DriveXInchesCommand(75,0.75));
				addSequential(new RotateToAngleCWFromStartCommand(-45));
//				addSequential(new DriveXInchesVisionCommand(53));
				addSequential(new DriveXInchesVisionCommand(61));
				
				addSequential(new WaitCommand(0.4));
				addSequential(new DriveXInchesCommand(-1.5,0.75));
				addSequential(new WaitCommand(0.15));

				addSequential(new OpenThenPunchGearCommand());
				addSequential(new WaitCommand(0.1));
				addSequential(new UnpunchCollectorCommand());
				addSequential(new WaitCommand(0.1));
				addSequential(new DriveXInchesCommand(-1.5,0.75));
				addSequential(new WaitCommand(0.75));
				
				addSequential(new OpenThenPunchGearCommand());
				addSequential(new WaitCommand(0.1));
				addSequential(new UnpunchCollectorCommand());
				addSequential(new WaitCommand(0.4));
				
				addSequential(new OpenThenPunchGearCommand());
				addSequential(new WaitCommand(0.1));
				
				addSequential(new DriveXInchesCommand(-20,0.75));
				addSequential(new WaitCommand(0.1));
				addSequential(new UnpunchCollectorCommand());
				
				addSequential(new UnpunchThenCloseGearCommand());
				addSequential(new RotateToAngleCWFromStartCommand(125));
				addSequential(new WaitCommand(0.1));
				addSequential(new DriveXInchesCommand(90,0.75));
				addSequential(new SetShooterSpeedCommand(3800,3800));
				addSequential(new WaitCommand(0.15));
				addSequential(new SetAugerSpeedCommand(0.4,true));
				addSequential(new DriveXInchesCommand(90,0.75));
				addSequential(new WaitCommand(0.15));
				
				
				break;
				
			case LEFT_GEAR_LEFT_BOILER:
				
				addSequential(new SetGyroZeroCommand());
				addSequential(new DriveXInchesCommand(75,0.75));
				addSequential(new RotateToAngleCWFromStartCommand(45));
//				addSequential(new DriveXInchesVisionCommand(53));
				addSequential(new DriveXInchesVisionCommand(61));
				
				addSequential(new WaitCommand(0.4));
				addSequential(new DriveXInchesCommand(-1.5,0.75));
				addSequential(new WaitCommand(0.15));

				addSequential(new OpenThenPunchGearCommand());
				addSequential(new WaitCommand(0.1));
				addSequential(new UnpunchCollectorCommand());
				addSequential(new WaitCommand(0.1));
				addSequential(new DriveXInchesCommand(-1.5,0.75));
				addSequential(new WaitCommand(0.75));
				
				addSequential(new OpenThenPunchGearCommand());
				addSequential(new WaitCommand(0.1));
				addSequential(new UnpunchCollectorCommand());
				addSequential(new WaitCommand(0.4));
				
				addSequential(new OpenThenPunchGearCommand());
				addSequential(new WaitCommand(0.1));
				
				addSequential(new DriveXInchesCommand(-20,0.75));
				addSequential(new WaitCommand(0.1));
				addSequential(new UnpunchCollectorCommand());
				
				addSequential(new UnpunchThenCloseGearCommand());
				addSequential(new RotateToAngleCWFromStartCommand(-125));
				addSequential(new WaitCommand(0.1));
				addSequential(new DriveXInchesCommand(90,0.75));
				addSequential(new SetShooterSpeedCommand(3800,3800));
				addSequential(new WaitCommand(0.15));
				addSequential(new SetAugerSpeedCommand(0.4,true));
				addSequential(new DriveXInchesCommand(90,0.75));
				addSequential(new WaitCommand(0.15));
				
				
			break;	
				
			case CENTER_GEAR_MOBILITY_RIGHT:
				
				
				addSequential(new SetGyroZeroCommand());
				addSequential(new RaiseCenterWheelCommand());
				addSequential(new SetShooterSpeedCommand(0,0));
				addSequential(new SetAugerSpeedCommand(0,true));
				addSequential(new SetCollectorSpeedCommand(0));
				addSequential(new DriveXInchesCommand(30,0.9));
				addSequential(new RotateToAngleCWFromStartCommand(0));
				addSequential(new DriveXInchesVisionCommand(47.5));

				addSequential(new WaitCommand(0.4));
				addSequential(new DriveXInchesCommand(-1.5,0.75));
				addSequential(new WaitCommand(0.15));

				addSequential(new OpenThenPunchGearCommand());
				addSequential(new WaitCommand(0.1));
				addSequential(new UnpunchCollectorCommand());
				addSequential(new WaitCommand(0.1));
				addSequential(new DriveXInchesCommand(-1.5,0.75));
				addSequential(new WaitCommand(0.75));
				
				addSequential(new OpenThenPunchGearCommand());
				addSequential(new WaitCommand(0.1));
				addSequential(new UnpunchCollectorCommand());
				addSequential(new WaitCommand(0.4));
				
				addSequential(new OpenThenPunchGearCommand());
				addSequential(new WaitCommand(0.1));
				
				addSequential(new DriveXInchesCommand(-20,0.75));
				addSequential(new WaitCommand(0.1));
				addSequential(new UnpunchCollectorCommand());
				
				addSequential(new UnpunchThenCloseGearCommand());
				addSequential(new RotateToAngleCWFromStartCommand(55));
				addSequential(new DriveXInchesCommand(120, .9));
				addSequential(new RotateToAngleCWFromStartCommand(0));
				addSequential(new DriveXInchesCommand(120, .9));
				
				break;
				
			case CENTER_GEAR_MOBILITY_LEFT:
				
				
				addSequential(new SetGyroZeroCommand());
				addSequential(new RaiseCenterWheelCommand());
				addSequential(new SetShooterSpeedCommand(0,0));
				addSequential(new SetAugerSpeedCommand(0,true));
				addSequential(new SetCollectorSpeedCommand(0));
				addSequential(new DriveXInchesCommand(30,0.9));
				addSequential(new RotateToAngleCWFromStartCommand(0));
				addSequential(new DriveXInchesVisionCommand(47.5));

				addSequential(new WaitCommand(0.4));
				addSequential(new DriveXInchesCommand(-1.5,0.75));
				addSequential(new WaitCommand(0.15));

				addSequential(new OpenThenPunchGearCommand());
				addSequential(new WaitCommand(0.1));
				addSequential(new UnpunchCollectorCommand());
				addSequential(new WaitCommand(0.1));
				addSequential(new DriveXInchesCommand(-1.5,0.75));
				addSequential(new WaitCommand(0.75));
				
				addSequential(new OpenThenPunchGearCommand());
				addSequential(new WaitCommand(0.1));
				addSequential(new UnpunchCollectorCommand());
				addSequential(new WaitCommand(0.4));
				
				addSequential(new OpenThenPunchGearCommand());
				addSequential(new WaitCommand(0.1));
				
				addSequential(new DriveXInchesCommand(-20,0.75));
				addSequential(new WaitCommand(0.1));
				addSequential(new UnpunchCollectorCommand());
				
				addSequential(new UnpunchThenCloseGearCommand());
				addSequential(new RotateToAngleCWFromStartCommand(-55));
				addSequential(new DriveXInchesCommand(120, .9));
				addSequential(new RotateToAngleCWFromStartCommand(0));
				addSequential(new DriveXInchesCommand(120, .9));
				
				break;
				
			case LEFT_GEAR_MOBILITY:
				
				addSequential(new SetGyroZeroCommand());
				addSequential(new DriveXInchesCommand(75,0.75));
				addSequential(new RotateToAngleCWFromStartCommand(45));
//				addSequential(new DriveXInchesVisionCommand(53));
				addSequential(new DriveXInchesVisionCommand(61));
				
				addSequential(new WaitCommand(0.4));
				addSequential(new DriveXInchesCommand(-1.5,0.75));
				addSequential(new WaitCommand(0.15));

				addSequential(new OpenThenPunchGearCommand());
				addSequential(new WaitCommand(0.1));
				addSequential(new UnpunchCollectorCommand());
				addSequential(new WaitCommand(0.1));
				addSequential(new DriveXInchesCommand(-1.5,0.75));
				addSequential(new WaitCommand(0.75));
				
				addSequential(new OpenThenPunchGearCommand());
				addSequential(new WaitCommand(0.1));
				addSequential(new UnpunchCollectorCommand());
				addSequential(new WaitCommand(0.4));
				
				addSequential(new OpenThenPunchGearCommand());
				addSequential(new WaitCommand(0.1));
				
				addSequential(new DriveXInchesCommand(-20,0.75));
				addSequential(new WaitCommand(0.1));
				addSequential(new UnpunchCollectorCommand());				
				
				addSequential(new UnpunchThenCloseGearCommand());
				addSequential(new RotateToAngleCWFromStartCommand(0));
				addSequential(new DriveXInchesCommand(160, .7));
			
				break;
			
			case RIGHT_GEAR_MOBILITY:
				
				addSequential(new SetGyroZeroCommand());
				addSequential(new DriveXInchesCommand(75,0.75));
				addSequential(new RotateToAngleCWFromStartCommand(-45));
//				addSequential(new DriveXInchesVisionCommand(53));
				addSequential(new DriveXInchesVisionCommand(61));
				
				addSequential(new WaitCommand(0.4));
				addSequential(new DriveXInchesCommand(-1.5,0.75));
				addSequential(new WaitCommand(0.15));

				addSequential(new OpenThenPunchGearCommand());
				addSequential(new WaitCommand(0.1));
				addSequential(new UnpunchCollectorCommand());
				addSequential(new WaitCommand(0.1));
				addSequential(new DriveXInchesCommand(-1.5,0.75));
				addSequential(new WaitCommand(0.75));
				
				addSequential(new OpenThenPunchGearCommand());
				addSequential(new WaitCommand(0.1));
				addSequential(new UnpunchCollectorCommand());
				addSequential(new WaitCommand(0.4));
				
				addSequential(new OpenThenPunchGearCommand());
				addSequential(new WaitCommand(0.1));
				
				addSequential(new DriveXInchesCommand(-20,0.75));
				addSequential(new WaitCommand(0.1));
				addSequential(new UnpunchCollectorCommand());
				
				addSequential(new UnpunchThenCloseGearCommand());
				addSequential(new RotateToAngleCWFromStartCommand(0));
				addSequential(new DriveXInchesCommand(160, .7));
				
				break;
				
			case LEFT_BOILER_MOBILITY:

//				addSequential(new DriveXInchesCommand(120,0.65));

				addSequential(new SetShooterSpeedCommand(3800,3800));
				addSequential(new WaitCommand(0.3));
				addSequential(new SetAugerSpeedCommand(0.4,true));
				addSequential(new WaitCommand(10));
				addSequential(new DriveXInchesCommand(-20, .9));
				addSequential(new RotateToAngleCWFromStartCommand(-35));
				addSequential(new DriveXInchesCommand(-80, .9));
				
				break;
				
			case RIGHT_BOILER_MOBILITY:
				
				addSequential(new SetShooterSpeedCommand(3800,3800));
				addSequential(new WaitCommand(0.15));
				addSequential(new SetAugerSpeedCommand(0.4,true));
				addSequential(new WaitCommand(10));
				addSequential(new DriveXInchesCommand(-20, .9));
				addSequential(new RotateToAngleCWFromStartCommand(35));
				addSequential(new DriveXInchesCommand(-100, .9));
				
				break;
				
			case DRIVE_FORWARD:
				
				addSequential(new DriveXInchesCommand(80,0.85));
				
				break;
				
			case NOTHING:
				
				break;
				
			default:
				
				break;
		
		}
		
	}
	
	public enum AutonomousMode {
		CENTER_GEAR_AUTO_LEFT_BOILER,
		CENTER_GEAR_AUTO_RIGHT_BOILER,
		LEFT_GEAR_AUTO,
		RIGHT_GEAR_AUTO,
		RIGHT_GEAR_RIGHT_BOILER,
		LEFT_GEAR_LEFT_BOILER,
		LEFT_BOILER_MOBILITY,
		RIGHT_BOILER_MOBILITY,
		CENTER_GEAR_MOBILITY_LEFT,
		CENTER_GEAR_MOBILITY_RIGHT,
		LEFT_GEAR_MOBILITY,
		RIGHT_GEAR_MOBILITY,
		DRIVE_FORWARD,
		NOTHING;
	}
	
}