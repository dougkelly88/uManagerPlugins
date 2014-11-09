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
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author dk1109
 */
public class PlateMapDrawPanel extends JPanel {

        Point selectionStart_;
        Point selectionEnd_;
        Rectangle selection_;
        boolean isSelecting_ = false;
        ArrayList<ArrayList<Boolean>> wellsSelected_;
        Color transRed = new Color(128, 0, 0, 64);
        
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
                    selectionStart_ = e.getPoint();
                    isSelecting_ = true;
                }

                @Override
                public void mouseReleased(MouseEvent e) {
//                    if (enabled_){
                        isSelecting_ = false;
                        selectionEnd_ = e.getPoint();
                        getSelectedWells();
                        repaint();
//                    }
                }              
                
            });
            
            addMouseMotionListener(new MouseMotionAdapter(){
                @Override
                public void mouseDragged(MouseEvent e){
                    selection_ = new Rectangle(
                            selectionStart_.x,
                            selectionStart_.y,
                            e.getPoint().x - selectionStart_.x,
                            e.getPoint().y - selectionStart_.y
                    );
//                    getSelectedWells();
                    repaint();
                }
                
            });
            
            wellsSelected_ = new ArrayList<ArrayList<Boolean>>();
            for (int cols = 0; cols < pp_.getPlateColumns(); cols++){
                ArrayList<Boolean> temp = new ArrayList<Boolean>();
                for (int rows = 0; rows < pp_.getPlateRows(); rows++){
                    temp.add(false);
                }
                wellsSelected_.add(temp);
            }
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
                
                ArrayList<Boolean> temp = wellsSelected_.get(cols);
                
                for (int rows = 0; rows < pp_.getPlateRows(); rows++){

                    g.setColor(Color.BLACK);

                    String wellString = Character.toString((char) (65 + rows)) + Integer.toString(cols+1);
                    Rectangle2D bounds = g.getFontMetrics().getStringBounds(wellString, g);
                    
                    g.drawString(wellString, 
                            (int)(wellSpaceH/2) + cols*wellSpaceH - (int)(bounds.getWidth()/2),
                            (int)(wellSpaceV/2) + rows*wellSpaceV + (int)(bounds.getHeight()/2));
                    
                    g.setColor(Color.CYAN);
                    
                    int x = (int)(wellSpaceH/2) + cols*wellSpaceH - (int) (wellSize/2);
                    int y = (int)(wellSpaceV/2) + rows*wellSpaceV - (int) (wellSize/2);
                    if ("Square".equals(pp_.getWellShape())){
                        if (temp.get(rows)){
                            g.setColor(transRed);
                            g.fillRect(x + 1, y + 1, wellSize - 2, wellSize - 2);
                        }
                        g.setColor(Color.CYAN);
                        g.drawRect(x, y, wellSize, wellSize);
                    }
                    else {
                        if (temp.get(rows)){
                            g.setColor(transRed);
                            g.fillOval(x + 1, y + 1, wellSize - 2, wellSize - 2);
                        }
                        g.setColor(Color.CYAN);
                        g.drawOval(x, y, wellSize, wellSize);
                    }
                    
                }
            
            }
            
            if (isSelecting_) {
                g.setColor(transRed);
                g.fillRect(selection_.x, selection_.y, 
                        selection_.width, selection_.height);
                
            }
            
        }
        
        private void getSelectedWells(){
            // convert selected region into list of selected wells
            // TODO: currently selects well IFF centre of well is enclosed in
            //      selection rectangle. Update such that well is selected if 
            //      any part is enclosed. 
            int wellSpaceH = (int) (pp_.getWellSpacingH()/conversionFactor_);
            int wellSpaceV = (int) (pp_.getWellSpacingV()/conversionFactor_);
            int wellSize = (int) (pp_.getWellSize()/conversionFactor_);
            
            for (int cols = 0; cols < pp_.getPlateColumns(); cols++){
                ArrayList<Boolean> temp = wellsSelected_.get(cols);
                for (int rows = 0; rows < pp_.getPlateRows(); rows++){
                    int wellYPixels = (int)(wellSpaceV/2) + rows*wellSpaceV;
                    int wellXPixels = (int)(wellSpaceH/2) + cols*wellSpaceH;
                    
                    if (selection_.contains(wellXPixels, wellYPixels)){
                        
                        temp.set(rows, !(temp.get(rows)));
                    }
                }
                wellsSelected_.set(cols, temp);
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
            
            wellsSelected_.clear();
            for (int cols = 0; cols < pp_.getPlateColumns(); cols++){
                ArrayList<Boolean> temp = new ArrayList<Boolean>();
                for (int rows = 0; rows < pp_.getPlateRows(); rows++){
                    temp.add(false);
                }
                wellsSelected_.add(temp);
            }
            
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

