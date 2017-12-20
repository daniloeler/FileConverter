/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste.opf;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import teste.matrix.DenseMatrix;
import teste.matrix.Matrix;

/**
 *
 * @author danilo
 */
public class MatrixToOPF {
    private Matrix matrix;    
    private float []classes;
    private float [][] data;
    private int cont;
    private int dim;
    private int qtdClasses;
    
    public MatrixToOPF(Matrix matrix){
        this.matrix = matrix;
        this.cont = matrix.getRowCount();
        this.dim = matrix.getDimensions();
        classes = matrix.getClassData();
        data = matrix.toMatrix();        
        qtdClasses = getNumClasses();
    }    
    
    public int getNumClasses(){
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=0; i<cont; i++){
            Integer klass = (int) classes[i];
            if (! list.contains(klass)){
                list.add(klass);
            }
        }
        return list.size();
    }
    
    public void saveText(String filename){
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new FileWriter(filename));
            ///#samples #labels #features
            out.write(Integer.toString(cont));
            out.write(" ");
            out.write(Integer.toString(qtdClasses));
            out.write(" ");
            out.write(Integer.toString(dim));
            out.write("\r\n");
            
            ///#id #label #features
            for (int i=0; i<cont; i++){                
                out.write(Integer.toString(i));
                out.write(" ");
                out.write(Integer.toString((int)classes[i]));
                out.write(" ");
                for (int j=0; j<dim-1; j++){
                    out.write(Float.toString(data[i][j]));
                    out.write(" ");
                }   
                out.write(Float.toString(data[i][dim-1]));
                out.write("\r\n");
            }    
            
        } catch (IOException ex) {
            Logger.getLogger(MatrixToOPF.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //close the file
            if (out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException ex) {
                    Logger.getLogger(MatrixToOPF.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public void save(String filename){
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(new File(filename));            
            oos = new ObjectOutputStream(fos);
            ///#samples #labels #features
            oos.writeInt(cont);
            oos.writeInt(qtdClasses);
            oos.writeInt(dim);
            
            ///#id #label #features
            for (int i=0; i<cont; i++){                
                oos.writeInt(i);
                oos.writeInt((int)classes[i]);
                for (int j=0; j<dim; j++){
                    oos.writeFloat(data[i][j]);
                }                
            }            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MatrixToOPF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MatrixToOPF.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                oos.close();
                fos.close();                
            } catch (IOException ex) {
                Logger.getLogger(MatrixToOPF.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
