package cellsociety;

public class Coordinate {
	
	
	private static int LARGE_PRIME_ONE = 709;
	private static int LARGE_PRIME_TWO = 991;
	int myX;
	int myY;
	
	public Coordinate(int x, int y){
		myX = x;
		myY = y;
	}
	
	public int getX(){
		return myX;
	}
	
	public int getY(){
		return myY;
	}
	
	@Override
    public int hashCode() {
        return LARGE_PRIME_ONE*myX + LARGE_PRIME_TWO*myY;
    }

    @Override
    public boolean equals(Object obj) {
       if (!(obj instanceof Coordinate)){
            return false;
       }
       else if (obj == this){
            return true;
       }
       Coordinate rhs = (Coordinate) obj;
       return rhs.getX() == myX && rhs.getY() == myY; 
    }

}
