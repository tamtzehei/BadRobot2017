package org.usfirst.frc.team1014.robot.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.usfirst.frc.team1014.robot.util.Vector2d;

public class Vector2dTest {
	
	private static final double FP_ERROR_TOLLORANCE = 1E-9;

	@Test
	public void testMagnitude() {
		Vector2d v1 = new Vector2d(5.3, 0);
		assertEquals(5.3, v1.magnitude(), FP_ERROR_TOLLORANCE);
		
		Vector2d v2 = new Vector2d(0, 4.232);
		assertEquals(4.232, v2.magnitude(), FP_ERROR_TOLLORANCE);
		
		Vector2d v3 = new Vector2d(2.6, 2.6);
		assertEquals(2.6 * Math.sqrt(2), v3.magnitude(), FP_ERROR_TOLLORANCE);
	}
	
	@Test
	public void testScale() {
		Vector2d v1 = new Vector2d(2, 7);
		double v1m = v1.magnitude();
		v1 = v1.scale(4);
		assertEquals(8, v1.getX(), FP_ERROR_TOLLORANCE);
		assertEquals(28, v1.getY(), FP_ERROR_TOLLORANCE);
		assertEquals(v1m * 4, v1.magnitude(), FP_ERROR_TOLLORANCE);
		
		Vector2d v2 = new Vector2d(5, 3);
		double v2m = v2.magnitude();
		v2 = v2.scale(-4);
		assertEquals(-20, v2.getX(), FP_ERROR_TOLLORANCE);
		assertEquals(-12, v2.getY(), FP_ERROR_TOLLORANCE);
		assertEquals(v2m * 4, v2.magnitude(), FP_ERROR_TOLLORANCE);
	}
	
	@Test
	public void testNormalize() {
		Vector2d v1 = new Vector2d(0, 5);
		v1 = v1.normalize();
		assertEquals(0, v1.getX(), FP_ERROR_TOLLORANCE);
		assertEquals(1, v1.getY(), FP_ERROR_TOLLORANCE);
		assertEquals(1, v1.magnitude(), FP_ERROR_TOLLORANCE);
		
		Vector2d v2 = new Vector2d(4.4,  0).normalize();
		assertEquals(1, v2.getX(), FP_ERROR_TOLLORANCE);
		assertEquals(0, v2.getY(), FP_ERROR_TOLLORANCE);
		assertEquals(1, v2.magnitude(), FP_ERROR_TOLLORANCE);
		
		Vector2d v3 = new Vector2d(-4.8,  0).normalize();
		assertEquals(-1, v3.getX(), FP_ERROR_TOLLORANCE);
		assertEquals(0, v3.getY(), FP_ERROR_TOLLORANCE);
		assertEquals(1, v3.magnitude(), FP_ERROR_TOLLORANCE);
		
		Vector2d v4 = new Vector2d(3, -3).normalize();
		assertEquals(Math.pow(2, -.5), v4.getX(), FP_ERROR_TOLLORANCE);
		assertEquals(-Math.pow(2, -.5), v4.getY(), FP_ERROR_TOLLORANCE);
		assertEquals(v4.magnitude(), 1, FP_ERROR_TOLLORANCE);
	}
	
	@Test
	public void testRotateRadians() {
		Vector2d v1 = new Vector2d(1, 0);
		double v1m = v1.magnitude();
		v1 = v1.rotateRadians(2 * Math.PI);
		assertEquals(1, v1.getX(), FP_ERROR_TOLLORANCE);
		assertEquals(0, v1.getY(), FP_ERROR_TOLLORANCE);
		assertEquals(v1m, v1.magnitude(), FP_ERROR_TOLLORANCE);
		
		Vector2d v2 = new Vector2d(4, 5);
		double v2m = v2.magnitude();
		v2 = v2.rotateRadians(Math.PI);
		assertEquals(-4, v2.getX(), FP_ERROR_TOLLORANCE);
		assertEquals(-5, v2.getY(), FP_ERROR_TOLLORANCE);
		assertEquals(v2m, v2.magnitude(), FP_ERROR_TOLLORANCE);
		
		Vector2d v3 = new Vector2d(1, 5);
		double v3m = v3.magnitude();
		v3 = v3.rotateRadians(Math.PI / 2);
		assertEquals(5, v3.getX(), FP_ERROR_TOLLORANCE);
		assertEquals(-1, v3.getY(), FP_ERROR_TOLLORANCE);
		assertEquals(v3m, v3.magnitude(), FP_ERROR_TOLLORANCE);
		
		Vector2d v4 = new Vector2d(1, 5);
		double v4m = v3.magnitude();
		v4 = v4.rotateRadians(-Math.PI / 2);
		assertEquals(-5, v4.getX(), FP_ERROR_TOLLORANCE);
		assertEquals(1, v4.getY(), FP_ERROR_TOLLORANCE);
		assertEquals(v4m, v4.magnitude(), FP_ERROR_TOLLORANCE);
	}
	
	@Test
	public void testRotateDegrees() {
		Vector2d v1 = new Vector2d(1, 0);
		double v1m = v1.magnitude();
		v1 = v1.rotateDegrees(360);
		assertEquals(1, v1.getX(), FP_ERROR_TOLLORANCE);
		assertEquals(0, v1.getY(), FP_ERROR_TOLLORANCE);
		assertEquals(v1m, v1.magnitude(), FP_ERROR_TOLLORANCE);
		
		Vector2d v2 = new Vector2d(4, 5);
		double v2m = v2.magnitude();
		v2 = v2.rotateDegrees(180);
		assertEquals(-4, v2.getX(), FP_ERROR_TOLLORANCE);
		assertEquals(-5, v2.getY(), FP_ERROR_TOLLORANCE);
		assertEquals(v2m, v2.magnitude(), FP_ERROR_TOLLORANCE);
		
		Vector2d v3 = new Vector2d(1, 5);
		double v3m = v3.magnitude();
		v3 = v3.rotateDegrees(90);
		assertEquals(5, v3.getX(), FP_ERROR_TOLLORANCE);
		assertEquals(-1, v3.getY(), FP_ERROR_TOLLORANCE);
		assertEquals(v3m, v3.magnitude(), FP_ERROR_TOLLORANCE);
		
		Vector2d v4 = new Vector2d(1, 5);
		double v4m = v4.magnitude();
		v4 = v4.rotateDegrees(-90);
		assertEquals(-5, v4.getX(), FP_ERROR_TOLLORANCE);
		assertEquals(1, v4.getY(), FP_ERROR_TOLLORANCE);
		assertEquals(v4m, v4.magnitude(), FP_ERROR_TOLLORANCE);
		
		Vector2d v5 = new Vector2d(1, 0);
		double v5m = v5.magnitude();
		v5 = v5.rotateDegrees(-45);
		assertEquals(Math.pow(2, -.5), v5.getX(), FP_ERROR_TOLLORANCE);
		assertEquals(Math.pow(2, -.5), v5.getY(), FP_ERROR_TOLLORANCE);
		assertEquals(v5m, v5.magnitude(), FP_ERROR_TOLLORANCE);
		
		Vector2d v6 = new Vector2d(0, 1);
		double v6m = v6.magnitude();
		v6 = v6.rotateDegrees(45);
		assertEquals(Math.pow(2, -.5), v6.getX(), FP_ERROR_TOLLORANCE);
		assertEquals(Math.pow(2, -.5), v6.getY(), FP_ERROR_TOLLORANCE);
		assertEquals(v6m, v6.magnitude(), FP_ERROR_TOLLORANCE);
	}
	
	@Test
	public void testPerpendicularCCW() {
		Vector2d v1 = new Vector2d(6, 0);
		double v1m = v1.magnitude();
		v1 = v1.perpendicularCCW();
		assertEquals(0, v1.getX(), FP_ERROR_TOLLORANCE);
		assertEquals(6, v1.getY(), FP_ERROR_TOLLORANCE);
		assertEquals(v1m, v1.magnitude(), FP_ERROR_TOLLORANCE);
		
		Vector2d v2 = new Vector2d(5, 5);
		double v2m = v2.magnitude();
		v2 = v2.perpendicularCCW();
		assertEquals(-5, v2.getX(), FP_ERROR_TOLLORANCE);
		assertEquals(5, v2.getY(), FP_ERROR_TOLLORANCE);
		assertEquals(v2m, v2.magnitude(), FP_ERROR_TOLLORANCE);
	}
	
	@Test
	public void testPerpendicularCW() {
		Vector2d v1 = new Vector2d(6, 0);
		double v1m = v1.magnitude();
		v1 = v1.perpendicularCW();
		assertEquals(0, v1.getX(), FP_ERROR_TOLLORANCE);
		assertEquals(-6, v1.getY(), FP_ERROR_TOLLORANCE);
		assertEquals(v1m, v1.magnitude(), FP_ERROR_TOLLORANCE);
		
		Vector2d v2 = new Vector2d(-5, 5);
		double v2m = v2.magnitude();
		v2 = v2.perpendicularCW();
		assertEquals(5, v2.getX(), FP_ERROR_TOLLORANCE);
		assertEquals(5, v2.getY(), FP_ERROR_TOLLORANCE);
		assertEquals(v2m, v2.magnitude(), FP_ERROR_TOLLORANCE);
	}

}
