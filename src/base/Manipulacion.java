
package base;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author oscahern
 */
public class Manipulacion {
    protected static BufferedImage buffImg;
    protected static BufferedImage buffOutImg;
    protected static Raster rasterImg;
    protected static Raster rasterOutImg;
    protected static WritableRaster dstImg;
    protected static int anchoImg;
    protected static int altoImg;
    protected static int tipoImg;
    
    // Falta hacer validaciones sobre el nombre del archivo
    // para poder garantizar que la imagen se cargará adecuadamente
    public static void cargarImagen(String name) throws IOException {
        File file = new File(name);
        buffImg = ImageIO.read(file);
        tipoImg = buffImg.getType();
        anchoImg = buffImg.getWidth();
        altoImg = buffImg.getHeight();
        //Graphics g = buffImg.getGraphics(); 
        rasterImg = buffImg.getData();
        //buffImg.setData(rasterImg);
        dstImg = rasterImg.createCompatibleWritableRaster();    
        //buffOutImg = new BufferedImage();
        buffOutImg = new BufferedImage(anchoImg, altoImg, tipoImg);
    }
    
    public static void generarImagen(String name, BufferedImage img) 
            throws IOException {
        if (img == null) {
            System.out.println("Error!, el buffer de la imagen esta vacio!");
        } else {
            System.out.println("Escribiendo en el archivo: " + name);
            ImageIO.write(img, "jpg", new File(name));        
            System.out.println("La imagen esta lista!");
        }
    }
    
    public static void generarImagen(String name) throws IOException {
        if (buffOutImg == null) return;
        buffOutImg.setData(dstImg);
        System.out.println("Escribiendo en el archivo: " + name);
        ImageIO.write(buffOutImg, "jpg",new File(name));
        System.out.println("La imagen esta lista!");
    }
    
    public static int obtenerNumColor(int red, int green, int blue) {
        int r =  red > 255 ? 255 : red;
        int g =  green > 255 ? 255 : green;
        int b =  blue > 255 ? 255 : blue;
        return (r*65536) + (g*256) + b;
    }
    
    public static void generarImagenAleatoria(String name, int ancho, int alto) throws IOException {
        ancho = Math.abs(ancho);
        alto = Math.abs(alto);
        BufferedImage buff = new BufferedImage(ancho,alto, BufferedImage.TYPE_INT_RGB);
        Raster raster = buff.getData();
        WritableRaster dst = raster.createCompatibleWritableRaster();
        int [] pixel = null;
        for (int i=0; i< ancho; i++) {
            for (int j=0; j<alto;j++) {
                dst.setSample(i, j, 0, Math.floor(Math.random()*255));
                dst.setSample(i, j, 1, Math.floor(Math.random()*255));
                dst.setSample(i, j, 2, Math.floor(Math.random()*255));
            }
        }
        buff.setData(dst);
        generarImagen(name, buff);
    }
    
    public static void modificarDimensiones(int nAncho, int nAlto) {
        if (rasterImg == null) return;
        
        dstImg = rasterImg.createCompatibleWritableRaster(nAncho, nAlto);
        buffOutImg = new BufferedImage(nAncho, nAlto, tipoImg);
    }
    
}
