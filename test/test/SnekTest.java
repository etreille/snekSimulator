package test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import snekpackage.FoodBehavior;
import snekpackage.SnekBehavior;

public class SnekTest {

	public static SnekBehavior snekBehavior;
	public static FoodBehavior foodBehavior;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		snekBehavior = new SnekBehavior();
		foodBehavior = new FoodBehavior();
	}

	@Test
	public void ShouldDie() {
		snekBehavior.positionSnek(350, 150);
		assertEquals(snekBehavior.isStillInBoard(300, 300), false);
		snekBehavior.positionSnek(200, 300);
		assertEquals(snekBehavior.isStillInBoard(300, 300), false);
		snekBehavior.positionSnek(-10, 150);
		assertEquals(snekBehavior.isStillInBoard(300, 300), false);
		snekBehavior.positionSnek(200, -50);
		assertEquals(snekBehavior.isStillInBoard(300, 300), false);
		snekBehavior.positionSnek(200, 20);
		assertEquals(snekBehavior.isStillInBoard(300, 300), true);
		snekBehavior.positionSnek(200, 150);
		assertEquals(snekBehavior.isStillInBoard(300, 300), true);
	}

	@Test
	public void ShouldBiteItself() {
		snekBehavior.positionSnek(150, 150);
		assertEquals(snekBehavior.snekBitesItself(), false);
		snekBehavior.positionSnek(150, 150);
		snekBehavior.getSnekX()[0] = 90;
		assertEquals(snekBehavior.snekBitesItself(), true);
	}

	@Test
	public void ShouldEat() {
		snekBehavior.positionSnek(160, 160);
		assertEquals(snekBehavior.isEating(160, 160), true);
		snekBehavior.positionSnek(160, 160);
		assertEquals(snekBehavior.isEating(200, 200), false);
	}

}
