package com.edinarobotics.zeppelin.subsystems;

import com.ctre.CANTalon;
import com.edinarobotics.utils.subsystems.Subsystem1816;

import edu.wpi.first.wpilibj.Solenoid;

public class Collector extends Subsystem1816{

	private CANTalon collectorTalon;
	private Solenoid gearCollectorSolenoid;
	
	private double collectorSpeed;
	private boolean gearCollectorOpen = false;
	
	public Collector(int collectorTalon, int gearSolenoidID, int pcmNode){
		this.collectorTalon = new CANTalon(collectorTalon);
		this.gearCollectorSolenoid = new Solenoid(pcmNode, gearSolenoidID);
		this.gearCollectorOpen = false;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		collectorTalon.set(collectorSpeed);
	
		if(gearCollectorSolenoid.get()!=gearCollectorOpen)
			gearCollectorSolenoid.set(gearCollectorOpen);
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

}
