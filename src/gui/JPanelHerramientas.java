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
  
  private JSlider sliderSepia;

  private JPanelImagen panelDst;

  private ListenerSliderBrillo actionBrillo;
  
  private ListenerSliderSepia actionSepia;
  
  private boolean estaBrillo;
  private boolean estaSepia;

  public JPanelHerramientas(int ANCHO, JPanelImagen dst) {
    this.panelDst = dst;
    this.setMinimumSize(new Dimension(ANCHO - 10, 30));
    this.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
    this.setOpaque(false);
    init();
    estaBrillo = false;
    estaSepia = false;
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
    
    sliderSepia = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
    sliderSepia.setInverted(false);
    sliderSepia.setPaintTicks(false);
    sliderSepia.setMajorTickSpacing(10);
    sliderSepia.setMinorTickSpacing(5);
    sliderSepia.setPaintLabels(false);
    sliderSepia.setVisible(false);
    actionSepia = new ListenerSliderSepia(this.panelDst, sliderSepia);
    sliderSepia.addChangeListener(actionSepia);
          //JPanel panel = frame.getPanelHerramientas();
    //buffDst = FiltrosRGB.filtroAlterarBrillo(imgSrc, brillo);
    //break;
    this.add(sliderBrillo);
    this.add(sliderSepia);
  }
  
  public void setImgSrc(String imgSrc) {
    this.actionBrillo.setImgSrc(imgSrc);
    this.actionSepia.setImgSrc(imgSrc);
  }

  public void SliderBrilloVisible(boolean b) {
    this.sliderBrillo.setVisible(b);
  }

  public void SliderSepiaVisible(boolean b) {
    this.sliderSepia.setVisible(b);
  }
  
  public void removeSliders() {
    if (estaBrillo) {
      this.SliderBrilloVisible(false);
      this.remove(sliderBrillo);
      this.estaBrillo = false;
    }
    if (estaSepia) {
      this.SliderSepiaVisible(false);
      this.remove(sliderSepia); 
      this.estaSepia = false;
    }
  }
  
  public void addSliderBrillo() {
    if (estaSepia) {
      this.SliderSepiaVisible(false);
     this.remove(sliderSepia);
     this.estaSepia = false;
    }
    if (!estaBrillo) {
      this.SliderBrilloVisible(true);
      this.add(sliderBrillo);
      this.estaBrillo = true;
    }
  }
  
  public void addSliderSepia() {
    if (estaBrillo) {
      this.SliderBrilloVisible(false);
      this.remove(sliderBrillo);
      this.estaBrillo = false;
    }
    if (!estaSepia) {
      this.SliderSepiaVisible(true);
      this.add(sliderSepia);
      this.estaSepia = true;
    }
  }

}