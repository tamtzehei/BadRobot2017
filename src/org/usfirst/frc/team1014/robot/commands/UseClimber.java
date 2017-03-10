package org.usfirst.frc.team1014.robot.commands;

import org.usfirst.frc.team1014.robot.OI;
import org.usfirst.frc.team1014.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;

public class UseClimber extends Command {

	Climber climber;

	protected void initialize() {
		climber = Climber.getInstance();
	}

	protected void execute() {
		climber.climb(OI.xboxController1.getRawAxis(5));
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
