package edu.ncsu.csc326.coffeemaker;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;
import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * 
 * @author Sarah Heckman
 *
 * Extended by Mike Whalen
 *
 * Unit tests for CoffeeMaker class.
 */

public class CoffeeMakerTest {
	
	private Recipe r1;
	private Recipe r2;
	private Recipe r3;
	private Recipe r4;
	private Recipe r5;
	private CoffeeMaker cm;
	private RecipeBook recipeBookStub;
	private Recipe [] stubRecipies; 
	
	protected void setUp() throws Exception {
		
		cm = new CoffeeMaker();
		
		//Set up for r1
		r1 = new Recipe();
		r1.setName("Coffee");
		r1.setAmtChocolate("0");
		r1.setAmtCoffee("3");
		r1.setAmtMilk("1");
		r1.setAmtSugar("1");
		r1.setPrice("50");
		
		//Set up for r2
		r2 = new Recipe();
		r2.setName("Mocha");
		r2.setAmtChocolate("20");
		r2.setAmtCoffee("3");
		r2.setAmtMilk("1");
		r2.setAmtSugar("1");
		r2.setPrice("75");
		
		//Set up for r3
		r3 = new Recipe();
		r3.setName("Latte");
		r3.setAmtChocolate("0");
		r3.setAmtCoffee("3");
		r3.setAmtMilk("3");
		r3.setAmtSugar("1");
		r3.setPrice("100");
		
		//Set up for r4
		r4 = new Recipe();
		r4.setName("Hot Chocolate");
		r4.setAmtChocolate("4");
		r4.setAmtCoffee("0");
		r4.setAmtMilk("4");
		r4.setAmtSugar("1");
		r4.setPrice("65");
		
		//Set up for r5 (added by MWW)
		r5 = new Recipe();
		r5.setName("Super Hot Chocolate");
		r5.setAmtChocolate("6");
		r5.setAmtCoffee("0");
		r5.setAmtMilk("5");
		r5.setAmtSugar("1");
		r5.setPrice("100");

		stubRecipies = new Recipe [] {r1, r2, r3,r4};

	}
	
	
	// put your tests here!

	@Test
	public void testAddInventory1() throws InventoryException {
		cm.addInventory("4","7","0","9");
		cm.checkInventory();
	}

	@Test
	public void testAddInventory2() throws InventoryException {
		cm.addInventory("4","7","3","9");

	}

	@Test(expected = InventoryException.class)
	public void testAddInventoryException2() throws InventoryException {
		cm.addInventory("3", "3", "0", "-1");
	}

	@Test(expected = InventoryException.class)
	public void testAddInventoryException3() throws InventoryException {
		cm.addInventory("3", "3", "0", "smf");
	}

	@Test(expected = InventoryException.class)
	public void testAddInventoryException1() throws InventoryException {
		cm.addInventory("4", "-1", "asdf", "3");
	}


	@Test(expected = RecipeException.class)
	public void testAddRecipe1() throws RecipeException {
		Recipe r6 = new Recipe();
		r6.setName("Super Hot Chocolate");
		r6.setAmtChocolate("6");
		r6.setAmtCoffee("0");
		r6.setAmtMilk("-1");
		r6.setAmtSugar("1");
		r6.setPrice("100");
		cm.addRecipe(r6);
	}

	@Test(expected = RecipeException.class)
	public void testAddRecipe2() throws RecipeException {
		Recipe r6 = new Recipe();
		r6.setName("Super Hot Chocolate");
		r6.setAmtChocolate("smf");
		r6.setAmtCoffee("0");
		r6.setAmtMilk("3");
		r6.setAmtSugar("1");
		r6.setPrice("100");
		cm.addRecipe(r6);
	}

	@Test(expected = RecipeException.class)
	public void testAddRecipe3() throws RecipeException {
		Recipe r6 = new Recipe();
		r6.setName("Super Hot Chocolate");
		r6.setAmtChocolate("-1");
		r6.setAmtCoffee("0");
		r6.setAmtMilk("3");
		r6.setAmtSugar("1");
		r6.setPrice("100");
		cm.addRecipe(r6);
	}

	@Test(expected = RecipeException.class)
	public void testAddRecipe4() throws RecipeException {
		Recipe r6 = new Recipe();
		r6.setName("Super Hot Chocolate");
		r6.setAmtChocolate("5");
		r6.setAmtCoffee("0");
		r6.setAmtMilk("smf");
		r6.setAmtSugar("1");
		r6.setPrice("100");
		cm.addRecipe(r6);
	}

	@Test(expected = RecipeException.class)
	public void testAddRecipe5() throws RecipeException {
		Recipe r6 = new Recipe();
		r6.setName("Super Hot Chocolate");
		r6.setAmtChocolate("5");
		r6.setAmtCoffee("0");
		r6.setAmtMilk("5");
		r6.setAmtSugar("smf");
		r6.setPrice("100");
		cm.addRecipe(r6);
	}

	@Test(expected = RecipeException.class)
	public void testAddRecipe6() throws RecipeException {
		Recipe r6 = new Recipe();
		r6.setName("Super Hot Chocolate");
		r6.setAmtChocolate("5");
		r6.setAmtCoffee("0");
		r6.setAmtMilk("5");
		r6.setAmtSugar("-1");
		r6.setPrice("100");
		cm.addRecipe(r6);
	}

	@Test(expected = RecipeException.class)
	public void testAddRecipe7() throws RecipeException {
		Recipe r6 = new Recipe();
		r6.setName("Super Hot Chocolate");
		r6.setAmtChocolate("5");
		r6.setAmtCoffee("0");
		r6.setAmtMilk("5");
		r6.setAmtSugar("0");
		r6.setPrice("-1");
		cm.addRecipe(r6);
	}

	@Test(expected = RecipeException.class)
	public void testAddRecipe8() throws RecipeException {
		Recipe r6 = new Recipe();
		r6.setName("Super Hot Chocolate");
		r6.setAmtChocolate("5");
		r6.setAmtCoffee("0");
		r6.setAmtMilk("5");
		r6.setAmtSugar("0");
		r6.setPrice("smf");
		cm.addRecipe(r6);
	}

	@Test
	public void testMakeCoffee1() {

		cm.addRecipe(r1);
		assertEquals(25, cm.makeCoffee(0, 75));

	}

	@Test
	public void testMakeCoffee2() {
		cm.addRecipe(r1);

		assertEquals(75, cm.makeCoffee(5, 75));
	}

	@Test
	public void testMakeCoffee22() {
		cm.addRecipe(r1);

		assertEquals(20, cm.makeCoffee(0, 20));
	}

	@Test
	public void testMakeCoffee3() {
		cm.addRecipe(r1);

		assertEquals(75, cm.makeCoffee(-1, 75));
	}

	@Test
	public void testMakeCoffee4() {
		assertEquals(null,cm.deleteRecipe(-1));
	}


	@Test
	public void testMakeCoffee5() {
		assertEquals(null,cm.deleteRecipe(5));

	}

	@Test
	public void testMakeCoffee6() {
		assertEquals(null,cm.editRecipe(-1,r3));
	}

	@Test
	public void testMakeCoffee7() {
		assertEquals(null,cm.editRecipe(5,r3));

	}

	@Test
	public void testMakeCoffee8() {
		cm.addRecipe(r1);
		cm.editRecipe(0,r3);
		Recipe[] recipeBook = cm.getRecipes();
		assertEquals("Coffee",recipeBook[0].getName());
	}

	@Test
	public void testMakeCoffee9() {
		cm.addRecipe(r1);
		cm.makeCoffee(0,75);

		assertEquals("Coffee: 12\n" +
				"Milk: 14\n" +
				"Sugar: 14\n" +
				"Chocolate: 15\n",cm.checkInventory());
	}


	@Test
	public void testMakeCoffee10() {
		cm.addRecipe(r1);
		cm.deleteRecipe(0);
		Recipe[] recipes = cm.getRecipes();

		assertEquals(null,recipes[0]);

	}


	@Test

	public void testMakeCoffee12(){

		cm.addRecipe(r1);

		assertEquals(25,cm.makeCoffee(0,75));

	}

	@Test

	public void testDeleteRecipe1()

	{cm.addRecipe(r1);

		cm.addRecipe(r2);

		cm.addRecipe(r3);

		cm.addRecipe(r4);

		assertEquals("Coffee",cm.deleteRecipe(0));

		assertEquals("Mocha",cm.deleteRecipe(1));

		assertEquals("Latte",cm.deleteRecipe(2));

		assertEquals("Hot Chocolate",cm.deleteRecipe(3));

	}

	@Test

	public void testMakeCoffee11(){

		cm.addRecipe(r1);

		cm.addRecipe(r2);

		cm.addRecipe(r3);

		cm.addRecipe(r4);

		assertEquals(75,cm.makeCoffee(4,75));

	}


}
