package com.triggerGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.triggerGame.Objects.Bullet;
import com.triggerGame.Objects.Trigger;
import com.triggerGame.Objects.Objective;
import com.triggerGame.model.Directions;


public class CPanel extends JPanel implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BufferedImage objectiveImg, triggerImg, bulletImg, backgroundImg;
	private Trigger trigger;	
	private final int bulletSpeed = 5;
	private final int sequenceCountObjective = 5;
	private final int sequenceCountTrigger = 7;
	private final int triggerShootsIndex = 0;
	private final int triggerLeftMoveIndex = 1;
	private final int triggerRightMoveIndex = 4;

	public CPanel() throws IOException {
		this.loadImages();
		trigger = new Trigger(sequenceCountTrigger, 22, triggerImg.getHeight(), triggerImg.getHeight());
	}

	public void setPanelSize(int width, int height) {
		this.setSize(width, height);
		trigger.setPositionY(height - trigger.getHeigth() - 85);
	}

	private void loadImages() throws IOException {	
		
		backgroundImg = ImageIO.read(Main.class.getResource("/resources/background.jpg"));
		objectiveImg = ImageIO.read( Main.class.getResource("/resources/objective.png"));
		triggerImg = ImageIO.read(Main.class.getResource("/resources/trigger.png"));
		bulletImg = ImageIO.read(Main.class.getResource("/resources/bullet.png"));
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());		
		g.drawImage(backgroundImg, 0, 0, this.getWidth(), this.getHeight(), null);
		draw(g);

	}

	private void draw(Graphics g) {
		if (WholeObjectsSingleton.getInstance().getObjectiveSeries().size() != 0) {
			drawObjectives(g);
		}

		drawHorseRider(g);

		if (WholeObjectsSingleton.getInstance().getBulletSeries().size() != 0) {
			drawBullets(g);

		}
	}

	private void drawHorseRider(Graphics g) {
		g.drawImage(triggerImg, trigger.getPositionX(), trigger.getPositionY(),
				trigger.getPositionX() + trigger.getWidth(), trigger.getPositionY() + trigger.getHeigth(),
				trigger.getSequenceIndex() * trigger.getWidth(), 0,
				trigger.getSequenceIndex() * trigger.getWidth() + trigger.getWidth(), trigger.getHeigth(),
				null);

	}

	private void drawBullets(Graphics g) {
		for (Bullet item : WholeObjectsSingleton.getInstance().getBulletSeries()) {

			g.drawImage(bulletImg, 
					item.getPositionX(), item.getPositionY(), 
					item.getPositionX() + item.getWidth(),item.getPositionY() + item.getHeigth(),
					0, 0, trigger.getWidth(), trigger.getPositionY(), null);
		}

	}

	private void drawObjectives(Graphics g) {
		for (Objective item : WholeObjectsSingleton.getInstance().getObjectiveSeries()) {
			g.drawImage(objectiveImg, item.getPositionX(), item.getPositionY(), item.getPositionX() + item.getWidth(),
					item.getPositionY() + item.getHeigth(), item.getSequenceIndex() * item.getWidth(), 0,
					item.getSequenceIndex() * item.getWidth() + item.getWidth(), item.getHeigth(), null);
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {

		case KeyEvent.VK_RIGHT:
			trigger.setDirection(Directions.RIGHT);
			if (trigger.getPositionX() < this.getWidth() - trigger.getWidth()) {
				trigger.move();
				trigger.setSequenceIndex(getTriggerMoveRightIndex());				
			}
				
			break;
		case KeyEvent.VK_LEFT:
			trigger.setDirection(Directions.LEFT);
			if (trigger.getPositionX() > 0) {
				trigger.move();
				trigger.setSequenceIndex(getTriggerMoveLeftIndex());
			}
				
			break;
		case KeyEvent.VK_DOWN:
			Objective objective = new Objective(sequenceCountObjective, objectiveImg.getHeight(),objectiveImg.getHeight());
			objective.setContainerHeigth(this.getHeight());
			objective.setContainerWidth(this.getWidth());
			WholeObjectsSingleton.getInstance().getObjectiveSeries().add(objective);
			break;
		case KeyEvent.VK_UP:
			int posx = trigger.getPositionX() + (trigger.getWidth() / 2);
			int posy = trigger.getPositionY();
			Bullet bullet = new Bullet(posx, posy, bulletImg.getHeight(), bulletImg.getWidth(), bulletSpeed);
			trigger.setSequenceIndex(triggerShootsIndex);
			WholeObjectsSingleton.getInstance().getBulletSeries().add(bullet);
			break;
		}

	}

	private int getTriggerMoveRightIndex() {
		if (trigger.getSequenceIndex() + 1 < sequenceCountTrigger && trigger.getSequenceIndex() >= triggerRightMoveIndex)
			return trigger.getSequenceIndex() + 1;
		else
			return triggerRightMoveIndex;
	}

	private int getTriggerMoveLeftIndex() {
		if (trigger.getSequenceIndex() < triggerRightMoveIndex && trigger.getSequenceIndex() >= triggerLeftMoveIndex)
			return trigger.getSequenceIndex() + 1;
		else
			return triggerLeftMoveIndex;
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void move() {

		ArrayList<Objective> objectives = WholeObjectsSingleton.getInstance().getObjectiveSeries();
		ArrayList<Bullet> bullets = WholeObjectsSingleton.getInstance().getBulletSeries();
		if (objectives.size() != 0) {
			for (int cont = 0; cont < objectives.size(); cont++) {
				Objective objective = objectives.get(cont);
				objective.move();
			}
		}

		if (bullets.size() != 0) {
			for (int cont = 0; cont < bullets.size(); cont++) {
				Bullet bullet = bullets.get(cont);
				if (bullet.getPositionX() == (bullet.getHeigth() * -1))
					bullets.remove(cont);
				bullet.move();
			}
		}
		detectCollision(objectives, bullets);

	}

	private void detectCollision(ArrayList<Objective> objectives, ArrayList<Bullet> bullets) {

		if (objectives.size() != 0) {
			for (int cont = 0; cont < objectives.size(); cont++) {
				Objective objective = objectives.get(cont);
				if (bullets.size() != 0) {
					for (int i = 0; i < bullets.size(); i++) {
						Bullet bullet = bullets.get(i);

						if (objective.getRectangle().intersects(bullet.getRectangle())) {
							objective.exploit();
							bullet.delete();
						}
					}

				}
			}
		}

	}

}
