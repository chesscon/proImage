package filtros;

import base.Manipulacion;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.IOException;

/**
 *
 * @author oscahern
 */
public class FiltrosVarios extends Manipulacion {

  public static float PASO_BAJO1[][] = {{0, 1, 0},
  {1, 2, 1},
  {0, 1, 0}};

  //public static float INT_PB1 = 1 / (float)6;
  public static float PASO_BAJO2[][] = {{1, 1, 1},
  {1, 1, 1},
  {1, 1, 1}};

  //public static float INT_PB2 = 1 / (float)9;
  public static float PASO_BAJO3[][] = {{1, 1, 1},
  {1, 2, 1},
  {1, 1, 1}};

  //public static float INT_PB3 = 1 / (float)10;
  public static float PASO_BAJO4[][] = {{1, 2, 1},
  {2, 4, 2},
  {1, 2, 1}};

  //public static float INT_PB4 = 1 / (float)16;
  public static float PASO_ALTO1[][] = {{0, -1, 0},
  {-1, 5, -1},
  {0, -1, 0}};

  public static float PASO_ALTO2[][] = {{-1, -1, -1},
  {-1, 9, -1},
  {-1, -1, -1}};

  public static float PASO_ALTO3[][] = {{1, -2, 1},
  {-2, 9, -2},
  {1, -2, 1}};

  public static BufferedImage pasaBajo1(String name)
          throws IOException {
    return Convolucion.aplicarMatriz(name, PASO_BAJO1, true);
  }

  public static BufferedImage pasaBajo2(String name)
          throws IOException {
    return Convolucion.aplicarMatriz(name, PASO_BAJO2, true);
  }

  public static BufferedImage pasaBajo3(String name)
          throws IOException {
    return Convolucion.aplicarMatriz(name, PASO_BAJO3, true);
  }

  public static BufferedImage pasaBajo4(String name)
          throws IOException {
    return Convolucion.aplicarMatriz(name, PASO_BAJO4, true);
  }

  public static BufferedImage pasaAlto1(String name)
          throws IOException {
    return Convolucion.aplicarMatriz(name, PASO_ALTO1, true);
  }

  public static BufferedImage pasaAlto2(String name)
          throws IOException {
    return Convolucion.aplicarMatriz(name, PASO_ALTO2, true);
  }

  public static BufferedImage pasaAlto3(String name)
          throws IOException {
    return Convolucion.aplicarMatriz(name, PASO_ALTO3, true);
  }

  public static BufferedImage Sepia(String name, int depth)
          throws IOException {
    Manipulacion.cargarImagen(FiltrosRGB.filtroGris1(name));
    depth = depth > 100 ? 100 : depth;
    depth = depth < 0 ? 0 : depth;

    int pixel[] = null;
    int red, green, blue;
    //int ancho = img.getWidth();
    //int alto = img.getHeight();

    for (int i = 0; i < anchoImg; i++) {
      for (int j = 0; j < altoImg; j++) {
        pixel = rasterImg.getPixel(i, j, pixel);
        red = pixel[0];
        green = pixel[1];
        blue = pixel[2];
        red += (depth*2);
        green += (depth);
        red = red <= 255 ? red : 255;
        red = red > 0 ? red : 0;
        green = green <= 255 ? green : 255;
        green = green > 0 ? green : 0;
        
        dstImg.setSample(i, j, 0, red); // Rojo
        dstImg.setSample(i, j, 1, green); // Verde
        dstImg.setSample(i, j, 2, blue); // Azul
      }
    }

    buffOutImg.setData(dstImg);
    return buffOutImg;
  }

}
