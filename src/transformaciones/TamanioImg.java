
package transformaciones;

import base.Manipulacion;
import base.ProImageException;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 *
 * @author oscahern
 */
public class TamanioImg extends Manipulacion {
    
    public static BufferedImage reducir1(String nameFile, int nAncho, int nAlto) 
            throws IOException, ProImageException {
        cargarImagen(nameFile);
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
        for (int i = 0, in = 0; i < anchoImg; i+=nAncho, in++) {
            for (int j = 0, jn = 0; j < altoImg; j+=nAlto, jn++) {
                red = 0;
                green = 0;
                blue = 0;
                cant = 0;
                for (int i2 = 0; i2 < nAncho && (i2+i) < anchoImg; i2++) {
                    for (int j2 = 0; j2 < nAlto && (j2+j) < altoImg; j2++) {
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
        //buffImg.setData(dstImg);
        return buffImg;
    }
}
