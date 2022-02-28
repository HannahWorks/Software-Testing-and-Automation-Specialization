package edu.ncsu.csc326.coffeemaker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import edu.ncsu.csc326.coffeemaker.CoffeeMakerUI.Mode;
import edu.ncsu.csc326.coffeemaker.CoffeeMakerUI.Status;
import edu.ncsu.csc326.coffeemaker.UICmd.AddInventory;
import edu.ncsu.csc326.coffeemaker.UICmd.CheckInventory;
import edu.ncsu.csc326.coffeemaker.UICmd.ChooseRecipe;
import edu.ncsu.csc326.coffeemaker.UICmd.ChooseService;
import edu.ncsu.csc326.coffeemaker.UICmd.Command;
import edu.ncsu.csc326.coffeemaker.UICmd.DescribeRecipe;
import edu.ncsu.csc326.coffeemaker.UICmd.InsertMoney;
import edu.ncsu.csc326.coffeemaker.UICmd.Reset;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;

/**
 * 
 * @author Sarah Heckman; modified by Mike Whalen
 *
 * Starts the console UI for the CoffeeMaker
 */
public class Main {
    private CoffeeMakerUI coffeeMakerMain;

    /**
     * Prints the main menu and handles user input for 
     * main menu commands.
     */
    
    public Command waitingMode() throws IOException {
        System.out.println("1. Add a recipe");
        System.out.println("2. Delete a recipe");
        System.out.println("3. Edit a recipe");
        System.out.println("4. Add inventory");
        System.out.println("5. Check inventory");
        System.out.println("6. Purchase coffee");
        System.out.println("7. Insert money");
        System.out.println("0. Exit\n");

        try {
        	int userInput = Integer.parseInt(inputOutput("Please press the number that corresponds to what you would like the coffee maker to do."));
        	if (userInput == 0) {
        		System.exit(0);
        	} else {
        		return new ChooseService(userInput);
        	}
        } catch (NumberFormatException e) {
        	System.out.println("Choice out of range.  Please enter number between 0-6");
        }
		return null;
    }

    
    public Recipe createRecipe() throws RecipeException, IOException {
    	
	    
	    //Read in recipe price
	    String priceString = inputOutput("\nPlease enter the recipe price: $");
	    	    
	    //Read in amt coffee
	    String coffeeString = inputOutput("\nPlease enter the units of coffee in the recipe: ");
	    	    
	    //Read in amt milk
	    String milkString = inputOutput("\nPlease enter the units of milk in the recipe: ");
	    	    
	    //Read in amt sugar
	    String sugarString = inputOutput("\nPlease enter the units of sugar in the recipe: ");
	    	    
	    //Read in amt chocolate
	    String chocolateString = inputOutput("\nPlease enter the units of chocolate in the recipe: ");
	    	    
		Recipe r = new Recipe();
		r.setPrice(priceString);
		r.setAmtCoffee(coffeeString);
		r.setAmtMilk(milkString);
		r.setAmtSugar(sugarString);
		r.setAmtChocolate(chocolateString);
		return r;
    }
    
    
    /**
     * The add recipe user interface that process user input.
     */
	public Command addRecipe() throws RecipeException, IOException {
		Recipe r = createRecipe(); 
		//Read in recipe name
	    String name = inputOutput("\nPlease enter the recipe name: ");
		r.setName(name);
		
		return new DescribeRecipe(r);
    }
    
	
	/**
	 * Delete recipe user interface that processes input.
	 */
    public Command deleteRecipe() throws IOException {
    	coffeeMakerMain.displayRecipes();
    	int recipeToDelete = recipeListSelection("Please select the number of the recipe to delete.");
        
	    if(recipeToDelete < 0) {
	    	return null;
	    }
	    else return new ChooseRecipe(recipeToDelete);
    }
    
    /**
     * Edit recipe user interface the processes user input.
     */
    public Command editRecipe() throws RecipeException, IOException {
    	coffeeMakerMain.displayRecipes();
    	int recipeToEdit = recipeListSelection("Please select the number of the recipe to edit.");
    	
	    if(recipeToEdit < 0) { return null; } 
        coffeeMakerMain.UI_Input(new ChooseRecipe(recipeToEdit));
        if (coffeeMakerMain.getStatus() == Status.OK) {
        	return new DescribeRecipe(createRecipe());
        } else {
        	return null;
        }
    }
    
    /**
     * Add inventory user interface that processes input.
     */
    public Command addInventory() throws IOException {
	    //Read in amt coffee
	    String coffeeString = inputOutput("\nPlease enter the units of coffee to add: ");
	    	    
	    //Read in amt milk
	    String milkString = inputOutput("\nPlease enter the units of milk to add: ");
	    	    
	    //Read in amt sugar
	    String sugarString = inputOutput("\nPlease enter the units of sugar to add: ");
	    	    
	    //Read in amt chocolate
	    String chocolateString = inputOutput("\nPlease enter the units of chocolate to add: ");
	    
	    return new AddInventory(Integer.parseInt(coffeeString), 
	    		Integer.parseInt(milkString), 
	    		Integer.parseInt(sugarString), 
	    		Integer.parseInt(chocolateString));
    }
    
    /**
     * Check inventory user interface that processes input.
     */
    public Command checkInventory() {
    	return new CheckInventory();
    }
    
    public Command insertMoney() throws IOException {
        String amountPaid = inputOutput("Please enter the amount you wish to insert");
        int amtPaid = Integer.parseInt(amountPaid);
    	return new InsertMoney(amtPaid);
    }
    /**
     * Make coffee user interface the processes input.
     */
    public Command purchaseBeverage() throws NumberFormatException, IOException {
    	coffeeMakerMain.displayRecipes();
    	
        int recipeToPurchase = recipeListSelection("Please select the number of the recipe to purchase.");
        return new ChooseRecipe(recipeToPurchase);
    }
    
    /**
     * Passes a prompt to the user and returns the user specified 
     * string.
     * @param message
     * @return String
     */
    private static String inputOutput(String message) throws IOException {
        System.out.println(message);
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    String returnString = "";
	    try {
	        returnString = br.readLine();
	    }
	    catch (IOException e){
	        System.out.println("Error reading in value");
	        throw e;
	    }
	    return returnString;
    }
    
    /**
     * Passes a prompt to the user that deals with the recipe list
     * and returns the user selected number.
     * @param message
     * @return int
     */
    private static int recipeListSelection(String message) throws IOException {
    	String userSelection = inputOutput(message);
    	int recipe = 0;
        try {
        	recipe = Integer.parseInt(userSelection) - 1;
        	if (recipe >= 0 && recipe <=2) {
        		//do nothing here.
        	} else {
        		throw new ArrayIndexOutOfBoundsException();
        	}
        } catch (NumberFormatException e) {
        	System.out.println("Please select a number from 1-3.");
        	throw new ArrayIndexOutOfBoundsException();
        }
        return recipe;
    }
    
    Command getCommand() throws Exception {
    	switch (coffeeMakerMain.mode) {
    	case WAITING: return waitingMode();
    	case ADD_RECIPE: return addRecipe();
    	case DELETE_RECIPE: return deleteRecipe();
    	case EDIT_RECIPE: return editRecipe();
    	case ADD_INVENTORY: return addInventory();
    	case CHECK_INVENTORY: return checkInventory();
    	case PURCHASE_BEVERAGE: return purchaseBeverage();
    	default: return null;
    	}
    }
    
    /**
     * Starts the coffee maker program.
     * @param args
     */
    public Main() {
	    coffeeMakerMain = new CoffeeMakerUI(new CoffeeMaker(new RecipeBook(), new Inventory()));
    }

    public void run() {
	    CoffeeMakerUI.Status status;
	    
	    System.out.println("Welcome to the CoffeeMaker!\n");
	    
    	while (true) {
    		try {
	    		Command cmd = getCommand();
	    		if (cmd != null) {
	    			coffeeMakerMain.UI_Input(cmd);
		    		status = coffeeMakerMain.getStatus(); 
	    		} else {
		    		status = coffeeMakerMain.getStatus(); 
	    			if (status == Status.OK)
	    				status = Status.UNKNOWN_ERROR;
	    		}
	    	}
    		catch (ArrayIndexOutOfBoundsException e) {
    			System.out.println("Out of range exception occurred: " + e.toString());
    			status = Status.OUT_OF_RANGE; 
    		}
    		catch (RecipeException e) {
    			System.out.println("Recipe exception occurred: " + e.toString());
    			status = Status.RECIPE_NOT_ADDED; 
    		}
    		catch (Exception e) {
	    		System.out.println("Exception occurred: " + e.toString());
	    		status = Status.UNKNOWN_ERROR; 
	    	}
			System.out.println("Mode: " + coffeeMakerMain.getMode());
			System.out.println("Status: " + status);
			System.out.println("Money in machine: " + coffeeMakerMain.moneyInserted);
			System.out.println("Money in coin return: " + coffeeMakerMain.moneyInTray);
			// To get back to a stable state.  This should probably be a 
			// user action.
			if (status != Status.OK) {
				coffeeMakerMain.UI_Input(new Reset());
			}
    	}
    }
    public static void main(String[] args) {
    	new Main().run(); 
    }
}
