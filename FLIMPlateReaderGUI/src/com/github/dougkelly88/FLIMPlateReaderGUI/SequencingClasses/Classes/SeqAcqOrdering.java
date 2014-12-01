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
//public class seqAcqOrdering implements Comparable<seqAcqOrdering> {
public class SeqAcqOrdering {

    int position_;
    String description_;
    
    /**
     * Initialise FOV with all fields
     *
     * @param pos
     * @param desc
     * 
     */
    public SeqAcqOrdering(int pos, String desc) {
        position_ = pos;
        description_ = desc;
        
    }

    public SeqAcqOrdering getSeqAcqOrdering() {
        return this;
    }

    public int getPosition() {
        return this.position_;
    }

    public String getDescription() {
        return this.description_;
    }

    public void setPosition(int pos) {
        this.position_ = pos;
    }

    public void setDescription(String desc) {
        this.description_ = desc;
    }

//    // Override compareTo so that FOV.sort orders by well value
//    @Override
//    public int compareTo(seqAcqOrdering fov) {
//        final int GREATER = 1;
//        final int LESS = -1;
//        final int EQUAL = 0;
//
//        if (this == fov) {
//            return EQUAL;
//        }
//
//        String[] well = new String[2];
//        String[] wellLetter = new String[2];
//        int[] wellNumber = new int[2];
//        int[] letterIndex = new int[2];
//        well[0] = this.getWell();
//        well[1] = fov.getWell();
//
//        for (int ind = 0; ind < 2; ind++) {
//
//            int i = 0;
//            while (!Character.isDigit(well[ind].charAt(i))) {
//                i++;
//            }
//
//            wellLetter[ind] = well[ind].substring(0, i);
//            wellNumber[ind] = Integer.parseInt(well[ind].substring(i, well[ind].length()));
//            for (int k = 0; k < i; k++) {
//                letterIndex[ind] += (int) well[ind].charAt(k) - 64;
//            }
//        }
//
//        if  (letterIndex[0] > letterIndex[1])
//            return GREATER;
//        if (letterIndex[0] < letterIndex[1])
//            return LESS;
//        if (letterIndex[0] == letterIndex[1]){
//            if (wellNumber[0] > wellNumber[1])
//                return GREATER;
//            if (wellNumber[0] < wellNumber[1])
//                    return LESS;
//        }
//
//        return EQUAL;
//    }
//    
//    // Override equals so that FOV.contains checks LOGICAL equality, not 
//    // reference equality; and ONLY for xy equality
//    // http://users.csc.calpoly.edu/~kmammen/documents/java/howToOverrideEquals.html
//    @Override
//    public boolean equals(Object other){
//        if (other == null)
//            return false;
//        
//        if (this.getClass() != other.getClass())
//            return false;
//       
//        double thisX = this.getX();
//        double thisY = this.getY();
//        double otherX = ((seqAcqOrdering) other).getX();
//        double otherY = ((seqAcqOrdering) other).getY();
//        
//        if (thisX == otherX & thisY == otherY)
//            return true;
//        
//        return false;
//    }
//
//    @Override
//    public int hashCode() {
//        int hash = 5;
//        hash = 79 * hash + (int) (Double.doubleToLongBits(this.x_) ^ (Double.doubleToLongBits(this.x_) >>> 32));
//        hash = 79 * hash + (int) (Double.doubleToLongBits(this.y_) ^ (Double.doubleToLongBits(this.y_) >>> 32));
//        return hash;
//    }

}
