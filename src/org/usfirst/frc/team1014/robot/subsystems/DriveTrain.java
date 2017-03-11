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
				add(new SwerveWheel("A", "left", new Vector2d(L / 2, W / 2), RobotMap.DRIVE_MOTOR_A,
						RobotMap.PIVOT_MOTOR_A, 375, 853, 15, ENCODER_CPR)); 
				add(new SwerveWheel("B", "right", new Vector2d(-L / 2, W / 2), RobotMap.DRIVE_MOTOR_B,
						RobotMap.PIVOT_MOTOR_B, 543, 867, 13, ENCODER_CPR));
				add(new SwerveWheel("C", "right", new Vector2d(-L / 2, -W / 2), RobotMap.DRIVE_MOTOR_C,
						RobotMap.PIVOT_MOTOR_C, 587, 882, 13, ENCODER_CPR)); 
				add(new SwerveWheel("D", "left", new Vector2d(L / 2, -W / 2), RobotMap.DRIVE_MOTOR_D,
						RobotMap.PIVOT_MOTOR_D, 294, 888, 13, ENCODER_CPR));
			}
		};

		navx = new AHRS(SPI.Port.kMXP);
		navx.zeroYaw();

		normalizer = new SpeedControllerNormalizer();
	}

	public void zeroYaw() {
		navx.zeroYaw();
	}

	public double getYaw() {
		return navx.getYaw();
	}
	
	public void test(){
		swerveWheels.forEach((w) -> w.print());
	}
	
	public double getCurrent(){
		double sum = 0;
		
		for(int i = 0; i < swerveWheels.size(); i++)
			sum += swerveWheels.get(i).getCurrent();
		
		return sum / swerveWheels.size();
	}

	public void drive(final double rotation, Vector2d translation) {

		final Vector2d move = translation.rotateDegrees(navx.getYaw() * -1);

		swerveWheels.forEach((w) -> w.drive(move, rotation, normalizer));

		normalizer.run();
		normalizer.clear();
	}

	public void relativeDrive(final double rotation, Vector2d translation) {
		if (translation.magnitude() < .15)
			translation = new Vector2d(0, 0);

		double robotAngle = Math.toRadians(navx.getYaw());

		final Vector2d move = new Vector2d(
				translation.getX() * Math.cos(robotAngle) - translation.getY() * Math.sin(robotAngle),
				translation.getY() * Math.cos(robotAngle) + translation.getX() * Math.sin(robotAngle));

		swerveWheels.forEach((w) -> w.relativeDrive(move, rotation, normalizer));

		normalizer.run();
		normalizer.clear();
	}

	public void setRelative() {
		for (SwerveWheel w : swerveWheels) {
			w.setRelative(ENCODER_CPR);
		}
	}

	public void tankDrive(double rightStick, double leftStick, double encoderPosition) {
		swerveWheels.forEach((w) -> w.tankDrive(rightStick, leftStick, encoderPosition));

	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

}
