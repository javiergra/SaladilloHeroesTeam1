package es.iessaladillo.juegos.saladillo.facade;

import es.iessaladillo.juegos.saladillo.controller.MapaInterface;
import es.iessaladillo.juegos.saladillo.model.delegate.SaladilloFacadeDelegate;
import es.iessaladillo.juegos.saladillo.util.ConjuntoPosiciones;
import es.iessaladillo.juegos.saladillo.util.Direccion;
import es.iessaladillo.juegos.saladillo.util.Entidad;
import es.iessaladillo.juegos.saladillo.util.Posicion;

public class SaladilloFacade implements SaladilloFacadeDelegate {

	@Override
	public MapaInterface mapaFromEntidades(Entidad[] entidades) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cargarMapa(Entidad[] entidades) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cargarMapa(MapaInterface mapa) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MapaInterface mover(Direccion direccion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MapaInterface reiniciarNivel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Posicion getPosicionHeroe() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int diamantesEnMapa() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ConjuntoPosiciones posicionesAActualizar() {
		// TODO Auto-generated method stub
		return null;
	}

}
