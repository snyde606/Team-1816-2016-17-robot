package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

public class WriteToSerialCommand extends Command{

	private Drivetrain drivetrain;
	private String stringSend;
	
	public WriteToSerialCommand(String stringSend){
		super("writetoserialcommand");
		this.drivetrain = Components.getInstance().drivetrain;
		this.stringSend = stringSend;
		requires(drivetrain);
	}
	
	protected void initialize(){
//		drivetrain.writeToSerial(stringSend);
		System.out.println("Writing: " + stringSend);
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}
