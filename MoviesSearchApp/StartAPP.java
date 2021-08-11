// this class handles the main function which creates new GUI object, by this it lunches the app

import javax.swing.JFrame;

public class StartAPP {
	public static void main(String[] args) {
		GUI app = new GUI();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
