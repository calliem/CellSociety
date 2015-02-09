import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class WatorController extends ComplexController{

	public WatorController(Map<String, String> parameters) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException{
		super(parameters);
	}

	private void manipulateCell(GridData data){        
		String type = data.curCell().toString();
		List<Coordinate> eligibleDestinations = makeEligible(data, type);//new ArrayList<Integer[]>();                                                                                                                                      
		if(eligibleDestinations.isEmpty()){                                                                                                      
			data.getNewGrid()[data.getRow()][data.getCol()] = ((ReproducingCell) data.curCell()).ageOneChronon();                                                             
		}                                                                                                                                        
		else{                                                                                                                                    
			Coordinate dest = getDestination(eligibleDestinations);                                                                                   
			data.getNewGrid()[dest.getX()][dest.getY()] = ((ReproducingCell) (data.curCell())).ageOneChronon();                       
			//grid[dest[0]][dest[1]] = ((ReproducingCell) (grid[r][c])).ageOneChronon();                       
			Coordinate currCoord = data.curCoord();
			if(type.equals("SharkCell")){
				if(data.getGrid()[dest.getX()][dest.getY()].toString().equals("FishCell")){
					((SharkCell) data.getNewGrid()[dest.getX()][dest.getY()]).replenishEnergy();
				}
			}
			data.getList().add(dest);                                                                                                            
			data.getList().add(currCoord);                                                                                                       
			data.getNewGrid()[data.getRow()][data.getCol()] = ((ReproducingCell) data.curCell()).reproducingResult();                                                                       
		}                                                                                                                                        
	}                                                                                                                                        
	
	private List<Coordinate> makeEligible(GridData data, String type) {
		List<Coordinate> list = new ArrayList<Coordinate>();
		if(type.equals("FishCell")){                                                                                                             
			list = getNeighbors(data, "EmptyCell");                                                        
		}                                                                                                                                        
		else{                                                                                                                                    
			list = getNeighbors(data, "FishCell");                                                         
			if(list.isEmpty()){                                                                                                      
				list = getNeighbors(data, "EmptyCell");                                                        
			}                                                                                                                                        
		}                                                                                                                                        
		return list;
	}

	private List<Coordinate> getNeighbors(GridData data, String s){
		List<Coordinate> list = myNeighbor.getNeighbors(data.getGrid(), data.getRow(), data.getCol());
		for(Coordinate c : new ArrayList<Coordinate>(list)){
			if (!(data.getGrid()[c.getX()][c.getY()].toString().equals(s)) || data.getList().contains(c)){
				list.remove(c);
			}
		}
		return list;
	}

	private Coordinate getDestination(List<Coordinate> eligibleDestinations) {
		return eligibleDestinations.get(new Random().nextInt(eligibleDestinations.size()));
	}

	private boolean deadShark(Cell cell) {
		if(cell instanceof SharkCell){
			return ((SharkCell) cell).isDead();
		}
		return false;
	}

	@Override
	protected void cellUpdate(GridData data) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		if(deadShark(data.curCell())){
			data.getNewGrid()[data.getRow()][data.getCol()] = ((SharkCell) data.curCell()).reproducingResult();
		}
		else if(data.curCell().toString().equals("EmptyCell")){
			data.getNewGrid()[data.getRow()][data.getCol()] = makeCell("EmptyCell");
		}
		else{
			manipulateCell(data); 
		}	
	}

	@Override
	protected List<String> typeTriage(List<String> list) {
		list.add("SharkCell");
		list.add("FishCell");
		list.add("EmptyCell");
		return list;
	}
}
