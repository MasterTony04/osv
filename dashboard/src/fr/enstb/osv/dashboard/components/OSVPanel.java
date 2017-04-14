/*
 * OSVPanel.java
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

package fr.enstb.osv.dashboard.components;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import fr.enstb.osv.dashboard.MainWindow;
import fr.enstb.osv.dashboard.MapPanel;
import fr.enstb.osv.dashboard.SettingsPanel;
import fr.enstb.osv.dashboard.widgets.OSVBasicTextWidget;
import fr.enstb.osv.dashboard.widgets.OSVBatteryWidget;
import fr.enstb.osv.dashboard.widgets.OSVDateWidget;
import fr.enstb.osv.dashboard.widgets.OSVSpeedCounter;

/**
 * @author guillaumelg
 *
 */
public class OSVPanel extends JPanel {

	private static final long serialVersionUID = 8572357445596859917L;
	protected MainWindow mw;
	public List<OSVToggleButton> screensButtons;
	JPanel leftPanel;
	private Component verticalSpaceLeftPanel;
	public OSVBatteryWidget batteryWidget;
	public OSVBasicTextWidget textWidget;
	public OSVSpeedCounter speedCounter;
	private MapPanel mapPanel;
	private OSVToggleButton mainB;
	private OSVToggleButton mapB;
	private OSVToggleButton settingsB;
	private SettingsPanel settingsPanel;
	private Component verticalSpaceLeftPanel2;

	JPanel bottomPanel;
	JPanel bp1;
	JPanel bp3;
	OSVButton exitButton;

	public enum ENUM_BUTTON_FUNCTIONALITY {
		MAIN_PANEL, SETTINGS_PANEL
	};

	public OSVPanel(MainWindow mw) {
		this.mw = mw;

		buildScreensButtons();

		this.setLayout(new BorderLayout());
		setOpaque(false);

		leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		verticalSpaceLeftPanel = Box.createVerticalStrut(mw.getHeight() / 2);
		leftPanel.add(verticalSpaceLeftPanel, 0);
		leftPanel.add(new OSVDateWidget(mw), 1);
		verticalSpaceLeftPanel2 = Box.createVerticalStrut(mw.getHeight() / 4);
		leftPanel.add(verticalSpaceLeftPanel2, 2);
		for (OSVToggleButton b : screensButtons) {
			JPanel p = new JPanel();
			p.add(b);
			leftPanel.add(p);
		}
		leftPanel.setOpaque(false);
		this.add(leftPanel, BorderLayout.WEST);

		batteryWidget = new OSVBatteryWidget(mw);
		this.add(batteryWidget, BorderLayout.EAST);

		bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(1, 3));

		exitButton = new OSVButton(mw.iconClose, mw.iconCloseBright);
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		bp1 = new JPanel();
		bp1.setOpaque(false);
		// bp1.add(exit);
		bottomPanel.add(bp1);
		bottomPanel.setOpaque(false);

		textWidget = new OSVBasicTextWidget(mw);
		bottomPanel.add(textWidget);
		bp3 = new JPanel();
		bp3.setOpaque(false);
		bottomPanel.add(bp3);
		this.add(bottomPanel, BorderLayout.SOUTH);

		screensButtons.get(0).makeSelected(true);

		speedCounter = new OSVSpeedCounter(mw);
		mapPanel = new MapPanel(mw);
		settingsPanel = new SettingsPanel(mw);

		add(speedCounter, BorderLayout.CENTER);
	}

	private void buildScreensButtons() {
		screensButtons = new ArrayList<OSVToggleButton>();

		mainB = new OSVToggleButton(mw.iconDash, mw.iconDashBright);
		mainB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (screensButtons.get(0).isSelected) {
					return;
				}
				mainB.makeSelected(true);
				mapB.makeSelected(false);
				settingsB.makeSelected(false);
				switchToSpeedCounter();
			}
		});

		mapB = new OSVToggleButton(mw.iconGps, mw.iconGpsBright);
		mapB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (screensButtons.get(1).isSelected) {
					return;
				}
				mainB.makeSelected(false);
				mapB.makeSelected(true);
				settingsB.makeSelected(false);
				switchToMap();
			}
		});

		settingsB = new OSVToggleButton(mw.iconSettings, mw.iconSettingsBright);
		settingsB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (screensButtons.get(2).isSelected) {
					return;
				}
				mainB.makeSelected(false);
				mapB.makeSelected(false);
				settingsB.makeSelected(true);
				switchToSettings();
			}
		});
		screensButtons.add(mainB);
		screensButtons.add(mapB);
		screensButtons.add(settingsB);
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		g.drawImage(mw.wallpaper.getScaledInstance(mw.getWidth(), mw.getHeight(), Image.SCALE_SMOOTH), 0, 0,
				mw.getWidth(), mw.getHeight(), null);
	}

	private void switchToSpeedCounter() {
		remove(mapPanel);
		remove(settingsPanel);
		add(speedCounter, BorderLayout.CENTER);
		bp1.remove(exitButton);
		revalidate();
		repaint();
	}

	private void switchToMap() {
		remove(speedCounter);
		remove(settingsPanel);
		add(mapPanel, BorderLayout.CENTER);
		bp1.remove(exitButton);
		revalidate();
		repaint();
	}

	private void switchToSettings() {
		remove(speedCounter);
		remove(mapPanel);
		add(settingsPanel, BorderLayout.CENTER);
		settingsPanel.recalculateDimensions();
		bp1.add(exitButton);
		revalidate();
		repaint();
	}
}
