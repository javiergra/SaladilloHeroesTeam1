package es.iessaladillo.juegos.saladillo.Acciones;

import java.util.ArrayList;
import es.iessaladillo.juegos.saladillo.util.Posicion;

public class PosicionesAActualizarAccion implements Accion {
	
	ArrayList<Posicion> posiciones = new ArrayList<Posicion>();
	
	public PosicionesAActualizarAccion (ArrayList<Posicion> getPosiciones()){
			posiciones = getPosiciones();
	}
		// ¿Qué mapa es el verdadero?
	@Override
	public Object execute() {
		
		return null;
	}

}
