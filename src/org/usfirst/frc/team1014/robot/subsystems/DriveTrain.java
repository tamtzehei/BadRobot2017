package org.usfirst.frc.team1014.robot.subsystems;

import org.usfirst.frc.team1014.robot.RobotMap;
import org.usfirst.frc.team1014.robot.subsystems.IMU;
import org.usfirst.frc.team1014.robot.utils.SpeedControllerNormalizer;
import org.usfirst.frc.team1014.robot.utils.SwerveWheel;
import org.usfirst.frc.team1014.robot.utils.Vector2d;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem{
	
	private static final double L = 1, W = 1;
	private static DriveTrain instance;
	SpeedControllerNormalizer normalizer;
	SwerveWheel wheelA, wheelB, wheelC, wheelD;
	IMU imu;
	SerialPort mxpPort;
	
	
	public static DriveTrain getInstance()
	{
		if(instance == null)
			instance = new DriveTrain();
		return instance;
	}
	
	
	private DriveTrain()
	{
		//Temporary values Need encoderMin, Max, offset
		wheelA = new SwerveWheel(new Vector2d(1,1), RobotMap.DRIVE_MOTOR_A, RobotMap.PIVOT_MOTOR_A);
		wheelB = new SwerveWheel(new Vector2d(-1,1), RobotMap.DRIVE_MOTOR_B, RobotMap.PIVOT_MOTOR_B);
		wheelC = new SwerveWheel(new Vector2d(-1,-1), RobotMap.DRIVE_MOTOR_C, RobotMap.PIVOT_MOTOR_C);
		wheelD = new SwerveWheel(new Vector2d(1,-1), RobotMap.DRIVE_MOTOR_D, RobotMap.PIVOT_MOTOR_D);
		
		mxpPort = new SerialPort(57600, SerialPort.Port.kMXP);
		imu = new IMU(mxpPort, (byte) 127);
		normalizer = new  SpeedControllerNormalizer();
	}
    
	public void drive(double rotation, Vector2d translation)
	{
	
		double robotAngle = Math.toRadians(imu.getYaw());
		
		System.out.println("ANGLE: " + robotAngle);
		System.out.println("IN: " + translation);
		
		wheelA.drive(translation, normalizer);
		wheelB.drive(translation, normalizer);
		wheelC.drive(translation, normalizer);
		wheelD.drive(translation, normalizer);
		
		normalizer.run();
		normalizer.clear();
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
