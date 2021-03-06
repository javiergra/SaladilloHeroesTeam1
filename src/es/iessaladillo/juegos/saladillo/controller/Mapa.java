package es.iessaladillo.juegos.saladillo.controller;


import java.util.ArrayList;

import es.iessaladillo.juegos.saladillo.music.MP3;
import es.iessaladillo.juegos.saladillo.util.*;

public class Mapa implements MapaInterface, Cloneable{
	
	Elementos[][] mapa=new Elementos[14][14];
	Entidad[] mapaFromEntidades;
	Posicion posicionHeroe = new Posicion(0,0);
	ArrayList<Posicion> posiciones = new ArrayList<Posicion>();
	private int diamantesEnMapa = 0;
	

	public ArrayList<Posicion> getPosiciones() {
		return posiciones;
	}

	public void setPosiciones(ArrayList<Posicion> posiciones) {
		this.posiciones = posiciones;
	}

	public Mapa(Entidad[] entidades) {
		mapaFromEntidades=entidades;
	}
	
	public Mapa() {
	}


	public int getDiamantesEnMapa() {
		return diamantesEnMapa;
	}

	public void setDiamantesEnMapa(int diamantesEnMapa) {
		this.diamantesEnMapa = diamantesEnMapa;
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
		Elementos contenido = new Elementos();
		
		x = posicion.getX();
		y = posicion.getY();
		
		
		if ( dibujable != null && dibujable.getNombreImagen().equals("Heroe")){	// Guardamos la posición del héroe.
			posicionHeroe.setX(x);
			posicionHeroe.setY(y);
		}
		else if (dibujable != null && dibujable.getNombreImagen().equals("Diamante")) // Si es un diamante, lo "contamos"
			setDiamantesEnMapa(getDiamantesEnMapa() + 1);
		
		if (dibujable == null)
			mapa[x][y] = null;	
		else if (mapa[x][y] == null )
			mapa[x][y] = (Elementos) dibujable;
		else{
			contenido = mapa[x][y];
			if (dibujable.getNombreImagen().equals("Heroe") && contenido.getNombreImagen().equals("Diamante")){
				setDiamantesEnMapa(getDiamantesEnMapa() - 1);
				dibujable.setFondo(contenido.getFondo());
				mapa[x][y] = (Elementos) dibujable;
			}
			else if (contenido.getTipoImagen().equals("Background") || 
				(dibujable.getNombreImagen().equals("Heroe")) ){
				dibujable.setFondo(contenido);
				mapa[x][y] = (Elementos) dibujable;
			}
			else if (contenido.getTipoImagen().equals(((Elementos)dibujable).getTipoImagen()))
				mapa[x][y] = (Elementos) dibujable;
			else{
				contenido.setFondo(dibujable);
				mapa[x][y] = contenido;
				}
		}
			
	}
	
	public boolean sePuedeMover(Posicion posicion, Direccion direccion, byte auxiliar){
		Posicion nuevaPosicion = posicion;
		Dibujable elemento;
		boolean sePuede = false;
		
		nuevaPosicion = siguientePosicion(nuevaPosicion, direccion);
		
		if (posicionLegal(nuevaPosicion)){	// Si no se sale del mapa
		
			elemento = mapa[nuevaPosicion.getX()][nuevaPosicion.getY()];
		
			if (elemento.getNombreClase().equals("ForegroundMovil") && (auxiliar == 0) ){
				sePuede = sePuedeMover(nuevaPosicion, direccion, (byte) 1);	
			}
			else if (elemento.getNombreClase().equals("Background") || 
					 (elemento.getNombreClase().equals("Diamante") && (auxiliar == 0) )|| // no podemos poner una pelota
					 elemento.getNombreClase().equals("Teletransporte") && (auxiliar == 0))	// sobre un diamante
				sePuede = true;
		}
		
		return sePuede;
	}
	
	public boolean posicionLegal(Posicion posicion){
		boolean legal = true;
		int x, y;
		
		x = posicion.getX();
		y = posicion.getY();
		
		if (x < 0 || x > 13 || y < 0 || y > 13)
			legal = false;
		return legal;
	}
	
	public Posicion siguientePosicion(Posicion posicion, Direccion direccion){
		
		if (direccion == Direccion.UP)
			posicion.setY(posicion.getY() - 1);
		else if (direccion == Direccion.DOWN)
			posicion.setY(posicion.getY() + 1);
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
