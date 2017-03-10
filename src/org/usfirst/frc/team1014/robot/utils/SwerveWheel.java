package org.usfirst.frc.team1014.robot.utils;

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
	String id, tankId;

	public SwerveWheel(String id, String tankId, Vector2d location, int driveMotorPin, int pivotMotorPin, double offset,
			double encoderMax, double encoderMin, double encoderCPR) {

		this.id = id;
		this.tankId = tankId;
		this.encoderCPR = encoderCPR;
		this.offset = offset;
		this.encoderMax = encoderMax;
		this.encoderMin = encoderMin;
		range = encoderMax - encoderMin;

		perpendicular = location;
		perpendicular = perpendicular.normalize();

		drive = new CANTalon(driveMotorPin);
		pivot = new CANTalon(pivotMotorPin);

		pivot.changeControlMode(TalonControlMode.Position);
		pivot.setFeedbackDevice(FeedbackDevice.AnalogEncoder);
		pivot.setPID(4, 0, 0);
		pivot.enableControl();

	}

	public void drive(Vector2d translation, double rotation, SpeedControllerNormalizer normalizer) {

		double speed = 0.0;
		double negativeIfInverted = 1.0;

		Vector2d move = translation.add(perpendicular.scale(rotation));

		double currentPosition = pivot.getPosition();
		double rawCurrent = pivot.getAnalogInRaw();
		
		
		if(id.equals("D")){
			System.out.println(id + ": " + pivot.getAnalogInRaw());
		}else
			System.out.print(id + ": " + pivot.getAnalogInRaw() + "  ");
		
		/*if(id.equals("D")){
			System.out.println(id + ": " + drive.getOutputCurrent());
		}else
			System.out.print(id + ": " + drive.getOutputCurrent() + "  ");*/


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
	
	public void print(){
		if(id.equals("D")){
			System.out.println(id + ": " + pivot.getAnalogInRaw());
		}else
			System.out.print(id + ": " + pivot.getAnalogInRaw() + "  ");
	}
	
	public double getCurrent(){
		return drive.getOutputCurrent();
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

	public void relativeDrive(Vector2d translation, double rotation, SpeedControllerNormalizer normalizer) {

		move = translation.add(perpendicular.scale(rotation));
		double angle = Math.atan2(move.getY(), move.getX());
		double current = pivot.getPosition();

		angle /= (2.0 * Math.PI);

		angle -= current;

		pivot.set(current + angle);

		double speed = move.magnitude();

		if (angle % 1 > .25 && angle % 1 < .75)
			speed = -speed;

		normalizer.add(drive, speed);
	}

	public void setRelative(double encoderCPR) {

		pivot.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		pivot.configEncoderCodesPerRev((int) encoderCPR);
	}

	public void center() {
		pivot.setPosition(0);
	}

	public double getAngle() {
		return (pivot.getPosition() / encoderCPR);
	}

	public double getEncoderPosition() {
		return pivot.getPosition();
	}

	public void tankDrive(double rightInput, double leftInput, double encoderPosition) {

		pivot.set(encoderPosition);

		if (tankId.equals("right")) {
			drive.set(rightInput);
		}
		if (tankId.equals("left")) {
			drive.set(leftInput);
		}

	}
}
