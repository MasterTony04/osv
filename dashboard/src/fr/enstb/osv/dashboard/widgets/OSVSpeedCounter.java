/*
 * OSVSpeedCounter.java
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
package fr.enstb.osv.dashboard.widgets;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import fr.enstb.osv.dashboard.MainWindow;

/**
 * @author guillaumelg
 *
 */
public class OSVSpeedCounter extends JPanel {

	private static final long serialVersionUID = 3557140821287365255L;
	private MainWindow mw;
	protected volatile float speed = 110;

	public OSVSpeedCounter(MainWindow mw) {
		this.mw = mw;

	}

	@Override
	protected void paintComponent(Graphics g) {
		int ySize = (int) (getParent().getHeight() * 0.72);
		int xSize = mw.counter.getWidth() * ySize / mw.counter.getHeight();
		ImageIcon sizedCounter = new ImageIcon(mw.counter.getScaledInstance(xSize, ySize, Image.SCALE_SMOOTH));
		int x1 = (int) (getWidth() * 0.2);
		int y1 = (int) (mw.getHeight() * 0.14);
		g.drawImage(sizedCounter.getImage(), x1, y1, sizedCounter.getIconWidth(), sizedCounter.getIconHeight(), null);

		ImageIcon sizedNeedle = new ImageIcon(mw.needle.getScaledInstance(xSize, ySize, Image.SCALE_SMOOTH));
		((Graphics2D) g).translate(x1, y1);
		drawNeedle((Graphics2D) g, sizedNeedle);
	}
	
	private void drawNeedle(Graphics2D g2d, ImageIcon needle) {
		if (needle != null) {
			AffineTransform trans = new AffineTransform();
			trans.translate(needle.getIconWidth() / 2, needle.getIconHeight() / 2);
			trans.rotate(Math.toRadians(2.25 * speed - 67.5));
			trans.translate(-needle.getIconWidth() / 2, -needle.getIconHeight() / 2);
			g2d.drawImage(needle.getImage(), trans, this);
		}
	}

	public void setSpeed(float speed) {
		this.speed = speed;
		repaint();
	}

}
