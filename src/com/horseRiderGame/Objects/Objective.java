package com.horseRiderGame.Objects;

import java.util.ArrayList;
import java.util.Random;

import com.horseRiderGame.CComponent;
import com.horseRiderGame.WholeObjectsSingleton;

public class Objective extends CComponent {

	private Random rnd;
	private boolean exploit;

	public Objective(int sequenceCount, int heigth, int width) {
		rnd = new Random();

		this.setPositionX(0);
		this.setPositionY(0);
		this.setSpeedX(rnd.nextInt(5) + 1);
		this.setSpeedY(rnd.nextInt(5) + 1);
		this.setSequenceCount(sequenceCount);// 6
		this.setSequenceIndex(0);
		this.setHeigth(heigth);// 38
		this.setWidth(width); // 42
		this.exploit = false;
		this.start();

	}

	public boolean getExploit() {
		return exploit;
	}

	public void exploit() {
		this.exploit = true;
	}

	public void run() {
		while (this.getSequenceIndex() < this.getSequenceCount()) {
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (exploit == true)
					this.setSequenceIndex(this.getSequenceIndex() + 1);
			}
		}
		WholeObjectsSingleton.getInstance().getObjectiveSeries().remove(this);
		/*ArrayList<Objective> alSecuencias = WholeObjectsSingleton.getInstance().getObjectiveSeries();
		for (int cont = 0; cont < alSecuencias.size(); cont++) {
			if (alSecuencias.get(cont) == this) {
				alSecuencias.remove(cont);
			}
		}*/
	}

	public void move() {

		if (this.getPositionX() > this.getContainerWidth() - this.getWidth())
			this.setSpeedX(this.getSpeedX() * -1);

		if (this.getPositionX() < 0)
			this.setSpeedX(this.getSpeedX() * -1);

		if (this.getPositionY() > this.getContainerHeigth() / 2 - this.getHeigth())
			this.setSpeedY(this.getSpeedY() * -1);

		if (this.getPositionY() < 0)
			this.setSpeedY(this.getSpeedY() * -1);

		this.setPositionX(this.getPositionX() + this.getSpeedX());
		this.setPositionY(this.getPositionY() + this.getSpeedY());

	}

}
