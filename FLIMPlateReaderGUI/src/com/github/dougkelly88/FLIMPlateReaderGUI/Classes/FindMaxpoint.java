/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.dougkelly88.FLIMPlateReaderGUI.Classes;

import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickMarkPosition;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.date.SerialDate;

/**
 *
 * @author dk1109
 */
public class FindMaxpoint {
    
    private JFreeChart chart_;
    private IntervalXYDataset dataset_;
    private IntervalXYDataset dataset2_;
    
    public FindMaxpoint(){
        dataset_ = createDummyDataset(0);
        dataset2_ = createDummyDataset(1000);
        chart_ = createDummyChart(dataset_, dataset2_);
//        chart_ = createOverlaidChart(dataset_, dataset2_);
    }
    
    private IntervalXYDataset createDummyDataset(int offset){
        
        final XYSeries s1 = new XYSeries("Dummy");
        s1.add(1000, 0 + offset);
        s1.add(2000, 1000 + offset);
        s1.add(3000, 4000 + offset);
        s1.add(4000, 3000 + offset);
        s1.add(5000, 1000 + offset);
        s1.add(6000, 500 + offset);
        s1.add(16000, 100 + offset);
        
        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(s1);
        
        return dataset;
    }
    
    /**
     * Creates a chart.
     * 
     * @param dataset  the data for the chart.
     * 
     * @return a chart.
     */
    private JFreeChart createDummyChart(final XYDataset dataset, final XYDataset dataset2) {
        
        String xlabel = "Delay (ps)";
        String ylabel = "Signal (DN)";
        
        // create the chart...
        final JFreeChart chart = ChartFactory.createXYLineChart(
            null,           // chart title
            xlabel,           // x axis label
            ylabel,          // y axis label
            dataset_,               // data
            PlotOrientation.VERTICAL,
            false,                  // include legend
            true,                   // tooltips
            false                   // urls
        );
        
        final JFreeChart chart2 = ChartFactory.createXYBarChart(
                null, 
                xlabel, 
                false, 
                ylabel, 
                dataset2_, 
                PlotOrientation.HORIZONTAL, 
                false, 
                true, 
                false);
        


        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        chart.setBackgroundPaint(Color.white);

        
        // get a reference to the plot for further customisation...
        final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        
        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, true);
        plot.setRenderer(renderer);

        // change the auto tick unit selection to integer units only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setTickLabelFont(new Font("Dialog", Font.PLAIN, 10));
        rangeAxis.setLabelFont(new Font("Dialog", Font.PLAIN, 10));
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setRange(0, 5000);
        
        final NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
        domainAxis.setTickLabelFont(new Font("Dialog", Font.PLAIN, 10));
        domainAxis.setLabelFont(new Font("Dialog", Font.PLAIN, 10));
        domainAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        domainAxis.setRange(0, 16666);
        
        
        
       
        // OPTIONAL CUSTOMISATION COMPLETED.
                
        return chart;
        
    }
            
//    private JFreeChart createOverlaidChart(IntervalXYDataset data1, IntervalXYDataset data2) {
//
//        String xlabel = "Delay (ps)";
//        String ylabel = "Signal (DN)";
//        JFreeChart c;
//        // create plot ...
////        final IntervalXYDataset data1 = createDummyDataset(10);
//        final XYItemRenderer renderer1 = new XYBarRenderer(0.20);
//        renderer1.setSeriesPaint(0, Color.BLUE);
//        final NumberAxis domainAxis = new NumberAxis(xlabel);
//        domainAxis.setTickLabelFont(new Font("Dialog", Font.PLAIN, 10));
//        domainAxis.setLabelFont(new Font("Dialog", Font.PLAIN, 10));
//        domainAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
//        domainAxis.setRange(0, 16666);
//        final ValueAxis rangeAxis = new NumberAxis(ylabel);
//        rangeAxis.setTickLabelFont(new Font("Dialog", Font.PLAIN, 10));
//        rangeAxis.setLabelFont(new Font("Dialog", Font.PLAIN, 10));
//        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
//        rangeAxis.setRange(0, 5000);
//        
//        final XYPlot plot = new XYPlot(data1, domainAxis, rangeAxis, renderer1);
//        
//        // add a second dataset and renderer...
////        final XYDataset data2 = createDummyDataset(100);
//        final XYItemRenderer renderer2 = new StandardXYItemRenderer();
//        renderer2.setSeriesPaint(0, Color.RED);
//        
//        renderer2.setToolTipGenerator(      //As of version 1.0.6, this override setting should not be used. You can use the base setting instead (XYItemRenderer.setBaseToolTipGenerator(XYToolTipGenerator)).
//            new StandardXYToolTipGenerator(
//                StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
//                new DecimalFormat("0.0"), new DecimalFormat("0.0")
//            )
//        );
//        plot.setDataset(1, data2);
//        plot.setRenderer(1, renderer2);
//        
//        
//        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
//        plot.setBackgroundPaint(Color.white);
//        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
//        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
//        
//        c = new JFreeChart(null, plot);
//        c.removeLegend();
//       
//        return c;
////        return new JFreeChart("Overlaid Plot Example", JFreeChart.DEFAULT_TITLE_FONT, plot, true);
//
//    }

    
    public JFreeChart getDummyChart(){
        return chart_;
    }
}
