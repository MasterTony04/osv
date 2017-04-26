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

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import fr.enstb.osv.dashboard.components.OSVEmptyPanel;

/**
 * @author guillaumelg
 *
 */
public class MapPanel extends JPanel {

	private static final long serialVersionUID = -1052555126335479979L;
	private JPanel cp;
	private OSVEmptyPanel verticalSpace;
	private MainWindow mw;
	MapDisplay mapDisplay;

	public MapPanel(MainWindow mw) {

		this.mw = mw;
		this.setOpaque(false);

		cp = new JPanel();
		cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));
		cp.setOpaque(false);
		verticalSpace = new OSVEmptyPanel(mw, 0, 0.28f);
		cp.add(verticalSpace);

		mapDisplay = new MapDisplay();
		mapDisplay.setOpaque(false);

		cp.add(mapDisplay);

		add(cp);
	}

	public void recalculateDimensions() {
		verticalSpace.calculateDimensions();
		mapDisplay.calculateWidth();
	}

	class MapDisplay extends JPanel {

		private static final long serialVersionUID = 2162625690520903862L;

		public MapDisplay() {
			setPreferredSize(new Dimension(mw.map.getWidth(), mw.map.getHeight()));
		}

		public void calculateWidth() {
			int width = (int) (mw.getWidth() * 0.5);
			int height = (int) (mw.map.getHeight() * width / mw.map.getWidth());
			setMinimumSize(new Dimension(width, height));
			setPreferredSize(new Dimension(width, height));
		}

		@Override
		protected void paintComponent(Graphics g) {
			calculateWidth();

			Graphics2D g2d = (Graphics2D) g;

			int x = (int) (getWidth() * 0.8f);
			int y = (int) (getHeight() * 0.8f);

			Image sizedMap = mw.map.getScaledInstance(x, y, Image.SCALE_SMOOTH);
			
			int x1 = (int) (getWidth() * 0.167f);
			
			g2d.drawImage(sizedMap, x1, 0, x, y, null);
		}
	}
}
