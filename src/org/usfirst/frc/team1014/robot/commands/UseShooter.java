package org.usfirst.frc.team1014.robot.commands;

import org.usfirst.frc.team1014.robot.OI;
import org.usfirst.frc.team1014.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

public class UseShooter extends Command {

	Shooter shooter;

	protected void initialize() {
		shooter = new Shooter();
	}

	protected void execute() {
		shooter.shoot(-OI.xboxController1.getRawAxis(5));
		shooter.rotateFeeder(-OI.xboxController1.getRawAxis(1));
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
