
import base.Manipulacion;
import base.ProImageException;
import filtros.Combinacion;
import filtros.Convolucion;
import java.io.IOException;
import transformaciones.TamanioImg;


/**
 *
 * @author oscahern
 */
public class TestTamanio {
    
    public static void main (String args[]) throws IOException, ProImageException {
        //testTamanio1();
        //testCombinarVertical();
        testCombinarHorizontal();
    }

    /**
     * Realiza la prueba utilizando la técnica del filtro mosaico para
     * reducir el tamaño de una imagen
     */
    public static void testTamanio1() throws IOException, ProImageException {
        Manipulacion.generarImagen("imgs/pinguinos600_393.jpg", 
                TamanioImg.reducir("imgs/pinguinos.jpg", 600, 393));
    }

    private static void testCombinarVertical() throws IOException, ProImageException {
        Manipulacion.generarImagen("imgs/pinguinos_com2Vertical.jpg", 
                Combinacion.combinarVertical("imgs/pinguinos.jpg", "imgs/pinguinos.jpg", 600, 393));
    }

    private static void testCombinarHorizontal() throws IOException, ProImageException {
        Manipulacion.generarImagen("imgs/ping" + "uinos_com2Horizontal.jpg", 
                Combinacion.combinarHorizontal("imgs/pinguinos.jpg", "imgs/pinguinos.jpg", 600, 393));
    }
}
