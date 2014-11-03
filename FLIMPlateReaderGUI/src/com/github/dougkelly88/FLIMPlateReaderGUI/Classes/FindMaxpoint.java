/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.dougkelly88.FLIMPlateReaderGUI.Classes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.util.ArrayList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.util.ShapeUtilities;


/**
 *
 * @author dk1109
 */
public class FindMaxpoint {
    
    private JFreeChart chart_;
    private XYDataset findMaxpointData_;
    private XYDataset gatePositionData_;
    private int resolution_ = 200;
//    private XYDataset dataset_;
    
    public FindMaxpoint(){
        findMaxpointData_ = createDummyMaxpointData(0);
        gatePositionData_ = createDummyGatingData();
        chart_ = createChart();
//        chart_ = createOverlaidChart(findMaxpointData_, gatePositionData_);
    }
    private XYDataset createDummyGatingData(){
        
        final XYSeries s1 = new XYSeries("DummyGating");
        s1.add(0, 0);
        s1.add(1000, 0);
        s1.add(2000, 0);
        s1.add(3000, 0);
        s1.add(4000, 0);
        s1.add(5000, 0);
        
        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(s1);
        
        return dataset;
    }
    
    private XYDataset createDummyMaxpointData(int offset){
        
        final XYSeries s1 = new XYSeries("DummyMaxpoint");
        for (int i = 0; i < 16666; i = i+resolution_){
        s1.add(i, 0 + offset);
        }
        
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
    public JFreeChart createChart() {
        
        //http://www.java2s.com/Code/Java/Chart/JFreeChartDualAxisDemo2.htm
        String xlabel = "Delay (ps)";
        String ylabel = "Signal (DN)";
                
        // create the chart with findmaxpoint results
        final JFreeChart chart = ChartFactory.createXYLineChart(
            null,                   // chart title
            xlabel,                 // x axis label
            ylabel,                 // y axis label
            findMaxpointData_,      // data
            PlotOrientation.VERTICAL,
            false,                  // include legend
            true,                   // tooltips
            false                   // urls
        );
        
        final XYPlot plot = chart.getXYPlot();
        // deal with axes and add second dataset
        final NumberAxis yaxis1 = (NumberAxis) plot.getRangeAxis();
        yaxis1.setTickLabelFont(new Font("Dialog", Font.PLAIN, 10));
        yaxis1.setLabelFont(new Font("Dialog", Font.PLAIN, 10));
        final NumberAxis yaxis2 = new NumberAxis(null);
        final NumberAxis xaxis = (NumberAxis) plot.getDomainAxis();
        xaxis.setTickLabelFont(new Font("Dialog", Font.PLAIN, 10));
        xaxis.setLabelFont(new Font("Dialog", Font.PLAIN, 10));
        plot.setRangeAxis(1,yaxis2);
        plot.setDataset(1, gatePositionData_);
        plot.mapDatasetToRangeAxis(1, 1);
        yaxis1.setRange(0,5000);
        yaxis2.setRange(-1,1);
        yaxis2.setTickLabelsVisible(false);
        xaxis.setRange(0,16666);

        // deal with visuals

        final XYLineAndShapeRenderer renderer1= new XYLineAndShapeRenderer(true, true);
        renderer1.setSeriesPaint(0, Color.RED);
        renderer1.setSeriesStroke(0, new BasicStroke(3));

//        renderer1.setBaseShapesVisible(true);
//        renderer1.setSeriesShape(0, ShapeUtilities.createDiagonalCross(4,1));

        plot.setRenderer(0, renderer1);
        
        
//        final StandardXYItemRenderer renderer2 = new StandardXYItemRenderer();
        final XYLineAndShapeRenderer renderer2= new XYLineAndShapeRenderer(false, true);
        renderer2.setSeriesPaint(0, Color.CYAN);
        renderer2.setSeriesShapesFilled(0, Boolean.TRUE);
        renderer2.setBaseShapesVisible(true);
        renderer2.setShape(new Rectangle(-2,-100,4,200));
        renderer2.setOutlineStroke(new BasicStroke(1));
        renderer2.setOutlinePaint(Color.GRAY);
        renderer2.setUseOutlinePaint(true);
        plot.setRenderer(1, renderer2);
        
        plot.setBackgroundPaint(Color.white);
        plot.setDomainGridlinePaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.lightGray);
//                
        return chart;
        
    }
            
    
    public JFreeChart getChart(){
        return chart_;
    }
    
    public void setGatingData(ArrayList<Integer> delays){
        final XYSeries s1 = new XYSeries("delays");
        for (Integer delay : delays) {
            s1.add((double) delay, 0);
        }
        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(s1);
        
        gatePositionData_ = dataset;
        XYPlot plot = chart_.getXYPlot();
        plot.setDataset(1, gatePositionData_);
    }
    
    public void acqMaxpointData(){
        //instrument interacting fn, currently dummy
        findMaxpointData_ = createDummyMaxpointData(500);
        XYPlot plot = chart_.getXYPlot();
        plot.setDataset(0, findMaxpointData_);
        // automatically calculate lifetime
    }
    
    
    public void setResolution(int res){
        resolution_ = res;
    }
    
    public int getResolution(){
        return resolution_;
    }
}
