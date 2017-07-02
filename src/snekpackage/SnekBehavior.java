package snekpackage;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import javax.swing.Timer;

public class SnekBehavior {

	private final int ALL_DOTS = 900;

	private int dots = 10;
	
	// Snek and food size
	private final int DOT_SIZE = 10;
	
	// Stores coordinates of snek
	private final int snekX[] = new int[ALL_DOTS];
	private final int snekY[] = new int[ALL_DOTS];
	
	public int[] getSnekX() {
		return snekX;
	}

	public int[] getSnekY() {
		return snekY;
	}

	public int getDots() {
		return dots;
	}

	public void setDots(int dots) {
		this.dots = dots;
	}

	public void positionSnek(int headX, int headY) {
		// placing snek
		for (int z = 0; z < dots; z++) {
			snekX[z] = headX - z * DOT_SIZE;
			snekY[z] = headY;
		}
	}
	
	public void positionSnek() {
		// placing snek
		for (int z = 0; z < dots; z++) {
			snekX[z] = 150 - z * 10;
			snekY[z] = 150;
		}
	}
	
	public boolean isEating(int appleX, int appleY) {
		if ((snekX[0] == appleX) && (snekY[0] == appleY)) {
			// snek grows after eating food
			dots++;

			// randomly placing new food
			return true;
		}
		return false;
	}
	
	public void move(boolean leftDirection, boolean rightDirection, boolean upDirection, boolean downDirection) {
		for (int z = dots; z > 0; z--) {
			snekX[z] = snekX[z - 1];
			snekY[z] = snekY[z - 1];
		}

		// placing snek's head
		if (leftDirection) {
			snekX[0] -= DOT_SIZE;
		}

		if (rightDirection) {
			snekX[0] += DOT_SIZE;
		}

		if (upDirection) {
			snekY[0] -= DOT_SIZE;
		}

		if (downDirection) {
			snekY[0] += DOT_SIZE;
		}
	}

	
	public void moveTowardApple(int appleX, int appleY) {
		try {
			Robot robot = new Robot();

			if (snekX[0] < appleX) {
				// GO TO RIGHT
				robot.keyPress(KeyEvent.VK_RIGHT);
			} else if (snekX[0] > appleX) {
				// GO TO LEFT
				robot.keyPress(KeyEvent.VK_LEFT);
			} else {
				if (snekY[0] < appleY) {
					// GO DOWN
					robot.keyPress(KeyEvent.VK_DOWN);
				} else if (snekY[0] > appleY) {
					robot.keyPress(KeyEvent.VK_UP);
				}
			}
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public boolean isStillInBoard(int B_HEIGHT, int B_WIDTH) {

		// checking if snek ran into a wall
		if (snekY[0] >= B_HEIGHT) {
			return false;
		}
		if (snekY[0] < 0) {
			return false;
		}
		if (snekX[0] >= B_WIDTH) {
			return false;
		}
		if (snekX[0] < 0) {
			return false;
		}
		return true;
	}
	
	public boolean snekBitesItself(){
		// checking if snek bites itself
		for (int z = dots; z > 0; z--) {
			if ((z > 4) && (snekX[0] == snekX[z]) && (snekY[0] == snekY[z])) {
				return true;
			}
		}
		return false;
	}
}
