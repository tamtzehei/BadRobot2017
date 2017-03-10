package org.usfirst.frc.team1014.robot;

import java.util.HashMap;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PowerAnalyser {
	PowerDistributionPanel pdp;
	HashMap<String, Integer> channels;

	public PowerAnalyser(PowerDistributionPanel pdp) {
		this.pdp = pdp;
	}

	public void add(String name, int channel) {
		channels.put(name +"-current", channel);
	}

	public void update() {
		channels.forEach((k, v) -> SmartDashboard.putDouble(k, pdp.getCurrent(v)));
	}

}
