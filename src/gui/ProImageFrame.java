
package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * Frame para la interfaz gráfica.
 * @author Oscar Hernández chesscon@gmail.com
 */
public class ProImageFrame extends JFrame {
  
  /**
   * Nombre del programa
   */
  public static final String NAME = "ProImage - OSCAR HC";
  
  /*
   * El ancho que tendra la ventana
   */
  private final int ANCHO = 650;
    
    /*
     * El alto que tendra la ventana
     */
    private final int ALTO = 600;
  
  /**
   * Carpeta donde estan las imagenes temporales
   */
  public static final String dirTMP = "img" + File.separator + "tmp" 
                                            + File.separator;
    
  /**
    * Panel en donde se cargará la imagen origen
    */
  private JPanel panelImgSource;
    
  /**
   * Panel en donde se mostrará la imagen modificada
   */
  private JPanel panelImgTarget;
    
  /**
   * Panel para las herramientas (controles, botones) adicionales de cada filtro
   */
  private JPanel panelHerramientas;
  
  /**
   * Constructor que inicializa los valores por defecto para el frame
   */
  public ProImageFrame() {
    // Definimos las dimensiones del frame
    this.setSize(ANCHO, ALTO);
    
    // tamaño minimo al que se podrá redimensionar la pantalla
    this.setMinimumSize(new Dimension(ANCHO, ALTO));
        
    // Le damos el titulo que deberá mostrar en la ventana
    this.setTitle(NAME);
    
    // Definimos la operacion (por default) del boton cerrar de la ventana
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    
    // Colocar la ventana en el centro e la pantalla
    this.setLocationRelativeTo(null);
        
    // Definimos el layout por defecto:
    BorderLayout layout = new BorderLayout();
    layout.setHgap(10);
    layout.setVgap(5);
        
    this.setLayout(layout);
    
    // ---  Asignamos los componentes del frame: ----------------
    
    // Barra de menus
    initBarMenus();
    
  }

  /**
   * Inicializa la barra de menus del frame
   */
  private void initBarMenus() {    
    JMenuBar barMenu = new JMenuBar();
    
    JMenu menuArchivo = initMenuArchivo();
    JMenu menuFiltros = initMenuFiltros();
    
    barMenu.add(menuArchivo);
    barMenu.add(menuFiltros);
    
    this.setJMenuBar(barMenu);
  }

  /**
   * Inicializa el menu Archivo con las opciones para abrir y guardar una imagen
   * @return una instancia para el menu archivo
   */
  private JMenu initMenuArchivo() {
    JMenu menuArchivo = new JMenu(ListenerMenuArchivo.MENU_ARCHIVO);
    ListenerMenuArchivo action = new ListenerMenuArchivo(this);
    
    JMenuItem guardar = new JMenuItem(ListenerMenuArchivo.STR_MENU_ARCHIVO_GUARDAR);
    guardar.setName(ListenerMenuArchivo.INT_MENU_ARCHIVO_GUARDAR + "");
    guardar.addActionListener(action);
      
    JMenuItem abrir = new JMenuItem(ListenerMenuArchivo.STR_MENU_ARCHIVO_ABRIR);
    abrir.setName(ListenerMenuArchivo.INT_MENU_ARCHIVO_ABRIR + "");
    abrir.addActionListener(action);
      
    JMenuItem salir = new JMenuItem(ListenerMenuArchivo.STR_MENU_ARCHIVO_SALIR);
    salir.setName(ListenerMenuArchivo.INT_MENU_ARCHIVO_SALIR + "");
    salir.addActionListener(action);
      
    menuArchivo.add(abrir);
    menuArchivo.add(guardar);
    menuArchivo.add(salir);
    
    return menuArchivo;
  }

  /**
   * Inicializa un menú desde el que se podrán aplicar los diferentes filtros a
   * la imagen que se debe cargar previamente
   * @return una instancia del menu para Filtros
   */
  private JMenu initMenuFiltros() {
    JMenu menuFiltros = new JMenu(ListenerMenuFiltros.MENU_FILTROS);
    ListenerMenuFiltros action = new ListenerMenuFiltros();
    
    return menuFiltros;
  }
  
}
