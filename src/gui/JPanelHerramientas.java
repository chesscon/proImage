package gui;

import filtros.FiltrosRGB;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.LineBorder;

/**
 *
 * @author oscahern
 */
class JPanelHerramientas extends JPanel {

  private JSlider sliderBrillo;

  private JPanelImagen panelDst;

  private ListenerSliderBrillo actionBrillo;

  public JPanelHerramientas(int ANCHO, JPanelImagen dst) {
    this.panelDst = dst;
    this.setMinimumSize(new Dimension(ANCHO - 10, 30));
    this.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
    this.setOpaque(false);
    init();
  }

  public void init() {
    sliderBrillo = new JSlider(JSlider.HORIZONTAL, -100, 100, 0);
    sliderBrillo.setInverted(false);
    sliderBrillo.setPaintTicks(false);
    sliderBrillo.setMajorTickSpacing(20);
    sliderBrillo.setMinorTickSpacing(10);
    sliderBrillo.setPaintLabels(false);
    sliderBrillo.setVisible(false);
    actionBrillo = new ListenerSliderBrillo(this.panelDst, sliderBrillo);
    sliderBrillo.addChangeListener(actionBrillo);
          //JPanel panel = frame.getPanelHerramientas();
    //buffDst = FiltrosRGB.filtroAlterarBrillo(imgSrc, brillo);
    //break;
    this.add(sliderBrillo);
  }
  
  void setImgSrc(String imgSrc) {
    this.actionBrillo.setImgSrc(imgSrc);
  }

  void SliderBrilloVisible(boolean b) {
    this.sliderBrillo.setVisible(b);
  }

}
