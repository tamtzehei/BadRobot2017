package org.usfirst.frc.team1014.robot.commands.auto;

import org.usfirst.frc.team1014.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Command;

public class AutoShoot extends Command {

	double time, passedTime, startTime;

	/**
	 * @param time - time in seconds it should shoot 
	 */
	public AutoShoot(double time) {
		this.time = time * 1000000;
		startTime = Utility.getFPGATime();
	}

	protected void execute() {
		Shooter.getInstance().shoot(-1);
		Shooter.getInstance().rotateFeeder(1);
		passedTime = Utility.getFPGATime() - startTime;
	}
	
	protected void end(){
		Shooter.getInstance().shoot(0);
		Shooter.getInstance().rotateFeeder(0);
	}

	@Override
	protected boolean isFinished() {
		if(passedTime > time)
			return true;
		return false;
	}

}
