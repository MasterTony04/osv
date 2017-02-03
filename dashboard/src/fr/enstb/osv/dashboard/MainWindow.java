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
	public final BufferedImage needle;
	public final BufferedImage counter;
	private MainPanel mainPanel;

	public MainWindow() throws IOException {
		// load images
		wallpaper = ImageIO.read(getClass().getResourceAsStream("/main_background.png"));
		leaf = ImageIO.read(getClass().getResourceAsStream("/leaf_background.png"));

		iconGps = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/picto/p_map.png")));
		iconSettings = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/picto/p_settings.png")));
		iconDash = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/picto/p_dashboard.png")));

		needle = ImageIO.read(getClass().getResourceAsStream("/needle.png"));
		counter = ImageIO.read(getClass().getResourceAsStream("/counter.png"));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("OSV Dashboard");

		mainPanel = new MainPanel(this);
		
		getContentPane().add(mainPanel);
		
		setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		
		pack();
		setVisible(true);

	}

}
