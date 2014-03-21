
package filtros;

import base.Manipulacion;
import static base.Manipulacion.cargarImagen;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author oscahern
 */
public class Histograma extends Manipulacion {
    
    public static BufferedImage acuarela(String nameFile) throws IOException {
        cargarImagen(nameFile);
        
        for (int i = 0; i < anchoImg; i++) {
            for (int j = 0; j < altoImg; j++) {
                aplicarAcuarela(i, j);
            }
        }
        
        buffOutImg.setData(dstImg);
        return buffOutImg;
    }

    private static void aplicarAcuarela(int i, int j) {
        int pixels[] =  null;
        int tamCuadro = 5;
        //pixels = rasterImg.getPixels(i, j, 5, 5, pixels);
        pixels = rasterImg.getPixels(i, j, (i+tamCuadro) < anchoImg ? tamCuadro : anchoImg - i, 
                            (j+tamCuadro) < altoImg ? tamCuadro : altoImg - j, pixels);
        
        ArrayList<Color> listaColores = new ArrayList<>();
        ArrayList<Integer> listaContadores = new ArrayList<>();
        
        for (int k = 0; k < pixels.length; k += 3) {
            Color c = new Color(pixels[k], pixels[k+1], pixels[k+2]);
            
            if (!listaColores.contains(c)) {
                listaColores.add(c);
                //System.out.println("Se agrego c:" + c);
                listaContadores.add(1);
            } else {
                int auxI = listaColores.indexOf(c);
                int auxVal = listaContadores.get(auxI);
                listaContadores.set(auxI, ++auxVal);
            }            
        }
        
        int max = 0;
        for (int p = 1; p < listaContadores.size(); p++) {
            max = listaContadores.get(p) > listaContadores.get(max) ? p : max;
        }
        
        int red = listaColores.get(max).getRed();
        int green = listaColores.get(max).getGreen();
        int blue = listaColores.get(max).getBlue(); 
        
        dstImg.setSample(i, j, 0, red); // Rojo
        dstImg.setSample(i, j, 1, green); // Verde
        dstImg.setSample(i, j, 2, blue); // Azul
        
    }
    
    public static void prueba(String nameFile) throws IOException {
        cargarImagen(nameFile);
        int pixels[] = null;
        pixels = rasterImg.getPixels(0, 0, 5, 5, pixels);
        System.out.println("pixels: ");
        for (int i = 0, j = 0; i < pixels.length; i+=3, j++) {
            System.out.print(j + ": " + pixels[i]);
            System.out.print(" , " + pixels[i+1]);
            System.out.println(" , " + pixels[i+2]);
        }
        for (int i = 0; i< 3; i++) {
            System.out.print("\nSample" + i + ": " + rasterImg.getSample(253, 520, i));
        }
    }
    
    public static void main(String args[]) {
        Color c1 = new Color(0,0,0);
        Color c2 = new Color(0,0,0);
        Color c3 = new Color(40,50,200);
        Color c4 = new Color(20,30,250);
        Color c5 = new Color(10,89,140);
        Color colores[] = {c2,c3,c4,c5};
        ArrayList<Color> listaColores = new ArrayList<Color>();
        ArrayList<Integer> listaContadores = new ArrayList<Integer>();
        listaColores.add(c1);
        listaContadores.add(1);
        System.out.println("Se agrego c1");
        for (Color c: colores) {
            if (!listaColores.contains(c)) {
                listaColores.add(c);
                System.out.println("Se agrego c:" + c);
                listaContadores.add(1);
            } else {
                int auxI = listaColores.indexOf(c);
                int auxVal = listaContadores.get(auxI);
                listaContadores.set(auxI, ++auxVal);
            }
        }
        
        for (int i = 0; i < listaColores.size(); i++) {
            System.out.println("color: " + listaColores.get(i));
            System.out.println("\t apariciones: " + listaContadores.get(i));
        }
        
        
    }
    
}