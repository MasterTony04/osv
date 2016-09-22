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
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author guillaumelg
 *
 */
public class OptionFrame extends JFrame {

	private static final long serialVersionUID = -3161755501829312674L;
	private MainWindow mw;
	private JPanel rootPanel;
	JTextField userInput;
	private static final String BMS_PATH = "BMS_PATH";
	private static final String CONF_FILE_NAME = "osv-bms-dashboard.conf";

	public OptionFrame(MainWindow mw) {
		this.mw = mw;
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMinimumSize(new Dimension(415, 120));
		rootPanel = (JPanel) getContentPane();
		rootPanel.setLayout(new BorderLayout());
		JPanel centerP = new JPanel();
		JLabel pathToFiles = new JLabel("Path to BMS data files: ");
		userInput = new JTextField(20);
		userInput.addActionListener(new AbstractAction() {
			private static final long serialVersionUID = 6141452036271520013L;
			public void actionPerformed(ActionEvent e) {
				clickedOnOK();
			}
		});
		centerP.add(pathToFiles);
		centerP.add(userInput);
		JPanel bottomPanel = new JPanel();
		JButton oK = new JButton("OK");
		oK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickedOnOK();
			}
		});
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userInput.setText(App.PATH_TO_BMS_FILES);
				hideMe();
			}
		});
		bottomPanel.add(cancel);
		bottomPanel.add(oK);
		rootPanel.add(centerP, BorderLayout.CENTER);
		rootPanel.add(bottomPanel, BorderLayout.SOUTH);
		
		loadConf();
		userInput.setText(App.PATH_TO_BMS_FILES);
	}

	protected void clickedOnOK() {
		String s = userInput.getText();
		if (!(new File(s)).isDirectory()) {
			JOptionPane.showMessageDialog(this,
					"The path you entered does not exist on filesystem.\n" + "Please enter a path that exists.", "",
					JOptionPane.ERROR_MESSAGE);
		} else {
			App.PATH_TO_BMS_FILES = s;
			mw.restartBmsFileWatcher();
			saveConf();
			hideMe();
		}
	}

	private void saveConf() {
		App.writeToFile(App.PATH + App.SEPARATOR + CONF_FILE_NAME, "// Auto-generated file, any change may be overwritten.\n\n" + BMS_PATH + " "
				+ App.PATH_TO_BMS_FILES + "\n\n");
	}

	private void loadConf() {
		String confPath = App.PATH + App.SEPARATOR + CONF_FILE_NAME;
		if(new File(confPath).exists()) {
			for(String s : App.readFile(confPath).split("\n")) {
				if(s.startsWith(BMS_PATH + " ")) {
					App.PATH_TO_BMS_FILES = (s.split(" "))[1];
				}
			}
		}
	}

	protected void hideMe() {
		setVisible(false);
	}
}
