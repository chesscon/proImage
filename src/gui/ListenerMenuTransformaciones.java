
package gui;

import base.Manipulacion;
import base.ProImageException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import transformaciones.TamanioImg;

/**
 *
 * @author oscahern
 */
class ListenerMenuTransformaciones implements ActionListener {
    
    static String MENU_TRANSFORMACIONES = "Transformaciones";
    
    static final String STR_MENU_ICONO = "Icono";
    static final String STR_MENU_ICONO16 = "16x16";
    static final String STR_MENU_ICONO32 = "32x32";
    static final String STR_MENU_ICONO64 = "64x64";
    static final String STR_MENU_ESCALAR = "Escalar";
    static final String STR_MENU_ROTAR = "Rotar";
    
    static final int INT_MENU_ICONO = 1;
    static final int INT_MENU_ICONO16 = 2;
    static final int INT_MENU_ICONO32 = 3;
    static final int INT_MENU_ICONO64 = 4;
    static final int INT_MENU_ESCALAR = 5;
    static final int INT_MENU_ROTAR = 6;
    
    private ProImageFrame frame;
    
    private JPanelImagen panelSrc;

    private JPanelImagen panelTarget;
    
    private final String dirTmp = "tmp";
    
    public ListenerMenuTransformaciones(ProImageFrame frame, JPanelImagen src, JPanelImagen dst) {
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
      System.out.println("evento sobre menu filtros: ..." + value);
      //panelTarget.setImagen("imgs/girasoles.jpg");
      BufferedImage buffDst = null;
      String imgSrc = this.panelSrc.getNombreImagen();
      switch (value) {
        case INT_MENU_ICONO16:
          buffDst = TamanioImg.reducir(imgSrc, 16, 16);
          break;
        case INT_MENU_ICONO32:
          buffDst = TamanioImg.reducir(imgSrc, 32, 32);
          break;
        case INT_MENU_ICONO64:
          buffDst = TamanioImg.reducir(imgSrc, 64, 64);
          break;
        case INT_MENU_ESCALAR:
          //buffDst = FiltrosRGB.filtroRojo(imgSrc);
          break;
        case INT_MENU_ROTAR:
          //buffDst = FiltrosRGB.filtroVerde(imgSrc);
          break;
      }

      File tmp = File.createTempFile("img", ".jpg", new File(dirTmp));

      Manipulacion.generarImagen(tmp, buffDst);

      panelTarget.setImagen(tmp.getAbsolutePath());
    } catch (IOException ex) {
      Logger.getLogger(ListenerMenuFiltros.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ProImageException ex) {
        Logger.getLogger(ListenerMenuTransformaciones.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    
}
