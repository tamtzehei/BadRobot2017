package org.usfirst.frc.team1014.robot;

import edu.wpi.first.wpilibj.XboxController;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	public static XboxController xboxController0;
	public static XboxController xboxController1;
	
	public OI() {
		xboxController0 = new XboxController(0);
		xboxController1 = new XboxController(1);
	}

}

