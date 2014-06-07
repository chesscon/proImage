package filtros;

import base.Manipulacion;
import static base.Manipulacion.cargarImagen;
//import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author oscahern
 */
public class Semitonos extends Manipulacion {

  public static String cuadricular(String nameFile, int nAlto, int nAncho, AuxSemitono aux)
          throws IOException {
    System.out.println("Semitonos - cuadricular");
    cargarImagen(nameFile);
    nAlto = Math.abs(nAlto);
    nAncho = Math.abs(nAncho);
    File html = File.createTempFile("cuadricula", ".html");
    html.deleteOnExit();
    try {
      FileWriter fw = null;
      PrintWriter pw = null;
      fw = new FileWriter(html);
      pw = new PrintWriter(fw);
      int valAncho = anchoImg/nAncho;
      valAncho *= 10;
      int valAlto = altoImg/nAlto;
      valAlto *= 10;
      pw.println("<head> <style>");
      pw.println("@font-face\n" +
    "{\n" +
    "font-family: naipes;\n" +
    "src: url(fonts/CARDS.TTF);\n" +
    "}");
      pw.println("@font-face\n" +
    "{\n" +
    "font-family: domino;\n" +
    "src: url(fonts/lasvwd_.TTF);\n" +
    "}");
      
      pw.println(" </style> </head>");
      pw.println("<div style='margin:0px; width:" + ((anchoImg+1)/nAncho) + "em; font-family: " + aux.getFont() + ";' >");
      int[] pixel = null;
      int cant;
      int red, green, blue;

      
     for (int j = 0; j < altoImg; j += nAlto) {
       for (int i = 0; i < anchoImg; i += nAncho) {   
          red = 0;
          green = 0;
          blue = 0;
          cant = 0;
          for (int i2 = 0; i2 < nAncho && (i2 + i) < anchoImg; i2++) {
            for (int j2 = 0; j2 < nAlto && (j2 + j) < altoImg; j2++) {
              pixel = rasterImg.getPixel(i2 + i, j2 + j, pixel);
              red += pixel[0];
              green += pixel[1];
              blue += pixel[2];
              cant++;
            }
          }
          red = red / cant;
          green = green / cant;
          blue = blue / cant;
          pw.print(aux.mapear(red, green, blue));
        }
        pw.println("<br />");
      }
      pw.println("</div>");
      fw.close();
      //buffImg.setData(dstImg);
      //return buffImg; 
    } catch (IOException e) {
      System.out.println("Ocurrio un error en archivos con Semitonos..");
      //e.printStackTrace();
    } 
    
    return html.getAbsolutePath();
  }

}
