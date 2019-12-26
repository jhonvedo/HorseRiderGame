package final_curso;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Bala extends Thread{
	
	private Motor motor;
	private int PosX;
	private int PosY;
	private int vel;
	private int balancho;
	private int balalto;

	public int getBalancho() {
		return balancho;
	}


	public int getBalalto() {
		return balalto;
	}
	
	
	public Bala(int posX,int posY,Motor motor) {
		this.motor=motor;
		this.vel=5;
		this.PosX=posX;
		this.PosY=posY;	
		this.balalto=57;
		this.balancho=22;
	
	}


	public int getPosX() {
		return PosX;
	}

	public void setPosX(int posX) {
		PosX = posX;
	}

	public int getPosY() {
		return PosY;
	}

	public void setPosY(int posY) {
		PosY = posY;
	}

	public int getVel() {
		return vel;
	}
	public void mover(){
		this.PosY-=this.vel;
		if(this.PosY<=0)
			this.eliminar();

	}
	public Rectangle getRectBala() {
		return new Rectangle(PosX,PosY,balancho,balalto);
	}
	public void eliminar() {
		ArrayList alSecuencias = motor.obtenerseriebalas();
		for(int cont=0; cont<alSecuencias.size(); cont++){
			if(alSecuencias.get(cont)==this){
				alSecuencias.remove(cont);
			}
		}
	}

}

