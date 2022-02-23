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
 */
package edu.ncsu.csc326.coffeemaker;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;

/**
 * @author Sarah Heckman
 */
public class CoffeeMaker {
	/** Array of recipes in coffee maker */
	private RecipeBook recipeBook;
	/** Inventory of the coffee maker */
    private Inventory inventory;
	
    /**
     * Constructor for the coffee maker
     *
     */
	public CoffeeMaker() {
	    recipeBook = new RecipeBook();
		inventory = new Inventory();
	}
	
	/**
	 * Returns true if the recipe is added to the
	 * list of recipes in the CoffeeMaker and false
	 * otherwise.
	 * @param r
	 * @return boolean
	 */
	public boolean addRecipe(Recipe r) {
		return recipeBook.addRecipe(r);
	}
	
	/**
	 * Returns the name of the successfully deleted recipe
	 * or null if the recipe cannot be deleted.
	 * 
	 * @param recipeToDelete
	 * @return String
	 */
	public String deleteRecipe(int recipeToDelete) {
		return recipeBook.deleteRecipe(recipeToDelete);
	}
	
	/**
	 * Returns the name of the successfully edited recipe
	 * or null if the recipe cannot be edited.
	 * @param recipeToEdit
	 * @param r
	 * @return String
	 */
	public String editRecipe(int recipeToEdit, Recipe r) {
		return recipeBook.editRecipe(recipeToEdit, r);
	}
    
    /**
     * Returns true if inventory was successfully added
     * @param amtCoffee
     * @param amtMilk
     * @param amtSugar
     * @param amtChocolate
     * @return boolean
     */
    public synchronized void addInventory(String amtCoffee, String amtMilk, String amtSugar, String amtChocolate) throws InventoryException {
	    inventory.addCoffee(amtCoffee);
	    inventory.addMilk(amtMilk);
	    inventory.addSugar(amtSugar);
	    inventory.addChocolate(amtChocolate);
    }
    
    /**
     * Returns the inventory of the coffee maker
     * @return Inventory
     */
    public synchronized String checkInventory() {
        return inventory.toString();
    }
    
    /**
     * Returns the change of a user's beverage purchase, or
     * the user's money if the beverage cannot be made
     * @param r
     * @param amtPaid
     * @return int
     */
    public synchronized int makeCoffee(int recipeToPurchase, int amtPaid) {
        int change = 0;
        
        if (recipeToPurchase < 0 || 
            recipeToPurchase >= getRecipes().length || 
            getRecipes()[recipeToPurchase] == null) {
            	change = amtPaid;
        } 
        else if (getRecipes()[recipeToPurchase].getPrice() <= amtPaid) {
        	if (inventory.useIngredients(getRecipes()[recipeToPurchase])) {
        		change = amtPaid - getRecipes()[recipeToPurchase].getPrice();
        	} else {
        		change = amtPaid;
        	}
        } else {
        	change = amtPaid;
        }
        
        return change;
    }

	/**
	 * Returns the list of Recipes in the RecipeBook.
	 * @return Recipe []
	 */
	public synchronized Recipe[] getRecipes() {
		return recipeBook.getRecipes();
	}
}
