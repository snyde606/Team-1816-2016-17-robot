package com.edinarobotics.zeppelin.subsystems;

import com.ctre.CANTalon;
import com.edinarobotics.utils.subsystems.Subsystem1816;

import edu.wpi.first.wpilibj.Solenoid;

public class Collector extends Subsystem1816{

	private CANTalon collectorTalon;
	private Solenoid gearCollectorSolenoid;
	private Solenoid gearPuncherSolenoid;
	private Solenoid gearDoorSolenoid;

	
	private double collectorSpeed;
	private boolean gearCollectorOpen = false;
	private boolean gearPuncherPunched = false;
	private boolean gearDoorsOpen = false;
	
	public Collector(int collectorTalon, int gearCollectorSolenoidID, int gearPuncherSolenoidID, int geardoors, int pcmNode){
		this.collectorTalon = new CANTalon(collectorTalon);
		this.gearCollectorSolenoid = new Solenoid(pcmNode, gearCollectorSolenoidID);
		this.gearPuncherSolenoid = new Solenoid(pcmNode, gearPuncherSolenoidID);
		this.gearDoorSolenoid = new Solenoid(pcmNode, geardoors);
		this.gearCollectorOpen = false;
		this.gearPuncherPunched = false;
		this.gearDoorsOpen = false;
		this.collectorSpeed = 0.0;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		collectorTalon.set(collectorSpeed);
	
		if(gearCollectorSolenoid.get()!=gearCollectorOpen)
			gearCollectorSolenoid.set(gearCollectorOpen);
		
		if(gearPuncherSolenoid.get()!=gearPuncherPunched)
			gearPuncherSolenoid.set(gearPuncherPunched);
		
		if(gearDoorSolenoid.get()!=gearDoorsOpen)
			gearDoorSolenoid.set(gearDoorsOpen);
	}
	
	public void setCollectorTalon(double cSpeed){
		collectorSpeed = cSpeed;
		update();
	}
	
	public CANTalon getCollectorTalon(){
		return collectorTalon;
	}
	
	public void openGearCollector(){
		gearCollectorOpen = true;
		update();
	}
	
	public void closeGearCollector(){
		gearCollectorOpen = false;
		update();
	}
	
	public void punchGearCollector(){
		gearPuncherPunched = true;
		update();
	}

	public void unpunchGearCollector(){
		gearPuncherPunched = false;
		update();
	}
	
	public void openGearDoors(){
		gearDoorsOpen = true;
		update();
	}

	public void closeGearDoors(){
		gearDoorsOpen = false;
		update();
	}
	
}
