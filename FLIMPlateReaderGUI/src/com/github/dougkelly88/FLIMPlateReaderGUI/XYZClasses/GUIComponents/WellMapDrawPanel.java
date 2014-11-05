/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.dougkelly88.FLIMPlateReaderGUI.XYZClasses.GUIComponents;

import com.github.dougkelly88.FLIMPlateReaderGUI.GeneralClasses.PlateProperties;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

/**
 *
 * @author dk1109
 */
public class WellMapDrawPanel extends JPanel {

        int currentX_ = 128;    
        int currentY_ = 128;    
        int currentFOVw_ = 12;
        int currentFOVh_ = 16;
        int r_ = 127;
        String currentWell_ = "C4";
        String wellShape_ = "Square";
        double wellSizeUm = 9000;
        boolean enabled_ = false;   // start up disabled, enable upon loading plate stuff. 
        PlateProperties pp_ = new PlateProperties();
        double conversionFactor_ = pp_.getWellSize()/r_;    //N.B. might be useful to have this as 1x2 array with x, y conv, which could be negative...
        
        WellMapDrawPanel() {
            // set a preferred size for the custom panel.
            setPreferredSize(new Dimension(255,255));
            
//            this.setEnabled(false);     
            addMouseListener(new MouseAdapter() {
                
                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (enabled_){
                        boolean inBounds = false;
                        Point p = e.getPoint();
                        // check if point is within bounds of well...
                        if (pp_.getWellShape() == "Round"){
                            inBounds = ( Math.pow((p.x + currentFOVw_/2 - r_),2) +
                                    Math.pow((p.y + currentFOVh_/2 - r_),2) < r_*r_ );
                        }
                        else{
                            inBounds = ( (Math.abs(p.x - r_) < r_) && (Math.abs(p.y - r_) < r_) );
                        }
                    
                        // move stage - instrument interacting functions...
                    
                        // redraw well
                        if (inBounds){
                            currentX_ = p.x;
                            currentY_ = p.y;
                            repaint();
                        }
                    }
                }
            });
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.setFont((new Font("Dialog", Font.PLAIN, 16)));
            g.drawString(currentWell_, (r_+1 - 8), (r_+1 + 8)); // fix - see below
            
//            g.setFont(new Font("Dialog", Font.PLAIN, 32));
//            
//            Rectangle2D bounds = g.getFontMetrics().getStringBounds(currentWell_, g);
//            g.drawString(currentWell_, (int) ((r_ - bounds.getWidth()) / 2), r_);     

            g.setColor(Color.CYAN);
            
            if ("Square".equals(pp_.getWellShape()))
                g.drawRect(0, 0, 2*r_, 2*r_);
            else 
                g.drawOval(0, 0, (1 + 2*r_), (1 + 2*r_));
            
            g.setColor(Color.RED);
            g.drawRect((currentX_-currentFOVw_/2), (currentY_-currentFOVh_/2), 
                    currentFOVw_, currentFOVh_);
        
            g.setColor(Color.GREEN);
            // draw saved FOVs
            
        }
        
        public void setCurrentX(double stagex){
            // identify which well we're (now) in, and position relative to 
            // centre of well, converting from um to pixels
            currentX_ = (int) stagex;
            repaint();
        }
        
        public int getCurrentX(){
            return currentX_;
        }
        
        public void setCurrentY(double stagey){
            // identify which well we're (now) in, and position relative to 
            // centre of well, converting from um to pixels
            currentY_ = (int) stagey;
            repaint();
        }
        
        public int getCurrentY(){
            return currentY_;
        }
        
        public void setCurrentWell(String well){
            currentWell_ = well;
            repaint();
        }
        
        public void setEnabled(boolean enabled, PlateProperties pp){
            enabled_ = enabled;
            pp_ = pp;
            // update conversion factor based on plate...
            conversionFactor_ = pp_.getWellSize()/r_;
            repaint();
        }
        
        @Override
        public boolean isEnabled(){
            return enabled_;
        }
        
//        public class PlateActionListener implements ActionListener{
//            String s;
//
//            public PlateActionListener(String shape){
//                s = shape;
//            }
//            
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                wellShape_ = this.s;
//            }
//        
//        }

     }

