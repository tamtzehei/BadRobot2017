package org.usfirst.frc.team1014.robot.commands;

import org.usfirst.frc.team1014.robot.OI;
import org.usfirst.frc.team1014.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class TankDrive extends Command {

	public void initialize() {
		
	}

	protected void execute() {
		DriveTrain.getInstance().tankDrive(-OI.xboxController0.getRawAxis(5), -OI.xboxController0.getRawAxis(1), 0);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
