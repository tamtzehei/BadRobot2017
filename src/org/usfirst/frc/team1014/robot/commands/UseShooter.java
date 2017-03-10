package org.usfirst.frc.team1014.robot.commands;

import org.usfirst.frc.team1014.robot.OI;
import org.usfirst.frc.team1014.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

public class UseShooter extends Command {

	Shooter shooter;
	boolean XButton, isShooting;

	protected void initialize() {
		shooter = Shooter.getInstance();
		XButton = false;
		isShooting = false;
	}

	protected void execute() {
		if(OI.xboxController1.getXButton())
			XButton = true;
		if(!OI.xboxController1.getXButton() && XButton){
			XButton = false;
			isShooting = !isShooting;
		}
		if(isShooting){
			shooter.shoot(-1);
			shooter.rotateFeeder(1);
		}
		
		if(Math.abs(OI.xboxController1.getY(Hand.kRight)) > .15) {
			shooter.shoot(-OI.xboxController1.getY(Hand.kRight));
		}
		if(Math.abs(OI.xboxController1.getY(Hand.kRight)) > .15){
			shooter.rotateFeeder(OI.xboxController1.getY(Hand.kLeft));
		}
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
