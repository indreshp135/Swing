package com.sahil.basketballapp.view.components;

import javax.swing.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class PieChartPanel extends JPanel {

    public PieChartPanel(HashMap<String, Integer> playerScores, int opponentScore) {
        PieDataset<String> dataset = createDataset(playerScores, opponentScore);
        JFreeChart chart = createChart(dataset, opponentScore);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(400, 300));
        add(chartPanel);
    }

    private PieDataset<String> createDataset(HashMap<String, Integer> playerScores, int opponentScore) {
        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
        for (Map.Entry<String, Integer> entry : playerScores.entrySet()) {
            dataset.setValue(entry.getKey() + " (" + entry.getValue() + ")", entry.getValue());
        }
        dataset.setValue("Opponent" + " (" + opponentScore + ")", opponentScore);
        return dataset;
    }

    private JFreeChart createChart(PieDataset<String> dataset, int opponentScore) {
        JFreeChart chart = ChartFactory.createPieChart(
                "Score Breakdown",
                dataset,
                true, // Show legend
                false,
                true); // Don't generate tooltips

        PiePlot<String> plot = (PiePlot<String>) chart.getPlot();
        plot.setSectionPaint("Opponent" + " (" + opponentScore + ")", Color.RED);

        // Set all player segments to different colors
        int colorIndex = 0;
        for (Object key : dataset.getKeys()) {
            Comparable<?> comparableKey = (Comparable<?>) key;
            if (!comparableKey.equals("Opponent" + " (" + opponentScore + ")")) {
                Color color = getSegmentColor(colorIndex);
                plot.setSectionPaint(comparableKey, color);
                colorIndex++;
            }
        }

        return chart;
    }

    private Color getSegmentColor(int index) {
        Color[] colors = {
            Color.BLUE, Color.GREEN, Color.ORANGE, Color.YELLOW, Color.MAGENTA,
            Color.CYAN, Color.PINK, Color.DARK_GRAY, Color.LIGHT_GRAY
        };
        return colors[index % colors.length];
    }
}
