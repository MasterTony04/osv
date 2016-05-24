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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.SwingUtilities;

/**
 * @author guillaumelg
 *
 */
public class App {

	public static final String SEPARATOR = System.getProperty("file.separator");
	public static final String PATH = System.getProperty("user.dir");
	public static final String APPLICATION_NAME = "OSV BMS Dashboard v0.1";
	public static final String TMP = System.getProperty("java.io.tmpdir");
	
	public static String PATH_TO_BMS_FILES = TMP + SEPARATOR + "osv-bms";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				(new MainWindow()).display();
			}
		});
	}
	
	public static String readFile(String pathToFile) {
		BufferedReader br = null;
		StringBuffer content = new StringBuffer();
		try {
			br = new BufferedReader(new FileReader(new File(pathToFile)));
			String s;
			while((s = br.readLine()) != null) {
				content.append(s);
				content.append("\n");
			}
		} catch (IOException e) {
			System.out.println(e.toString());
		} finally {
			if(br != null) {
				try {
					br.close();
				} catch (IOException e) {}
			}
		}
		
		return content.toString();
	}
	
	public static void writeToFile(String pathToFile, String toWrite) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new PrintWriter(new File(pathToFile)));
			bw.write(toWrite);
		}  catch (IOException e) {
			System.out.println(e.toString());
		} finally {
			if(bw != null) {
				try {
					bw.close();
				} catch (IOException e) {}
			}
		}
	}
}
