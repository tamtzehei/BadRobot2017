package org.usfirst.frc.team1014.robot.commands;

import org.usfirst.frc.team1014.robot.OI;
import org.usfirst.frc.team1014.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Subsystem;

public class UseShooter extends CommandBase {

	public static double speed;
	public static double spinnerSpeed;

	public UseShooter()
	{
		requires((Subsystem) shooter);
	}
	
	/*
	public UseShooter() {
		requires(shooter.getInstance());
		speed = 1;
	}*/
	
	protected void initialize() {
		shooter.shoot(0.0);
		shooter.rotateSpinner(0.0);
	}

	protected void execute() {
		if (OI.xboxController0.getYButton()) 
		{
			shooter.shoot(speed);
		}
		shooter.rotateSpinner(spinnerSpeed);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	protected void end()
	{
		shooter.shoot(0.0);
	}
	
	
	public String getConsoleIdentity() {
		// TODO Auto-generated method stub
		return "Use_Shooter";
	}

}
