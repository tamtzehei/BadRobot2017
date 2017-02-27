package org.usfirst.frc.team1014.robot.commands.auto;

import org.usfirst.frc.team1014.robot.util.Vector2d;

import edu.wpi.first.wpilibj.command.Command;

public class AutoDrive extends Command {

	double time, rotation;
	Vector2d translation;

	public AutoDrive(double time, Vector2d translation) {
		this.time = time;
		this.translation = translation;
		
	}

	public void execute() {
		
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
