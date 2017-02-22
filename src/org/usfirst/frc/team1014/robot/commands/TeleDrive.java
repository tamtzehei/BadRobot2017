package org.usfirst.frc.team1014.robot.commands;

import org.usfirst.frc.team1014.robot.OI;
import org.usfirst.frc.team1014.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1014.robot.util.Vector2d;

import edu.wpi.first.wpilibj.command.Command;

public class TeleDrive extends Command {

	private static boolean isSwerve;
	private static double badEncoderPosition;

	protected void initialize() {
		isSwerve = true;
	}

	public void execute() {
		double rotation = OI.xboxController0.getRawAxis(4);
		if (Math.abs(rotation) < .15)
			rotation = 0;

		if (OI.xboxController0.getXButton()) {
			isSwerve = !isSwerve;
			if (!isSwerve)
				badEncoderPosition = DriveTrain.getInstance().getAngleOfBrokenWheel();
		}

		if (isSwerve) {
			DriveTrain.getInstance().drive(rotation,
					new Vector2d(OI.xboxController0.getRawAxis(0), -OI.xboxController0.getRawAxis(1)));
		}

		if (!isSwerve) {
			DriveTrain.getInstance().tankDrive(-OI.xboxController0.getRawAxis(5), -OI.xboxController0.getRawAxis(1),
					badEncoderPosition);
		}

	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}

