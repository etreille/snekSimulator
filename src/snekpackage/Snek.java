package snekpackage;
import java.awt.EventQueue;

import javax.swing.JFrame;

public class Snek extends JFrame{
	public Snek(){
		add(new SnekBoard());
		
		setResizable(false);
		pack();
		
		setTitle("Snek Simulator");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable(){
			@Override
			public void run() {
				JFrame ex = new Snek();
				ex.setVisible(true);
			}
		});
	}
}