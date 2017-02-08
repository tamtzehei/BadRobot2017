package org.usfirst.frc.team1014.robot.drive;

import java.util.List;

import org.usfirst.frc.team1014.robot.controls.TankController;

import edu.wpi.first.wpilibj.SpeedController;

public class TankDrive {
	List<SpeedController> left;
	List<SpeedController> right;
	TankController controller;

	public TankDrive(List<SpeedController> left, List<SpeedController> right, TankController controller) {
		this.left = left;
		this.right = right;
		this.controller = controller;
	}

	public void drive() {
		double l = controller.getLeft(), r = controller.getRight();
		left.forEach((c) -> c.set(l));
		right.forEach((c) -> c.set(r));
	}
}
