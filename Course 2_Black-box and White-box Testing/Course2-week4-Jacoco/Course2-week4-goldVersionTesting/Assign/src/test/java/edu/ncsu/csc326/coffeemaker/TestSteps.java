/*
 * Copyright (c) 2009,  Sarah Heckman, Laurie Williams, Dright Ho
 * All Rights Reserved.
 * 
 * Permission has been explicitly granted to the University of Minnesota 
 * Software Engineering Center to use and distribute this source for 
 * educational purposes, including delivering online education through
 * Coursera or other entities.  
 * 
 * No warranty is given regarding this software, including warranties as
 * to the correctness or completeness of this software, including 
 * fitness for purpose.
 * 
 * 
 * Modified 20171114 by Ian De Silva -- Updated to adhere to coding standards.
 * 
 */
package edu.ncsu.csc326.coffeemaker;


import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.ncsu.csc326.coffeemaker.CoffeeMaker;
import edu.ncsu.csc326.coffeemaker.UICmd.*;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;


import javax.swing.*;
import java.util.ArrayList;
import java.util.IllegalFormatConversionException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Contains the step definitions for the cucumber tests.  This parses the 
 * Gherkin steps and translates them into meaningful test steps.
 */
public class TestSteps {
	
	private Recipe recipe1;
	private Recipe recipe2;
	private Recipe recipe3;
	private Recipe recipe4;
	private Recipe recipe5;
	private CoffeeMakerUI coffeeMakerMain;
	private CoffeeMaker coffeeMaker;
	private RecipeBook recipeBook;

	
	private void initialize() {
		recipeBook = new RecipeBook();
		coffeeMaker = new CoffeeMaker(recipeBook, new Inventory());
		coffeeMakerMain = new CoffeeMakerUI(coffeeMaker);
	}
	
    @Given("^an empty recipe book$")
    public void an_empty_recipe_book() throws Throwable {
        initialize();
    }


    @Given("a default recipe book")
	public void a_default_recipe_book() throws Throwable {
    	initialize();
    	
		//Set up for r1
		recipe1 = new Recipe();
		recipe1.setName("Coffee");
		recipe1.setAmtChocolate("0");
		recipe1.setAmtCoffee("3");
		recipe1.setAmtMilk("1");
		recipe1.setAmtSugar("1");
		recipe1.setPrice("50");
		
		//Set up for r2
		recipe2 = new Recipe();
		recipe2.setName("Mocha");
		recipe2.setAmtChocolate("20");
		recipe2.setAmtCoffee("3");
		recipe2.setAmtMilk("1");
		recipe2.setAmtSugar("1");
		recipe2.setPrice("75");
		
		//Set up for r3
		recipe3 = new Recipe();
		recipe3.setName("Latte");
		recipe3.setAmtChocolate("0");
		recipe3.setAmtCoffee("3");
		recipe3.setAmtMilk("3");
		recipe3.setAmtSugar("1");
		recipe3.setPrice("100");
		
		//Set up for r4
		recipe4 = new Recipe();
		recipe4.setName("Hot Chocolate");
		recipe4.setAmtChocolate("4");
		recipe4.setAmtCoffee("0");
		recipe4.setAmtMilk("1");
		recipe4.setAmtSugar("1");
		recipe4.setPrice("65");
		
		//Set up for r5 (added by MWW)
		recipe5 = new Recipe();
		recipe5.setName("Super Hot Chocolate");
		recipe5.setAmtChocolate("6");
		recipe5.setAmtCoffee("0");
		recipe5.setAmtMilk("1");
		recipe5.setAmtSugar("1");
		recipe5.setPrice("100");

		recipeBook.addRecipe(recipe1);
		recipeBook.addRecipe(recipe2);
		recipeBook.addRecipe(recipe3);
		recipeBook.addRecipe(recipe4);
		
	}

	@When("^I entered the option (-?\\d+)$")
	public void i_entered_the_option_userInput(int userInput) throws Throwable {

		// Write code here that turns the phrase above into concrete actions

		Command cmd = new ChooseService(userInput);
		coffeeMakerMain.UI_Input(cmd);
		//System.out.println(coffeeMakerMain.getStatus().toString());

	}

	@When("^user enter the option (.*) it will catch the exception$")
	public void user_enter_the_option_it_will_catch_the_exception(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		try{
			Command cmd = new ChooseService(Integer.parseInt(arg1));
			fail("Test should have failed with conversion exception ");

		}
		catch (IllegalArgumentException e){}

	}

	@When("^user submits a recipe with wrong input it will catch the exception$")
	public void user_submits_a_recipe_with_wrong_input_it_will_catch_the_exception(DataTable recipes) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		// For automatic transformation, change DataTable to one of
		// List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
		// E,K,V must be a scalar (String, Integer, Date, enum etc)

			List<Map<String ,String>> recipeList = recipes.asMaps(String.class, String.class);

			for (Map<String, String> form : recipeList) {
				try {
					Recipe r = new Recipe();
					r.setName(form.get("name"));

					r.setPrice(form.get("price"));

					r.setAmtCoffee(form.get("amtCoffee"));

					r.setAmtSugar(form.get("amtSugar"));

					r.setAmtMilk(form.get("amtMilk"));

					r.setAmtChocolate(form.get("amtChocolate"));

					fail("Test should have failed with at least one wrong input");

					//fail("Test should be failed with at least one wrong input");
				}
				catch (Exception e) {}
			}
	}

	@When("^user delete Recipe (.*) it will catch an exception$")
	public void user_delete_Recipe_xx_it_will_catch_an_exception(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		try {
			Command cmd = new ChooseService(2);
			coffeeMakerMain.UI_Input(cmd);
			Command cmd2 = new ChooseRecipe(Integer.parseInt(arg1));
			coffeeMakerMain.UI_Input(cmd2);

			fail("The test should have failed with wrong input ");
		}
		catch(Exception e){}
	}

	@When("^user edit Recipe with wrong index (.*) it will catch an exception$")
	public void user_edit_Recipe_with_wrong_index_it_will_catch_an_exception(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		try{
			Command cmd = new ChooseService(3);
			coffeeMakerMain.UI_Input(cmd);
			Command cmd2 = new ChooseRecipe(Integer.parseInt(arg1));
			coffeeMakerMain.UI_Input(cmd2);
			fail("The test should have failed with wrong input ");
		}
		catch (Exception e){}

	}

	@When("^user edit recipe (\\d+)$")
	public void user_edit_recipe(int arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		Command cmd = new ChooseService(3);
		coffeeMakerMain.UI_Input(cmd);
		Command cmd2 = new ChooseRecipe(arg1);
		coffeeMakerMain.UI_Input(cmd2);
		Recipe r = new Recipe();
		r.setAmtChocolate("3");
		r.setAmtCoffee("2");
		r.setAmtMilk("5");
		r.setAmtSugar("4");
		r.setPrice("12");
		Command cmd3 = new DescribeRecipe(r);
		coffeeMakerMain.UI_Input(cmd3);

	}

	@When("^user edit recipe -?(\\d+) which doesn't exist$")
	public void user_edit_recipe_which_doesn_t_exist(int arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		Command cmd = new ChooseService(3);
		coffeeMakerMain.UI_Input(cmd);
		Command cmd2 = new ChooseRecipe(arg1);
		coffeeMakerMain.UI_Input(cmd2);
	}

	@When("^user add inventory$")
	public void user_add_inventory(DataTable addInventory) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		// For automatic transformation, change DataTable to one of
		// List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
		// E,K,V must be a scalar (String, Integer, Date, enum etc)
		coffeeMakerMain.UI_Input(new ChooseService(4));
		List<Map<String ,Integer >> add = addInventory.asMaps(String.class, Integer.class);

		Command cmd4 = new AddInventory(add.get(0).get("coffee"),add.get(0).get("milk"),add.get(0).get("sugar"),add.get(0).get("chocolate"));
		coffeeMakerMain.UI_Input(cmd4);
		//System.out.println(coffeeMaker.checkInventory());

	}

	@When("^user add inventory with wrong input it will catch an exception$")
	public void user_add_inventory_with_wrong_input_it_will_catch_an_exception(DataTable addInventory) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		// For automatic transformation, change DataTable to one of
		// List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
		// E,K,V must be a scalar (String, Integer, Date, enum etc)
		List<Map<String ,String >> addList = addInventory.asMaps(String.class, String.class);

		for(Map<String ,String > add : addList )
		{
			try{
				coffeeMaker.addInventory(add.get("coffee"),add.get("milk"),add.get("sugar"),add.get("chocolate"));
				fail("Test should have failed with at least one wrong input");
		}
		catch (Exception e){}
		}

	}

	@Then("^check inventory$")
	public void check_inventory(DataTable inventory) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		// For automatic transformation, change DataTable to one of
		// List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
		// E,K,V must be a scalar (String, Integer, Date, enum etc)

		List<Map<String ,Integer >> inventoryList = inventory.asMaps(String.class, Integer.class);

		StringBuffer buf = new StringBuffer();
		buf.append("Coffee: ");
		buf.append(inventoryList.get(0).get("coffee"));
		buf.append("\n");
		buf.append("Milk: ");
		buf.append(inventoryList.get(0).get("milk"));
		buf.append("\n");
		buf.append("Sugar: ");
		buf.append(inventoryList.get(0).get("sugar"));
		buf.append("\n");
		buf.append("Chocolate: ");
		buf.append(inventoryList.get(0).get("chocolate"));
		buf.append("\n");
		//System.out.println(buf.toString());
		//System.out.println(coffeeMaker.checkInventory());
		assertEquals(buf.toString(),coffeeMaker.checkInventory());

	}

	@Then("^check inventory from the main menu$")
	public void check_inventory_from_the_main_menu(DataTable inventory) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		// For automatic transformation, change DataTable to one of
		// List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
		// E,K,V must be a scalar (String, Integer, Date, enum etc)
		coffeeMakerMain.UI_Input(new CheckInventory());
		//System.out.println(coffeeMakerMain.mode);

		List<Map<String ,Integer >> inventoryList = inventory.asMaps(String.class, Integer.class);

		StringBuffer buf = new StringBuffer();
		buf.append("Coffee: ");
		buf.append(inventoryList.get(0).get("coffee"));
		buf.append("\n");
		buf.append("Milk: ");
		buf.append(inventoryList.get(0).get("milk"));
		buf.append("\n");
		buf.append("Sugar: ");
		buf.append(inventoryList.get(0).get("sugar"));
		buf.append("\n");
		buf.append("Chocolate: ");
		buf.append(inventoryList.get(0).get("chocolate"));
		buf.append("\n");
		//System.out.println(buf.toString());
		//System.out.println(coffeeMaker.checkInventory());
		assertEquals(buf.toString(),coffeeMaker.checkInventory());

	}

	@Then("^check updated recipe (\\d+)$")
	public void check_updated_recipe(int arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		assertEquals("Coffee",recipeBook.getRecipes()[arg1].getName());
		assertEquals(2,recipeBook.getRecipes()[arg1].getAmtCoffee());
		assertEquals(12,recipeBook.getRecipes()[arg1].getPrice());
		assertEquals(5,recipeBook.getRecipes()[arg1].getAmtMilk());
		assertEquals(4,recipeBook.getRecipes()[arg1].getAmtSugar());
		assertEquals(3,recipeBook.getRecipes()[arg1].getAmtChocolate());
	}

	@Then("^the coffee maker should return to the ([A-Za-z]+.?[A-Za-z]+) mode$")
	public void the_states_should_be_in_the_mode_mode(String mode) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		assertEquals(mode,coffeeMakerMain.getMode().toString());

	}

	@When("^user submits a recipe$")
	public void user_submits_a_recipe(DataTable recipes) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		// For automatic transformation, change DataTable to one of
		// List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
		// E,K,V must be a scalar (String, Integer, Date, enum etc)

		List<Map<String ,String>> recipeList = recipes.asMaps(String.class, String.class);

		for (Map<String, String> form : recipeList) {
			Recipe r = new Recipe();
			r.setName(form.get("name"));
			r.setPrice(form.get("price"));
			r.setAmtCoffee(form.get("amtCoffee"));
			r.setAmtSugar(form.get("amtSugar"));
			r.setAmtMilk(form.get("amtMilk"));
			r.setAmtChocolate(form.get("amtChocolate"));

			coffeeMakerMain.UI_Input(new ChooseService(1));
			Command cmd2 = new DescribeRecipe(r);
			coffeeMakerMain.UI_Input(cmd2);
			coffeeMakerMain.displayRecipes();

		}
		//System.out.println(recipeList);
	}

	@When("^user submits a recipeList$")
	public void user_submits_a_recipe(List<Recipe> recipes) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		// For automatic transformation, change DataTable to one of
		// List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
		// E,K,V must be a scalar (String, Integer, Date, enum etc)
		for(Recipe r : recipes){
			Command cmd2 = new DescribeRecipe(r);
			coffeeMakerMain.UI_Input(cmd2);
		}
	}

	@When("^user delete Recipe -?(\\d+)$")
	public void user_delete_Recipe(int arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		Command cmd = new ChooseService(2);
		coffeeMakerMain.UI_Input(cmd);
		Command cmd2 = new ChooseRecipe(arg1);
		coffeeMakerMain.UI_Input(cmd2);
	}

	@Then("^the status should be (.*)$")
	public void the_status_should_be_OK(String status) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		assertEquals(status,coffeeMakerMain.getStatus().toString());
	}

	@When("^insert money (-?\\d+)$")
	public void insert_money(int arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		Command cmd5 = new InsertMoney(arg1);
		coffeeMakerMain.UI_Input(cmd5);
		//System.out.println(coffeeMakerMain.moneyInserted);
	}

	@When("^insert money (.*) it will catch an exception$")
	public void insert_money_it_will_catch_an_exception(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		try{
			Command cmd5 = new InsertMoney(Integer.parseInt(arg1));
			fail("Test should have been failed with an exception");
		}
		catch (Exception e){}
	}

	@When("^purchase coffee (\\d+)$")
	public void purchase_coffee(int arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		coffeeMakerMain.UI_Input(new ChooseService(6));
		//System.out.println(coffeeMakerMain.mode);
		coffeeMakerMain.UI_Input(new ChooseRecipe(arg1));
		//System.out.println(coffeeMakerMain.mode);
		//System.out.println(coffeeMakerMain.moneyInserted);
	}

	@Then("^we should take money from tray (\\d+)$")
	public void we_should_take_money_from_tray(int arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		assertEquals(arg1, coffeeMakerMain.moneyInTray);
		//System.out.println(coffeeMakerMain.moneyInTray);

	}

	@When("^take money from tray$")
	public void take_money_from_tray() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		Command cmd = new TakeMoneyFromTray();
		coffeeMakerMain.UI_Input(cmd);
	}

}
