
package base;

/**
 *
 * @author oscahern
 */
public class Pixel {
    
    private int red;

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }
    private int green;
    private int blue;
    
    public boolean equals(Pixel p) {
        return p.red == this.red && p.green == this.green && p.blue == this.blue;
    }
    
}
