package com.edinarobotics.zeppelin.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousCommand extends CommandGroup {

	public AutonomousCommand(AutonomousMode mode) {
		
		switch(mode) {
			
			case CENTER_GEAR_AUTO:
				
				addSequential(new SetGyroZeroCommand());
				addSequential(new DriveXInchesVisionCommand(110));
				addSequential(new RotateToAngleCWFromStartCommand(0));
				addSequential(new VisionStrafeCommand(false));
				addSequential(new DriveXInchesCommand(3.0,0.3));
				
				break;
				
			case LEFT_GEAR_AUTO:
				
				addSequential(new SetGyroZeroCommand());
				addSequential(new DriveXInchesCommand(100,0.6));
				addSequential(new RotateToAngleCWFromStartCommand(60));
				addSequential(new VisionStrafeCommand(false));
				addSequential(new DriveXInchesVisionCommand(35));
				
				break;
				
			case RIGHT_GEAR_AUTO:
				
				addSequential(new SetGyroZeroCommand());
				addSequential(new DriveXInchesCommand(100,0.6));
				addSequential(new RotateToAngleCWFromStartCommand(-60));
				addSequential(new VisionStrafeCommand(false));
				addSequential(new DriveXInchesVisionCommand(35));
				
				break;
				
			case HOPPER_BOILER_SHOOT_LEFT:
				
				addSequential(new SetGyroZeroCommand());
				addSequential(new DriveXInchesCommand(105,0.6));
				addSequential(new RotateToAngleCWFromStartCommand(-90));
				addSequential(new DriveXInchesCommand(60,0.6));
				addSequential(new RotateToAngleCWFromStartCommand(-180));
				addSequential(new DriveXInchesCommand(84,0.6));
//				addSequential(new UniversalShootCommand());
				
				break;
				
			case HOPPER_BOILER_SHOOT_RIGHT:
				
				addSequential(new SetGyroZeroCommand());
				addSequential(new DriveXInchesCommand(105,0.6));
				addSequential(new RotateToAngleCWFromStartCommand(90));
				addSequential(new DriveXInchesCommand(60,0.6));
				addSequential(new RotateToAngleCWFromStartCommand(180));
				addSequential(new DriveXInchesCommand(84,0.6));
//				addSequential(new UniversalShootCommand());
				
				break;
				
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
		NOTHING;
	}
	
}
