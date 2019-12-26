package final_curso;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

public class Esfera extends Thread{
private Motor motor;
private  int bolancho;
private  int bolalto;
private int PosX;
private int PosY;
private int velX;
private int velY;
private int numseries;
private int numdibujo;
private Random rnd;
private boolean explotar;

	public Esfera(Motor motor) {
		rnd=new Random();
		this.motor=motor;
		this.PosX=0;
		this.PosY=0;
		this.velX=rnd.nextInt(5)+1;
		this.velY=rnd.nextInt(5)+1;
		this.numseries=6;
		this.numdibujo=0;
		this.bolalto=38;
		this.bolancho=42;
		this.explotar=false;
		this.start();
		
	}
	public int getNumseries() {
		return numseries;
	}
	public int getBolancho() {
		return bolancho;
	}
	public int getBolalto() {
		return bolalto;
	}
	public int getVelY() {
		return velY;
	}
	public int getVelX() {
		return velX;
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
	public int getNumdibujo() {
		return numdibujo;
	}
	public void setNumdibujo(int numdibujo) {
		this.numdibujo = numdibujo;
	}
	
	public void iniciar(){		
			this.explotar=true;
	}
	public void run(){
		while(numdibujo < numseries){
			try{
				Thread.sleep(100);
			}catch(Exception e){
				e.printStackTrace();
			}
			finally{
				if(explotar==true)
				numdibujo++;
			}
		}
		ArrayList alSecuencias = motor.obtenerseriebolas();
		for(int cont=0; cont<alSecuencias.size(); cont++){
			if(alSecuencias.get(cont)==this){
				alSecuencias.remove(cont);
			}
		}
	}
	public void setVelX(int velX) {
		this.velX = velX;
	}
	public void setVelY(int velY) {
		this.velY = velY;
	}
	
	
	public void moverBola(int ancho,int alto) {
		
			
			if (this.getPosX() >  ancho - this.getBolancho() )
				this.setVelX(this.getVelX()*-1);					
				
			if (this.getPosX()<0)			
				this.setVelX(this.getVelX()*-1);		
				
			if (this.getPosY() > alto/2- this.getBolalto())
				this.setVelY(this.getVelY()*-1);		
				
			if (this.getPosY()<0)			
				this.setVelY(this.getVelY()*-1);
				
			
			this.setPosX(this.getPosX()+this.getVelX());
			this.setPosY(this.getPosY()+this.getVelY());	
			
			
								
		
		
	}
	public Rectangle getRectEsfera() {
		return new Rectangle(PosX,PosY,bolancho,bolalto);
	}
	
	
}
