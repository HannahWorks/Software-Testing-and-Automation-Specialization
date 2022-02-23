
package edu.ncsu.csc326.coffeemaker;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;
import org.mockito.ArgumentCaptor;
/* EasySolutions- The BrainStromer */

/*
Unit tests for CoffeeMaker class.
 */

public class CoffeeMakerTest {

    //-----------------------------------------------------------------------
    //	DATA MEMBERS
    //-----------------------------------------------------------------------
    private Recipe recipe1;
    private Recipe recipe2;
    private Recipe recipe3;
    private Recipe recipe4;
    private Recipe recipe5;

    private Recipe [] stubRecipies;

    /**
     * The coffee maker -- our object under test.
     */
    private CoffeeMaker coffeeMaker;
    private Inventory inv;

    /**
     * The stubbed recipe book.
     */
    private RecipeBook recipeBookStub;


    //-----------------------------------------------------------------------
    //	Set-up / Tear-down
    //-----------------------------------------------------------------------
    /**
     * Initializes some recipes to test with, creates the {@link CoffeeMaker}
     * object we wish to test, and stubs the {@link RecipeBook}.
     *
     * @throws RecipeException  if there was an error parsing the ingredient
     * 		amount when setting up the recipe.
     * 	**/

     @Test
     public void testMakeCoffee1() {
     when(recipeBookStub.getRecipes()).thenReturn(stubRecipies);
     coffeeMaker.addRecipe(stubRecipies[0]);
     assertEquals(75, coffeeMaker.makeCoffee(1, 75));
     }
     @Test
     public void testMakeCoffee2() {
     when(recipeBookStub.getRecipes()).thenReturn(stubRecipies);
     coffeeMaker.addRecipe(stubRecipies[0]);
     assertEquals(25, coffeeMaker.makeCoffee(0, 75));
     }
     @Test
     public void testMakeCoffee3() {
     when(recipeBookStub.addRecipe(stubRecipies[0])).thenReturn(true);
     when(recipeBookStub.addRecipe(stubRecipies[1])).thenReturn(true);
     when(recipeBookStub.addRecipe(stubRecipies[2])).thenReturn(true);
     when(recipeBookStub.addRecipe(stubRecipies[2])).thenReturn(false);
     }
     @Test
     public void testMakeCoffee4() {
     when(recipeBookStub.addRecipe(stubRecipies[0])).thenReturn(true);
     when(recipeBookStub.addRecipe(stubRecipies[1])).thenReturn(true);
     when(recipeBookStub.addRecipe(stubRecipies[1])).thenReturn(false);
     when(recipeBookStub.addRecipe(recipe4)).thenReturn(false);
     when(recipeBookStub.addRecipe(stubRecipies[2])).thenReturn(true);
     }
     @Test
     public void testDeleteRecipe()
     {   coffeeMaker.addRecipe(stubRecipies[0]);
     coffeeMaker.addRecipe(stubRecipies[1]);
     coffeeMaker.addRecipe(stubRecipies[2]);
     //coffeeMaker.addRecipe(stubRecipies[3]);
     when(recipeBookStub.deleteRecipe(0)).thenReturn("Coffee");
     when(recipeBookStub.deleteRecipe(1)).thenReturn("Mocha");
     when(recipeBookStub.deleteRecipe(2)).thenReturn("Latte");
     when(recipeBookStub.deleteRecipe(3)).thenReturn(null);
     }

     /*----------------------------------------------------------*/
    @Test
    public void testDummy() throws RecipeException {
        when(recipeBookStub.getRecipes()).thenReturn(stubRecipies);
        coffeeMaker.addRecipe(stubRecipies[2]);
        assertEquals(stubRecipies[2].getName(), "Latte");
        assertEquals(stubRecipies[2].getAmtChocolate(), 0);
    }

    @Test
    public void testDummy2() throws Exception {
        when(recipeBookStub.getRecipes()).thenReturn(stubRecipies);
        coffeeMaker.addRecipe(stubRecipies[2]);
        assertEquals(stubRecipies[2].getName(), "Latte");
        assertEquals(stubRecipies[2].getAmtChocolate(), 0);
        assertEquals(stubRecipies[2].getAmtCoffee(), 3);
        assertEquals(stubRecipies[2].getAmtMilk(), 3);
        assertEquals(stubRecipies[2].getAmtSugar(), 1);
        assertEquals(stubRecipies[2].getPrice(), 100);
    }
/*
	@Test
	public void testException5() throws InventoryException {
		coffeeMaker.addInventory("16", "16", "-6", "16");
	}
	@Test
	public void testException7() throws InventoryException {
		coffeeMaker.addInventory("1", "2", "3", "4");
	}*/
}