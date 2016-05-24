/**
 * Copyright 2016 Institut Mines-Télécom
 *
 * This file is part of osv-bms-dashboard.
 *
 * osv-bms-dashboard is free software: you can redistribute it
 * and/or modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * osv-bms-dashboard is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with osv-bms-dashboard.  If not, see
 * <http://www.gnu.org/licenses/>.
 */

package eu.telecom_bretagne.osvbmsdashboard.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * @author guillaumelg
 *
 */
public class MainWindow extends JFrame {

	private static final long serialVersionUID = -683027145524533015L;

	JPanel rootPane;
	JMenuBar menuBar;

	private CenterPanel centerPanel;
	private EastPanel eastPanel;

	public MainWindow() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowListener() {

			public void windowOpened(WindowEvent e) {}

			public void windowClosing(WindowEvent e) {}

			public void windowClosed(WindowEvent e) {
				onExit();
			}

			public void windowIconified(WindowEvent e) {}

			public void windowDeiconified(WindowEvent e) {}

			public void windowActivated(WindowEvent e) {}

			public void windowDeactivated(WindowEvent e) {}
		});
		setMinimumSize(new Dimension(320, 240));
		rootPane = (JPanel) getContentPane();
		rootPane.setLayout(new BorderLayout());
		makeMenu();
		makeMainPanel();
		
		setTitle(App.APPLICATION_NAME);
		pack();
	}

	private void makeMainPanel() {
		makeCenterPanel();
		makeEastPanel();
	}

	private void makeCenterPanel() {
		centerPanel = new CenterPanel();
		rootPane.add(centerPanel, BorderLayout.CENTER);
	}
	
	private void makeEastPanel() {
		eastPanel = new EastPanel();
		rootPane.add(eastPanel, BorderLayout.EAST);
	}

	private void makeMenu() {
		menuBar = new JMenuBar();
		JMenu file = new JMenu("Fichier");
		JMenuItem quit = new JMenuItem("Quitter");
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onExit();
			}
		});

		JMenuItem options = new JMenuItem("Options");
		options.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		});
		
		file.add(options);
		file.addSeparator();
		file.add(quit);
		
		menuBar.add(file);
		rootPane.add(menuBar, BorderLayout.NORTH);
	}

	protected void onExit() {
		System.exit(0);
	}

	public void display() {
		setVisible(true);
	}
}
