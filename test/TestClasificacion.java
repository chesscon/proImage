
import base.Clasificacion;
import java.io.IOException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author oscahern
 */
public class TestClasificacion {
    
    public static void main (String args[]) throws IOException {
        testClasificacionColor();
    }

    private static void testClasificacionColor() throws IOException {
        //String carpeta = "/Users/oscahern/Pictures/art_explosion";
        //String newfile = "art_exposion.txt";
        
        String carpeta = "/Users/oscahern/Pictures/bboa";
        String newfile = "bboa.txt";
        Clasificacion.clasificarImgs(carpeta, newfile);
    }
    
}
