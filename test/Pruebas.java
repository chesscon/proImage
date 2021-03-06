
import base.Manipulacion;
import base.ProImageException;
import filtros.AuxDomino;
import filtros.AuxNaipes;
import filtros.AuxSemitono;
import filtros.Convolucion;
import filtros.FiltrosRGB;
import filtros.FiltrosVarios;
import filtros.SemiPuntos;
import filtros.Semitonos;
import java.io.File;
import java.io.IOException;


/**
 *
 * @author oscahern
 */
public class Pruebas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ProImageException {
        //testImagenAleatoria();
        //FiltrosRGB.filtroGris11("imgs/pinguinos.jpg");
        //testReducir1();
        //Manipulacion.generarImagen("imgs/pinguinoesGris11.jpg");
        //testConvolusionIdentidad();
        //testConvolusiones();
        //testHistograma();
        //testBlending();
       //testSepia();
      //testLuzNegra();
      testSemitonos();
    }
    
    public static void testReducir1() throws IOException, ProImageException {
        Manipulacion.generarImagen("imgs/pinguinosReduc1_800-600.jpg", 
                transformaciones.TamanioImg.reducir("imgs/pinguinos.jpg", 800, 600));
    }
    
    public static void testImagenAleatoria() throws IOException {
        Manipulacion.generarImagenAleatoria("imgs/aletoria500-500.jpg", 500, 500);
    }
    
    public static void testFiltrosRGB() throws IOException {
        // EL Color se debe ingresar en la forma:
        // alfaRGB, 0x00RRGGBB
        /*
        FiltrosRGB.generarImagen("imgs/pinguinosTeal.jpg", 
                FiltrosRGB.filtroRGB("imgs/pinguinos.jpg", 0x00008080) );
        FiltrosRGB.generarImagen("imgs/pinguinosAzul.jpg",
                FiltrosRGB.filtroAzul("imgs/pinguinos.jpg"));
        FiltrosRGB.generarImagen("imgs/pinguinosRojo.jpg",
                FiltrosRGB.filtroRojo("imgs/pinguinos.jpg"));
        FiltrosRGB.generarImagen("imgs/pinguinosVerde.jpg",
                FiltrosRGB.filtroVerde("imgs/pinguinos.jpg"));
      */
        
        /*
        FiltrosRGB.generarImagen("imgs/PinguinoContraste1.jpg", 
                FiltrosRGB.filtroAltoContraste1("imgs/Pinguino.jpg"));
        FiltrosRGB.generarImagen("imgs/PinguinoConstraste2.jpg", 
                FiltrosRGB.filtroAltoContraste2("imgs/Pinguino.jpg"));
        */
        
        /*FiltrosRGB.generarImagen("imgs/pinguinosGris2Brillo125.jpg", 
                FiltrosRGB.filtroAlterarBrillo("imgs/pinguinosGris2.jpg", 125));*/
        FiltrosRGB.generarImagen("imgs/pinguinosMosaico100x79.jpg", 
                FiltrosRGB.filtroMosaico("imgs/pinguinos.jpg", 100,79));
    }
    
    public static void testConvolusionIdentidad() throws IOException {
        float matId[][] = new float[3][3];
        matId[1][1] = 1;
        Manipulacion.generarImagen("imgs/girasolesConvId.jpg", 
                Convolucion.aplicarMatriz("imgs/girasoles.jpg", matId, true));
    }
    
    public static void testConvolusiones() throws IOException {
        Manipulacion.generarImagen("imgs/girasolesBlur.jpg", 
                Convolucion.aplicarMatriz("imgs/girasoles.jpg", Convolucion.BLUR, true));
        Manipulacion.generarImagen("imgs/girasolesBlur2.jpg", 
                Convolucion.aplicarMatriz("imgs/girasoles.jpg", Convolucion.BLUR2, true));
        Manipulacion.generarImagen("imgs/girasolesEdgeFilter.jpg", 
                Convolucion.aplicarMatriz("imgs/girasoles.jpg", Convolucion.EDGE_FILTER, true));
        Manipulacion.generarImagen("imgs/girasolesEdgeFilter2.jpg", 
                Convolucion.aplicarMatriz("imgs/girasoles.jpg", Convolucion.EDGE_FILTER2, true));
        Manipulacion.generarImagen("imgs/girasolesEmboss.jpg", 
                Convolucion.aplicarMatriz("imgs/girasoles.jpg", Convolucion.EMBOSS, true));
        Manipulacion.generarImagen("imgs/girasolesSharpen.jpg", 
                Convolucion.aplicarMatriz("imgs/girasoles.jpg", Convolucion.SHARPEN, true));
        Manipulacion.generarImagen("imgs/girasolesSharpen2.jpg", 
                Convolucion.aplicarMatriz("imgs/girasoles.jpg", Convolucion.SHARPEN2, true));
        Manipulacion.generarImagen("imgs/girasolesSharpen3.jpg", 
                Convolucion.aplicarMatriz("imgs/girasoles.jpg", Convolucion.SHARPEN3, true));
    }
    
    public static void testHistograma() throws IOException, ProImageException {
        Manipulacion.generarImagen("imgs/pinguinosAcuarela10.jpg", 
                filtros.Histograma.acuarela("imgs/pinguinos.jpg"));
    }
    
    public static void testBlending() throws IOException, ProImageException {
        Manipulacion.generarImagen("imgs/blending100.jpg", 
                filtros.Combinacion.blending("imgs/pinguino.jpg", 
                        "imgs/girasoles.jpg", 1));
        
        Manipulacion.generarImagen("imgs/blending0.jpg", 
                filtros.Combinacion.blending("imgs/pinguino.jpg", 
                        "imgs/girasoles.jpg", 0));
        
        /*Manipulacion.generarImagen("imgs/blending25.jpg", 
                filtros.Combinacion.blending("imgs/pinguino.jpg", 
                        "imgs/girasoles.jpg", 0.25));
        */
        /*Manipulacion.generarImagen("imgs/blending50.jpg", 
                filtros.Combinacion.blending("imgs/pinguino.jpg", 
                        "imgs/girasoles.jpg", 0.5));
        */
    }
    
    public static void testSepia() throws IOException, ProImageException {
      Manipulacion.generarImagen("imgs/sepia10.jpg", 
          FiltrosVarios.Sepia("imgs/pinguino.jpg", 10));
      
      Manipulacion.generarImagen("imgs/sepia50.jpg", 
          FiltrosVarios.Sepia("imgs/pinguino.jpg", 50));
      
      Manipulacion.generarImagen("imgs/sepia80.jpg", 
          FiltrosVarios.Sepia("imgs/pinguino.jpg", 80));
    }
    
    public static void testLuzNegra() throws IOException, ProImageException {
      Manipulacion.generarImagen("imgs/luzNegra2.jpg", 
          FiltrosVarios.luzNegra("imgs/girasoles.jpg", 2));
      Manipulacion.generarImagen("imgs/luzNegra4.jpg", 
          FiltrosVarios.luzNegra("imgs/girasoles.jpg", 4));
    }
    
    public static void testSemitonos() throws IOException, ProImageException { 
      
      String file = Semitonos.cuadricular("imgs/im-22704.jpg", 4, 4, new AuxNaipes());
      File tmp = new File(file);
      File f = new File("prueba5.html");
      tmp.renameTo(f);
      
      String file2 = Semitonos.cuadricular("imgs/im-22704.jpg", 4, 4, new AuxDomino());
      File tmp2 = new File(file2);
      File f2 = new File("prueba6.html");
      tmp2.renameTo(f2);
    }
    
}
