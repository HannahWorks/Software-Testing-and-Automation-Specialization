package edu.ncsu.csc326.coffeemaker.UICmd;

public class CheckInventory implements Command {

	String inventory;
	
	public String getInventory() {
		return inventory;
	}

	public void setInventory(String inventory) {
		this.inventory = inventory;
		//System.out.println(inventory);
	}

	public CheckInventory() {}
	
}
