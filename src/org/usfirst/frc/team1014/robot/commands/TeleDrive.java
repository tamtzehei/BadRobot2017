package org.usfirst.frc.team1014.robot.commands;

import org.usfirst.frc.team1014.robot.OI;
import org.usfirst.frc.team1014.robot.commands.auto.AutoRotate;
import org.usfirst.frc.team1014.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1014.robot.utils.Vector2d;

import edu.wpi.first.wpilibj.command.Command;

public class TeleDrive extends Command {

	/*
	 * private static boolean isSwerve; private static boolean isAbsolute;
	 * private static double badEncoderPosition; private static boolean Xbutton,
	 * Abutton;
	 * 
	 * protected void initialize() {
	 * 
	 * isSwerve = true; isAbsolute = true; Xbutton = false; Abutton = false;
	 * 
	 * }
	 */
	private static boolean AButton;
	
	protected void initialize(){
		AButton = false;
	}
	
	double angle;
	AutoRotate rotate;
	
	public void execute() {
		double rotation = OI.xboxController0.getRawAxis(4);
		if (Math.abs(rotation) < .15)
			rotation = 0;

		DriveTrain.getInstance().drive(rotation,
				new Vector2d(OI.xboxController0.getRawAxis(0), -OI.xboxController0.getRawAxis(1)));
		
		if(OI.xboxController0.getAButton()){
			AButton = true;
		}
		if(OI.xboxController0.getAButton() && AButton){
			DriveTrain.getInstance().zeroYaw();
			AButton = false;
		}

		/*angle = OI.xboxController0.getPOV();
		
		if (OI.xboxController0.getPOV() != -1) {
			if(angle > 180)
				angle = 360 - angle;
			rotate = new AutoRotate(angle);
			rotate.start();
		}*/
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
