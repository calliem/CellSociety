
public class Coordinate {
	
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
        return 709*myX + 991*myY;
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
