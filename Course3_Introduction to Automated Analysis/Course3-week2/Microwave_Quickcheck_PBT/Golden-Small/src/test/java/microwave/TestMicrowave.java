/**
 * 
 */
package microwave;

import static org.junit.Assert.*;
import static org.junit.Assume.*;

import java.util.ArrayList;

import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.runner.RunWith;

import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.Size;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;

/**
 * @author whalen
 *
 */
@RunWith(JUnitQuickcheck.class)
public class TestMicrowave {

	public Microwave microwave;
	
	@Before
	public void setUp() {
		microwave = new Microwave(new ModeController(), new DisplayController(1), new ArrayList<Preset>());
	}

	public String digitsString(byte [] digits) {
    	return "" + 
    			digits[DisplayController.TENS_OF_MINUTES] +  
    			digits[DisplayController.MINUTES] + 
    			digits[DisplayController.TENS_OF_SECONDS] +  
    			digits[DisplayController.SECONDS];
	}

	InputRecord previousInput = null;
	OutputRecord previousOutput = null;
	OutputRecord currentOutput = null;

	
	int computeTimeToCook(byte [] display) {
		return display[DisplayController.SECONDS] + 
				display[DisplayController.TENS_OF_SECONDS] * 10 + 
				display[DisplayController.MINUTES] * 60 + 
				display[DisplayController.TENS_OF_MINUTES] * 600; 
	}
	
	public boolean implies(boolean A, boolean B) {
		return (!A || B);
	}
	
	public class ImpliesWithin {
		int maxTicks; 
		int currentTicks;
		
		public ImpliesWithin(int maxTicks) {
			this.maxTicks = maxTicks;
			currentTicks = 0;
		}
		
		public boolean implies(boolean A, boolean B) {
			if (A && !B) {
				currentTicks++;
			} else {
				currentTicks = 0;
			}
			return currentTicks <= maxTicks; 
		}
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
	private void checkProperties(InputRecord currentInput) {
				
		// Suppose I have the following properties:
		try {
		assertTrue("The microwave shall be in cooking mode only when the door is closed", 
				implies(currentOutput.mode == ModeController.Mode.Cooking, currentInput.doorOpen == false));
		
		assertTrue("If current time to cook is zero, then mode is setup mode.",
				implies(currentOutput.timeToCook == 0, 
						currentOutput.mode == ModeController.Mode.Setup));
		
		ImpliesWithin condition = new ImpliesWithin(microwave.getTickRateInHz() + 1);
		
		assertTrue("When the microwave is cooking, within tickRateInHz ticks, the time to cook will decrease",
				condition.implies(currentOutput.mode == ModeController.Mode.Cooking && 
						previousOutput.mode == ModeController.Mode.Cooking,  
						currentOutput.timeToCook < previousOutput.timeToCook));

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


		assertTrue("If the display is quiescent (no buttons pressed) and mode is setup, " + 
					"the seconds_to_cook shall not change.", 
					implies(!currentInput.clearPressed && !currentInput.startPressed && !currentInput.digitPressed &&
							currentOutput.mode == ModeController.Mode.Setup, 
							currentOutput.timeToCook == previousOutput.timeToCook));
		
		} catch (Throwable e) {
			System.out.println("Exception occurred during property check.  ");
			System.out.println("Previous Input: " + previousInput.toString());
			System.out.println("Previous Output: " + previousOutput.toString());
			System.out.println("Current Input: " + currentInput.toString());
			System.out.println("Current Output: " + currentOutput.toString());
			throw e;
		}
		
	} 
	
	/** This method executes the "behavior" of the microwave, given an InputRecord
	 * to stimulate the behavior.
	 * @param record: the InputRecord that causes the microwave to do something.
	 * 
	 */
	// Part of test harness: stimulate microwave behavior
	private void behavior(InputRecord record) {
		// perform functional behavior
		if (record.digitPressed) {
			microwave.digitPressed(record.digit % 10);
		}
		if (record.clearPressed) {
			microwave.clearPressed();
		}
		if (record.startPressed) {
			microwave.startPressed();
		}
		microwave.setDoorOpen(record.doorOpen);
		microwave.tick();
	}

	static int trial = 1; 
	
	/** This method runs a trial, which is an execution sequence of the microwave.  It 
	 * constructs an ArrayList of InputRecords using the InputRecordGenerator.  This 
	 * list will be used to stimulate the behavior of the Microwave - each element in 
	 * the array becomes an "input" to the microwave for a time step.
	 * 
	 * For each input, we run the behavior() method, which updates the microwave using 
	 * the input data.  Then we check the behavior against our requirements using the 
	 * checkProperties() method.
	 * 
	 */
	
	@Property(trials = 20)
	public void runTrial(@Size(min = 10, max = 50) ArrayList<@From(InputRecordGenerator.class) InputRecord> records) {
		System.out.println("Trial " + (TestMicrowave.trial++));
		
		
		assumeThat(records, not(equalTo(null)));
				
		for (InputRecord record: records) {
			// "run" system
			behavior(record);
			
			currentOutput = new OutputRecord(microwave.getMode(), microwave.digits(), computeTimeToCook(microwave.digits()));
			
			// check properties if we have history.
			if (previousInput != null) {
				checkProperties(record); 
			}
			
			// record state information for future use.
			previousInput = record;
			previousOutput = currentOutput; 
		}
	}
}
