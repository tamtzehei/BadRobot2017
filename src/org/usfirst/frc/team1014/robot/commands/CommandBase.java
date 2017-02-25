package org.usfirst.frc.team1014.robot.commands;
import org.usfirst.frc.team1014.robot.subsystems.LEDLights;
import org.usfirst.frc.team1014.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

public abstract class CommandBase extends Command
{
	public static LEDLights lights;
	public static Shooter shooter;

	public static void init()
	{
		lights = LEDLights.getInstance();
		shooter = Shooter.getInstance();
	}
	public CommandBase(String name)
	{
		super(name);
	}
	public CommandBase()
	{
		super();
	}

	protected abstract void initialize();

	/**
	 * @return a {@link String} with the name of the class.
	 */
	public abstract String getConsoleIdentity();

	public boolean isFinished;

	/**
	 * Called when class is {@code cancel()} or multiple commands use the same subsystem.
	 */
}
