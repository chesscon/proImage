
package gui;

import base.Manipulacion;
import filtros.Convolucion;
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
class ListenerFiltrosConvolucion implements ActionListener {
    static String MENU_CONVOLUCION = "Convolución";
    
    static String STR_MENU_BLUR = "BLUR";
    static String STR_MENU_BLUR1 = "BLUR 1";
    static String STR_MENU_BLUR2 = "BLUR 2";
    static String STR_MENU_EDGE_FILTER = "Bordes";
    static String STR_MENU_EDGE_FILTER1 = "Edge Filter 1";
    static String STR_MENU_EDGE_FILTER2 = "Edge Filter 2";
    static String STR_MENU_SHARPEN = "Sharpen";
    static String STR_MENU_SHARPEN1 = "Sharpen1";
    static String STR_MENU_SHARPEN2 = "Sharpen2";
    static String STR_MENU_SHARPEN3 = "Sharpen3";
    static String STR_MENU_EMBOSS = "Emboss";
    
    static final int INT_MENU_BLUR = 0;
    static final int INT_MENU_BLUR1 = 1;
    static final int INT_MENU_BLUR2 = 2;
    static final int INT_MENU_EDGE_FILTER = 3;
    static final int INT_MENU_EDGE_FILTER1 = 4;
    static final int INT_MENU_EDGE_FILTER2 = 5;
    static final int INT_MENU_SHARPEN = 6;
    static final int INT_MENU_SHARPEN1 = 7;
    static final int INT_MENU_SHARPEN2 = 8;
    static final int INT_MENU_SHARPEN3 = 9;
    static final int INT_MENU_EMBOSS = 10;
    
    private ProImageFrame frame;
    
    private JPanelImagen panelSrc;
    
    private JPanelImagen panelTarget;
    
    private final String dirTmp = "tmp";
    
    public ListenerFiltrosConvolucion(ProImageFrame frame, JPanelImagen src, JPanelImagen dst) {
      this.frame = frame;
      this.panelSrc = src;
      this.panelTarget = dst;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      try {
        String name = ((JMenuItem) e.getSource()).getName();
        this.frame.limpiarHerramientas();
        //this.frame.SliderBrilloVisible(false);
        int value = Integer.parseInt(name);
        System.out.println("evento sobre menu convolucion: ..." + value);
        //panelTarget.setImagen("imgs/girasoles.jpg");
        BufferedImage buffDst = null;
        String imgSrc = this.panelSrc.getNombreImagen();
        switch (value) {
          case INT_MENU_BLUR1:
            buffDst = Convolucion.convBlur(imgSrc);
            break;
          case INT_MENU_BLUR2:
            buffDst = Convolucion.convBlur2(imgSrc);
            break;
          case INT_MENU_EDGE_FILTER1:
            buffDst = Convolucion.convEdgeFilter(imgSrc);
            break;
          case INT_MENU_EDGE_FILTER2:
            buffDst = Convolucion.convEdgeFilter2(imgSrc);
            break;
          case INT_MENU_EMBOSS:
            buffDst = Convolucion.convEmboss(imgSrc);
            break;
          case INT_MENU_SHARPEN1:
            buffDst = Convolucion.convSharpen(imgSrc);
            break;
          case INT_MENU_SHARPEN2:
            buffDst = Convolucion.convSharpen2(imgSrc);
            break;
          case INT_MENU_SHARPEN3:
            buffDst = Convolucion.convSharpen3(imgSrc);
            break;
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
