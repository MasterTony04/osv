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
import java.util.List;

import javax.swing.SwingWorker;



/**
 * @author guillaumelg
 *
 */
public class OSVDataWatcher extends SwingWorker<Void, String> {

	public static final String speedFile = "/tmp/osvdatasimulator/speed";
	public static final String socFile = "/tmp/osvdatasimulator/soc";

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
		
		String sSpeed = null, sSoc = null;
		
		while(true) {
			key = watchService.take();
			if (key != null) {
				List<WatchEvent<?>> eventList = key.pollEvents();

				for (int i = 0; i < eventList.size(); i++) {

					if (eventList.get(i).context().toString().equals("speed")) {
						
						sSpeed = readFirstLineInFile(speedFile);
						if (sSpeed != null) {
							publish("sp" + sSpeed);
						}
					} else if (eventList.get(i).context().toString().equals("soc")) {
						sSoc =  readFirstLineInFile(socFile);
						if (sSoc != null) {
							publish("so" + sSoc);
						}
					} 
				}
			}
			key.reset();
		}
	}
	
	public void process(List<String> data) {
		for(String s : data) {
			if(s.startsWith("sp")) {
				mw.setSpeed(Float.parseFloat(s.substring(2)));
			} else if (s.startsWith("so")) {
				mw.setSoc(Float.parseFloat(s.substring(2)) / 100);
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
		if((new File(socFile)).exists()) {
			mw.setSoc(Float.parseFloat(readFirstLineInFile(socFile)) / 100);
		}
		if((new File(speedFile)).exists()) {
			mw.setSpeed(Float.parseFloat(readFirstLineInFile(speedFile)));
		}
	}
}
