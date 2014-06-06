
package gui;

import filtros.Convolucion;
import filtros.FiltrosRGB;
import filtros.Histograma;
import static gui.ListenerFiltrosConvolucion.INT_MENU_BLUR1;
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
    public static final String STR_MENU_PASA_ALTOS = "Paso altos";
    public static final String STR_MENU_ESTEGANO = "Esteganografía";
    
    public static final int INT_MENU_OLEO = 1;
    public static final int INT_MENU_SEPIA = 2;
    public static final int INT_MENU_ALIEN = 3;
    public static final int INT_MENU_PASA_BAJOS = 4;
    public static final int INT_MENU_PASA_ALTOS = 5;
    public static final int INT_MENU_ESTEGANO = 6;
    
    private JPanelImagen panelSrc;
    
    private JPanelImagen panelTarget;
    
    private final String dirTmp = "tmp";
    
    public ListenerMenuOtros(JPanelImagen src, JPanelImagen dst) {
      this.panelSrc = src;
      this.panelTarget = dst;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
        String name = ((JMenuItem) e.getSource()).getName();
        //this.frame.SliderBrilloVisible(false);
        int value = Integer.parseInt(name);
        System.out.println("evento sobre menu Otros: ..." + value);
        //panelTarget.setImagen("imgs/girasoles.jpg");
        BufferedImage buffDst = null;
        String imgSrc = this.panelSrc.getNombreImagen();
        switch (value) {
          case INT_MENU_OLEO:
            buffDst = Histograma.acuarela(imgSrc);
            break;
          case INT_MENU_SEPIA:
            //buffDst = Convolucion.convEdgeFilter(imgSrc);
            break;
          case INT_MENU_ALIEN:
            //buffDst = Convolucion.convEdgeFilter2(imgSrc);
            break;
          case INT_MENU_PASA_BAJOS:
            //buffDst = Convolucion.convEmboss(imgSrc);
            break;
          case INT_MENU_PASA_ALTOS:
            //buffDst = Convolucion.convSharpen(imgSrc);
            break;
          case INT_MENU_ESTEGANO:
            //buffDst = Convolucion.convSharpen2(imgSrc);
            break;
        }
        
        File tmp = File.createTempFile("img", ".jpg", new File(dirTmp));
        
        FiltrosRGB.generarImagen(tmp, buffDst);
        tmp.deleteOnExit();
        panelTarget.setImagen(tmp.getAbsolutePath());
      } catch (IOException ex) {
        Logger.getLogger(ListenerFiltrosConvolucion.class.getName()).log(Level.SEVERE, null, ex);
      }      
    }
    
}
