package org.usfirst.frc.team1014.robot.commands.auto;

import org.usfirst.frc.team1014.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1014.robot.utils.Vector2d;

import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Command;

public class AutoDrive extends Command {

	double time, startTime, passedTime;
	Vector2d translation;

	/**
	 * 
	 * @param time
	 *            - time in seconds the robot should drive
	 * @param translation
	 *            - vector robot should move in
	 */
	public AutoDrive(double time, Vector2d translation) {
		this.time = time * 1000000;
		this.translation = new Vector2d(translation.getX(), translation.getY());
		startTime = Utility.getFPGATime();
	}

	protected void execute() {
		DriveTrain.getInstance().drive(0, translation);
		passedTime = Utility.getFPGATime() - startTime;
	}

	protected void end() {
		DriveTrain.getInstance().drive(0, new Vector2d(0, 0));
	}

	@Override
	protected boolean isFinished() {
		if (passedTime > time)
			return true;
		return false;
	}

}
