package com.edinarobotics.zeppelin.subsystems;

import com.ctre.CANTalon;
import com.edinarobotics.utils.subsystems.Subsystem1816;

import edu.wpi.first.wpilibj.Solenoid;

public class Collector extends Subsystem1816{

	private CANTalon collectorTalon;
	private Solenoid gearCollectorSolenoid;
	private Solenoid gearPuncherSolenoid;
	
	private double collectorSpeed;
	private boolean gearCollectorOpen = false;
	private boolean gearPuncherPunched = false;
	
	public Collector(int collectorTalon, int gearCollectorSolenoidID, int gearPuncherSolenoidID, int pcmNode){
		this.collectorTalon = new CANTalon(collectorTalon);
		this.gearCollectorSolenoid = new Solenoid(pcmNode, gearCollectorSolenoidID);
		this.gearPuncherSolenoid = new Solenoid(pcmNode, gearPuncherSolenoidID);
		this.gearCollectorOpen = false;
		this.gearPuncherPunched = false;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		collectorTalon.set(collectorSpeed);
	
		if(gearCollectorSolenoid.get()!=gearCollectorOpen)
			gearCollectorSolenoid.set(gearCollectorOpen);
		
		if(gearPuncherSolenoid.get()!=gearPuncherPunched)
			gearPuncherSolenoid.set(gearPuncherPunched);
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
	
}
