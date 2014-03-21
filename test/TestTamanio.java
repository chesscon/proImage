
import base.Manipulacion;
import base.ProImageException;
import filtros.Convolucion;
import java.io.IOException;
import transformaciones.TamanioImg;


/**
 *
 * @author oscahern
 */
public class TestTamanio {
    
    public static void main (String args[]) throws IOException, ProImageException {
        testTamanio1();
    }

    /**
     * Realiza la prueba utilizando la técnica del filtro mosaico para
     * reducir el tamaño de una imagen
     */
    public static void testTamanio1() throws IOException, ProImageException {
        Manipulacion.generarImagen("imgs/pinguinos600_393.jpg", 
                TamanioImg.reducir1("imgs/pinguinos.jpg", 600, 393));
    }
}
