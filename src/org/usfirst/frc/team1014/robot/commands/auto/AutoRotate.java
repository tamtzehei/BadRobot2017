package org.usfirst.frc.team1014.robot.commands.auto;

import org.usfirst.frc.team1014.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1014.robot.utils.Vector2d;

import edu.wpi.first.wpilibj.command.Command;

public class AutoRotate extends Command {

	double angle, diff;

	/**
	 * 
	 * @param angle angle from -180 to 180 
	 */
	public AutoRotate(double angle) {
		this.angle = angle;
	}

	protected void execute() {
		DriveTrain.getInstance().drive(Math.abs(angle) / angle, new Vector2d(0, 0));
		diff = DriveTrain.getInstance().getYaw() - angle;
	}

	protected void end() {
		DriveTrain.getInstance().drive(0, new Vector2d(0, 0));
	}

	@Override
	protected boolean isFinished() {
		if (diff < 5.0)
			return true;
		return false;
	}

}
