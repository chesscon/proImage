
package filtros;

import base.Manipulacion;
import static base.Manipulacion.cargarImagen;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 *
 * @author oscahern
 */
public class Combinacion extends Manipulacion {
    
    public static BufferedImage aplicarMatriz(String name, String name2 ) 
            throws IOException {
        cargarImagen(name);
        BufferedImage img1 = buffImg;
        cargarImagen(name2);
        //BufferedImage img2 = buffImg;
        
        return null;
    }
    
}
