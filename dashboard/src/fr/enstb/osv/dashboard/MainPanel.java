/*
 * MainPanel.java
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

package fr.enstb.osv.dashboard;

import java.awt.BorderLayout;

import fr.enstb.osv.dashboard.components.OSVPanel;
import fr.enstb.osv.dashboard.widgets.OSVSpeedCounter;

/**
 * @author guillaumelg
 *
 */
public class MainPanel extends OSVPanel {

	private static final long serialVersionUID = 7020725149595013712L;

	public MainPanel(MainWindow mw) {
		super(mw);
		
		screensButtons.get(0).makeSelected(true);
		
		add(new OSVSpeedCounter(mw), BorderLayout.CENTER);
	}

}
