/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.dougkelly88.FLIMPlateReaderGUI.SequencingClasses.Classes;

import com.github.dougkelly88.FLIMPlateReaderGUI.GeneralClasses.PlateProperties;
import java.awt.Point;
import java.awt.Rectangle;


/**
 *
 * @author dk1109
 */
public class FOV {
    double x_;
    double y_;
    double z_;
    double width_ = 314;
    double height_ = 230;   //40x obj, 0.7 relay
    String well_;
    PlateProperties pp_;
    
    // initialise with nonsense - remove entirely?
//    FOV(){
//        x_ = 0;
//        y_ = 0;
//        z_ = 0;
//        well_ = "Z99";
//    }
    
    /**
     * Initialise FOV with all fields
     * @param x
     * @param y
     * @param z
     * @param well 
     */
    public FOV(double x, double y, double z, String well, PlateProperties pp){
        x_ = x;
        y_ = y;
        z_ = z;
        well_ = well;
        pp_ = pp;
    }
    
    /**
     * Initialise FOV with xyz pos, assign well given plate properties
     * @param x
     * @param y
     * @param z
     * @param pp 
     */
    public FOV(double x, double y, double z, PlateProperties pp){
        x_ = x;
        y_ = y;
        z_ = z;
        pp_ = pp;
        // TODO: assign well automatically based on plate properties
        pp.getTopLeftWellOffsetH();
        pp.getTopLeftWellOffsetV();
        pp.getWellSpacingH();
        pp.getWellSpacingV();
    }
    
    /**
     * Initialise a FOV in the centre of a given well, given plate properties.
     * @param well
     * @param pp
     * @param z 
     */
    public FOV(String well, PlateProperties pp, double z){
        // split well text into numeric data, dealing with possibility of multiple
        // letters in well label - break out to general classes? 
        // Make well an object?
        
        String wellLetter;
        int wellNumber;
        int letterIndex = 0;
        
        int i = 0;
        while (!Character.isDigit(well.charAt(i))) i++;
        
        wellLetter = well.substring(0, i);
        wellNumber = Integer.parseInt(well.substring(i,well.length()));
        for (int k = 0; k < i; k++) {
            letterIndex += (int) well.charAt(k) - 64;
        }
        
        well_ = well;
        x_ = pp.getTopLeftWellOffsetH() + 
                (wellNumber - 1) * pp.getWellSpacingH();
        y_ = pp.getTopLeftWellOffsetV() + 
                (letterIndex - 1) * pp.getWellSpacingV();
        z_ = z;
        pp_ = pp;
        
    }
    
    public FOV getFOV(){
        return this;
    }
    
    public double getX(){
        return x_;
    }
    
    public double getY(){
        return y_;
    }
    
    public double getZ(){
        return z_;
    }
    
    public String getWell(){
        return well_;
    }
    
    public void setX(double x){
        x_ = x;
    }
    
    public void setY(double y){
        y_ = y;
    }
    
    public void setZ(double z){
        z_ = z;
    }
    
    public void setWell(String well){
        well_ = well;
    }
    
    public void setPlateProps(PlateProperties pp){
        pp_ = pp;
    }
    
    public PlateProperties getPlateProps(){
        return pp_;
    }
    
    public double getWidth_() {
        return width_;
    }

    public double getHeight_() {
        return height_;
    }
    
    public void setWidth_(double width_) {
        this.width_ = width_;
    }

    public void setHeight_(double height_) {
        this.height_ = height_;
    }
    
    
    public boolean isValid(){
       
        int wellNumber;
        int letterIndex = 0;
        
        int i = 0;
        while (!Character.isDigit(well_.charAt(i))) i++;
        
//        wellLetter = fov.well_.substring(0, i);
        wellNumber = Integer.parseInt(well_.substring(i,well_.length()));
        for (int k = 0; k < i; k++) {
            letterIndex += (int) well_.charAt(k) - 64;
        }
        
        // Replace rectangle well bounds with circle. Make more general class?
        class Circle {
            //circle defined by centre x,y and radius r. 
            public double x_, y_, r_;
        
            public Circle(double x, double y, double r){
                x_ = x; y_ = y; r_ = r;
            }
            
            public boolean contains(Rectangle fov){
                Point[] points = {new Point(fov.x,fov.y), new Point(fov.x,fov.y+fov.height), 
                    new Point(fov.x+fov.width,fov.y), new Point(fov.x+fov.width,fov.y+fov.height)};
                
                for(Point p : points){
                    if (Math.pow((x_ - p.x),2) + Math.pow((y_ - p.y),2) > 
                            Math.pow(r_,2)) {
                        return false;
                        }
                }
                return true;
            }
        }
              
        Rectangle fov = new Rectangle(
                (int) (this.x_ - this.width_/2), (int) (this.y_- this.height_/2), 
                (int) this.width_, (int) this.height_);
        if (pp_.getWellShape() == "Circle"){
            Circle well = new Circle(pp_.getTopLeftWellOffsetH() + (wellNumber - 1) * pp_.getWellSpacingH(), 
                pp_.getTopLeftWellOffsetV() + (letterIndex - 1) * pp_.getWellSpacingV(), 
                pp_.getWellSize()/2);
            return well.contains(fov);
        }
        else {
            Rectangle well = new Rectangle( (int) (pp_.getTopLeftWellOffsetH() + (wellNumber - 1) * pp_.getWellSpacingH()), 
                (int) (pp_.getTopLeftWellOffsetV() + (letterIndex - 1) * pp_.getWellSpacingV()), 
                (int) pp_.getWellSize(), (int) pp_.getWellSize());
            return well.contains(fov);
        }
        
        
        
    }
}

