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
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import fr.enstb.osv.dashboard.MainWindow;
import fr.enstb.osv.dashboard.widgets.OSVBasicTextWidget;
import fr.enstb.osv.dashboard.widgets.OSVBatteryWidget;
import fr.enstb.osv.dashboard.widgets.OSVDateWidget;

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
	public enum ENUM_BUTTON_FUNCTIONALITY {MAIN_PANEL, SETTINGS_PANEL};

	public OSVPanel(MainWindow mw) {
		this.mw = mw;
		
		buildScreensButtons();
		
		this.setLayout(new BorderLayout());
		
		leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		verticalSpaceLeftPanel = Box.createVerticalStrut(mw.getHeight() / 4);
		leftPanel.add(verticalSpaceLeftPanel);
		leftPanel.add(new OSVDateWidget(mw));
		for(OSVToggleButton b : screensButtons) {
			JPanel p = new JPanel();
			p.add(b);
			leftPanel.add(p);
		}
		leftPanel.setOpaque(false);
		this.add(leftPanel, BorderLayout.WEST);

		this.add(new OSVBatteryWidget(mw), BorderLayout.EAST);
		
		JPanel p3  = new JPanel();
		p3.setLayout(new BoxLayout(p3, BoxLayout.X_AXIS));
		p3.add(new OSVBasicTextWidget(mw));
		this.add(p3, BorderLayout.SOUTH);
	}

	private void buildScreensButtons() {
		screensButtons = new ArrayList<OSVToggleButton>();
		
		OSVToggleButton mainB = new OSVToggleButton(mw.iconDash, mw.iconDashBright);
//		mainB.addActionListener(new ScreenChangeListener(mainB));
		mainB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(screensButtons.get(0).isSelected()) {
					return;
				}
					
				mw.switchToMainPanel();
			}
		});
		
//		OSVToggleButton gpsB = new OSVToggleButton(mw.iconGps, mw.iconGpsBright);
		OSVToggleButton settingsB = new OSVToggleButton(mw.iconSettings, mw.iconSettingsBright);
//		settingsB.addActionListener(new ScreenChangeListener(settingsB));
		settingsB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(screensButtons.get(1).isSelected()) {
					return;
				}
				mw.switchToSettingsPanel();
			}
		});
		screensButtons.add(mainB);
		screensButtons.add(settingsB);
	}
	
//	private class ScreenChangeListener implements ActionListener {
//		private OSVToggleButton b;
//		private ENUM_BUTTON_FUNCTIONALITY f;
//
//		ScreenChangeListener (OSVToggleButton b, ENUM_BUTTON_FUNCTIONALITY f) {
//			this.b = b;
//			this.f = f;
//		}
//		
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			for(OSVToggleButton b : screensButtons) {
//				if(!b.equals(this.b)) {
//					b.makeSelected(false);
//				}
//			}
//			switch 
//		}
//	}

	@Override
	protected void paintComponent(Graphics g) {
		leftPanel.remove(verticalSpaceLeftPanel);
		verticalSpaceLeftPanel = Box.createVerticalStrut(mw.getHeight() / 3);
		leftPanel.add(verticalSpaceLeftPanel, 0);
		
		super.paintComponent(g);

		g.drawImage(mw.wallpaper.getScaledInstance(mw.getWidth(), mw.getHeight(), Image.SCALE_SMOOTH), 0, 0,
				mw.getWidth(), mw.getHeight(), null);
	}
}
