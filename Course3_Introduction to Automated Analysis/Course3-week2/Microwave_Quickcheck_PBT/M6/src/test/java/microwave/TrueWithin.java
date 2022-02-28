package microwave;

/** 
 * TrueWithin
 * 
 * This class allows specification of a 'bounded failure' property.  If 
 * the property is violated consecutively for more than maxTicks
 * check() will return false; otherwise it will return true
 * 
 * @author whalen
 *
 */
public class TrueWithin {
	int maxTicks; 
	int currentTicks;
	
	public TrueWithin(int maxTicks) {
		this.maxTicks = maxTicks;
		currentTicks = 0;
	}
	
	public boolean check(boolean A) {
		if (!A) {
			currentTicks++;
		} else {
			currentTicks = 0;
		}
		return currentTicks <= maxTicks; 
	}
}
