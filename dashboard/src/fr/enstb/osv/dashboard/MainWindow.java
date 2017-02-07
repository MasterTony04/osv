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
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

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
	public final BufferedImage needle;
	public final BufferedImage counter;
	private MainPanel mainPanel;
	private SettingsPanel settingsPanel;
	public boolean mainPanelSected;

	public MainWindow() throws IOException {
		// load images
		wallpaper = ImageIO.read(getClass().getResourceAsStream("/main_background.png"));
		leaf = ImageIO.read(getClass().getResourceAsStream("/leaf_background.png"));

		iconGps = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/picto/p_map.png")));
		iconGpsBright = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/picto/p_map_bright.png")));
		iconSettings = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/picto/p_settings.png")));
		iconSettingsBright = new ImageIcon(
				ImageIO.read(getClass().getResourceAsStream("/picto/p_settings_bright.png")));
		iconDash = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/picto/p_dashboard.png")));
		iconDashBright = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/picto/p_dashboard_bright.png")));
		iconClose = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/picto/p_close.png")));
		iconCloseBright = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/picto/p_close_bright.png")));

		needle = ImageIO.read(getClass().getResourceAsStream("/needle.png"));
		counter = ImageIO.read(getClass().getResourceAsStream("/counter.png"));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("OSV Dashboard");
		setMinimumSize(new Dimension(640, 360));

		mainPanel = new MainPanel(this);
		settingsPanel = new SettingsPanel(this);

		getContentPane().add(mainPanel);

		setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		// DEBUG
		// setUndecorated(true);

		pack();
		setVisible(true);

	}

	public void switchToSettingsPanel() {
	
		try {
			this.getContentPane().remove(mainPanel);
		} catch (NullPointerException e) {
			return;
		}
		this.getContentPane().add(settingsPanel);
		settingsPanel.screensButtons.get(0).makeSelected(false);
		settingsPanel.screensButtons.get(1).makeSelected(true);
		pack();
		revalidate();
		repaint();
		
		mainPanelSected = false;
	}

	public void switchToMainPanel() {
		try {
			this.getContentPane().remove(settingsPanel);
		} catch (NullPointerException e) {
			return;
		}
		this.getContentPane().removeAll();
		this.getContentPane().add(mainPanel);
		settingsPanel.screensButtons.get(1).makeSelected(false);
		settingsPanel.screensButtons.get(0).makeSelected(true);
		pack();
		revalidate();
		repaint();
		
		mainPanelSected = true;
	}

}
