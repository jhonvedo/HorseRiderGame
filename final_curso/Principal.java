package final_curso;

import java.awt.Dialog;

import javax.swing.JOptionPane;

public class Principal {

	public static void main(String[] args) {
		String mensaje="creado por : \n jhon alexander acevedo \n Paula andrea paniagua montoya \n\nCOMANDOS :\nUP: nuevas flechas \nDOWN: nuevas esferas \nLEFT and RIGHT: mover jinete";
		JOptionPane.showMessageDialog (null, mensaje,"Bienvenido", JOptionPane.INFORMATION_MESSAGE);
		
		@SuppressWarnings("unused")		
		Motor nuevo = new Motor();

	}

}
