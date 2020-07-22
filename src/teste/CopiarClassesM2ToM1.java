/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import java.io.IOException;
import teste.matrix.DenseMatrix;

/**
 *
 * @author Danilo Medeiros Eler (FCT-UNESP) - https://daniloeler.github.io/
 */
public class CopiarClassesM2ToM1 {

    public static void main(String[] args) throws IOException {
        ///matrix with classes
        DenseMatrix dm2 = new DenseMatrix();
        dm2.load("E:\\DANILO\\UNESP\\GIT\\fruitdataset\\filename-class.csv.data");

        ///matrix without classes
        DenseMatrix dm1 = new DenseMatrix();
        dm1.load("E:\\DANILO\\UNESP\\GIT\\fruitdataset\\Frutas.data");

        System.out.println("DM1 = "+dm1.getRowCount());
        System.out.println("DM2 = "+dm2.getRowCount());
        for (int i = 0; i < dm1.getRowCount(); i++) {
            String name = dm1.getRow(i).getId().trim();
            int j = 0;
            while (j < dm2.getRowCount() && !dm2.getRow(j).getId().trim().equals(name)) {
                j++;
            }
            if (dm2.getRow(j).getId().trim().equals(name)) {
                dm1.getRow(i).setKlass(dm2.getRow(j).getKlass());
                System.out.println(i+" ===> "+name + " == " + dm2.getRow(j).getId() + " -- class = " + dm2.getRow(j).getKlass());
            }
        }
        
        dm1.save("E:\\DANILO\\UNESP\\GIT\\fruitdataset\\PEx-Image-features.data");
    }
}
