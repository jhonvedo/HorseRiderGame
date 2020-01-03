package com.triggerGame.Objects;


import com.triggerGame.CComponent;
import com.triggerGame.WholeObjectsSingleton;

public class Bullet extends CComponent {

	public Bullet(int posX, int posY, int heigth, int width, int speedY) {
		this.setSpeedY(speedY);// 5
		this.setPositionX(posX);
		this.setPositionY(posY);
		this.setHeigth(heigth);// 57
		this.setWidth(width);// 22
	}

	public void move() {
		this.setPositionY(this.getPositionY() - this.getSpeedY());
		if (this.getPositionY() <= 0)
			this.delete();

	}

	public void delete() {
		WholeObjectsSingleton.getInstance().getBulletSeries().remove(this);
		/*ArrayList<Bullet> alSecuencias = WholeObjectsSingleton.getInstance().getBulletSeries();
		for (int cont = 0; cont < alSecuencias.size(); cont++) {
			if (alSecuencias.get(cont) == this) {
				alSecuencias.remove(cont);
			}
		}*/
	}

}
