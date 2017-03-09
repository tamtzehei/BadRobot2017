package org.usfirst.frc.team1014.robot.commands.auto;

import org.usfirst.frc.team1014.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1014.robot.utils.Vector2d;

import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Command;

public class AutoRotate extends Command {

	double angle, diff, count;

	/**
	 * 
	 * @param angle
	 *            angle from -180 to 180
	 */
	public AutoRotate(double angle) {
		this.angle = angle;
	}

	protected void execute() {
		diff = DriveTrain.getInstance().getYaw() - angle;

		DriveTrain.getInstance().drive(Math.abs(diff) / (3 * diff), new Vector2d(0, 0));	//scale speed to 1/3 and get correct sign using abs
		
		if (Math.abs(diff) < 10) {
			count++;
		} else
			count = 0;
	}

	protected void end() {
		DriveTrain.getInstance().drive(0, new Vector2d(0, 0));
	}

	@Override
	protected boolean isFinished() {
		if (count > 10)
			return true;
		return false;
	}

}
