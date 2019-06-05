/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste.csv;

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
public class CSVReader {

    private DenseMatrix matrix;
    private boolean hasClass;
    private boolean hasID;
    
    public CSVReader(boolean hasClass, boolean hasID) {
        this.hasClass = hasClass;
        this.hasID = hasID;
        this.matrix = new DenseMatrix();
    }

    public void load(String filename) throws IOException {
        ArrayList<Vector> rows = new ArrayList<Vector>();
        ArrayList<String> attributes = new ArrayList<String>();
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(filename));

            //read the attributes
            String line = in.readLine();
            StringTokenizer t1 = new StringTokenizer(line, ",");

            while (t1.hasMoreTokens()) {
                String token = t1.nextToken();
                attributes.add(token.trim());
            }

            int idInt = 0;
            if (hasClass){
                attributes.remove(attributes.size()-1);
            }           
            
            int nrdims = attributes.size();            
            matrix.setAttributes(attributes);
            //read the vectors
            int labelcount = 0;
            while ((line = in.readLine()) != null && line.trim().length() > 0) {
                StringTokenizer t2 = new StringTokenizer(line, ",");
                String id;
                if (hasID){
                   id = t2.nextToken();
                }
                else{
                   id = Integer.toString(idInt);
                   idInt++;
                }
                

                //class data
                float klass = 0.0f;

                //the vector
                float[] vector = new float[nrdims];

                int index = 0;
                while (t2.hasMoreTokens()) {
                    String token = t2.nextToken();
                    float value = Float.parseFloat(token.trim());
                    if (hasClass) {
                        if (t2.hasMoreTokens()) {
                            if (index < nrdims) {
                                vector[index] = value;
                                index++;
                            } else {
                                throw new IOException("Vector with wrong number of "
                                        + "dimensions!");
                            }
                        } else {
                            klass = value;
                        }
                    } else {
                        if (index < nrdims) {
                            vector[index] = value;
                            index++;
                        } else {
                            throw new IOException("Vector with wrong number of "
                                    + "dimensions!");
                        }

                    }
                }
                matrix.addRow(new DenseVector(vector, id, klass));

            }
        } catch (FileNotFoundException e) {
            throw new IOException("File " + filename + " does not exist!");
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        } finally {            
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    Logger.getLogger(DenseMatrix.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public Matrix getMatrix(){
        return this.matrix;
    }
    
}
