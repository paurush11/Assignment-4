package edu.neu.coe.info6205.union_find;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.JFrame;

public class HWQUPC_Solution {

    private static int createHWQUPC(int n) {

        int no_connections = 0;
        int no_pairs = 0;

        UF_HWQUPC uf = new UF_HWQUPC(n);
        while (uf.components() != 1) {
            int x = (int) (Math.random() * (n));
            int y = (int) (Math.random() * (n));
            no_pairs += 1;
            if (!uf.connected(x, y)) {
                uf.union(x, y);
                no_connections++;
            }
        }
        System.out.println("Number of connections: " + no_connections);
        System.out.println("Number of pairs: " + no_pairs + "\n");
        return no_pairs;
    }

    public static void main(String[] args) {

        int n = 100;
        int numTrials = 7;
        int increment = 3000;

        XYSeries series = new XYSeries("Pairs Count vs Node Count");
        XYSeries series2 = new XYSeries("Pairs Count vs Node Count (NlogN)");
        double avgPairsPerNodeLogN = 0.0;
        for (int i = 0; i < numTrials; i++) {
            System.out.println("Number of nodes: " + n);
            int no_pairs = createHWQUPC(n);
            series.add(n, no_pairs);

            avgPairsPerNodeLogN += (no_pairs / (n * Math.log(n)));

            n += increment;
        }

        avgPairsPerNodeLogN /= numTrials;
        n = 100;
        for (int i = 0; i < numTrials; i++) {
            series2.add(n, avgPairsPerNodeLogN * (n * Math.log(n)));
            n += increment;
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        dataset.addSeries(series2);

        JFreeChart chart = ChartFactory.createXYLineChart("Number of Nodes vs Number of Pairs", "Number of Nodes",
                "Number of Pairs", dataset, PlotOrientation.VERTICAL, true, true, false);

        JFrame frame = new JFrame("Paurush Batish - 002755631");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ChartPanel(chart));
        frame.setSize(1000, 500);
        frame.setVisible(true);
    }

}
