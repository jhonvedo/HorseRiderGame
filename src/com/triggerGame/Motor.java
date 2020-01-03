package com.triggerGame;

public class Motor extends Thread {
	private CFrame frame;

	public Motor(CFrame frame) {
		this.frame = frame;
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

}
