/*
 * OSVThermoSensorWidget.java
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

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.enstb.osv.dashboard.MainWindow;
import fr.enstb.osv.dashboard.OSVException;
import fr.enstb.osv.dashboard.components.OSVColors;

/**
 * @author guillaumelg
 *
 */
public class OSVThermoSensorWidget extends JPanel {

	private static final long serialVersionUID = -1501291316409561832L;
	private MainWindow mw;
	private JLabel temperatureL;
	private Font font;
	private float temperature;

	public OSVThermoSensorWidget(MainWindow mw) {
		this.mw = mw;
		temperatureL = new JLabel();
		temperatureL.setForeground(OSVColors.GREY_3);
		temperatureL.setFont(new Font(temperatureL.getFont().getName(), Font.PLAIN, 24));
		font = getFont();

		try {
			setTemperature(20f);
		} catch (OSVException e) {
		}
	}

	public void calculateWidth() {
		int width = (int) (mw.getWidth() * 0.08);
		int height = (int) (width * 0.9f);
		setMinimumSize(new Dimension(width, height));
		setPreferredSize(new Dimension(width, height));
	}

	@Override
	public void paintComponent(Graphics g) {
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

		g2d.setColor(OSVColors.GREY_3);
		float yF = width / 6;
		g2d.setFont(font.deriveFont(yF));

		FontMetrics fm = getFontMetrics(g2d.getFont());
		int xF = fm.stringWidth(temperatureL.getText());
		int xFPos = offX + width / 3 - xF / 2;
		int yFPos = (int) (offY + height / 2 + yF / 2) - 2;
		g2d.drawString(temperatureL.getText(), xFPos, yFPos);

		// Draw temperature gauge
		float gWidth = width / 6f;
		float gHeight = height * 0.9f;
		float xC = xF + xFPos + offX;
		float yC = (height - gHeight) / 2f + offY;

		if (temperature > 40 && temperature < 60) {
			g2d.setColor(OSVColors.ORANGE);
		} else if (temperature >= 60) {
			g2d.setColor(OSVColors.RED_1);
		} else {
			g2d.setColor(OSVColors.BLUE_1);
		}

		if (temperature > 0) {
			float glHeight;
			if (temperature >= 60) {
				glHeight = gHeight;
			} else {
				glHeight = gHeight * temperature / 60f;
			}
			float ylC = yC + gHeight - glHeight;
			g2d.fillRoundRect((int) xC, (int) ylC, (int) gWidth, (int) glHeight, 15, 15);
		}

		g2d.setColor(OSVColors.WHITE);
		g2d.setStroke(new BasicStroke(3));
		g2d.drawRoundRect((int) xC, (int) yC, (int) gWidth, (int) gHeight, 15, 15);
		//

	}

	public void setTemperature(float temperature) throws OSVException {
		if (temperature == this.temperature) {
			return;
		}

		if (temperature < -60f || temperature > 100f) {
			throw new OSVException("Wrong voltage value: " + temperature);
		}

		this.temperature = temperature;

		temperatureL.setText(String.format("%.1f", this.temperature) + " °C");

		repaint();
	}

	/**
	 * @return the temperature
	 */
	public float getTemperature() {
		return temperature;
	}
}
