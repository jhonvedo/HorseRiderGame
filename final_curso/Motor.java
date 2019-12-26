package final_curso;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Motor extends Thread {
	private ArrayList<Esfera> Seriebolas;
	private ArrayList<Bala> Seriebalas;
	private CFrame ventana;
	

	public Motor() {
		ventana=new CFrame(this);
		Seriebolas = new ArrayList<Esfera>();
		Seriebalas = new ArrayList<Bala>();		
		this.start();
	}

	public void run(){
		while(true){
			try{
				Thread.sleep(20);
				
			}catch(Exception e){
				e.printStackTrace();
			}
			finally{				
				ventana.obtienePanel().mover();						
				ventana.obtienePanel().repaint();				
			}
		}
		
		
		
		
	}
	public ArrayList<Esfera> obtenerseriebolas(){
		return Seriebolas;
	}
	public ArrayList<Bala> obtenerseriebalas(){
		return Seriebalas;
	}
}
