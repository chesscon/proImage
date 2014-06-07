package gui;

import base.Manipulacion;
import filtros.FiltrosVarios;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;

/**
 *
 * @author oscahern
 */
class ListenerMenuOtros implements ActionListener {

  public static final String STR_MENU_OTROS = "Otros";

  public static final String STR_MENU_OLEO = "Óleo";
  public static final String STR_MENU_SEPIA = "Sepia";
  public static final String STR_MENU_ALIEN = "Luz negra";
  public static final String STR_MENU_PASA_BAJOS = "Paso Bajos";
  public static final String STR_MENU_PASA_BAJOS1 = "bajos 1 (1/6)";
  public static final String STR_MENU_PASA_BAJOS2 = "bajos 2 (1/9)";
  public static final String STR_MENU_PASA_BAJOS3 = "bajos 3 (1/10)";
  public static final String STR_MENU_PASA_BAJOS4 = "bajos 4 (1/16)";
  public static final String STR_MENU_PASA_ALTOS = "Paso Altos";
  public static final String STR_MENU_PASA_ALTOS1 = "altos 1";
  public static final String STR_MENU_PASA_ALTOS2 = "altos 2";
  public static final String STR_MENU_PASA_ALTOS3 = "altos 3";
  public static final String STR_MENU_HISTOGRAMA = "Histograma";
  public static final String STR_MENU_HISTOGRAMA_ROJO = "Rojo";
  public static final String STR_MENU_HISTOGRAMA_AZUL = "Azul";
  public static final String STR_MENU_HISTOGRAMA_VERDE = "Verde";
  public static final String STR_MENU_HISTOGRAMA_GRIS = "Gris";

  public static final int INT_MENU_OLEO = 1;
  public static final int INT_MENU_SEPIA = 2;
  public static final int INT_MENU_ALIEN = 3;
  public static final int INT_MENU_PASA_BAJOS = 4;
  public static final int INT_MENU_PASA_ALTOS = 5;
  public static final int INT_MENU_HISTOGRAMA = 6;
  public static final int INT_MENU_PASA_BAJOS1 = 7;
  public static final int INT_MENU_PASA_BAJOS2 = 8;
  public static final int INT_MENU_PASA_BAJOS3 = 9;
  public static final int INT_MENU_PASA_BAJOS4 = 10;
  public static final int INT_MENU_PASA_ALTOS1 = 11;
  public static final int INT_MENU_PASA_ALTOS2 = 12;
  public static final int INT_MENU_PASA_ALTOS3 = 13;
  public static final int INT_MENU_HISTOGRAMA_ROJO = 14;
  public static final int INT_MENU_HISTOGRAMA_AZUL = 15;
  public static final int INT_MENU_HISTOGRAMA_VERDE = 16;
  public static final int INT_MENU_HISTOGRAMA_GRIS = 17;
  

  private ProImageFrame frame;

  private JPanelImagen panelSrc;

  private JPanelImagen panelTarget;

  private final String dirTmp = "tmp";

  public ListenerMenuOtros(ProImageFrame frame, JPanelImagen src, JPanelImagen dst) {
    this.frame = frame;
    this.panelSrc = src;
    this.panelTarget = dst;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    try {
      String name = ((JMenuItem) e.getSource()).getName();
      this.frame.limpiarHerramientas();
      int value = Integer.parseInt(name);
      //System.out.println("evento sobre menu Otros: ..." + value);
      BufferedImage buffDst = null;
      String imgSrc = this.panelSrc.getNombreImagen();
      switch (value) {
        case INT_MENU_OLEO:
          buffDst = filtros.Histograma.acuarela(imgSrc);
          break;
        case INT_MENU_SEPIA:
          this.frame.SliderSepiaVisible(true);
          this.frame.getHerramientas().addSliderSepia();
          this.frame.setImgSrcPanel(panelSrc.getNombreImagen());
          this.frame.pack();
          buffDst = FiltrosVarios.Sepia(imgSrc, 50);
          break;
        case INT_MENU_ALIEN:
          this.frame.SliderLuzNegraVisible(true);
          this.frame.getHerramientas().addSliderLuzNegra();
          this.frame.setImgSrcPanel(panelSrc.getNombreImagen());
          this.frame.pack();
          buffDst = FiltrosVarios.luzNegra(imgSrc, 2);
          break;
        case INT_MENU_PASA_BAJOS1:
          buffDst = FiltrosVarios.pasaBajo1(imgSrc);
          break;
        case INT_MENU_PASA_BAJOS2:
          buffDst = FiltrosVarios.pasaBajo2(imgSrc);
          break;
        case INT_MENU_PASA_BAJOS3:
          buffDst = FiltrosVarios.pasaBajo3(imgSrc);
          break;
        case INT_MENU_PASA_BAJOS4:
          buffDst = FiltrosVarios.pasaBajo4(imgSrc);
          break;
        case INT_MENU_PASA_ALTOS1:
          buffDst = FiltrosVarios.pasaAlto1(imgSrc);
          break;
        case INT_MENU_PASA_ALTOS2:
          buffDst = FiltrosVarios.pasaAlto2(imgSrc);
          break;
        case INT_MENU_PASA_ALTOS3:
          buffDst = FiltrosVarios.pasaAlto3(imgSrc);
          break;
        case INT_MENU_HISTOGRAMA_ROJO:
          base.Histograma ObjHistograma=new base.Histograma();
          int[][] histograma=ObjHistograma.histograma(this.panelSrc.getBufferedImage());
          //DIBUJAMOS EL HISTOGRAMA
          DibujarGrafico ObjDibujaHisto=new DibujarGrafico();
          int[] histogramaCanal=new int[256];
          System.arraycopy(histograma[0], 0, histogramaCanal, 0, histograma[0].length);
          ObjDibujaHisto.crearHistograma(histogramaCanal, this.panelTarget, Color.RED);
          return;
        case INT_MENU_HISTOGRAMA_VERDE:
          base.Histograma ObjHistograma2=new base.Histograma();
          int[][] histograma2=ObjHistograma2.histograma(this.panelSrc.getBufferedImage());
          //DIBUJAMOS EL HISTOGRAMA
          DibujarGrafico ObjDibujaHisto2=new DibujarGrafico();
          int[] histogramaCanal2=new int[256];
          System.arraycopy(histograma2[1], 0, histogramaCanal2, 0, histograma2[1].length);
          ObjDibujaHisto2.crearHistograma(histogramaCanal2, this.panelTarget, Color.GREEN);
          return;
        case INT_MENU_HISTOGRAMA_AZUL:
          base.Histograma ObjHistograma3=new base.Histograma();
          int[][] histograma3=ObjHistograma3.histograma(this.panelSrc.getBufferedImage());
          //DIBUJAMOS EL HISTOGRAMA
          DibujarGrafico ObjDibujaHisto3=new DibujarGrafico();
          int[] histogramaCanal3=new int[256];
          System.arraycopy(histograma3[2], 0, histogramaCanal3, 0, histograma3[2].length);
          ObjDibujaHisto3.crearHistograma(histogramaCanal3, this.panelTarget, Color.BLUE);
          return;
        case INT_MENU_HISTOGRAMA_GRIS:
          base.Histograma ObjHistograma4=new base.Histograma();
          int[][] histograma4=ObjHistograma4.histograma(this.panelSrc.getBufferedImage());
          //DIBUJAMOS EL HISTOGRAMA
          DibujarGrafico ObjDibujaHisto4=new DibujarGrafico();
          int[] histogramaCanal4=new int[256];
          System.arraycopy(histograma4[4], 0, histogramaCanal4, 0, histograma4[4].length);
          ObjDibujaHisto4.crearHistograma(histogramaCanal4, this.panelTarget, Color.BLACK);
          return;
      }

      File tmp = File.createTempFile("img", ".jpg", new File(dirTmp));

      Manipulacion.generarImagen(tmp, buffDst);
      tmp.deleteOnExit();
      panelTarget.setImagen(tmp.getAbsolutePath());
    } catch (IOException ex) {
      Logger.getLogger(ListenerFiltrosConvolucion.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

}
