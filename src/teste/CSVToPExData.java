/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import java.io.IOException;
import teste.csv.CSVReader;

/**
 *
 * @author danilo
 */
public class CSVToPExData {
    public static void main(String args[]) throws IOException{
        CSVReader csvReader = new CSVReader(true, true);
        csvReader.load("E:\\Downloads\\dados.csv");
        csvReader.getMatrix().save("E:\\Downloads\\dados.data");
    }
}
