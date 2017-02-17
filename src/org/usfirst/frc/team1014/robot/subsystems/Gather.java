package org.usfirst.frc.team1014.robot.subsystems;

import com.ctre.CANTalon;

import org.usfirst.frc.team1014.robot.controls.ControlsManager;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Gather extends Subsystem
{
	public static Gather instance;
	public static CANTalon gatherMotor;

	private Gather()
	{
		super();
	}
	
	public static Gather getInstance()
	{
		if (instance == null)
			instance = new Gather();
		return instance;
	}
	
	protected void initialize()
	{
		gatherMotor = new CANTalon(ControlsManager.GATHERMOTOR);
	}

	public void gather(double speed)
	{
		gatherMotor.set(speed);
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
