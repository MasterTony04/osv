/*
 * OSVBatteryCellWidget.java
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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.enstb.osv.dashboard.MainWindow;
import fr.enstb.osv.dashboard.OSVException;
import fr.enstb.osv.dashboard.components.OSVColors;

/**
 * @author guillaumelg
 *
 */
public class OSVBatteryCellWidget extends JPanel {

	private static final long serialVersionUID = 6500412900180905722L;
	private MainWindow mw;
	private JLabel voltageL;
	private Font font;
	private float voltage;

	public OSVBatteryCellWidget(MainWindow mw) {
		this.mw = mw;
		voltageL = new JLabel();
		voltageL.setForeground(OSVColors.GREY_3);
		voltageL.setFont(new Font(voltageL.getFont().getName(), Font.PLAIN, 24));
		font = getFont();

//		calculateWidth();

		try {
			setVoltage(0.1f);
		} catch (OSVException e) {
		}
	}

	public void calculateWidth() {
		int width = (int) (mw.getWidth() * 0.08);
		int height = width / 3;
		setMinimumSize(new Dimension(width, height));
		setPreferredSize(new Dimension(width, height));
	}

	@Override
	protected void paintComponent(Graphics g) {
		calculateWidth();

		int x = getWidth();
		int y = getHeight();
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(OSVColors.GREY_2);
		int offX = x / 10;
		int offY = y / 10;
		int width = x * 8 / 10;
		int height = y * 8 / 10;
		g2d.fillRect(offX, offY, width, height);

		// setForeground(Color.BLUE);
		g2d.setColor(OSVColors.GREY_3);
		float yF = width / 6;
		g2d.setFont(font.deriveFont(yF));

		FontMetrics fm = getFontMetrics(g2d.getFont());
		int xF = fm.stringWidth(voltageL.getText());
		int xFPos = offX + width / 3 - xF / 2;
		int yFPos = (int) (offY + height / 2 + yF / 2) - 2;
		g2d.drawString(voltageL.getText(), xFPos, yFPos);

		// Draw circle.
		float diameter = width / 5f;
		float xC = xF + xFPos + width / 6f;
		float yC = (height) / 2f - diameter / 2f + offY;

		Ellipse2D.Double circle = new Ellipse2D.Double(xC, yC, diameter, diameter);
		g2d.fill(circle);

		diameter = width / 7;
		xC += (width / 5f - width / 7f) / 2 + 0.4f;
		yC += (width / 5f - width / 7f) / 2 + 0.4f;
		g2d.setColor(getCircleColor());
		g2d.fill(new Ellipse2D.Double(xC, yC, diameter, diameter));
	}

	private Color getCircleColor() {
		// Gre 99 212 16
		// Red 212 16 16
		if (this.voltage <= 2.6f || voltage >= 3.75f) {
			return OSVColors.RED_1;
		}
		float r, g, b;
		if (voltage <= 3.175) {
			r = 212f;
			g = 196.52f * voltage - 411.95f;
		} else {
			r = -340.87f * voltage + 1294.26f;
			g = 212f;
		}
		b = 16f;
		return new Color((int) r, (int) g, (int) b);
	}

	public void setVoltage(float voltage) throws OSVException {
		if (voltage == this.voltage) {
			return;
		}

		if (voltage < 0f || voltage > 4.0f) {
			throw new OSVException("Wrong voltage value: " + voltage);
		}

		this.voltage = voltage;

		voltageL.setText(String.format("%.2f", this.voltage) + " V");

		repaint();
	}

	public float getVoltage() {
		return voltage;
	}
}
