
package filtros;

/**
 *
 * @author oscahern
 */
public class AuxLetras implements AuxSemitono {
  
  public String cadena = " .+%$2YODAUQ#HNM";
  
  public String font = "";
  
  private int[] intervalos = { 19, 40, 60, 80, 100, 120, 140, 160, 180, 
    200, 220, 240, 256 };

  @Override
  public String mapear(int red, int green, int blue) {
    int prom = (red + green + blue) / 3;
    int pos = 0;
    while (pos < 16 && intervalos[pos] < prom ) pos++;
    
    String res = "" + cadena.charAt(pos);
    return res;
  }

  @Override
  public String getFont() {
    return this.font;
  }
  
}
