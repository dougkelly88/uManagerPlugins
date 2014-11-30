/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.dougkelly88.FLIMPlateReaderGUI.GeneralClasses;

import java.util.ArrayList;

import org.json.JSONObject;

import java.util.Arrays;

import mmcorej.CMMCore;
import org.micromanager.MMStudio;

import loci.formats.ome.OMEXMLMetadata;
import loci.formats.services.OMEXMLServiceImpl;
import loci.formats.services.*;
import loci.common.services.ServiceException;
import loci.common.services.ServiceFactory;
import loci.formats.ImageWriter;
//import loci.formats.CoreMetadata;
import loci.common.DataTools;

import loci.formats.CoreMetadata;

import ome.xml.model.enums.DimensionOrder;
import ome.xml.model.enums.PixelType;
import ome.xml.model.primitives.PositiveInteger;
import ome.xml.model.primitives.PositiveFloat;
import ome.xml.model.primitives.NonNegativeInteger;

import java.io.File;
import java.io.IOException;

import com.quirkware.guid.PlatformIndependentGuidGen;
import java.util.logging.Level;
import java.util.logging.Logger;
import loci.common.Location;
import loci.formats.FormatException;
import loci.formats.IFormatWriter;
import loci.formats.out.OMETiffWriter;

import mmcorej.TaggedImage;

/**
 *
 * @author dk1109
 */
public class Acquisition {

    MMStudio gui_;
    CMMCore core_;
    CoreMetadata cm;

    public Acquisition() {
        gui_ = MMStudio.getInstance();
        core_ = gui_.getCore();
    }

    public void snapFLIMImage(String path, ArrayList<Integer> delays) {

        try {
            OMEXMLMetadata m = setBasicMetadata(delays);
            IFormatWriter writer = generateWriter(path, m);

            for (Integer delay : delays) {
                core_.setProperty("Delay box", "Delay (ps)", delay);
                core_.snapImage();
                saveLayersToOMETiff(writer, delays.indexOf(delay));
            }
            // clean up writer when finished...
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void saveLayersToOMETiff(IFormatWriter writer, int layer)
            throws Exception {
        Object img = core_.getImage();

        if (img instanceof byte[]) {
            System.out.println("Img is in bytes");
            writer.saveBytes(layer, (byte[]) img);
        } else if (img instanceof short[]) {
            byte[] bytes = DataTools.shortsToBytes((short[]) img, true);
            System.out.println("Img is short[], converting to bytes, i = " + layer);
            writer.saveBytes(layer, bytes);
        } else {
            System.out.println("I don't know what type img is!");
        }
    }

    ;

    private IFormatWriter generateWriter(String path, OMEXMLMetadata m)
            throws FormatException, IOException {
        IFormatWriter writer = new ImageWriter().getWriter(path);
        writer.setWriteSequentially(true);
        writer.setMetadataRetrieve(m);
        writer.setCompression("LZW");

        writer.setId(path);

        return writer;
    }

    public void dummyTest() {

        int[] delArrayInt = {0, 1000, 2000, 3000, 4000};
        int no_delays = delArrayInt.length;
        String[] delArrayStr = new String[no_delays];
        for (int a = 0; a < no_delays; a++) {
            delArrayStr[a] = String.valueOf(delArrayInt[a]);
        }

        //width = height = 512;
        String path = "C:/Users/dk1109/newtest5.OME.tiff";

        //set up hri
        // mmc.setProperty("KentechHRISingleEdge", "Calibrated", "Yes");
        // mmc.setProperty("KentechHRISingleEdge", "Inhibit", "Running");
        // mmc.setProperty("KentechHRISingleEdge", "Gain", 450);
        // mmc.setProperty("KentechHRISingleEdge", "Width", 3000);
        //populate metadata
        //N.B. setting up all metadata beforehand precludes things like power monitoring being saved...
        try {
            OMEXMLServiceImpl serv = new OMEXMLServiceImpl();

            OMEXMLMetadata m = serv.createOMEXMLMetadata();

            core_.clearCircularBuffer();
            core_.startSequenceAcquisition(no_delays, 1, true);

            int series = 0;
            String description = "nonesense";

            m.createRoot();
            m.setImageDescription(description, series);
            m.setImageID("Image:0", series);
            m.setPixelsID("Pixels:0", series);
            m.setPixelsDimensionOrder(DimensionOrder.XYZCT, series);
            m.setChannelID("Channel:0:0", 0, 0);
            m.setChannelSamplesPerPixel(new PositiveInteger(1), 0, 0);
//            m.setPixelsBinDataBigEndian(Boolean.FALSE, 0, 0);
//            m.setPixelsBigEndian(Boolean.TRUE, 0);
//            m.setPixelsBinDataBigEndian(Boolean.FALSE, 0, 0);
            m.setPixelsType(PixelType.UINT8, series);

            PositiveInteger w1 = new PositiveInteger((int) core_.getImageWidth());
            PositiveInteger h1 = new PositiveInteger((int) core_.getImageHeight());
            PositiveInteger g1 = new PositiveInteger(no_delays);

            m.setPixelsSizeX((w1), series);
            m.setPixelsSizeY((h1), series);
            m.setPixelsSizeZ(new PositiveInteger(1), series);
            m.setPixelsSizeC(new PositiveInteger(1), series);
            m.setPixelsSizeT(g1, series);

            String binningStr = core_.getProperty(core_.getCameraDevice(), "Binning");
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

            for (int ii = 0; ii < no_delays; ii++) {    //TEMP!!!

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

                gui_.message("done loop ind " + ii);
//                System.out.println("done loop ind" + ii);

            }

            CoreMetadata cm = new CoreMetadata();

            cm.moduloT.labels = delArrayStr;
            cm.moduloT.unit = "ps";
            cm.moduloT.typeDescription = "Gated";
            cm.moduloT.type = loci.formats.FormatTools.LIFETIME;
//            
//
//            serv.addModuloAlong(m, cm, series);

//            path = "nonsense.OME.tiff";
//            new File(path).delete();
//            Location.mapId(path,File.createTempFile("test",".ome.tiff").getAbsolutePath());
//            IFormatWriter writer = new ImageWriter().getWriter(path);
            IFormatWriter writer = new OMETiffWriter();
//            IFormatWriter writer = new ImageWriter();
            writer.setId(path);
            writer.setWriteSequentially(true);
            writer.setMetadataRetrieve(m);
            writer.setCompression("LZW");

            int delay = 0;
            for (int i = 0; i < no_delays; ++i) {

                delay = 1000 * i;
                core_.setExposure(600 - i * 100);
                core_.setProperty("KentechHDG800", "Delay (ps)", delay);
                core_.sleep(1000);
                gui_.message("Count = " + i);
//                System.out.println("count = " + i);

                core_.snapImage();
//                    gui_.snapSingleImage();
                byte[] img = (byte[]) core_.getImage();
//                        gui_.displayImage(img);
//                core_.popNextImage();
//                        gui_.addToAlbum((TaggedImage) img);
//                        TaggedImage img2;
//                        img2 = new TaggedImage(img);
//                        img

//                //No camera so simulate image
//                short[] pixels = new short[1344 * 1024];
//                Arrays.fill(pixels, (short) ((5 - i) * 128));
//
//                byte[] img = DataTools.shortsToBytes(pixels, true);
                if (img instanceof byte[]) {
                    gui_.message("Img is in bytes");
//                    System.out.println("img is in bytes");
//                    writer.setId(path);
                    writer.saveBytes(i, img);
                }
//                    else if (img instanceof short[])
//                    {
//                            byte[] bytes = DataTools.shortsToBytes(img, true);
//                            gui.message("Img is short[], converting to bytes, i = " + i);
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
            System.out.println("Service exception = " + e.getMessage());
//        } catch (FormatException e) {
//            System.out.println("Format exception = " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOExeption = " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Some other exception = " + e.getMessage());
        }

    }

    private PositiveFloat checkPixelPitch() {

        PositiveFloat pitch = new PositiveFloat(1.0);

        try {
            String binningStr = core_.getProperty(core_.getCameraDevice(), "Binning");
            float binning = 1;
            if (binningStr.equals("1")) {
                binning = 1;
            } else if (binningStr.equals("2")) {
                binning = 2;
            } else if (binningStr.equals("4")) {
                binning = 4;
            } else if (binningStr.equals("8")) {
                binning = 8;
            }

            pitch = new PositiveFloat(binning * 6.45);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return pitch;
    }

    private OMEXMLMetadata setBasicMetadata(ArrayList<Integer> delays)
            throws ServiceException {

        OMEXMLServiceImpl serv = new OMEXMLServiceImpl();
        OMEXMLMetadata m = serv.createOMEXMLMetadata();

        int no_delays = delays.size();
        // delays must be parsed as strings for metadata
        String[] delArrayStr = new String[no_delays];
        for (int ind = 0; ind < no_delays; ind++) {
            delArrayStr[ind] = String.valueOf(delays.get(ind));
        }

        try {
            m.createRoot();
            m.setImageID("Image:0", 0);
            m.setPixelsID("Pixels:0", 0);
            m.setPixelsDimensionOrder(DimensionOrder.XYZCT, 0);
            m.setChannelID("Channel:0:0", 0, 0);
            m.setChannelSamplesPerPixel(new PositiveInteger(1), 0, 0);
            m.setPixelsBinDataBigEndian(Boolean.FALSE, 0, 0);
            m.setPixelsType(PixelType.UINT8, 0);

            long bpp = core_.getBytesPerPixel();

            if (bpp == 1) {
                m.setPixelsType(PixelType.UINT8, 0);
            }
            if (bpp == 2) {
                m.setPixelsType(PixelType.UINT16, 0);
            }

            PositiveInteger w1 = new PositiveInteger((int) core_.getImageWidth());
            PositiveInteger h1 = new PositiveInteger((int) core_.getImageHeight());
            PositiveInteger g1 = new PositiveInteger(no_delays);

            m.setPixelsSizeX((w1), 0);
            m.setPixelsSizeY((h1), 0);
            m.setPixelsSizeZ(new PositiveInteger(1), 0);
            m.setPixelsSizeC(new PositiveInteger(1), 0);
            m.setPixelsSizeT(g1, 0);

            PositiveFloat pitch = checkPixelPitch();

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
                System.out.println("done loop ind " + ii);
            }

            CoreMetadata cm = new CoreMetadata();

            cm.moduloT.labels = delArrayStr;
            cm.moduloT.unit = "ps";
            cm.moduloT.typeDescription = "Gated";
            cm.moduloT.type = loci.formats.FormatTools.LIFETIME;
            serv.addModuloAlong(m, cm, 0);
            System.out.println("did addModulo");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return m;
    }

    public void doacqModulo() {
        int[] delArrayInt = {0, 7000, 7500, 9600, 9900};
        int no_delays = delArrayInt.length;
        String[] delArrayStr = new String[no_delays];
        for (int a = 0; a < no_delays; a++) {
            delArrayStr[a] = String.valueOf(delArrayInt[a]);
        }

        String path = "C:/Users/dk1109/newtest_FROMHCAFLIM.OME.tiff";

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
            m.setPixelsType(PixelType.UINT8, 0);

            PositiveInteger w1 = new PositiveInteger((int) core_.getImageWidth());
            PositiveInteger h1 = new PositiveInteger((int) core_.getImageHeight());
            PositiveInteger g1 = new PositiveInteger(no_delays);

            m.setPixelsSizeX((w1), 0);
            m.setPixelsSizeY((h1), 0);
            m.setPixelsSizeZ(new PositiveInteger(1), 0);
            m.setPixelsSizeC(new PositiveInteger(1), 0);
            m.setPixelsSizeT(g1, 0);

            PositiveFloat pitch = checkPixelPitch();

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
                System.out.println("done loop ind " + ii);
            }

            CoreMetadata cm = new CoreMetadata();

            cm.moduloT.labels = delArrayStr;
            cm.moduloT.unit = "ps";
            cm.moduloT.typeDescription = "Gated";
            cm.moduloT.type = loci.formats.FormatTools.LIFETIME;
            serv.addModuloAlong(m, cm, 0);
            System.out.println("did addModulo");
            new File(path).delete();
            System.out.println("did newfile");
//            IFormatWriter writer = new ImageWriter().getWriter(path);
            IFormatWriter writer = new ImageWriter().getWriter(path);

            writer.setWriteSequentially(true);
            writer.setMetadataRetrieve(m);
            writer.setCompression("LZW");

            writer.setId(path);
            for (int i = 0; i < no_delays; ++i) {

                int delay = 1000 * i;
                core_.setProperty("KentechHDG800", "Delay (ps)", delay);
                core_.sleep(1000);
                System.out.println("Count = " + i);
                core_.setExposure(200 * (i + 1));
                System.out.println("exposure is " + core_.getExposure());
                core_.snapImage();
                Object img = core_.getImage();

                if (img instanceof byte[]) {
                    System.out.println("Img is in bytes");
                    writer.saveBytes(i, (byte[]) img);
                } else if (img instanceof short[]) {
                    byte[] bytes = DataTools.shortsToBytes((short[]) img, true);
                    System.out.println("Img is short[], converting to bytes, i = " + i);
                    writer.saveBytes(i, bytes);
                } else {
                    System.out.println("I don't know what type img is!");
                }

            }

            writer.close();

        } catch (ServiceException e) {
            System.out.println("svc exception = " + e.getMessage());

        } catch (FormatException e) {
            System.out.println("fmt exception = " + e.getMessage());
        } catch (Exception e) {
            System.out.println("other exception = " + e.getMessage());

        }

    }
}
