import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SnekBoard extends JPanel implements ActionListener {

	//Size of the board
	private final int B_WIDTH = 300;
	private final int B_HEIGHT = 300;
	//Snek and food size
	private final int DOT_SIZE = 10;
	//Maximum number of possible dots
	private final int ALL_DOTS = 900;
	//Food random position
	private final int RAND_POS = 29;
	//Speed of the game
	private final int DELAY = 140;
	
	//Stores coordinates of snek
	private final int snekX[] = new int[ALL_DOTS];
	private final int snekY[] = new int[ALL_DOTS];
	
	//Moves
	private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    //Snek's in board's game
    private boolean inGame = true;

    private int dots;
    //Food coordinates
    private int appleX;
    private int appleY;
    
    private Timer timer;
    
    //images
    private Image ball;
    private Image apple;
    private Image head;
    
    //get the images
	private void loadImages(){
		ImageIcon imageIDot = new ImageIcon("dot.png");
		ball = imageIDot.getImage();
		
		ImageIcon imageIApple = new ImageIcon("apple.png");
		apple = imageIApple.getImage();
		
		ImageIcon imageIHead = new ImageIcon("head.png");
		head = imageIHead.getImage();
	}
	
	private void initGame(){
		//snek is 3 dots long
		dots = 3;
		
		//placing snek
		for (int z = 0 ; z < dots; z++){
			snekX[z] = 50 - z * 10 ;
			snekY[z] = 50;
		}
		
		//randomly placing food
		locateApple();
		
		//starting the timer
		timer = new Timer(DELAY, this);
		timer.start();
	}
	
	private void checkApple(){
		if((snekX[0] == appleX) && (snekY[0] == appleY)){
			//snek grows after eating food
			dots++;

			//randomly placing new food
			locateApple();
		}
	}
	
	private void move(){
		for(int z = dots ; z > 0 ; z--){
			snekX[z] = snekX[z-1];
			snekY[z] = snekY[z-1];
		}
		
		//placing snek's head
		if(leftDirection){
			snekX[0] -= DOT_SIZE;
		}
		
		if(rightDirection){
			snekX[0] += DOT_SIZE;
		}

		if(upDirection){
			snekY[0] -= DOT_SIZE;
		}

		if(downDirection){
			snekY[0] += DOT_SIZE;
		}
	}
	
	private void checkCollision(){
		//checking if snek bites itself
		for(int z = dots ; z > 0 ; z--){
			if((z > 4) && (snekX[0] == snekX[z]) && (snekY[0] == snekY[z])){
				inGame = false;
			}
		}
		
		//checking if snek ran into a wall
		if (snekY[0] >= B_HEIGHT){
			inGame = false;
		}
		if(snekY[0] < 0){
			inGame = false;
		}
		if (snekX[0] >= B_WIDTH){
			inGame = false;
		}
		if(snekX[0] < 0){
			inGame = false;
		}
		
		if(!inGame){
			timer.stop();
		}
	}
	
	private void locateApple(){
		//Generates food
		int r = (int) (Math.random() * RAND_POS);
		appleX = ((r * DOT_SIZE));
		
		r = (int) (Math.random() * RAND_POS);
		appleY = ((r * DOT_SIZE));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(inGame){
			checkApple();
			checkCollision();
			move();
		}
		repaint();
	}
	
	
	private class TAdapter extends KeyAdapter{
		
		@Override
		public void keyPressed(KeyEvent e){
			int key = e.getKeyCode();
			
			if ((key == KeyEvent.VK_LEFT) && (!rightDirection)){
				leftDirection = true;
				upDirection = false; 
				downDirection = false;
			}
			
			if((key == KeyEvent.VK_RIGHT) && (!leftDirection)){
				rightDirection = true;
				upDirection = false;
				downDirection = false;
			}
			
			if((key == KeyEvent.VK_DOWN) && (!upDirection)){
				downDirection = true;
				leftDirection = false;
				rightDirection = false;
			}
			
			if((key == KeyEvent.VK_UP) && (!downDirection)){
				upDirection = true;
				leftDirection = false;
				rightDirection = false;
			}
		}
	}
}



