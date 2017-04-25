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

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

import fr.enstb.osv.dashboard.MainWindow;

/**
 * @author guillaumelg
 *
 */
public class OSVTextWidget extends OSVBasicTextWidget {

	private static final long serialVersionUID = 5877777051772979699L;
	private int preferredWidth;

	public OSVTextWidget(MainWindow mw, int width) {
		super(mw);
		this.preferredWidth = width;
		setOurWidth();
	}

	public OSVTextWidget(MainWindow mw, int width, String text) {
		super(mw);
		this.preferredWidth = width;
		setOurWidth();

		setLabelText(text);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		setOurWidth();

		genericOSVTextComponentPainter(g);
	}

	private void setOurWidth() {
		setMinimumSize(new Dimension(preferredWidth, preferredWidth / 4));
		setPreferredSize(new Dimension(preferredWidth, preferredWidth / 4));

	}
}
