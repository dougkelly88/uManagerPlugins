/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.dougkelly88.FLIMPlateReaderGUI.Classes;

import com.github.dougkelly88.FLIMPlateReaderGUI.GUIComponents.SaveData;
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
    public String tet1="yes";
    public int tet2;
    public String basepath;
    public StrVector DichroicComboBox;
    public int DichState;
    public String DichroicComboBoxSelectedItem;
    
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
 
  public String saveMetadata(String basepath){
      //Saving the all data in basepath+ConfigSoftware.txt
        if (basepath==null){
        JOptionPane.showMessageDialog(null,"Please choose a base path!");
        }
        else{
        PrintWriter writer=null;
        try {
            writer = new PrintWriter(basepath+"\\ConfigSoftware.txt", "UTF-8");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SaveData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(SaveData.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Data to write
        writer.println("Data Document");
        writer.println("Dichroic: "+DichroicComboBoxSelectedItem+";");
        writer.close();
        }
        String ok="OK!";
        return ok;
  }
  public String findLabelOfProperty(String entireFileText, String searchedProperty){
            //String entireFileText= new Scanner(new File("C:\\Users\\Frederik\\Desktop\\ConfigSoftware.txt"))
            //        .useDelimiter("\\A").next();
            
            int lengthProperty=searchedProperty.length();
            int a=entireFileText.indexOf(searchedProperty);
            String substr=entireFileText.substring(a);
            int b=substr.indexOf(";");
            String label=substr.substring(lengthProperty+2, b);
        return label;
  }
}
