package org.usfirst.frc.team1014.robot.subsystems;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {

	public static Shooter shooter;
	public static CANTalon shoot, rotater;

	public Shooter getInstance() {
		if (shooter == null)
			shooter = new Shooter();
		return shooter;
	}

	private Shooter() {
		super();

	}

	public void shoot(double speed) {
		shoot.set(speed);
	}

	public void rotateFeeder(double speed) {
		rotater.set(speed);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

}
