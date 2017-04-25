package fr.enstb.osv.dashboard.widgets;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import fr.enstb.osv.dashboard.MainWindow;
import fr.enstb.osv.dashboard.components.OSVColors;

public class OSVElementStatusWidget extends JPanel {

	private static final long serialVersionUID = 2326734011530889886L;
	private boolean statusOK;
	private Image icon;
	private Image iconRed;
	private MainWindow mw;

	public OSVElementStatusWidget(MainWindow mw, Image icon, Image iconRed) {
		this.mw = mw;
		statusOK = false;
//		setMinimumSize(new Dimension(128, 93));
//		setPreferredSize(new Dimension(128, 93));
		this.icon = icon;
		this.iconRed = iconRed;
		calculateWidth();
	}

	public void setStatus(boolean isOK) {
		if (statusOK != isOK) {
			statusOK = isOK;
			repaint();
		}
	}
	
	public void calculateWidth() {
		int width = (int) (mw.getWidth() * 0.065);
		int height = (int) (width * 0.72);
		setMinimumSize(new Dimension(width, height));
		setPreferredSize(new Dimension(width, height));
	}

	@Override
	public void paintComponent(Graphics g) {
		calculateWidth();
		
		Graphics2D g2d = (Graphics2D) g;

		int x = getWidth();
		int y = getHeight();

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(OSVColors.GREY_4);
		g2d.fillRect(0, 0, x, y);

		if (icon != null && iconRed != null) {
			Image ico;
			if (statusOK) {
				ico = icon;
//				icon.paintIcon(this, g2d, (getWidth() - icon.getIconWidth()) / 2,
//						(getHeight() - icon.getIconHeight()) / 2);
			} else {
				ico = iconRed;
//				iconRed.paintIcon(this, g2d, (getWidth() - iconRed.getIconWidth()) / 2,
//						(getHeight() - iconRed.getIconHeight()) / 2);
			}
			
			int ySize = (int) (getParent().getHeight() * 0.8);
			int xSize = ico.getWidth(null) * ySize / ico.getHeight(null);
			ImageIcon sizedIcon = new ImageIcon(ico.getScaledInstance(xSize, ySize, Image.SCALE_SMOOTH));
//			int x1 = (int) (x * 0.1);
//			int y1 = (int) (y * 0.1);
			int x1 = (x - xSize) / 2;
			int y1 = (y - ySize) / 2;
			g2d.drawImage(sizedIcon.getImage(), x1, y1, sizedIcon.getIconWidth(), sizedIcon.getIconHeight(), null);
		}
	}
}
