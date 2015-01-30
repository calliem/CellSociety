import java.lang.reflect.InvocationTargetException;


public class TestFire {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException{
		Cell[][] grid1 = makeGrid(3);
		printGrid(grid1);
		FireController fc= new FireController(100);
		Cell[][] grid2 = fc.runOneSim(grid1);
		printGrid(grid2);
		Cell[][] grid3 = fc.runOneSim(grid2);
		printGrid(grid3);
		Cell[][] grid4 = fc.runOneSim(grid3);
		printGrid(grid4);
		Cell[][] grid5 = fc.runOneSim(grid4);
		printGrid(grid5);
		Cell[][] grid6 = fc.runOneSim(grid5);
		printGrid(grid6);
		Cell[][] grid7 = fc.runOneSim(grid6);
		printGrid(grid7);
	}
	//one in the middle
	static Cell[][] makeGrid(int num){
		Cell[][] outGrid = new Cell[num][num];
		for (int i = 0; i < num; i++){
			for (int j = 0; j < num; j++){
				if(i == (num/2) && j == (num/2)){
					outGrid[i][j] = new FireCell();
				}
				else{
					outGrid[i][j] = new TreeCell();
				}
			}
		}
		return outGrid;
	}
	
	/*beacon/block
	static Cell[][] makeGrid(int num){
		Cell[][] outGrid = new Cell[num][num];
		for (int i = 0; i < num; i++){
			for (int j = 0; j < num; j++){
				if(((i==0 || i==1) && (j==0 || j==1)) ||
						((i==2 || i==3) && (j==2 || j==3))){
					outGrid[i][j] = new LiveCell();
				}
				else{
					outGrid[i][j] = new EmptyCell();
				}
			}
		}
		return outGrid;
	}
	*/
	//glider
	/*
	static Cell[][] makeGrid(int num){
		Cell[][] outGrid = new Cell[num][num];
		for (int i = 0; i < num; i++){
			for (int j = 0; j < num; j++){
				if((i==0 && j==1) || (i==1 && j==2) ||
						(i==2 && j<3)){
					outGrid[i][j] = new LiveCell();
				}
				else{
					outGrid[i][j] = new EmptyCell();
				}
			}
		}
		return outGrid;
	}
	*/
	
	static void printGrid(Cell[][] grid){
		System.out.println("!!!!");
		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j< grid[0].length; j++){
				System.out.println(grid[i][j].toString() + i +  j);
				
			}
		}
		System.out.println("!!!!");
	}

}