/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.dougkelly88.uManagerPlugins.SingleEdgeDemo;

import com.quirkware.guid.PlatformIndependentGuidGen;
import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import loci.common.DataTools;
import loci.common.services.ServiceException;
import loci.formats.CoreMetadata;
import loci.formats.FormatException;
import loci.formats.IFormatWriter;
import loci.formats.ImageWriter;
import loci.formats.ome.OMEXMLMetadata;
import loci.formats.services.OMEXMLServiceImpl;
import loci.formats.services.OMEXMLService;
import mmcorej.CMMCore;
import ome.xml.model.enums.DimensionOrder;
import ome.xml.model.enums.PixelType;
import ome.xml.model.primitives.NonNegativeInteger;
import ome.xml.model.primitives.PositiveFloat;
import ome.xml.model.primitives.PositiveInteger;
import org.json.JSONObject;
import org.micromanager.MMStudioMainFrame;
import org.micromanager.acquisition.AcquisitionEngine;
import org.micromanager.api.ScriptInterface;
import org.micromanager.utils.ReportingUtils;
import loci.common.services.ServiceFactory;

/**
 *
 * @author dk1109
 */
public class KentechHRISingleEdgeDemo implements org.micromanager.api.MMPlugin {

    public static String menuName = "Time Gated FLIM";
    public static String tooltipDescription = "Simple inteface for implementing time gated FLIM";
    private CMMCore mmc;
    private MMStudioMainFrame gui_;
    private AcquisitionEngine acq_;
    public DemoKentechControls dialog_;

    @Override
    public void dispose() {
        if (dialog_  != null){
            dialog_.setVisible(false);
            dialog_.dispose();
            dialog_ = null;
    }
    }

    @Override
    public void setApp(ScriptInterface app) {
        gui_ = (MMStudioMainFrame) app;
        mmc = app.getMMCore();
        acq_ = gui_.getAcquisitionEngine();

        if (dialog_ == null) {
            dialog_ = new DemoKentechControls(this);
            dialog_.setVisible(true);
        } else {
            dialog_.toFront();
        }

    }

    @Override
    public void show() {
//        gui_.showMessage("Testing...");
        //Check that components (camera, HRI, delay box) are configured. 
    }

    @Override
    public String getDescription() {
        return "Description: Time Gated FLIM";
    }

    @Override
    public String getInfo() {
        return "Info: Time Gated FLIM";
    }

    @Override
    public String getVersion() {
        return "0.1";
    }

    @Override
    public String getCopyright() {
        return "Doug Kelly 2014";
    }

    int testPlugin() {

//        gui_.showMessage("Testing...");
//        List<Integer> delays = dialog_.getDelays();
        List<Integer> delays = new ArrayList<Integer>();
        delays.add(100);
        delays.add(1000);
        delays.add(2500);
        
        try {
//            int[] delArrayInt = {0, 1000, 2000, 3000, 4000};
//            int no_delays = delArrayInt.length;
            int no_delays = delays.size();
            String[] delArrayStr = new String[no_delays];
            //N.B. here we convert dels back to str, having converted to integers
            //in controlFrame. May be best this way in long run. 
//            List<String> delArrayStr = new ArrayList<String>();
            for (int a = 0; a < no_delays; a++) {
//                delArrayStr[a] = String.valueOf(delArrayInt[a]);
                delArrayStr[a] = String.valueOf(delays.get(a));
//                delArrayStr.add(String.valueOf(delays.get(a)));
            }

            //width = height = 512;
            String path = "C:/Users/dk1109/test.OME.tiff";

            //set up hri
            mmc.setProperty("KentechHRISingleEdge", "Calibrated", "Yes");
            mmc.setProperty("KentechHRISingleEdge", "Inhibit", "Running");
            mmc.setProperty("KentechHRISingleEdge", "Gain", 450);
            mmc.setProperty("KentechHRISingleEdge", "Width", 3000);
            //populate metadata
            //N.B. setting up all metadata beforehand precludes things like power monitoring being saved...

//            OMEXMLServiceImpl serv = new OMEXMLServiceImpl();
            ServiceFactory factory = new ServiceFactory();
            OMEXMLService serv = factory.getInstance(OMEXMLService.class);
            OMEXMLMetadata m = serv.createOMEXMLMetadata();
            

//            java.lang.System.setProperty("javax.xml.transform.TransformerFactory", "com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl");

            m.createRoot();
            m.setImageID("Image:0", 0);
            m.setPixelsID("Pixels:0", 0);
            m.setPixelsDimensionOrder(DimensionOrder.XYZCT, 0);
            m.setChannelID("Channel:0:0", 0, 0);
            m.setChannelSamplesPerPixel(new PositiveInteger(1), 0, 0);
            m.setPixelsBinDataBigEndian(Boolean.FALSE, 0, 0);
            m.setPixelsType(PixelType.UINT16, 0);
//            m.setPixelsAnnotationRef("Annotation:0", 0, 0);

            PositiveInteger w1 = new PositiveInteger((int) mmc.getImageWidth());
            PositiveInteger h1 = new PositiveInteger((int) mmc.getImageHeight());
            PositiveInteger g1 = new PositiveInteger(no_delays);

            m.setPixelsSizeX((w1), 0);
            m.setPixelsSizeY((h1), 0);
            m.setPixelsSizeZ(new PositiveInteger(1), 0);
            m.setPixelsSizeC(new PositiveInteger(1), 0);
            m.setPixelsSizeT(g1, 0);
            

            String binningStr = mmc.getProperty(mmc.getCameraDevice(), "Binning");
            float binning = 1;
            if (binningStr.equals("1x1")) {
                binning = 1;
            } else if (binningStr.equals("2x2")) {
                binning = 2;
            } else if (binningStr.equals("4x4")) {
                binning = 4;
            } else if (binningStr.equals("8x8")) {
                binning = 8;
            }

            PositiveFloat pitch = new PositiveFloat(binning * 6.45);

            m.setPixelsPhysicalSizeX(pitch, 0);
            m.setPixelsPhysicalSizeY(pitch, 0);
            m.setPixelsPhysicalSizeZ(new PositiveFloat(1.0), 0);

            PlatformIndependentGuidGen p = PlatformIndependentGuidGen.getInstance();

            for (int ii = 0; ii < no_delays; ii++) {

                m.setUUIDFileName(delArrayStr[ii], 0, ii);
                m.setUUIDValue(p.genNewGuid(), 0, ii);
                m.setTiffDataPlaneCount(new NonNegativeInteger(0), 0, ii);
                m.setTiffDataIFD(new NonNegativeInteger(0), 0, ii);
                m.setTiffDataFirstZ(new NonNegativeInteger(0), 0, ii);
                m.setTiffDataFirstC(new NonNegativeInteger(0), 0, ii);
                m.setTiffDataFirstT(new NonNegativeInteger(0), 0, ii);
                m.setPlaneTheC(new NonNegativeInteger(0), 0, ii);
                m.setPlaneTheZ(new NonNegativeInteger(0), 0, ii);
                m.setPlaneTheT(new NonNegativeInteger(ii), 0, ii);
                m.setTiffDataPlaneCount(new NonNegativeInteger(ii), 0, ii);
//                m.setPlaneAnnotationRef(("Annotation:"+ii), 0, ii, 0);
                System.out.println("done loop ind " + ii);

            }
            
//            m.setXMLAnnotationAnnotationRef("Annotation:0", 0, 0);
            

            CoreMetadata cm = new CoreMetadata();
            cm.moduloT.type = loci.formats.FormatTools.LIFETIME;
            cm.moduloT.unit = "ps";
            cm.moduloT.typeDescription = "Gated";
//            cm.moduloT.labels = delArrayStr;
            
            cm.moduloT.labels = new String[no_delays];
            
            for (int i = 0; i<no_delays; i++)
            {
                cm.moduloT.labels[i] = delArrayStr[i];
            }
            
            
            serv.addModuloAlong(m, cm, 0);
            
            m.setXMLAnnotationDescription("Description string", 0);
            m.setXMLAnnotationAnnotationRef("Annotation:3", 0, 0);
//            m.setXMLAnnotationValue("3", 0);

            new File(path).delete();
            IFormatWriter writer = new ImageWriter().getWriter(path);

            writer.setWriteSequentially(true);
            writer.setMetadataRetrieve(m);
            writer.setCompression("LZW");

            writer.setId(path);
            for (int i = 0; i < no_delays; ++i) {

//                int delay = 1000 * i;
                mmc.setProperty("KentechHRISingleEdge", "Delay", delays.get(i));
                mmc.sleep(1000);
                System.out.println("Delay = " + delays.get(i));
                mmc.snapImage();
                short[] img = (short[]) mmc.getImage();

                if (img instanceof short[]) {
                    byte[] bytes = DataTools.shortsToBytes(img, true);
                    System.out.println("Img is short[], converting to bytes, i = " + i);
                    writer.saveBytes(i, bytes);
                } //                    else if (img instanceof byte[]) {
                //                            System.out.println("Img is in bytes");
                //                  		  writer.setId(path);
                //                   		  writer.saveBytes(i, img);
                //                }
                else {
                    System.out.println("I don't know what type img is!");
                }

            }
            writer.close();

        } catch (ServiceException e) {
            System.out.println("Service exception = " + e);
        } catch (FormatException e) {
            System.out.println("Format exception = " + e);
        } catch (Exception e) {
            System.out.println("General exception = " + e);
        }

        return 0;
    }

}
