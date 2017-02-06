/*
 * OSVPanel.java
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

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import fr.enstb.osv.dashboard.MainWindow;
import fr.enstb.osv.dashboard.widgets.OSVBatteryWidget;
import fr.enstb.osv.dashboard.widgets.OSVDateWidget;

/**
 * @author guillaumelg
 *
 */
public class OSVPanel extends JPanel {

	private static final long serialVersionUID = 8572357445596859917L;
	private MainWindow mw;

	public OSVPanel(MainWindow mw) {
		this.mw = mw;
		
		this.setLayout(new BorderLayout());
		
		JPanel p1  = new JPanel();
		p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
		this.add((p1).add(new OSVDateWidget(mw)), BorderLayout.WEST);
		
		JPanel p2  = new JPanel();
		p2.setLayout(new BoxLayout(p2, BoxLayout.Y_AXIS));
		p2.add(new OSVBatteryWidget(mw));
		this.add(new OSVBatteryWidget(mw), BorderLayout.EAST);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(mw.wallpaper.getScaledInstance(mw.getWidth(), mw.getHeight(), Image.SCALE_SMOOTH), 0, 0,
				mw.getWidth(), mw.getHeight(), null);
	}
}
