package filtros;

import base.Manipulacion;
import static base.Manipulacion.cargarImagen;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 *
 * @author constantino92
 */
public class Convolucion extends Manipulacion {
    
    private static float[][] matConv;
    
    public static float BLUR[][] = { {0,    0.2f, 0},
                                         {0.2f, 0.2f, 0.2f},
                                         {0,    0.2f, 0} };
    
    public static float BLUR2[][] = { {0,0,1,0,0},
                                          {0,1,1,1,0},
                                          {1,1,1,1,1},
                                          {0,1,1,1,0},
                                          {0,0,1,0,0}};
    
    public static float EDGE_FILTER[][] = { {0,0,0,0,0},
                                            {0,0,0,0,0},
                                            {-1,-1,2,0,0},
                                            {0,0,0,0,0},
                                            {0,0,0,0,0}};
    
    public static float EDGE_FILTER2[][] = { {0,0,-1,0,0},
                                            {0,0,-1,0,0},
                                            {0,0,4,0,0},
                                            {0,0,-1,0,0},
                                            {0,0,-1,0,0}};
    
    public static float SHARPEN[][] = { {-1,  -1, -1},
                                         {-1, 9, -1},
                                         {-1, -1, -1} };
    
    public static float SHARPEN2[][] = { {-1,-1,-1,-1,-1},
                                         {-1, 2, 2, 2,-1},
                                         {-1, 2, 8, 2,-1},
                                         {-1, 2, 2, 2,-1},
                                         {-1,-1,-1,-1,-1}};
    
    public static float SHARPEN3[][] = { {1, 1, 1},
                                         {1,-7, 1},
                                         {1, 1, 1} };
    
    public static float EMBOSS[][] = { {-1, -1, 0},
                                         {-1,0, 1},
                                         {0, 1, 1} };
    
    public static BufferedImage aplicarMatriz(String name, float mat[][], 
            boolean bFactor) 
            throws IOException {
        if (mat == null) return null;
        cargarImagen(name);
        matConv = mat;
        //int topeAncho = matConv.length/2; 
        //int topeAlto = matConv[0].length/2; 
        
        for (int i = 0; i < anchoImg; i++) {
            for (int j = 0; j < altoImg; j++) {
                convolucionarPixel(i, j, bFactor);
            }
        }
        buffOutImg.setData(dstImg);
        return buffOutImg;
    }
    
    private static void convolucionarPixel(int renglon, int columna, boolean b) {
        //variable que sirve de control para evitar que se desborde la mascara de la matriz
        int topeAncho = matConv.length/2; 
        int topeAlto = matConv[0].length/2; 
        int[] pixel = new int[3];
        float factor =0;
        int []auxPixel = null;
        int aux1, aux2;
        for(int i=0; i < matConv.length; i++){
            for(int j=0; j< matConv[0].length; j++){
                factor+= matConv[i][j];
                aux1 = renglon-topeAncho+i;
                aux2 = columna-topeAlto+j;
                if (aux1<0 || aux2<0 || aux1>=anchoImg || aux2>=altoImg) {
                    auxPixel = new int[3];
                } else {
                    auxPixel = rasterImg.getPixel(aux1, aux2, auxPixel);
                }
                pixel[0] +=  matConv[i][j]*auxPixel[0];
                pixel[1] +=  matConv[i][j]*auxPixel[1];
                pixel[2] +=  matConv[i][j]*auxPixel[2];
            }
        }
        
        if(b && factor>0){
            pixel[0] /=factor;            
            pixel[1] /=factor;
            pixel[2] /=factor;
        }
        /*
        pixel[0] = pixel[0] > 254 ? 255 :pixel[0];
        pixel[1] = pixel[1] > 254 ? 255 :pixel[2];
        pixel[2] = pixel[2] > 254 ? 255 :pixel[1];
        
        pixel[0] = pixel[0] < 0 ? 0 :pixel[0];
        pixel[1] = pixel[1] < 0 ? 0 :pixel[2];
        pixel[2] = pixel[2] < 0 ? 0 :pixel[1];
        */
        dstImg.setSample(renglon, columna, 0, pixel[0]); // Rojo
        dstImg.setSample(renglon, columna, 1, pixel[1]); // Verde
        dstImg.setSample(renglon, columna, 2, pixel[2]); // Azul
        
    }
    
    public static BufferedImage convId(String name) 
            throws IOException {
        return convBrillo(name, 1);
    }
    
    public static BufferedImage convBrillo(String name, int brillo) 
            throws IOException {
        float mat[][] = new float[3][3];
        mat[1][1] = brillo;
        return aplicarMatriz(name, mat, false);
    }
    
    public static BufferedImage convBlur(String name) 
            throws IOException {
        return aplicarMatriz(name, BLUR, true);
    }
    
}
