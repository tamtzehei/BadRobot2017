package org.usfirst.frc.team1014.robot.utils;

public class SmartDashboard {
	
	private static SmartDashboard smartDashboard;

	public static SmartDashboard getInstance()
	{
		if(smartDashboard == null)
		{
			smartDashboard = new SmartDashboard();
		}
		return smartDashboard;
	}
	
	
	
}
