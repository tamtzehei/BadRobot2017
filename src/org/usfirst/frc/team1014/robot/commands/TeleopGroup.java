package org.usfirst.frc.team1014.robot.commands;

import org.usfirst.frc.team1014.robot.OI;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TeleopGroup extends CommandGroup {
	
	public TeleopGroup() {
		this.addParallel(new TeleDrive());
		this.addParallel(new UseShooter());
		this.addParallel(new UseClimber());
	}

}

