package org.usfirst.frc.team1014.robot.utils;

/**
 * This class represents a 2d point with two doubles.
*/
public class Vector2d {

	/*
	 * NOTE: Each Vector2d is immutable. Each method will return a new
	 * Vector2d and not change the value of the current vector.
	 */

	private final double x, y;
	
	public Vector2d(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Return a new vector that is the product of this vector and the
	 * other vector.
	 */
	public Vector2d add(Vector2d other) {
		return new Vector2d(this.x + other.getX(), this.y + other.getY());
	}

	/**
	 * Returns distance of a point at the end of the vector to the origin.
	 * @return the magnitude of the vector
	 */
	public double magnitude() {
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
	
	/**
	 * @param scale
	 * @return a new {@code Vector2d} scaled by the {@code scale}
	 */
	public Vector2d scale(double scale) {
		return new Vector2d(scale * x, scale * y);
	}

	/**
	 * Return a new Vector2d in the same direction but with a length of 1.
	 */
	public Vector2d normalize() {
		double magnitude = magnitude();
		return new Vector2d(x / magnitude, y / magnitude);
	}

	/**
	 * Return a new Vector2d that is rotated by @param{theta} radians
	 * counterclockwise.
	 */
	public Vector2d rotateRadians(double theta) {
		theta *= -1; // To make theta counterclockwise for math
		double x = this.x * Math.cos(theta) - this.y * Math.sin(theta);
		double y = this.x * Math.sin(theta) + this.y * Math.cos(theta);
		return new Vector2d(x, y);
	}

	/**
	 * Return a new Vector2d that is rotated by @param{theta} degrees
	 * counterclockwise.
	 */
	public Vector2d rotateDegrees(double theta) {
		return rotateRadians(Math.toRadians(theta));
	}

	/**
	 * Return a new Vector2d that is perpenducular to this vector in the
	 * counterclockwise direction.
	 */
	public Vector2d perpendicularCCW() {
		return new Vector2d(-y, x);
	}

	/**
	 * Return a new Vector2d that is perpenducular to this vector in the
	 * clockwise direction.
	 */
	public Vector2d perpendicularCW() {
		return new Vector2d(y, -x);
	}

	@Override
	public Object clone() {
		return new Vector2d(x, y);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector2d other = (Vector2d) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}

