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

import java.awt.BorderLayout;

/*
 * Copyright 2016 Institut Mines-Télécom
 *
 * This file is part of TB's 151181 dashboard.
 *
 * TB's 151181 dashboard is free software: you can redistribute it
 * and/or modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * TB's 151181 dashboard is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with TB's 151181 dashboard.  If not, see
 * <http://www.gnu.org/licenses/>.
 */

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

public class StateOfChargeDisplay extends JPanel {

	public static final String LABELSTART = "State of Charge (SOC) = ";
	public static final String UNKNOWN = "Unknown";
	private static final long serialVersionUID = -6423671013441933100L;
	private int value;
	JLabel label;
	DisplayImage img;

	MainWindow mw;

	public StateOfChargeDisplay(MainWindow mw) {
		super();
		this.mw = mw;
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder("SOC"));
		img = new DisplayImage(mw);
		JPanel imgPanel = new JPanel();
		imgPanel.add(img);
		imgPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

		label = new JLabel(LABELSTART + UNKNOWN);
		label.setBorder(BorderFactory.createLineBorder(getBackground(), 20));
		JPanel p = new JPanel();
		p.add(label);
		add(p, BorderLayout.SOUTH);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);

		add(imgPanel, BorderLayout.CENTER);
	}

	/**
	 * Update the current displayed value to value.
	 * 
	 * @param value
	 */
	public void updateDisplay(int soc) {
		if (soc >= 0 && soc <= 100) {
			value = soc;
			img.updateValue(value);
			label.setText(LABELSTART + value + "%");
		} else {
			img.updateValue(0);
			label.setText(LABELSTART + UNKNOWN);
			value = -1;
		}
	}

	class DisplayImage extends JPanel {

		private static final long serialVersionUID = 2861368154636660247L;
		MainWindow mw;
		BufferedImage background;
		BufferedImage needle;
		float value = 0;

		public DisplayImage(MainWindow mw) {
			this.mw = mw;
			try {
				background = ImageIO.read(getClass().getResourceAsStream("/Counter_background.png"));
				needle = ImageIO.read(getClass().getResourceAsStream("/Needle.png"));
			} catch (IOException e) {
				System.err.println("Could not load images!!");
				background = new BufferedImage(128, 128, BufferedImage.TYPE_INT_RGB);
				needle = null;
			}

			this.setPreferredSize(new Dimension(background.getWidth(), background.getHeight()));
		}

		private synchronized void updateValue(float value) {
			if (value != this.value) {
				this.value = value;
				this.repaint();
			}
		}

		@Override
		protected void paintComponent(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			super.paintComponent(g2d);
			g2d.drawImage(background, null, 0, 0);
			drawNeedle(g2d);
		}

		private void drawNeedle(Graphics2D g2d) {
			if (needle != null) {
				AffineTransform trans = new AffineTransform();
				trans.translate(needle.getWidth() / 2, needle.getHeight() / 2);
				trans.rotate(Math.toRadians(2.7 * value - 45));
				trans.translate(-needle.getWidth() / 2, -needle.getHeight() / 2);
				g2d.drawImage((Image) needle, trans, this);
			}
		}
	}

	public synchronized int getValue() {
		return this.value;
	}
}
