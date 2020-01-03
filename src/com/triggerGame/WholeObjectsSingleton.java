package com.triggerGame;

import java.util.ArrayList;

import com.triggerGame.Objects.Bullet;
import com.triggerGame.Objects.Objective;

public class WholeObjectsSingleton {
	private ArrayList<Objective> objectiveSeries;
	private ArrayList<Bullet> bulletSeries;
	private static WholeObjectsSingleton _instance;

	private WholeObjectsSingleton() {
		setObjectiveSeries(new ArrayList<Objective>());
		setBulletSeries(new ArrayList<Bullet>());
	}

	public static WholeObjectsSingleton getInstance() {
		if (_instance == null)
			_instance = new WholeObjectsSingleton();
		return _instance;
	}

	public ArrayList<Bullet> getBulletSeries() {
		return bulletSeries;
	}

	public void setBulletSeries(ArrayList<Bullet> bulletSeries) {
		this.bulletSeries = bulletSeries;
	}

	public ArrayList<Objective> getObjectiveSeries() {
		return objectiveSeries;
	}

	public void setObjectiveSeries(ArrayList<Objective> objectiveSeries) {
		this.objectiveSeries = objectiveSeries;
	}

}
