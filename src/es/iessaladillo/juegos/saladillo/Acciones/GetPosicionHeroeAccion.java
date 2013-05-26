package es.iessaladillo.juegos.saladillo.Acciones;

import es.iessaladillo.juegos.saladillo.facade.SaladilloFacade;
import es.iessaladillo.juegos.saladillo.util.Posicion;

public class GetPosicionHeroeAccion implements Accion {
	Posicion posicion;
	
	 public GetPosicionHeroeAccion() {
		posicion=SaladilloFacade.mapa.getPosicionHeroe();
	}

	@Override
	public Object execute() {
		return posicion;
	}

}
