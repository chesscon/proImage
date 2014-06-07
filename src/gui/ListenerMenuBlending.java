
package gui;

import base.Manipulacion;
import base.ProImageException;
import filtros.Combinacion;
import static gui.ListenerMenuArchivo.TITULO_VENTANA_ABRIR;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author oscahern
 */
class ListenerMenuBlending implements ActionListener {
    
    public static final String STR_MENU_BLENDING = "Blending";
    
    public static final String STR_MENU_MEZCLAR = "Mezclar (blending)";
    public static final String STR_MENU_MARCA = "Marca de Agua";
    public static final String STR_MENU_IMG = "Encimar imagen";
    
    public static final int INT_MENU_MEZCLAR = 1;
    public static final int INT_MENU_MARCA = 2;
    public static final int INT_MENU_IMG = 3;
    
    private ProImageFrame frame;
    
    private JPanelImagen panelSrc;
    
    private JPanelImagen panelTarget;
    
    private final String dirTmp = "tmp";

    public ListenerMenuBlending(ProImageFrame frame, JPanelImagen src, JPanelImagen dst) {
      this.frame = frame;
      this.panelSrc = src;
      this.panelTarget = dst;      
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.limpiarHerramientas();
        String imgSrc = this.panelSrc.getNombreImagen();
        //Creamos selector de apertura
    JFileChooser chooser = new JFileChooser();

    //Titulo que llevara la ventana
    chooser.setDialogTitle(TITULO_VENTANA_ABRIR);

    //Si seleccionamos algún archivo devolveremos su directorio
    if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
          try {
            String pathFile = chooser.getSelectedFile().getAbsolutePath();
            
            File tmp = File.createTempFile("img", ".jpg", new File(dirTmp));

            Manipulacion.generarImagen(tmp, Combinacion.blending(imgSrc, pathFile, 0.5));
            tmp.deleteOnExit();
            panelTarget.setImagen(tmp.getAbsolutePath());
            
          } catch (IOException ex) {
            Logger.getLogger(ListenerMenuBlending.class.getName()).log(Level.SEVERE, null, ex);
          } catch (ProImageException ex) {
            Logger.getLogger(ListenerMenuBlending.class.getName()).log(Level.SEVERE, null, ex);
          }
    } 
    }
    
}
