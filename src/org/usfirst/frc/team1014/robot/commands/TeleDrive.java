package org.usfirst.frc.team1014.robot.commands;

import org.usfirst.frc.team1014.robot.OI;
import org.usfirst.frc.team1014.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1014.robot.util.Vector2d;

import edu.wpi.first.wpilibj.command.Command;

public class TeleDrive extends Command {

	private static boolean isSwerve;
	private static boolean isAbsolute;
	private static double badEncoderPosition;
	private static boolean Xbutton, Abutton;

	protected void initialize() {
		isSwerve = true;
		isAbsolute = true;
		Xbutton = false;
		Abutton = false;
	}

	public void execute() {
		double rotation = OI.xboxController0.getRawAxis(4);
		if (Math.abs(rotation) < .15)
			rotation = 0;

		if (OI.xboxController0.getRawButton(3)) {
			Xbutton = true;
		}
		else if(!OI.xboxController0.getRawButton(3) && Xbutton){
			Xbutton = false;
			isSwerve = !isSwerve;
			if (isSwerve == false)
				badEncoderPosition = DriveTrain.getInstance().getAngleOfBrokenWheel();
		}

		if (OI.xboxController0.getRawButton(1)) {
			Abutton = true;
		}
		else if(!OI.xboxController0.getRawButton(1) && Abutton){
			Abutton = false;
			isAbsolute = !isAbsolute;
			if (isAbsolute == false)
				DriveTrain.getInstance().setRelative();
		}

		if (isSwerve && isAbsolute) {
			DriveTrain.getInstance().drive(rotation,
					new Vector2d(OI.xboxController0.getRawAxis(0), -OI.xboxController0.getRawAxis(1)));
		}

		if (isSwerve && !isAbsolute) {
			DriveTrain.getInstance().relativeDrive(rotation,
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
