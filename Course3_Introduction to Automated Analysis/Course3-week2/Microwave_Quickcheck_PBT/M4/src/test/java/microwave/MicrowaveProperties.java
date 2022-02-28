package microwave;

import static org.junit.Assert.assertTrue;

public class MicrowaveProperties {

	public static boolean implies(boolean A, boolean B) {
		return (!A || B);
	}

	// This is needed for one of the properties.
	TrueWithin condition;  
	
	MicrowaveProperties(Microwave microwave) {
		condition = new TrueWithin(microwave.getTickRateInHz() + 1);
	}
	
	/**
	 * checkProperties: this method checks whether the microwave is meeting its 
	 * behavioral requirements.  It requires the current and previous values of 
	 * the microwave inputs (InputRecord) and state (OutputRecord).
	 * 
	 * Your job for this assignment is to take the requirements and create Java
	 * code to check whether they are true.  
	 * 
	 * It is sometimes tricky to determine whether a requirement is talking 
	 * about the 'current state' or the 'previous state'.  For example, often
	 * a requirement says something like: when the system is in mode X, and Y happens,
	 * then the system shall enter mode Z.  In this case, mode X is the previous 
	 * system mode and mode Z is the current system mode.  Usually Y is a current 
	 * state input, but read carefully!
	 * 
	 * @param currentInput
	 */
	public void checkProperties(InputRecord previousInput, OutputRecord previousOutput,
			   InputRecord currentInput, OutputRecord currentOutput) {
				
		// Suppose I have the following properties:
		try {
		assertTrue("The microwave shall be in cooking mode only when the door is closed", 
				implies(currentOutput.mode == ModeController.Mode.Cooking, currentInput.doorOpen == false));
		
		assertTrue("If current time to cook is zero, then mode is setup mode.",
				implies(currentOutput.timeToCook == 0, 
						currentOutput.mode == ModeController.Mode.Setup));
		
		assertTrue("When the microwave is cooking, within tickRateInHz ticks, the time to cook will decrease",
				condition.check(implies(currentOutput.mode == ModeController.Mode.Cooking && 
						previousOutput.mode == ModeController.Mode.Cooking,  
						currentOutput.timeToCook < previousOutput.timeToCook)));

		assertTrue("When the microwave is cooking, the time is either the same or decreases by one",
				implies(currentOutput.mode == ModeController.Mode.Cooking && 
				previousOutput.mode == ModeController.Mode.Cooking,  
				currentOutput.timeToCook == previousOutput.timeToCook || 
				currentOutput.timeToCook == previousOutput.timeToCook - 1));
		
		assertTrue("seconds to cook is always >= 0", 
					currentOutput.timeToCook >= 0);
		

		assertTrue("At the instant the clear button is pressed, if the " + 
				   "microwave was cooking, then the microwave shall stop cooking", 
				   implies(currentInput.clearPressed && previousOutput.mode == ModeController.Mode.Cooking, 
						   currentOutput.mode != ModeController.Mode.Cooking));

		assertTrue("At the instant when the clear button is pressed, if the " + 
				  "microwave is in suspended mode, it shall enter the setup mode", 
				  implies(currentInput.clearPressed && previousOutput.mode == ModeController.Mode.Suspended, 
						  currentOutput.mode == ModeController.Mode.Setup));
		
		assertTrue("If in suspended mode, at the instant the start key is pressed " + 
				   "the microwave shall enter cooking mode if the door is closed and " + 
				   "the seconds_to_cook is greater than 0", 
				   implies(previousOutput.mode == ModeController.Mode.Suspended && currentInput.startPressed 
						   && !currentInput.doorOpen && currentOutput.timeToCook > 0, 
						   currentOutput.mode == ModeController.Mode.Cooking));

		assertTrue("If in suspended mode, at the instant the start key is pressed " + 
				   "the microwave shall remain in suspended mode if the door is open and " + 
				   "the seconds_to_cook is greater than 0", 
				   implies(previousOutput.mode == ModeController.Mode.Suspended && currentInput.startPressed 
				   && currentInput.doorOpen && currentOutput.timeToCook > 0, 
				   currentOutput.mode == ModeController.Mode.Suspended));

		assertTrue("If the mode is setup or suspended and the clear button is pressed, the steps to cook shall be zero", 
					implies((previousOutput.mode == ModeController.Mode.Setup || previousOutput.mode == ModeController.Mode.Suspended) && currentInput.clearPressed, 
							currentOutput.timeToCook == 0));	


		assertTrue("If the display is quiescent (no buttons pressed) and mode was setup, " + 
					"the mode is still setup and the seconds_to_cook shall not change.", 
					implies(!currentInput.clearPressed && !currentInput.startPressed && !currentInput.digitPressed &&
							previousOutput.mode == ModeController.Mode.Setup, 
							currentOutput.mode == ModeController.Mode.Setup && 
							currentOutput.timeToCook == previousOutput.timeToCook));
		
		} catch (Throwable e) {
			System.out.println("Exception occurred during property check.  ");
			System.out.println("Exception: " + e.toString());
			System.out.println("Previous Input: " + previousInput.toString());
			System.out.println("Previous Output: " + previousOutput.toString());
			System.out.println("Current Input: " + currentInput.toString());
			System.out.println("Current Output: " + currentOutput.toString());
			throw e;
		}
		
	} 
}
