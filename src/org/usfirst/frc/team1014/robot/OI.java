package org.usfirst.frc.team1014.robot;

import edu.wpi.first.wpilibj.XboxController;

/**
This is the Operator Interface class. It stores and initializes the controllers.
 */
public class OI {
	
	public static XboxController xboxController0;
	
	public OI() {
		xboxController0 = new XboxController(0);
	}

}

