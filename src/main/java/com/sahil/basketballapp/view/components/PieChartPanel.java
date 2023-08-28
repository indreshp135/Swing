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
        PieDataset dataset = createDataset(playerScores, opponentScore);
        JFreeChart chart = createChart(dataset);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(400, 300));
        add(chartPanel);
    }

    private PieDataset createDataset(HashMap<String, Integer> playerScores, int opponentScore) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (Map.Entry<String, Integer> entry : playerScores.entrySet()) {
            dataset.setValue(entry.getKey(), entry.getValue());
        }
        dataset.setValue("Opponent", opponentScore);
        return dataset;
    }

    private JFreeChart createChart(PieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart(
                "Player Scores",
                dataset,
                false,
                true,
                false);

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionPaint("Opponent", Color.RED);

        // Set all player segments to blue
        // Set all player segments to blue
        for (Object key : dataset.getKeys()) {
            Comparable<?> comparableKey = (Comparable<?>) key;
            if (!comparableKey.equals("Opponent")) {
                plot.setSectionPaint(comparableKey, Color.BLUE);
            }
        }

        return chart;
    }

    public static void main(String[] args) {
        HashMap<String, Integer> playerScores = new HashMap<>();
        playerScores.put("Player 1", 50);
        playerScores.put("Player 2", 30);
        playerScores.put("Player 3", 20);

        int opponentScore = 70;

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Pie Chart Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new PieChartPanel(playerScores, opponentScore));
            frame.pack();
            frame.setVisible(true);
        });
    }
}
