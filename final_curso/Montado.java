package final_curso;

public class Montado {
private int posX;
private int posY;
private int numdibujo;
private int move;
private int monancho;
private int monalto;
@SuppressWarnings("unused")
private int numseries;
private Motor motor;
	public Montado(Motor motor) {
		this.motor=motor;
		this.posX=0;
		this.posY=0;
		this.numdibujo=0;
		this.numseries=4;
		this.move=22;
		this.monalto=151;
		this.monancho=152;
		
	}
	
	public int getMonancho() {
		return monancho;
	}

	public int getMonalto() {
		return monalto;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getNumdibujo() {
		return numdibujo;
	}

	public void setNumdibujo(int numdibujo) {
		this.numdibujo = numdibujo;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}
	public void MovDer(){
		this.posX+=this.move;
	}
	public void MovIzq(){
		this.posX+=(this.move*-1);
	}

}
