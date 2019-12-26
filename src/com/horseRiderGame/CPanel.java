package com.horseRiderGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class CPanel extends JPanel implements KeyListener {
	private Motor motor;
	private BufferedImage objectiveImg, horseriderImg, bulletImg, wallpaperImg;
	private HorseRider horserider;

	public CPanel(Motor motor) {

		this.motor = motor;
		this.setSize(600, 600);
		this.loadImages();
		horserider = new HorseRider(motor);
		horserider.setPositionY(this.getHeight() - horserider.getHeigth() + 30);

	}

	private void loadImages() {
		try {

			wallpaperImg = ImageIO.read(new File("src/images/fondo.jpg"));
			objectiveImg = ImageIO.read(new File("src/images/globo.png"));
			horseriderImg = ImageIO.read(new File("src/images/montado.png"));
			bulletImg = ImageIO.read(new File("src/images/fle.png"));
		} catch (Exception e) {

		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.white);
		g.fillRect(0, 0, 600, 600);
		g.drawImage(wallpaperImg, 0, 0, 600, 600, null);
		draw(g);

	}

	private void draw(Graphics g) {
		if (motor.getObjectiveSeries().size() != 0) {
			ArrayList<Objective> aux = motor.getObjectiveSeries();
			for (int cont = 0; cont < aux.size(); cont++) {
				Objective e = aux.get(cont);
				g.drawImage(objectiveImg, e.getPositionX(), e.getPositionY(), e.getPositionX() + e.getWidth(),
						e.getPositionY() + e.getHeigth(), e.getBoxNumber() * e.getWidth(), 0,
						e.getBoxNumber() * e.getWidth() + e.getWidth(), e.getHeigth(), null);
			}
		}
		g.drawImage(horseriderImg, horserider.getPositionX(), horserider.getPositionY(), horserider.getPositionX() + horserider.getWidth(),
				horserider.getPositionY() + horserider.getHeigth(), horserider.getBoxNumber() * horserider.getWidth(), 0,
				horserider.getBoxNumber() * horserider.getWidth() + horserider.getWidth(), horserider.getHeigth(), null);

		if (motor.getBulletSeries().size() != 0) {
			ArrayList<Bullet> aux = motor.getBulletSeries();

			for (int cont = 0; cont < aux.size(); cont++) {
				Bullet e = aux.get(cont);
				g.drawImage(bulletImg, e.getPositionX(), e.getPositionY(), e.getPositionX() + e.getWidth(), e.getPositionY() + e.getHeigh(), 0,
						0, horserider.getWidth(), horserider.getHeigth(), null);
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {

		case KeyEvent.VK_RIGHT:
			if (horserider.getPositionX() < this.getWidth() - horserider.getWidth())
				horserider.moveRight();

			if (horserider.getBoxNumber() + 1 < 4)
				horserider.setBoxNumber(horserider.getBoxNumber() + 1);
			else
				horserider.setBoxNumber(0);
			break;
		case KeyEvent.VK_LEFT:
			if (horserider.getPositionX() > 0)
				horserider.moveLeft();

			if (horserider.getBoxNumber() + 1 < 4)
				horserider.setBoxNumber(horserider.getBoxNumber() + 1);
			else
				horserider.setBoxNumber(0);
			break;
		case KeyEvent.VK_DOWN:
			motor.getObjectiveSeries().add(new Objective(motor));
			break;
		case KeyEvent.VK_UP:
			motor.getBulletSeries().add(new Bullet(horserider.getPositionX() + (horserider.getWidth() / 2),
					this.getHeight() - horserider.getHeigth() + 30, motor));
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

		ArrayList<Objective> bola = motor.getObjectiveSeries();
		ArrayList<Bullet> bala = motor.getBulletSeries();
		if (motor.getObjectiveSeries().size() != 0) {
			for (int cont = 0; cont < bola.size(); cont++) {
				Objective e = bola.get(cont);
				e.move(this.getWidth(), this.getHeight());
			}
		}

		if (motor.getBulletSeries().size() != 0) {
			for (int cont = 0; cont < bala.size(); cont++) {
				Bullet a = bala.get(cont);
				if (a.getPositionX() == (a.getHeigh() * -1))
					bala.remove(cont);
				a.move();
			}
		}
		detectCollision(bola, bala);

	}

	private void detectCollision(ArrayList<Objective> bola, ArrayList<Bullet> bala) {

		if (motor.getObjectiveSeries().size() != 0) {
			for (int cont = 0; cont < bola.size(); cont++) {
				Objective e = bola.get(cont);
				if (motor.getBulletSeries().size() != 0) {
					for (int i = 0; i < bala.size(); i++) {
						Bullet a = bala.get(i);

						if (e.getRectangle().intersects(a.getRectBala())) {
							e.exploit();
							a.delete();

						}
					}

				}
			}

		}

	}

}
