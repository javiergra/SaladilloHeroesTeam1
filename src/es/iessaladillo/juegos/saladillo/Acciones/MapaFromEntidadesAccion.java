package es.iessaladillo.juegos.saladillo.Acciones;

import es.iessaladillo.juegos.saladillo.controller.Elementos;
import es.iessaladillo.juegos.saladillo.controller.Mapa;
import es.iessaladillo.juegos.saladillo.util.Background;
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
			posicion=new Posicion(entidades[i].getX(),entidades[i].getY());
			if (Heroe.isHeroe(prueba.getTipoImagen().toUpperCase()))
				prueba.setNombreImagen(Heroe.class.getSimpleName()); //No se si era aqui donde guardas el tipo o en tipoImagen
			else if (Background.isBackground(prueba.getTipoImagen().toUpperCase()))
				prueba.setNombreImagen(Background.class.getSimpleName());
			
			//Continuaria con los demas...
			mapa.ponerElemento(posicion, prueba);
		}
		return new Mapa(entidades);

	}
}
