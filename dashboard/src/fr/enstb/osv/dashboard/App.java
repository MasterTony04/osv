/**
 * 
 */
package fr.enstb.osv.dashboard;

import java.io.IOException;

import javax.swing.SwingUtilities;

/**
 * @author guillaumelg
 *
 */
public class App {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			// Override
			public void run() {
				try {
					new MainWindow();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

}
