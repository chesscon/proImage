
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

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
  private final int ANCHO = 700;
    
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
  private JPanelImagen panelImgSource;
    
  /**
   * Panel en donde se mostrará la imagen modificada
   */
  private JPanelImagen panelImgTarget;
  
  /**
   * Componente en donde se mostrará información (texto) de la imagen modificada
   */
  private JLabel lblImgTgt;
  
  /**
   * Componente en donde se mostrará información (texto) de la imagen original
   */
  private JLabel lblImgSrc;
    
  /**
   * Panel para las herramientas (controles, botones) adicionales de cada filtro
   */
  private JPanel panelHerramientas;
  
  /**
   * Constructor que inicializa los valores por defecto para el frame
   */
  public ProImageFrame() {
    super (NAME);
    Container container = this.getContentPane();
    container.setLayout(new GridBagLayout());
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
    
    // ---  Asignamos los componentes del frame: ----------------
    
    GridBagConstraints constraints = new GridBagConstraints();
    
    // Texto imagen original
    lblImgSrc = new JLabel("Imagen original: ");
    lblImgSrc.setSize(10, 50);
    constraints.gridx = 0; 
    constraints.gridy = 0; 
    //constraints.anchor = GridBagConstraints.EAST; 
    constraints.gridwidth = 1; 
    constraints.gridheight = 1;
    constraints.fill = GridBagConstraints.BOTH;     
    constraints.weighty = 0.1;
    constraints.weightx = 1.0;
    constraints.insets = new Insets(3,3,3,3);    
    container.add (lblImgSrc, constraints);
    
    // panel imagen original
    panelImgSource = new JPanelImagen();
    panelImgSource.setImagen("imgs/Pinguino.jpg");
    panelImgSource.setMinimumSize(new Dimension(ANCHO - 10, ALTO-10));
    panelImgSource.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
    panelImgSource.setOpaque(false);
    constraints.gridx = 0; // La imagen original empieza en la columna cero.
    constraints.gridy = 1; // La imagen original empieza en la fila uno
    //constraints.anchor = GridBagConstraints.CENTER; 
    constraints.gridwidth = 1; // La imagen ocupa una columna
    constraints.gridheight = 1; // La imagen ocupa una fila
    constraints.fill = GridBagConstraints.BOTH;     
    constraints.weighty = 1.0;
    constraints.weightx = 1.0;
    constraints.insets = new Insets(3,3,3,3);    
    container.add (panelImgSource, constraints);
    
    // texto imagen modificada
    lblImgTgt = new JLabel("Imagen modificada: ");
    lblImgTgt.setSize(10, 50);
    constraints.gridx = 1; 
    constraints.gridy = 0; 
    constraints.gridwidth = 1; 
    constraints.gridheight = 1;
    constraints.fill = GridBagConstraints.BOTH;     
    constraints.weighty = 0.1;
    constraints.weightx = 1.0;
    constraints.insets = new Insets(3,3,3,3);    
    container.add (lblImgTgt, constraints);
    
    // Agregamos el panel que contendrá la imagen modificada
    panelImgTarget = new JPanelImagen();
    panelImgTarget.setImagen("imgs/Pinguino.jpg");
    panelImgTarget.setMinimumSize(new Dimension(ANCHO - 10, ALTO-50));
    panelImgTarget.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
    panelImgTarget.setOpaque(false);
        
    constraints.gridx = 1; // La imagen modificada empieza en la columna uno.
    constraints.gridy = 1; // La imagen modificada empieza en la fila uno.
    //constraints.anchor = GridBagConstraints.CENTER; 
    constraints.gridwidth = 1; // La imagen ocupa una columna
    constraints.gridheight = 1; // La imagen ocupa una fila
    constraints.fill = GridBagConstraints.BOTH; 
    constraints.weighty = 1.0;
    constraints.weightx = 1.0;
    constraints.insets = new Insets(3,3,3,3);
    container.add (panelImgTarget, constraints);

    // panelHerramientas
    panelHerramientas = new JPanelImagen();
    panelHerramientas.setMinimumSize(new Dimension(ANCHO - 10, 30));
    panelHerramientas.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
    panelHerramientas.setOpaque(false);        
    constraints.gridx = 0;
    constraints.gridy = 2;
    //constraints.anchor = GridBagConstraints.WEST; 
    constraints.gridwidth = 2; // panel herramientas ocupa 2 columnas
    constraints.gridheight = 1; // y una fila
    constraints.fill = GridBagConstraints.BOTH; 
    constraints.weighty = 0.2;
    constraints.weightx = 1.0;
    constraints.insets = new Insets(3,3,3,3);
    container.add (panelHerramientas, constraints);
    
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
