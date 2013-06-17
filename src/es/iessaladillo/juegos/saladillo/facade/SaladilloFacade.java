package es.iessaladillo.juegos.saladillo.facade;


import imagenes.ImprimirMapa;
import imagenes.JPanelConFondo;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import es.iessaladillo.juegos.saladillo.Acciones.*;
import es.iessaladillo.juegos.saladillo.controller.*;
import es.iessaladillo.juegos.saladillo.model.delegate.SaladilloFacadeDelegate;
import es.iessaladillo.juegos.saladillo.music.MP3;
import es.iessaladillo.juegos.saladillo.util.*;

public class SaladilloFacade implements SaladilloFacadeDelegate {

	private Mapa mapaInicial = new Mapa();
	private Mapa mapa = new Mapa();
	private Entidad[] arrayentidades;
	private byte nivelACargar = 1;
	
	public Mapa getMapaInicial() {
		return mapaInicial;
	}

	public void setMapaInicial(Mapa mapaInicial) {
		this.mapaInicial = mapaInicial;
	}

	public Mapa getMapa() {
		return mapa;
	}

	public void setMapa(Mapa mapa) {
		this.mapa = mapa;
	}

	@Override
	public MapaInterface mapaFromEntidades(Entidad[] entidades) {
		Accion accion=new MapaFromEntidadesAccion(entidades);
		return (MapaInterface) accion.execute();
	}

	@Override
	public void cargarMapa(Entidad[] entidades) {
		mapa=(Mapa) mapaFromEntidades(entidades);
		mapaInicial=(Mapa) mapa.clone();
		
	}

	@Override
	public void cargarMapa(MapaInterface mapa) {
		mapa=(Mapa) mapa;
		mapaInicial=(Mapa) ((Mapa)mapa).clone();
	}

	@Override
	public MapaInterface mover(Direccion direccion) {
		Accion accion = new MoverAccion(mapa, direccion);
		return (MapaInterface) accion.execute();
	}

	@Override
	public MapaInterface reiniciarNivel() {
		Accion accion = new ReiniciarNivelAccion(mapaInicial);
		return (MapaInterface) accion.execute();
	}

	@Override
	public Posicion getPosicionHeroe() {
		Accion accion = new GetPosicionHeroeAccion(mapa);
		return (Posicion) accion.execute();
	}

	@Override
	public int diamantesEnMapa() {
		Accion accion=new DiamantesEnMapaAccion(mapa);
		return ((Integer) accion.execute()).intValue();
	}

	@Override
	public ConjuntoPosiciones posicionesAActualizar() {
		Accion accion = new PosicionesAActualizarAccion(mapa);
		return (ConjuntoPosiciones) accion.execute();
	}

	public static void main (String[] args){
		
		final SaladilloFacade fachada = new SaladilloFacade();
		
		ArrayList<Entidad> entidades =CargadorNiveles.cargarNivel(ImprimirMapa.cargarSiguienteNivel(fachada.nivelACargar));
		fachada.nivelACargar++;
		fachada.arrayentidades = ImprimirMapa.convertirAArray(entidades);		
		fachada.setMapa( (Mapa) fachada.mapaFromEntidades(fachada.arrayentidades) );
		
	    final JPanelConFondo ventanaPrincipal = new JPanelConFondo(fachada.mapa);
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
	        		fachada.mapa.setDiamantesEnMapa(0);
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
	        		fachada.mapa.setDiamantesEnMapa(0);
	        		fachada.nivelACargar--;
	        		ArrayList<Entidad> entidades =CargadorNiveles.cargarNivel(ImprimirMapa.cargarSiguienteNivel(fachada.nivelACargar));
            		fachada.nivelACargar++;
            		fachada.arrayentidades = ImprimirMapa.convertirAArray(entidades);	
            		fachada.setMapa( (Mapa) fachada.mapaFromEntidades(fachada.arrayentidades) );
            		fachada.mapa.setPosiciones(null);
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
	        	if (fachada.mapa.getDiamantesEnMapa() == 0){
            		JOptionPane.showMessageDialog(null, "¡Enhorabuena, has conseguido todos los diamantes del nivel " + (fachada.nivelACargar - 1) + "!");
            		ArrayList<Entidad> entidades =CargadorNiveles.cargarNivel(ImprimirMapa.cargarSiguienteNivel(fachada.nivelACargar));
            		fachada.nivelACargar++;
            		fachada.arrayentidades = ImprimirMapa.convertirAArray(entidades);	
            		fachada.setMapa( (Mapa) fachada.mapaFromEntidades(fachada.arrayentidades) );
            		fachada.mapa.setPosiciones(null);
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
