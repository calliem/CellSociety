
public class FishState extends CellState{
	private static int myReproduction;
	public FishState() {
		super("fish");
		myReproduction = 0;
	}
	
	public int getReproduction(){
		return myReproduction;
	}

}
