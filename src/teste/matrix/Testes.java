/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste.matrix;

import java.io.IOException;
import teste.csv.CSVReader;

/**
 *
 * @author danilo
 */
public class Testes {
    public static void main(String args[]) throws IOException{
//        DenseMatrix matrix = new DenseMatrix();
//        matrix.load("E:\\Iris.data");
//        DenseVector vec = (DenseVector) matrix.getRow(0);
//        vec.getValue(0);

        CSVReader reader = new CSVReader(true, true);
        reader.load("E:\\DANILO\\UNESP\\GIT\\fruitdataset\\filename-class.csv");
        reader.getMatrix().save("E:\\DANILO\\UNESP\\GIT\\fruitdataset\\filename-class.csv.data");


    }
}
