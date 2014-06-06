package gui;

import base.Manipulacion;
import filtros.FiltrosRGB;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JColorChooser;
import javax.swing.JMenuItem;

/**
 * Controlador para el menu de los filtros
 *
 * @author oscahern
 */
class ListenerMenuFiltros implements ActionListener {

  public static final String MENU_FILTROS = "Filtros Básicos";

  public static final String STR_MENU_FILTROS_CONTRASTE = "Contraste";
  public static final String STR_MENU_FILTROS_CONTRASTE1 = "Altro Contraste";
  public static final String STR_MENU_FILTROS_CONTRASTE2 = "Altro Contraste Invertido";
  public static final String STR_MENU_FILTROS_RUIDO = "Ruido";
  public static final String STR_MENU_FILTROS_AZUL = "Azul";
  public static final String STR_MENU_FILTROS_ROJO = "Rojo";
  public static final String STR_MENU_FILTROS_VERDE = "Verde";
  public static final String STR_MENU_FILTROS_OTRO = "Otro";
  public static final String STR_MENU_FILTROS_COLOR = "Color";
  public static final String STR_MENU_FILTROS_GRIS = "Gris";
  public static final String STR_MENU_FILTROS_GRIS1 = "Gris (promedio)";
  public static final String STR_MENU_FILTROS_GRIS2 = "Gris (brillo)";
  public static final String STR_MENU_FILTROS_GRIS3 = "Gris (fórmula)";
  public static final String STR_MENU_FILTROS_BRILLO = "Brillo";

  public static final int INT_MENU_FILTROS_CONTRASTE1 = 0;
  public static final int INT_MENU_FILTROS_CONTRASTE2 = 1;
  public static final int INT_MENU_FILTROS_RUIDO = 2;
  public static final int INT_MENU_FILTROS_AZUL = 3;
  public static final int INT_MENU_FILTROS_ROJO = 4;
  public static final int INT_MENU_FILTROS_OTRO = 5;
  public static final int INT_MENU_FILTROS_COLOR = 6;
  public static final int INT_MENU_FILTROS_GRIS1 = 7;
  public static final int INT_MENU_FILTROS_GRIS2 = 8;
  public static final int INT_MENU_FILTROS_GRIS3 = 9;
  public static final int INT_MENU_FILTROS_BRILLO = 10;
  public static final int INT_MENU_FILTROS_CONTRASTE = 11;
  public static final int INT_MENU_FILTROS_GRIS = 12;
  public static final int INT_MENU_FILTROS_VERDE = 13;
  public static final int INT_MENU_FILTROS = 14;

  private ProImageFrame frame;
  private JPanelImagen panelSrc;
  //private JPanelImagen panelSrc;
  private JPanelImagen panelTarget;

  private final String dirTmp = "tmp";

  public ListenerMenuFiltros(ProImageFrame frame, JPanelImagen src, JPanelImagen tg) {
    this.frame = frame;
    this.panelSrc = src;
    this.panelTarget = tg;
    File tmp = new File(dirTmp);
    if (!tmp.exists()) {
      tmp.mkdir();
    }

  }

  /**
   * Controlador para los eventos sobre este menú
   *
   * @param e el evento que provoco la llamada a este controlador
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    try {
      String name = ((JMenuItem) e.getSource()).getName();
      this.frame.limpiarHerramientas();
      int value = Integer.parseInt(name);
      System.out.println("evento sobre menu filtros: ..." + value);
      //panelTarget.setImagen("imgs/girasoles.jpg");
      BufferedImage buffDst = null;
      String imgSrc = this.panelSrc.getNombreImagen();
      switch (value) {
        case INT_MENU_FILTROS_CONTRASTE1:
          buffDst = FiltrosRGB.filtroAltoContraste1(imgSrc);
          break;
        case INT_MENU_FILTROS_CONTRASTE2:
          buffDst = FiltrosRGB.filtroAltoContraste2(imgSrc);
          break;
        case INT_MENU_FILTROS_AZUL:
          buffDst = FiltrosRGB.filtroAzul(imgSrc);
          break;
        case INT_MENU_FILTROS_ROJO:
          buffDst = FiltrosRGB.filtroRojo(imgSrc);
          break;
        case INT_MENU_FILTROS_VERDE:
          buffDst = FiltrosRGB.filtroVerde(imgSrc);
          break;
        case INT_MENU_FILTROS_OTRO:
          int rgb = 0;
          buffDst = null;
          Color newColor = JColorChooser.showDialog(frame, "Elige un Color", Color.ORANGE);
          if (newColor != null) {
            rgb = newColor.getRGB();
            buffDst = FiltrosRGB.filtroRGB(imgSrc, rgb);
          }
          break;
        case INT_MENU_FILTROS_GRIS1:
          buffDst = FiltrosRGB.filtroGris1(imgSrc);
          break;
        case INT_MENU_FILTROS_GRIS2:
          buffDst = FiltrosRGB.filtroGrisBrillo(imgSrc);
          break;
        case INT_MENU_FILTROS_GRIS3:
          buffDst = FiltrosRGB.filtroGris2(imgSrc);
          break;
        case INT_MENU_FILTROS_BRILLO:
          this.frame.getHerramientas().addSliderBrillo();
          this.frame.SliderBrilloVisible(true);
          this.frame.setImgSrcPanel(panelSrc.getNombreImagen());
          this.frame.pack();
          buffDst = FiltrosRGB.filtroAlterarBrillo(imgSrc, 0);
          break;
      }

      File tmp = File.createTempFile("img", ".jpg", new File(dirTmp));

      if (value == INT_MENU_FILTROS_RUIDO) {
        Manipulacion.generarImagenAleatoria(tmp.getAbsolutePath(), 500, 500);
      } else {
        Manipulacion.generarImagen(tmp, buffDst);
      }

      panelTarget.setImagen(tmp.getAbsolutePath());
    } catch (IOException ex) {
      Logger.getLogger(ListenerMenuFiltros.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

}
