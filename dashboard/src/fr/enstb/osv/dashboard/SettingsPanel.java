/*
 * SettingsPanel.java
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

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import fr.enstb.osv.dashboard.widgets.OSVBatteryCellWidget;
import fr.enstb.osv.dashboard.widgets.OSVElementStatusWidget;
import fr.enstb.osv.dashboard.widgets.OSVTextWidget;
import fr.enstb.osv.dashboard.widgets.OSVThermoSensorWidget;

/**
 * @author guillaumelg
 *
 */
public class SettingsPanel extends JPanel {

	private static final long serialVersionUID = -1052555126335479979L;
	private JPanel cp;
	private Component verticalSpace;
	public List<OSVBatteryCellWidget> batteryCellWidgets;
	public List<OSVThermoSensorWidget> temperatureSensorsWidgets;
	private OSVElementStatusWidget temperatureStatus;
	private OSVElementStatusWidget batteryStatus;
	private OSVTextWidget kilometersWidget;

	public SettingsPanel(MainWindow mw) {

		this.setOpaque(false);
		setLayout(new BorderLayout());
		cp = new JPanel();
		cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));
		cp.setOpaque(false);
		verticalSpace = Box.createVerticalStrut((int) (mw.getHeight() * 0.35f));
		cp.add(verticalSpace);

		kilometersWidget = new OSVTextWidget(mw, 230, "0 km");
		JPanel p2 = new JPanel();
		p2.setOpaque(false);
		p2.add(kilometersWidget);
		cp.add(p2);

		JPanel p = new JPanel();
		p.setOpaque(false);
		batteryStatus = new OSVElementStatusWidget(mw, mw.iconBattery, mw.iconBatteryRed);
		p.add(batteryStatus);
		cp.add(p);

		JPanel cellsPanel = new JPanel();
		cellsPanel.setLayout(new GridLayout(6, 4));
		batteryCellWidgets = new ArrayList<OSVBatteryCellWidget>();
		for (int i = 0; i < 24; i++) {
			batteryCellWidgets.add(new OSVBatteryCellWidget(mw));
		}
		for (OSVBatteryCellWidget w : batteryCellWidgets) {
			cellsPanel.add(w);
		}
		cp.add(cellsPanel);

		JPanel p1 = new JPanel();
		p1.setOpaque(false);
		temperatureStatus = new OSVElementStatusWidget(mw, mw.iconThermo, mw.iconThermoRed);
		p1.add(temperatureStatus);
		cp.add(p1);

		JPanel temperaturesPanel = new JPanel();
		temperaturesPanel.setLayout(new GridLayout(1, 4));
		temperaturesPanel.setOpaque(false);
		temperatureSensorsWidgets = new ArrayList<OSVThermoSensorWidget>();
		for (int i = 0; i < 4; i++) {
			temperatureSensorsWidgets.add(new OSVThermoSensorWidget(mw));
		}
		for (OSVThermoSensorWidget w : temperatureSensorsWidgets) {
			temperaturesPanel.add(w);
		}
		cp.add(temperaturesPanel);

		JPanel p3 = new JPanel();
		p3.setOpaque(false);
		p3.add(cp);
		add(p3);
	}

	public void recalculateDimensions() {
		for (OSVBatteryCellWidget w : batteryCellWidgets) {
			w.calculateWidth();
		}
		for (OSVThermoSensorWidget w : temperatureSensorsWidgets) {
			w.calculateWidth();
		}
	}

	public void setTemperatureStatusWidgetColor() {
		for (OSVThermoSensorWidget w : temperatureSensorsWidgets) {
			if (w.getTemperature() < 5f || w.getTemperature() > 50) {
				temperatureStatus.setStatus(false);
				return;
			}
		}
		temperatureStatus.setStatus(true);
	}

	public void setBatteryStatusWidgetColor() {
		for (OSVBatteryCellWidget w : batteryCellWidgets) {
			if (w.getVoltage() <= 2.8f || w.getVoltage() > 3.75f) {
				batteryStatus.setStatus(false);
				return;
			}
		}
		batteryStatus.setStatus(true);
	}

	public void setTotalDistance(float distance) {
		kilometersWidget.setLabelText(String.format("%.2f kms", distance));
	}
}
