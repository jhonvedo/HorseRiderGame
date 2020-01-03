package com.triggerGame;

public class Main {
	private final static int WIDTH = 1200;
	private final static int HEIGHT = 600;

	public static void main(String[] args) {
		// String mensaje = "COMANDOS :\nUP: nuevas flechas \nDOWN: nuevas esferasnLEFT and RIGHT: mover jinete";
		 //JOptionPane.showMessageDialog(null, mensaje, "Bienvenido",
		 //JOptionPane.INFORMATION_MESSAGE);
		try {
			CPanel panel = new CPanel();
			CFrame frame = new CFrame(panel, WIDTH, HEIGHT);

			@SuppressWarnings("unused")
			Motor nuevo = new Motor(frame);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

}
