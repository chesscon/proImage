
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

/**
 * Controlador para el menu archivo
 * @author oscahern
 */
class ListenerMenuArchivo implements ActionListener {
  
  /**
   * Nombre que aparecera en la barra de menús
   */
  public static final String MENU_ARCHIVO = "Archivo";
  
  /**
   * Nombre para la opción de abrir
   */
  public static final String STR_MENU_ARCHIVO_ABRIR = "Abrir Imagen";
  
  /**
   * Nombre para la opción para guardar la imagen que ha sido modificada
   */
  public static final String STR_MENU_ARCHIVO_GUARDAR = "Guardar Imagen";
  
  /**
   * Nombre para la opción de salir del programa
   */
  public static final String STR_MENU_ARCHIVO_SALIR = "Salir";
  
  /**
   * Entero para codificar el nombre de la opción abrir
   */
  public static final int INT_MENU_ARCHIVO_ABRIR = 0;
  
  /**
   * Entero para codificar el nombre de la opción guardar
   */
  public static final int INT_MENU_ARCHIVO_GUARDAR = 1;
  
  /**
   * Entero para codificar el nombre de la opción salir
   */
  public static final int INT_MENU_ARCHIVO_SALIR = 2;
  
  /**
   * Título para la ventana que aparece cuando se pide abrir una imagen
   */
  protected static final String TITULO_VENTANA_ABRIR = "Cargar Imagen";
  
  /**
   * La vista
   */
  private ProImageFrame frame;
  
  private JPanelImagen panelSrc;
  private JPanelImagen panelDst;
  
  /**
   * Constructor que recibe la vista en donde se cargan los datos
   * @param vista El frame donde se cargarán los datos
   */
  public ListenerMenuArchivo(ProImageFrame vista, JPanelImagen panel, JPanelImagen paneldst) {
    this.frame = vista;
    this.panelSrc = panel;
    this.panelDst = paneldst;
  }
  
  /**
   * Controlador para los eventos sobre este menú
   * @param e el evento que provoco la llamada a este controlador
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    String name = ((JMenuItem)e.getSource()).getName();
    
    int value = Integer.parseInt(name);
    
    switch(value) {
      case INT_MENU_ARCHIVO_ABRIR:
        abrir();
        break;
      case INT_MENU_ARCHIVO_GUARDAR:
        guardar();
        break;
      case INT_MENU_ARCHIVO_SALIR:
        System.exit(0);    
    }    
  }

  /**
   * Se ejecuta cuando ocurre el evento al dar click en la opción de abrir.
   * Se encarga de mostrar una ventana para que el usuario pueda elegir que 
   * imagen desea abrir.
   */
  private void abrir() {
    //Creamos selector de apertura
    JFileChooser chooser = new JFileChooser();

    //Titulo que llevara la ventana
    chooser.setDialogTitle(TITULO_VENTANA_ABRIR);

    //Si seleccionamos algún archivo devolveremos su directorio
    if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
        String pathFile = chooser.getSelectedFile().getAbsolutePath();        
        panelSrc.setImagen(pathFile);
    } 
  }

  /**
   * Se encarga de guardar la imagen modificada
   */
  private void guardar() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Guardar imagen modificada");   

    int userSelection = fileChooser.showSaveDialog(this.frame);

    if (userSelection == JFileChooser.APPROVE_OPTION) {
        File fileToSave = fileChooser.getSelectedFile();
        File imagen = new File(panelDst.getNombreImagen());
        
        String file_name = fileToSave.toString();
        if (!file_name.endsWith(".jpg"))
            file_name += ".jpg";
        
        imagen.renameTo(new File (file_name));
        /*
        System.out.println("Save as file: " + fileToSave.getAbsolutePath());
        */
    }
  }
  
}
