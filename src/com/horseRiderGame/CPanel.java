package com.horseRiderGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.horseRiderGame.Objects.Bullet;
import com.horseRiderGame.Objects.HorseRider;
import com.horseRiderGame.Objects.Objective;
import com.hourseRiderGame.model.Directions;

public class CPanel extends JPanel implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BufferedImage objectiveImg, horseriderImg, bulletImg, wallpaperImg;
	private HorseRider horserider;	
	private final int sequenceCountObjective = 5;

	public CPanel() throws IOException {
		this.loadImages();
		horserider = new HorseRider(4, 22, horseriderImg.getHeight(), horseriderImg.getHeight());
	}

	public void setPanelSize(int width, int height) {
		this.setSize(width, height);
		horserider.setPositionY(height - horserider.getHeigth() - 85);
	}

	private void loadImages() throws IOException {
		wallpaperImg = ImageIO.read(new File("src/images/fondo.jpg"));
		objectiveImg = ImageIO.read(new File("src/images/globo.png"));
		horseriderImg = ImageIO.read(new File("src/images/montado.png"));
		bulletImg = ImageIO.read(new File("src/images/bullet.png"));
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());		
		g.drawImage(wallpaperImg, 0, 0, this.getWidth(), this.getHeight(), null);
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
		g.drawImage(horseriderImg, horserider.getPositionX(), horserider.getPositionY(),
				horserider.getPositionX() + horserider.getWidth(), horserider.getPositionY() + horserider.getHeigth(),
				horserider.getSequenceIndex() * horserider.getWidth(), 0,
				horserider.getSequenceIndex() * horserider.getWidth() + horserider.getWidth(), horserider.getHeigth(),
				null);

	}

	private void drawBullets(Graphics g) {
		for (Bullet item : WholeObjectsSingleton.getInstance().getBulletSeries()) {

			g.drawImage(bulletImg, 
					item.getPositionX(), item.getPositionY(), 
					item.getPositionX() + item.getWidth(),item.getPositionY() + item.getHeigth(),
					0, 0, horserider.getWidth(), horserider.getPositionY(), null);
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
			horserider.setDirection(Directions.RIGHT);
			if (horserider.getPositionX() < this.getWidth() - horserider.getWidth())
				horserider.move();

			if (horserider.getSequenceIndex() + 1 < 7 && horserider.getSequenceIndex() + 1 >= 5)
				horserider.setSequenceIndex(horserider.getSequenceIndex() + 1);
			else
				horserider.setSequenceIndex(4);
			break;
		case KeyEvent.VK_LEFT:
			horserider.setDirection(Directions.LEFT);
			if (horserider.getPositionX() > 0)
				horserider.move();

			if (horserider.getSequenceIndex() + 1 < 4 && horserider.getSequenceIndex() + 1 >= 2)
				horserider.setSequenceIndex(horserider.getSequenceIndex() + 1);
			else
				horserider.setSequenceIndex(1);
			break;
		case KeyEvent.VK_DOWN:
			Objective objective = new Objective(sequenceCountObjective, objectiveImg.getHeight(),objectiveImg.getHeight());
			objective.setContainerHeigth(this.getHeight());
			objective.setContainerWidth(this.getWidth());
			WholeObjectsSingleton.getInstance().getObjectiveSeries().add(objective);
			break;
		case KeyEvent.VK_UP:
			int posx = horserider.getPositionX() + (horserider.getWidth() / 2);
			int posy = horserider.getPositionY();
			Bullet bullet = new Bullet(posx, posy, bulletImg.getHeight(), bulletImg.getWidth(), 5);
			horserider.setSequenceIndex(0);
			WholeObjectsSingleton.getInstance().getBulletSeries().add(bullet);
			break;
		}

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
