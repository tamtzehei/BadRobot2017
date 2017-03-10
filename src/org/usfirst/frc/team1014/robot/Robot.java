package org.usfirst.frc.team1014.robot;

import org.usfirst.frc.team1014.robot.commands.AutoGroup;
import org.usfirst.frc.team1014.robot.commands.CommandBase;
import org.usfirst.frc.team1014.robot.commands.RelativeDrive;
import org.usfirst.frc.team1014.robot.commands.TankDrive;
import org.usfirst.frc.team1014.robot.commands.TeleDrive;
import org.usfirst.frc.team1014.robot.commands.TeleopGroup;
import org.usfirst.frc.team1014.robot.commands.TestGroup;
import org.usfirst.frc.team1014.robot.commands.auto.AutoDrive;
import org.usfirst.frc.team1014.robot.commands.auto.CrossCenter;
import org.usfirst.frc.team1014.robot.commands.auto.CrossLeft;
import org.usfirst.frc.team1014.robot.commands.auto.CrossRight;
import org.usfirst.frc.team1014.robot.commands.auto.GearCenter;
import org.usfirst.frc.team1014.robot.commands.auto.GearLeft;
import org.usfirst.frc.team1014.robot.commands.auto.GearRight;
import org.usfirst.frc.team1014.robot.commands.auto.ShootCenter;
import org.usfirst.frc.team1014.robot.commands.auto.ShootLeft;
import org.usfirst.frc.team1014.robot.commands.auto.ShootRight;
import org.usfirst.frc.team1014.robot.subsystems.LEDLights.LEDState;
import org.usfirst.frc.team1014.robot.utils.Vector2d;

import com.ctre.CANTalon;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
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

	SendableChooser driveChooser, autoChooser;
	SmartDashboard smartDashboard;
	UsbCamera camera;
	
	PowerAnalyser powerAnalyser;

	public static OI oi;
	
	CANTalon climber;

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
		smartDashboard = new SmartDashboard();
		
		driveChooser = new SendableChooser();
		driveChooser.addDefault("Swerve Drive", new TeleDrive());
		driveChooser.addObject("Relative Swerve", new RelativeDrive());
		driveChooser.addObject("Tank Drive", new TankDrive());
		
		autoChooser = new SendableChooser();
		autoChooser.addDefault("CrossLeft", new CrossLeft());
		autoChooser.addObject("CrossRight", new CrossRight());
		autoChooser.addObject("CrossCenter", new CrossCenter());
		autoChooser.addObject("GearLeft", new GearLeft());
		autoChooser.addObject("GearRight", new GearRight());
		autoChooser.addObject("GearCenter", new GearCenter());
		autoChooser.addObject("ShootLeft", new ShootLeft());
		autoChooser.addObject("ShootRight", new ShootRight());
		autoChooser.addObject("ShootCenter", new ShootCenter());
		
		smartDashboard.putNumber("Delay", 0);
		smartDashboard.putData("Drive Mode Chooser", driveChooser);
		smartDashboard.putData("Auto Chooser", autoChooser);
		
		//climber = new CANTalon(30);

		//camera = CameraServer.getInstance().startAutomaticCapture();
		//camera.setResolution(640, 480);
		
		if (CommandBase.lights != null) {
			CommandBase.lights.setLights(LEDState.kDEFAULT);
		}
		
		powerAnalyser = new PowerAnalyser(new PowerDistributionPanel()) {{
			add("PIVOT-A", 7);
			add("DRIVE-A", 3);
			add("PIVOT-B", 4);
			add("DRIVE-B", 14);
			add("PIVOT-C", 11);
			add("DRIVE-C", 15);
			add("PIVOT-D", 8);
			add("DRIVE-D", 2);
		}};
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
		//teleopGroup.addParallel((Command) driveChooser.getSelected());
		Scheduler.getInstance().add(teleopGroup);
	}

	@Override
	public void autonomousInit() {
		stateChangeInit();
		//autoGroup.addSequential(new AutoDelay(smartDashboard.getNumber("Delay", 0)));
		//autoGroup.addSequential((Command) autoChooser.getSelected());
		autoGroup.addSequential(new AutoDrive(2, new Vector2d(0,1)));
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
		powerAnalyser.update();
	}

	@Override
	public void teleopPeriodic() {
		periodic();
		//climber.set(oi.xboxController1.getTriggerAxis(Hand.kLeft) - oi.xboxController1.getTriggerAxis(Hand.kRight));
		//This climber works but a new climber has been made that uses buttons that needs to be tested
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