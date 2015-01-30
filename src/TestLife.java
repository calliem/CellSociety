import java.lang.reflect.InvocationTargetException;


public class TestLife {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException{
		Cell[][] grid1 = makeGrid(5);
		printGrid(grid1);
		LifeController lc= new LifeController();
		Cell[][] grid2 = lc.runOneSim(grid1);
		printGrid(grid2);
		Cell[][] grid3 = lc.runOneSim(grid2);
		printGrid(grid3);
		Cell[][] grid4 = lc.runOneSim(grid3);
		printGrid(grid4);
		Cell[][] grid5 = lc.runOneSim(grid4);
		printGrid(grid5);
		Cell[][] grid6 = lc.runOneSim(grid5);
		printGrid(grid6);
		Cell[][] grid7 = lc.runOneSim(grid6);
		printGrid(grid7);
	}
	//blinker
	static Cell[][] makeGrid(int num){
		Cell[][] outGrid = new Cell[num][num];
		for (int i = 0; i < num; i++){
			for (int j = 0; j < num; j++){
				if((i==1 && (j == 1 || j==2)) 
						|| (i==3 && j==3)
						|| (i==4 && (j==1 || j==3 ||))){
					outGrid[i][j] = new LiveCell();
				}
				else{
					outGrid[i][j] = new EmptyCell();
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
				System.out.println(grid[i][j].toString());
			}
		}
		System.out.println("!!!!");
	}

}
