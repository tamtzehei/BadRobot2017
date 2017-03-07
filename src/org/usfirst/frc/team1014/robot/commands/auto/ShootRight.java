package org.usfirst.frc.team1014.robot.commands.auto;

import org.usfirst.frc.team1014.robot.utils.Vector2d;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShootRight extends CommandGroup{

	public ShootRight(){
		this.addSequential(new AutoDrive(5, new Vector2d(-1, 0)));
		this.addSequential(new AutoRotate(-135));
		this.addSequential(new AutoShoot(5));
	}
}
