package es.iessaladillo.juegos.saladillo.facade;


import imagenes.ImprimirMapa;
import imagenes.JPanelConFondo;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import es.iessaladillo.juegos.saladillo.Acciones.*;
import es.iessaladillo.juegos.saladillo.controller.*;
import es.iessaladillo.juegos.saladillo.interfaz.util.VariablesGlobales;
import es.iessaladillo.juegos.saladillo.model.delegate.SaladilloFacadeDelegate;
import es.iessaladillo.juegos.saladillo.util.*;

public class SaladilloFacade implements SaladilloFacadeDelegate {

	private Mapa mapaInicial = new Mapa();
	private Mapa mapa = new Mapa();
	
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
		Accion accion=new DiamantesEnMapaAccion();
		return ((Integer) accion.execute()).intValue();
	}

	@Override
	public ConjuntoPosiciones posicionesAActualizar() {
		Accion accion = new PosicionesAActualizarAccion(mapa);
		return (ConjuntoPosiciones) accion.execute();
	}

	public static void main (String[] args){
		int i=0;
		Direccion direccion;

		Entidad[] arrayentidades;
		
		ArrayList<Entidad> entidades =CargadorNiveles.cargarNivel("src/1.lvl");

		arrayentidades = ImprimirMapa.convertirAArray(entidades);
		
		final SaladilloFacade fachada = new SaladilloFacade();
		fachada.setMapa( (Mapa) fachada.mapaFromEntidades(arrayentidades) );
		 
	    final JPanelConFondo ventanaPrincipal = new JPanelConFondo(fachada.mapa);
	    ventanaPrincipal.addKeyListener(new KeyListener()
	    {									// Clase interna implementando la interfaz KeyListener
	       
	        @Override 
	        public void keyPressed(KeyEvent e) // tecla presionada (en ese momento)
	        {
	        	Direccion direccion = null;
	        	int c = e.getKeyCode ();
	            if (c==KeyEvent.VK_UP) {     
	            	direccion = Direccion.UP;
	            } else if(c==KeyEvent.VK_DOWN) {      
	            	direccion = Direccion.DOWN;
	            } else if(c==KeyEvent.VK_LEFT) {      
	            	direccion = Direccion.LEFT;
	            	new Movimiento(fachada.mapa, Direccion.LEFT); 
	            } else if(c==KeyEvent.VK_RIGHT) {   
	            	direccion = Direccion.RIGHT;
	            	new Movimiento(fachada.mapa, Direccion.RIGHT);  
	            }
	            Movimiento movemos = new Movimiento(fachada.mapa, direccion);
	            ventanaPrincipal.setArrayImagen( (Mapa) movemos.siguienteMovimiento() );

	        }
	        @Override
	        public void keyReleased(KeyEvent e)	// tecla presionada (la última, y que ahora está suelta)
	        {
	        	if (VariablesGlobales.DIAMANTES == 0){
            		JOptionPane.showMessageDialog(null, "¡Enhorabuena, has conseguido todos los diamantes!");
            		System.exit(0);				// Salimos del juego (esta forma no le gusta a Eva :)
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
