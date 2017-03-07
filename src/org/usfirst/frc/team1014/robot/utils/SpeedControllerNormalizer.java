package org.usfirst.frc.team1014.robot.utils;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.SpeedController;

/**
 * This class scales the speeds given to motor controllers so that you are never
 * exceeding 1.0. Instead of using the set method you add the controller to a
 * normalizer and set the speed there. At the end you call run and each motor is
 * set at its ideal speed. It will only decrease the speeds it it is required.
 */
public class SpeedControllerNormalizer {

	double maxSpeed = 0;

	private class UnsetSpeedController {
		private UnsetSpeedController(SpeedController speedController, double speed) {
			controller = speedController;
			this.speed = speed;
		}

		SpeedController controller;
		double speed;
	}

	private List<UnsetSpeedController> controllers = new ArrayList<>();

	/**
	 * Add a speed controller to be set with the raw speed.
	 */
	public void add(SpeedController controller, double speed) {
		maxSpeed = Math.max(Math.abs(speed), maxSpeed);
		controllers.add(new UnsetSpeedController(controller, speed));
	}

	/**
	 * Scale the inputs and set the speed controllers.
	 */
	public void run() {
		if (maxSpeed <= 1)
			controllers.forEach((c) -> c.controller.set(c.speed));
		else
			controllers.forEach((c) -> c.controller.set(c.speed / maxSpeed));
	}

	/**
	 * Clears the controllers and resets the max speed.
	 */
	public void clear() {
		controllers.clear();
		maxSpeed = 0;
	}
}
