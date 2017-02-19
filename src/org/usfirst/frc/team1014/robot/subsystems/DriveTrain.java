package org.usfirst.frc.team1014.robot.subsystems;

import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc.team1014.robot.RobotMap;
import org.usfirst.frc.team1014.robot.util.Vector2d;
import org.usfirst.frc.team1014.robot.utils.SpeedControllerNormalizer;
import org.usfirst.frc.team1014.robot.utils.SwerveWheel;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {
	private static DriveTrain instance;
	SpeedControllerNormalizer normalizer;
	List<SwerveWheel> swerveWheels;
	SerialPort mxpPort;
	IMU imu;
	private static final double L = 19.0;
	private static final double W = 27.0;
	private static final double ENCODER_CPR = 414.1666d;

	public static DriveTrain getInstance() {
		if (instance == null)
			instance = new DriveTrain();
		return instance;
	}

	private DriveTrain() {
		swerveWheels = new ArrayList<SwerveWheel>() {
			{
				// Relative Encoder values are not correct
				add(new SwerveWheel(new Vector2d(-L/2, W/2), RobotMap.DRIVE_MOTOR_A, RobotMap.PIVOT_MOTOR_A, 288, 862,
						11, RobotMap.PIVOT_ENCODER_AA, RobotMap.PIVOT_ENCODER_AB, ENCODER_CPR)); // A
				add(new SwerveWheel(new Vector2d(-L/2, -W/2), RobotMap.DRIVE_MOTOR_B, RobotMap.PIVOT_MOTOR_B, 700, 863,
						11, RobotMap.PIVOT_ENCODER_BA, RobotMap.PIVOT_ENCODER_BB, ENCODER_CPR)); // B 373
				add(new SwerveWheel(new Vector2d(L/2, -W/2), RobotMap.DRIVE_MOTOR_C, RobotMap.PIVOT_MOTOR_C, 752, 867,
						11, RobotMap.PIVOT_ENCODER_CA, RobotMap.PIVOT_ENCODER_CB, ENCODER_CPR)); // C
				add(new SwerveWheel(new Vector2d(L/2, W/2), RobotMap.DRIVE_MOTOR_D, RobotMap.PIVOT_MOTOR_D, 75, 850,
						11, RobotMap.PIVOT_ENCODER_DA, RobotMap.PIVOT_ENCODER_DB, ENCODER_CPR)); // D
			}
		};
		
		//Using values from old robot, may not be correct
		mxpPort = new SerialPort(57600, SerialPort.Port.kMXP);
		imu = new IMU(mxpPort, (byte) 127);

		normalizer = new SpeedControllerNormalizer();
	}

	public void drive(final double rotation, final Vector2d translation) {

		swerveWheels.forEach((w) -> w.drive(translation, rotation, normalizer));

		normalizer.run();
		normalizer.clear();
	}

	public void relativeDrive(double rotation, Vector2d translation) {
		if (translation.magnitude() < .15)
			translation = new Vector2d(0, 0);

		double robotAngle = Math.toRadians(imu.getYaw());

		translation = new Vector2d(
				translation.getX() * Math.cos(robotAngle) - translation.getY() * Math.sin(robotAngle),
				translation.getY() * Math.cos(robotAngle) + translation.getX() * Math.sin(robotAngle));

		System.out.println("OUT: " + translation);

		double a = translation.getX() - rotation * L / 2;
		double b = translation.getX() + rotation * L / 2;
		double c = translation.getY() - rotation * W / 2;
		double d = translation.getY() + rotation * W / 2;

		swerveWheels.get(0).relativeDrive(-Math.atan2(b, c), speed(b, c), normalizer);
		swerveWheels.get(1).relativeDrive(-Math.atan2(b, d), speed(b, d), normalizer);
		swerveWheels.get(2).relativeDrive(-Math.atan2(a, d), speed(a, d), normalizer);
		swerveWheels.get(3).relativeDrive(-Math.atan2(a, c), speed(a, c), normalizer);

		normalizer.run();
		normalizer.clear();
	}

	private double speed(double x, double y)
	{
		return Math.sqrt(x * x + y * y);
	}
	
	public void zeroYaw() {
		imu.zeroYaw();
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

}
