/*
 * OSVToggleButton.java
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

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Icon;

public class OSVToggleButton extends OSVButton {

	private static final long serialVersionUID = -6939932897180946275L;

	private boolean isSelected = false;

	public OSVToggleButton(Icon icon, Icon brightIcon) {
		super(icon, brightIcon);
	}

	@Override
	protected void makeLookChangeListener() {
		addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				isPressed = false;
//				isSelected = isSelected ? false : true;
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

	@Override
	protected boolean isPressedOrSelected() {
		return isPressed || isSelected;
	}
	
	public boolean isButtonSelected() {
		return isSelected;
	}
	
	public void makeSelected(boolean shouldBeSelected) {
		isSelected = shouldBeSelected;
		repaint();
	}
	
	public boolean equals(Object button) {
		if(button instanceof OSVToggleButton) {
			OSVToggleButton b = (OSVToggleButton) button;
			return b.getIcon().equals(this.getIcon()) && b.getPressedIcon().equals(this.getPressedIcon());
		}
		return false;
	}
}
