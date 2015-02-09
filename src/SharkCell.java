import java.util.Map;

public class SharkCell extends Cell implements ReproducingCell{
	
	private static int myMaxEnergy;
	private static int myReproducingAge;

	private int myCurAge;
	private int myCurEnergy;

		
	public SharkCell(String name){
		super(name);
		myCurAge = 0;
		myCurEnergy = myMaxEnergy;
		//getShape().setFill(myColor);
	}

	public SharkCell(Map<String, String> params) {
		super(params);
		System.out.println("cellparammap in shark cell:");
		for (String s: params.keySet()){
			System.out.print(s + ": ");
			System.out.println(params.get(s));
		}
		
		myMaxEnergy = Integer.parseInt(params.get("maxEnergy"));
		System.out.println(myMaxEnergy);
		myReproducingAge = Integer.parseInt(params.get("reproductionAge"));
		System.out.println(myReproducingAge);

		myCurAge = 0;
		myCurEnergy = myMaxEnergy;

	}

	
	//REFACTOR!!!  make ReproducingCell abstract
	@Override
	public Cell reproducingResult() {

		if(myCurAge >= myReproducingAge){
			myCurAge = 0;
			return new SharkCell(Strings.SHARK_CELL);
		}
		else{
			return new Cell(Strings.EMPTY_CELL);
		}
	}
	
	public int getEnergy(){
		return myCurEnergy;
	}
	
	public Cell ageOneChronon(){
		myCurAge++;
		myCurEnergy--;
		return this;
	}
	
	public boolean isDead(){
		return myCurEnergy == 0;
	}
	
	public String toString(){
		return "SharkCell";
	}

	public void replenishEnergy() {
		myCurEnergy = myMaxEnergy;
		
	}
}
