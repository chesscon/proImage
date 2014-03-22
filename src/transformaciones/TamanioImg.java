
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
    
    public static BufferedImage combinarVertical(String nameFile1, 
            String nameFile2, int nAncho,int nAlto) throws IOException, ProImageException {        
        int nAncho1 = nAncho/2;
        int nAncho2 = nAncho - nAncho1;
        BufferedImage img1 = reducir1(nameFile1, nAncho1, nAlto);
        BufferedImage img2 = reducir1(nameFile2, nAncho2, nAlto);
        
        modificarDimensiones(nAncho, nAlto);
       
        Raster r1 = img1.getData();
        Raster r2 = img2.getData();
        
        int pixel[] = null;
        
        for (int i = 0; i < nAncho1; i++) {
            for (int j = 0; j < nAlto; j++) {
                dstImg.setPixel(i, j, r1.getPixel(i, j, pixel)); 
            }
        }
        
        for (int i = nAncho1; i < nAncho; i++) {
            for (int j = 0; j < nAlto; j++) {
                dstImg.setPixel(i, j, r2.getPixel(i-nAncho1, j, pixel)); 
            }
        }
        
        buffOutImg.setData(dstImg);
        return buffOutImg;
    }
    
    public static BufferedImage combinarHorizontal(String nameFile1, 
            String nameFile2, int nAncho,int nAlto) throws IOException, ProImageException {        
        int nAlto1 = nAlto/2;
        int nAlto2 = nAlto - nAlto1;
        BufferedImage img1 = reducir1(nameFile1, nAncho, nAlto1);
        BufferedImage img2 = reducir1(nameFile2, nAncho, nAlto2);
        
        modificarDimensiones(nAncho, nAlto);
       
        Raster r1 = img1.getData();
        Raster r2 = img2.getData();
        
        int pixel[] = null;
        
        for (int i = 0; i < nAncho; i++) {
            for (int j = 0; j < nAlto1; j++) {
                dstImg.setPixel(i, j, r1.getPixel(i, j, pixel)); 
            }
        }
        
        for (int i = 0; i < nAncho; i++) {
            for (int j = nAlto1; j < nAlto; j++) {
                dstImg.setPixel(i, j, r2.getPixel(i, j-nAlto1, pixel)); 
            }
        }
        
        buffOutImg.setData(dstImg);
        return buffOutImg;
    }
    
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
}
