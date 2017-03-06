package org.usfirst.frc.team1014.robot.commands.auto;

import org.usfirst.frc.team1014.robot.util.Vector2d;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GearLeft extends CommandGroup {
	
	public GearLeft(){
		this.addSequential(new AutoDrive(1, new Vector2d(0, 1)));
		this.addSequential(new AutoRotate(45));
		this.addSequential(new AutoDrive(1, new Vector2d(0, 1)));
		this.addSequential(new AutoDelay(3));
	}
}
