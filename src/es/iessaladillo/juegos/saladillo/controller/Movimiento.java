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
		
	Posicion posicionHeroe, posicion, posicionMov;
	Dibujable heroe, elemento;
	
	mapa.posiciones.clear();		// Limpiamos el Array antes de cada movimiento
	posicionHeroe = mapa.getPosicionHeroe();
	posicionMov = new Posicion(posicionHeroe.getX(), posicionHeroe.getY());
	heroe = mapa.obtenerPosicion(posicionHeroe);
	if (mapa.sePuedeMover(posicionMov, direccion, (byte) 0)){
		
		elemento = heroe.getFondo();
		mapa.ponerElemento(posicionHeroe, null);
		mapa.ponerElemento(posicionHeroe, elemento);
		mapa.posiciones.add(posicionMov);	// Añadimos la posición que tenemos que "refrescar".
	
		posicionHeroe = mapa.siguientePosicion(posicionHeroe, direccion);	
		elemento =mapa.obtenerPosicion(posicionHeroe);
		
		if (elemento.getNombreClase().equals("ForegroundMovil")){	// Si hay pelota...
			mapa.ponerElemento(posicionHeroe, elemento.getFondo());
			posicion = mapa.siguientePosicion(posicionHeroe, direccion);
			mapa.ponerElemento(posicion, elemento);
			mapa.posiciones.add(posicion);	// Añadimos la nueva posición de la pelota para "refrescar".
		}
		mapa.ponerElemento(posicionHeroe, heroe);
		mapa.posiciones.add(posicionHeroe);	// Añadimos la posición del Héroe para "refrescar".
	}
	return mapa;
	}
	
}
