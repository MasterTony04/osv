package fr.enstb.osv.dashboard.widgets;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.Icon;
import javax.swing.JPanel;

import fr.enstb.osv.dashboard.MainWindow;
import fr.enstb.osv.dashboard.components.OSVColors;

public class OSVElementStatusWidget extends JPanel {

	private static final long serialVersionUID = 2326734011530889886L;
	private boolean statusOK;
	private Icon icon;
	private Icon iconRed;

	public OSVElementStatusWidget(MainWindow mw, Icon icon, Icon iconRed) {
		statusOK = false;
		setMinimumSize(new Dimension(128, 93));
		setPreferredSize(new Dimension(128, 93));
		this.icon = icon;
		this.iconRed = iconRed;
	}

	public void setStatus(boolean isOK) {
		statusOK = isOK;
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		int x = getWidth();
		int y = getHeight();

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(OSVColors.GREY_4);
		g2d.fillRect(0, 0, x, y);

		if (icon != null && iconRed != null) {
			if (statusOK) {
				icon.paintIcon(this, g2d, (getWidth() - icon.getIconWidth()) / 2,
						(getHeight() - icon.getIconHeight()) / 2);
			} else {
				iconRed.paintIcon(this, g2d, (getWidth() - iconRed.getIconWidth()) / 2,
						(getHeight() - iconRed.getIconHeight()) / 2);
			}
		}
	}

}
