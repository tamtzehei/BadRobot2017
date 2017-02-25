package org.usfirst.frc.team1014.robot.subsystems;

import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc.team1014.robot.RobotMap;
import org.usfirst.frc.team1014.robot.utils.SpeedControllerNormalizer;
import org.usfirst.frc.team1014.robot.utils.SwerveWheel;
import org.usfirst.frc.team1014.robot.utils.Vector2d;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {
	private static DriveTrain instance;
	SpeedControllerNormalizer normalizer;
	List<SwerveWheel> swerveWheels;

	AHRS navx;

	private static final double L = .69;
	private static final double W = .48;

	public static DriveTrain getInstance() {
		if (instance == null)
			instance = new DriveTrain();
		return instance;
	}

	private DriveTrain() {
		swerveWheels = new ArrayList<SwerveWheel>() {
			{
				// Relative Encoder values are not correct
				add(new SwerveWheel("A", SwerveWheel.TankPosition.LEFT,new Vector2d(L / 2, W / 2), RobotMap.DRIVE_MOTOR_A, RobotMap.PIVOT_MOTOR_A,
						789, 888, 13)); // A
				add(new SwerveWheel("B", SwerveWheel.TankPosition.RIGHT, new Vector2d(-L / 2, W / 2), RobotMap.DRIVE_MOTOR_B, RobotMap.PIVOT_MOTOR_B,
						99, 867, 13)); // B
				add(new SwerveWheel("C", SwerveWheel.TankPosition.RIGHT, new Vector2d(-L / 2, -W / 2), RobotMap.DRIVE_MOTOR_C, RobotMap.PIVOT_MOTOR_C,
						118, 882, 13)); // C
				add(new SwerveWheel("D", SwerveWheel.TankPosition.LEFT, new Vector2d(L / 2, -W / 2), RobotMap.DRIVE_MOTOR_D, RobotMap.PIVOT_MOTOR_D,
						701, 888, 13)); // D
			}
		};
		
		

		navx = new AHRS(SPI.Port.kMXP);
		navx.zeroYaw();

		normalizer = new SpeedControllerNormalizer();
	}

	public void zeroYaw() {
		navx.zeroYaw();

	}

	public void drive(final double rotation, Vector2d translation) {

		final Vector2d move = translation.rotateDegrees(navx.getYaw() * -1);

		swerveWheels.forEach((w) -> w.drive(move, rotation, normalizer));

		normalizer.run();
		normalizer.clear();
	}

	public void tankDrive(double rightStick, double leftStick, double encoderPosition) {
		swerveWheels.forEach((w) -> w.tankDrive(rightStick, leftStick, encoderPosition));

	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

}

