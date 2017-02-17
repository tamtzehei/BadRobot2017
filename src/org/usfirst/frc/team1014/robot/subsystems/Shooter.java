package org.usfirst.frc.team1014.robot.subsystems;

import com.ctre.CANTalon;
import org.usfirst.frc.team1014.robot.controls.ControlsManager;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {

	public static Shooter instance;
	public static CANTalon launcher, spinner;

	public static Shooter getInstance() {
		if (instance == null)
			instance = new Shooter();
		return instance;
	}

	protected void initialize()
	{
		launcher = new CANTalon(ControlsManager.LAUNCHER);
		spinner = new CANTalon(ControlsManager.SPINNER);
	}
	
	private Shooter() {
		super();
	}
	
	/**
	 * Sets the speed of the shooters. Automatically inverts the proper motor to keep them moving in
	 * the same direction.
	 * 
	 * @param speed
	 *            to set the shooter to
	 */
	public void shoot(double speed)
	{
		launcher.set(speed);

		// set lights to gather or shoot mode
		/*
		if(CommandBase.lights != null)
		{
			if(speed > 0)
				CommandBase.lights.setLights(LEDState.kSHOOT);
			else
				CommandBase.lights.setLights(DriverStation.getInstance().getAlliance() == DriverStation.Alliance.Blue ? LEDState.kBLUE : LEDState.kRED);
		}*/
	}

	public void rotateSpinner(double speed)
	{
		spinner.set(speed);
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}
}
