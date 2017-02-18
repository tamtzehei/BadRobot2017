package org.usfirst.frc.team1014.robot.utils;

import edu.wpi.first.wpilibj.PWM;

public class BadTouchlessEncoder extends PWM{
	
	public BadTouchlessEncoder(int channel) {
		super(channel);
		// TODO Auto-generated constructor stub
	}

    public int getValue(BadTouchlessEncoder encoder)
    {
    	return encoder.getRaw();
    }
    
    public void reset(BadTouchlessEncoder encoder)
    {
    	encoder.free();
    }
}
