import java.util.Map;

public class SharkCell extends Cell implements ReproducingCell{
	
	private static int myMaxEnergy;
	private static int myReproducingAge;
<<<<<<< HEAD
	private int myCurAge;
	private int myCurEnergy;
	//private static Color myColor;
	private static Color myColor;
=======
	private int myCurrAge;
	private int myCurrEnergy;
>>>>>>> 34117744d8d73640c88a35e5a94ed724ecbf7c73
		
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
<<<<<<< HEAD
		//getShape().setFill(myColor);
		getShape().setFill(Color.BLACK);
		myCurAge = 0;
		myCurEnergy = myMaxEnergy;
		//myEnergy = Integer.parseInt(params.get("fullEnergy"));
=======
		myCurrAge = 0;
		myCurrEnergy = myMaxEnergy;
>>>>>>> 34117744d8d73640c88a35e5a94ed724ecbf7c73
	}

	
	//REFACTOR!!!  make ReproducingCell abstract
	@Override
	public Cell reproducingResult() {
<<<<<<< HEAD
		if(myCurAge >= myReproducingAge){
			myCurAge = 0;
			return new SharkCell("SharkCell");
=======
		if(myCurrAge >= myReproducingAge){
			myCurrAge = 0;
			return new SharkCell(Strings.SHARK_CELL);
>>>>>>> 34117744d8d73640c88a35e5a94ed724ecbf7c73
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
