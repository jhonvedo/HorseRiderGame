package com.horseRiderGame;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Motor extends Thread {
	private ArrayList<Objective> objectiveSeries;
	private ArrayList<Bullet> bulletSeries;
	private CFrame frame;

	public Motor() {
		frame = new CFrame(this);
		objectiveSeries = new ArrayList<Objective>();
		bulletSeries = new ArrayList<Bullet>();
		this.start();
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(20);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				frame.getPanel().move();
				frame.getPanel().repaint();
			}
		}

	}

	public ArrayList<Objective> getObjectiveSeries() {
		return objectiveSeries;
	}

	public ArrayList<Bullet> getBulletSeries() {
		return bulletSeries;
	}
}
