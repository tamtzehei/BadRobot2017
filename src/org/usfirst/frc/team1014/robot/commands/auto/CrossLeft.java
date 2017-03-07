package org.usfirst.frc.team1014.robot.commands.auto;

import org.usfirst.frc.team1014.robot.utils.Vector2d;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossLeft extends CommandGroup{
	
	public CrossLeft(){
		this.addSequential(new AutoDrive(1, new Vector2d(-1, 1)));
		this.addSequential(new AutoDrive(3, new Vector2d(0, 1)));
	}
}
