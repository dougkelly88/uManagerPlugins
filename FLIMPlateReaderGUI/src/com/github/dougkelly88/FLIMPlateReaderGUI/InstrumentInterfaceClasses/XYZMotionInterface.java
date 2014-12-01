/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.dougkelly88.FLIMPlateReaderGUI.InstrumentInterfaceClasses;

import com.github.dougkelly88.FLIMPlateReaderGUI.GeneralClasses.PlateProperties;
import com.github.dougkelly88.FLIMPlateReaderGUI.SequencingClasses.Classes.FOV;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import mmcorej.CMMCore;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.LUDecomposition;

/**
 *
 * @author dk1109
 */
public final class XYZMotionInterface {

    PlateProperties pp_;
    CMMCore core_;
    String xystage_;
    String zstage_;
    Point2D.Double[] stageWellCentres_ = new Point2D.Double[3];
    Point2D.Double[] xpltWellCentres_ = new Point2D.Double[3];
    AffineTransform transform_;

    //TODO: implement safety checks for objective fouling. 
    //TODO: deal with objective focal shifts
    //TODO: deal (limited) with camera coregistration with stage move?
    //TODO: set first/last well centres in calibration method
    public XYZMotionInterface(PlateProperties pp, CMMCore core) {
        pp_ = pp;
        core_ = core;
        xystage_ = core.getXYStageDevice();
        zstage_ = core.getFocusDevice();
        stageWellCentres_[0] = new Point2D.Double(106100, 6700); //TL
        stageWellCentres_[1] = new Point2D.Double(7100, 69700);  //BR
        stageWellCentres_[2] = new Point2D.Double(7100, 6700); //TR
        xpltWellCentres_[0] = new Point2D.Double(
                pp_.getTopLeftWellOffsetH(),
                pp_.getTopLeftWellOffsetV());
        xpltWellCentres_[1] = new Point2D.Double(
                pp_.getTopLeftWellOffsetH() + (pp_.getPlateColumns() - 1) * pp_.getWellSpacingH(),
                pp_.getTopLeftWellOffsetV() + (pp_.getPlateRows() - 1) * pp_.getWellSpacingV());
        xpltWellCentres_[2] = new Point2D.Double(
                pp_.getTopLeftWellOffsetH() + (pp_.getPlateColumns() - 1) * pp_.getWellSpacingH(),
                pp_.getTopLeftWellOffsetV());
        transform_ = deriveAffineTransform(xpltWellCentres_, stageWellCentres_);
    }

    public int gotoFOV(FOV fov) {

        try {
            Point2D.Double stage = fovXYtoStageXY(fov);
            core_.setXYPosition(xystage_, stage.getX(), stage.getY());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return 1;
    }

    public FOV getCurrentFOV() {

        try {
            Point2D.Double xy = stageXYtoFOVXY(core_.getXYStagePosition(xystage_));
            Double z = getZAbsolute();
            return new FOV(xy.getX(), xy.getY(), z, pp_);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return new FOV("A1", pp_, 0);   // replace with something that more clearly
        // indicated error?
    }

    public Point2D.Double fovXYtoStageXY(FOV fov) {

        Point2D.Double xy = new Point2D.Double(fov.getX(), fov.getY());
        Point2D.Double xyout = new Point2D.Double();

        transform_.transform(xy, xyout);

        return xyout;
    }

    public Point2D.Double stageXYtoFOVXY(Point2D.Double stagexy) {

        Point2D.Double xyout = new Point2D.Double();
        try {
            transform_.inverseTransform(stagexy, xyout);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return xyout;
    }

    public void setStageWellCentres(Point2D.Double p1, Point2D.Double p2, Point2D.Double p3) {
        stageWellCentres_[0] = p1;
        stageWellCentres_[1] = p2;
        stageWellCentres_[2] = p3;
    }

    public void setXPLTWellCentres(Point2D.Double p1, Point2D.Double p2, Point2D.Double p3) {
        xpltWellCentres_[0] = p1;
        xpltWellCentres_[1] = p2;
        xpltWellCentres_[2] = p3;
    }

    public static AffineTransform deriveAffineTransform(
            Point2D.Double[] xplt, Point2D.Double[] stage) {
            // GENERAL CASE:    
        // if S is stage space and P is XPLT plate space, 
        // and T is the matrix to transform between the two:
        // S = TP
        // SP^-1 = TPP^-1
        // T = SP^-1;
        // where S is a 2x3 matrix with 3 points, T is a 2x3 matrix and P is a 
        // 3x3 matrix with 3 points and the bottom row occupied by ones...

        double[][] Parr = {{xplt[0].getX(), xplt[1].getX(), xplt[2].getX()},
        {xplt[0].getY(), xplt[1].getY(), xplt[2].getY()}, {1, 1, 1}};
        RealMatrix P = MatrixUtils.createRealMatrix(Parr);

        double[][] Sarr = {{stage[0].getX(), stage[1].getX(), stage[2].getX()},
        {stage[0].getY(), stage[1].getY(), stage[2].getY()}};
        RealMatrix S = MatrixUtils.createRealMatrix(Sarr);

        RealMatrix Pinv = (new LUDecomposition(P)).getSolver().getInverse();
        RealMatrix transformationMatrix = S.multiply(Pinv);

        double m00 = transformationMatrix.getEntry(0, 0);
        double m01 = transformationMatrix.getEntry(0, 1);
        double m02 = transformationMatrix.getEntry(0, 2);
        double m10 = transformationMatrix.getEntry(1, 0);
        double m11 = transformationMatrix.getEntry(1, 1);
        double m12 = transformationMatrix.getEntry(1, 2);

        return new AffineTransform(m00, m10, m01, m11, m02, m12);
    }

    public boolean moveXYRelative(double x, double y) {
        try {
            core_.setRelativeXYPosition(xystage_, x, y);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public boolean moveZRelative(double z) {
        try {
            core_.setRelativePosition(zstage_, z);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public boolean moveZAbsolute(double z) {
        try {
            // TODO: check within bounds?
            // TODO: calibrate to make up for lack of parfocality...
            core_.setPosition(zstage_, z);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
    
    public Double getZAbsolute(){
        double z = 0.0;
        
        try{
            z = core_.getPosition(zstage_);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        
        return z;
    }

}
