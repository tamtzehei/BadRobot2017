package org.usfirst.frc.team1014.robot.utils;

import org.usfirst.frc.team1014.robot.util.Vector2d;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

public class SwerveWheel {

	private Vector2d perpendicular, location;
	private CANTalon drive, pivot;
	private double offset;
	private double encoderMax;
	private double encoderMin;
	private double range;

	public SwerveWheel(Vector2d location, int driveMotorPin, int pivotMotorPin, double offset, double encoderMax,
			double encoderMin) {
		this.offset = offset;
		this.encoderMax = encoderMax;
		this.encoderMin = encoderMin;
		range = encoderMax - encoderMin;

		perpendicular = new Vector2d(location.getX(), -location.getY());

		drive = new CANTalon(driveMotorPin);
		pivot = new CANTalon(pivotMotorPin);

		this.location = location;
	}

	public void drive(Vector2d translation, double rotation, SpeedControllerNormalizer normalizer) {
		int negativeIfInverted = 1;

		//translation.add(perpendicular.scale(rotation));
		
		translation = new Vector2d(translation.getX() + rotation * perpendicular.getX(), translation.getY() + rotation * perpendicular.getY());

		double speed = translation.magnitude();

		double currentPosition = pivot.getPosition();
		double rawCurrent = pivot.getAnalogInRaw();
		double currentRadians = (Math.PI * 2 * (rawCurrent - offset)) / range;
		Vector2d currentVector = new Vector2d(Math.cos(currentRadians), Math.sin(currentRadians));
		if ((currentVector.getX() * translation.getX() + currentVector.getY() * translation.getY()) < 0) {
			translation = new Vector2d((-1 * translation.getX()), (-1 * translation.getY()));
			negativeIfInverted = -1;
		}

		;

		// double rawFinal = (range) * (Math.atan2(translation.getY(),
		// translation.getX()) / (2 * Math.PI)) + encoderMin + offset;
		double rawFinal = (range / (2 * Math.PI) * Math.atan2(translation.getY(), translation.getX()) + offset);

		if (rawFinal < encoderMin) {
			rawFinal += range;
		}

		int n = (int) (currentPosition / 1024);

		if (rawFinal < rawCurrent && (rawCurrent - rawFinal) > (range / 2))
			n++;

		if (rawFinal < rawCurrent && (rawFinal - rawCurrent) > (range / 2))
			n--;

		double finalPosition = rawFinal + (double) (n) * 1024;
		/*
		 * double diff = finalPosition - currentPosition;
		 * 
		 * if(currentPosition % 1024 + diff > encoderMax) n++;
		 */
		/*
		 * if (Math.abs(diff) > (range) / 4) { speed = -1 * speed; diff = -1 *
		 * (((range) / 2) - diff); }
		 * 
		 * if(rawCurrent + diff > encoderMax){ diff += (1024 - range); }
		 * 
		 * if(rawCurrent + diff < encoderMin){ diff -= (1024 - range); }
		 * 
		 */

		pivot.changeControlMode(TalonControlMode.Position);
		pivot.setFeedbackDevice(FeedbackDevice.AnalogEncoder);
		pivot.setPID(4, 0, 0);
		pivot.enableControl();
		pivot.set(finalPosition);

		normalizer.add(drive, speed * negativeIfInverted);
		drive.set(speed * negativeIfInverted);
	}

}
