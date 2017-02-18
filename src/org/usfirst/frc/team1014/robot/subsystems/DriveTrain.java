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

	public static DriveTrain getInstance() {
		if (instance == null)
			instance = new DriveTrain();
		return instance;
	}

	private DriveTrain() {
		swerveWheels = new ArrayList<SwerveWheel>() {
			{
				add(new SwerveWheel(new Vector2d(1, 1), RobotMap.DRIVE_MOTOR_A, RobotMap.PIVOT_MOTOR_A, 354, 888, 13)); // A
				add(new SwerveWheel(new Vector2d(-1, 1), RobotMap.DRIVE_MOTOR_B, RobotMap.PIVOT_MOTOR_B, 171, 867, 13)); // B
				add(new SwerveWheel(new Vector2d(-1, -1), RobotMap.DRIVE_MOTOR_C, RobotMap.PIVOT_MOTOR_C, 824, 882,
						13)); // C
				add(new SwerveWheel(new Vector2d(1, -1), RobotMap.DRIVE_MOTOR_D, RobotMap.PIVOT_MOTOR_D, 737, 888, 13)); // D
			}
		};

		normalizer = new SpeedControllerNormalizer();
	}

	public void drive(final double rotation, final Vector2d translation) {
		swerveWheels.forEach((w) -> w.drive(translation, rotation, normalizer));

		// normalizer.run();
		// normalizer.clear();
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

}
