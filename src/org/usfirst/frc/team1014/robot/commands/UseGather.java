package org.usfirst.frc.team1014.robot.commands;

import org.usfirst.frc.team1014.robot.OI;
import org.usfirst.frc.team1014.robot.subsystems.Gather;

import edu.wpi.first.wpilibj.command.Subsystem;


public class UseGather extends CommandBase
{
	public static double speed;

	public UseGather()
	{
		requires((Subsystem) gatherer);
	}

	public void execute()
	{
		if (OI.xboxController0.getXButton())
		{
			gatherer.gather(speed);
		}
	}

	public static void setSpeed()
	{
		if(OI.xboxController0.getBumper() == true)
		{
			speed = -5; //temporary
		}
		else if(OI.xboxController0.getRawAxis(2) > 0) // temporary
		{
			speed = OI.xboxController0.getRawAxis(2);
		}
		else if(OI.xboxController0.getRawAxis(2) == 0)
		{
			speed = 0;
		}
	}

	protected void end()
	{
		gatherer.gather(0.0);
	}

	protected void initialize() {
		gatherer.gather(0.0);
	}

	public String getConsoleIdentity() {
		// TODO Auto-generated method stub
		return "USE_GATHER";
	}

	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
