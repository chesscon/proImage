
package gui;

import filtros.FiltrosRGB;
import filtros.Histograma;
import static gui.ListenerMenuOtros.INT_MENU_OLEO;
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
class ListenerMenuMosaicos implements ActionListener {
    
    static String STR_MENU_MOSAICOS = "Mosaicos";
    
    static String STR_MENU_TRADICIONAL = "Tradicional";
    static String STR_MENU_RECURSIVO = "Recursivo";
    static String STR_MENU_FOTOMOSAICO = "Fotomosaico";
    
    static final int INT_MENU_TRADICIONAL = 1;
    static final int INT_MENU_RECURSIVO = 2;
    static final int INT_MENU_FOTOMOSAICO = 3;
    
    private JPanelImagen panelSrc;
    
    private JPanelImagen panelTarget;
    
    private final String dirTmp = "tmp";
    
    public ListenerMenuMosaicos(JPanelImagen src, JPanelImagen dst) {
      this.panelSrc = src;
      this.panelTarget = dst;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
        String name = ((JMenuItem) e.getSource()).getName();
        //this.frame.SliderBrilloVisible(false);
        int value = Integer.parseInt(name);
        System.out.println("evento sobre menu Mosaicos: ..." + value);
        //panelTarget.setImagen("imgs/girasoles.jpg");
        BufferedImage buffDst = null;
        String imgSrc = this.panelSrc.getNombreImagen();
        switch (value) {
          case INT_MENU_TRADICIONAL:
            buffDst = FiltrosRGB.filtroMosaico(imgSrc, 5, 5);
            break;
          case INT_MENU_RECURSIVO:
            //buffDst = Convolucion.convEdgeFilter(imgSrc);
            break;
          case INT_MENU_FOTOMOSAICO:
            //buffDst = Convolucion.convEdgeFilter2(imgSrc);
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
