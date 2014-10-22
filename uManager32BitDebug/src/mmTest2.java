
import mmcorej.CMMCore;
import org.micromanager.MMStudioMainFrame;
import org.micromanager.acquisition.AcquisitionEngine;
import org.micromanager.api.ScriptInterface;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Frederik
 */
public class mmTest2 implements org.micromanager.api.MMPlugin{
   public static String menuName = "Fred nonsense";
   public static String tooltipDescription = "A nonsensical test plugin";
   private CMMCore core_;
   private MMStudioMainFrame gui_;
   private AcquisitionEngine acq_;
   
   
    @Override
    public void dispose() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setApp(ScriptInterface app) {
      gui_ = (MMStudioMainFrame) app;
      core_ = app.getMMCore();
      acq_ = gui_.getAcquisitionEngine(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void show() {
        gui_.showMessage("This is some nonsense, but it is right!"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDescription() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getInfo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getVersion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCopyright() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
