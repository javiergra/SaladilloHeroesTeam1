package es.iessaladillo.juegos.saladillo.facade;

import imagenes.ImprimirMapa;
import imagenes.JPanelConFondo;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import es.iessaladillo.juegos.saladillo.controller.Mapa;
import es.iessaladillo.juegos.saladillo.music.MP3;
import es.iessaladillo.juegos.saladillo.util.CargadorNiveles;
import es.iessaladillo.juegos.saladillo.util.Direccion;
import es.iessaladillo.juegos.saladillo.util.Entidad;

public class Main {

	/**
	 * @param args
	 */
public static void main (String[] args){
		
		final SaladilloFacade fachada = new SaladilloFacade();
		
		ArrayList<Entidad> entidades =CargadorNiveles.cargarNivel(ImprimirMapa.cargarSiguienteNivel(fachada.getNivelACargar()));
		fachada.setNivelACargar((byte) (fachada.getNivelACargar() + 1));
		fachada.setArrayentidades( ImprimirMapa.convertirAArray(entidades) );		
		fachada.setMapa( (Mapa) fachada.mapaFromEntidades(fachada.getArrayentidades()) );
		
	    final JPanelConFondo ventanaPrincipal = new JPanelConFondo(fachada.getMapa());
	    ventanaPrincipal.setResizable(false);
	    ventanaPrincipal.addKeyListener(new KeyListener()
	    {									// Clase interna implementando la interfaz KeyListener
	       
	        @Override 
	        public void keyPressed(KeyEvent e) // tecla presionada (en ese momento)
	        {
	        	int diamantes;
	        	Direccion direccion = null;
	        	
	        	diamantes = fachada.diamantesEnMapa();
	        	
	        	int c = e.getKeyCode ();
	        	
	            if (c==KeyEvent.VK_UP) {     
	            	direccion = Direccion.UP;
	            	ventanaPrincipal.setArrayImagen( (Mapa) fachada.mover(direccion) );
	            } else if(c==KeyEvent.VK_DOWN) {      
	            	direccion = Direccion.DOWN;
	            	ventanaPrincipal.setArrayImagen( (Mapa) fachada.mover(direccion) );
	            } else if(c==KeyEvent.VK_LEFT) {      
	            	direccion = Direccion.LEFT;
	            	ventanaPrincipal.setArrayImagen( (Mapa) fachada.mover(direccion) );
	            } else if(c==KeyEvent.VK_RIGHT) {   
	            	direccion = Direccion.RIGHT;
	            	ventanaPrincipal.setArrayImagen( (Mapa) fachada.mover(direccion) );
	        	} else if(c==81) {   
	        		fachada.getMapa().setDiamantesEnMapa(0);
	        	} else if(c==82) {   					// música on/off.
	        		if (ventanaPrincipal.getMp3() != null){
	        			ventanaPrincipal.detenerMp3();
	        			ventanaPrincipal.setMp3(null);
	        		}
	        		else{
	        			String filename = "src/es/iessaladillo/juegos/saladillo/music/juego.mp3";
	        			MP3 mp3 = new MP3(filename, true);
	        			ventanaPrincipal.setMp3(mp3);
	        			ventanaPrincipal.reproducirMp3();
	        		}
	        	} else if(c==69) {   					// Reinicio el nivel.
	        		fachada.getMapa().setDiamantesEnMapa(0);
	        		fachada.setNivelACargar((byte) (fachada.getNivelACargar() - 1));
	        		ArrayList<Entidad> entidades =CargadorNiveles.cargarNivel(ImprimirMapa.cargarSiguienteNivel(fachada.getNivelACargar()));
            		fachada.setNivelACargar((byte) (fachada.getNivelACargar() + 1));
            		fachada.setArrayentidades( ImprimirMapa.convertirAArray(entidades) );	
            		fachada.setMapa( (Mapa) fachada.mapaFromEntidades(fachada.getArrayentidades()) );
            		fachada.getMapa().setPosiciones(null);
            		ventanaPrincipal.setArrayImagen(fachada.getMapa());
	        	} 
	            
	            if (diamantes != fachada.diamantesEnMapa()){
					String filename = "src/es/iessaladillo/juegos/saladillo/music/diamante.mp3";
		            MP3 mp3d = new MP3(filename);
		            mp3d.play();
	            }

	        }
	        @Override
	        public void keyReleased(KeyEvent e)	// tecla presionada (la última, y que ahora está suelta)
	        {
	        	//System.out.println(e);
	        	if (fachada.getMapa().getDiamantesEnMapa() == 0){
            		JOptionPane.showMessageDialog(null, "¡Enhorabuena, has conseguido todos los diamantes del nivel " + (fachada.getNivelACargar() - 1) + "!");
            		ArrayList<Entidad> entidades =CargadorNiveles.cargarNivel(ImprimirMapa.cargarSiguienteNivel(fachada.getNivelACargar()));
            		fachada.setNivelACargar((byte) (fachada.getNivelACargar() + 1));
            		fachada.setArrayentidades( ImprimirMapa.convertirAArray(entidades) );	
            		fachada.setMapa( (Mapa) fachada.mapaFromEntidades(fachada.getArrayentidades()) );
            		fachada.getMapa().setPosiciones(null);
            		ventanaPrincipal.setArrayImagen(fachada.getMapa());
	        	}
	        }
	        
	        @Override
	        public void keyTyped(KeyEvent e)
	        {
	        	//
	           }
	    });
	                
	               

		
		
		
	}

}
