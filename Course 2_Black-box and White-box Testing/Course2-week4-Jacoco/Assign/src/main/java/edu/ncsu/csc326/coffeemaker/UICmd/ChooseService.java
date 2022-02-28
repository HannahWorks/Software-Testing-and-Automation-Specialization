package edu.ncsu.csc326.coffeemaker.UICmd;

import edu.ncsu.csc326.coffeemaker.CoffeeMakerUI;
import edu.ncsu.csc326.coffeemaker.CoffeeMakerUI.Mode;

public class ChooseService implements Command {
	public final CoffeeMakerUI.Mode mode;

	public ChooseService(int button) {
		switch (button) { 
		case 1: mode = Mode.ADD_RECIPE; break;
		case 2: mode = Mode.DELETE_RECIPE; break;
		case 3: mode = Mode.EDIT_RECIPE; break;
		case 4: mode = Mode.ADD_INVENTORY; break;
		case 5: mode = Mode.CHECK_INVENTORY; break;
		case 6: mode = Mode.PURCHASE_BEVERAGE; break;
		default: mode = Mode.WAITING; break;
		}
	}
}
