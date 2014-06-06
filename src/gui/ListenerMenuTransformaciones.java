
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author oscahern
 */
class ListenerMenuTransformaciones implements ActionListener {
    
    static String MENU_TRANSFORMACIONES = "Transformaciones";
    
    static String STR_MENU_ICONO = "Icono";
    static String STR_MENU_ICONO16 = "16x16";
    static String STR_MENU_ICONO32 = "32x32";
    static String STR_MENU_ICONO64 = "64x64";
    static String STR_MENU_ESCALAR = "Escalar";
    static String STR_MENU_ROTAR = "Rotar";
    
    static int INT_MENU_ICONO = 1;
    static int INT_MENU_ICONO16 = 2;
    static int INT_MENU_ICONO32 = 3;
    static int INT_MENU_ICONO64 = 4;
    static int INT_MENU_ESCOLAR = 5;
    static int INT_MENU_ROTAR = 6;
    
    private ProImageFrame frame;
    
    public ListenerMenuTransformaciones(ProImageFrame frame) {
      this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
