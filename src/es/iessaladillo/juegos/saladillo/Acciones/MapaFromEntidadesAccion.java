package es.iessaladillo.juegos.saladillo.Acciones;

import es.iessaladillo.juegos.saladillo.controller.Elementos;
import es.iessaladillo.juegos.saladillo.controller.Mapa;
import es.iessaladillo.juegos.saladillo.util.Entidad;
import es.iessaladillo.juegos.saladillo.util.Heroe;
import es.iessaladillo.juegos.saladillo.util.Posicion;

public class MapaFromEntidadesAccion implements Accion {
	Mapa mapa;
	Entidad[] entidades;

	public MapaFromEntidadesAccion(Entidad[] entidades) {
		this.entidades = entidades;
	}

	@Override
	public Object execute() {
		int i = 0;
		Elementos prueba = null;
		Posicion posicion = null;
		for (i = 0; i < 196; i++) {
			prueba.setNombreImagen(entidades[i].getTipo());
			posicion.setX(entidades[i].getX());
			posicion.setY(entidades[i].getY());
			// (((Heroe)(entidades[i].getTipo())).isHeroe(entidades[i].getTipo()));
			mapa.ponerElemento(posicion, prueba);
			return new Mapa(entidades);
		}
	}
}
