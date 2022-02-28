package microwave;

import java.util.Arrays;

/** 
 * The OutputRecord class provides a means for storing the state of part of the 
 * microwave, so that we can compare the current state and the previous state.
 * 
 * @Author Mike Whalen
 * @Version 1.0
 * 
 */

public class OutputRecord {
	public final ModeController.Mode mode;
	public final byte [] displayDigits;
	public final int timeToCook;
	
	@Override
	public String toString() {
		return "OutputRecord [mode=" + mode + ", displayDigits=" + Arrays.toString(displayDigits) + ", timeToCook=" + timeToCook + "]";
	}

	public OutputRecord(ModeController.Mode mode, byte[] bs, int timeToCook) {
		this.mode = mode;
		this.displayDigits = bs;
		this.timeToCook = timeToCook;
	}
}; 