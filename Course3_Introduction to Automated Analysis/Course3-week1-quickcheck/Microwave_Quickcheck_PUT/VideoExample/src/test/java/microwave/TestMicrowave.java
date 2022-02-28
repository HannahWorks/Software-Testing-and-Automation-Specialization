/**
 * 
 */
package microwave;

import static org.junit.Assert.*;
import static org.junit.Assume.*;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.runner.RunWith;

import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.generator.Size;
import com.pholser.junit.quickcheck.generator.ValuesOf;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import com.pholser.junit.quickcheck.generator.*;

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

	/** seconds_elapse
	 * 
	 * This method allows 'time' seconds to elapse with the microwave.
	 * 
	 * @param time
	 */
    public void secondsElapse(int time)  {
        // System.out.println("" + time + " seconds elapse.");
        for (int i = 0; i < time*microwave.getTickRateInHz(); i++) {
        	microwave.tick(); 
        }
    }

    public String printDigits(byte[] digits) {
    	return "" + digits[0] + digits[1] + digits[2] + digits[3]; 
    }
    
    public int calcTime(byte [] digits) {
    	return digits[DisplayController.TENS_OF_MINUTES] * 600 + 
    		   digits[DisplayController.MINUTES] * 60 + 
    		   digits[DisplayController.TENS_OF_SECONDS] * 10 + 
    		   digits[DisplayController.SECONDS];
    }

    
    
    /** 
     * Insert your junit-quickcheck test here!
     * 
     * Test steps: 
     * 	set door closed and time to cook to 20 seconds.
     *  start microwave cooking
     *  run microwave for <timeToCook> seconds
     *  if timeToCook < 20, then 
     *    - time remaining should equal 20 - timeToCook
     *    - mode should remain cooking
     *  otherwise, 
     *    - time remaining should be zero.
     *    - mode should be setup
     *  
     */
    
    /*
    @Property(trials=10)
    public void checkCookingTime(@InRange(min="0", max="100") int timeToCook,
    							@ValuesOf boolean foo) {
    	    	
    	System.out.println(".");
    	
    	microwave.setDoorOpen(false);
    	microwave.tick();
    	microwave.digitPressed(2);
    	microwave.tick();
    	microwave.digitPressed(0);
    	microwave.tick();
    	microwave.startPressed();
    	microwave.tick();
    	
    	secondsElapse(timeToCook);
    	
    	byte [] digits = microwave.digits();
    	ModeController.Mode mode = microwave.getMode(); 
    	
    	System.out.println("Time to cook: " + timeToCook + "; ");
    	System.out.println("Time remaining (digits) " + this.printDigits(digits));
    	
    	boolean timeIsCorrectlyDisplayed;
    	boolean modeIsCorrect;
    	
    	if (timeToCook < 20) {
    		timeIsCorrectlyDisplayed = (String.format("%04d",  20-timeToCook)).contentEquals(this.printDigits(digits));
    		modeIsCorrect = mode == ModeController.Mode.Cooking;
    	} else {
    		timeIsCorrectlyDisplayed = "0000".contentEquals(this.digitsString(digits));
    		modeIsCorrect = mode == ModeController.Mode.Setup;
    	}
    	assertTrue(timeIsCorrectlyDisplayed);
    	assertTrue(modeIsCorrect);
    }
    */
    
    
    /**
     * 
     * Bias examples: how do I bias inputs towards specific values?
     * 
     */
    
    /*
    @Property 
    public void testSpaceStationDocking(double speed, int mode, 
    		boolean failure1, 
    		boolean failure2, 
    		boolean failure3, 
    		boolean failure4,
    		boolean failure5,
    		boolean failure6,
    		boolean failure7,
    		boolean failure8,
    		boolean failure9,
    		boolean failure10,
    		boolean failure11,
    		boolean failure12,
    		boolean failure13,
    		boolean failure14,
    		boolean failure15) {
    	
    	// If any failures occur, docking aborts!
    	if (failure1 || failure2 || failure3 || failure4 || failure5 || 
    		failure6 || failure7 || failure8 || failure9 || failure10 || 
    		failure11 || failure12 || failure13 || failure14 || failure15 ) {
    		System.out.println("Docking aborted");
    	} else {
    		System.out.println("No failure condition: proceed with docking");
    	}
    }
	*/
    public enum Color {Red, Green, Blue, White, Black, Gray};

    /*
    @Property 
    public void biasInputs(int biasedIntInput, @InRange(min="0.0", max="100000.0") double biasedDoubleInput) {
    	boolean biasedTowardsFalse = (biasedIntInput % 10 == 0);
    	System.out.println("biasedTowardsFalse = " + biasedTowardsFalse);
    	
    	double biasedTowardsTen = (Math.log(biasedDoubleInput));
    	System.out.println("biasedTowardsTen = " + biasedTowardsTen);
    	
    	int remainder = biasedIntInput % 10;
    	Color biasedTowardsRed;
    	if (remainder < 5) biasedTowardsRed = Color.Red;
    	else if (remainder == 5) biasedTowardsRed = Color.Green;
    	else if (remainder == 6) biasedTowardsRed = Color.Blue;
    	else if (remainder == 7) biasedTowardsRed = Color.White;
    	else if (remainder == 8) biasedTowardsRed = Color.Black;
    	else biasedTowardsRed = Color.Gray;
    	System.out.println("biasedTowardRed = " + biasedTowardsRed);
    	
    }
   */
}
