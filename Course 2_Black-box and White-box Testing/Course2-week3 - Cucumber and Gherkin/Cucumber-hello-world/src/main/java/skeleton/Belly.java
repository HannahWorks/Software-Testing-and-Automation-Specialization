package skeleton;

public class Belly {
    int eatenCucumbers = 0;
    int timeWaited = 0;
    
    public void reset() { 
    	eatenCucumbers = 0; 
    	timeWaited = 0;
    }
    
	public void eat(int cukes) {
    	System.out.println("I ate " + cukes + " cucumbers.");
    	eatenCucumbers += cukes;
    }
    
	public void wait(int timeInHours) {
		if (timeInHours > 0) { 
			timeWaited += timeInHours;
		}
	}
    
	public boolean isGrowling() { 
		return timeWaited >= 2 && eatenCucumbers > 10; 
	}
	
}
