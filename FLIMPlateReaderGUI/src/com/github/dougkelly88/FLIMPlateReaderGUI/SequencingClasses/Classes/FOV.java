/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.dougkelly88.FLIMPlateReaderGUI.SequencingClasses.Classes;

import com.github.dougkelly88.FLIMPlateReaderGUI.GeneralClasses.PlateProperties;

/**
 *
 * @author dk1109
 */
public class FOV {
    double x_;
    double y_;
    double z_;
    String well_;
    
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
    public FOV(double x, double y, double z, String well){
        x_ = x;
        y_ = y;
        z_ = z;
        well_ = well;
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
        int j = i;
        while (Character.isDigit(well.charAt(j))) j++;
        
        wellLetter = well.substring(0, i);
        wellNumber = Integer.parseInt(well.substring(i,j));
        for (int k = 0; k < i; k++) {
            letterIndex += (int) well.charAt(k);
        }
        
        well_ = well;
        x_ = pp.getTopLeftWellOffsetH() + 
                (wellNumber - 1) * pp.getWellSpacingH();
        y_ = pp.getTopLeftWellOffsetH() + 
                (wellNumber - 1) * pp.getWellSpacingH();
        z_ = z;
        
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
}

