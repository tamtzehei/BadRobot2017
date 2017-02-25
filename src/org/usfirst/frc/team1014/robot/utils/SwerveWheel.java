package org.usfirst.frc.team1014.robot.utils;

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
	String moduleID;
	TankPosition tankPosition;

	public enum TankPosition {
		LEFT, RIGHT;
	}


	public SwerveWheel(String moduleID, TankPosition tankPosition, Vector2d location, int driveMotorPin, int pivotMotorPin, double offset,
			double encoderMax, double encoderMin) {

		this.moduleID = moduleID;
		this.offset = offset;
		this.encoderMax = encoderMax;
		this.encoderMin = encoderMin;
		range = encoderMax - encoderMin;
		this.tankPosition = tankPosition;

		perpendicular = location.perpendicularCW().rotateDegrees(90);
		perpendicular = perpendicular.normalize();

		drive = new CANTalon(driveMotorPin);
		pivot = new CANTalon(pivotMotorPin);

		pivot.changeControlMode(TalonControlMode.Position);
		pivot.setFeedbackDevice(FeedbackDevice.AnalogEncoder);
		pivot.setPID(32, 0, 0);
		pivot.enableControl();
	}

	public void drive(Vector2d translation, double rotation, SpeedControllerNormalizer normalizer) {

		double speed = 0.0;
		double negativeIfInverted = 1.0;


		Vector2d move = translation.add(perpendicular.scale(rotation));

		double currentPosition = pivot.getPosition();
		double rawCurrent = pivot.getAnalogInRaw();

		System.out.println(moduleID + ": " + rawCurrent);

		double currentRadians = (Math.PI * 2.0 * (rawCurrent - offset)) / range;

		Vector2d currentVector = new Vector2d(Math.cos(currentRadians), Math.sin(currentRadians));

		// If the wheel is turning more than 90 degrees negate the vector and
		// speed to make the wheel go the most efficient path
		if ((currentVector.getX() * move.getX() + currentVector.getY() * move.getY()) < 0.0) {
			move = move.scale(-1.0);
			negativeIfInverted = -1.0;
		}

		double rawFinal = getRawFinal(move);

		double finalPosition = getFinalPosition(rawFinal, currentPosition);

		// If the change is very small assume no movement wanted so set the
		// final to the current
		if (move.magnitude() < .15) {
			speed = 0.0;
			finalPosition = currentPosition;
		}

		speed = negativeIfInverted * move.magnitude();

		pivot.set(finalPosition);

		normalizer.add(drive, speed);
	}


	/**
	 *
	 * @param move
	 *            - vector robot should move
	 * @return - the final encoder value between the max and min
	 */
	public double getRawFinal(Vector2d move) {
		double rawFinal = ((range / (2.0 * Math.PI)) * Math.atan2(move.getY(), move.getX())) + offset;

		// Ensure rawFinal is between the max and min
		if (rawFinal < encoderMin) {
			rawFinal += range;
		}

		if (rawFinal > encoderMax) {
			rawFinal -= range;
		}

		return rawFinal;
	}

	/**
	 *
	 * @param rawFinal
	 *            - the final encoder value between the max and min
	 * @param currentPosition
	 *            - the current encoder value of the wheel
	 * @return - the encoder value the motor should turn to
	 */
	public double getFinalPosition(double rawFinal, double currentPosition) {
		finalPosition = rawFinal + Math.floor(currentPosition / 1024.0) * 1024.0;

		// Account for the encoder crossing 0 or 1024
		if ((finalPosition - currentPosition) > (range / 4.0))
			finalPosition -= 1024.0;

		if ((finalPosition - currentPosition) < (-range / 4.0))
			finalPosition += 1024.0;

		return finalPosition;
	}

	public void tankDrive(double rightInput, double leftInput, double encoderPosition) {
		pivot.set(encoderPosition);

		double driveSpeed = tankPosition == TankPosition.LEFT ? leftInput : rightInput;
		drive.set(driveSpeed);

	}
}
