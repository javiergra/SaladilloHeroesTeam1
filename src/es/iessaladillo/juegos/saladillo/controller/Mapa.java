package es.iessaladillo.juegos.saladillo.controller;


import java.util.ArrayList;

import es.iessaladillo.juegos.saladillo.interfaz.util.VariablesGlobales;
import es.iessaladillo.juegos.saladillo.util.*;

public class Mapa implements MapaInterface, Cloneable{
	
	Elementos[][] mapa=new Elementos[14][14];
	Entidad[] mapaFromEntidades=new Entidad[196];
	Posicion posicionHeroe = new Posicion(0,0);
	ArrayList<Posicion> posiciones = new ArrayList<Posicion>();
	

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
		// TODO Auto-generated constructor stub
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
		
		if (dibujable == null)
			mapa[x][y] = null;
		else if (mapa[x][y] == null )
			mapa[x][y] = (Elementos) dibujable;
		else{
			if (dibujable.getNombreImagen().equals("Heroe")){
				posicionHeroe.setX(x);
				posicionHeroe.setY(y);
			}
			
			contenido = mapa[x][y];
			if (dibujable.getNombreImagen().equals("Heroe") && contenido.getNombreImagen().equals("Diamante")){
				VariablesGlobales.diamantes++;
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
					 elemento.getNombreClase().equals("Diamante") || 
					 elemento.getNombreClase().equals("Teletransporte") )
				sePuede = true;
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
			posicion.setX(posicion.getX() - 1);
		else if (direccion == Direccion.DOWN)
			posicion.setX(posicion.getX() + 1);
		else if (direccion == Direccion.LEFT)
			posicion.setY(posicion.getY() - 1);
		else
			posicion.setY(posicion.getY() + 1);
		
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
