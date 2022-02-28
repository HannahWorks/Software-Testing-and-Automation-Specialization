package edu.ncsu.csc326.coffeemaker;

import edu.ncsu.csc326.coffeemaker.UICmd.AddInventory;
import edu.ncsu.csc326.coffeemaker.UICmd.CheckInventory;
import edu.ncsu.csc326.coffeemaker.UICmd.ChooseRecipe;
import edu.ncsu.csc326.coffeemaker.UICmd.ChooseService;
import edu.ncsu.csc326.coffeemaker.UICmd.Command;
import edu.ncsu.csc326.coffeemaker.UICmd.DescribeRecipe;
import edu.ncsu.csc326.coffeemaker.UICmd.InsertMoney;
import edu.ncsu.csc326.coffeemaker.UICmd.Reset;
import edu.ncsu.csc326.coffeemaker.UICmd.TakeMoneyFromTray;
import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;

public class CoffeeMakerUI {

	public enum Mode {WAITING, ADD_RECIPE, DELETE_RECIPE, EDIT_RECIPE, ADD_INVENTORY, CHECK_INVENTORY, PURCHASE_BEVERAGE};
	public enum Status { OK, WRONG_MODE, RECIPE_NOT_ADDED, OUT_OF_RANGE, INSUFFICIENT_FUNDS, UNKNOWN_ERROR };
	
	public final int NO_CHOSEN_RECIPE = -1;
	
	Mode mode = Mode.WAITING;
	Status status = Status.OK;
	int moneyInserted = 0;
	int moneyInTray = 0;
	int chosenRecipe = -1;
	CoffeeMaker coffeeMaker;

	public CoffeeMakerUI(CoffeeMaker coffeeMaker) {
		this.coffeeMaker = coffeeMaker;
	}
	
		
	public Mode getMode() {
		return mode;
	}
	
	public void defaultCommands(Command cmd) {
		if (cmd instanceof InsertMoney) {
			int amount = ((InsertMoney)cmd).amount;
			if (amount > 0) {
				moneyInserted += amount;
				System.out.println("Money in machine: " + moneyInserted);
				status = Status.OK;
			} else {
				status = Status.OUT_OF_RANGE;
			}
		} else if (cmd instanceof TakeMoneyFromTray) {
			moneyInTray = 0;
			status = Status.OK;
		} else if (cmd instanceof Reset) {
			mode = Mode.WAITING;
		}
		else {
			status = Status.WRONG_MODE; 
		}		
	}
	
	public void displayRecipes() {
		System.out.println("Recipes: ");
        Recipe [] recipes = coffeeMaker.getRecipes();
        for(int i = 0; i < recipes.length; i++) {
        	if (recipes[i] != null) {
        		System.out.println((i+1) + ". " + recipes[i].getName());
        	}
        }
	}
	
	public Recipe [] getRecipes() {
		return coffeeMaker.getRecipes();
	}
	
	public void UI_Input(Command cmd) {
		switch(mode) {
		case WAITING: 
			chosenRecipe = NO_CHOSEN_RECIPE;
			if (cmd instanceof ChooseService) {
				mode = ((ChooseService)cmd).mode;
				status = Status.OK; 
			} else {
				defaultCommands(cmd);
			}
			break;
		case ADD_RECIPE: 
			if (cmd instanceof DescribeRecipe) {
				boolean success = coffeeMaker.addRecipe(((DescribeRecipe)cmd).recipe);
				status = (success) ? Status.OK : Status.RECIPE_NOT_ADDED; 
				mode = Mode.WAITING;
			} else {
				defaultCommands(cmd); 
			}
			break;
		case DELETE_RECIPE: 
			if (cmd instanceof ChooseRecipe) {
				String result = coffeeMaker.deleteRecipe(((ChooseRecipe)cmd).number);
				if (result == null) {
					status = Status.OUT_OF_RANGE;
				} else {
					status = Status.OK;
				}
				mode = Mode.WAITING;
			}
			break;
		case EDIT_RECIPE: 
			if (cmd instanceof ChooseRecipe) {
				this.chosenRecipe = ((ChooseRecipe)cmd).number;
				if (this.chosenRecipe < 0 || 
					this.chosenRecipe >= coffeeMaker.getRecipes().length || 
					coffeeMaker.getRecipes()[this.chosenRecipe] == null) {
					status = Status.OUT_OF_RANGE;
					mode = Mode.WAITING;
				} else {
					status = Status.OK;
				}
			} else if (cmd instanceof DescribeRecipe) {
				String result = coffeeMaker.editRecipe(this.chosenRecipe, ((DescribeRecipe)cmd).recipe);
				if (result == null) {
					status = Status.RECIPE_NOT_ADDED; 
					mode = Mode.WAITING;
				} else {
					status = Status.OK;
					mode = Mode.WAITING;
				}
			} else {
				defaultCommands(cmd);
			}
			break ;
		case ADD_INVENTORY: 
			if (cmd instanceof AddInventory) {
				AddInventory aiCmd = (AddInventory)cmd;
				try { 
					coffeeMaker.addInventory("" + aiCmd.amtCoffee, 
						"" + aiCmd.amtMilk, 
						"" + aiCmd.amtSugar, 
						"" + aiCmd.amtChocolate);
					status = Status.OK;
				} catch (InventoryException ie) {
					System.out.println(ie); 
					status = Status.OUT_OF_RANGE; 
				}
				mode = Mode.WAITING;
			} else {
				defaultCommands(cmd);
			}
			break;
		case CHECK_INVENTORY : 
			if (cmd instanceof CheckInventory) {
				CheckInventory ciCmd = (CheckInventory)cmd;
				String result = coffeeMaker.checkInventory(); 
				System.out.println(result);
				ciCmd.setInventory(result);
				status = Status.OK;
				mode = Mode.WAITING;
			} else {
				defaultCommands(cmd);
			}
			break;
		case PURCHASE_BEVERAGE:
			if (cmd instanceof ChooseRecipe) {
				ChooseRecipe crCmd = (ChooseRecipe)cmd;
				try { 
					int remainder = coffeeMaker.makeCoffee(crCmd.number, this.moneyInserted);
					System.out.println("moneyInserted: " + moneyInserted + " remainder: " + remainder);
					if (moneyInserted == remainder) {
						this.status = Status.INSUFFICIENT_FUNDS; 
					} else {
						System.out.println("Beverage purchased.");
						this.status = Status.OK;
						this.moneyInTray += remainder;
						this.moneyInserted = 0; 
					}
				} catch (RecipeException re) {
					this.status = Status.OUT_OF_RANGE; 
				}
				this.mode = Mode.WAITING;
			} else {
				defaultCommands(cmd);
			}
			break;
		}
		// System.out.println("Executed: " + cmd + "; Mode = " + mode + "; Status = " + status);
	}
	
	public Status getStatus() {
		return status;
	}
	
	public int getMoneyInserted() {
		return moneyInserted;
	}
	
	public int getMoneyInTray() {
		return moneyInTray;
	}
	
	
	
	
}
