public class GroundCell extends Cell implements Updatable {

	private static int sugarGrowBackRate;
	private static int sugarGrowBackInterval;
	private int maxSugar;
	private int curSugar;
	private int curInterval;

	
	//public GroundCell()

	@Override
	public void ageOneChronon(GridData data) {
		if(sugarGrowBackInterval == ++curInterval){
			curSugar = Math.min(curSugar+sugarGrowBackRate, maxSugar);
		}
		data.getNewGrid()[data.getRow()][data.getCol()] = this;
	}
}
