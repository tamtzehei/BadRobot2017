package org.usfirst.frc.team1014.robot.utils;

import org.usfirst.frc.team1014.robot.util.Vector2d;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Encoder;

public class SwerveWheel {

	private Vector2d perpendicular;
	private CANTalon drive, pivot;
	private double offset;
	private double encoderMax;
	private double encoderMin;
	private double range;
	Vector2d move;
	double finalPosition;
	Encoder encoder;
	private double encoderCPR;

	public SwerveWheel(Vector2d location, int driveMotorPin, int pivotMotorPin, double offset, double encoderMax,
			double encoderMin, int encoderAPin, int encoderBPin, double encoderCPR) {

		this.encoderCPR = encoderCPR;
		this.offset = offset;
		this.encoderMax = encoderMax;
		this.encoderMin = encoderMin;
		range = encoderMax - encoderMin;

		perpendicular = location.perpendicularCW();
		perpendicular = perpendicular.normalize();

		drive = new CANTalon(driveMotorPin);
		pivot = new CANTalon(pivotMotorPin);
		encoder = new Encoder(encoderAPin, encoderBPin);

		pivot.changeControlMode(TalonControlMode.Position);
		pivot.setFeedbackDevice(FeedbackDevice.AnalogEncoder);
		pivot.setPID(16, 0, 0);
		pivot.enableControl();
	}

	public void drive(Vector2d translation, double rotation, SpeedControllerNormalizer normalizer) {

		double speed = 0.0;
		double negativeIfInverted = 1.0;

		// Magnitude of rotation cancels out with radius of circle of rotation
		move = translation.add(perpendicular.scale(rotation));

		double currentPosition = pivot.getPosition();
		double rawCurrent = pivot.getAnalogInRaw();
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

	public void relativeDrive(double angle, double speed, SpeedControllerNormalizer normalizer) {
		angle /= 2d * Math.PI;
		double turn_speed = rotateFunc(angle);
		if ((angle - getAngle()) % 1 > .25 && (angle - getAngle()) % 1 < .75)
			speed = -speed;
		normalizer.add(drive, speed);
		pivot.set(turn_speed);
	}

	private double rotateFunc(double angle) {
		angle -= getAngle();
		angle = angle % 1d;
		if (angle < .25)
			return angle * 4;
		if (angle > .75)
			return (angle - 1) * 4;
		return (angle - .5) * 4;
	}

	public void center() {
		encoder.reset();
	}

	public double getAngle() {
		return ((double) encoder.get()) / encoderCPR;
	}
}
