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
		
		Vector2d rotate = perpendicular.scale(rotation);
		Vector2d move = rotate.add(translation);//new Vector2d((rotate.getX() + translation.getX()), (rotate.getY() + translation.getY()));
		
		pivot.changeControlMode(TalonControlMode.Position);
		pivot.setFeedbackDevice(FeedbackDevice.AnalogEncoder);
		pivot.setPID(16.0, 0.0, 0.0);
		pivot.enableControl();
		
		double position = (Math.atan2(move.getY(), move.getX()) - (Math.PI / 2) * 2.84);
		
		pivot.set(position);
		
		double speed = move.getMagnitude();//Math.sqrt(move.getX() * move.getX() + move.getY() * move.getY());
		
		drive.set(speed);
		normalizer.add(drive, speed);
	}

}
