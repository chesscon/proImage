package filtros;


import base.Manipulacion;
import java.awt.image.BufferedImage;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author oscahern
 */
public class FiltrosRGB extends Manipulacion {    
    
    public static BufferedImage filtroRGB(String nameFile, int valor) 
            throws IOException {
        cargarImagen(nameFile);
        int [] pixel = null;
        int red = (valor & 0x00FF0000) >> 16;
        int green = (valor & 0x0000FF00) >> 8;
        int blue = valor & 0x000000FF;
        for (int i = 0; i < altoImg; i++) {
            for (int j = 0; j < anchoImg; j++) {
                pixel = rasterImg.getPixel(j, i, pixel);
                dstImg.setSample(j, i, 0, pixel[0] & red); // Rojo
                dstImg.setSample(j, i, 1, pixel[1] & green); // Verde
                dstImg.setSample(j, i, 2, pixel[2] & blue); // Azul
            }
        }
        buffImg.setData(dstImg);
        return buffImg;
    }
    
    public static BufferedImage filtroRojo(String nameFile) throws IOException {
        return filtroRGB(nameFile, 0x00FF0000);
    }
    
    public static BufferedImage filtroVerde(String nameFile) throws IOException {
        return filtroRGB(nameFile, 0x0000FF00);
    }
    
    public static BufferedImage filtroAzul(String nameFile) throws IOException {
        return filtroRGB(nameFile, 0x000000FF);
    }
    
    public static BufferedImage filtroMosaico(String nameFile, int nAlto, int nAncho) 
            throws IOException {
        cargarImagen(nameFile);
        nAlto = Math.abs(nAlto);
        nAncho = Math.abs(nAncho);
        int [] pixel = null;
        int cant;
        int red, green, blue;
        for (int i = 0; i < anchoImg; i+=nAncho) {
            for (int j = 0; j < altoImg; j+=nAlto) {
                red = 0;
                green = 0;
                blue = 0;
                cant = 0;
                for (int i2 = 0; i2 < nAncho && (i2+i) < anchoImg; i2++) {
                    for (int j2 = 0; j2 < nAlto && (j2+j) < altoImg; j2++) {
                        pixel = rasterImg.getPixel(i2+j, j2+i, pixel);
                        red += pixel[0];
                        green += pixel[1];
                        blue += pixel[2];
                        cant++;
                    }
                }
                red = red / cant;
                green = green / cant;
                blue = blue / cant;
                for (int i2 = 0; i2 < nAncho && (i2+i) < anchoImg; i2++) {
                    for (int j2 = 0; j2 < nAlto && (j2+j) < altoImg; j2++) {
                        dstImg.setSample(i2+i, j2+j, 0, red); 
                        dstImg.setSample(i2+i, j2+j, 1, green); 
                        dstImg.setSample(i2+i, j2+j, 2, blue); 
                    }
                }
            }
        }
        buffImg.setData(dstImg);
        return buffImg;
    }
    
    /**
     * Obtiene una imagen con escala de grises.
     * Se utiliza la siguiente formula, en cada pixel, para calcular el tono de
     * gris: (R + G + B) / 3
     * @param nameFile
     * @return
     * @throws IOException 
     */
    public static BufferedImage filtroGris1(String nameFile) throws IOException{
        cargarImagen(nameFile);
        int [] pixel = null;
        int gris;
        for (int i = 0; i < altoImg; i++) {
            for (int j = 0; j < anchoImg; j++) {
                pixel = rasterImg.getPixel(j, i, pixel);
                gris = rasterImg.getSample(j, i, 0) // rojo
                     + rasterImg.getSample(j, i, 1) // Verde
                     + rasterImg.getSample(j, i, 2); // Azul
                gris = gris / 3;
                dstImg.setSample(j, i, 0, gris); // Rojo
                dstImg.setSample(j, i, 1, gris); // Verde
                dstImg.setSample(j, i, 2, gris); // Azul
            }
        }
        buffImg.setData(dstImg);
        return buffImg;
    }
    
    public static void filtroGris11(String nameFile) throws IOException{
        cargarImagen(nameFile);
        int [] pixel = null;
        int gris;
        for (int i = 0; i < altoImg; i++) {
            for (int j = 0; j < anchoImg; j++) {
                pixel = rasterImg.getPixel(j, i, pixel);
                /*if (i==j && i > 500 && j <600) {
                    System.out.println("Set sample es: ");
                    System.out.println("0: " + rasterImg.getSample(j, i, 0));
                    System.out.println("1: " + rasterImg.getSample(j, i, 1));
                    System.out.println("2: " + rasterImg.getSample(j, i, 2));
                    System.out.println("El pixel es:");
                    for (int k = 0; k < pixel.length; k++) {
                        System.out.println(k+ ": " + pixel[k]);
                    }
                }*/
                int [] newPixel =new int[3];
                gris = (pixel[0] + pixel[1] + pixel[2])/3;
                newPixel[0] = gris;
                newPixel[1] = gris;
                newPixel[2] = gris;
                dstImg.setPixel(j, i, newPixel);
            }
        }
    }
    
    /**
     * Obtiene una imagen con escala de grises.
     * Se utiliza la siguiente formula, en cada pixel, para calcular el tono de
     * gris: 
     * R * 0.21
     * G * 0.71
     * B * 0.07
     * @param nameFile
     * @return
     * @throws IOException 
     */
    public static BufferedImage filtroGris2(String nameFile) throws IOException{
        cargarImagen(nameFile);
        int [] pixel = null;
        int gris;
        for (int i = 0; i < altoImg; i++) {
            for (int j = 0; j < anchoImg; j++) {
                pixel = rasterImg.getPixel(j, i, pixel);
                gris = (int) (rasterImg.getSample(j, i, 0)*0.21 // rojo
                        + rasterImg.getSample(j, i, 1)*0.71 // Verde
                        + rasterImg.getSample(j, i, 2)*0.07); // Azul
                dstImg.setSample(j, i, 0, gris); // Rojo
                dstImg.setSample(j, i, 1, gris); // Verde
                dstImg.setSample(j, i, 2, gris); // Azul
            }
        }
        buffImg.setData(dstImg);
        return buffImg;
    }
    
    /**
     * Obtiene una imagen con escala de grises.
     * Se utiliza la siguiente formula, en cada pixel, para calcular el tono de
     * gris: 
     * ( max(R,G,B) + min(R,G,B) ) / 2
     * @param nameFile
     * @return
     * @throws IOException 
     */
    public static BufferedImage filtroGrisBrillo(String nameFile) throws IOException{
        cargarImagen(nameFile);
        int [] pixel = null;
        int max, min;
        int gris;
        for (int i = 0; i < altoImg; i++) {
            for (int j = 0; j < anchoImg; j++) {
                pixel = rasterImg.getPixel(j, i, pixel);
                max = pixel[0] > pixel[1] ? pixel[0] : pixel[1];
                min = pixel[1] < pixel[0] ? pixel[1] : pixel[0];                
                max = max > pixel[2] ? max : pixel[2];
                min = min < pixel[2] ? min : pixel[2];
                gris = (max+min)/2;
                dstImg.setSample(j, i, 0, gris); // Rojo
                dstImg.setSample(j, i, 1, gris); // Verde
                dstImg.setSample(j, i, 2, gris); // Azul
            }
        }
        buffImg.setData(dstImg);
        return buffImg;
    }
    
    public static BufferedImage filtroAltoContraste1(String nameFile) 
            throws IOException {
        cargarImagen(nameFile);
        int [] pixel = null;
        int gris;
        for (int i = 0; i < altoImg; i++) {
            for (int j = 0; j < anchoImg; j++) {
                pixel = rasterImg.getPixel(j, i, pixel);
                gris = FiltrosRGB.obtenerNumColor(
                        rasterImg.getSample(j, i, 0), 
                        rasterImg.getSample(j, i, 1), 
                        rasterImg.getSample(j, i, 2));
                // gris > 0x00FFFFFF / 2
                gris = gris > 8388607 ? 0x00000000 : 0x00FFFFFF;
                dstImg.setSample(j, i, 0, gris); // Rojo
                dstImg.setSample(j, i, 1, gris); // Verde
                dstImg.setSample(j, i, 2, gris); // Azul
            }
        }
        buffImg.setData(dstImg);
        return buffImg;
    }
    
    public static BufferedImage filtroAltoContraste2(String nameFile) 
            throws IOException {
        cargarImagen(nameFile);
        int [] pixel = null;
        int gris;
        for (int i = 0; i < altoImg; i++) {
            for (int j = 0; j < anchoImg; j++) {
                pixel = rasterImg.getPixel(j, i, pixel);
                gris = FiltrosRGB.obtenerNumColor(
                        rasterImg.getSample(j, i, 0), 
                        rasterImg.getSample(j, i, 1), 
                        rasterImg.getSample(j, i, 2));
                // gris > 0x00FFFFFF / 2
                gris = gris > 8388607 ? 0x00FFFFFF : 0x00000000;
                dstImg.setSample(j, i, 0, gris); // Rojo
                dstImg.setSample(j, i, 1, gris); // Verde
                dstImg.setSample(j, i, 2, gris); // Azul
            }
        }
        buffImg.setData(dstImg);
        return buffImg;
    }
    
    public static BufferedImage filtroAlterarBrillo(String nameFile, int valor) 
            throws IOException {
        cargarImagen(nameFile);
        int [] pixel = null;
        int red, green, blue;
        for (int i = 0; i < altoImg; i++) {
            for (int j = 0; j < anchoImg; j++) {
                pixel = rasterImg.getPixel(j, i, pixel);
                red = pixel[0] + valor;
                green = pixel[1] + valor;
                blue = pixel[2] + valor;
                if (red < 0)
                    red = 0;
                else if (red > 255)
                    red = 255;
                if (blue < 0)
                    blue = 0;
                else if (blue > 255)
                    blue = 255;
                if (green < 0)
                    green = 0;
                else if (green > 255)
                    green = 255;
                
                dstImg.setSample(j, i, 0, red); 
                dstImg.setSample(j, i, 1, green);
                dstImg.setSample(j, i, 2, blue);
            }
        }
        buffImg.setData(dstImg);
        return buffImg;
    }
    
    public static void prueba(String nameFile) throws IOException {
        cargarImagen(nameFile);
        int pixel[] = null;
        pixel = rasterImg.getPixel(253, 520, pixel);
        System.out.println("pixel: ");
        for (int i = 0; i < pixel.length; i++) {
            System.out.print("   " + pixel[i]);
        }
        for (int i = 0; i< 3; i++) {
            System.out.print("\nSample" + i + ": " + rasterImg.getSample(253, 520, i));
        }
    }
    
}
