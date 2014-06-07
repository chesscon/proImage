package filtros;

import base.Manipulacion;
import base.ProImageException;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import transformaciones.TamanioImg;

/**
 *
 * @author oscahern
 */
public class MosaicoRecursivo implements AuxSemitono {

  public String dir = "recur/";

  private String nameFile;
  
  private float propor;
  
  private int nAncho;
  
  private int nAlto;

  public MosaicoRecursivo(String nameFile, float propor) {
    try {
      this.propor = Math.abs(propor);
      this.nameFile = nameFile;
      Manipulacion.cargarImagen(nameFile);
      this.nAlto = (int) (propor*Manipulacion.obtenerAlto());
      this.nAlto = this.nAlto > 50 ? 50 : nAlto;
      this.nAncho = (int) (propor*Manipulacion.obtenerAancho());
      this.nAncho = this.nAncho > 50 ? 50 : 100;
      Manipulacion.generarImagen("aux-" + nameFile, TamanioImg.reducir(nameFile, nAncho, nAlto));
      this.nameFile = "aux-" + nameFile;
      File tmp = new File(dir);
      if (!tmp.exists()) {
        tmp.mkdir();
      }
      String path = nameFile;
      String file = path.substring(path.lastIndexOf('/') + 1, path.lastIndexOf('.')) + "/";
      
      File tmp2 = new File(dir + file + "/");
      if (!tmp2.exists()) {
        tmp2.mkdir();
      }
      dir = dir + file + "/";
    } catch (IOException ex) {
      Logger.getLogger(MosaicoRecursivo.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ProImageException ex) {
      Logger.getLogger(MosaicoRecursivo.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @Override
  public String mapear(int red, int green, int blue) {
    String auxName = dir + red + "-" + green + "-" + blue + ".jpg";
    File f = new File(auxName);
    
    if (!f.exists()) {
      try {
        Color c = new Color(red, green, blue);
        Manipulacion.generarImagen(f, FiltrosRGB.filtroRGB(nameFile, c.getRGB()));
        
      } catch (IOException ex) {
        Logger.getLogger(MosaicoRecursivo.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    String res = "<img src='" + dir + auxName + "' width='' height='10' />";
    return res;
  }

  @Override
  public String getFont() {
    return "";
  }
}
