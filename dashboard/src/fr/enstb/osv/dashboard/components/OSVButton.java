/*
 * OSVButton.java
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

package fr.enstb.osv.dashboard.components;

import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.Icon;
import javax.swing.JButton;

public class OSVButton extends JButton {

	private static final long serialVersionUID = 7354557441675341918L;
	protected boolean isPressed = false;

	public OSVButton() {
		super();
		makeButton();
	}

	public OSVButton(String label) {
		super(label);
		makeButton();
	}

	public OSVButton(Icon icon) {
		super(icon);
		makeButton();
	}

	public OSVButton(Icon icon, Icon brightIcon) {
		super(icon);
		super.setPressedIcon(brightIcon);
		makeButton();
	}

	private void makeButton() {
		setBorder(null);
		setFont(OSVFonts.BUTTON_FONT);
		Dimension d = getPreferredSize();
		d.height = 90;
		d.width = 180;
		setPreferredSize(d);
		makeLookChangeListener();
	}

	protected void makeLookChangeListener() {
		addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				isPressed = false;
			}

			@Override
			public void mousePressed(MouseEvent e) {
				isPressed = true;
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
	}

	public boolean isPressed() {
		return isPressed;
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		GradientPaint gp;

		if (!isPressedOrSelected()) {
			gp = new GradientPaint(0, getHeight(), OSVColors.GREY_1, 0, 0, OSVColors.GREY_2);
		} else {
			gp = new GradientPaint(0, getHeight(), OSVColors.GREY_4, 0, 0, OSVColors.GREY_3);
		}

		g2d.setPaint(gp);

		g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 10, 10));
		if (getMousePosition() == null) {
			g2d.setColor(OSVColors.GREY_3);
		} else {
			g2d.setColor(OSVColors.GREY_2);
		}

		g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);

		if (getIcon() != null) {
			if (getPressedIcon() != null) {
				if (isPressedOrSelected()) {
					getPressedIcon().paintIcon(this, g2d, (getWidth() - getIcon().getIconWidth()) / 2,
							(getHeight() - getIcon().getIconHeight()) / 2);
				} else {
					getIcon().paintIcon(this, g2d, (getWidth() - getIcon().getIconWidth()) / 2,
							(getHeight() - getIcon().getIconHeight()) / 2);
				}
			} else {
				getIcon().paintIcon(this, g2d, (getWidth() - getIcon().getIconWidth()) / 2,
						(getHeight() - getIcon().getIconHeight()) / 2);
			}
		} else {
			Rectangle2D r = getFont().getStringBounds(getText(), new FontRenderContext(null, false, false));
			if(isPressedOrSelected()) {
				g2d.setColor(OSVColors.GREEN_2);
			}  else {
				g2d.setColor(OSVColors.GREEN_1);
			}
			g2d.drawString(getText(), (float) (getWidth() - r.getWidth()) / 2,
					(float) (getHeight() - getFont().getSize()) / 2 + getFont().getSize());
		}
	}

	protected boolean isPressedOrSelected() {
		return isPressed;
	}
}