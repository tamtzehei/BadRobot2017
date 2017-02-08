package org.usfirst.frc.team1014.robot.util;

public class Vector2d {
	private final double x, y;
	
	public Vector2d(double x, double y) {
		this.x = x;
		this.y = y;
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
	
	public Vector2d normalize() {
		double magnitude = magnitude();
		return new Vector2d(x / magnitude, y / magnitude);
	}
	
	public Vector2d rotateRadians(double theta) {
		theta *= -1; // To make theta counterclockwise for math
		double x = this.x * Math.cos(theta) - this.y * Math.sin(theta);
		double y = this.x * Math.sin(theta) + this.y * Math.cos(theta);
		return new Vector2d(x, y);
	}
	
	public Vector2d rotateDegrees(double theta) {
		return rotateRadians(Math.toRadians(theta));
	}
	
	public Vector2d perpendicularCCW() {
		return new Vector2d(-y, x);
	}
	
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
