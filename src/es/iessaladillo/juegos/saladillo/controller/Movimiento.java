package es.iessaladillo.juegos.saladillo.controller;

import java.util.ArrayList;

import imagenes.ImprimirMapa;
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
	Dibujable heroe, elemento = null;
	
	if (mapa.posiciones == null)
		mapa.posiciones = new ArrayList<Posicion>();
	mapa.posiciones.clear();		// Limpiamos el Array antes de cada movimiento
	posicionHeroe = mapa.getPosicionHeroe();
	posicionMov = new Posicion(posicionHeroe.getX(), posicionHeroe.getY());
	heroe = mapa.obtenerPosicion(posicionHeroe);
	if (mapa.sePuedeMover(posicionMov, direccion, (byte) 0)){
		if (heroe.getFondo() != null)
			elemento = heroe.getFondo();
		mapa.ponerElemento(posicionHeroe, null);
		if (heroe.getFondo() != null)
			mapa.ponerElemento(posicionHeroe, elemento);
		mapa.posiciones.add(new Posicion(posicionHeroe.getX(), posicionHeroe.getY()) );	// Añadimos la posición que tenemos que "refrescar".
	
		posicionHeroe = mapa.siguientePosicion(posicionHeroe, direccion);	
		elemento =mapa.obtenerPosicion(posicionHeroe);
		
		if (elemento.getNombreClase().equals("ForegroundMovil")){	// Si hay pelota...
			posicionMov = new Posicion(posicionHeroe.getX(), posicionHeroe.getY()); // copia de seguridad
			mapa.ponerElemento(posicionHeroe, null);
			mapa.ponerElemento(posicionHeroe, elemento.getFondo());
			posicion = mapa.siguientePosicion(posicionMov, direccion);
			mapa.ponerElemento(posicion, elemento);
			mapa.posiciones.add(posicion);	// Añadimos la nueva posición de la pelota para "refrescar".
		}
		if (elemento.getNombreClase().equals("Teletransporte")){	// Si hay Teletransporte
			posicionHeroe = ImprimirMapa.nuevoTeletransporte(mapa, posicionHeroe, elemento.getNombreImagen());
		}
		mapa.ponerElemento(posicionHeroe, heroe);
		mapa.posiciones.add(posicionHeroe);	// Añadimos la posición del Héroe para "refrescar".
	}
	else if (heroe.getFondo().getNombreClase().equals("Teletransporte")){	// Si un elemento "pilla" en una esquina
		elemento = heroe.getFondo();										// y no podemos movernos, al darle hacia
		mapa.ponerElemento(posicionHeroe, null);							// un lado, aunque no se pueda mover
		mapa.posiciones.add(new Posicion(posicionHeroe.getX(), posicionHeroe.getY()) );
		mapa.ponerElemento(posicionHeroe, elemento);						// vuelve a usar ese mismo teletransporte.
		posicionHeroe = ImprimirMapa.nuevoTeletransporte(mapa, posicionHeroe, elemento.getNombreImagen());
		mapa.ponerElemento(posicionHeroe, heroe);
		mapa.posiciones.add(posicionHeroe);	// Añadimos la posición del Héroe para "refrescar".
	}
	return mapa;
	}
	
}
