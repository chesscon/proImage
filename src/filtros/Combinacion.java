
package filtros;

import base.Manipulacion;
import static base.Manipulacion.cargarImagen;
import static base.Manipulacion.modificarDimensiones;
import base.ProImageException;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.IOException;
import static transformaciones.TamanioImg.reducir;

/**
 *
 * @author oscahern
 */
public class Combinacion extends Manipulacion {
    
    /**
     * Combina dos imagenes de manera vertical (una a lado de la otra).
     * @param nameFile1 Nombre de la imagen que irá a la izquierda
     * @param nameFile2 Nombre de la imagen que irá a la derecha
     * @param nAncho ancho en pixeles que se desea para la imagen combinada 
     * (debe ser menor al ancho del minimo ancho de las dos imágenes)
     * @param nAlto alto en pixeles que se desea para la imagen combinada (debe
     * se menor al alto del mínimo alto de las dos imágenes)
     * @return El buffer con la imagen que resulta de combinar las 2 solicitadas
     * @throws IOException Se lanza si ocurre un error al intentar leer la imagen
     * @throws ProImageException Se lanza si las dimensiones especificas no se
     * pueden utilizar. (ya que son mayores a alguna de las imágenes)
     */
    public static BufferedImage combinarVertical(String nameFile1, 
            String nameFile2, int nAncho,int nAlto) throws IOException, ProImageException {        
        int nAncho1 = nAncho/2;
        int nAncho2 = nAncho - nAncho1;
        BufferedImage img1 = reducir(nameFile1, nAncho1, nAlto);
        BufferedImage img2 = reducir(nameFile2, nAncho2, nAlto);
        
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
    
    /**
     * Combina dos imagenes de manera horizontal (una a bajo de la otra).
     * @param nameFile1 Nombre de la imagen que irá arriba
     * @param nameFile2 Nombre de la imagen que irá abajo
     * @param nAncho ancho en pixeles que se desea para la imagen combinada 
     * (debe ser menor al ancho del minimo ancho de las dos imágenes)
     * @param nAlto alto en pixeles que se desea para la imagen combinada (debe
     * se menor al alto del mínimo alto de las dos imágenes)
     * @return El buffer con la imagen que resulta de combinar las 2 solicitadas
     * @throws IOException Se lanza si ocurre un error al intentar leer la imagen
     * @throws ProImageException Se lanza si las dimensiones especificas no se
     * pueden utilizar. (ya que son mayores a alguna de las imágenes)
     */
    public static BufferedImage combinarHorizontal(String nameFile1, 
            String nameFile2, int nAncho,int nAlto) throws IOException, ProImageException {        
        int nAlto1 = nAlto/2;
        int nAlto2 = nAlto - nAlto1;
        BufferedImage img1 = reducir(nameFile1, nAncho, nAlto1);
        BufferedImage img2 = reducir(nameFile2, nAncho, nAlto2);
        
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
    
    
    /**
     * Realiza el blending entre 2 imágenes (las fusiona de acuerdo al 
     * porcentaje especificado)
     * @param name Nombre del archivo que contiene la imagen 1
     * @param name2 Nombre del archivo que contiene la imagen 2
     * @param p porcentaje en que predominará la imagen1
     * @return regresa un buffer con el blending de las 2 imágenes
     * @throws IOException Se lanza si ocurre un error mientras se lee la imagen
     * @throws base.ProImageException
     */
    public static BufferedImage blending(String name, String name2, double p ) 
            throws IOException, ProImageException {
        p = p > 1 ? 1 : p;
        p = p < 0 ? 0 : p;
        cargarImagen(name);
        int ancho1 = anchoImg;
        int alto1 = altoImg;
        
        System.out.println("Blending.....");
        System.out.println("Dimensiones Imagen1: " + ancho1 + " , " + alto1);
        BufferedImage img1 = buffImg;
        cargarImagen(name2);
        int ancho2 = anchoImg;
        int alto2 = altoImg;
        System.out.println("Dimensiones Imagen2: " + ancho2 + " , " + alto2);
        BufferedImage img2 = buffImg;
        
        int nAncho = Math.min(ancho1, ancho2);
        int nAlto = Math.min(alto1, alto2);
        System.out.println("Dimensiones Imagen a generar: " + nAncho + " , " + nAlto);
        img1 = reducir(img1, nAncho, nAlto);
        img2 = reducir(img2, nAncho, nAlto);
        
        Raster r1 = img1.getData();
        Raster r2 = img2.getData();
        
        int pixel[] = null;
        double red, green, blue;
        double q = 1-p;
        for (int i = 0; i < nAncho; i++) {
            for (int j = 0; j < nAlto; j++) {
                red = 0;
                green = 0;
                blue = 0;
                pixel = r1.getPixel(i, j, pixel);
                red += pixel[0]*p;
                green += pixel[1]*p;
                blue += pixel[2]*p;
                pixel = r2.getPixel(i, j, pixel);
                red += pixel[0]*q;
                green += pixel[1]*q;
                blue += pixel[2]*q;
                pixel[0] = (int) red;
                pixel[1] = (int) green;
                pixel[2] = (int) blue;
                dstImg.setPixel(i, j, pixel); 
            }
        }
        
        buffOutImg.setData(dstImg);
        return buffOutImg;
    }
    
}
