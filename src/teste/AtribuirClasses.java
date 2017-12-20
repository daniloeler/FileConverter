/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import java.io.IOException;
import teste.classes.FindClasses;
import teste.matrix.DenseMatrix;

/**
 *
 * @author danilo
 */
public class AtribuirClasses {
    public static void main(String args[]) throws IOException{
        DenseMatrix dm = new DenseMatrix();
        dm.load("e:\\AllFiguresNotNormalized.data");        
        FindClasses fC = new FindClasses(dm);
        fC.execute("_");
        fC.getNewMatrix().save("e:\\AllFigures-withClasses-NotNormalized.data");
    }
}
