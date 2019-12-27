package com.horseRiderGame;

//import javax.swing.JOptionPane;

public class Principal {
	private final static int WIDTH  = 600;
	private final static int HEIGHT = 600;
	
	public static void main(String[] args) {
	//	String mensaje = "COMANDOS :\nUP: nuevas flechas \nDOWN: nuevas esferas \nLEFT and RIGHT: mover jinete";
	//	JOptionPane.showMessageDialog(null, mensaje, "Bienvenido", JOptionPane.INFORMATION_MESSAGE);
		
		CPanel panel = new CPanel();
		CFrame frame = new CFrame(panel,WIDTH,HEIGHT);
		
		@SuppressWarnings("unused")
		Motor nuevo = new Motor(frame);
	}

}
