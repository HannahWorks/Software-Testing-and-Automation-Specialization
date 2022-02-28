package edu.ncsu.csc326.coffeemaker.UICmd;

public class InsertMoney implements Command {
	public final int amount;

	public InsertMoney(int amount) {
		this.amount = amount;
	}
}
