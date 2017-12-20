/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import java.io.IOException;
import teste.matrix.DenseMatrix;
import teste.opf.MatrixToOPF;

/**
 *
 * @author danilo
 */
public class ConverterParaOPF {
    public static void main(String args[]) throws IOException{
        DenseMatrix m = new DenseMatrix();
        m.load("E:\\iris.data");
        //m.load("E:\\iris.data");
        
        MatrixToOPF mToOPF = new MatrixToOPF(m);
        mToOPF.save("E:\\iris.data.opf");
        mToOPF.saveText("E:\\iris.data.opf.txt");
    }
}
