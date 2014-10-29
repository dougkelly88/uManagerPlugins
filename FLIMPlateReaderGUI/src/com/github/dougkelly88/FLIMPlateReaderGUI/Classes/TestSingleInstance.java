/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.dougkelly88.FLIMPlateReaderGUI.Classes;

/**
 *
 * @author dk1109
 */

// http://www.javapractices.com/topic/TopicAction.do?Id=46

// alternative method? http://stackoverflow.com/questions/15406717/can-i-use-the-builder-pattern-on-a-java-enum

// also useful: http://www.algosome.com/articles/builder-setter-methods-java.html



public final class TestSingleInstance {
    private boolean useScanFLIM;
    private boolean powerMonitoring;

  public static TestSingleInstance getInstance() {
     return fINSTANCE;
  }

  // PRIVATE

  /**
  * Single instance created upon class loading.
  */
  private static final TestSingleInstance fINSTANCE =  new TestSingleInstance();

  /**
  * Private constructor prevents construction outside this class.
  */
  private TestSingleInstance() {
    //..elided
  }
  
  public TestSingleInstance setUseScanFLIM(boolean usf){
      useScanFLIM = usf;
      return this;
  }
  
  public boolean getUseScanFLIM(){
      return useScanFLIM;
  }
} 
