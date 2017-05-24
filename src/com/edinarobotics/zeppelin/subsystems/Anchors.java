package com.edinarobotics.zeppelin.subsystems;

import com.edinarobotics.utils.subsystems.Subsystem1816;

import edu.wpi.first.wpilibj.Solenoid;

public class Anchors extends Subsystem1816{

	private Solenoid anchorPistonSolenoid;
	private boolean anchorsDown = false;
	private boolean universalShooting = false;

	public Anchors(int anchorSolenoidID, int pcmNode){
		this.anchorPistonSolenoid = new Solenoid(pcmNode, anchorSolenoidID);
		this.anchorPistonSolenoid.set(false);
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
//		if(anchorPistonSolenoid.get() != anchorsDown)
//			anchorPistonSolenoid.set(anchorsDown);
	}
//
//		Commented out because there's a lexan sheet that extends over max height
//		so we can't use our anchors while that's on there
//		
//		   

	public void anchorRobot(){
		anchorsDown = true;
		update();
	}
	public void unanchorRobot(){
		anchorsDown = false;
		update();
	}
	
	public Solenoid getAnchorPistonSolenoid(){
		return anchorPistonSolenoid;
	}
	
	public void setUniversalShooting(boolean shooting){
		universalShooting = shooting;
	}
	
	public boolean isUniversalShooting(){
		return universalShooting;
	}

}
