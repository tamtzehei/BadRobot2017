package org.usfirst.frc.team1014.robot.commands;

import org.usfirst.frc.team1014.robot.OI;
import org.usfirst.frc.team1014.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

public class UseShooter extends Command {

	Shooter shooter;
	public static double speed;

	public UseShooter() {
		requires(shooter.getInstance());
		speed = 1;
	}

	protected void execute() {
		if (OI.xboxController0.getYButton()) {
			shooter.rotateFeeder(speed);
			shooter.shoot(speed);
		}
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
