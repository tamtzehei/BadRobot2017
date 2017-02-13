package org.usfirst.frc.team1014.robot.subsystems;

import org.usfirst.frc.team1014.robot.RobotMap;
import org.usfirst.frc.team1014.robot.util.Vector2d;
import org.usfirst.frc.team1014.robot.utils.SpeedControllerNormalizer;
import org.usfirst.frc.team1014.robot.utils.SwerveWheel;

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
		//12.75, 11
		wheelA = new SwerveWheel(new Vector2d(1 ,1), RobotMap.DRIVE_MOTOR_A, RobotMap.PIVOT_MOTOR_A , 354, 888, 13);
		//wheelB = new SwerveWheel(new Vector2d(-1 ,1), RobotMap.DRIVE_MOTOR_B, RobotMap.PIVOT_MOTOR_B , 171, 867, 13);
		wheelB = new SwerveWheel(new Vector2d(-1 ,1), RobotMap.DRIVE_MOTOR_B, RobotMap.PIVOT_MOTOR_B , 171, 867, 13);
		wheelC = new SwerveWheel(new Vector2d(-1 ,-1), RobotMap.DRIVE_MOTOR_C, RobotMap.PIVOT_MOTOR_C , 824, 882, 13);
		//wheelD = new SwerveWheel(new Vector2d(1 ,-1), RobotMap.DRIVE_MOTOR_D, RobotMap.PIVOT_MOTOR_D, 473, 888, 13);
		wheelD = new SwerveWheel(new Vector2d(1 ,-1), RobotMap.DRIVE_MOTOR_D, RobotMap.PIVOT_MOTOR_D, 473, 888, 13);
		
		mxpPort = new SerialPort(57600, SerialPort.Port.kMXP);
		imu = new IMU(mxpPort, (byte) 127);
		normalizer = new  SpeedControllerNormalizer();
	}
    
	public void drive(double rotation, Vector2d translation)
	{
		/*if(Math.abs(rotation) < .15)
			rotation = 0;*/
		
		/*if(translation.magnitude() < .15)
			translation = new Vector2d(0,0);*/
	                     
		double robotAngle = Math.toRadians(imu.getYaw());
		
		System.out.println("ANGLE: " + robotAngle);
		System.out.println("IN: " + translation);
		
		wheelA.drive(translation, rotation, normalizer);
		wheelB.drive(translation, rotation, normalizer);
		wheelC.drive(translation, rotation, normalizer);
		wheelD.drive(translation, rotation, normalizer);
		
		//normalizer.run();
		//normalizer.clear();
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
