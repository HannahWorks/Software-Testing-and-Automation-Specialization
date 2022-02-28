package microwave;

public class ModeController {

	public enum Mode {Cooking, Suspended, Setup};
	
	private boolean startPressed = false;
	private boolean clearPressed = false;
	private Mode mode = Mode.Setup;
	private Mode preMode = Mode.Setup;
	
	public synchronized boolean isStartPressed() {
		return startPressed;
	}

	public synchronized void setStartPressed(boolean startPressed) {
		this.startPressed = startPressed;
	}

	public synchronized boolean isClearPressed() {
		return clearPressed;
	}

	public synchronized void setClearPressed(boolean clearPressed) {
		this.clearPressed = clearPressed;
	}

	public synchronized Mode getMode() {
	 	return mode;
	}

	synchronized Mode tick(boolean doorOpen, boolean timeRemaining) {

		if (!timeRemaining) {
			mode = Mode.Setup;
		}
		else if (doorOpen && mode == Mode.Cooking) {
			mode = Mode.Suspended;
		} 
		else if (clearPressed) {
			if (mode == Mode.Cooking) {
				mode = Mode.Suspended; 
			} else if (mode == Mode.Suspended) {
				mode = Mode.Setup;
			}
		} 
		else if (startPressed && !doorOpen) {
			mode = Mode.Cooking;
		}
		
		startPressed = false;
		clearPressed = false;
		
		return mode;
	}
	
	public boolean modeChanged() {
		return mode != preMode;
	}
	
	public boolean inCooking() {
		return (mode == preMode &&
				mode == Mode.Cooking);
	}
	
}
