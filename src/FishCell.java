import java.util.Map;

public class FishCell extends Cell implements ReproducingCell{

	// private int reproductionAge; this should be passed into the controller

	private static int myReproducingAge;
	private int myCurrAge;

	public FishCell(String name){
		super(name);
		myCurrAge = 0;

	}

	public FishCell(Map<String, String> params) {
		super(params);
		myReproducingAge = Integer.parseInt(params.get("reproductionAge"));
		myCurrAge = 0;

	}

	public int getAge(){
		return myReproducingAge;
	}

	//REFACTOR!!!! make ReproducingCell interactive
	@Override
	public Cell reproducingResult() {
		if(myCurrAge >= myReproducingAge){
			myCurrAge = 0;
			return new FishCell("FishCell");
		}
		else{
			return new Cell("EmptyCell");
		}
	}

	public Cell ageOneChronon(){
		myCurrAge++;
		return this;
	}
	
	public String toString(){
		return "FishCell";
	}

}
