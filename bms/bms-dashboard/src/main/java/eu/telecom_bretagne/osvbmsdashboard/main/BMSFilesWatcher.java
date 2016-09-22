/**
 * Copyright 2016 Institut Mines-Télécom
 *
 * This file is part of osv-bms-dashboard.
 *
 * osv-bms-dashboard is free software: you can redistribute it
 * and/or modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * osv-bms-dashboard is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with osv-bms-dashboard.  If not, see
 * <http://www.gnu.org/licenses/>.
 */

package eu.telecom_bretagne.osvbmsdashboard.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.file.NoSuchFileException;
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
public class BMSFilesWatcher extends SwingWorker<Void, String> {

	public static final String BALANCING = "balancing";
	public static final String CELL_00_VOLTAGE = "cell_00_voltage";
	public static final String CELL_01_VOLTAGE = "cell_01_voltage";
	public static final String CELL_02_VOLTAGE = "cell_02_voltage";
	public static final String CELL_03_VOLTAGE = "cell_03_voltage";
	public static final String CELL_04_VOLTAGE = "cell_04_voltage";
	public static final String CELL_05_VOLTAGE = "cell_05_voltage";
	public static final String CELL_06_VOLTAGE = "cell_06_voltage";
	public static final String CELL_07_VOLTAGE = "cell_07_voltage";
	public static final String CELL_08_VOLTAGE = "cell_08_voltage";
	public static final String CELL_09_VOLTAGE = "cell_09_voltage";
	public static final String CELL_10_VOLTAGE = "cell_10_voltage";
	public static final String CELL_11_VOLTAGE = "cell_11_voltage";
	public static final String CELL_12_VOLTAGE = "cell_12_voltage";
	public static final String CELL_13_VOLTAGE = "cell_13_voltage";
	public static final String CELL_14_VOLTAGE = "cell_14_voltage";
	public static final String CELL_15_VOLTAGE = "cell_15_voltage";
	public static final String CELL_16_VOLTAGE = "cell_16_voltage";
	public static final String CELL_17_VOLTAGE = "cell_17_voltage";
	public static final String CELL_18_VOLTAGE = "cell_18_voltage";
	public static final String CELL_19_VOLTAGE = "cell_19_voltage";
	public static final String CELL_20_VOLTAGE = "cell_20_voltage";
	public static final String CELL_21_VOLTAGE = "cell_21_voltage";
	public static final String CELL_22_VOLTAGE = "cell_22_voltage";
	public static final String CELL_23_VOLTAGE = "cell_23_voltage";
	public static final String CHARGING = "charging";
	public static final String CURRENT = "current";
	public static final String PWM_TO_CHARGER = "pwm_to_charger";
	public static final String PWM_TO_ENGINE_CONTROLLER = "pwm_to_engine_controller";
	public static final String SOC = "soc";
	public static final String TEMPERATURE_0 = "temperature_0";
	public static final String TEMPERATURE_1 = "temperature_1";
	public static final String TEMPERATURE_2 = "temperature_2";
	public static final String TEMPERATURE_3 = "temperature_3";

	List<String> files = new ArrayList<String>();
	List<String> cellVoltagesFields = new ArrayList<String>();
	private MainWindow mw;
	private boolean stop = false;

	public BMSFilesWatcher(MainWindow mainWindow) {
		this.mw = mainWindow;
		createListOfFilesAndCellVoltagesFields();
		lookForFiles();
	}

	private void createListOfFilesAndCellVoltagesFields() {
		files.add(BALANCING);
		files.add(CELL_00_VOLTAGE);
		files.add(CELL_01_VOLTAGE);
		files.add(CELL_02_VOLTAGE);
		files.add(CELL_03_VOLTAGE);
		files.add(CELL_04_VOLTAGE);
		files.add(CELL_05_VOLTAGE);
		files.add(CELL_06_VOLTAGE);
		files.add(CELL_07_VOLTAGE);
		files.add(CELL_08_VOLTAGE);
		files.add(CELL_09_VOLTAGE);
		files.add(CELL_10_VOLTAGE);
		files.add(CELL_11_VOLTAGE);
		files.add(CELL_12_VOLTAGE);
		files.add(CELL_13_VOLTAGE);
		files.add(CELL_14_VOLTAGE);
		files.add(CELL_15_VOLTAGE);
		files.add(CELL_16_VOLTAGE);
		files.add(CELL_17_VOLTAGE);
		files.add(CELL_18_VOLTAGE);
		files.add(CELL_19_VOLTAGE);
		files.add(CELL_20_VOLTAGE);
		files.add(CELL_21_VOLTAGE);
		files.add(CELL_22_VOLTAGE);
		files.add(CELL_23_VOLTAGE);
		files.add(CHARGING);
		files.add(CURRENT);
		files.add(PWM_TO_CHARGER);
		files.add(PWM_TO_ENGINE_CONTROLLER);
		files.add(SOC);
		files.add(TEMPERATURE_0);
		files.add(TEMPERATURE_1);
		files.add(TEMPERATURE_2);
		files.add(TEMPERATURE_3);
		
		cellVoltagesFields.add(DataPanel.CELL_01_VOLTAGE);
		cellVoltagesFields.add(DataPanel.CELL_02_VOLTAGE);
		cellVoltagesFields.add(DataPanel.CELL_03_VOLTAGE);
		cellVoltagesFields.add(DataPanel.CELL_04_VOLTAGE);
		cellVoltagesFields.add(DataPanel.CELL_05_VOLTAGE);
		cellVoltagesFields.add(DataPanel.CELL_06_VOLTAGE);
		cellVoltagesFields.add(DataPanel.CELL_07_VOLTAGE);
		cellVoltagesFields.add(DataPanel.CELL_08_VOLTAGE);
		cellVoltagesFields.add(DataPanel.CELL_09_VOLTAGE);
		cellVoltagesFields.add(DataPanel.CELL_10_VOLTAGE);
		cellVoltagesFields.add(DataPanel.CELL_11_VOLTAGE);
		cellVoltagesFields.add(DataPanel.CELL_12_VOLTAGE);
		cellVoltagesFields.add(DataPanel.CELL_13_VOLTAGE);
		cellVoltagesFields.add(DataPanel.CELL_14_VOLTAGE);
		cellVoltagesFields.add(DataPanel.CELL_15_VOLTAGE);
		cellVoltagesFields.add(DataPanel.CELL_16_VOLTAGE);
		cellVoltagesFields.add(DataPanel.CELL_17_VOLTAGE);
		cellVoltagesFields.add(DataPanel.CELL_18_VOLTAGE);
		cellVoltagesFields.add(DataPanel.CELL_19_VOLTAGE);
		cellVoltagesFields.add(DataPanel.CELL_20_VOLTAGE);
		cellVoltagesFields.add(DataPanel.CELL_21_VOLTAGE);
		cellVoltagesFields.add(DataPanel.CELL_22_VOLTAGE);
		cellVoltagesFields.add(DataPanel.CELL_23_VOLTAGE);
		cellVoltagesFields.add(DataPanel.CELL_24_VOLTAGE);
	}

	private void updateFieldContent(String fileName, String pathToFile) {
		if (fileName.equals(BALANCING)) {
			try {
			mw.centerPanel.batteryStatus.setData(DataPanel.BATTERY_BALANCING,
					Float.parseFloat(readFirstLineInFile(pathToFile)));
			} catch (FileNotFoundException e) {
				mw.centerPanel.batteryStatus.setData(DataPanel.BATTERY_BALANCING, 0);
			}
		}
		if (fileName.equals(CELL_00_VOLTAGE)) {
			try {
			mw.centerPanel.cellVoltagePanel.setData(DataPanel.CELL_01_VOLTAGE,
					Float.parseFloat(readFirstLineInFile(pathToFile)));
			} catch (FileNotFoundException e) {
				mw.centerPanel.cellVoltagePanel.setData(DataPanel.CELL_01_VOLTAGE, 0);
			}
			updateBatteryOverallVoltage();
		}
		if (fileName.equals(CELL_01_VOLTAGE)) {
			try {
			mw.centerPanel.cellVoltagePanel.setData(DataPanel.CELL_02_VOLTAGE,
					Float.parseFloat(readFirstLineInFile(pathToFile)));
			} catch (FileNotFoundException e) {
				mw.centerPanel.cellVoltagePanel.setData(DataPanel.CELL_02_VOLTAGE, 0);
			}
			updateBatteryOverallVoltage();
		}
		if (fileName.equals(CELL_02_VOLTAGE)) {
			try {
			mw.centerPanel.cellVoltagePanel.setData(DataPanel.CELL_03_VOLTAGE,
					Float.parseFloat(readFirstLineInFile(pathToFile)));
			} catch (FileNotFoundException e) {
				mw.centerPanel.cellVoltagePanel.setData(DataPanel.CELL_03_VOLTAGE, 0);
			}
			updateBatteryOverallVoltage();
		}
		if (fileName.equals(CELL_03_VOLTAGE)) {
			try {
			mw.centerPanel.cellVoltagePanel.setData(DataPanel.CELL_04_VOLTAGE,
					Float.parseFloat(readFirstLineInFile(pathToFile)));
			} catch (FileNotFoundException e) {
				mw.centerPanel.cellVoltagePanel.setData(DataPanel.CELL_04_VOLTAGE, 0);
			}
			updateBatteryOverallVoltage();
		}
		if (fileName.equals(CELL_04_VOLTAGE)) {
			try {
			mw.centerPanel.cellVoltagePanel.setData(DataPanel.CELL_05_VOLTAGE,
					Float.parseFloat(readFirstLineInFile(pathToFile)));
			} catch (FileNotFoundException e) {
				mw.centerPanel.cellVoltagePanel.setData(DataPanel.CELL_05_VOLTAGE, 0);
			}
			updateBatteryOverallVoltage();
		}
		if (fileName.equals(CELL_05_VOLTAGE)) {
			try {
			mw.centerPanel.cellVoltagePanel.setData(DataPanel.CELL_06_VOLTAGE,
					Float.parseFloat(readFirstLineInFile(pathToFile)));
			} catch (FileNotFoundException e) {
				mw.centerPanel.cellVoltagePanel.setData(DataPanel.CELL_06_VOLTAGE, 0);
			}
			updateBatteryOverallVoltage();
		}
		if (fileName.equals(CELL_06_VOLTAGE)) {
			try {
			mw.centerPanel.cellVoltagePanel.setData(DataPanel.CELL_07_VOLTAGE,
					Float.parseFloat(readFirstLineInFile(pathToFile)));
			} catch (FileNotFoundException e) {
				mw.centerPanel.cellVoltagePanel.setData(DataPanel.CELL_07_VOLTAGE, 0);
			}
			updateBatteryOverallVoltage();
		}
		if (fileName.equals(CELL_07_VOLTAGE)) {
			try {
			mw.centerPanel.cellVoltagePanel.setData(DataPanel.CELL_08_VOLTAGE,
					Float.parseFloat(readFirstLineInFile(pathToFile)));
			} catch (FileNotFoundException e) {
				mw.centerPanel.cellVoltagePanel.setData(DataPanel.CELL_08_VOLTAGE, 0);
			}
			updateBatteryOverallVoltage();
		}
		if (fileName.equals(CELL_08_VOLTAGE)) {
			try {
			mw.centerPanel.cellVoltagePanel.setData(DataPanel.CELL_09_VOLTAGE,
					Float.parseFloat(readFirstLineInFile(pathToFile)));
			} catch (FileNotFoundException e) {
				mw.centerPanel.cellVoltagePanel.setData(DataPanel.CELL_09_VOLTAGE, 0);
			}
			updateBatteryOverallVoltage();
		}
		if (fileName.equals(CELL_09_VOLTAGE)) {
			try {
			mw.centerPanel.cellVoltagePanel.setData(DataPanel.CELL_10_VOLTAGE,
					Float.parseFloat(readFirstLineInFile(pathToFile)));
			} catch (FileNotFoundException e) {
				mw.centerPanel.cellVoltagePanel.setData(DataPanel.CELL_10_VOLTAGE, 0);
			}
			updateBatteryOverallVoltage();
		}
		if (fileName.equals(CELL_10_VOLTAGE)) {
			try {
			mw.centerPanel.cellVoltagePanel.setData(DataPanel.CELL_11_VOLTAGE,
					Float.parseFloat(readFirstLineInFile(pathToFile)));
			} catch (FileNotFoundException e) {
				mw.centerPanel.cellVoltagePanel.setData(DataPanel.CELL_11_VOLTAGE, 0);
			}
			updateBatteryOverallVoltage();
		}
		if (fileName.equals(CELL_11_VOLTAGE)) {
			try {
			mw.centerPanel.cellVoltagePanel.setData(DataPanel.CELL_12_VOLTAGE,
					Float.parseFloat(readFirstLineInFile(pathToFile)));
			} catch (FileNotFoundException e) {
				mw.centerPanel.cellVoltagePanel.setData(DataPanel.CELL_12_VOLTAGE, 0);
			}
			updateBatteryOverallVoltage();
		}
		if (fileName.equals(CELL_12_VOLTAGE)) {
			try {
			mw.centerPanel.cellVoltagePanel.setData(DataPanel.CELL_13_VOLTAGE,
					Float.parseFloat(readFirstLineInFile(pathToFile)));
			} catch (FileNotFoundException e) {
				mw.centerPanel.cellVoltagePanel.setData(DataPanel.CELL_13_VOLTAGE, 0);
			}
			updateBatteryOverallVoltage();
		}
		if (fileName.equals(CELL_13_VOLTAGE)) {
			try {
			mw.centerPanel.cellVoltagePanel.setData(DataPanel.CELL_14_VOLTAGE,
					Float.parseFloat(readFirstLineInFile(pathToFile)));
			} catch (FileNotFoundException e) {
				mw.centerPanel.cellVoltagePanel.setData(DataPanel.CELL_14_VOLTAGE, 0);
			}
			updateBatteryOverallVoltage();
		}
		if (fileName.equals(CELL_14_VOLTAGE)) {
			try {
			mw.centerPanel.cellVoltagePanel.setData(DataPanel.CELL_15_VOLTAGE,
					Float.parseFloat(readFirstLineInFile(pathToFile)));
			} catch (FileNotFoundException e) {
				mw.centerPanel.cellVoltagePanel.setData(DataPanel.CELL_15_VOLTAGE, 0);
			}
			updateBatteryOverallVoltage();
		}
		if (fileName.equals(CELL_15_VOLTAGE)) {
			try {
			mw.centerPanel.cellVoltagePanel.setData(DataPanel.CELL_16_VOLTAGE,
					Float.parseFloat(readFirstLineInFile(pathToFile)));
			} catch (FileNotFoundException e) {
				mw.centerPanel.cellVoltagePanel.setData(DataPanel.CELL_16_VOLTAGE, 0);
			}
			updateBatteryOverallVoltage();
		}
		if (fileName.equals(CELL_16_VOLTAGE)) {
			try {
			mw.centerPanel.cellVoltagePanel.setData(DataPanel.CELL_17_VOLTAGE,
					Float.parseFloat(readFirstLineInFile(pathToFile)));
			} catch (FileNotFoundException e) {
				mw.centerPanel.cellVoltagePanel.setData(DataPanel.CELL_17_VOLTAGE, 0);
			}
			updateBatteryOverallVoltage();
		}
		if (fileName.equals(CELL_17_VOLTAGE)) {
			try {
			mw.centerPanel.cellVoltagePanel.setData(DataPanel.CELL_18_VOLTAGE,
					Float.parseFloat(readFirstLineInFile(pathToFile)));
			} catch (FileNotFoundException e) {
				mw.centerPanel.cellVoltagePanel.setData(DataPanel.CELL_18_VOLTAGE, 0);
			}
			updateBatteryOverallVoltage();
		}
		if (fileName.equals(CELL_18_VOLTAGE)) {
			try {
			mw.centerPanel.cellVoltagePanel.setData(DataPanel.CELL_19_VOLTAGE,
					Float.parseFloat(readFirstLineInFile(pathToFile)));
			} catch (FileNotFoundException e) {
				mw.centerPanel.cellVoltagePanel.setData(DataPanel.CELL_19_VOLTAGE, 0);
			}
			updateBatteryOverallVoltage();
		}
		if (fileName.equals(CELL_19_VOLTAGE)) {
			try {
			mw.centerPanel.cellVoltagePanel.setData(DataPanel.CELL_20_VOLTAGE,
					Float.parseFloat(readFirstLineInFile(pathToFile)));
			} catch (FileNotFoundException e) {
				mw.centerPanel.cellVoltagePanel.setData(DataPanel.CELL_20_VOLTAGE, 0);
			}
			updateBatteryOverallVoltage();
		}
		if (fileName.equals(CELL_20_VOLTAGE)) {
			try {
			mw.centerPanel.cellVoltagePanel.setData(DataPanel.CELL_21_VOLTAGE,
					Float.parseFloat(readFirstLineInFile(pathToFile)));
			} catch (FileNotFoundException e) {
				mw.centerPanel.cellVoltagePanel.setData(DataPanel.CELL_21_VOLTAGE, 0);
			}
			updateBatteryOverallVoltage();
		}
		if (fileName.equals(CELL_21_VOLTAGE)) {
			try {
			mw.centerPanel.cellVoltagePanel.setData(DataPanel.CELL_22_VOLTAGE,
					Float.parseFloat(readFirstLineInFile(pathToFile)));
			} catch (FileNotFoundException e) {
				mw.centerPanel.cellVoltagePanel.setData(DataPanel.CELL_22_VOLTAGE, 0);
			}
			updateBatteryOverallVoltage();
		}
		if (fileName.equals(CELL_22_VOLTAGE)) {
			try {
			mw.centerPanel.cellVoltagePanel.setData(DataPanel.CELL_23_VOLTAGE,
					Float.parseFloat(readFirstLineInFile(pathToFile)));
			} catch (FileNotFoundException e) {
				mw.centerPanel.cellVoltagePanel.setData(DataPanel.CELL_23_VOLTAGE, 0);
			}
			updateBatteryOverallVoltage();
		}
		if (fileName.equals(CELL_23_VOLTAGE)) {
			try {
			mw.centerPanel.cellVoltagePanel.setData(DataPanel.CELL_24_VOLTAGE,
					Float.parseFloat(readFirstLineInFile(pathToFile)));
			} catch (FileNotFoundException e) {
				mw.centerPanel.cellVoltagePanel.setData(DataPanel.CELL_24_VOLTAGE, 0);
			}
			updateBatteryOverallVoltage();
		}
		if (fileName.equals(CHARGING)) {
			try {
			mw.centerPanel.batteryStatus.setData(DataPanel.BATTERY_CHARGING,
					Float.parseFloat(readFirstLineInFile(pathToFile)));
			} catch (FileNotFoundException e) {
				mw.centerPanel.batteryStatus.setData(DataPanel.BATTERY_CHARGING, 0);
			}
		}
		if (fileName.equals(CURRENT)) {
			try {
			mw.centerPanel.batteryStatus.setData(DataPanel.BATTERY_CURRENT,
					Float.parseFloat(readFirstLineInFile(pathToFile)));
			} catch (FileNotFoundException e) {
				mw.centerPanel.batteryStatus.setData(DataPanel.BATTERY_CURRENT, 0);
			}
		}
		if (fileName.equals(PWM_TO_CHARGER)) {
			try {
			mw.centerPanel.batteryStatus.setData(DataPanel.PWM_TO_CHARGER,
					Float.parseFloat(readFirstLineInFile(pathToFile)));
			} catch (FileNotFoundException e) {
				mw.centerPanel.batteryStatus.setData(DataPanel.PWM_TO_CHARGER, 0);
			}
		}
		if (fileName.equals(PWM_TO_ENGINE_CONTROLLER)) {
			try {
			mw.centerPanel.batteryStatus.setData(DataPanel.PWM_TO_ENGINE_CONTROLLER,
					Float.parseFloat(readFirstLineInFile(pathToFile)));
			} catch (FileNotFoundException e) {
				mw.centerPanel.batteryStatus.setData(DataPanel.PWM_TO_ENGINE_CONTROLLER, 0);
			}
		}
		if (fileName.equals(SOC)) {
			try {
			mw.eastPanel.socDisplay.updateDisplay(Integer.parseInt(readFirstLineInFile(pathToFile)));
			} catch (FileNotFoundException e) {
				mw.eastPanel.socDisplay.updateDisplay(0);
			}
		}
		if (fileName.equals(TEMPERATURE_0)) {
			try {
			mw.eastPanel.temperaturesPanel.setData(DataPanel.TEMPERATURE_0,
					Float.parseFloat(readFirstLineInFile(pathToFile)));
			} catch (FileNotFoundException e) {
				mw.eastPanel.temperaturesPanel.setData(DataPanel.TEMPERATURE_0, 0);
			}
		}
		if (fileName.equals(TEMPERATURE_1)) {
			try {
			mw.eastPanel.temperaturesPanel.setData(DataPanel.TEMPERATURE_1,
					Float.parseFloat(readFirstLineInFile(pathToFile)));
			} catch (FileNotFoundException e) {
				mw.eastPanel.temperaturesPanel.setData(DataPanel.TEMPERATURE_1, 0);
			}
		}
		if (fileName.equals(TEMPERATURE_2)) {
			try {
			mw.eastPanel.temperaturesPanel.setData(DataPanel.TEMPERATURE_2,
					Float.parseFloat(readFirstLineInFile(pathToFile)));
			} catch (FileNotFoundException e) {
				mw.eastPanel.temperaturesPanel.setData(DataPanel.TEMPERATURE_2, 0);
			}
		}
		if (fileName.equals(TEMPERATURE_3)) {
			try {
			mw.eastPanel.temperaturesPanel.setData(DataPanel.TEMPERATURE_3,
					Float.parseFloat(readFirstLineInFile(pathToFile)));
			} catch (FileNotFoundException e) {
				mw.eastPanel.temperaturesPanel.setData(DataPanel.TEMPERATURE_3, 0);
			}
		}
	}

	private void updateBatteryOverallVoltage() {
		float bv = 0f;
		float cellMin = mw.centerPanel.cellVoltagePanel.getData(DataPanel.CELL_01_VOLTAGE);
		float cellMax = mw.centerPanel.cellVoltagePanel.getData(DataPanel.CELL_01_VOLTAGE);
		for(String s : cellVoltagesFields) {
			bv += mw.centerPanel.cellVoltagePanel.getData(s);
			if(mw.centerPanel.cellVoltagePanel.getData(s) < cellMin) {
				cellMin = mw.centerPanel.cellVoltagePanel.getData(s);
			}
			if(mw.centerPanel.cellVoltagePanel.getData(s) > cellMax) {
				cellMax = mw.centerPanel.cellVoltagePanel.getData(s);
			}
		}
		mw.centerPanel.batteryStatus.setData(DataPanel.BATTERY_VOLTAGE,
				bv);
		mw.eastPanel.cellVoltagePanel.setData(DataPanel.AVG_CELL_VOLTAGE,
				bv / 24);
		mw.eastPanel.cellVoltagePanel.setData(DataPanel.MIN_CELL_VOLTAGE,
				cellMin);
		mw.eastPanel.cellVoltagePanel.setData(DataPanel.MAX_CELL_VOLTAGE,
				cellMax);
	}
	
	private void lookForFiles() {
		for (String s : files) {
			String path = App.PATH_TO_BMS_FILES + App.SEPARATOR + s;
			if ((new File(path)).exists()) {
				updateFieldContent(s, path);
			}
		}
	}

	@Override
	protected Void doInBackground() throws Exception {

		Path toWatch = Paths.get(App.PATH_TO_BMS_FILES);
		if (toWatch == null) {
			System.err.println("Path to BMS files does not exist!");
			return null;
		}

		WatchService service = null;
		try {
			service = toWatch.getFileSystem().newWatchService();
			toWatch.register(service, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE);

			WatchKey key = null;

			do {
				key = service.take();

				for (WatchEvent<?> e : key.pollEvents()) {
					System.out.println("Received " + e.kind() + " event for file " + e.context() + ".");
					publish(e.context().toString());
				}
				key.reset();
			} while (key != null && !stop);

			service.close();
			
			System.out.println("Watcher service closed.");

		} catch (NoSuchFileException e) {
			mw.displayError("Path to BMS files does not exist!\n" + "Path is: " + App.PATH_TO_BMS_FILES + "\n"
					+ "Please make sure that the path exists and restart the application.");
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (InterruptedException e2) {
			System.err.println(e2.toString());
			if (service != null) {
				service.close();
			}
		}

		return null;
	}

	@Override
	protected void process(List<String> fileName) {
		for (String s : fileName) {
			if (s == null) {
				return;
			}
			String path = App.PATH_TO_BMS_FILES + App.SEPARATOR + s;
//			if ((new File(path)).exists()) {
				updateFieldContent(s, path);
//			}
		}
	}

	public void stopWatching() {
		this.stop = true;
	}

	public String readFirstLineInFile(String pathToFile) throws FileNotFoundException {
		String toReturn = "";
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(new File(pathToFile)));
			toReturn = br.readLine();
		} catch (IOException e) {
			System.err.println(e.toString());
			if(e instanceof FileNotFoundException) {
				if(br != null) {
					try {
						br.close();
					} catch (IOException e1) {
					}
				}
				throw (FileNotFoundException) e;
			}
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
				}
			}
		}
		return toReturn;
	}
}
