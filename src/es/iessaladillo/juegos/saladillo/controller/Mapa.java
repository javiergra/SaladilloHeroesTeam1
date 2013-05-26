package es.iessaladillo.juegos.saladillo.controller;


import es.iessaladillo.juegos.saladillo.interfaz.util.VariablesGlobales;
import es.iessaladillo.juegos.saladillo.util.*;

public class Mapa implements MapaInterface, Cloneable{
	
	Elementos[][] mapa=new Elementos[14][14];
	Entidad[] mapaFromEntidades=new Entidad[196];
	Posicion posicionHeroe;
	

	public Mapa(Entidad[] entidades) {
		mapaFromEntidades=entidades;
	}		// RellenarMapa? de Entidades[] a Elementos[][] NO?? Raul-> Creo que esa es la idea, aunq no me gusta mucho.

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
		
		if (mapa[x][y] == null || dibujable == null)
			mapa[x][y] = (Elementos) dibujable;
		else{
			contenido = mapa[x][y];
			if (dibujable.getNombreImagen().equals("Heroe") && contenido.getNombreImagen().equals("Diamante")){
				VariablesGlobales.diamantes++;
				dibujable.setFondo(contenido.getFondo());
				mapa[x][y] = (Elementos) dibujable;
			}
			else if (contenido.getNombreClase().equals("Background") || 
				(dibujable.getNombreImagen().equals("Heroe")) ){
				dibujable.setFondo(contenido);
				mapa[x][y] = (Elementos) dibujable;
			}	// al mover, ¿Heroe o Teletransporte primero? Preguntar a Javier
				// Raul-> Creo que la idea es heroe-teletrans-backgr.
			else{
				contenido.setFondo(dibujable);
				mapa[x][y] = contenido;
				}
		}
			
	}
	
	public boolean sePuedeMover(Posicion posicion, Direccion direccion){
		Posicion nuevaPosicion = posicion;
		Dibujable elemento;
		boolean sePuede = false;
		
		nuevaPosicion = siguientePosicion(posicion, direccion);
		
		if (posicionLegal(nuevaPosicion)){	// Si no se sale del mapa
		
		elemento = mapa[nuevaPosicion.getX()][nuevaPosicion.getY()];
		
		if (elemento.getNombreClase().equals("ForegroundMovil")){
			sePuede = sePuedeMover(nuevaPosicion, direccion);	// metodo recursivo
		}//Problema pelotero con la recursividad: Y si el heroe quiere mover una pelota y justo detras de esta hay otra pelota?
		else if (elemento.getNombreClase().equals("ForegroundFijo"))
			sePuede = false;
		}
		
		return sePuede;
	}
	
	public boolean posicionLegal(Posicion posicion){
		boolean legal = true;
		int x, y;
		
		x = posicion.getX();
		y = posicion.getY();
		
		if (x < 0 || x > 14 || y < 0 || y > 14)
			legal = false;
		return legal;
	}
	
	public Posicion siguientePosicion(Posicion posicion, Direccion direccion){
		
		if (direccion == Direccion.UP)
			posicion.setY(posicion.getY() + 1);
		else if (direccion == Direccion.DOWN)
			posicion.setY(posicion.getY() - 1);
		else if (direccion == Direccion.LEFT)
			posicion.setX(posicion.getX() - 1);
		else
			posicion.setX(posicion.getX() + 1);
		
		return posicion;
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
