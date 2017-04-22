package com.edinarobotics.zeppelin.subsystems;//vroom vroom  vroom

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.edinarobotics.utils.subsystems.Subsystem1816;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Command;

public class Drivetrain extends Subsystem1816{

	private boolean slowMode = false;
	private boolean fieldCentric = false;
	private boolean centerWheelDown = true;
	
	private SerialPort serialPort;
	private SerialPort sendingSerial;//maximum strafe engaged!!!
	private String newString = "";
	private String oldString = "";
	private int visionX = 0;
	private int visionY = 0;
	private int visionArea = 0;
	
	private AHRS navX;
	private double gyroZero;
	private double autoGyroZero = 0;
	
	private final double SLOW_MODE = 0.5;
	private final double RAMP_RATE = 100; //woohooooo fast robot vroom vroom
	private final double P = 0.008;
	private final double I = 0.000;
	private final double D = 0.000;
	
	private CANTalon frontLeft, frontRight, backLeft, backRight, center;
//	private RobotDrive robotDrive;
	
	private Solenoid versaDropSolenoid;
	
	private double rotation, xStrafe, rightSpeed, leftSpeed;
	
	public Drivetrain(int frontLeft, int frontRight, int backLeft, int backRight, int center, int pcmNode, int dropSolenoidID) {
		// TODO Auto-generated constructor stub
		
		navX = new AHRS(Port.kMXP);
		
		try{
			this.serialPort = new SerialPort(9600, SerialPort.Port.kMXP, 8,					//examine settings in RoboRealm:Serial to get the proper inputs. inputs in order are: baud rate, port type, data bits, parity, stop bits
					SerialPort.Parity.kNone, SerialPort.StopBits.kOne);
		}catch(Exception e){System.out.println("serial port creation crashed");};
		
//		this.sendingSerial = new SerialPort(9600, SerialPort.Port.kUSB1, 8,					//examine settings in RoboRealm:Serial to get the proper inputs. inputs in order are: baud rate, port type, data bits, parity, stop bits
//				SerialPort.Parity.kNone, SerialPort.StopBits.kOne);
		
		this.frontLeft = new CANTalon(frontLeft);
		this.frontRight = new CANTalon(frontRight);
		this.backLeft = new CANTalon(backLeft);
		this.backRight = new CANTalon(backRight);
		this.center = new CANTalon(center);
		
		this.backLeft.setInverted(true);
		this.center.setInverted(true);
		
		this.frontLeft.enableBrakeMode(true);
		this.frontRight.enableBrakeMode(true);
		this.backLeft.enableBrakeMode(true);
		this.backRight.enableBrakeMode(true);
		this.center.enableBrakeMode(true);
		
		this.backLeft.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		this.backRight.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		this.frontLeft.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		this.frontRight.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		this.center.setFeedbackDevice(FeedbackDevice.QuadEncoder);
//		this.backLeft.changeControlMode(TalonControlMode.Speed);
//		this.backRight.changeControlMode(TalonControlMode.Speed);

//		this.backRight.setPID(P, I, D);
//		this.backLeft.setPID(P, I, D);
		
		this.frontLeft.changeControlMode(TalonControlMode.Follower);
		this.frontLeft.set(backLeft);
		this.frontRight.changeControlMode(TalonControlMode.Follower);
		this.frontRight.set(backRight);
		this.frontLeft.setVoltageRampRate(RAMP_RATE);
		this.frontRight.setVoltageRampRate(RAMP_RATE);
		this.backLeft.setVoltageRampRate(RAMP_RATE);
		this.backRight.setVoltageRampRate(RAMP_RATE);
		this.center.setVoltageRampRate(RAMP_RATE);
		
		this.versaDropSolenoid = new Solenoid(pcmNode, dropSolenoidID);
		
		centerWheelDown = true;
		
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		double leftVelocity = leftSpeed;
		double rightVelocity = rightSpeed;
		double centerVelocity = xStrafe;
		
		rightVelocity -= rotation*0.55;
		leftVelocity += rotation*0.55;
		
		backLeft.set(signum(leftVelocity,1,-1));
		backRight.set(signum(rightVelocity,1,-1));
		center.set(signum(centerVelocity,1,-1));
		
		if(versaDropSolenoid.get() != centerWheelDown)
			versaDropSolenoid.set(centerWheelDown);
		
	}
	
	public void setValues(double xStrafe, double yStrafe, double rotation){
		if(slowMode){
			yStrafe *= SLOW_MODE;
			rotation *= SLOW_MODE;
		}
	
//		if(fieldCentric){
//			double offset = gyroZero - navX.getAngle();
//			offset*=6.28/360;
//			
//			double xTemp = Math.sin(offset)*Math.abs(Math.sin(offset))*yStrafe;
//			double yTemp = -Math.sin(offset)*Math.abs(Math.sin(offset))*xStrafe;
//			xTemp += Math.cos(offset)*Math.abs(Math.cos(offset))*xStrafe;
//			yTemp += Math.cos(offset)*Math.abs(Math.cos(offset))*yStrafe;
//			
//			this.xStrafe = xTemp;
//			this.rightSpeed = yTemp;
//			this.leftSpeed = yTemp;
//			this.rotation = rotation;
//		}
//		else{
			this.xStrafe = xStrafe;
			this.rightSpeed = yStrafe;
			this.leftSpeed = yStrafe;
			this.rotation = rotation;
//		}
		
		update();
	}
	public void setDrivetrainSides(double leftSpeed, double rightSpeed){
		if(slowMode){
			leftSpeed *= SLOW_MODE;
			rightSpeed *= SLOW_MODE;
		}

		this.leftSpeed = leftSpeed;
		this.rightSpeed = rightSpeed;
		
		update();
	}
	
	public void setDrivetrainSides(double leftSpeed, double rightSpeed, double centerSpeed){
		if(slowMode){
			leftSpeed *= SLOW_MODE;
			rightSpeed *= SLOW_MODE;
		}

		this.leftSpeed = leftSpeed;
		this.rightSpeed = rightSpeed;
		this.xStrafe = centerSpeed;
		
		update();
	}
	
	public void lowerCenterWheel(){
		centerWheelDown = true;
		update();
	}
	public void raiseCenterWheel(){
		centerWheelDown = false;
		update();
	}

	public void oldOutdatedReadSerialXY(){
		int space1 = 0;
		int space2 = 0;
		int endBracket = 0;
		boolean successread = false;
		while(!successread)
			try{
				newString = serialPort.readString();	
				if(!newString.equals(""))
					successread = true;
				//read the string from the serial port; store it into temp variable
			} catch(Exception e){
				System.out.println("serialPort read messed up");
			}
		
		System.out.println("Raw Serial Port output: " + newString);

		//START STRING PARSING
		//string packet format is {xcoord ycoord fps}
		//stores xcoord into x; stores ycoord into y
		try{
			if(newString.length()>0){																			//is string not blank?
				if (newString.substring(0, 1).equals("{")
						&& newString.substring(newString.length() - 1, newString.length()).equals("}")){		//does string begin with "{" and end with "}"?
					//START xcoord and ycoord extraction
					//
					space1 = newString.indexOf(' ');															//find index of first space (in between xcoord and ycoord)
					space2 = newString.substring(space1 + 1).indexOf(' ') + space1 + 1;							//find index of second space (in between ycoord and fps)
					endBracket = newString.indexOf('}');
					if(space1!=1){
						visionX = Integer.parseInt(newString.substring(1, space1), 10);							//select xcoord, convert it from String to int, store it in visionX	
						visionY = Integer.parseInt(newString.substring(space1 + 1, space2), 10);				//select ycoord, convert it from String to int, store it in visionY
						visionArea = Integer.parseInt(newString.substring(space2+1, endBracket), 10);			//select vision target's Area, convert it from String to int, store it in visionArea
					}
					System.out.println("X: " + visionX);													//print x coordinate
					System.out.println("Y: " + visionY);													//print x coordinate
					System.out.println("A: " + visionArea);													//print y coordinate
					//END xcoord and ycoord extraction
				}
				else if(newString.substring(0, 1).equals("{")){												//does string begin with "{"? i.e. it's the first half of a packet?
					oldString = newString;																	//store string into oldstring variable
				}
				else if(newString.substring(newString.length() - 1, newString.length()).equals("}")){		//does string end with "}"? i.e. it's the second half of a packet?
					oldString += newString;																	//add string to oldstring (which should contain the first half of the packet already)
					if (oldString.substring(0, 1).equals("{")												//go through extraction of xcoord and ycoord as explained above
							&& oldString.substring(oldString.length() - 1, oldString.length()).equals("}")) {
						space1 = oldString.indexOf(' ');
						space2 = oldString.substring(space1 + 1).indexOf(' ') + space1 + 1;
						endBracket = newString.indexOf('}');
						if(space1!=1){
							visionX = Integer.parseInt(oldString.substring(1, space1), 10);
							visionY = Integer.parseInt(oldString.substring(space1 + 1, space2), 10);
							visionArea = Integer.parseInt(newString.substring(space2+1, endBracket), 10);
						}
						System.out.println("PiecedString: " + oldString);
						System.out.println("X: " + visionX);
						System.out.println("Y: " + visionY);
					}
					else
						oldString = "";
				}
			}
		} catch(Exception e){
			System.out.println("Serial parsing messed up");
			oldString = "";
			newString = "";
		}
		//END STRING PARSING
	}
	
	public boolean readSerialXY(){
		int space1 = 0;
		int space2 = 0;
		int endBracket = 0;
		boolean successread = false;
		double whileLoopStart = System.currentTimeMillis();
		while(!successread)
			try{
				System.out.println("looping while loop");
				newString = serialPort.readString();	
				if(!newString.equals(""))
					successread = true;
				if(Math.abs(whileLoopStart - System.currentTimeMillis()) > 3000){
					System.out.println("we're stuck");
					return true;
				}
				
				//read the string from the serial port; store it into temp variable
			} catch(Exception e){
				System.out.println("serialPort read messed up");
			}
		
		System.out.println("Raw Serial Port output: " + newString);

		//START STRING PARSING
		//string packet format is {xcoord,ycoord,area}
		//stores xcoord into visionX; stores ycoord into visionY; stores area into visionArea
		try{
			if(newString.length()>0){																			//is string not blank?
				if (newString.charAt(0) == '{' && newString.indexOf("}") > 0){		//does string begin with "{" and contain a "}"?
					//START xcoord and ycoord extraction
					String insidePacket = newString.substring(1, newString.indexOf("}"));
					String[] dataValues = insidePacket.split(",");
					if(dataValues.length > 0){
						visionX = (int)Double.parseDouble(dataValues[0]);							//select xcoord, convert it from String to double then cast to int, store it in visionX	
						visionY = (int)Double.parseDouble(dataValues[1]);				//select ycoord, convert it from String to double then cast to int, store it in visionY
						visionArea = (int)Double.parseDouble(dataValues[2]);			//select vision target's Area, convert it from String to double then cast to int, store it in visionArea
					}
					System.out.println("X: " + visionX);													//print x coordinate
					System.out.println("Y: " + visionY);													//print x coordinate
					System.out.println("A: " + visionArea);													//print y coordinate
					//END xcoord and ycoord extraction
				}
				else if(newString.substring(0, 1).equals("{")){												//does string begin with "{"? i.e. it's the first half of a packet?
					oldString = newString;																	//store string into oldstring variable
				}
				else if(newString.substring(newString.length() - 1, newString.length()).equals("}")){		//does string end with "}"? i.e. it's the second half of a packet?
					oldString += newString;																	//add string to oldstring (which should contain the first half of the packet already)
					if (newString.charAt(0) == '{' && newString.indexOf("}") > 0){		//does string begin with "{" and contain a "}"?
						//START xcoord and ycoord extraction
						String insidePacket = newString.substring(1, newString.indexOf("}"));
						String[] dataValues = insidePacket.split(",");
						if(dataValues.length > 0){
							visionX = (int)Double.parseDouble(dataValues[0]);							//select xcoord, convert it from String to double then cast to int, store it in visionX	
							visionY = (int)Double.parseDouble(dataValues[1]);				//select ycoord, convert it from String to double then cast to int, store it in visionY
							visionArea = (int)Double.parseDouble(dataValues[2]);			//select vision target's Area, convert it from String to double then cast to int, store it in visionArea
						}
						System.out.println("X: " + visionX);													//print x coordinate
						System.out.println("Y: " + visionY);													//print x coordinate
						System.out.println("A: " + visionArea);													//print y coordinate
						//END xcoord and ycoord extraction
					}
					else
						oldString = "";
				}
			}
		} catch(Exception e){
			System.out.println("Serial parsing messed up");
			oldString = "";
			newString = "";
		}
		
		return false;
		//END STRING PARSING
	}
	
//	public void writeToSerial(String sendString){
//		sendingSerial.writeString(sendString);
//	}
	
	public void clearSerial(){
		serialPort.reset();
	}
	
	public void readSerial(){
		serialPort.readString();
	}
	
	@Override
	public void setDefaultCommand(Command command) {
		if (getDefaultCommand() != null) {
			super.getDefaultCommand().cancel();
		}
		super.setDefaultCommand(command);
	}
	
	public double getGyroAngle(){
		return navX.getAngle();
	}
	
	public double getAutoGyroZero(){
		return autoGyroZero;
	}
	
	public void setGyroZero(){
		gyroZero = navX.getAngle();
	}
	
	public void setAutoGyroZero(double gyroZero){
		autoGyroZero = gyroZero;
	}
	
	public void setFieldCentric(boolean fCentric){
		fieldCentric = fCentric;
		update();
	}
	public boolean isFieldCentric(){
		return fieldCentric;
	}
	
	public void setSlowMode(boolean sMode){
		slowMode = sMode;
		update();
	}
	
	public CANTalon getBackLeftTalon(){
		return backLeft;
	}
	public CANTalon getBackRightTalon(){
		return backRight;
	}
	public CANTalon getFrontLeftTalon(){
		return frontLeft;
	}
	public CANTalon getFrontRightTalon(){
		return frontRight;
	}
	public CANTalon getCenterTalon(){
		return center;
	}
	public Solenoid getVersaDropSolenoid(){
		return versaDropSolenoid;
	}
	public SerialPort getSerialPort(){
		return serialPort;
	}
	public int getVisionX(){
		return visionX;
	}
	public int getVisionY(){
		return visionY;
	}
	public int getVisionArea(){
		return visionArea;
	}
	
	private double signum(double value, double max, double min) {		
		if (value > max) {
			value = max;
		} else if (value < min) {
			value = min;
		}
		
		return value;
	}
}
