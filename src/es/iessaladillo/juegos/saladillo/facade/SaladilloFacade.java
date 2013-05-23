package es.iessaladillo.juegos.saladillo.facade;

import es.iessaladillo.juegos.saladillo.Acciones.*;
import es.iessaladillo.juegos.saladillo.controller.MapaInterface;
import es.iessaladillo.juegos.saladillo.model.delegate.SaladilloFacadeDelegate;
import es.iessaladillo.juegos.saladillo.util.*;

public class SaladilloFacade implements SaladilloFacadeDelegate {

	int diamantes;
	
	@Override
	public MapaInterface mapaFromEntidades(Entidad[] entidades) {
		Accion accion=new MapaFromEntidadesAccion(entidades);
		return (MapaInterface) accion.execute();
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
		Accion accion=new DiamantesEnMapaAccion(diamantes);
		return ((Integer) accion.execute()).intValue();
	}

	@Override
	public ConjuntoPosiciones posicionesAActualizar() {
		// TODO Auto-generated method stub
		return null;
	}

}
