package org.usfirst.frc.team1014.robot.controls;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;

public class TankOnXbox implements TankController {

	XboxController xboxController;

	public TankOnXbox(XboxController xboxController) {
		this.xboxController = xboxController;
	}

	@Override
	public double getLeft() {
		return -xboxController.getY(Hand.kLeft);
	}

	@Override
	public double getRight() {
		return -xboxController.getY(Hand.kRight);
	}

}
