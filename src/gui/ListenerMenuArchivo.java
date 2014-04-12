
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Scanner;
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
  
  /**
   * Constructor que recibe la vista en donde se cargan los datos
   * @param vista El frame donde se cargarán los datos
   */
  public ListenerMenuArchivo(ProImageFrame vista) {
    this.frame = vista;
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
      try {
        System.out.println(chooser.getSelectedFile().getName());
        Scanner lc = new Scanner(chooser.getSelectedFile());
        String pos = lc.next().trim();
        // this.tablero.setPosPSY(pos);
      } catch (FileNotFoundException ex) { }
    } 
  }

  /**
   * Se encarga de guardar la imagen modificada
   */
  private void guardar() {
    // FALTA IMPLEMENTAR ESTA PARTE!!!
  }
  
}
