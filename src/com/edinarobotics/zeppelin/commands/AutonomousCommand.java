package com.edinarobotics.zeppelin.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousCommand extends CommandGroup {

	public AutonomousCommand(AutonomousMode mode) {
		
		switch(mode) {
			
			case CENTER_GEAR_AUTO:
//experimental end of 10k gear and shoot
				addSequential(new SetGyroZeroCommand());
				addSequential(new RaiseCenterWheelCommand());
				addSequential(new SetShooterSpeedCommand(0,0));
				addSequential(new SetAugerSpeedCommand(0,true));
				addSequential(new SetCollectorSpeedCommand(0));
				addSequential(new DriveXInchesCommand(30,0.9));
				addSequential(new RotateToAngleCWFromStartCommand(0));
				addSequential(new DriveXInchesVisionCommand(47.5));
				addSequential(new PunchCollectorCommand());
				addSequential(new WaitCommand(0.2));
				addSequential(new DriveXInchesCommand(-20,0.65));
				addSequential(new DriveXInchesCommand(-20,0.85));
				addSequential(new UnpunchCollectorCommand());
				addSequential(new WaitCommand(0.15));
				addSequential(new RotateToAngleCWFromStartCommand(75));
				addSequential(new WaitCommand(0.15));
				addSequential(new SetCollectorSpeedCommand(1.0));
				addSequential(new DriveXInchesCommand(93,0.9));
				addSequential(new WaitCommand(0.15));
				addSequential(new RotateToAngleCWFromStartCommand(125));
				addSequential(new SetShooterSpeedCommand(3300,3400));
				addSequential(new WaitCommand(0.15));
				addSequential(new DriveXInchesCommand(10,0.85));
				addSequential(new SetAugerSpeedCommand(0.4,true));
//				addSequential(new DriveXInchesCommand(10,0.85));
				addSequential(new WaitCommand(3));
				addSequential(new DriveXInchesCommand(-40,0.95));
				addSequential(new RotateToAngleCWFromStartCommand(170));
				addSequential(new DriveXInchesCommand(-48,0.95));
				addSequential(new LowerCenterWheelCommand());

				break;
				
			case LEFT_GEAR_AUTO:
				
				addSequential(new SetGyroZeroCommand());
				addSequential(new RaiseCenterWheelCommand());
				addSequential(new DriveXInchesCommand(75,0.75));
				addSequential(new RotateToAngleCWFromStartCommand(55));
				addSequential(new DriveXInchesVisionCommand(45));
				addSequential(new PunchCollectorCommand());
				addSequential(new WaitCommand(0.4));
				addSequential(new DriveXInchesCommand(-20,0.75));
				addSequential(new WaitCommand(0.4));
				addSequential(new UnpunchCollectorCommand());
				addSequential(new LowerCenterWheelCommand());
				
				break;
				
			case RIGHT_GEAR_AUTO:
				
				addSequential(new SetGyroZeroCommand());
				addSequential(new RaiseCenterWheelCommand());
				addSequential(new DriveXInchesCommand(75,0.75));
				addSequential(new RotateToAngleCWFromStartCommand(-55));
				addSequential(new DriveXInchesVisionCommand(45));
				addSequential(new PunchCollectorCommand());
				addSequential(new WaitCommand(0.4));
				addSequential(new DriveXInchesCommand(-20,0.75));
				addSequential(new WaitCommand(0.4));
				addSequential(new UnpunchCollectorCommand());
				addSequential(new LowerCenterWheelCommand());
				
				break;
				
			case HOPPER_BOILER_SHOOT_LEFT:
				
				addSequential(new SetGyroZeroCommand());
				addSequential(new DriveXInchesCommand(70,0.65));
				addSequential(new RotateToAngleCWFromStartCommand(-90));
				addSequential(new SetCollectorSpeedCommand(1.0));	
				addSequential(new DriveXInchesCommand(42,0.6));
				addSequential(new WaitCommand(3.5));				
				addSequential(new DriveXInchesCommand(-10,0.65));
				addSequential(new RotateToAngleCWFromStartCommand(-180));
				addSequential(new DriveXInchesCommand(66,0.65));
				addSequential(new RotateToAngleCWFromStartCommand(-135));
				addSequential(new DriveXInchesCommand(12,0.6));
				addSequential(new UniversalShootCommand());
				
				break;
				
			case HOPPER_BOILER_SHOOT_RIGHT:
				
				addSequential(new SetGyroZeroCommand());
				addSequential(new DriveXInchesCommand(70,0.65));
				addSequential(new RotateToAngleCWFromStartCommand(90));
				addSequential(new SetCollectorSpeedCommand(1.0));
				addSequential(new DriveXInchesCommand(42,0.65));
				addSequential(new WaitCommand(3.5));				
				addSequential(new DriveXInchesCommand(-10,0.65));
				addSequential(new RotateToAngleCWFromStartCommand(180));
				addSequential(new DriveXInchesCommand(66,0.65));
				addSequential(new RotateToAngleCWFromStartCommand(135));
				addSequential(new DriveXInchesCommand(12,0.6));
				addSequential(new UniversalShootCommand());
				
				break;
				
			case JUST_SHOOT:

				addSequential(new DriveXInchesCommand(120,0.65));

//				addSequential(new UniversalShootCommand());
				
			case NOTHING:
				
				break;
				
			default:
				
				break;
		
		}
		
	}
	
	public enum AutonomousMode {
		CENTER_GEAR_AUTO,
		LEFT_GEAR_AUTO,
		RIGHT_GEAR_AUTO,
		HOPPER_BOILER_SHOOT_LEFT,
		HOPPER_BOILER_SHOOT_RIGHT,
		JUST_SHOOT,
		NOTHING;
	}
	
}
