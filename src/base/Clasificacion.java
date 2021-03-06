
package base;

import static base.Manipulacion.cargarImagen;
import static base.Manipulacion.obtenerColorPromedio;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author oscahern
 */
public class Clasificacion extends Manipulacion {
    
    public static void clasificarImgs(String ruta, String newFile) throws IOException {
        File dir = new File(ruta);
        FileFilter filter = new FileFilter() {        
            @Override
            public boolean accept(File file) {
                return file.getName().endsWith(".jpg");
            }
        };
        if (dir.isDirectory()) {
            File clasf = new File(newFile);
            FileWriter writer = new FileWriter(clasf);
            BufferedWriter buffer = new BufferedWriter(writer);
            PrintWriter out = new PrintWriter(buffer);
            File imagenes[] = dir.listFiles(filter);
            if (imagenes == null) {
                System.out.println("No hay imagenes jpg en la ruta especificada.");
                return;
            }
            System.out.println("Se encontraron: " + imagenes.length + " imagenes"
                    + "para ser procesadas....");
            int i = 0;
            for (File f : imagenes) {
                ++i;
                if (i%1000 == 0) {
                    System.out.println("Se han procesado: " + i + "imagenes...");
                }
                //if (i<9770 || i>9980) continue;
                //System.out.println(++i + " Procesando el archivo: " + f.getName());
                try {
                    cargarImagen(f);
                    int colorProm[] = obtenerColorPromedio(0,0, anchoImg, altoImg);
                    String aux = colorProm[0] + " " + colorProm[1] + " " 
                            + colorProm[2] + " " + f.getName();
                    out.println(aux);
                } catch (Exception ex) {
                    System.out.println("Ocurrio una excepcion ");
                    System.out.println("al procesar el archivo: " + f.getName());
                }
            }
            buffer.close();
            out.close();
            System.out.println("Se creo el archivo: " + clasf.getName());
        } else {
            System.out.println("La ruta especificada no es un directorio.");
        }
    }
    
}
