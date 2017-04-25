/*
 * OSVDataWatcher.java
 *
 *
 * Copyright 2017 Institut Mines-Télécom
 *
 * This file is part of tb-osv-dashboard.
 *
 * tb-osv-dashboard is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * tb-osv-dashboard is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with tb-osv-dashboard. If not, see <http://www.gnu.org/licenses/>.
 */
package fr.enstb.osv.dashboard;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingWorker;

/**
 * @author guillaumelg
 *
 */
public class OSVDataWatcher extends SwingWorker<Void, String> {

	public static final String speedFile = "/tmp/osvdatasimulator/speed";
	public static final String socFile = "/tmp/osvdatasimulator/soc";
	public static final String isChargingFile = "/tmp/osvdatasimulator/is_charging";
	public static final String temperatureFile = "/tmp/osvdatasimulator/temperature_";
	public static final String cellVoltageFile = "/tmp/osvdatasimulator/cell_voltage_";
	private static final String totalDistanceFile = "/tmp/osvdatasimulator/total_distance";

	private MainWindow mw;

	public OSVDataWatcher(MainWindow mw) {
		this.mw = mw;
	}

	@Override
	protected Void doInBackground() throws Exception {

		System.out.println("Watcher starting");

		while (!(new File("/tmp/osvdatasimulator")).isDirectory()) {
			Thread.sleep(1000);
		}

		System.out.println("Watcher ready");

		WatchService watchService = FileSystems.getDefault().newWatchService();
		Path directory = Paths.get("/tmp/osvdatasimulator");
		directory.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
		WatchKey key = null;

		while (true) {
			key = watchService.take();
			if (key != null) {
				List<WatchEvent<?>> eventList = key.pollEvents();

				for (int i = 0; i < eventList.size(); i++) {
					publish(eventList.get(i).context().toString());
				}

				key.reset();
			}
		}
	}

	public void process(List<String> data) {
		List<String> deduplicateEventList = new ArrayList<String>();
		for (String s : data) {
			if (!deduplicateEventList.contains(s)) {
				deduplicateEventList.add(s);
			}
		}

		String sData;

		for (String s : deduplicateEventList) {

			if (s.equals("speed")) {
				sData = readFirstLineInFile(speedFile);
				if (sData != null) {
					mw.setSpeed(Float.parseFloat(sData));
				}
			} else if (s.equals("soc")) {
				sData = readFirstLineInFile(socFile);
				if (sData != null) {
					mw.setSoc(Float.parseFloat(sData) / 100);
				}
			} else if (s.equals("is_charging")) {
				sData = readFirstLineInFile(isChargingFile);
				if (sData != null) {
					mw.setIsCharging(sData.startsWith("1"));
				}
			} else if (s.equals("total_distance")) {
				sData = readFirstLineInFile(totalDistanceFile);
				if (sData != null) {
					mw.setTotalDistance(Float.parseFloat(sData));
				}
			} else if (s.startsWith("temperature_")) {
				String tempSensorNumber = s.substring(12);
				sData = readFirstLineInFile(temperatureFile + tempSensorNumber);
				if (sData != null) {
					mw.setTemperature(Integer.parseInt(tempSensorNumber), Float.parseFloat(sData));
				}
			} else if (s.startsWith("cell_voltage_")) {
				String cellNumber = s.substring(13);
				sData = readFirstLineInFile(cellVoltageFile + cellNumber);
				if (sData != null) {
					try {
						mw.setCellVoltage(Integer.parseInt(cellNumber), Float.parseFloat(sData));
					} catch (NumberFormatException e) {
					}
				}
			}
		}
	}

	public String readFirstLineInFile(String pathToFile) {
		String lign = "";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(pathToFile)));
			lign = reader.readLine();
			reader.close();
		} catch (IOException ex) {
			System.err.println("Error: " + ex.getMessage());
		}
		return lign;
	}

	public void init() {
		if ((new File(socFile)).exists()) {
			mw.setSoc(Float.parseFloat(readFirstLineInFile(socFile)) / 100);
		}
		if ((new File(speedFile)).exists()) {
			mw.setSpeed(Float.parseFloat(readFirstLineInFile(speedFile)));
		}
	}
}
