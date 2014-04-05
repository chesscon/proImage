
package transformaciones;

import base.Manipulacion;
import static base.Manipulacion.modificarDimensiones;
import base.ProImageException;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.IOException;

/**
 *
 * @author oscahern
 */
public class TamanioImg extends Manipulacion {
    
    /**
     * Reduce el tamaño de una imagen, de acuerdo a los parámetros especificados
     * @param buffImage buffer del archivo de la imagen que se desea reducir
     * @param nAncho Ancho en pixeles al cual se desea recucir
     * @param nAlto Alto en pixeles al cual se desea reducir
     * @return el buffer que contiene la imagen reducida.
     * @throws IOException Se lanza si no se pudo leer correctamente la imagen
     * @throws ProImageException Se lanza si las dimensiones que se indicaron no 
     * son correctas (es decir, son mayores a las de la imágen original)
     */
    public static BufferedImage reducir(BufferedImage buffImage, int nAncho, int nAlto) 
            throws IOException, ProImageException {
        cargarImagen(buffImage);
        nAlto = Math.abs(nAlto);
        nAncho = Math.abs(nAncho);
        
        if (nAlto > altoImg || nAncho > anchoImg) {
            String error = "El ancho y el alto para reducir deben ser menores "
                    + "que el ancho y alto originales, y mayores que cero.";
            throw new ProImageException(error);
        }
        
        System.out.println("Xn: " +  nAncho);
        System.out.println("Yn: " + nAlto);
        
        int anchoOri = anchoImg;
        int altoOri = altoImg;
        
        double proporX = anchoOri/(double)nAncho;
        double proporY = altoOri/(double)nAlto;
        
        int desplazamientoX = (int) Math.floor(proporX);
        int desplazamientoY = (int) Math.floor(proporY);
        
        int proporcionX = (int) Math.ceil(proporX);
        int proporcionY = (int) Math.ceil(proporY);
        
        System.out.println("xOri: " +  anchoOri);
        System.out.println("yOri: " + altoOri);
        
        System.out.println("orig/nue X:" + anchoOri/(double)nAncho);
        System.out.println("orig/nue Y:" + altoOri/(double)nAlto);
        
        System.out.println("ceil_x: " +  Math.ceil(anchoOri/(double)nAncho));
        System.out.println("ceil_y: " + Math.ceil(altoOri/(double)nAlto));
        
        System.out.println("floor_x: " +  Math.floor(anchoOri/(double)nAncho));
        System.out.println("floor_y: " + Math.floor(altoOri/(double)nAlto));
        
        int propX = (int) Math.ceil(proporX);
        int propY = (int) Math.ceil(proporY);
        System.out.println("PropX: " +  propX);
        System.out.println("PropY: " + propY);
        
        Raster r1 = buffImg.getData();
        
        modificarDimensiones(nAncho, nAlto);
        
        int [] pixel = null;
        int cant;
        int red, green, blue;
        double i = 0, j = 0;
        int in = 0, jn = 0;
        for (i = 0, in = 0; in < nAncho && i < anchoOri; i+=proporX, in++) {
            for (j = 0, jn = 0; j < altoOri && jn < nAlto; j+=proporY, jn++) {
                red = 0;
                green = 0;
                blue = 0;
                cant = 0;
                
                int aux1 = (int) Math.floor(i);
                int aux2 = (int) Math.floor(j);
                
                for (int i2 = 0; i2 < proporcionX && (i2+aux1) < anchoOri; i2++) {
                    for (int j2 = 0; j2 < proporcionY && (j2+aux2) < altoOri; j2++) {
                        pixel = r1.getPixel(i2+aux1, j2+aux2, pixel);
                        red += pixel[0];
                        green += pixel[1];
                        blue += pixel[2];
                        cant++;
                    }
                }
                if (cant!= 0) {
                    red /= cant;
                    green /= cant;
                    blue /= cant;
                }

                dstImg.setSample(in, jn, 0, red); 
                dstImg.setSample(in, jn, 1, green); 
                dstImg.setSample(in, jn, 2, blue); 
            }
        }
        buffOutImg.setData(dstImg);
        return buffOutImg;
    }
    
    /**
     * Reduce el tamaño de una imagen, de acuerdo a los parámetros especificados
     * @param nameFile Nombre del archivo de la imagen que se desea reducir
     * @param nAncho Ancho en pixeles al cual se desea recucir
     * @param nAlto Alto en pixeles al cual se desea reducir
     * @return el buffer que contiene la imagen reducida.
     * @throws IOException Se lanza si no se pudo leer correctamente la imagen
     * @throws ProImageException Se lanza si las dimensiones que se indicaron no 
     * son correctas (es decir, son mayores a las de la imágen original)
     */
    public static BufferedImage reducir(String nameFile, int nAncho, int nAlto) 
            throws IOException, ProImageException {
        cargarImagen(nameFile);
        return reducir(buffImg, nAncho, nAlto);
    }
}
