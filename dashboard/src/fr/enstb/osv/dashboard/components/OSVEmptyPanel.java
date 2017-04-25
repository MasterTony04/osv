/*
 * OSVEmptyPanel.java
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

import javax.swing.JPanel;
import fr.enstb.osv.dashboard.MainWindow;

/**
 * @author guillaumelg
 *
 */
public class OSVEmptyPanel extends JPanel {

	private static final long serialVersionUID = -7702630822196955563L;
	private MainWindow mw;
	private float yCoeff;
	private float xCoeff;

	public OSVEmptyPanel(MainWindow mw, float xCoeff, float yCoeff) {
		this.mw = mw;
		this.xCoeff = xCoeff;
		this.yCoeff = yCoeff;
		calculateDimensions();
		setOpaque(false);
	}

	public void calculateDimensions() {
		int width, height;
		if (xCoeff > 0) {
			width = (int) (mw.getWidth() * xCoeff);
		} else {
			width = 1;
		}
		if (yCoeff > 0) {
			height = (int) (mw.getHeight() * yCoeff);
		} else {
			height = 1;
		}
		setMinimumSize(new Dimension(width, height));
		setPreferredSize(new Dimension(width, height));
	}
}
