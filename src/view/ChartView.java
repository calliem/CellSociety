package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cell.Cell;
import javafx.geometry.Insets;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;

public class ChartView {

	private LineChart<Number, Number> myChart;
	private List<Series<Number, Number>> mySeries;
	
	public ChartView() {
		
		Axis<Number> xAxis = new NumberAxis();
		xAxis.setAutoRanging(true);
		xAxis.setTickLabelsVisible(false);
	
		Axis<Number> yAxis = new NumberAxis();
		yAxis.setAutoRanging(true);
	
		myChart = new LineChart<Number, Number>(xAxis, yAxis);
		myChart.setAnimated(false);
		myChart.setCreateSymbols(false);
		myChart.setPadding(new Insets(0, 50, 0, 25));
	
	}
	
	public void updateChartLines(Cell[][] cells, int numFrames, String[] names) {

		HashMap<String, Integer> cellCounts = new HashMap<String, Integer>();

		for (int i = 0; i < names.length; i++) {
			cellCounts.put(names[i], 0);
		}

		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[0].length; j++) {
				String cellName = cells[i][j].toString();

				cellCounts.put(cellName, cellCounts.get(cellName) + 1);

			}
		}

		for (int i = 0; i < mySeries.size(); i++) {
			Series<Number, Number> series = mySeries.get(i);
			series.getData().add(
					new Data<Number, Number>(numFrames, cellCounts.get(names[i])));
		}

	}
	
	public void generateChartLines(String[] cellNames) {

		myChart.getData().clear();
		mySeries = new ArrayList<Series<Number, Number>>();

		for (int i = 0; i < cellNames.length; i++) {
			Series<Number, Number> series = new Series<Number, Number>();
			series.setName(cellNames[i]);
			mySeries.add(series);
		}

		myChart.getData().addAll(mySeries);

	}
	
	public LineChart<Number, Number> getChart() {
		return myChart;
	}
}
