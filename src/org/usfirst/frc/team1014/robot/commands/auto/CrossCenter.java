package org.usfirst.frc.team1014.robot.commands.auto;

import org.usfirst.frc.team1014.robot.util.Vector2d;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossCenter extends CommandGroup{
	
	public CrossCenter(){
		this.addSequential(new AutoDrive(3, new Vector2d(-1, 1)));
		this.addSequential(new AutoDrive(3, new Vector2d(0, 1)));
	}

}
