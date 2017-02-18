package org.usfirst.frc.team1014.robot.utils;

import org.usfirst.frc.team1014.robot.util.Vector2d;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

public class SwerveWheel {

	private Vector2d perpendicular;
	private CANTalon drive, pivot;
	private double offset;
	private double encoderMax;
	private double encoderMin;
	private double range;
	Vector2d move;
	double finalPosition;

	public SwerveWheel(Vector2d location, int driveMotorPin, int pivotMotorPin, double offset, double encoderMax,
			double encoderMin) {

		this.offset = offset;
		this.encoderMax = encoderMax;
		this.encoderMin = encoderMin;
		range = encoderMax - encoderMin;

		perpendicular = location.perpendicularCCW();

		drive = new CANTalon(driveMotorPin);
		pivot = new CANTalon(pivotMotorPin);
	}

	public void drive(Vector2d translation, double rotation, SpeedControllerNormalizer normalizer) {

		double speed = 0;
		int negativeIfInverted = 1;

		// Magnitude of rotation cancels out with radius of circle of rotation
		move = translation.add(perpendicular.scale(rotation));

		double currentPosition = pivot.getPosition();
		double rawCurrent = pivot.getAnalogInRaw();
		double currentRadians = (Math.PI * 2 * (rawCurrent - offset)) / range;
		Vector2d currentVector = new Vector2d(Math.cos(currentRadians), Math.sin(currentRadians));

		// if dot product is less than 0 that means the angle is obtuse so we
		// need to make the translation vector negative

		// double rawFinal = (range) * (Math.atan2(translation.getY(),
		// translation.getX()) / (2 * Math.PI)) + encoderMin + offset;
		double rawFinal = (range / (2 * Math.PI)) * Math.atan2(move.getY(), move.getX()) + offset;

		if (rawFinal < encoderMin) {
			rawFinal += range;
		}

		if (rawFinal > encoderMax) {
			rawFinal -= range;
		}

		finalPosition = rawFinal + Math.floor(currentPosition / 1024) * 1024;
		
		if(finalPosition - currentPosition > range / 2)
			finalPosition -= 1024;
		
		if(finalPosition - currentPosition < -range / 2)
			finalPosition += 1024;
		
		if ((currentVector.getX() * move.getX() + currentVector.getY() * move.getY()) < 0) {
			move.scale(-1);
			//translation = new Vector2d((-1 * move.getX()), (-1 * move.getY()));
			negativeIfInverted = -1;
		}
		
		/*if (rawFinal < rawCurrent && (rawCurrent - rawFinal) > (range / 2))
			n++;

		if (rawFinal > rawCurrent && (rawFinal - rawCurrent) > (range / 2))
			n--;*/

		//double finalPosition = rawFinal + (n * 1024);
		
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
		speed = negativeIfInverted * move.magnitude();
		
		if(move.magnitude() < .15){
			speed = 0;
			finalPosition = currentPosition;
		}

		pivot.changeControlMode(TalonControlMode.Position);
		pivot.setFeedbackDevice(FeedbackDevice.AnalogEncoder);
		pivot.setPID(4, 0, 0);
		pivot.enableControl();
		pivot.set(finalPosition);


		//normalizer.add(drive, speed);
		drive.set(speed);
	}

}
