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

import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * @author guillaumelg
 *
 */
public class CenterPanel extends JPanel {

	private static final long serialVersionUID = -5398304583015651658L;
	DataPanel cellVoltagePanel;
	DataPanel batteryStatus;
	
	public CenterPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		cellVoltagePanel = new DataPanel(8, 3, "Cells voltages");
		cellVoltagePanel.setData(DataPanel.CELL_01_VOLTAGE, 0);
		cellVoltagePanel.setData(DataPanel.CELL_09_VOLTAGE, 0);
		cellVoltagePanel.setData(DataPanel.CELL_17_VOLTAGE, 0);
		cellVoltagePanel.setData(DataPanel.CELL_02_VOLTAGE, 0);
		cellVoltagePanel.setData(DataPanel.CELL_10_VOLTAGE, 0);
		cellVoltagePanel.setData(DataPanel.CELL_18_VOLTAGE, 0);
		cellVoltagePanel.setData(DataPanel.CELL_03_VOLTAGE, 0);
		cellVoltagePanel.setData(DataPanel.CELL_11_VOLTAGE, 0);
		cellVoltagePanel.setData(DataPanel.CELL_19_VOLTAGE, 0);
		cellVoltagePanel.setData(DataPanel.CELL_04_VOLTAGE, 0);
		cellVoltagePanel.setData(DataPanel.CELL_12_VOLTAGE, 0);
		cellVoltagePanel.setData(DataPanel.CELL_20_VOLTAGE, 0);
		cellVoltagePanel.setData(DataPanel.CELL_05_VOLTAGE, 0);
		cellVoltagePanel.setData(DataPanel.CELL_13_VOLTAGE, 0);
		cellVoltagePanel.setData(DataPanel.CELL_21_VOLTAGE, 0);
		cellVoltagePanel.setData(DataPanel.CELL_06_VOLTAGE, 0);
		cellVoltagePanel.setData(DataPanel.CELL_14_VOLTAGE, 0);
		cellVoltagePanel.setData(DataPanel.CELL_22_VOLTAGE, 0);
		cellVoltagePanel.setData(DataPanel.CELL_07_VOLTAGE, 0);
		cellVoltagePanel.setData(DataPanel.CELL_15_VOLTAGE, 0);
		cellVoltagePanel.setData(DataPanel.CELL_23_VOLTAGE, 0);
		cellVoltagePanel.setData(DataPanel.CELL_08_VOLTAGE, 0);
		cellVoltagePanel.setData(DataPanel.CELL_16_VOLTAGE, 0);
		cellVoltagePanel.setData(DataPanel.CELL_24_VOLTAGE, 0);
		add(cellVoltagePanel);
		batteryStatus = new DataPanel(2, 2, "Overall battery status");
		batteryStatus.setData(DataPanel.BATTERY_CURRENT, 0);
		batteryStatus.setData(DataPanel.BATTERY_VOLTAGE, 0);
		batteryStatus.setData(DataPanel.BATTERY_CHARGING, 0);
		batteryStatus.setData(DataPanel.BATTERY_BALANCING, 0);
		add(batteryStatus);
	}
}
