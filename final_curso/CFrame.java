package final_curso;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CFrame extends JFrame{
	private Motor motor;
	private CPanel panel;
	
	public CFrame(Motor motor) {		
		super("Tirale a las esferas :p");
		this.motor=motor;		
		this.setLayout(null);	
		this.setSize(600,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);	
		
		panel=new CPanel(motor);
		this.add(panel);
		this.addKeyListener(panel);		
		this.setVisible(true);
		
		
	}

	public CPanel obtienePanel(){
		return panel;
	}

}
