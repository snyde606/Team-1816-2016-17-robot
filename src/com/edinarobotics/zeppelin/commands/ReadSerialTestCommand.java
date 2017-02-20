package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Command;

public class ReadSerialTestCommand extends Command {

	SerialPort serialPort;
	Drivetrain drivetrain;

	String temp = "";
	String oldstring = "";
	Integer x = 0;
	Integer y = 0;
	Integer fps = 0;

	public ReadSerialTestCommand() {
		super("readserialtestcommand");
		this.serialPort = new SerialPort(9600, SerialPort.Port.kMXP, 8,					//examine settings in RoboRealm:Serial to get the proper inputs. inputs in order are: baud rate, port type, data bits, parity, stop bits
				SerialPort.Parity.kNone, SerialPort.StopBits.kOne);
		this.drivetrain = Components.getInstance().drivetrain;
	}
	

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void execute() {

		int space1 = 0;
		int space2 = 0;
		temp = serialPort.readString();													//read the string from the serial port; store it into temp variable
		//System.out.println(temp.length());
		System.out.println("Read String: " + temp);
		
		//START STRING PARSING
		//string packet format is {xcoord ycoord fps}
		//stores xcoord into x; stores ycoord into y
		if(temp.length()>0){															//is string not blank?
			if (temp.substring(0, 1).equals("{")
					&& temp.substring(temp.length() - 1, temp.length()).equals("}")){		//does string begin with "{" and end with "}"?
				//START xcoord and ycoord extraction
				space1 = temp.indexOf(' ');														//find index of first space (in between xcoord and ycoord)
				space2 = temp.substring(space1 + 1).indexOf(' ') + space1 + 1;					//find index of second space (in between ycoord and fps)
				if(space1!=1){
					x = Integer.parseInt(temp.substring(1, space1), 10);							//select xcoord, convert it from String to int, store it in x	
					y = Integer.parseInt(temp.substring(space1 + 1, space2), 10);					//select ycoord, convert it from String to int, store it in y
				}
				System.out.println("X: " + x);													//print x coordinate
				System.out.println("Y: " + y);													//print y coordinate
				//END xcoord and ycoord extraction
			}
			else if(temp.substring(0, 1).equals("{")){										//does string begin with "{"? i.e. it's the first half of a packet?
				oldstring = temp;																//store string into oldstring variable
			}
			else if(temp.substring(temp.length() - 1, temp.length()).equals("}")){			//does string end with "}"? i.e. it's the second half of a packet?
				oldstring += temp;																//add string to oldstring (which should contain the first half of the packet already)
				if (oldstring.substring(0, 1).equals("{")										//go through extraction of xcoord and ycoord as explained above
						&& oldstring.substring(oldstring.length() - 1, oldstring.length()).equals("}")) {
					space1 = oldstring.indexOf(' ');
					space2 = oldstring.substring(space1 + 1).indexOf(' ') + space1 + 1;
					if(space1!=1){
						x = Integer.parseInt(oldstring.substring(1, space1), 10);
						y = Integer.parseInt(oldstring.substring(space1 + 1, space2), 10);
					}
					System.out.println("PiecedString: " + oldstring);
					System.out.println("X: " + x);
					System.out.println("Y: " + y);
				}
				else
					oldstring = "";
			}
		}
		//END STRING PARSING
		
			System.out.println("X: " + x + "\tY: " + y);
			
			// TODO Auto-generated method stub
		}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
