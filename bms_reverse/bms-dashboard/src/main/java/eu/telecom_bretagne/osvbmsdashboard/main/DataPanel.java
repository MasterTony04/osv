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

import java.awt.GridLayout;
import java.util.Hashtable;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author guillaumelg
 *
 */
public class DataPanel extends JPanel {

	private static final long serialVersionUID = -4245823017734782548L;

	public static final String TEMPERATURE_0 = "Temperature 0 (°C): ";
	public static final String TEMPERATURE_1 = "Temperature 1 (°C): ";
	public static final String TEMPERATURE_2 = "Temperature 2 (°C): ";
	public static final String TEMPERATURE_3 = "Temperature BMS (°C): ";
	public static final String MIN_CELL_VOLTAGE = "Min. cell Voltage (V): ";
	public static final String MAX_CELL_VOLTAGE = "Max. cell Voltage (V): ";
	public static final String AVG_CELL_VOLTAGE = "Avg. cell Voltage (V): ";
	public static final String CELL_01_VOLTAGE = "Cell 01 voltage (V): ";
	public static final String CELL_02_VOLTAGE = "Cell 02 voltage (V): ";
	public static final String CELL_03_VOLTAGE = "Cell 03 voltage (V): ";
	public static final String CELL_04_VOLTAGE = "Cell 04 voltage (V): ";
	public static final String CELL_05_VOLTAGE = "Cell 05 voltage (V): ";
	public static final String CELL_06_VOLTAGE = "Cell 06 voltage (V): ";
	public static final String CELL_07_VOLTAGE = "Cell 07 voltage (V): ";
	public static final String CELL_08_VOLTAGE = "Cell 08 voltage (V): ";
	public static final String CELL_09_VOLTAGE = "Cell 09 voltage (V): ";
	public static final String CELL_10_VOLTAGE = "Cell 10 voltage (V): ";
	public static final String CELL_11_VOLTAGE = "Cell 11 voltage (V): ";
	public static final String CELL_12_VOLTAGE = "Cell 12 voltage (V): ";
	public static final String CELL_13_VOLTAGE = "Cell 13 voltage (V): ";
	public static final String CELL_14_VOLTAGE = "Cell 14 voltage (V): ";
	public static final String CELL_15_VOLTAGE = "Cell 15 voltage (V): ";
	public static final String CELL_16_VOLTAGE = "Cell 16 voltage (V): ";
	public static final String CELL_17_VOLTAGE = "Cell 17 voltage (V): ";
	public static final String CELL_18_VOLTAGE = "Cell 18 voltage (V): ";
	public static final String CELL_19_VOLTAGE = "Cell 19 voltage (V): ";
	public static final String CELL_20_VOLTAGE = "Cell 20 voltage (V): ";
	public static final String CELL_21_VOLTAGE = "Cell 21 voltage (V): ";
	public static final String CELL_22_VOLTAGE = "Cell 22 voltage (V): ";
	public static final String CELL_23_VOLTAGE = "Cell 23 voltage (V): ";
	public static final String CELL_24_VOLTAGE = "Cell 24 voltage (V): ";
	public static final String BATTERY_CURRENT = "Battery current (A): ";
	public static final String BATTERY_VOLTAGE = "Battery voltage (V): ";
	public static final String BATTERY_CHARGING = "Charging: ";
	public static final String BATTERY_BALANCING = "Balancing: ";
	public static final String PWM_TO_CHARGER = "PWM to Charger (% duty-cycle): ";
	public static final String PWM_TO_ENGINE_CONTROLLER = "PWM to Engine Controller (% duty-cycle): ";

	private Map<String, DataPanel.DataField> dataFields = new Hashtable<String, DataPanel.DataField>();

	public DataPanel(int rows, int columns, String title) {
		setLayout(new GridLayout(rows, columns * 2));
		setBorder(BorderFactory.createTitledBorder(title));
	}

	public void setData(String name, float value) {
		DataField df = dataFields.get(name);
		if (df == null) {
			df = new DataField(name, value);
			dataFields.put(name, df);
			add(df.nameP);
			add(df.valueP);
		} else {
			df.setValue(value);
		}
	}

	public void removeData(String name) {
		DataField df = dataFields.remove(name);
		if (df != null) {
			remove(df.nameP);
			remove(df.valueP);
		}
	}

	class DataField {
		
		private JPanel valueP = new JPanel();
		private JPanel nameP = new JPanel();
		private JLabel valueL;
		private JLabel nameL;

		public DataField(String name, float value) {
			nameL = new JLabel(name);
			valueL = new JLabel(Float.toString(value));
			nameP.add(nameL);
			valueP.add(valueL);
		}

		public void setValue(float value) {
			this.valueL.setText(Float.toString(value));
		}
		
		public float getFieldValue() {
			return Float.parseFloat(valueL.getText());
		}
	}

	public float getData(String fieldName) {
		return dataFields.get(fieldName).getFieldValue();
	}
}
