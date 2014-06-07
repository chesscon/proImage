
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
  private JPanelHerramientas panelHerramientas;
  
  //private JSlideBrillo sliderBrillo;
  
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
    panelHerramientas = new JPanelHerramientas(ANCHO, this.panelImgTarget);
           
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
    JMenu menuFiltrosBasicos = initMenuFiltrosBasicos();
    JMenu menuConvolucion = initMenuFiltrosConvolucion();
    JMenu menuTransformaciones = initMenuTransformaciones();
    JMenu menuTonos = initMenuTonos();
    JMenu menuMosaicos = initMenuMosaicos();
    JMenu menuBlending = initMenuBlending();    
    JMenu menuOtros = initMenuOtros();
    
    barMenu.add(menuArchivo);
    barMenu.add(menuFiltrosBasicos);
    barMenu.add(menuConvolucion);
    barMenu.add(menuTransformaciones);
    barMenu.add(menuTonos);
    barMenu.add(menuMosaicos);
    barMenu.add(menuBlending);
    barMenu.add(menuOtros);
    
    this.setJMenuBar(barMenu);
  }

  /**
   * Inicializa el menu Archivo con las opciones para abrir y guardar una imagen
   * @return una instancia para el menu archivo
   */
  private JMenu initMenuArchivo() {
    JMenu menuArchivo = new JMenu(ListenerMenuArchivo.MENU_ARCHIVO);
    ListenerMenuArchivo action = new ListenerMenuArchivo(this, this.panelImgSource, this.panelImgTarget);
    
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
  private JMenu initMenuFiltrosBasicos() {
    JMenu menuFiltros = new JMenu(ListenerMenuFiltros.MENU_FILTROS);
    ListenerMenuFiltros action = new ListenerMenuFiltros(this, this.panelImgSource, this.panelImgTarget);
    
    JMenuItem brillo = new JMenuItem(ListenerMenuFiltros.STR_MENU_FILTROS_BRILLO);
    brillo.setName(ListenerMenuFiltros.INT_MENU_FILTROS_BRILLO + "");
    brillo.addActionListener(action);
    
    JMenu contraste = new JMenu(ListenerMenuFiltros.STR_MENU_FILTROS_CONTRASTE);
    contraste.setName(ListenerMenuFiltros.INT_MENU_FILTROS_CONTRASTE + "");
    //contraste.addActionListener(action);
    
    JMenuItem contraste1 = new JMenuItem(ListenerMenuFiltros.STR_MENU_FILTROS_CONTRASTE1);
    contraste1.setName(ListenerMenuFiltros.INT_MENU_FILTROS_CONTRASTE1 + "");
    contraste1.addActionListener(action);
    
    JMenuItem contraste2 = new JMenuItem(ListenerMenuFiltros.STR_MENU_FILTROS_CONTRASTE2);
    contraste2.setName(ListenerMenuFiltros.INT_MENU_FILTROS_CONTRASTE2     + "");
    contraste2.addActionListener(action);
    
    contraste.add(contraste1);
    contraste.add(contraste2);
    
    JMenu color = new JMenu(ListenerMenuFiltros.STR_MENU_FILTROS_COLOR);
    color.setName(ListenerMenuFiltros.INT_MENU_FILTROS_COLOR + "");
    //color.addActionListener(action);
    
    JMenuItem rojo = new JMenuItem(ListenerMenuFiltros.STR_MENU_FILTROS_ROJO);
    rojo.setName(ListenerMenuFiltros.INT_MENU_FILTROS_ROJO + "");
    rojo.addActionListener(action);
    
    JMenuItem verde = new JMenuItem(ListenerMenuFiltros.STR_MENU_FILTROS_VERDE);
    verde.setName(ListenerMenuFiltros.INT_MENU_FILTROS_VERDE + "");
    verde.addActionListener(action);
    
    JMenuItem azul = new JMenuItem(ListenerMenuFiltros.STR_MENU_FILTROS_AZUL);
    azul.setName(ListenerMenuFiltros.INT_MENU_FILTROS_AZUL + "");
    azul.addActionListener(action);
    
    JMenuItem otro = new JMenuItem(ListenerMenuFiltros.STR_MENU_FILTROS_OTRO);
    otro.setName(ListenerMenuFiltros.INT_MENU_FILTROS_OTRO + "");
    otro.addActionListener(action);
    
    color.add(rojo);
    color.add(verde);
    color.add(azul);
    color.add(otro);
    
    JMenu gris = new JMenu(ListenerMenuFiltros.STR_MENU_FILTROS_GRIS);
    gris.setName(ListenerMenuFiltros.INT_MENU_FILTROS_GRIS + "");
    //gris.addActionListener(action);
    
    JMenuItem gris1 = new JMenuItem(ListenerMenuFiltros.STR_MENU_FILTROS_GRIS1);
    gris1.setName(ListenerMenuFiltros.INT_MENU_FILTROS_GRIS1 + "");
    gris1.addActionListener(action);
    
    JMenuItem gris2 = new JMenuItem(ListenerMenuFiltros.STR_MENU_FILTROS_GRIS2);
    gris2.setName(ListenerMenuFiltros.INT_MENU_FILTROS_GRIS2 + "");
    gris2.addActionListener(action);
    
    JMenuItem gris3 = new JMenuItem(ListenerMenuFiltros.STR_MENU_FILTROS_GRIS3);
    gris3.setName(ListenerMenuFiltros.INT_MENU_FILTROS_GRIS3 + "");
    gris3.addActionListener(action);
    
    gris.add(gris1);
    gris.add(gris2);
    gris.add(gris3);
    
    JMenuItem ruido = new JMenuItem(ListenerMenuFiltros.STR_MENU_FILTROS_RUIDO);
    ruido.setName(ListenerMenuFiltros.INT_MENU_FILTROS_RUIDO + "");
    ruido.addActionListener(action);
    
    menuFiltros.add(contraste);
    menuFiltros.add(brillo);
    menuFiltros.add(color);
    menuFiltros.add(gris);
    menuFiltros.add(ruido);
    
    return menuFiltros;
  }

    private JMenu initMenuFiltrosConvolucion() {
        JMenu menuConvolucion = new JMenu(ListenerFiltrosConvolucion.MENU_CONVOLUCION);
        ListenerFiltrosConvolucion action = 
                new ListenerFiltrosConvolucion(this, this.panelImgSource, this.panelImgTarget);
        
        JMenu blur = new JMenu(ListenerFiltrosConvolucion.STR_MENU_BLUR);
        blur.setName(ListenerFiltrosConvolucion.INT_MENU_BLUR + "");   
        
        JMenuItem blur1 = new JMenuItem(ListenerFiltrosConvolucion.STR_MENU_BLUR1);
        blur1.setName(ListenerFiltrosConvolucion.INT_MENU_BLUR1 + "");
        blur1.addActionListener(action);
        
        JMenuItem blur2 = new JMenuItem(ListenerFiltrosConvolucion.STR_MENU_BLUR2);
        blur2.setName(ListenerFiltrosConvolucion.INT_MENU_BLUR2 + "");
        blur2.addActionListener(action);
        
        blur.add(blur1);
        blur.add(blur2);
        
        JMenu edge = new JMenu(ListenerFiltrosConvolucion.STR_MENU_EDGE_FILTER);
        edge.setName(ListenerFiltrosConvolucion.INT_MENU_EDGE_FILTER + "");   
        
        JMenuItem edge1 = new JMenuItem(ListenerFiltrosConvolucion.STR_MENU_EDGE_FILTER1);
        edge1.setName(ListenerFiltrosConvolucion.INT_MENU_EDGE_FILTER1 + "");
        edge1.addActionListener(action);
        
        JMenuItem edge2 = new JMenuItem(ListenerFiltrosConvolucion.STR_MENU_EDGE_FILTER2);
        edge2.setName(ListenerFiltrosConvolucion.INT_MENU_EDGE_FILTER2 + "");
        edge2.addActionListener(action);
        
        edge.add(edge1);
        edge.add(edge2);
        
        JMenu sharpen = new JMenu(ListenerFiltrosConvolucion.STR_MENU_SHARPEN);
        sharpen.setName(ListenerFiltrosConvolucion.INT_MENU_SHARPEN + "");   
        
        JMenuItem sharpen1 = new JMenuItem(ListenerFiltrosConvolucion.STR_MENU_SHARPEN1);
        sharpen1.setName(ListenerFiltrosConvolucion.INT_MENU_SHARPEN1 + "");
        sharpen1.addActionListener(action);
        
        JMenuItem sharpen2 = new JMenuItem(ListenerFiltrosConvolucion.STR_MENU_SHARPEN2);
        sharpen2.setName(ListenerFiltrosConvolucion.INT_MENU_SHARPEN2 + "");
        sharpen2.addActionListener(action);
        
        JMenuItem sharpen3 = new JMenuItem(ListenerFiltrosConvolucion.STR_MENU_SHARPEN3);
        sharpen3.setName(ListenerFiltrosConvolucion.INT_MENU_SHARPEN3 + "");
        sharpen3.addActionListener(action);
        
        sharpen.add(sharpen1);
        sharpen.add(sharpen2);
        sharpen.add(sharpen3);
        
        JMenuItem emboss = new JMenuItem(ListenerFiltrosConvolucion.STR_MENU_EMBOSS);
        emboss.setName(ListenerFiltrosConvolucion.INT_MENU_EMBOSS + "");
        emboss.addActionListener(action);
        
        menuConvolucion.add(blur);
        menuConvolucion.add(edge);
        menuConvolucion.add(sharpen);
        menuConvolucion.add(emboss);
        
        return menuConvolucion;
    }

    private JMenu initMenuTransformaciones() {
        JMenu menu = new JMenu(ListenerMenuTransformaciones.MENU_TRANSFORMACIONES);
        ListenerMenuTransformaciones action = 
                new ListenerMenuTransformaciones(this, this.panelImgSource, this.panelImgTarget);
        
        JMenu icono = new JMenu(ListenerMenuTransformaciones.STR_MENU_ICONO);
        icono.setName(ListenerMenuTransformaciones.INT_MENU_ICONO + "");   
        
        JMenuItem icono16 = new JMenuItem(ListenerMenuTransformaciones.STR_MENU_ICONO16);
        icono16.setName(ListenerMenuTransformaciones.INT_MENU_ICONO16 + "");
        icono16.addActionListener(action);
        
        JMenuItem icono32 = new JMenuItem(ListenerMenuTransformaciones.STR_MENU_ICONO32);
        icono32.setName(ListenerMenuTransformaciones.INT_MENU_ICONO32 + "");
        icono32.addActionListener(action);
        
        JMenuItem icono64 = new JMenuItem(ListenerMenuTransformaciones.STR_MENU_ICONO64);
        icono64.setName(ListenerMenuTransformaciones.INT_MENU_ICONO64 + "");
        icono64.addActionListener(action);
        
        icono.add(icono16);
        icono.add(icono32);
        icono.add(icono64);
        
        JMenuItem escalar = new JMenuItem(ListenerMenuTransformaciones.STR_MENU_ESCALAR);
        escalar.setName(ListenerMenuTransformaciones.INT_MENU_ESCALAR + "");
        escalar.addActionListener(action);
        
        JMenuItem rotar = new JMenuItem(ListenerMenuTransformaciones.STR_MENU_ROTAR);
        rotar.setName(ListenerMenuTransformaciones.INT_MENU_ROTAR + "");
        rotar.addActionListener(action);
        
        //menu.add(escalar);
        //menu.add(rotar);
        menu.add(icono);
        
        return menu;
    }

    private JMenu initMenuTonos() {
        JMenu menu = new JMenu(ListenerMenuTonos.STR_MENU_TONOS);
        ListenerMenuTonos action =
          new ListenerMenuTonos(this, this.panelImgSource, this.panelImgTarget);
        
        JMenu semi = new JMenu(ListenerMenuTonos.STR_MENU_SEMITONOS);
        semi.setName(ListenerMenuTonos.INT_MENU_SEMITONOS + "");   
        
        JMenuItem puntos = new JMenuItem(ListenerMenuTonos.STR_MENU_SEMIPTS);
        puntos.setName(ListenerMenuTonos.INT_MENU_SEMIPTS + "");
        puntos.addActionListener(action);
        
        semi.add(puntos);
        
        JMenu ascii = new JMenu(ListenerMenuTonos.STR_MENU_ASCII);
        ascii.setName(ListenerMenuTonos.INT_MENU_ASCII + "");   
        
        JMenuItem letras = new JMenuItem(ListenerMenuTonos.STR_MENU_LETRAS);
        letras.setName(ListenerMenuTonos.INT_MENU_LETRAS + "");
        letras.addActionListener(action);
        
        JMenuItem domino = new JMenuItem(ListenerMenuTonos.STR_MENU_DOMINO);
        domino.setName(ListenerMenuTonos.INT_MENU_DOMINO + "");
        domino.addActionListener(action);
        
        JMenuItem naipes = new JMenuItem(ListenerMenuTonos.STR_MENU_NAIPES);
        naipes.setName(ListenerMenuTonos.INT_MENU_NAIPES + "");
        naipes.addActionListener(action);
        
        ascii.add(letras);
        ascii.add(domino);
        ascii.add(naipes);        
        
        menu.add(semi);
        menu.add(ascii);
        
        return menu;
    }

    private JMenu initMenuMosaicos() {
        JMenu menu = new JMenu(ListenerMenuMosaicos.STR_MENU_MOSAICOS);
        ListenerMenuMosaicos action = new ListenerMenuMosaicos(this, this.panelImgSource, this.panelImgTarget);
        
        JMenuItem tradicional = new JMenuItem(ListenerMenuMosaicos.STR_MENU_TRADICIONAL);
        tradicional.setName(ListenerMenuMosaicos.INT_MENU_TRADICIONAL + "");
        tradicional.addActionListener(action);
        
        JMenuItem recursivo = new JMenuItem(ListenerMenuMosaicos.STR_MENU_RECURSIVO);
        recursivo.setName(ListenerMenuMosaicos.INT_MENU_RECURSIVO + "");
        recursivo.addActionListener(action);
        
        JMenuItem foto = new JMenuItem(ListenerMenuMosaicos.STR_MENU_FOTOMOSAICO);
        foto.setName(ListenerMenuMosaicos.INT_MENU_FOTOMOSAICO + "");
        foto.addActionListener(action);
        
        menu.add(tradicional);
        menu.add(recursivo);
        //menu.add(foto);
        
        return menu;
    }

    private JMenu initMenuBlending() {
        JMenu menu = new JMenu(ListenerMenuBlending.STR_MENU_BLENDING );
        ListenerMenuBlending action = 
                new ListenerMenuBlending(this, this.panelImgSource, this.panelImgTarget);
        
        JMenuItem mezclar = new JMenuItem(ListenerMenuBlending.STR_MENU_MEZCLAR);
        mezclar.setName(ListenerMenuBlending.INT_MENU_MEZCLAR + "");
        mezclar.addActionListener(action);
        
        JMenuItem encimar = new JMenuItem(ListenerMenuBlending.STR_MENU_IMG);
        encimar.setName(ListenerMenuBlending.INT_MENU_IMG + "");
        encimar.addActionListener(action);
        
        JMenuItem marca = new JMenuItem(ListenerMenuBlending.STR_MENU_MARCA);
        marca.setName(ListenerMenuBlending.INT_MENU_MARCA + "");
        marca.addActionListener(action);
        
        menu.add(mezclar);
        //menu.add(encimar);
        //menu.add(marca);
        
        return menu;
    }

    private JMenu initMenuOtros() {
        JMenu menu = new JMenu(ListenerMenuOtros.STR_MENU_OTROS);
        ListenerMenuOtros action = new ListenerMenuOtros(this, this.panelImgSource, this.panelImgTarget);
        
        JMenuItem oleo = new JMenuItem(ListenerMenuOtros.STR_MENU_OLEO);
        oleo.setName(ListenerMenuOtros.INT_MENU_OLEO + "");
        oleo.addActionListener(action);
        
        JMenuItem sepia = new JMenuItem(ListenerMenuOtros.STR_MENU_SEPIA);
        sepia.setName(ListenerMenuOtros.INT_MENU_SEPIA + "");
        sepia.addActionListener(action);
        
        JMenuItem alien = new JMenuItem(ListenerMenuOtros.STR_MENU_ALIEN);
        alien.setName(ListenerMenuOtros.INT_MENU_ALIEN + "");
        alien.addActionListener(action);
        
        JMenu pasaBajos = new JMenu(ListenerMenuOtros.STR_MENU_PASA_BAJOS);
        pasaBajos.setName(ListenerMenuOtros.INT_MENU_PASA_BAJOS + "");
        
        JMenuItem bajos1 = new JMenuItem(ListenerMenuOtros.STR_MENU_PASA_BAJOS1);
        bajos1.setName(ListenerMenuOtros.INT_MENU_PASA_BAJOS1 + "");
        bajos1.addActionListener(action);
        
        JMenuItem bajos2 = new JMenuItem(ListenerMenuOtros.STR_MENU_PASA_BAJOS2);
        bajos2.setName(ListenerMenuOtros.INT_MENU_PASA_BAJOS2 + "");
        bajos2.addActionListener(action);
        bajos2.addActionListener(action);
        
        JMenuItem bajos3 = new JMenuItem(ListenerMenuOtros.STR_MENU_PASA_BAJOS3);
        bajos3.setName(ListenerMenuOtros.INT_MENU_PASA_BAJOS3 + "");
        bajos3.addActionListener(action);
        bajos3.addActionListener(action);
        
        JMenuItem bajos4 = new JMenuItem(ListenerMenuOtros.STR_MENU_PASA_BAJOS4);
        bajos4.setName(ListenerMenuOtros.INT_MENU_PASA_BAJOS4 + "");
        bajos4.addActionListener(action);
        bajos4.addActionListener(action);
        
        pasaBajos.add(bajos1);
        pasaBajos.add(bajos2);
        pasaBajos.add(bajos3);
        pasaBajos.add(bajos4);
        
        JMenu pasaAltos = new JMenu(ListenerMenuOtros.STR_MENU_PASA_ALTOS);
        pasaAltos.setName(ListenerMenuOtros.INT_MENU_PASA_ALTOS + "");
        
        JMenuItem altos1 = new JMenuItem(ListenerMenuOtros.STR_MENU_PASA_ALTOS1);
        altos1.setName(ListenerMenuOtros.INT_MENU_PASA_ALTOS1 + "");
        altos1.addActionListener(action);
        altos1.addActionListener(action);
        
        JMenuItem altos2 = new JMenuItem(ListenerMenuOtros.STR_MENU_PASA_ALTOS2);
        altos2.setName(ListenerMenuOtros.INT_MENU_PASA_ALTOS2 + "");
        altos2.addActionListener(action);
        altos2.addActionListener(action);
        
        JMenuItem altos3 = new JMenuItem(ListenerMenuOtros.STR_MENU_PASA_ALTOS3);
        altos3.setName(ListenerMenuOtros.INT_MENU_PASA_ALTOS3 + "");
        altos3.addActionListener(action);
        altos3.addActionListener(action);
        
        pasaAltos.add(altos1);
        pasaAltos.add(altos2);
        pasaAltos.add(altos3);
        
        JMenu histograma = new JMenu(ListenerMenuOtros.STR_MENU_HISTOGRAMA);
        histograma.setName(ListenerMenuOtros.INT_MENU_HISTOGRAMA + "");
        //histograma.addActionListener(action);
        
        JMenuItem histoRojo = new JMenuItem(ListenerMenuOtros.STR_MENU_HISTOGRAMA_ROJO);
        histoRojo.setName(ListenerMenuOtros.INT_MENU_HISTOGRAMA_ROJO + "");
        histoRojo.addActionListener(action);
        
        JMenuItem histoVerde = new JMenuItem(ListenerMenuOtros.STR_MENU_HISTOGRAMA_VERDE);
        histoVerde.setName(ListenerMenuOtros.INT_MENU_HISTOGRAMA_VERDE + "");
        histoVerde.addActionListener(action);
        
        JMenuItem histoAzul = new JMenuItem(ListenerMenuOtros.STR_MENU_HISTOGRAMA_AZUL);
        histoAzul.setName(ListenerMenuOtros.INT_MENU_HISTOGRAMA_AZUL + "");
        histoAzul.addActionListener(action);
        
        JMenuItem histoGris = new JMenuItem(ListenerMenuOtros.STR_MENU_HISTOGRAMA_GRIS);
        histoGris.setName(ListenerMenuOtros.INT_MENU_HISTOGRAMA_GRIS + "");
        histoGris.addActionListener(action);
        
        histograma.add(histoRojo);
        histograma.add(histoVerde);
        histograma.add(histoAzul);
        histograma.add(histoGris);
        
        menu.add(oleo);
        menu.add(sepia);
        menu.add(alien);
        menu.add(pasaBajos);
        menu.add(pasaAltos);
        menu.add(histograma);
        
        return menu;
    }

  JPanelHerramientas getPanelHerramientas() {
    return this.panelHerramientas;
  }

  void SliderBrilloVisible(boolean b) {
    this.panelHerramientas.SliderBrilloVisible(b);
  }
  
  void SliderSepiaVisible(boolean b) {
    this.panelHerramientas.SliderSepiaVisible(b);
  }
  
  void SliderLuzNegraVisible(boolean b) {
    this.panelHerramientas.SliderLuzNegraVisible(b);
  }

  void setImgSrcPanel(String nombreImagen) {
    this.panelHerramientas.setImgSrc(nombreImagen);
  }

  void limpiarHerramientas() {
    this.panelImgTarget.removeAll();
    this.panelHerramientas.removeSliders();
  }

  protected JPanelHerramientas getHerramientas() {
    return this.panelHerramientas;
  }
  
}
