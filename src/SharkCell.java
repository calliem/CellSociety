
public class SharkCell extends Cell{
	private static final int MAX_ENERGY = 5;
	private int myEnergy;
	
	public SharkCell(){
		super("shark");
		myEnergy = MAX_ENERGY;
	}
	
	public int getEnergy(){
		return myEnergy;
	}
	
	public void decrementEnergy(){
		myEnergy--;
	}
}
