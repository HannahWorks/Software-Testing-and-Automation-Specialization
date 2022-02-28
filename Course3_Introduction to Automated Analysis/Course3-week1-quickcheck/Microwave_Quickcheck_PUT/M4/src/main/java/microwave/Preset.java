package microwave;

public class Preset {
	private String name;
	private int timeToCook;
	private int powerLevel;
	
	public Preset(String name, int timeInSeconds, int powerLevel) {
		super();
		this.name = name;
		this.timeToCook = timeInSeconds;
		this.powerLevel = powerLevel;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Preset [name=" + name + ", timeToCook=" + timeToCook + ", powerLevel=" + powerLevel +  "]";
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTimeToCook() {
		return timeToCook;
	}

	public void setTimeToCook(int timeInSeconds) {
		this.timeToCook = timeInSeconds;
	}

	public int getPowerLevel() {
		return powerLevel;
	}

	public void setPowerLevel(int powerLevel) {
		this.powerLevel = powerLevel;
	}
	
	
}
