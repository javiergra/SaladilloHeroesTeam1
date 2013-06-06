package es.iessaladillo.juegos.saladillo.Acciones;

import es.iessaladillo.juegos.saladillo.controller.Elementos;
import es.iessaladillo.juegos.saladillo.controller.Mapa;
import es.iessaladillo.juegos.saladillo.util.*;


public class MapaFromEntidadesAccion implements Accion {
	Mapa mapa = new Mapa();
	Entidad[] entidades;

	public MapaFromEntidadesAccion(Entidad[] entidades) {
		this.entidades = entidades;
	}

	@Override
	public Object execute() {
		int i = 0;
		Elementos prueba;;
		Posicion posicion = new Posicion(0,0);
		for (i = 0; i < entidades.length; i++) {
			prueba =  new Elementos();
			prueba.setNombreImagen(entidades[i].getTipo());
			posicion=new Posicion(entidades[i].getX(),entidades[i].getY());
			if (Heroe.isHeroe(prueba.getNombreImagen().toUpperCase()))
				prueba.setTipoImagen(Heroe.class.getSimpleName()); 
			else if (Background.isBackground(prueba.getNombreImagen().toUpperCase()))
				prueba.setTipoImagen(Background.class.getSimpleName());
			else if (ForegroundMovil.isForegroundMovil(prueba.getNombreImagen().toUpperCase()))
				prueba.setTipoImagen(ForegroundMovil.class.getSimpleName());
			else if (ForegroundFijo.isForegroundFijo(prueba.getNombreImagen().toUpperCase()))
				prueba.setTipoImagen(ForegroundFijo.class.getSimpleName());
			else if (Teletransporte.isTeletransporte(prueba.getNombreImagen().toUpperCase()))
				prueba.setTipoImagen(Teletransporte.class.getSimpleName());
			else if (Diamante.isDiamante(prueba.getNombreImagen().toUpperCase()))
				prueba.setTipoImagen(Diamante.class.getSimpleName());
			
			mapa.ponerElemento(posicion, prueba);
		}
		return mapa;

	}
}
