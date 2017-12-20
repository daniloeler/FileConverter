/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste.libsvm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import teste.matrix.DenseMatrix;
import teste.matrix.DenseVector;
import teste.matrix.Matrix;
import teste.matrix.Vector;

/**
 *
 * @author danilo
 */
public class LIBSVCReader {

    private DenseMatrix matrix;
    private boolean hasClass;
    private int nrdims;

    public LIBSVCReader(boolean hasClass, int nrdims) {
        this.hasClass = hasClass;
        this.matrix = new DenseMatrix();
        this.nrdims = nrdims;
    }

    public void load(String filename) {
        ArrayList<Vector> rows = new ArrayList<Vector>();
        ArrayList<String> attributes = new ArrayList<String>();
        BufferedReader in = null;

        try {
            in = new BufferedReader(new FileReader(filename));
            String line;
            int id = 0;
            while ((line = in.readLine()) != null && line.trim().length() > 0) {
                StringTokenizer t1 = new StringTokenizer(line, " ");
                String klass = t1.nextToken();
                float[] vector = new float[nrdims];
                while (t1.hasMoreTokens()) {
                    String pair = t1.nextToken();
                    StringTokenizer t2 = new StringTokenizer(pair, ":");
                    int index = Integer.parseInt(t2.nextToken());
                    float value = Float.parseFloat(t2.nextToken());
                    vector[index-1] = value;
                }
                int klassC = id / 100;
                matrix.addRow(new DenseVector(vector, Integer.toString(id+1)+".jpg", klassC));
                id++;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LIBSVCReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LIBSVCReader.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(LIBSVCReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Matrix getMatrix() {
        return this.matrix;
    }

}
