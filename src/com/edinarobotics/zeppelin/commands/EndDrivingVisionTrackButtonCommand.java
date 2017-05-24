package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

public class EndDrivingVisionTrackButtonCommand extends Command{

	private Drivetrain drivetrain;
	
	public EndDrivingVisionTrackButtonCommand() {
		super("enddrivingvisiontrackbuttoncommand");
		drivetrain = Components.getInstance().drivetrain;
		requires(drivetrain);
		// TODO Auto-generated constructor stub
	}

	public void initialize(){
		System.out.println("INIT END DRIVING VISION BUTTON");
		
		drivetrain.setVisionButtonPressed(true);
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}
