/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.dougkelly88.FLIMPlateReaderGUI.SequencingClasses.GUIComponents;

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
public class PlateMapDrawPanel extends JPanel {

        int[] plateSizePixels = {470, 313};
        String currentWell_ = "C4";
        String wellShape_ = "Square";
        double wellSizeUm_ = 6500;
        double wellSpacingUm_ = 9000;
        boolean enabled_ = false;   // start up disabled, enable upon loading plate stuff. 
        PlateProperties pp_ = new PlateProperties();
        double conversionFactor_ = ((pp_.getPlateColumns() + 1) * 
                pp_.getWellSpacingH())/plateSizePixels[0];    //N.B. might be useful to have this as 1x2 array with x, y conv, which could be negative...
        
        PlateMapDrawPanel() {
            // set a preferred size for the custom panel.
            setPreferredSize(new Dimension(470, 313));
            
//            this.setEnabled(false);     
            addMouseListener(new MouseAdapter() {
                
                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (enabled_){
                        // handle stuff - move well, select wells?
                        // check paint demo for select with ROI
                    }
                }
            });
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            int wellSpaceH = (int) (pp_.getWellSpacingH()/conversionFactor_);
            int wellSpaceV = (int) (pp_.getWellSpacingV()/conversionFactor_);
            int wellSize = (int) (pp_.getWellSize()/conversionFactor_);
            Font font = g.getFont();
//          font = font.deriveFont(Font.PLAIN, 16);
            g.setFont(font.deriveFont(Font.PLAIN, 11));
            
            for (int cols = 0; cols < pp_.getPlateColumns(); cols++){
                
                for (int rows = 0; rows < pp_.getPlateRows(); rows++){

                    g.setColor(Color.BLACK);
                    
                    
                    String wellString = Character.toString((char) (65 + rows)) + Integer.toString(cols+1);
                    Rectangle2D bounds = g.getFontMetrics().getStringBounds(wellString, g);
                    
                    g.drawString(wellString, 
                            (int)(wellSpaceH/2) + cols*wellSpaceH - (int)(bounds.getWidth()/2),
                            (int)(wellSpaceV/2) + rows*wellSpaceV + (int)(bounds.getHeight()/2));
                    
                    g.setColor(Color.CYAN);
                    
                    if ("Square".equals(pp_.getWellShape()))
                        g.drawRect((int)(wellSpaceH/2) + cols*wellSpaceH - (int) (wellSize/2),
                                (int)(wellSpaceV/2) + rows*wellSpaceV - (int) (wellSize/2), 
                                wellSize, wellSize);
                    else 
                        g.drawOval((int)(wellSpaceH/2) + cols*wellSpaceH - (int) (wellSize/2),
                                (int)(wellSpaceV/2) + rows*wellSpaceV - (int) (wellSize/2), 
                                wellSize, wellSize);
                    
                }
            
            }
           
        }
        
       
        
        public void setCurrentWell(String well){
            currentWell_ = well;
            repaint();
        }
        
        public void setEnabled(boolean enabled, PlateProperties pp){
            enabled_ = enabled;
            pp_ = pp;
            // update conversion factor based on plate...
            conversionFactor_ = ((pp_.getPlateColumns() + 1) * 
                pp_.getWellSpacingH())/plateSizePixels[0];
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

