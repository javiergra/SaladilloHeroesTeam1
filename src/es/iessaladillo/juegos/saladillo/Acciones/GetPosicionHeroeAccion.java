package es.iessaladillo.juegos.saladillo.Acciones;

import es.iessaladillo.juegos.saladillo.controller.Mapa;
import es.iessaladillo.juegos.saladillo.util.Posicion;

public class GetPosicionHeroeAccion implements Accion {
	Posicion posicion;
	
	 public GetPosicionHeroeAccion(Mapa mapa) {
		posicion = mapa.getPosicionHeroe();
	}

	@Override
	public Object execute() {
		return posicion;
	}

}
