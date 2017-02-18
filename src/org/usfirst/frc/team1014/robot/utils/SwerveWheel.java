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
		
		//if (Math.acos(((currentVector.getX() * move.getX()) + (currentVector.getY() * move.getY())) / (currentVector.magnitude() * move.magnitude())) > Math.PI / 2) {
		if((currentVector.getX() * move.getX() + currentVector.getY() * move.getY()) < 0.0)	{
			move = move.scale(-1.0);
			negativeIfInverted = -1.0;
		}
	
		double rawFinal = ((range / (2.0 * Math.PI)) * Math.atan2(move.getY(), move.getX())) + offset;

		if (rawFinal < encoderMin) {
			rawFinal += range;
		}

		if (rawFinal > encoderMax) {
			rawFinal -= range;
		}

		finalPosition = rawFinal + Math.floor(currentPosition / 1024.0) * 1024.0;

		if ((finalPosition - currentPosition) > (range / 4.0))
			finalPosition -= 1024.0;

		if ((finalPosition - currentPosition) < (-range / 4.0))
			finalPosition += 1024.0;

		if (move.magnitude() < .15) {
			speed = 0.0;
			finalPosition = currentPosition;
		}
		
		speed = negativeIfInverted * move.magnitude();
		
		pivot.set(finalPosition);

		normalizer.add(drive, speed);
	}
	
	public void relativeDrive(double angle, double speed, SpeedControllerNormalizer normalizer){
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
