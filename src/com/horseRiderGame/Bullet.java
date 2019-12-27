package com.horseRiderGame;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Bullet extends Thread {
	
	private int PosX;
	private int PosY;
	private int speed;
	private int width;
	private int heigh;

	public Bullet(int posX, int posY, int heigh,int width,int speed) {		
		this.speed = speed;//5
		this.PosX = posX;
		this.PosY = posY;
		this.heigh = heigh;//57
		this.width = width;//22
	}

	public int getWidth() {
		return width;
	}

	public int getHeigh() {
		return heigh;
	}

	public int getPositionX() {
		return PosX;
	}

	public void setPositionX(int posX) {
		PosX = posX;
	}

	public int getPositionY() {
		return PosY;
	}

	public void setPositionY(int posY) {
		PosY = posY;
	}

	public int getSpeed() {
		return speed;
	}

	public void move() {
		this.PosY -= this.speed;
		if (this.PosY <= 0)
			this.delete();

	}

	public Rectangle getRectBala() {
		return new Rectangle(PosX, PosY, width, heigh);
	}

	public void delete() {
		ArrayList<Bullet> alSecuencias = WholeObjectsSingleton.getInstance().getBulletSeries();
		for (int cont = 0; cont < alSecuencias.size(); cont++) {
			if (alSecuencias.get(cont) == this) {
				alSecuencias.remove(cont);
			}
		}
	}

}
