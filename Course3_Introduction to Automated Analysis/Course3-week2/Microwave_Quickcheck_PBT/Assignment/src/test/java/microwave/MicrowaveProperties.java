package microwave;

import static org.junit.Assert.assertTrue;

public class MicrowaveProperties {

	public static boolean implies(boolean A, boolean B) {
		return (!A || B);
	}

	// This is needed for one of the properties.
	TrueWithin timedCondition;  
	
	MicrowaveProperties(Microwave microwave) {
		timedCondition = new TrueWithin(microwave.getTickRateInHz() + 1);
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
	 * @param previousInput
	 * @param previousOutput
	 * @param currentInput
	 * @param currentOutput
	 * 
	 */
	public void checkProperties(InputRecord previousInput, OutputRecord previousOutput,
			   InputRecord currentInput, OutputRecord currentOutput) {
				
		try {
		
		// A freebie: I provide the definition	
		assertTrue("The microwave shall be in cooking mode only when the door is closed", 
				implies(currentOutput.mode == ModeController.Mode.Cooking, currentInput.doorOpen == false));
		
		// Another freebie: I provide the definition	
		assertTrue("At the instant the clear button is pressed, if the " + 
				   "microwave was cooking, then the microwave shall stop cooking", 
				   implies(currentInput.clearPressed && previousOutput.mode == ModeController.Mode.Cooking, 
						   currentOutput.mode != ModeController.Mode.Cooking));

		// replace 'true' with the property definition.
		assertTrue("If current time to cook is zero, then mode is setup mode.",
				true);
		
		assertTrue("When the microwave is cooking, the time is either the same or " + 
				   "decreases by one",
				true);
		
		assertTrue("seconds to cook is always >= 0", 
					true);

		assertTrue("At the instant when the clear button is pressed, if the " + 
				  "microwave is in suspended mode, it shall enter the setup mode", 
				  true);
		
		assertTrue("If in suspended mode, at the instant the start key is pressed " + 
				   "the microwave shall enter cooking mode if the door is closed and " + 
				   "the seconds_to_cook is greater than 0", 
				   true);

		assertTrue("If in suspended mode, at the instant the start key is pressed " + 
				   "the microwave shall remain in suspended mode if the door is open and " + 
				   "the seconds_to_cook is greater than 0", 
				   true);

		assertTrue("If the mode is setup or suspended and the clear button is pressed, " +
				   "the steps to cook shall be zero", 
					true);	


		assertTrue("If the display is quiescent (no buttons pressed) and mode was setup, " + 
					"the mode is still setup and the seconds_to_cook shall not change.", 
					true);
		
		// Note: this example is tricky: we want to check that within a certain number
		// of "checks" that the condition is true.  We use the TrueWithin class to check this.
		// Still, replace 'true' with the property definition.
		assertTrue("When the microwave is cooking, within tickRateInHz ticks, the time to cook will decrease",
				timedCondition.check(true));


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
