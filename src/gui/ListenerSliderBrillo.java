/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import base.Manipulacion;
import filtros.FiltrosRGB;
import static gui.ListenerMenuFiltros.INT_MENU_FILTROS_RUIDO;
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
class ListenerSliderBrillo implements ChangeListener {

  private String imgSrc;

  private JPanelImagen panelDst;

  private final String dirTmp = "tmp";

  private JSlider slider;

  public ListenerSliderBrillo(JPanelImagen dst, JSlider slider) {
    this.panelDst = dst;
    this.slider = slider;
  }

  @Override
  public void stateChanged(ChangeEvent e) {
    try {
      File tmp = File.createTempFile("img", ".jpg", new File(dirTmp));
      FiltrosRGB.generarImagen(tmp, FiltrosRGB.filtroAlterarBrillo(imgSrc, slider.getValue()));
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
