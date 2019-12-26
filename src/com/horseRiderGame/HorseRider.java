package com.horseRiderGame;

public class HorseRider {
	private int posX;
	private int posY;
	private int boxNumber;
	private int move;
	private int width;
	private int heigth;
	@SuppressWarnings("unused")
	private int numseries;
	private Motor motor;

	public HorseRider(Motor motor) {
		this.motor = motor;
		this.posX = 0;
		this.posY = 0;
		this.boxNumber = 0;
		this.numseries = 4;
		this.move = 22;
		this.heigth = 151;
		this.width = 152;

	}

	public int getWidth() {
		return width;
	}

	public int getHeigth() {
		return heigth;
	}

	public int getPositionY() {
		return posY;
	}

	public void setPositionY(int posY) {
		this.posY = posY;
	}

	public int getBoxNumber() {
		return boxNumber;
	}

	public void setBoxNumber(int boxNumber) {
		this.boxNumber = boxNumber;
	}

	public int getPositionX() {
		return posX;
	}

	public void setPositionX(int posX) {
		this.posX = posX;
	}

	public void moveRight() {
		this.posX += this.move;
	}

	public void moveLeft() {
		this.posX += (this.move * -1);
	}

}
