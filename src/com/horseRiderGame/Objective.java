package com.horseRiderGame;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

public class Objective extends Thread {
	private Motor motor;
	private int width;
	private int heigth;
	private int PosX;
	private int PosY;
	private int velX;
	private int velY;
	private int boxSize;
	private int boxNumber;
	private Random rnd;
	private boolean exploit;

	public Objective(Motor motor) {
		rnd = new Random();
		this.motor = motor;
		this.PosX = 0;
		this.PosY = 0;
		this.velX = rnd.nextInt(5) + 1;
		this.velY = rnd.nextInt(5) + 1;
		this.boxSize = 6;
		this.boxNumber = 0;
		this.heigth = 38;
		this.width = 42;
		this.exploit = false;
		this.start();

	}

	public int getBoxSize() {
		return boxSize;
	}

	public int getWidth() {
		return width;
	}

	public int getHeigth() {
		return heigth;
	}

	public int getSpeedY() {
		return velY;
	}

	public int getSpeedX() {
		return velX;
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

	public int getBoxNumber() {
		return boxNumber;
	}

	public void setBoxNumber(int boxNumber) {
		this.boxNumber = boxNumber;
	}

	public void exploit() {
		this.exploit = true;
	}

	public void run() {
		while (boxNumber < boxSize) {
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (exploit == true)
					boxNumber++;
			}
		}
		ArrayList alSecuencias = motor.getObjectiveSeries();
		for (int cont = 0; cont < alSecuencias.size(); cont++) {
			if (alSecuencias.get(cont) == this) {
				alSecuencias.remove(cont);
			}
		}
	}

	public void setSpeedX(int velX) {
		this.velX = velX;
	}

	public void setSpeedY(int velY) {
		this.velY = velY;
	}

	public void move(int ancho, int alto) {

		if (this.getPositionX() > ancho - this.getWidth())
			this.setSpeedX(this.getSpeedX() * -1);

		if (this.getPositionX() < 0)
			this.setSpeedX(this.getSpeedX() * -1);

		if (this.getPositionY() > alto / 2 - this.getHeigth())
			this.setSpeedY(this.getSpeedY() * -1);

		if (this.getPositionY() < 0)
			this.setSpeedY(this.getSpeedY() * -1);

		this.setPositionX(this.getPositionX() + this.getSpeedX());
		this.setPositionY(this.getPositionY() + this.getSpeedY());

	}

	public Rectangle getRectangle() {
		return new Rectangle(PosX, PosY, width, heigth);
	}

}
