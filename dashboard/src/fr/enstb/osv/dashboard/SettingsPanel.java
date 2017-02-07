/*
 * SettingsPanel.java
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
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import fr.enstb.osv.dashboard.components.OSVButton;
import fr.enstb.osv.dashboard.components.OSVPanel;

/**
 * @author guillaumelg
 *
 */
public class SettingsPanel extends OSVPanel {

	private static final long serialVersionUID = -1052555126335479979L;
	private JPanel cp;
	private Component verticalSpace;

	public SettingsPanel(MainWindow mw) {
		super(mw);

		cp = new JPanel();
		cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));
		cp.setOpaque(false);
		verticalSpace = Box.createVerticalStrut(mw.getHeight() / 2);
		cp.add(verticalSpace);

		
		OSVButton exit = new OSVButton(mw.iconClose, mw.iconCloseBright);
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		JPanel cp1 = new JPanel();
		cp1.setOpaque(false);
		cp1.add(exit);
		cp.add(cp1);
		
		add(cp, BorderLayout.CENTER);
	}
	
//	@Override
//	protected void paintComponent(Graphics g) {
//		
////		cp.remove(verticalSpace);
////		verticalSpace = Box.createVerticalStrut(mw.getHeight() / 4);
////		cp.add(verticalSpace, 0);
//		
//		super.paintComponents(g);
//	}

}
