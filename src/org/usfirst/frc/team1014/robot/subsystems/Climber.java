package org.usfirst.frc.team1014.robot.subsystems;

import org.usfirst.frc.team1014.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem {
	
	public static Climber climber;
	public static CANTalon climbTalon;
	
	public static Climber getInstance(){
		if (climber == null)
			climber = new Climber();
		return climber;
	}

	public Climber() {
		super();
		
		climbTalon = new CANTalon(RobotMap.CLIMBER_MOTOR);
	}
	
	public void climb(double speed){
		climbTalon.set(speed);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

}
