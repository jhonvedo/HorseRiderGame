package final_curso;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;



public class CPanel extends JPanel implements KeyListener{
	private Motor motor;
	private BufferedImage esfera,montado,flecha,fondo;
	private Montado mont;	
	
	public CPanel(Motor motor){
		
		this.motor=motor;
		this.setSize(600,600);
		this.cargarimagenes();
		mont=new Montado(motor);
		mont.setPosY(this.getHeight()-mont.getMonalto()+30);	
			
	}


	private void cargarimagenes() {
	try{
		fondo=ImageIO.read(getClass().getClassLoader().getResource("fondo.jpg"));
		esfera=ImageIO.read(getClass().getClassLoader().getResource("globo.png"));
		montado=ImageIO.read(getClass().getClassLoader().getResource("montado.png"));
		flecha=ImageIO.read(getClass().getClassLoader().getResource("fle.png"));
	}catch(Exception e){
		
	}		
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);		
		g.setColor(Color.white);
		g.fillRect(0,0,600,600);
		g.drawImage(fondo,0,0,600,600,null);
		Dibujar(g);		
		
	}


	private void Dibujar(Graphics g) {
		if(motor.obtenerseriebolas().size() != 0){
			ArrayList<Esfera> aux = motor.obtenerseriebolas();
			for(int cont=0; cont<aux.size(); cont++){
			Esfera e = aux.get(cont);				
			g.drawImage(esfera,e.getPosX(),e.getPosY(),e.getPosX()+e.getBolancho(),e.getPosY()+e.getBolalto(),e.getNumdibujo()*e.getBolancho(),0,e.getNumdibujo()*e.getBolancho()+e.getBolancho(),e.getBolalto(),null);
			}
		}
		g.drawImage(montado,mont.getPosX() ,mont.getPosY() ,mont.getPosX()+mont.getMonancho(),mont.getPosY()+mont.getMonalto() ,mont.getNumdibujo()*mont.getMonancho(),0,mont.getNumdibujo()*mont.getMonancho()+mont.getMonancho(),mont.getMonalto(), null);
		

		if(motor.obtenerseriebalas().size() != 0){
			ArrayList<Bala> aux = motor.obtenerseriebalas();
		
		for(int cont=0; cont<aux.size(); cont++){
			Bala e = aux.get(cont);
		g.drawImage(flecha,e.getPosX(),e.getPosY() ,e.getPosX()+e.getBalancho(),e.getPosY()+e.getBalalto() ,0,0,mont.getMonancho(),mont.getMonalto(), null);
		}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {

		case KeyEvent.VK_RIGHT:
			if(mont.getPosX()<this.getWidth()-mont.getMonancho())
			mont.MovDer();		
			
			if(mont.getNumdibujo()+1<4)
				mont.setNumdibujo(mont.getNumdibujo()+1);
			else
				mont.setNumdibujo(0);
			break;
		case KeyEvent.VK_LEFT:
			if(mont.getPosX()>0)				
			mont.MovIzq();
			
			
			if(mont.getNumdibujo()+1<4)
				mont.setNumdibujo(mont.getNumdibujo()+1);
			else
				mont.setNumdibujo(0);			
			break;
		case KeyEvent.VK_DOWN:
			motor.obtenerseriebolas().add(new Esfera(motor));		
			break;
		case KeyEvent.VK_UP:
			motor.obtenerseriebalas().add(new Bala(mont.getPosX()+(mont.getMonancho()/2),this.getHeight()-mont.getMonalto()+30,motor));		
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
	
public void mover(){

		ArrayList<Esfera> bola = motor.obtenerseriebolas();
		ArrayList<Bala> bala = motor.obtenerseriebalas();
		if(motor.obtenerseriebolas().size() != 0){			
			for(int cont=0; cont<bola.size(); cont++){
			Esfera e = bola.get(cont);
			e.moverBola(this.getWidth(), this.getHeight());			
			}	
		}
		
		if(motor.obtenerseriebalas().size() != 0){		
			for(int cont=0; cont<bala.size(); cont++){
			Bala a = bala.get(cont);
			if(a.getPosX()==(a.getBalalto()*-1))
			bala.remove(cont);			
			a.mover();
			}
		}
		DetectarColision(bola,bala);

	
	}


	private void DetectarColision(ArrayList<Esfera> bola, ArrayList<Bala> bala) {

		
		if(motor.obtenerseriebolas().size() != 0){			
			for(int cont=0; cont<bola.size(); cont++){
			Esfera e = bola.get(cont);
			if(motor.obtenerseriebalas().size() != 0){		
				for(int i=0; i<bala.size(); i++){
				Bala a = bala.get(i);
				
				if(e.getRectEsfera().intersects(a.getRectBala())){									
						e.iniciar();
						a.eliminar();
						
				}
			}
			
			}
		}
		
		
		}
		
	}



}
