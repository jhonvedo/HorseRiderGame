package com.horseRiderGame.Objects;

import com.horseRiderGame.CComponent;
import com.hourseRiderGame.model.Directions;

public class HorseRider extends CComponent {

	public HorseRider(int sequenceCount, int speedX, int heigth, int width) {
		this.setPositionX(0);
		this.setPositionY(0);
		this.setSequenceIndex(0);
		this.setSequenceCount(sequenceCount);// 4
		this.setSpeedX(speedX); // 22
		this.setHeigth(heigth);// 151
		this.setWidth(width);// 152

	}

	public void move() {
		if (this.getDirection() == Directions.LEFT) {
			this.setPositionX(this.getPositionX() + (this.getSpeedX() * -1));
		} else {
			this.setPositionX(this.getPositionX() + this.getSpeedX());
		}

	}

}
