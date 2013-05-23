package es.iessaladillo.juegos.saladillo.facade;

import es.iessaladillo.juegos.saladillo.Acciones.*;
import es.iessaladillo.juegos.saladillo.controller.Dibujable;
import es.iessaladillo.juegos.saladillo.controller.Mapa;
import es.iessaladillo.juegos.saladillo.controller.MapaInterface;
import es.iessaladillo.juegos.saladillo.model.delegate.SaladilloFacadeDelegate;
import es.iessaladillo.juegos.saladillo.util.*;

public class SaladilloFacade implements SaladilloFacadeDelegate {

	Mapa mapa,mapaInicial;
	
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
		this.mapa=(Mapa) mapa;
		mapaInicial=(Mapa) ((Mapa)mapa).clone();
	}

	@Override
	public MapaInterface mover(Direccion direccion) {
		Posicion posicion;
		Dibujable heroe;
		//A la hora de mover, se podría aprovechar y enviar la posicion antigua del heroe al
		//ArrayList de ConjuntoPosiciones para el historial de movimiento. Se podría hacer llamando al
		//metodo posicionesAActualizar()?
		posicion = mapa.getPosicionHeroe();
		heroe = mapa.obtenerPosicion(posicion);
		
		if (direccion == Direccion.UP)
			posicion.setY(posicion.getY() + 1);
		else if (direccion == Direccion.DOWN)
			posicion.setY(posicion.getY() - 1);
		else if (direccion == Direccion.LEFT)
			posicion.setX(posicion.getX() - 1);
		else
			posicion.setX(posicion.getX() + 1);
		
		mapa.ponerElemento(posicion, heroe);
		
		return mapa;
	}

	@Override
	public MapaInterface reiniciarNivel() {
		return mapaInicial;
	}

	@Override
	public Posicion getPosicionHeroe() {
		return mapa.getPosicionHeroe();
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
