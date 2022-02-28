/**
 * 
 */
package microwave;

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
public class TestMicrowaveHarness {

	public Microwave microwave;
	
	@Before
	public void setUp() {
		ArrayList<Preset> presets = new ArrayList<Preset>();
		presets.add(new Preset("Popcorn", 60, 10));
		presets.add(new Preset("Pizza 1 slice", 55, 6));
		presets.add(new Preset("Pizza 2 slice", 100, 6));
		presets.add(new Preset("Chicken breast", 40, 7));
		presets.add(new Preset("Defrost 1 lb hamburger", 180, 5));
		
		microwave = new Microwave(new ModeController(), new DisplayController(1), presets);
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
		int ttc= display[DisplayController.SECONDS] + 
				display[DisplayController.TENS_OF_SECONDS] * 10 + 
				display[DisplayController.MINUTES] * 60 + 
				display[DisplayController.TENS_OF_MINUTES] * 600; 
		return ttc;
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
		try {
			if (record.presetPressed) {
				microwave.presetPressed(record.preset);
			}
		}
		catch (IllegalArgumentException e) {
			System.out.println("Error: " + e.toString());
			System.out.println("Preset value: " + record.preset);
			System.out.println("Presets: " + microwave.presets);
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
	
	@Property
	public void runTrial(@Size(min = 10, max = 50) ArrayList<@From(InputRecordGenerator.class) InputRecord> records) {
		//System.out.println("Trial " + (TestMicrowaveHarness.trial++));
		
		// create properties
		MicrowaveProperties props = new MicrowaveProperties(microwave);
		
		assumeThat(records, not(equalTo(null)));
				
		for (InputRecord currentInput: records) {
			// "run" system
			behavior(currentInput);
			
			currentOutput = new OutputRecord(microwave.getMode(), microwave.digits(), computeTimeToCook(microwave.digits()));
			
			// check properties if we have history.
			if (previousInput != null) {
				props.checkProperties(previousInput, previousOutput, currentInput, currentOutput); 
			}
			
			// record state information for future use.
			previousInput = currentInput;
			previousOutput = currentOutput; 
		}
	}
}
