package org.usfirst.frc.team1014.robot.controls;

import org.usfirst.frc.team1014.robot.utils.Vector2d;

public interface SwerveController {
	public Vector2d getTranslation();

	public double getRotation();

	public boolean isFieldCentric();

	public boolean resetFieldCentric();
}
