
package gui;

import filtros.FiltrosRGB;
import filtros.FiltrosVarios;
import filtros.Histograma;
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
    public static final String STR_MENU_ESTEGANO = "Esteganografía";
    
    public static final int INT_MENU_OLEO = 1;
    public static final int INT_MENU_SEPIA = 2;
    public static final int INT_MENU_ALIEN = 3;
    public static final int INT_MENU_PASA_BAJOS = 4;
    public static final int INT_MENU_PASA_ALTOS = 5;
    public static final int INT_MENU_ESTEGANO = 6;
    public static final int INT_MENU_PASA_BAJOS1 = 7;
    public static final int INT_MENU_PASA_BAJOS2 = 8;
    public static final int INT_MENU_PASA_BAJOS3 = 9;
    public static final int INT_MENU_PASA_BAJOS4 = 10;
    public static final int INT_MENU_PASA_ALTOS1 = 11;
    public static final int INT_MENU_PASA_ALTOS2 = 12;
    public static final int INT_MENU_PASA_ALTOS3 = 13;
    
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
        this.frame.SliderSepiaVisible(false);
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
