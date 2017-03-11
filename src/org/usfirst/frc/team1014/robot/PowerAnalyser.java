package org.usfirst.frc.team1014.robot;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PowerAnalyser {
	PowerDistributionPanel pdp;
	LinkedHashMap<String, Integer> channels;
	String fileName = "/home/lvuser/powerlog-" + System.currentTimeMillis() / 1000 + ".csv";
	Optional<FileWriter> fileWriter = Optional.empty();

	public PowerAnalyser(PowerDistributionPanel pdp) {
		channels = new LinkedHashMap<>();
		this.pdp = pdp;
		try {
			fileWriter = Optional.of(new FileWriter(fileName, true));
			System.out.println("Writing power to " + fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void add(String name, int channel) {
		channels.put(name + "-current", channel);
	}

	public void update() {
		channels.forEach((k, v) -> SmartDashboard.putDouble(k, pdp.getCurrent(v)));
		if (!fileWriter.isPresent())
			return;
		List<String> valueStrings = new ArrayList<>();
		channels.forEach((k, v) -> valueStrings.add(String.valueOf(pdp.getCurrent(v))));
		String[] vals = new String[channels.size()];
		valueStrings.toArray(vals);
		try {
			fileWriter.get().write(String.join(", ", vals) + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fileWriter.get().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void printFieldHeader() {
		if (!fileWriter.isPresent())
			return;
		List<String> valueStrings = new ArrayList<>();
		channels.forEach((k, v) -> valueStrings.add(k));
		String[] vals = new String[channels.size()];
		valueStrings.toArray(vals);
		try {
			fileWriter.get().write(String.join(", ", vals) + "\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
