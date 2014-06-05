package gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class JPanelImagen extends JPanel {

    private Image imagen;
    private String nombreImg;
    
    //private BufferedImage buff;

    public JPanelImagen() {
    }

    public JPanelImagen(String nombreImagen) {
        this.nombreImg = nombreImagen;
        if (nombreImagen != null) {
            imagen = new ImageIcon(getClass().getResource(nombreImagen)).getImage();
        }
    }

    /*public JPanelImagen(Image imagenInicial) {
        if (imagenInicial != null) {
            imagen = imagenInicial;
        }
    }*/
    
    public BufferedImage getBufferedImage() {
        if (imagen instanceof BufferedImage) {
            return (BufferedImage) imagen;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(imagen.getWidth(null), 
                imagen.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(imagen, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }

    public void setImagen(String nombreImagen) {
        if (nombreImagen != null) {
            this.nombreImg = nombreImagen;
            try {
                imagen = ImageIO.read(new File(nombreImagen));
            } catch (IOException ex) {
                System.out.println("Archivo no encontrado");
                Logger.getLogger(JPanelImagen.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            imagen = null;
        }

        repaint();
    }

    /*
    public void setImagen(Image nuevaImagen) {
        imagen = nuevaImagen;

        repaint();
    }*/

    @Override
    public void paint(Graphics g) {
        if (imagen != null) {
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);

            setOpaque(false);
        } else {
            setOpaque(true);
        }

        super.paint(g);
    }

    String getNombreImagen() {
        return nombreImg;
    }
}
