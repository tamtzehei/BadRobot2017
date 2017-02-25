package org.usfirst.frc.team1014.robot.commands.auto;

import edu.wpi.first.wpilibj.DigitalInput;

public class AutoManager {
	
	private static DigitalInput S1 = new DigitalInput(1);  //Temporary Input
	private static DigitalInput S2 = new DigitalInput(1);
	private static DigitalInput S3 = new DigitalInput(1);
	private static DigitalInput S4 = new DigitalInput(1);

	public int getSwitchSum()
	{
		int sum = 0;
		if(!S1.get())
			sum += 1;
		if(!S2.get())
			sum += 2;
		if(!S3.get())
			sum += 4;
		if(!S4.get())
			sum += 8;
		
		return sum;
	}
}
