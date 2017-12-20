/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import java.io.IOException;
import teste.libsvm.LIBSVCReader;
import teste.matrix.DenseMatrix;
import teste.opf.MatrixToOPF;

/**
 *
 * @author danilo
 */
public class ConverterLIBSVCParaMatrix {
     public static void main(String args[]) throws IOException{
        LIBSVCReader lReader = new LIBSVCReader(true, 4096);
        String filename = "C:\\Users\\danilo\\Downloads\\corel5k_features\\corel5k_features.libsvm.txt";
        lReader.load(filename);
        lReader.getMatrix().save(filename+".data");
    }
}
