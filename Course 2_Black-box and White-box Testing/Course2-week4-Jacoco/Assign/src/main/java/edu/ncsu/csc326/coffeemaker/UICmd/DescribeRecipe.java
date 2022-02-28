package edu.ncsu.csc326.coffeemaker.UICmd;

import edu.ncsu.csc326.coffeemaker.Recipe;

public class DescribeRecipe implements Command {
	public final Recipe recipe; 
	
	public DescribeRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
	
}
