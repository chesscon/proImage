
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author oscahern
 */
class ListenerMenuBlending implements ActionListener {
    
    public static final String STR_MENU_BLENDING = "Blending";
    
    public static final String STR_MENU_MEZCLAR = "Mezclar (blending)";
    public static final String STR_MENU_MARCA = "Marca de Agua";
    public static final String STR_MENU_IMG = "Encimar imagen";
    
    public static final int INT_MENU_MEZCLAR = 1;
    public static final int INT_MENU_MARCA = 2;
    public static final int INT_MENU_IMG = 3;
    
    private ProImageFrame frame;

    public ListenerMenuBlending(ProImageFrame frame) {
      this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.limpiarHerramientas();
    }
    
}
