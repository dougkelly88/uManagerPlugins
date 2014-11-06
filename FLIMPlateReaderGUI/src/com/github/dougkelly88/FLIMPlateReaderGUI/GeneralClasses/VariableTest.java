/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.dougkelly88.FLIMPlateReaderGUI.GeneralClasses;

import com.github.dougkelly88.FLIMPlateReaderGUI.GeneralGUIComponents.SaveData;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import mmcorej.StrVector;

/**
 *
 * @author Frederik
 */
public class VariableTest {
// All variables define here:
    //Test variables:
    
    public String tet1="yes";
    public int tet2;
    
    // General variables:
    public String basepath="C:\\Users\\Frederik\\Desktop";//Default basepath
    public String entireFileText;
    
    // LightPathControl variables:
    public StrVector DichroicComboBox;
    public String DichroicComboBoxSelectedItem;
    public StrVector EmissionComboBox;
    public String EmissionComboBoxSelectedItem;
    public StrVector ExcitationComboBox;
    public String ExcitationComboBoxSelectedItem;
    public StrVector NDFWComboBox;
    public String NDFWComboBoxSelectedItem;
    public StrVector ObjectiveomboBox;
    public String ObjectiveComboBoxSelectedItem;
    public StrVector FilterCubeComboBox;
    public String FilterCubeComboBoxSelectedItem;
    public StrVector SwitchPortComboBox;
    public String SwitchPortComboBoxSelectedItem;
    
    
  public static VariableTest getInstance() {
     return fINSTANCE;
  }

  // PRIVATE

  /**
  * Single instance created upon class loading.
  */
  private static final VariableTest fINSTANCE =  new VariableTest();
  /**
  * Private constructor prevents construction outside this class.
  */
 
  public String saveMetadata(){
      //Saving the all data in basepath+ConfigSoftware.txt
      // Check if basepath is defined. If yes saves, if not open dialog.
        if (basepath==null){
        JOptionPane.showMessageDialog(null,"Please choose a base path!");
        }
        else{
        PrintWriter writer=null;
        try {
            writer = new PrintWriter(basepath+"\\ConfigSoftware.txt", "UTF-8"); 
//Data to write. Please write in the form [writer.println("Label: "+PropertyToSave+";");]. This structure is important for the loading process.
        writer.println("Data Document");
        writer.println();
        writer.println();
        writer.println("General");
        writer.println("Base path: "+basepath);
        writer.println();
        writer.println("LightPathControl:");
        writer.println("Dichroic: "+DichroicComboBoxSelectedItem+";");
        writer.println("Emission: "+EmissionComboBoxSelectedItem+";");
        writer.println("Excitation: "+ExcitationComboBoxSelectedItem+";");
        writer.println("Neutral Density Filter: "+NDFWComboBoxSelectedItem+";");
        writer.println("Objective: "+ObjectiveComboBoxSelectedItem+";");
        writer.println("Filter Cube: "+FilterCubeComboBoxSelectedItem+";");
        writer.println("Light Path Prism: "+SwitchPortComboBoxSelectedItem+";");
        writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SaveData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(SaveData.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        }
        String ok="OK!";
        return ok;
  }
  public String findLabelOfProperty(String searchedProperty){
            //String entireFileText= new Scanner(new File("C:\\Users\\Frederik\\Desktop\\ConfigSoftware.txt"))
             // .useDelimiter("\\A").next();
      String label=null;
      // Check if basepath is defined. If yes contiue, if not open dialog.        
        if (basepath==null){
        JOptionPane.showMessageDialog(null,"Please choose a base path!");
        }
        else{
        PrintWriter writer=null;
            try {
               
                entireFileText = new Scanner(new File(basepath+"\\ConfigSoftware.txt"))
                .useDelimiter("\\A").next();
        
            } catch (FileNotFoundException ex) {
                    Logger.getLogger(SaveData.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            int lengthProperty=searchedProperty.length();
            int a=entireFileText.indexOf(searchedProperty);
            String substr=entireFileText.substring(a);
            int b=substr.indexOf(";");
            label=substr.substring(lengthProperty+2, b);
        }
        return label; 
  }
}
