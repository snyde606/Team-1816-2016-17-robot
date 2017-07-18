
package com.edinarobotics.zeppelin;

import com.edinarobotics.utils.gamepad.Gamepad;
import com.edinarobotics.zeppelin.commands.AutonomousCommand;
import com.edinarobotics.zeppelin.commands.AutonomousCommand.AutonomousMode;
import com.edinarobotics.zeppelin.commands.GamepadAugersCommand;
import com.edinarobotics.zeppelin.commands.GamepadClimberCommand;
import com.edinarobotics.zeppelin.commands.GamepadDriveCommand;
import com.edinarobotics.zeppelin.subsystems.Augers;
import com.edinarobotics.zeppelin.subsystems.Climber;
import com.edinarobotics.zeppelin.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Zeppelin extends IterativeRobot {

	Command autonomousCommand;
	SendableChooser<Command> chooser;

	private Drivetrain drivetrain;
	private Augers augers;
	private Climber climber;
	
	AnalogInput ai;
	double bits;
	@Override
	public void robotInit() {
		Controls.getInstance();
		Components.getInstance();
		
		drivetrain = Components.getInstance().drivetrain;
		augers = Components.getInstance().augers;
		climber = Components.getInstance().climber;
		
		ai = new AnalogInput(2);
		
		
		ai.setOversampleBits(4);
		ai.setAverageBits(2);
		bits = ai.getAverageVoltage();
		AnalogInput.setGlobalSampleRate(62500);
		
		chooser = new SendableChooser<Command>();		
		
		chooser.addObject("Center Gear Auto Right Boiler", new AutonomousCommand(AutonomousMode.CENTER_GEAR_AUTO_RIGHT_BOILER));
		chooser.addObject("Center Gear Auto Left Boiler", new AutonomousCommand(AutonomousMode.CENTER_GEAR_AUTO_LEFT_BOILER));
		chooser.addObject("Right Gear Auto Right Boiler",new AutonomousCommand(AutonomousMode.RIGHT_GEAR_RIGHT_BOILER));
		chooser.addObject("Left Gear Auto Left Boiler", new AutonomousCommand(AutonomousMode.LEFT_GEAR_LEFT_BOILER));
		chooser.addObject("Left Gear Auto", new AutonomousCommand(AutonomousMode.LEFT_GEAR_AUTO));
		chooser.addObject("Right Gear Auto", new AutonomousCommand(AutonomousMode.RIGHT_GEAR_AUTO));
		chooser.addObject("Right Boiler Mobility", new AutonomousCommand(AutonomousMode.RIGHT_BOILER_MOBILITY));
		chooser.addObject("Left Boiler Mobility", new AutonomousCommand(AutonomousMode.LEFT_BOILER_MOBILITY));
		chooser.addObject("Left Gear Mobility", new AutonomousCommand(AutonomousMode.LEFT_GEAR_MOBILITY));
		chooser.addObject("Right Gear Mobility", new AutonomousCommand(AutonomousMode.RIGHT_GEAR_MOBILITY));
		chooser.addDefault("Center Gear Mobility Right", new AutonomousCommand(AutonomousMode.CENTER_GEAR_MOBILITY_RIGHT));
		chooser.addDefault("Center Gear Mobility Left", new AutonomousCommand(AutonomousMode.CENTER_GEAR_MOBILITY_LEFT));
		chooser.addObject("Drive", new AutonomousCommand(AutonomousMode.DRIVE_FORWARD));
		chooser.addObject("Nothing", new AutonomousCommand(AutonomousMode.NOTHING));
		SmartDashboard.putData("Autonomous", chooser);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = (Command)chooser.getSelected();
		autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		
		Gamepad gamepad0 = Controls.getInstance().gamepad0;
		Gamepad gamepad1 = Controls.getInstance().gamepad1;
		drivetrain.setDefaultCommand(new GamepadDriveCommand(gamepad0));
		augers.setDefaultCommand(new GamepadAugersCommand(gamepad1));
		climber.setDefaultCommand(new GamepadClimberCommand(gamepad1));
		drivetrain.setGyroZero();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
