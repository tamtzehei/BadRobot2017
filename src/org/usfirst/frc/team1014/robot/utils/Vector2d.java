package org.usfirst.frc.team1014.robot.utils;

public class Vector2d {
	final double x, y;

	public Vector2d(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public Vector2d add(Vector2d vector) {
		Vector2d newVector = new Vector2d(x + vector.getX(), y + vector.getY());
		return newVector;
	}

	public Vector2d rotateRadians(double radians) {
		double magnitude = getMagnitude();
		double currentRadians = 0;
		if (y == 0 && x < 0) {
			currentRadians = Math.PI;
		}
		else if (x == 0) {
			if (y > 0)
				currentRadians = Math.PI / 2;
			else
				currentRadians = -Math.PI / 2;
		}
		else
			currentRadians = Math.atan(y / x);
		currentRadians += radians;
		Vector2d newVector = new Vector2d(Math.cos(currentRadians) * magnitude, Math.sin(currentRadians) * magnitude);
		return newVector;
	}

	public Vector2d rotateRotations(double rotations) {
		double magnitude = getMagnitude();
		double currentRadians = 0;
		if (y == 0 && x < 0) {
			currentRadians = Math.PI;
		}
		else if (x == 0) {
			if (y > 0)
				currentRadians = Math.PI / 2;
			else
				currentRadians = -Math.PI / 2;
		}
		else
			currentRadians = Math.atan(y / x);
		currentRadians += rotations * 2 * Math.PI;
		Vector2d newVector = new Vector2d(Math.cos(currentRadians) * magnitude, Math.sin(currentRadians) * magnitude);
		return newVector;
	}

	public Vector2d normalize() {
		double magnitude = getMagnitude();
		if (magnitude != 0) {
			Vector2d newVector = new Vector2d(x / magnitude, y / magnitude);
			return newVector;
		}
		return null;
	}

	public double getMagnitude() {
		return Math.sqrt(Math.pow(this.getX(), 2) + Math.pow(this.getY(), 2));
	}

	public Vector2d scale(double scaleFactor) {
		Vector2d newVector = new Vector2d(x * scaleFactor, y * scaleFactor);
		return newVector;
	}

	public void perpendicularCW() {
		rotateRadians(-Math.PI / 2);
	}

	public void perpendicularCCW() {
		rotateRadians(Math.PI / 2);
	}

	public double getAngleRadians() {
		return Math.atan(y / x);
	}

	public double getAngleRotations() {
		return Math.atan(y / x) / (Math.PI * 2);
	}

}
