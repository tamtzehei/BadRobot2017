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
		if (OI.xboxController0.getRawAxis(3) > 0)
			climber.climb(OI.xboxController0.getRawAxis(3));
		else if (OI.xboxController0.getRawAxis(2) > 0)
			climber.climb(-OI.xboxController0.getRawAxis(2));
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
