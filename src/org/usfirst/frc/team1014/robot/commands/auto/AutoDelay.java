package org.usfirst.frc.team1014.robot.commands.auto;

import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Command;

public class AutoDelay extends Command{
	
	double time, startTime, passedTime;

	public AutoDelay(double time){
		this.time = time * 1000000;
		startTime = Utility.getFPGATime();
	}
	
	protected void execute(){
		passedTime = Utility.getFPGATime() - startTime;
		System.out.println(passedTime);
	}
	
	@Override
	protected boolean isFinished() {
		if(passedTime > time)
			return true;
		return false;
	}

}
