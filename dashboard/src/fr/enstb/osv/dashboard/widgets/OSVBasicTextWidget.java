/*
 * OSVBasicTextWidget.java
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

import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.enstb.osv.dashboard.MainWindow;
import fr.enstb.osv.dashboard.components.OSVColors;

/**
 * @author guillaumelg
 *
 */
public class OSVBasicTextWidget extends JPanel {

	private static final long serialVersionUID = -4089580648619011563L;
	private MainWindow mw;
	private JLabel text;
	private final Font font;

	public OSVBasicTextWidget(MainWindow mw) {
		this.mw = mw;
		text = new JLabel();
		text.setForeground(Color.white);
		text.setFont(new Font(text.getFont().getName(), Font.PLAIN, 64));
		font = getFont();

		setOurWidth();

		setLabelText("0 km/h");
	}

	private void setOurWidth() {
		int width = (int) (mw.getWidth() * 0.21);
		setMinimumSize(new Dimension(width, width / 4));
		setPreferredSize(new Dimension(width, width / 4));
	}

	@Override
	protected void paintComponent(Graphics g) {
		setOurWidth();

		int x1 = this.getWidth(); // (int) (((float) this.getWidth()) * 0.2);
		int y1 = (int) (((float) this.getHeight()) * 0.8);
		int xOff = 0; // (int) (((float) this.getWidth()) * 0.4);
		int yOff = (int) (((float) this.getHeight()) * 0.1);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.setColor(OSVColors.GREY_4);
		g.fillRect(xOff, yOff, x1, y1);

		g.setColor(OSVColors.WHITE);
		float yF = y1 / 2;
		g.setFont(font.deriveFont(yF));

		FontMetrics fm = getFontMetrics(g.getFont());
		int xF = fm.stringWidth(text.getText());
		int xFPos = xOff + x1 / 2 - xF / 2;
		int yFPos = (int) (yOff + y1 / 2 + yF / 3);
		g.drawString(text.getText(), xFPos, yFPos);
	}

	public void setLabelText(String labelText) {
		text.setText(labelText);

		repaint();
	}
}
