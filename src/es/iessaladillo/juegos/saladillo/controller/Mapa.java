package es.iessaladillo.juegos.saladillo.controller;


import es.iessaladillo.juegos.saladillo.util.*;

public class Mapa implements MapaInterface, Cloneable{
	public static int diamantes = 0;
	Elementos[][] mapa=new Elementos[14][14];
	Entidad[] mapaFromEntidades=new Entidad[196];
	Posicion posicionHeroe;
	

	public Mapa(Entidad[] entidades) {
		mapaFromEntidades=entidades;
	}

	@Override
	public Dibujable obtenerPosicion(Posicion posicion) {
		Elementos elemento = null;
		elemento = mapa.clone()[posicion.getX()][posicion.getY()];
		return elemento;
	}
	
	@Override
	public Posicion getPosicionHeroe() {
		return posicionHeroe;
	}
	
	@Override
	public void ponerElemento(Posicion posicion, Dibujable dibujable) {
		int x, y;
		Elementos contenido = null;
		
		x = posicion.getX();
		y = posicion.getY();
		
		if (dibujable.getNombreImagen().equals("Heroe")){
			posicionHeroe.setX(x);
			posicionHeroe.setY(y);
		}
		
		if (mapa[x][y] == null)
			mapa[x][y] = (Elementos) dibujable;
		else{
			contenido = mapa[x][y];
			if (contenido.getNombreClase().equals("Background") || 
				(dibujable.getNombreImagen().equals("Heroe")) ){
				dibujable.setFondo(contenido);
				mapa[x][y] = (Elementos) dibujable;
			}	// al mover, ¿Heroe o Teletransporte primero? Preguntar a Javier
			else{
				contenido.setFondo(dibujable);
				mapa[x][y] = contenido;
				}
		}
			
	}
	
	public Object clone() {
		try{
			
			Mapa mapa = (Mapa) super.clone();
			return mapa;
			
		}catch(CloneNotSupportedException cnse){
			return null;
		}
	}
}
