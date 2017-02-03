/**
 * 
 */
package fr.enstb.osv.dashboard.components;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Icon;

public class OSVToggleButton extends OSVButton {

	private static final long serialVersionUID = -6939932897180946275L;

	private boolean isSelected = false;

	public OSVToggleButton(Icon icon, Icon brightIcon) {
		super(icon, brightIcon);
	}

	@Override
	protected void makeLookChangeListener() {
		addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				isPressed = false;
				isSelected = isSelected ? false : true;
			}

			@Override
			public void mousePressed(MouseEvent e) {
				isPressed = true;
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
	}

	@Override
	protected boolean isPressedOrSelected() {
		return isPressed || isSelected;
	}
	
	public boolean isButtonSelected() {
		return isSelected;
	}
	
	public void makeSelected(boolean shouldBeSelected) {
		isSelected = shouldBeSelected;
		repaint();
	}
}
