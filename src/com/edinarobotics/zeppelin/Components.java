package com.edinarobotics.zeppelin;

import com.edinarobotics.zeppelin.subsystems.Anchors;
import com.edinarobotics.zeppelin.subsystems.Augers;
import com.edinarobotics.zeppelin.subsystems.Climber;
import com.edinarobotics.zeppelin.subsystems.Collector;
import com.edinarobotics.zeppelin.subsystems.Drivetrain;
import com.edinarobotics.zeppelin.subsystems.Shooter;

import edu.wpi.first.wpilibj.Compressor;

public class Components {

	private static Components instance;//Jacob why r u so disrespectful
	
	public Drivetrain drivetrain;
	public Collector collector;
	public Shooter shooter;
	public Augers augers;
	public Climber climber;
	public Anchors anchors;
	
	public Compressor compressor;//boooooooooooooooooooooooooo Jacob sucks

	//Drivetrain constants
	private static final int FRONT_LEFT_TALON = 2;
	private static final int FRONT_RIGHT_TALON = 8; //Trent likes big Ds apparently   
	private static final int BACK_LEFT_TALON = 1;
	private static final int BACK_RIGHT_TALON = 9;
	private static final int CENTER_TALON = 3;
	//End Drivetrain constants
	
	//Collector constants
	private static final int COLLECTOR_TALON = 5; //collector talon!!! whooooohooooooooooooo
	private static final int GEAR_COLLECTOR_SOLENOID = 5;
	private static final int GEAR_PUNCHER_SOLENOID = 99;
	//End Collector constants
	
	//Shooter constants
	private static final int RIGHT_SHOOTER_TALON = 11;
	private static final int LEFT_SHOOTER_TALON = 10;
	//End Shooter constants
	
	//Augers constants
	private static final int RIGHT_AUGER_TALON = 6;
	private static final int LEFT_AUGER_TALON = 7;
	//End Augers constants
	
	//Climber constants
	private static final int CLIMBER_TALON = 4;
	//End Climber constants
	
	//Pneumatic constants
	private static final int VERSA_DROP_SOLENOID = 7;
	private static final int ANCHOR_PISTON_SOLENOID = 6;
	private static final int PCM_NODE_ID = 10;
	//End Pneumatic constants
	
	public Components() {
		// TODO Auto-generated constructor stub
		drivetrain = new Drivetrain(FRONT_LEFT_TALON, FRONT_RIGHT_TALON, //Jacob your code is garbage
				BACK_LEFT_TALON, BACK_RIGHT_TALON, CENTER_TALON, PCM_NODE_ID, VERSA_DROP_SOLENOID);
		
		collector = new Collector(COLLECTOR_TALON, GEAR_COLLECTOR_SOLENOID, GEAR_PUNCHER_SOLENOID, PCM_NODE_ID);
		
		shooter = new Shooter(RIGHT_SHOOTER_TALON, LEFT_SHOOTER_TALON);
		
		augers = new Augers(LEFT_AUGER_TALON,RIGHT_AUGER_TALON);
		
		climber = new Climber(CLIMBER_TALON);
		
		anchors = new Anchors(ANCHOR_PISTON_SOLENOID, PCM_NODE_ID);
		
		compressor = new Compressor(PCM_NODE_ID);
		compressor.start();	
	}
	
	public static Components getInstance() {
		if (instance == null) {
			instance = new Components();
		}
		return instance;
	}

}
