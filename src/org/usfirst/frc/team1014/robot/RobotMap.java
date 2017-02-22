package org.usfirst.frc.team1014.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	/*
	 * PWM
	 */
	public static final int DRIVE_MOTOR_A = 26;
 	public static final int DRIVE_MOTOR_B = 24;
 	public static final int DRIVE_MOTOR_C = 21;
 	public static final int DRIVE_MOTOR_D = 28;
 	public static final int PIVOT_MOTOR_A = 25;
 	public static final int PIVOT_MOTOR_B = 23;
 	public static final int PIVOT_MOTOR_C = 22;
 	public static final int PIVOT_MOTOR_D = 27;
	public static final int SHOOTER_MOTOR = 31;
	public static final int ROTATER_MOTOR = 33;
	//public static final int CLIMBER_MOTOR = ;
	


	/*
	 * DIO
	 */
	public static final int PIVOT_ENCODER_AA = 9;
	public static final int PIVOT_ENCODER_AB = 8;
	public static final int PIVOT_ENCODER_BA = 4;
	public static final int PIVOT_ENCODER_BB = 3;
	public static final int PIVOT_ENCODER_CA = 1;
	public static final int PIVOT_ENCODER_CB = 0;
	public static final int PIVOT_ENCODER_DA = 6;
	public static final int PIVOT_ENCODER_DB = 5;
}
