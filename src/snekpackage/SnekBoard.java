package snekpackage;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.Robot;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SnekBoard extends JPanel implements ActionListener {

	// Size of the board
	private final int B_WIDTH = 300;
	private final int B_HEIGHT = 300;
	// Speed of the game
	private final int DELAY = 140;

	FoodBehavior foodBehav = new FoodBehavior();
	SnekBehavior snekBehav = new SnekBehavior();

	// images
	private Image ball;
	private Image apple;
	private Image head;
	ImageIcon imageIDot;
	ImageIcon imageIApple;
	ImageIcon imageIHead;

	// Moves
	private boolean leftDirection = false;
	private boolean rightDirection = true;
	private boolean upDirection = false;
	private boolean downDirection = false;
	// Snek's in board's game
	private boolean inGame = true;
	private boolean bitItself = false;

	public void setInGame(boolean inGame) {
		this.inGame = inGame;
	}

	private Timer timer;


	public SnekBoard() {

		addKeyListener(new TAdapter());
		setBackground(Color.black);
		setFocusable(true);

		setPreferredSize(new Dimension(getB_WIDTH(), getB_HEIGHT()));
		loadImages();
		initGame();
	}
	

	public Timer getTimer() {
		return timer;
	}

	public boolean isInGame() {
		return inGame;
	}

	public int getB_WIDTH() {
		return B_WIDTH;
	}

	public int getB_HEIGHT() {
		return B_HEIGHT;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		doDrawing(g);
	}

	private void doDrawing(Graphics g) {

		if (isInGame() && !bitItself ) {

			g.drawImage(apple, foodBehav.getAppleX(), foodBehav.getAppleY(), this);

			for (int z = 0; z < snekBehav.getDots(); z++) {
				if (z == 0) {
					g.drawImage(head, snekBehav.getSnekX()[z], snekBehav.getSnekY()[z], this);
				} else {
					g.drawImage(ball, snekBehav.getSnekX()[z], snekBehav.getSnekY()[z], this);
				}
			}
			Toolkit.getDefaultToolkit().sync();
		} else {
			gameOver(g);
		}
	}

	private void gameOver(Graphics g) {

		String msg = "Game Over";
		Font small = new Font("Helvetica", Font.BOLD, 14);
		FontMetrics metr = getFontMetrics(small);

		g.setColor(Color.white);
		g.setFont(small);
		g.drawString(msg, (getB_WIDTH() - metr.stringWidth(msg)) / 2, getB_HEIGHT() / 2);
	}

	// get the images
	private void loadImages() {
		imageIDot = new ImageIcon("src/snekpackage/Images/dot.png");
		ball = imageIDot.getImage();

		imageIApple = new ImageIcon("src/snekpackage/Images/apple.png");
		apple = imageIApple.getImage();

		imageIHead = new ImageIcon("src/snekpackage/Images/head.png");
		head = imageIHead.getImage();
	}

	private void initGame() {
		snekBehav.positionSnek();
		// randomly placing food
		foodBehav.locateApple();

		// starting the timer
		timer = new Timer(DELAY, this);
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (isInGame()) {
			boolean ate = snekBehav.isEating(foodBehav.getAppleX(), foodBehav.getAppleY());
			if (ate){
				foodBehav.locateApple();
			}
			inGame = snekBehav.isStillInBoard( B_HEIGHT, B_WIDTH);
			bitItself = snekBehav.snekBitesItself();
			if(!inGame || bitItself){
				timer.stop();
			}
			snekBehav.move(leftDirection, rightDirection, upDirection, downDirection);
		}
		repaint();
	}

	private class TAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();

			if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
				leftDirection = true;
				upDirection = false;
				downDirection = false;
			}

			if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
				rightDirection = true;
				upDirection = false;
				downDirection = false;
			}

			if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
				downDirection = true;
				leftDirection = false;
				rightDirection = false;
			}

			if ((key == KeyEvent.VK_UP) && (!downDirection)) {
				upDirection = true;
				leftDirection = false;
				rightDirection = false;
			}
		}
	}
}
