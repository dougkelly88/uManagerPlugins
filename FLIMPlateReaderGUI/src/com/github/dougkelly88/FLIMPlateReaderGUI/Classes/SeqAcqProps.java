/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.dougkelly88.FLIMPlateReaderGUI.Classes;

import java.awt.List;
import java.util.ArrayList;

/**
 *
 * @author dk1109
 */

// http://www.javapractices.com/topic/TopicAction.do?Id=46

// alternative method? http://stackoverflow.com/questions/15406717/can-i-use-the-builder-pattern-on-a-java-enum

// also useful: http://www.algosome.com/articles/builder-setter-methods-java.html



public final class SeqAcqProps {
    private boolean useScanFLIM;
    private boolean powerMonitoring;
    private ArrayList<ArrayList<Integer>> delaysArray;

  public static SeqAcqProps getInstance() {
     return fINSTANCE;
  }

  // PRIVATE

  /**
  * Single instance created upon class loading.
  */
  private static final SeqAcqProps fINSTANCE =  new SeqAcqProps();

  /**
  * Private constructor prevents construction outside this class.
  */
  private SeqAcqProps() {
    useScanFLIM = false;
    powerMonitoring = false;
//    delaysArray = new ArrayList<Integer>().add(4);
  }
  
  public SeqAcqProps setUseScanFLIM(boolean usf){
      useScanFLIM = usf;
      return this;
  }
  
  public boolean getUseScanFLIM(){
      return useScanFLIM;
  }
} 
