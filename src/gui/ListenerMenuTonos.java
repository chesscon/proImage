package gui;

import filtros.AuxDomino;
import filtros.AuxLetras;
import filtros.AuxNaipes;
import filtros.SemiPuntos;
import filtros.Semitonos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author oscahern
 */
class ListenerMenuTonos implements ActionListener {

  static final String STR_MENU_TONOS = "Tonos";

  static final String STR_MENU_SEMITONOS = "semitonos";
  static final String STR_MENU_SEMIPTS = "puntos";
  static final String STR_MENU_ASCII = "ASCII";
  static final String STR_MENU_NAIPES = "Naipes";
  static final String STR_MENU_DOMINO = "Dominó";
  static final String STR_MENU_LETRAS = "Letras";

  static final int INT_MENU_SEMITONOS = 1;
  static final int INT_MENU_SEMIPTS = 2;
  static final int INT_MENU_ASCII = 3;
  static final int INT_MENU_NAIPES = 4;
  static final int INT_MENU_DOMINO = 5;
  static final int INT_MENU_LETRAS = 6;

  private ProImageFrame frame;

  private JPanelImagen panelSrc;

  private JPanelImagen panelTarget;

  private final String dirTmp = "tmp";

  public ListenerMenuTonos(ProImageFrame frame, JPanelImagen src, JPanelImagen dst) {
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
      //System.out.println("evento sobre menu tonos: ..." + value);
      //panelTarget.setImagen("imgs/girasoles.jpg");
      //BufferedImage buffDst = null;
      String imgSrc = this.panelSrc.getNombreImagen();
      switch (value) {
        case INT_MENU_SEMIPTS:
          String nameFile = JOptionPane.showInputDialog(frame, 
                  "Introduce nombre para el archivo (): ", "Crear Semitonos", JOptionPane.INFORMATION_MESSAGE);
          
          String file = Semitonos.cuadricular(imgSrc, 4, 4, new SemiPuntos());
          File tmp = new File(file);
          if (!nameFile.endsWith(".html"))
            nameFile += ".html";
          File f = new File(nameFile);
          tmp.renameTo(f);
          JOptionPane.showMessageDialog(frame, "La imagen ha sido guardada como: " + nameFile);
          break;
        case INT_MENU_NAIPES:
          String nameFile2 = JOptionPane.showInputDialog(frame, 
                  "Introduce nombre para el archivo (): ", "Crear Semitonos", JOptionPane.INFORMATION_MESSAGE);
          
          String file2 = Semitonos.cuadricular(imgSrc, 4, 4, new AuxNaipes());
          File tmp2 = new File(file2);
          if (!nameFile2.endsWith(".html"))
            nameFile2 += ".html";
          File f2 = new File(nameFile2);
          tmp2.renameTo(f2);
          JOptionPane.showMessageDialog(frame, "La imagen ha sido guardada como: " + nameFile2);
          break;
        case INT_MENU_DOMINO:
          String nameFile3 = JOptionPane.showInputDialog(frame, 
                  "Introduce nombre para el archivo (): ", "Crear Semitonos", JOptionPane.INFORMATION_MESSAGE);
          
          String file3 = Semitonos.cuadricular(imgSrc, 4, 4, new AuxDomino());
          File tmp3 = new File(file3);
          if (!nameFile3.endsWith(".html"))
            nameFile3 += ".html";
          File f3 = new File(nameFile3);
          tmp3.renameTo(f3);
          JOptionPane.showMessageDialog(frame, "La imagen ha sido guardada como: " + nameFile3);
          break;
        case INT_MENU_LETRAS:
          String nameFile4 = JOptionPane.showInputDialog(frame, 
                  "Introduce nombre para el archivo (): ", "Crear Semitonos", JOptionPane.INFORMATION_MESSAGE);
          
          String file4 = Semitonos.cuadricular(imgSrc, 4, 4, new AuxLetras());
          File tmp4 = new File(file4);
          if (!nameFile4.endsWith(".html"))
            nameFile4 += ".html";
          File f4 = new File(nameFile4);
          tmp4.renameTo(f4);
          JOptionPane.showMessageDialog(frame, "La imagen ha sido guardada como: " + nameFile4);
          break;
      }

      //File tmp = File.createTempFile("img", ".jpg", new File(dirTmp));

      //Manipulacion.generarImagen(tmp, buffDst);

      //panelTarget.setImagen(tmp.getAbsolutePath());
    } catch (IOException ex) {
      Logger.getLogger(ListenerMenuFiltros.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

}
