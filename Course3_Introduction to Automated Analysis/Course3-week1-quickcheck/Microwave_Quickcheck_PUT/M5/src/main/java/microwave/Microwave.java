package microwave;
import java.util.List;

public class Microwave {
	private ModeController mc;
	private DisplayController dc;
	List<Preset> presets; 
	private int powerLevel; 

	private boolean doorOpen, cooking;
	private ModeController.Mode mode = ModeController.Mode.Setup;

	public Microwave(ModeController mc, DisplayController dc, 
			List<Preset> presets) {
		this.mc = mc;
		this.dc = dc;
		cooking = false;
		doorOpen = true; // start in fail-safe state.
		this.presets = presets;
	}
	
	public void startPressed() { mc.setStartPressed(true); }
	public void clearPressed() { mc.setClearPressed(true); 
								 dc.setClearPressed(true); }

	public void digitPressed(int digit) {
		dc.digitPressed(digit);
	}
	
	public void presetPressed(int preset) {
		if (preset < 1 || preset > presets.size()) {
			throw new IllegalArgumentException("Preset out of range for presetPressed.");
		}
		// Presets only do something when in setup mode.
		if (mode != ModeController.Mode.Setup) {
			return;
		}
		Preset p = presets.get(preset);			// Fault: off-by-one: should be preset -1
		
		dc.setTimeToCook(p.getTimeToCook());
		setPowerLevel(p.getPowerLevel());
	}
	
	public List<Preset> getPresets() {
		return presets;
	}

	public void setDoorOpen(boolean doorOpen) { this.doorOpen = doorOpen; }
	
	public void setPowerLevel(int powerLevel) { 
		if (powerLevel >= 1 && powerLevel <= 10) {
			this.powerLevel = powerLevel; 
		} else {
			throw new IllegalArgumentException("power level out of range");
		}
	}
	
	public int getPowerLevel() {
		return this.powerLevel; 
	}
	
	public boolean isDoorOpen() {
		return doorOpen;
	}

	public void tick() {
		dc.tick(mode);
		mode = mc.tick(doorOpen, dc.timeToCook() != 0);
		cooking = (mode == ModeController.Mode.Cooking);
	}
	
	public boolean isCooking() { return cooking; }
	
	public byte [] digits() { return dc.getDigits().clone(); } ; 
	
	public ModeController.Mode getMode() { return mode; }
	
	public int getTickRateInHz() { return dc.getTickRateInHz(); }
	
	public void setTickRateInHz(int tickRate) { dc.setTickRateInHz(tickRate); }
}
