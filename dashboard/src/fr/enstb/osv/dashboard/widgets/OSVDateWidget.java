/*
 * OSVDateWidget.java
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
import java.awt.Image;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import fr.enstb.osv.dashboard.MainWindow;
import fr.enstb.osv.dashboard.components.OSVColors;

/**
 * @author guillaumelg
 *
 */
public class OSVDateWidget extends JPanel {

	private static final long serialVersionUID = -4089580648619011563L;
	private MainWindow mw;
	private JLabel time;

	public OSVDateWidget(MainWindow mw) {
		this.mw = mw;
		time = new JLabel();
		time.setForeground(Color.white);
		time.setFont(new Font(time.getFont().getName(), Font.PLAIN, 64));
		setMinimumSize(new Dimension(mw.leaf.getWidth(), mw.leaf.getHeight()));
		setPreferredSize(new Dimension(mw.leaf.getWidth(), mw.leaf.getHeight()));
		setOpaque(false);
		
		// setLayout(new BorderLayout());
		// add(time, BorderLayout.CENTER);
		(new TimeChangeWathcer(this)).execute();
	}

	@Override
	protected void paintComponent(Graphics g) {
		
//		super.paintComponent(g);

		int x1 = (int) (this.getWidth() * 0.8);
		int y1 = (int) (this.getWidth() * mw.leaf.getHeight() / mw.leaf.getWidth() * 0.8);

		System.out.println("width = " + x1);
		System.out.println("height = " + y1);

		g.drawImage(mw.leaf.getScaledInstance(x1, y1, Image.SCALE_SMOOTH), this.getWidth() / 10, this.getHeight() / 3,
				x1, y1, null);

		time.setText(((new Date()).toString()).substring(11, 16));

		setForeground(OSVColors.WHITE);
		float yF = y1 / 4;
		setFont(getFont().deriveFont(yF));
		FontMetrics fm = getFontMetrics(getFont());
		int xF = fm.stringWidth(time.getText());
		System.out.println("xF = " + xF);
		System.out.println("yF = " + yF);

		int xFPos = x1 / 2 + this.getWidth() / 10 - xF / 3;
		int yFPos = (int) (this.getHeight() / 3 + y1 /2 + yF / 4);
		System.out.println("xFPos = " + xFPos);
		System.out.println("yFPos = " + yFPos);

		g.drawString(time.getText(), xFPos, yFPos);
	}

	public class TimeChangeWathcer extends SwingWorker<Void, Void> {

		private OSVDateWidget odw;

		protected TimeChangeWathcer(OSVDateWidget odw) {
			this.odw = odw;
		}

		@Override
		protected Void doInBackground() throws Exception {
			int minute = 61;
			while (odw != null) {
				if (minute != Calendar.getInstance().get(Calendar.MINUTE)) {
					minute = Calendar.getInstance().get(Calendar.MINUTE);
					publish();
				}
				Thread.sleep(2000);
			}

			return null;
		}

		protected void process(List<Void> chunks) {
			odw.repaint();
		}

	}

}
