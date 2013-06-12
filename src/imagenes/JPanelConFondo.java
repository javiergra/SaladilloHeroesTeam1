package imagenes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import es.iessaladillo.juegos.saladillo.controller.Grafico;
import es.iessaladillo.juegos.saladillo.controller.Mapa;
import es.iessaladillo.juegos.saladillo.util.Posicion;

public class JPanelConFondo extends JFrame {

    private Grafico[][] ArrayImagen = new Grafico[14][14];

    public JPanelConFondo() {

    	super("Heroes del Saladillo (v.1.0)");
        this.setSize(448, 470);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
        this.setLocationRelativeTo(null); 
    }

    public JPanelConFondo(Mapa mapa) {
    	
    	super("Heroes del Saladillo (v.1.0)");
    	
    	Grafico grafico;
    	
        this.setSize(448, 470);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
        
    
    	
        if (mapa != null) {
        	for (int i = 0; i < 14; i++){
        		for (int j = 0; j < 14; j++){

        				grafico = ImprimirMapa.convertirAGrafico(mapa.obtenerPosicion(new Posicion(i, j)));		 
        				 ArrayImagen[i][j] = grafico;
        		}
        	}         
        }
        
        repaint();
    }

    public JPanelConFondo(Grafico[][] ArrayImagen) {
        this.ArrayImagen = ArrayImagen;
    }

    public void setArrayImagen(Mapa mapa) {
    	Grafico grafico;
    	
        if (mapa != null) {
        	for (int i = 0; i < 14; i++){
        		for (int j = 0; j < 14; j++){
        			grafico = ImprimirMapa.convertirAGrafico(mapa.obtenerPosicion(new Posicion(i, j)));		 
   				 	ArrayImagen[i][j] = grafico;
        		}
        	}
            
        }

        repaint();	// actualizamos
    }

    public void setArrayImagen(Grafico[][] ArrayImagen) {
        this.ArrayImagen = ArrayImagen;

        repaint();
    }

    @Override
    public void paint(Graphics g) {
    	
    	Grafico grafico;
    	Graphics g2 = (Graphics2D)g;
    	g2.setColor(Color.black);
    	g2.fillRect(0, 0, 448, 448);//creo un rectangulo negro del tamaño de la ventana para fondo (no se ve)
        
    	Image imagen;
    	
    	try{
        	for (int i = 0; i < 14; i++){
        		for ( int j = 0; j < 14; j++){
        			grafico = ArrayImagen[i][j];
        			if (grafico.getImagenFondo() != null && grafico.getImagenFondo().getImagenFondo() != null)
        				g2.drawImage(grafico.getImagenFondo().getImagenFondo().getImagenPrincipal() , (i * 32), 22 + (j * 32), 32, 32, null);
        			if (grafico.getImagenFondo() != null)
        				g2.drawImage(grafico.getImagenFondo().getImagenPrincipal() , (i * 32), 22 + (j * 32), 32, 32, null);
        			g2.drawImage(grafico.getImagenPrincipal() , (i * 32), 22 + (j * 32), 32, 32, null);
        		}
        	}
    	}catch(NullPointerException o){}	// captura el primer NullPointerException. Solución temporal...
    }
    
}
