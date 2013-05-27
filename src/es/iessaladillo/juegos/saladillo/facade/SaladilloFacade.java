package es.iessaladillo.juegos.saladillo.facade;

import es.iessaladillo.juegos.saladillo.Acciones.*;
import es.iessaladillo.juegos.saladillo.controller.*;
import es.iessaladillo.juegos.saladillo.model.delegate.SaladilloFacadeDelegate;
import es.iessaladillo.juegos.saladillo.util.*;

public class SaladilloFacade implements SaladilloFacadeDelegate {

	public static Mapa mapa,mapaInicial;// He cambiado a estático porque quiero dejar los mapas en memoria
										// y su acceso se vuelve más rápido.
	
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
		SaladilloFacade.mapa=(Mapa) mapa;
		mapaInicial=(Mapa) ((Mapa)mapa).clone();
	}

	@Override
	public MapaInterface mover(Direccion direccion) {
		Posicion posicionHeroe, posicion;
		Dibujable heroe, elemento;
		//A la hora de mover, se podría aprovechar y enviar la posicion antigua del heroe al
		//ArrayList de ConjuntoPosiciones para el historial de movimiento. Se podría hacer llamando al
		//metodo posicionesAActualizar()?
		posicion = mapa.getPosicionHeroe();
		heroe = mapa.obtenerPosicion(posicion);
		if (mapa.sePuedeMover(posicion, direccion)){
			
			elemento = heroe.getFondo();
			mapa.ponerElemento(posicion, null);
			mapa.ponerElemento(posicion, elemento);
		
			posicionHeroe = mapa.siguientePosicion(posicion, direccion);	
			elemento =mapa.obtenerPosicion(posicionHeroe);
			
			if (elemento.getNombreClase().equals("ForegroundMovil")){	// Si hay pelota...
				mapa.ponerElemento(posicionHeroe, elemento.getFondo());
				posicion = mapa.siguientePosicion(posicionHeroe, direccion);
				mapa.ponerElemento(posicion, elemento);
			}
			mapa.ponerElemento(posicionHeroe, heroe);
		}
		return mapa;
	}

	@Override
	public MapaInterface reiniciarNivel() { //Cambio hecho en 26/05/2013
		Accion accion = new ReiniciarNivelAccion();
		return (MapaInterface) accion.execute();
	}

	@Override
	public Posicion getPosicionHeroe() {//Cambio hecho en 26/05/2013
		Accion accion = new GetPosicionHeroeAccion();
		return (Posicion) accion.execute();
	}

	@Override
	public int diamantesEnMapa() {
		Accion accion=new DiamantesEnMapaAccion();
		return ((Integer) accion.execute()).intValue();
	}

	@Override
	public ConjuntoPosiciones posicionesAActualizar() {
		Accion accion = new PosicionesAActualizarAccion();
		return (ConjuntoPosiciones) accion.execute();
	}

}
