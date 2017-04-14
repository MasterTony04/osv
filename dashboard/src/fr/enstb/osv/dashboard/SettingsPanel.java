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
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import fr.enstb.osv.dashboard.widgets.OSVBatteryCellWidget;
import fr.enstb.osv.dashboard.widgets.OSVElementStatusWidget;
import fr.enstb.osv.dashboard.widgets.OSVThermoSensorWidget;

/**
 * @author guillaumelg
 *
 */
public class SettingsPanel extends JPanel {

	private static final long serialVersionUID = -1052555126335479979L;
	private JPanel cp;
	private Component verticalSpace;
	private MainWindow mw;
	public List<OSVBatteryCellWidget> batteryCellWidgets;
	public List<OSVThermoSensorWidget> temperatureSensorsWidgets;

	public SettingsPanel(MainWindow mw) {

		this.mw = mw;
		this.setOpaque(false);
		cp = new JPanel();
		cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));
		cp.setOpaque(false);
//		verticalSpace = Box.createVerticalStrut(mw.getHeight() * 3 / 4);
		verticalSpace = Box.createVerticalStrut((int) (mw.getHeight() * 0.45f));
		cp.add(verticalSpace);
		
//		OSVBatteryCellWidget testBattCell = new OSVBatteryCellWidget(mw);
		JPanel p = new JPanel();
		p.setOpaque(false);
		p.add(new OSVElementStatusWidget(mw, mw.iconBattery, mw.iconBatteryRed));
		cp.add(p);
		
		JPanel cellsPanel = new JPanel();
		cellsPanel.setLayout(new GridLayout(6, 4));
		batteryCellWidgets = new ArrayList<OSVBatteryCellWidget>();
		for(int i = 0 ; i < 24 ; i ++) {
			batteryCellWidgets.add(new OSVBatteryCellWidget(mw));
		}
		for(OSVBatteryCellWidget w : batteryCellWidgets) {
			cellsPanel.add(w);
		}
		cp.add(cellsPanel);

		verticalSpace = Box.createVerticalStrut(mw.getHeight() / 8);
		cp.add(verticalSpace);

		JPanel p1 = new JPanel();
		p1.setOpaque(false);
		p1.add(new OSVElementStatusWidget(mw, mw.iconThermo, mw.iconThermoRed));
		cp.add(p1);
		
		
		///////////////////////////
		// TODO
		
		JPanel temperaturesPanel = new JPanel();
		temperaturesPanel.setLayout(new GridLayout(1, 4));
		temperaturesPanel.setOpaque(false);
		temperatureSensorsWidgets = new ArrayList<OSVThermoSensorWidget>();
		for(int i = 0 ; i < 4 ; i ++) {
			temperatureSensorsWidgets .add(new OSVThermoSensorWidget(mw));
		}
		for(OSVThermoSensorWidget w : temperatureSensorsWidgets) {
			temperaturesPanel.add(w);
		}
		cp.add(temperaturesPanel);
		
		verticalSpace = Box.createVerticalStrut(mw.getHeight() / 8);
		cp.add(verticalSpace);
		
		///////////////////////////

		
		add(cp, BorderLayout.CENTER);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
//		super.paintComponents(g);
	}

	public void recalculateDimensions() {
		// TODO Auto-generated method stub
		for(OSVBatteryCellWidget w : batteryCellWidgets) {
			w.calculateWidth();
//			w.repaint();
		}
		for(OSVThermoSensorWidget w : temperatureSensorsWidgets) {
			w.calculateWidth();
//			w.repaint();
		}
	}
}
