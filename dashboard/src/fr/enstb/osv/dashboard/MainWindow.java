/*
 * MainWindow.java
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

import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import fr.enstb.osv.dashboard.OSVPanel.ENUM_OSV_PANEL;

/**
 * @author guillaumelg
 *
 */
public class MainWindow extends JFrame {

	private static final long serialVersionUID = -4133091438139234240L;
	public final BufferedImage wallpaper;
	public final BufferedImage leaf;
	public final ImageIcon iconGps;
	public final ImageIcon iconSettings;
	public final ImageIcon iconDash;
	public final ImageIcon iconGpsBright;
	public final ImageIcon iconSettingsBright;
	public final ImageIcon iconDashBright;
	public final ImageIcon iconClose;
	public final ImageIcon iconCloseBright;
	public final BufferedImage iconBattery;
	public final BufferedImage iconBatteryRed;
	public final BufferedImage iconThermo;
	public final BufferedImage iconThermoRed;
	public final BufferedImage needle;
	public final BufferedImage counter;
	public final BufferedImage map;
	public final BufferedImage chargeLightning;
	private OSVPanel osvPanel;

	OSVDataWatcher dataWatcher;
	public BufferedImage chargeLightning0;
	public BufferedImage chargeLightning1;
	public BufferedImage chargeLightning2;
	public BufferedImage chargeLightning3;
	public BufferedImage chargeLightning4;
	public BufferedImage chargeLightning5;
	public BufferedImage chargeLightning6;
	public BufferedImage chargeLightning7;
	public BufferedImage chargeLightning8;
	public BufferedImage chargeLightning9;
	private float evSpeed = 0.0f;

	public MainWindow() throws IOException {
		// load images
		wallpaper = ImageIO.read(getClass().getResourceAsStream("/main_background.png"));
		leaf = ImageIO.read(getClass().getResourceAsStream("/leaf_background.png"));
		map = ImageIO.read(getClass().getResourceAsStream("/map.png"));

		iconGps = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/picto/p_map.png")));
		iconGpsBright = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/picto/p_map_bright.png")));
		iconSettings = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/picto/p_settings.png")));
		iconSettingsBright = new ImageIcon(
				ImageIO.read(getClass().getResourceAsStream("/picto/p_settings_bright.png")));
		iconDash = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/picto/p_dashboard.png")));
		iconDashBright = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/picto/p_dashboard_bright.png")));
		iconClose = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/picto/p_close.png")));
		iconCloseBright = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/picto/p_close_bright.png")));

		iconBattery = ImageIO.read(getClass().getResourceAsStream("/picto/p_battery.png"));
		iconBatteryRed = ImageIO.read(getClass().getResourceAsStream("/picto/p_battery_red.png"));
		iconThermo = ImageIO.read(getClass().getResourceAsStream("/picto/p_thermo.png"));
		iconThermoRed = ImageIO.read(getClass().getResourceAsStream("/picto/p_thermo_red.png"));

		needle = ImageIO.read(getClass().getResourceAsStream("/needle.png"));
		counter = ImageIO.read(getClass().getResourceAsStream("/counter.png"));
		chargeLightning = ImageIO.read(getClass().getResourceAsStream("/charge_lightning.png"));
		chargeLightning0 = ImageIO.read(getClass().getResourceAsStream("/charge_lightning/charge_lightning_0.png"));
		chargeLightning1 = ImageIO.read(getClass().getResourceAsStream("/charge_lightning/charge_lightning_1.png"));
		chargeLightning2 = ImageIO.read(getClass().getResourceAsStream("/charge_lightning/charge_lightning_2.png"));
		chargeLightning3 = ImageIO.read(getClass().getResourceAsStream("/charge_lightning/charge_lightning_3.png"));
		chargeLightning4 = ImageIO.read(getClass().getResourceAsStream("/charge_lightning/charge_lightning_4.png"));
		chargeLightning5 = ImageIO.read(getClass().getResourceAsStream("/charge_lightning/charge_lightning_5.png"));
		chargeLightning6 = ImageIO.read(getClass().getResourceAsStream("/charge_lightning/charge_lightning_6.png"));
		chargeLightning7 = ImageIO.read(getClass().getResourceAsStream("/charge_lightning/charge_lightning_7.png"));
		chargeLightning8 = ImageIO.read(getClass().getResourceAsStream("/charge_lightning/charge_lightning_8.png"));
		chargeLightning9 = ImageIO.read(getClass().getResourceAsStream("/charge_lightning/charge_lightning_9.png"));

		// Set up frame.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("OSV Dashboard");
		setMinimumSize(new Dimension(640, 360));
		// DEBUG
		 setPreferredSize(new Dimension(1366, 768));

		osvPanel = new OSVPanel(this);
		getContentPane().add(osvPanel);

//		setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		// setUndecorated(true);

		MainWindowListener mwl = new MainWindowListener(this);
		addWindowStateListener(mwl);
		addComponentListener(mwl);

		pack();
		setVisible(true);
	}

	public void setSoc(float f) {
		try {
			osvPanel.batteryWidget.setSoc(f);
		} catch (OSVException e) {
			System.err.println(e.toString());
		}
	}

	public void setSpeed(float i) {
		this.evSpeed  = i;
		osvPanel.textWidget.setLabelText((int) i + " km/h");
		if (osvPanel.selectedPanel == ENUM_OSV_PANEL.MAIN_PANEL) {
			osvPanel.speedCounter.setSpeed(i);
		}
	}

	public void setTemperature(int index, float value) {
//		if (osvPanel.selectedPanel == ENUM_OSV_PANEL.SETTINGS_PANEL) {
			if (index < 0 || index > 3) {
				System.err.println("Temperature index must be in [0 ; 3].");
				return;
			}
			try {
				osvPanel.getSettingsPanel().temperatureSensorsWidgets.get(index).setTemperature(value);
				osvPanel.getSettingsPanel().setTemperatureStatusWidgetColor();
			} catch (OSVException e) {
				System.err.println(e.toString());
			}
//		}
	}

	public void setCellVoltage(int index, float value) {
//		if (osvPanel.selectedPanel == ENUM_OSV_PANEL.SETTINGS_PANEL) {
			if (index < 0 || index > 23) {
				System.err.println("Cell index must be in [0 ; 23].");
				return;
			}
			try {
				osvPanel.getSettingsPanel().batteryCellWidgets.get(index).setVoltage(value);
				osvPanel.getSettingsPanel().setBatteryStatusWidgetColor();
			} catch (OSVException e) {
				System.err.println(e.toString());
			}
//		}
	}

	private class MainWindowListener implements WindowStateListener, ComponentListener {
		private MainWindow mw;

		public MainWindowListener(MainWindow mw) {
			this.mw = mw;
		}

		@Override
		public void windowStateChanged(WindowEvent e) {
			componentResized(null);
		}

		@Override
		public void componentResized(ComponentEvent e) {
			switch (mw.osvPanel.selectedPanel) {
			case MAIN_PANEL:
				mw.osvPanel.switchToSpeedCounter();
				break;
			case MAP_PANEL:
				mw.osvPanel.switchToMap();
				break;
			case SETTINGS_PANEL:
				mw.osvPanel.switchToSettings();
				break;
			}
		}

		@Override
		public void componentMoved(ComponentEvent e) {
		}

		@Override
		public void componentShown(ComponentEvent e) {
		}

		@Override
		public void componentHidden(ComponentEvent e) {
		}
	}

	public void setIsCharging(boolean b) {
		osvPanel.batteryWidget.setIsCharging(b);
	}

	public void setTotalDistance(float distance) {
//		if (osvPanel.selectedPanel == ENUM_OSV_PANEL.SETTINGS_PANEL) {
			osvPanel.getSettingsPanel().setTotalDistance(distance);
//		}
	}

	public float getEVSpeed() {
		return this.evSpeed;
	}

}
