package com.sahil.basketballapp.view.components;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;

import com.sahil.basketballapp.model.StatsModel.PointsModel;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.text.SimpleDateFormat;

public class ExtendedRegressionLineChart extends JPanel {
    public static int DAYS_TO_EXTEND = 2;

    public ExtendedRegressionLineChart(String title, List<PointsModel> gameDataList) {

        // Create dataset
        DefaultXYDataset dataset = createDataset(gameDataList, DAYS_TO_EXTEND);

        // Create chart
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Game Scores Over Time with Extended Regression Line", // Chart title
                "Game Date", // X-axis label
                "Points", // Y-axis label
                dataset, // Dataset
                PlotOrientation.VERTICAL, // Plot orientation
                true, // Show legend
                true, // Use tooltips
                false // Generate URLs
        );

        XYPlot plot = (XYPlot) chart.getPlot();

        // Customize the X-axis to show dates
        DateAxis xAxis = new DateAxis("Game Date");
        xAxis.setDateFormatOverride(new SimpleDateFormat("MM/dd/yyyy"));
        plot.setDomainAxis(xAxis);

        // Highlight data points with circles
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, true);
        renderer.setSeriesShapesVisible(0, true); // Series index 0
        renderer.setSeriesShape(0, new java.awt.geom.Ellipse2D.Double(-3, -3, 6, 6)); // Adjust point size
        renderer.setSeriesLinesVisible(1, true); // Series index 1 (regression line)
        renderer.setSeriesStroke(1,
                new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] { 2 }, 0)); // Dotted
                                                                                                            // line
        plot.setRenderer(renderer);

        // Create a ChartPanel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));

        // Add ChartPanel to the JFrame
        add(chartPanel);
    }

    private DefaultXYDataset createDataset(List<PointsModel> gameDataList, int daysToExtend) {
        DefaultXYDataset dataset = new DefaultXYDataset();

        double[][] series = new double[2][gameDataList.size()];
        for (int i = 0; i < gameDataList.size(); i++) {
            PointsModel data = gameDataList.get(i);
            series[0][i] = data.getDate().getTime();
            series[1][i] = data.getScore();
        }

        dataset.addSeries("Game Scores", series);

        // Perform linear regression and add regression line
        LinearRegression linearRegression = new LinearRegression(series);
        double minX = series[0][0];
        double maxX = series[0][series[0].length - 1];

        // Extend regression line
        double[][] extendedLine = new double[2][2];
        extendedLine[0][0] = minX;
        extendedLine[0][1] = maxX + daysToExtend * 86400000; // Extend by daysToExtend days in milliseconds
        extendedLine[1][0] = linearRegression.predict(minX);
        extendedLine[1][1] = linearRegression.predict(extendedLine[0][1]);
        dataset.addSeries("Regression", extendedLine);

        return dataset;
    }

}

class LinearRegression {
    private double slope;
    private double intercept;

    public LinearRegression(double[][] data) {
        int n = data[0].length;
        double sumX = Arrays.stream(data[0]).sum();
        double sumY = Arrays.stream(data[1]).sum();
        double sumXX = Arrays.stream(data[0]).map(x -> x * x).sum();
        double sumXY = 0;

        for (int i = 0; i < n; i++) {
            sumXY += data[0][i] * data[1][i];
        }

        slope = (n * sumXY - sumX * sumY) / (n * sumXX - sumX * sumX);
        intercept = (sumY - slope * sumX) / n;
    }

    public double predict(double x) {
        return slope * x + intercept;
    }
}