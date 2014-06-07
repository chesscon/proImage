
package filtros;

/**
 *
 * @author oscahern
 */
public class AuxDomino implements AuxSemitono  {
  
  public String cadena = "1234567890";
  
  private int[] intervalos = { 26, 52, 78, 104, 130, 155, 182, 208, 233, 256 };
  
  public String font = "domino";
  

  @Override
  public String mapear(int red, int green, int blue) {
    int prom = (red + green + blue) / 3;
    int pos = 0;
    while (pos < 10 && intervalos[pos] < prom ) pos++;
    String font = "";
    
    String res = "" + cadena.charAt(pos);
    return res;
  }

  @Override
  public String getFont() {
    return this.font;
  }
  
}
