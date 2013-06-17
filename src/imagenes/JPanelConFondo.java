package imagenes;

import java.awt.AWTException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import es.iessaladillo.juegos.saladillo.controller.Grafico;
import es.iessaladillo.juegos.saladillo.controller.Mapa;
import es.iessaladillo.juegos.saladillo.music.MP3;
import es.iessaladillo.juegos.saladillo.util.Posicion;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class JPanelConFondo extends JFrame {

    private Grafico[][] ArrayImagen = new Grafico[14][14];
    private ArrayList<Posicion> posicionesARefrescar;
    private MP3 mp3;

    public JPanelConFondo() {

    	super("Heroes del Saladillo (v.1.0)");
        this.setSize(448, 510);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
        this.setLocationRelativeTo(null); 
    }

    public JPanelConFondo(Mapa mapa) {
    	
    	super("Heroes del Saladillo (v.1.0)");
    	
    	Grafico grafico;
    	
        this.setSize(448, 510);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
       
        final JButton reiniciarNivel = new JButton();
        reiniciarNivel.setBounds(20, 449, 126, 40);
        reiniciarNivel.setVisible(true);
        reiniciarNivel.setFocusable(false);
        reiniciarNivel.setText("Reiniciar nivel");
		
		final JButton musiquilla = new JButton();
		musiquilla.setBounds(156, 449, 126, 40);
		musiquilla.setVisible(true);
		musiquilla.setFocusable(false);
		musiquilla.setText("Música on/off");
		
		final JButton acercaDe = new JButton();
		acercaDe.setBounds(292, 449, 126, 40);
		acercaDe.setVisible(true);
		acercaDe.setFocusable(false);
		acercaDe.setText("Acerca de...");
		
		this.add(reiniciarNivel);
		this.add(musiquilla);
		this.add(acercaDe);
		
		reiniciarNivel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					Robot robot = new Robot();
					robot.keyPress(KeyEvent.VK_E);	// Presionamos la tecla E
				} catch (AWTException e) {
					e.printStackTrace();
				}
			}
		});
		
		musiquilla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Robot robot = new Robot();
					robot.keyPress(KeyEvent.VK_R);	// Presionamos la tecla R
				} catch (AWTException e) {
					e.printStackTrace();
				}
			}
		});
		
		acercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JDialog dialogo = new JDialog();
	            dialogo.setSize(320, 300);
	            dialogo.setResizable(false);
	            dialogo.setLocationRelativeTo(null);
	            dialogo.setVisible(true);
	            JLabel etiqueta = new JLabel();
	            dialogo.add(etiqueta);
	            etiqueta.setBorder(new EmptyBorder(10, 10, 10, 10));
	            etiqueta.setBounds(25, 25, 250, 280);
	            etiqueta.setVisible(true);
	            etiqueta.setVerticalAlignment(SwingConstants.TOP);
	            etiqueta.setText("<html><h3>&#09;Héroes del Saladillo</h3><br>&emsp;&emsp;Este simpático juego ha sido" +
	            				" diseñado y programado íntegramente por los alumnos de 1º de C.F.G.S. de" +
	            				" Desarrollo de aplicaciones multiplataforma del I.E.S. Saladillo de Algeciras." +
	            				"<p>&emsp;&emsp;Javi Naranjo, estudiante de la E.S.0., ha sido el diseñador gráfico del juego." +
	            				"<p>&emsp;&emsp;Una mención especial a Javier Graña y Eva Peralta, profesores del centro, sin" +
	            				" los que aprender hubiese sido más difícil e infinitamente más aburrido." +
	            				"<br><br>&#09;&#09;&emsp;&emsp;&emsp;Junio de 2013</html>");
				System.out.println("acerca de!");
			}
		});

    
    	
        if (mapa != null) {
        	for (int i = 0; i < 14; i++){
        		for (int j = 0; j < 14; j++){

        				grafico = ImprimirMapa.convertirAGrafico(mapa.obtenerPosicion(new Posicion(i, j)));		 
        				 ArrayImagen[i][j] = grafico;
        		}
        	}         
        }
        
        repaint();
        String filename = "src/es/iessaladillo/juegos/saladillo/music/juego.mp3";
		MP3 mp3 = new MP3(filename, true);
		this.mp3 = mp3;
		reproducirMp3();
        
    }

    public JPanelConFondo(Grafico[][] ArrayImagen) {
        this.ArrayImagen = ArrayImagen;
    }

    public void setArrayImagen(Mapa mapa) {
    	this.posicionesARefrescar = mapa.getPosiciones();
    	Grafico grafico;
    	Posicion posicion;
    	
    	if (posicionesARefrescar != null){
    		for (int i = 0; i < mapa.getPosiciones().size(); i++){
    			posicion = posicionesARefrescar.get(i);
    			grafico = ImprimirMapa.convertirAGrafico(mapa.obtenerPosicion(posicion));		 
				ArrayImagen[posicion.getX()][posicion.getY()] = grafico;
    		}
    	}
    	else {
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
    
    public void setMp3(MP3 mp3){
    	this.mp3 = mp3;
    }
    
    public MP3 getMp3(){
    	return mp3;
    }
    
    public void reproducirMp3(){
    	if (mp3 != null)
    		mp3.play();
    }
    
    public void detenerMp3(){
    	mp3.close();
    }

    @Override
    public void paint(Graphics g) {
    	
    	Grafico grafico;
    	Graphics g2 = (Graphics2D)g;
    	Posicion posicion;
    	
    	try{
    		
    		if ( posicionesARefrescar != null){
    			
    			for ( int i = 0; i < posicionesARefrescar.size(); i++){
    				posicion = posicionesARefrescar.get(i);
        			grafico = ArrayImagen[posicion.getX()][posicion.getY()];
        			if (grafico.getImagenFondo() != null && grafico.getImagenFondo().getImagenFondo() != null)
        				g2.drawImage(grafico.getImagenFondo().getImagenFondo().getImagenPrincipal() , (posicion.getX() * 32), 22 + (posicion.getY() * 32), 32, 32, null);
        			if (grafico.getImagenFondo() != null)
        				g2.drawImage(grafico.getImagenFondo().getImagenPrincipal() , (posicion.getX() * 32), 22 + (posicion.getY() * 32), 32, 32, null);
        			g2.drawImage(grafico.getImagenPrincipal() , (posicion.getX() * 32), 22 + (posicion.getY() * 32), 32, 32, null);
    			}
    		}
    		else{
    		super.paint(g);		// Pinta los botones
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
    		}
    	}catch(NullPointerException o){}	// captura el primer NullPointerException. Solución temporal...
    }

	
    
}
