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
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import fr.enstb.osv.dashboard.components.OSVButton;
import fr.enstb.osv.dashboard.components.OSVPanel;

/**
 * @author guillaumelg
 *
 */
public class MapPanel extends OSVPanel {

	private static final long serialVersionUID = -1052555126335479979L;
	private JPanel cp;
	private Component verticalSpace;

	public MapPanel(MainWindow mw) {
		super(mw);

		cp = new JPanel();
		cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));
		cp.setOpaque(false);
		verticalSpace = Box.createVerticalStrut(mw.getHeight() / 2);
		cp.add(verticalSpace);

		
		
		JPanel cp1 = new MapDisplay();
//		cp1.setOpaque(false);
		
//		cp1.add(new ImageIcon(mw.map));
		
		cp.add(cp1);
		
		add(cp, BorderLayout.CENTER);
	}
	
	class MapDisplay extends JPanel {

		private static final long serialVersionUID = 2162625690520903862L;

		public MapDisplay() {
			setPreferredSize(new Dimension(mw.map.getWidth(), mw.map.getHeight()));
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			int ySize = (int) (getParent().getHeight() * 0.6);
			int xSize = mw.map.getWidth() * ySize / mw.map.getHeight();
			ImageIcon sizedMap = new ImageIcon(mw.map.getScaledInstance(xSize, ySize, Image.SCALE_SMOOTH));
			int x1 = (int) (getWidth() * 0.25);
			int y1 = (int) (mw.getHeight() * 0.14);
			g.drawImage(sizedMap.getImage(), x1, y1, sizedMap.getIconWidth(), sizedMap.getIconHeight(), null);
		}
	}
	
//	@Override
//	protected void paintComponent(Graphics g) {
//		
////		cp.remove(verticalSpace);
////		verticalSpace = Box.createVerticalStrut(mw.getHeight() / 4);
////		cp.add(verticalSpace, 0);
//		
//		super.paintComponents(g);
//	}

}
