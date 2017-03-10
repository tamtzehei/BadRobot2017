package org.usfirst.frc.team1014.robot.commands;

import org.usfirst.frc.team1014.robot.OI;
import org.usfirst.frc.team1014.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

public class UseClimber extends Command {

	Climber climber;
	boolean BButton, isClimbing;

	protected void initialize() {
		climber = Climber.getInstance();
		BButton = false;
		isClimbing = false;
	}

	protected void execute() {
		if (OI.xboxController1.getBButton()){
			/*BButton = true;
		if (!OI.xboxController1.getBButton() && BButton) {
			BButton = false;
			isClimbing = !isClimbing;
		}
		if (isClimbing) {*/
			climber.climb(-1);
		}
		else
			climber.climb(0);

		if (OI.xboxController1.getTriggerAxis(Hand.kRight) > .15 || OI.xboxController1.getTriggerAxis(Hand.kLeft) > .15)
			climber.climb(
					OI.xboxController1.getTriggerAxis(Hand.kLeft) - OI.xboxController1.getTriggerAxis(Hand.kRight));
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
