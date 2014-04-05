
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
     * @param nameFile Nombre del archivo de la imagen que se desea reducir
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
        modificarDimensiones(nAncho, nAlto);
        int [] pixel = null;
        int cant;
        int red, green, blue;
        
        int proporX = anchoImg/nAncho;
        int proporY = altoImg/nAlto;
        
        for (int i = 0, in = 0; i < anchoImg && in< nAncho; i+=proporX, in++) {
            for (int j = 0, jn = 0; j < altoImg && jn < nAlto; j+=proporY, jn++) {
                red = 0;
                green = 0;
                blue = 0;
                cant = 0;
                for (int i2 = 0; i2 < proporX && (i2+i) < anchoImg; i2++) {
                    for (int j2 = 0; j2 < proporY && (j2+j) < altoImg; j2++) {
                        pixel = rasterImg.getPixel(i2+i, j2+j, pixel);
                        red += pixel[0];
                        green += pixel[1];
                        blue += pixel[2];
                        cant++;
                    }
                }
                red = red / cant;
                green = green / cant;
                blue = blue / cant;
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
