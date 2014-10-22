/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.dougkelly88.uManagerPlugins.Dummy;


import org.micromanager.api.ScriptInterface;
import mmcorej.CMMCore;
import org.micromanager.MMStudioMainFrame;
import org.micromanager.acquisition.AcquisitionEngine;
import org.micromanager.api.ScriptInterface;
import org.micromanager.utils.ReportingUtils;
import loci.formats.MetadataTools;

/**
 *
 * @author dk1109
 */
public class Dummy implements org.micromanager.api.MMPlugin{
   public static String menuName = "Nonsense";
   public static String tooltipDescription = "A nonsensical test plugin";
   private CMMCore core_;
   private MMStudioMainFrame gui_;
   private AcquisitionEngine acq_;
   public DummyControls controlFrame_;
            
   @Override
   public void dispose() {
      /*
       * you can put things that need to be run on shutdown here
       * note: if you launch a JDialog from the plugin using show(), shutdown of the dialog will not automatically call dispose()
       * You will need to add a call to dispose() from the formWindowClosing() method of your JDialog.
       */
   }

   @Override
   public void setApp(ScriptInterface app) {
      gui_ = (MMStudioMainFrame) app;
      core_ = app.getMMCore();
      acq_ = gui_.getAcquisitionEngine();
   }

   @Override
   public void show() {//this is run when the menu listing for the plugin is clicked
      gui_.showMessage("This is some nonsense!"); 
      
   }



   @Override
   public String getDescription() {
      return "Description: Hello world";
   }

   @Override
   public String getInfo() {
      return "Info: Hello world";
   }

   @Override
   public String getVersion() {
      return "1.0";
   }

   @Override
   public String getCopyright() {
      return "(C) 2012 S Holden, EPFL, Switzerland. This software is released under the BSD license";
   }
   

}