
package filtros;

/**
 *
 * @author oscahern
 */
public class SemiPuntos implements AuxSemitono {
  
  private String dir = "imgs/circulos/";
  
  private String pref = "2";
  
  private String[] names = { "circulo0.jpg", "circulo5.jpg", "circulo8.jpg",
    "circulo10.jpg", "circulo12.jpg", "circulo15.jpg", "circulo18.jpg",
    "circulo20.jpg", "circulo22.jpg", "circulo25.jpg", "circulo28.jpg",
    "circulo30.jpg", "circulo32.jpg", "circulo35.jpg", "circulo38.jpg",
    "circulo40.jpg"}; 
  
  private int[] intervalos = { 16, 32, 48, 64, 80, 96, 112, 128, 144, 160, 176, 
    192, 208, 224, 240, 256 };

  @Override
  public String mapear(int red, int green, int blue) {
    int prom = (red + green + blue) / 3;
    int pos = 0;
    while (pos < 16 && intervalos[pos] < prom ) pos++;
   
    String aux = pref + names[pos];
    String res = "\n<img src='" + dir + aux + "' width='10' height='10' />";
    return res;
  }

  @Override
  public String getFont() {
    return "";
  }
  
}
