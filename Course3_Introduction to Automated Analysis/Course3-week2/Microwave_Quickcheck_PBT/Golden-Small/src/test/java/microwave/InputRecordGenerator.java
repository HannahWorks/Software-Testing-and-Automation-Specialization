package microwave;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

import microwave.InputRecord;

public class InputRecordGenerator extends Generator<InputRecord> {
	public InputRecordGenerator() {
		super(InputRecord.class);
	}
	
	// Set boolean value to be "true" only 1 / ratio times.
	// Use modulo operator to determine probability of 'true' value.
	private boolean onePer(SourceOfRandomness r, int ratio) {
		return (r.nextInt() % ratio == 0);
	}

	public InputRecord generate(
	            SourceOfRandomness r,
	            GenerationStatus status) {

		 	InputRecord elem = new InputRecord();
		 	// we want to support different probabilities for input values, 
		 	// but these are not natively supported.  However, there are 
		 	// various ways of constructing them.  In this lesson, we will not
		 	// describe distributions, but different probabilities for discrete
		 	// values.
		 		 	
		 	// clearPressed will happen (on average) 1/20 
		 	elem.clearPressed = onePer(r, 20);
		 	
		 	// nextInt(k) returns number from [0 .. k-1]
		 	elem.digit = (r.nextInt(10));
		 	
		 	// and so on...
		 	elem.digitPressed = onePer(r, 10);
		 	elem.doorOpen = r.nextBoolean();
		 	elem.presetPressed = onePer(r, 30);
		 	elem.preset = r.nextInt(5);
		 	elem.startPressed = onePer(r, 10);
		 	
		 	return elem;
	 }	
	
};
