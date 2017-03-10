package org.usfirst.frc.team1014.robot.commands.auto;

import org.usfirst.frc.team1014.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1014.robot.utils.Vector2d;

import edu.wpi.first.wpilibj.command.Command;

public class AutoGearDrive extends Command {

	DriveTrain driveTrain;
	int count;

	protected void initialize() {
		driveTrain = DriveTrain.getInstance();
		count = 0;
	}

	protected void execute() {
		driveTrain.drive(0, new Vector2d(0, .5));
		System.out.println(driveTrain.getCurrent());
		if (driveTrain.getCurrent() > 2)
			count++;
		else
			count = 0;
	}

	protected void end() {
		driveTrain.drive(0, new Vector2d(0, 0));
	}

	@Override
	protected boolean isFinished() {
		if (count > 10)
			return true;
		return false;
	}

}
