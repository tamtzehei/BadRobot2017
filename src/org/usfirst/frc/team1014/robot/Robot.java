package org.usfirst.frc.team1014.robot;

import org.usfirst.frc.team1014.robot.commands.AutoGroup;
import org.usfirst.frc.team1014.robot.commands.CommandBase;
import org.usfirst.frc.team1014.robot.commands.RelativeDrive;
import org.usfirst.frc.team1014.robot.commands.TankDrive;
import org.usfirst.frc.team1014.robot.commands.TeleDrive;
import org.usfirst.frc.team1014.robot.commands.TeleopGroup;
import org.usfirst.frc.team1014.robot.commands.TestGroup;
import org.usfirst.frc.team1014.robot.subsystems.LEDLights.LEDState;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	TeleopGroup teleopGroup;
	AutoGroup autoGroup;
	TestGroup testGroup;

	SendableChooser driveChooser;
	SmartDashboard smartDashboard;
	UsbCamera camera;

	public static OI oi;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		teleopGroup = new TeleopGroup();
		autoGroup = new AutoGroup();
		testGroup = new TestGroup();

		driveChooser = new SendableChooser();
		driveChooser.addDefault("Swerve Drive", new TeleDrive());
		driveChooser.addObject("Relative Swerve", new RelativeDrive());
		driveChooser.addObject("Tank Drive", new TankDrive());
		smartDashboard.putData("Drive Mode Chooser", driveChooser);
		
		camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(640, 480);
		
		if (CommandBase.lights != null) {
			CommandBase.lights.setLights(LEDState.kDEFAULT);
		}
	}

	/*
	 * An Init function is called whenever the robot changes state.
	 */

	private void stateChangeInit() {
		Scheduler.getInstance().removeAll();
	}

	@Override
	public void teleopInit() {
		stateChangeInit();
		teleopGroup.addParallel((Command) driveChooser.getSelected());
		Scheduler.getInstance().add(teleopGroup);
	}

	@Override
	public void autonomousInit() {
		stateChangeInit();
		Scheduler.getInstance().add(autoGroup);
	}

	@Override
	public void testInit() {
		stateChangeInit();
		Scheduler.getInstance().add(testGroup);
	}

	@Override
	public void disabledInit() {
		stateChangeInit();
	}

	/*
	 * Periodic commands are called every 20m by the system. If it does not
	 * return within 20ms it will wait until the last one returned.
	 */

	private void periodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopPeriodic() {
		periodic();
		//climbTalon.set(oi.xboxController0.getTriggerAxis(Hand.kLeft) - oi.xboxController0.getTriggerAxis(Hand.kRight));
	}

	@Override
	public void autonomousPeriodic() {
		periodic();
	}

	@Override
	public void testPeriodic() {
		periodic();
	}

	@Override
	public void disabledPeriodic() {
	}
}
