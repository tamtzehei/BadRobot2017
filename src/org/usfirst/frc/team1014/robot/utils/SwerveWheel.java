package org.usfirst.frc.team1014.robot.utils;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

public class SwerveWheel {
	
	private Vector2d perpendicular, location;
	private CANTalon drive, pivot;
	private double offset;
	private double encoderMax;
	private double encoderMin;
	
	public SwerveWheel(Vector2d location, int driveMotorPin, int pivotMotorPin, double offset, double encoderMax, double encoderMin)
	{
		this.offset = offset;
		this.encoderMax = encoderMax;
		this.encoderMin = encoderMin;
		
		perpendicular = new Vector2d(location.getX(), -location.getY());
		
		drive = new CANTalon(driveMotorPin);
		pivot = new CANTalon(pivotMotorPin);
		
		this.location = location;
	}
	
	public void drive(Vector2d translation, SpeedControllerNormalizer normalizer)
	{   
		double range = encoderMax-encoderMin;
		double rawCurrent = pivot.getAnalogInRaw();
		double rawFinal = (range)*(Math.atan2(translation.getY(), translation.getX())/(2*Math.PI))+encoderMin;
		double diff = rawFinal-rawCurrent;
		double errorRange = 1023-range;
		double speed = translation.getMagnitude();

		
		if(Math.abs(diff)>(range)/4 )
		{
			speed = -1 * speed;
			diff= -1*(((range)/2)-diff);
		}
		
		pivot.changeControlMode(TalonControlMode.Position);
		pivot.setFeedbackDevice(FeedbackDevice.AnalogEncoder);
		pivot.setPID(4, 0, 0);
		pivot.enableControl();
		pivot.set(rawCurrent + diff + offset);
		
		
		normalizer.add(drive, speed);
		drive.set(speed);
	}

}
