package org.usfirst.frc.team1014.robot.subsystems;

import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc.team1014.robot.RobotMap;
import org.usfirst.frc.team1014.robot.util.Vector2d;
import org.usfirst.frc.team1014.robot.utils.SpeedControllerNormalizer;
import org.usfirst.frc.team1014.robot.utils.SwerveWheel;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {
	private static DriveTrain instance;
	SpeedControllerNormalizer normalizer;
	List<SwerveWheel> swerveWheels;
	SerialPort mxpPort;
	IMU imu;
	private static final double ENCODER_CPR = 414.1666d;

	public static DriveTrain getInstance() {
		if (instance == null)
			instance = new DriveTrain();
		return instance;
	}

	private DriveTrain() {
		swerveWheels = new ArrayList<SwerveWheel>() {
			{
				add(new SwerveWheel(new Vector2d(12.75, 11), RobotMap.DRIVE_MOTOR_A, RobotMap.PIVOT_MOTOR_A, 334, 888,
						13, RobotMap.PIVOT_ENCODER_AA, RobotMap.PIVOT_ENCODER_AB, ENCODER_CPR)); // A
				add(new SwerveWheel(new Vector2d(-12.75, 11), RobotMap.DRIVE_MOTOR_B, RobotMap.PIVOT_MOTOR_B, 722, 867,
						13, RobotMap.PIVOT_ENCODER_BA, RobotMap.PIVOT_ENCODER_BB, ENCODER_CPR)); // B
				add(new SwerveWheel(new Vector2d(-12.75, -11), RobotMap.DRIVE_MOTOR_C, RobotMap.PIVOT_MOTOR_C, 127, 882,
						13, RobotMap.PIVOT_ENCODER_CA, RobotMap.PIVOT_ENCODER_CB, ENCODER_CPR)); // C
				add(new SwerveWheel(new Vector2d(12.75, -11), RobotMap.DRIVE_MOTOR_D, RobotMap.PIVOT_MOTOR_D, 731, 888,
						13, RobotMap.PIVOT_ENCODER_DA, RobotMap.PIVOT_ENCODER_DB, ENCODER_CPR)); // D
			}
		};
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
		if (Math.abs(rotation) < .2)
			rotation = 0;
		if (translation.magnitude() < .15)
			translation = new Vector2d(0, 0);

		double robotAngle = Math.toRadians(imu.getYaw());

		translation = new Vector2d(
				translation.getX() * Math.cos(robotAngle) - translation.getY() * Math.sin(robotAngle),
				translation.getY() * Math.cos(robotAngle) + translation.getX() * Math.sin(robotAngle));

		System.out.println("OUT: " + translation);

		/*double a = translation.getX() - rotation * L / 2;
		double b = translation.getX() + rotation * L / 2;
		double c = translation.getY() - rotation * W / 2;
		double d = translation.getY() + rotation * W / 2;

		wheelA.drive(-Math.atan2(b, c), speed(b, c), 1, normalizer);
		wheelB.drive(-Math.atan2(b, d), speed(b, d), 2, normalizer);
		wheelC.drive(-Math.atan2(a, d), speed(a, d), 3, normalizer);
		wheelD.drive(-Math.atan2(a, c), speed(a, c), 4, normalizer);*/

		normalizer.run();
		normalizer.clear();
	}

	public void zeroYaw() {
		imu.zeroYaw();
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

}
