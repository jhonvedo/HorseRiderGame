package com.horseRiderGame;

import java.awt.Rectangle;

import com.hourseRiderGame.model.Directions;

public abstract class CComponent extends Thread {
	private int width, heigth;
	private int positionX, positionY;
	private int speedX, speedY;
	private int sequenceCount, sequenceIndex;
	private int containerWidth, containerHeigth;
	private Directions direction;

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeigth() {
		return heigth;
	}

	public void setHeigth(int heigth) {
		this.heigth = heigth;
	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public int getSequenceCount() {
		return sequenceCount;
	}

	public void setSequenceCount(int sequenceCount) {
		this.sequenceCount = sequenceCount;
	}

	public int getSequenceIndex() {
		return sequenceIndex;
	}

	public void setSequenceIndex(int sequenceIndex) {
		this.sequenceIndex = sequenceIndex;
	}

	public int getContainerWidth() {
		return containerWidth;
	}

	public void setContainerWidth(int containerWidth) {
		this.containerWidth = containerWidth;
	}

	public int getContainerHeigth() {
		return containerHeigth;
	}

	public void setContainerHeigth(int containerHeigth) {
		this.containerHeigth = containerHeigth;
	}

	public Rectangle getRectangle() {
		return new Rectangle(positionX, positionY, width, heigth);
	}

	public Directions getDirection() {
		return direction;
	}

	public void setDirection(Directions direction) {
		this.direction = direction;
	}

	public abstract void move();
}
