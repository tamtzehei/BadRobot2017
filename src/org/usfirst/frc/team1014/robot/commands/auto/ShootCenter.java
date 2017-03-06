package org.usfirst.frc.team1014.robot.commands.auto;

import org.usfirst.frc.team1014.robot.util.Vector2d;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShootCenter extends CommandGroup {

	public ShootCenter() {
		this.addSequential(new AutoDrive(3, new Vector2d(-1, 0)));
		this.addSequential(new AutoRotate(-135));
		this.addSequential(new AutoShoot(5));
	}

}
