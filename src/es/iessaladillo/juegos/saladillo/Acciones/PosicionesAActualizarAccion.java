package es.iessaladillo.juegos.saladillo.Acciones;

import java.util.ArrayList;

import es.iessaladillo.juegos.saladillo.controller.Mapa;
import es.iessaladillo.juegos.saladillo.util.ConjuntoPosiciones;
import es.iessaladillo.juegos.saladillo.util.Posicion;

public class PosicionesAActualizarAccion implements Accion {
	
	ArrayList<Posicion> posiciones = new ArrayList<Posicion>();
	
	public PosicionesAActualizarAccion (Mapa mapa){
			posiciones = mapa.getPosiciones();
	}

	@Override
	public Object execute() {
		ConjuntoPosiciones conjunto = new ConjuntoPosiciones();
		
		for (int i = 0; i < posiciones.size(); i++){
			conjunto.anhadirPosicion(posiciones.get(i));
		}
		
		return conjunto;
	}

}
