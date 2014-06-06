
package gui;

import base.Manipulacion;
import filtros.FiltrosRGB;
import filtros.FiltrosVarios;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author oscahern
 */
public class ListenerSliderSepia implements ChangeListener {
  
  private String imgSrc;

  private JPanelImagen panelDst;

  private final String dirTmp = "tmp";

  private JSlider slider;

  public ListenerSliderSepia(JPanelImagen dst, JSlider slider) {
    this.panelDst = dst;
    this.slider = slider;
  }

  @Override
  public void stateChanged(ChangeEvent e) {
    try {
      System.out.println("Evento sobre menu sepia");
      File tmp = File.createTempFile("img", ".jpg", new File(dirTmp));
      FiltrosRGB.generarImagen(tmp, FiltrosVarios.Sepia(imgSrc, slider.getValue()));
      panelDst.setImagen(tmp.getAbsolutePath());
      tmp.deleteOnExit();
    } catch (IOException ex) {
      Logger.getLogger(ListenerSliderBrillo.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  void setImgSrc(String imgSrc) {
    this.imgSrc = imgSrc;
  }
  
}
