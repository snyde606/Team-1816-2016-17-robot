package com.edinarobotics.zeppelin.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousCommand extends CommandGroup {

	public AutonomousCommand(AutonomousMode mode) {
		
		switch(mode) {
			
			case CENTER_GEAR_AUTO:
				//accurate
				addSequential(new SetGyroZeroCommand());
				addSequential(new DriveXInchesCommand(30,0.5));
				addSequential(new RotateToAngleCWFromStartCommand(0));
				addSequential(new DriveXInchesVisionCommand(48.0));
				
				break;
				
			case LEFT_GEAR_AUTO:
				 //accurate
				addSequential(new SetGyroZeroCommand());
				addSequential(new DriveXInchesCommand(90,0.6));
				addSequential(new RotateToAngleCWFromStartCommand(55));
				addSequential(new DriveXInchesVisionCommand(45));
				
				break;
				
			case RIGHT_GEAR_AUTO:
				//accurate
				addSequential(new SetGyroZeroCommand());
				addSequential(new DriveXInchesCommand(62,0.6));
				addSequential(new RotateToAngleCWFromStartCommand(-55));
				addSequential(new DriveXInchesVisionCommand(70));
				
				break;
				
			case HOPPER_BOILER_SHOOT_LEFT:
				
				addSequential(new SetGyroZeroCommand());
				addSequential(new DriveXInchesCommand(70,0.6));
				addSequential(new RotateToAngleCWFromStartCommand(-90));
				addSequential(new SetCollectorSpeedCommand(1.0));	
				addSequential(new DriveXInchesCommand(42,0.6));
				addSequential(new WaitCommand(3.5));				
				addSequential(new DriveXInchesCommand(-10,0.6));
				addSequential(new RotateToAngleCWFromStartCommand(-180));
				addSequential(new DriveXInchesCommand(66,0.6));
				addSequential(new RotateToAngleCWFromStartCommand(-135));
				addSequential(new DriveXInchesCommand(12,0.5));
				addSequential(new UniversalShootCommand());
				
				break;
				
			case HOPPER_BOILER_SHOOT_RIGHT:
				
				addSequential(new SetGyroZeroCommand());
				addSequential(new DriveXInchesCommand(70,0.6));
				addSequential(new RotateToAngleCWFromStartCommand(90));
				addSequential(new SetCollectorSpeedCommand(1.0));
				addSequential(new DriveXInchesCommand(42,0.6));
				addSequential(new WaitCommand(3.5));				
				addSequential(new DriveXInchesCommand(-10,0.6));
				addSequential(new RotateToAngleCWFromStartCommand(180));
				addSequential(new DriveXInchesCommand(66,0.6));
				addSequential(new RotateToAngleCWFromStartCommand(135));
				addSequential(new DriveXInchesCommand(12,0.5));
				addSequential(new UniversalShootCommand());
				
				break;
				
			case JUST_SHOOT:
				
				addSequential(new DriveXInchesCommand(0,0.45));
				
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
