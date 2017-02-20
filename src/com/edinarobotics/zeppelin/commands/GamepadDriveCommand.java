package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.utils.gamepad.Gamepad;
import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GamepadDriveCommand extends Command{

	private Drivetrain drivetrain;
	private Gamepad gamepad;
	
	public GamepadDriveCommand(Gamepad gamepad) {
		// TODO Auto-generated constructor stub
		super("gamepaddrivecommand");
		this.drivetrain = Components.getInstance().drivetrain;
		this.gamepad = gamepad;
		requires(drivetrain);
	}

	@Override
	protected void initialize(){

	}
	
	@Override
	protected void execute(){
		double xStrafe = gamepad.getLeftJoystick().getX();
		double yStrafe = gamepad.getLeftJoystick().getY();
		double rotation = gamepad.getRightJoystick().getX();
		
//		SmartDashboard.putNumber("BackLeft Encoder: ", drivetrain.getBackLeftTalon().getEncPosition());
//		SmartDashboard.putNumber("BackRight Encoder: ", drivetrain.getBackRightTalon().getEncPosition());
//		SmartDashboard.putNumber("FrontLeft Encoder: ", drivetrain.getFrontLeftTalon().getEncPosition());
//		SmartDashboard.putNumber("FrontRight Encoder: ", drivetrain.getFrontRightTalon().getEncPosition());
//		SmartDashboard.putNumber("Center Encoder: ", drivetrain.getCenterTalon().getEncPosition());
		
		drivetrain.setValues(xStrafe, yStrafe, rotation);
//		System.out.println("Encoder BackRight: " + drivetrain.getBackRightTalon().getEncPosition() + "\nEncoder BackLeft: " + drivetrain.getBackLeftTalon().getEncPosition());
//		System.out.println("Encoder FrontRight: " + drivetrain.getFrontRightTalon().getEncPosition() + "\nEncoder BackLeft: " + drivetrain.getFrontLeftTalon().getEncPosition());
//		System.out.println("\nEncoder Center: " + drivetrain.getCenterTalon());
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
