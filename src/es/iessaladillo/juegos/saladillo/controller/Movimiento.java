package es.iessaladillo.juegos.saladillo.controller;

import es.iessaladillo.juegos.saladillo.util.Direccion;
import es.iessaladillo.juegos.saladillo.util.Posicion;

public class Movimiento {
	
	Mapa mapa;
	Direccion direccion;
	
	public Movimiento(Mapa mapa, Direccion direccion){
		this.mapa = mapa;
		this.direccion = direccion;
	}

	public MapaInterface siguienteMovimiento(){
		
	Posicion posicionHeroe, posicion;
	Dibujable heroe, elemento;
	
	posicion = mapa.getPosicionHeroe();
	heroe = mapa.obtenerPosicion(posicion);
	if (mapa.sePuedeMover(posicion, direccion, (byte) 0)){
		
		elemento = heroe.getFondo();
		mapa.ponerElemento(posicion, null);
		mapa.ponerElemento(posicion, elemento);
	
		posicionHeroe = mapa.siguientePosicion(posicion, direccion);	
		elemento =mapa.obtenerPosicion(posicionHeroe);
		
		if (elemento.getNombreClase().equals("ForegroundMovil")){	// Si hay pelota...
			mapa.ponerElemento(posicionHeroe, elemento.getFondo());
			posicion = mapa.siguientePosicion(posicionHeroe, direccion);
			mapa.ponerElemento(posicion, elemento);
		}
		mapa.ponerElemento(posicionHeroe, heroe);
	}
	return mapa;
	}
	
}
