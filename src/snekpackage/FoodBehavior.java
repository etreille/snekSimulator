package snekpackage;

public class FoodBehavior {

	// Food coordinates
	private int appleX;
	public int getAppleX() {
		return appleX;
	}

	public void setAppleX(int appleX) {
		this.appleX = appleX;
	}

	private int appleY;
	public int getAppleY() {
		return appleY;
	}

	public void setAppleY(int appleY) {
		this.appleY = appleY;
	}

	// Snek and food size
	private final int DOT_SIZE = 10;
	// Food random position
	private final int RAND_POS = 29;
	
	public void locateApple() {
		// Generates food
		int r = (int) (Math.random() * RAND_POS);
		appleX = ((r * DOT_SIZE));

		r = (int) (Math.random() * RAND_POS);
		appleY = ((r * DOT_SIZE));
	}

	public void locateApple(int appleX, int appleY) {
		this.appleX = appleX * DOT_SIZE;
		this.appleY = appleY * DOT_SIZE;
	}

	
}
