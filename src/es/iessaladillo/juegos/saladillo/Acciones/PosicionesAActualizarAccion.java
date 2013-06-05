package es.iessaladillo.juegos.saladillo.Acciones;

import java.util.ArrayList;

import es.iessaladillo.juegos.saladillo.controller.Mapa;
import es.iessaladillo.juegos.saladillo.util.Posicion;

public class PosicionesAActualizarAccion implements Accion {
	
	ArrayList<Posicion> posiciones = new ArrayList<Posicion>();
	
	public PosicionesAActualizarAccion (Mapa mapa){
			posiciones = mapa.getPosiciones();
	}

	@Override
	public Object execute() {
		
		return posiciones;
	}

}
