package microwave;

/** 
 * The InputRecord class provides a means for creating a record of all possible
 * inputs to the microwave, so that we can drive its behavior.
 * 
 * @Author Mike Whalen
 * @Version 1.0
 * 
 */


public class InputRecord {
	public boolean digitPressed;
	public int digit;
	public boolean startPressed;
	public boolean clearPressed;
	public boolean doorOpen;
	public boolean presetPressed;
	public int preset;
	
	@Override
	public String toString() {
		return "InputRecord [digitPressed=" + digitPressed + ", digit=" + digit + ", startPressed=" + startPressed
				+ ", clearPressed=" + clearPressed + ", doorOpen=" + doorOpen + ", presetPressed=" + presetPressed
				+ ", preset=" + preset + "]";
	}
	
};
