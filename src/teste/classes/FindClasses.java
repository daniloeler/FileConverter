/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste.classes;

import java.util.ArrayList;
import java.util.HashMap;
import teste.matrix.DenseMatrix;
import teste.matrix.Matrix;
import teste.matrix.SparseMatrix;

/**
 *
 * @author danilo
 */
public class FindClasses {
    private Matrix oldMatrix;
    private Matrix newMatrix;
    
    public FindClasses(Matrix oldMatrix){
        this.oldMatrix = oldMatrix;
        if (oldMatrix instanceof DenseMatrix)
           this.newMatrix = new DenseMatrix();
        else
            this.newMatrix = new SparseMatrix();
        this.newMatrix.setAttributes(oldMatrix.getAttributes());
    }
    
    public void execute(String separator){
        int cont = oldMatrix.getRowCount();
        ArrayList<String> classes = new ArrayList<String>();
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        int classCont = 0;
        for (int i=0; i<cont; i++){
            String filename = oldMatrix.getRow(i).getId();
            int index = filename.indexOf(separator);
            String classStr = filename.substring(0, index);
            
            Integer klass = map.get(classStr);            
            if (klass == null){
                map.put(classStr, classCont);
                klass = classCont;
                classCont++;                
            }
            newMatrix.addRow(oldMatrix.getRow(i));
            newMatrix.getRow(i).setKlass(klass);
        }
    }
    
    public Matrix getNewMatrix(){
        return this.newMatrix;
    }
}
