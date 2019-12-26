package com.horseRiderGame;

import java.awt.Dialog;

import javax.swing.JOptionPane;

public class Principal {

	public static void main(String[] args) {
		String mensaje = "COMANDOS :\nUP: nuevas flechas \nDOWN: nuevas esferas \nLEFT \\n RIGHT: mover jinete";
		JOptionPane.showMessageDialog(null, mensaje, "Bienvenido", JOptionPane.INFORMATION_MESSAGE);

		@SuppressWarnings("unused")
		Motor nuevo = new Motor();
	}

}
