package org.usfirst.frc.team1014.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalOutput;

public class LEDLights
{
	private static LEDLights instance;
	private DigitalOutput bit1;
	private final int channel = 4;

	public LEDLights()
	{
		bit1 = new DigitalOutput(channel); //temporary
	}
	public static LEDLights getInstance()
	{
		if(instance == null)
			instance = new LEDLights();
		return instance;
	}

	public enum LEDState
	{
		kDEFAULT, kRED, kBLUE, k1014COLOR, kRAINBOW, kLOW_BATTERY, kJOIN_BLUE, kJOIN_RED, kSHOOT, kAMERICA, kGEAR, kCLIMB, kBLUEDRIVE, kREDDRIVE;
	}

	public void setLights(LEDState state)
	{
		switch(state)
		{
			case kDEFAULT:
				bit1.pulse(0.00f); //Meeting two colors red and blue when turned on
				break;
			case kRED:
				bit1.pulse(0.00002f); //Red
				break;
			case kBLUE:
				bit1.pulse(0.00004f); //Blue
				break;
			case k1014COLOR:
				bit1.pulse(0.00006f); //Yellow
				break;
			case kRAINBOW:
				bit1.pulse(0.000008f); //Rainbow scroll
				break;
			case kLOW_BATTERY:
				bit1.pulse(0.00010f); //Blinks Red
				break;
			case kJOIN_BLUE:
				bit1.pulse(0.00012f); //meets colors finishes blue
				break;
			case kJOIN_RED:
				bit1.pulse(0.00014f); //meets colors finishes red
				break;
			case kSHOOT:
				bit1.pulse(0.00016f); //as it shoots sets colors
				break;
			case kAMERICA:
				bit1.pulse(0.00018f); // as it gathers balls
				break;
			case kGEAR:
				bit1.pulse(0.00020f); // as it places a gear blue and yellow
				break;
			case kCLIMB:
				bit1.pulse(0.00022f); // as it climbs the rope
				break;
			case kBLUEDRIVE:
				bit1.pulse(0.00024f); // as it drives for blue team
				break;
			case kREDDRIVE:
				bit1.pulse(0.00026f); //as it drives for red team
				break;
		}
	}
	public void pulse()
	{
		bit1.pulse(4);
	}
}
