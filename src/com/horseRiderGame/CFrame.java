package com.horseRiderGame;

import java.awt.Dimension;
import javax.swing.JFrame;

public class CFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CPanel panel;

	public CFrame(CPanel panel, int width, int heigh) {
		super("Tirale a las esferas :p");
		configureFrame(width, heigh);
		configurePanel(panel);

	}

	private void configurePanel(CPanel panel) {
		panel.setPanelSize(this.getWidth(), this.getHeight());
		this.panel = panel;
		this.add(this.panel);
		this.addKeyListener(this.panel);
	}

	private void configureFrame(int width, int heigh) {
		this.setLayout(null);
		this.setSize(width, heigh);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		Dimension actualSize = this.getContentPane().getSize();
		int extraW = width - actualSize.width;
		int extraH = heigh - actualSize.height;
		this.setSize(width + extraW, heigh + extraH);

	}

	public CPanel getPanel() {
		return this.panel;
	}

}
