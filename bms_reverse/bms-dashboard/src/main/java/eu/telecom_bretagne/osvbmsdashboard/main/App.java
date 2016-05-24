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

import javax.swing.SwingUtilities;

/**
 * @author guillaumelg
 *
 */
public class App {

	public static final String SEPARATOR = System.getProperty("file.separator");
	public static final String PATH = System.getProperty("user.dir");
	public static final String APPLICATION_NAME = "OSV BMS Dashboard v0.1";
	
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

	public static String getImagePath(String relativePathToFile) {
		return Thread.currentThread().getContextClassLoader()
				.getResource(relativePathToFile).getPath();
	}
}
