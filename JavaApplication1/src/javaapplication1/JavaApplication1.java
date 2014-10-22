/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

/**
 *
 * @author dk1109
 */
import org.json.JSONObject;
//import org.micromanager.api.TaggedImageStorage;

import loci.formats.ome.OMEXMLMetadata;
import loci.formats.services.OMEXMLServiceImpl;
import loci.common.services.ServiceException;
//import loci.common.services.ServiceFactory;
import loci.formats.ImageWriter;
import loci.formats.IFormatWriter;
import loci.common.DataTools;
import java.util.Arrays;
import java.io.File;
import java.io.IOException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import java.lang.Exception;
import loci.formats.FormatException;
import com.quirkware.guid.PlatformIndependentGuidGen;
//import mmcorej.CMMCore;

import loci.formats.CoreMetadata;
import ome.xml.model.enums.DimensionOrder;
import ome.xml.model.enums.PixelType;
import ome.xml.model.primitives.PositiveInteger;
import ome.xml.model.primitives.PositiveFloat;
import ome.xml.model.primitives.NonNegativeInteger;

public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) throws IOException, ServiceException {
        // TODO code application logic here

        int[] delArrayInt = {0, 1000, 2000, 3000, 4000};
        int no_delays = delArrayInt.length;
        String[] delArrayStr = new String[no_delays];
        for (int a = 0; a < no_delays; a++) {
            delArrayStr[a] = String.valueOf(delArrayInt[a]);
        }

        //width = height = 512;
        String path = "C:/Users/dk1109/newtest.OME.tiff";

    ////set up hri
        //mmc.setProperty("KentechHRISingleEdge", "Calibrated", "Yes");
        //mmc.setProperty("KentechHRISingleEdge", "Inhibited", "Running");
        ////mmc.setProperty("KentechHRISingleEdge", "Gain", 750);
        //mmc.setProperty("KentechHRISingleEdge", "Width", 3000);
    //populate metadata
        //N.B. setting up all metadata beforehand precludes things like power monitoring being saved...
        try {
            OMEXMLServiceImpl serv = new OMEXMLServiceImpl();

            OMEXMLMetadata m = serv.createOMEXMLMetadata();

            m.createRoot();
            m.setImageID("Image:0", 0);
            m.setPixelsID("Pixels:0", 0);
            m.setPixelsDimensionOrder(DimensionOrder.XYZCT, 0);
            m.setChannelID("Channel:0:0", 0, 0);
            m.setChannelSamplesPerPixel(new PositiveInteger(1), 0, 0);
            m.setPixelsBinDataBigEndian(Boolean.FALSE, 0, 0);
            m.setPixelsType(PixelType.UINT16, 0);

    //long w = core_.getImageWidth();
            //long h = core_.getImageHeight();
            PositiveInteger w1 = new PositiveInteger(1344);
            PositiveInteger h1 = new PositiveInteger(1024);
            PositiveInteger g1 = new PositiveInteger(no_delays);

            m.setPixelsSizeX((w1), 0);
            m.setPixelsSizeY((h1), 0);
            m.setPixelsSizeZ(new PositiveInteger(1), 0);
            m.setPixelsSizeC(new PositiveInteger(1), 0);
            m.setPixelsSizeT(g1, 0);

            PositiveFloat pitch = new PositiveFloat(6.45);

            m.setPixelsPhysicalSizeX(pitch, 0);
            m.setPixelsPhysicalSizeY(pitch, 0);
            m.setPixelsPhysicalSizeZ(new PositiveFloat(1.0), 0);

            PlatformIndependentGuidGen p = PlatformIndependentGuidGen.getInstance();

            for (int ii = 0; ii < no_delays; ii++) {

                m.setUUIDFileName(delArrayStr[ii], 0, ii);
//       String UUID = p.genNewGuid();
                m.setUUIDValue(p.genNewGuid(), 0, ii);		//how to call DICOM UID in java?
                m.setTiffDataPlaneCount(new NonNegativeInteger(0), 0, ii);
                m.setTiffDataIFD(new NonNegativeInteger(0), 0, ii);
                m.setTiffDataFirstZ(new NonNegativeInteger(0), 0, ii);
                m.setTiffDataFirstC(new NonNegativeInteger(0), 0, ii);
                m.setTiffDataFirstT(new NonNegativeInteger(0), 0, ii);
                m.setPlaneTheC(new NonNegativeInteger(0), 0, ii);
                m.setPlaneTheZ(new NonNegativeInteger(0), 0, ii);
                m.setPlaneTheT(new NonNegativeInteger(ii), 0, ii);
                m.setTiffDataPlaneCount(new NonNegativeInteger(ii), 0, ii);
                //   gui.message("done loop ind " + ii);
                System.out.println("done loop ind" + ii);

            }

            CoreMetadata cm = new CoreMetadata();
            cm.moduloT.labels = delArrayStr;
            cm.moduloT.unit = "ps";
            cm.moduloT.typeDescription = "Gated";
            cm.moduloT.type = loci.formats.FormatTools.LIFETIME;

            serv.addModuloAlong(m, cm, 0);

            new File(path).delete();
            IFormatWriter writer = new ImageWriter().getWriter(path);

            writer.setWriteSequentially(true);
            writer.setMetadataRetrieve(m);
            writer.setCompression("LZW");

//    writer.setId(path);
            for (int i = 0; i < no_delays; ++i) {

//                    delay = 1000 * i;
                //		mmc.setProperty("KentechHRISingleEdge", "Delay", delay);
//                    mmc.sleep(1000);
//                    gui.message("Count = " + i);
                System.out.println("count = " + i);

    //		mmc.snapImage();
                //		img = mmc.getImage();
                //No camera so simulate image
                short[] pixels = new short[1344 * 1024];
                Arrays.fill(pixels, (short) ((5 - i) * 128));

                byte[] img = DataTools.shortsToBytes(pixels, true);

                if (img instanceof byte[]) {
//                            gui.message("Img is in bytes");
                    System.out.println("img is in bytes");
                    writer.setId(path);
                    writer.saveBytes(i, img);
                }
//                    else if (img instanceof short[])
//                    {
//                            byte[] bytes = DataTools.shortToBytes(img, true);
//                            gui.message("Img is short[], converting to bytes");
//                            writer.saveBytes(i, bytes);
//                    }
//                    else
//                    {
//                            gui.message("I don't know what type img is!");
//                    }

            }

            writer.close();
        } //    catch (ServiceException e){
        //        System.out.println("Service exception = " + e) ;
        //    }
        catch (ServiceException e) {
            System.out.println("Service exception = " + e);
        } catch (FormatException e) {
            System.out.println("Format exception = " + e);
        }
    }
}
