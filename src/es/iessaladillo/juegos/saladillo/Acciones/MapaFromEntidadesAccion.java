package es.iessaladillo.juegos.saladillo.Acciones;

import es.iessaladillo.juegos.saladillo.controller.Mapa;
import es.iessaladillo.juegos.saladillo.util.Entidad;

public class MapaFromEntidadesAccion implements Accion {

	Entidad [] entidades;
	
	public MapaFromEntidadesAccion(Entidad[] entidades) {
		this.entidades=entidades;
	}

	@Override
	public Object execute() {
		return new Mapa (entidades);
	}

}
