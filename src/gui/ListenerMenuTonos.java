
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author oscahern
 */
class ListenerMenuTonos implements ActionListener {
    
    static String STR_MENU_TONOS = "Tonos";
    
    static String STR_MENU_SEMITONOS = "semitonos";
    static String STR_MENU_SEMIPTS = "puntos";
    static String STR_MENU_ASCII = "ASCII";
    static String STR_MENU_NAIPES = "Naipes";
    static String STR_MENU_DOMINO = "Dominó";
    static String STR_MENU_LETRAS = "Letras";
    
    static int INT_MENU_SEMITONOS = 1;
    static int INT_MENU_SEMIPTS = 2;
    static int INT_MENU_ASCII = 3;
    static int INT_MENU_NAIPES = 4;
    static int INT_MENU_DOMINO = 5;
    static int INT_MENU_LETRAS = 6;
    
    private ProImageFrame frame;
    
    public ListenerMenuTonos(ProImageFrame frame) {
      this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.limpiarHerramientas();
    }
    
}
