package edu.ncsu.csc326.coffeemaker.UICmd;

public class AddInventory implements Command {
	
	public final int amtCoffee;
	public final int amtMilk;
	public final int amtSugar;
	public final int amtChocolate;
	
	public AddInventory(int amtCoffee, int amtMilk, int amtSugar, int amtChocolate) {
		this.amtCoffee = amtCoffee;
		this.amtMilk = amtMilk;
		this.amtSugar = amtSugar;
		this.amtChocolate = amtChocolate;
	}

}
