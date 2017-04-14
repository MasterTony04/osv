/*
 * OSVBatteryWidget.java
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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.enstb.osv.dashboard.MainWindow;
import fr.enstb.osv.dashboard.OSVException;
import fr.enstb.osv.dashboard.components.OSVColors;

/**
 * @author guillaumelg
 *
 */
public class OSVBatteryWidget extends JPanel {

	private static final long serialVersionUID = -4089580648619011563L;
	private MainWindow mw;
	private JLabel socS;
	private final Font font;
	private float soc = 0f;
	private volatile boolean isCharging = false;
	TimerTask lightningPainterTask = null;
	private Timer lightningPainterTaskTimer;
	private int lightningCounter = -1;

	public OSVBatteryWidget(MainWindow mw) {
		this.mw = mw;
		socS = new JLabel();
		socS.setForeground(Color.white);
		socS.setFont(new Font(socS.getFont().getName(), Font.PLAIN, 64));
		font = getFont();

		setOurWidth();

		try {
			setSoc(0.32f);
		} catch (OSVException e) {
			e.printStackTrace();
		}

	}

	private void setOurWidth() {
		int width = (int) (mw.getWidth() * 0.21);
		int height = 2 * width;
		setMinimumSize(new Dimension(width, height));
		setPreferredSize(new Dimension(width, height));
	}

	@Override
	protected void paintComponent(Graphics g) {
		setOurWidth();
		int x = getWidth();
		int y = getHeight();

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(OSVColors.GREY_3);
		int offX = x / 5;
		int offY = y / 3;
		int width = x * 3 / 5;
		int height = y * 2 / 5;
		g2d.fillRect(offX, offY, width, height);

		if (soc > 0f) {
			g2d.setColor(OSVColors.BLUE_1);
			int maxHeight = 5 * height / 8 - width / 10;
			g2d.fillRect(offX + width / 4 + width / 20, (int) (offY + height / 8 + width / 20 + (1 - soc) * maxHeight),
					width / 2 - width / 10, (int) (5 * height / 8 - width / 10 - (1 - soc) * maxHeight));
		}

		g2d.setStroke(new BasicStroke(6));
		g2d.setColor(OSVColors.WHITE);
		g2d.drawRect(offX + width / 4, offY + height / 8, width / 2, 5 * height / 8);
		g2d.drawRect(offX + width / 4 + width / 8, offY + height / 8 - height / 20, width / 4, height / 20);

		if (isCharging) {
			// TODO Draw lightning
			int ySize = height / 2;
			int xSize = mw.chargeLightning.getWidth() * ySize / mw.chargeLightning.getHeight();
			BufferedImage lightningImg;
			switch (lightningCounter++) {
			case 0:
				lightningImg = mw.chargeLightning0;
				break;
			case 1:
				lightningImg = mw.chargeLightning1;
				break;
			case 2:
				lightningImg = mw.chargeLightning2;
				break;
			case 3:
				lightningImg = mw.chargeLightning3;
				break;
			case 4:
				lightningImg = mw.chargeLightning4;
				break;
			case 5:
				lightningImg = mw.chargeLightning5;
				break;
			case 6:
				lightningImg = mw.chargeLightning6;
				break;
			case 7:
				lightningImg = mw.chargeLightning7;
				break;
			case 8:
				lightningImg = mw.chargeLightning8;
				break;
			case 9:
				lightningImg = mw.chargeLightning9;
				break;
			default:
				lightningImg = mw.chargeLightning;
			}
			if(lightningCounter == 48) {
				lightningCounter = -1;
			}

			ImageIcon sizedLightning = new ImageIcon(lightningImg.getScaledInstance(xSize, ySize, Image.SCALE_SMOOTH));
			int x1 = offX + width / 2 - sizedLightning.getIconWidth() / 2;
			int y1 = offY + 7 * height / 16 - sizedLightning.getIconHeight() / 2;
			g.drawImage(sizedLightning.getImage(), x1, y1, sizedLightning.getIconWidth(),
					sizedLightning.getIconHeight(), null);
		}

		setForeground(OSVColors.WHITE);
		float yF = height / 12;
		g.setFont(font.deriveFont(yF));

		FontMetrics fm = getFontMetrics(g.getFont());
		int xF = fm.stringWidth(socS.getText());
		int xFPos = offX + width / 2 - xF / 2;
		// int xFPos = x1 / 2 + this.getWidth() / 10 - xF / 2;
		// int yFPos = (int) (this.getHeight() / 3 + y1 / 2 + yF / 4);
		int yFPos = (int) (offY + height - height / 16 - yF / 2);
		g.drawString(socS.getText(), xFPos, yFPos);
	}

	public void setSoc(float soc) throws OSVException {
		if (soc == this.soc) {
			return;
		}

		if (soc < 0f || soc > 1f) {
			throw new OSVException("Wrong SOC value: " + soc);
		}

		this.soc = soc;
		socS.setText((int) (100 * soc) + " %");

		repaint();
	}

	public synchronized void setIsCharging(boolean charging) {
		if (isCharging == charging) {
			return;
		}
		isCharging = charging;
		if (charging) {
			lightningPainterTask = new TimerTask() {
				@Override
				public void run() {
					getWidgetInstance().repaint();
				}
			};
			lightningPainterTaskTimer = new Timer(true);
			lightningPainterTaskTimer.scheduleAtFixedRate(lightningPainterTask, 0, 40);
		} else {
			if (lightningPainterTaskTimer != null) {
				lightningPainterTaskTimer.cancel();
				lightningPainterTaskTimer = null;
			}
			repaint();
		}
	}

	private OSVBatteryWidget getWidgetInstance() {
		return this;
	}
}
