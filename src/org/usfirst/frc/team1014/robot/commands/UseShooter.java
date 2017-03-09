package org.usfirst.frc.team1014.robot.commands;

import org.usfirst.frc.team1014.robot.OI;
import org.usfirst.frc.team1014.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

public class UseShooter extends Command {

	Shooter shooter;

	protected void initialize() {
		shooter = Shooter.getInstance();
	}

	protected void execute() {
		if (Math.abs(OI.xboxController1.getRawAxis(5)) > .15) {
			shooter.shoot(-1);
		}
		shooter.rotateFeeder(-OI.xboxController1.getRawAxis(1));
		
		System.out.println(-OI.xboxController1.getRawAxis(1));

	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}

