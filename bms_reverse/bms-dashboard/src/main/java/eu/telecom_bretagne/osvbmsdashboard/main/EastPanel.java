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
public class EastPanel extends JPanel {

	private static final long serialVersionUID = 7594905098655010108L;
	private StateOfChargeDisplay socDisplay;
	private DataPanel cellVoltagePanel;
	private DataPanel temperaturesPanel;

	public EastPanel(MainWindow mw) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		socDisplay = new StateOfChargeDisplay(mw);
		add(socDisplay);
		cellVoltagePanel = new DataPanel(3, 1, "Cells voltages");
		cellVoltagePanel.setData(DataPanel.MIN_CELL_VOLTAGE, 0);
		cellVoltagePanel.setData(DataPanel.MAX_CELL_VOLTAGE, 0);
		cellVoltagePanel.setData(DataPanel.AVG_CELL_VOLTAGE, 0);
		add(cellVoltagePanel);
		temperaturesPanel = new DataPanel(2, 2, "Temperatures");
		temperaturesPanel.setData(DataPanel.TEMPERATURE_0, 0);
		temperaturesPanel.setData(DataPanel.TEMPERATURE_1, 0);
		temperaturesPanel.setData(DataPanel.TEMPERATURE_2, 0);
		temperaturesPanel.setData(DataPanel.TEMPERATURE_3, 0);
		add(temperaturesPanel);
	}
	
}
