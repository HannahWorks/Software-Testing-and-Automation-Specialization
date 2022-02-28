package edu.ncsu.csc326.coffeemaker.UICmd;

public class ChooseRecipe implements Command {
	public final int number; 
	
	public ChooseRecipe(int number) {
		this.number = number;
	}
}
