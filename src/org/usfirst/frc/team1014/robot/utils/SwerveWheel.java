package org.usfirst.frc.team1014.robot.utils;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

public class SwerveWheel {
	
	private Vector2d perpendicular, location;
	private CANTalon drive, pivot;
	
	public SwerveWheel(Vector2d location, int driveMotorPin, int pivotMotorPin)
	{
		perpendicular = new Vector2d(location.getX(), -location.getY());
		
		drive = new CANTalon(driveMotorPin);
		pivot = new CANTalon(pivotMotorPin);
		
		this.location = location;
	}
	
	public void drive(Vector2d translation, SpeedControllerNormalizer normalizer)
	{   
		double encoderMax = 880;
		double encoderMin = 12;
		double rawCurrent = pivot.getAnalogInRaw();
		double rawFinal = (encoderMax-encoderMin)*(Math.atan2(translation.getY(), translation.getX())/(2*Math.PI))+encoderMin;
		double diff = rawFinal-rawCurrent;
		
		if(Math.abs(diff)>(encoderMax-encoderMin)/4 )
			diff= -1*((encoderMax-encoderMin)-diff);
		
		
		
		
		
		
		normalizer.add(drive, speed);
	}

}
