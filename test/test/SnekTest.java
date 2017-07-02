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
		snekBehavior.positionSnek(350,150);
		assertEquals(snekBehavior.isStillInBoard(300, 300), false);
		snekBehavior.positionSnek(200,300);
		assertEquals(snekBehavior.isStillInBoard(300, 300), false);
		snekBehavior.positionSnek(-10,150);
		assertEquals(snekBehavior.isStillInBoard(300, 300), false);
		snekBehavior.positionSnek(200,-50);
		assertEquals(snekBehavior.isStillInBoard(300, 300), false);
		snekBehavior.positionSnek(200,20);
		assertEquals(snekBehavior.isStillInBoard(300, 300), true);
		snekBehavior.positionSnek(200,150);
		assertEquals(snekBehavior.isStillInBoard(300, 300), true);
	}
	
	

}
