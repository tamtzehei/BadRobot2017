package org.usfirst.frc.team1014.robot.commands;

import org.usfirst.frc.team1014.robot.OI;
import org.usfirst.frc.team1014.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1014.robot.util.Vector2d;

import edu.wpi.first.wpilibj.command.Command;

public class TeleDrive extends Command {
	
	protected void initialize()
	{
		//requires(DriveTrain.getInstance());
	}

	public void execute() {
		DriveTrain.getInstance().drive(OI.xboxController0.getRawAxis(4),
				new Vector2d(OI.xboxController0.getRawAxis(0),  -OI.xboxController0.getRawAxis(1)));

	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
