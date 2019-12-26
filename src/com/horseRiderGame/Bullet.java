package com.horseRiderGame;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Bullet extends Thread {

	private Motor motor;
	private int PosX;
	private int PosY;
	private int vel;
	private int width;
	private int heigh;

	public Bullet(int posX, int posY, Motor motor) {
		this.motor = motor;
		this.vel = 5;
		this.PosX = posX;
		this.PosY = posY;
		this.heigh = 57;
		this.width = 22;
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
		return vel;
	}

	public void move() {
		this.PosY -= this.vel;
		if (this.PosY <= 0)
			this.delete();

	}

	public Rectangle getRectBala() {
		return new Rectangle(PosX, PosY, width, heigh);
	}

	public void delete() {
		ArrayList alSecuencias = motor.getBulletSeries();
		for (int cont = 0; cont < alSecuencias.size(); cont++) {
			if (alSecuencias.get(cont) == this) {
				alSecuencias.remove(cont);
			}
		}
	}

}
