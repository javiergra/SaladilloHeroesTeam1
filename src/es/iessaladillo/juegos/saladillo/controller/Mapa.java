package es.iessaladillo.juegos.saladillo.controller;


import es.iessaladillo.juegos.saladillo.util.*;

public class Mapa implements MapaInterface, Cloneable{
	Elementos[][] mapa=new Elementos[14][14];
	Entidad[] mapaFromEntidades=new Entidad[196];
	Posicion posicionHeroe;
	

	public Mapa(Entidad[] entidades) {
		mapaFromEntidades=entidades;
	}

	@Override
	public Dibujable obtenerPosicion(Posicion posicion) {
		Elementos elemento = null;
		elemento.setX(posicion.getX());
		elemento.setY(posicion.getY());
		return elemento;
	}
	
	@Override
	public Posicion getPosicionHeroe() {
		return posicionHeroe;
	}
	
	@Override
	public void ponerElemento(Posicion posicion, Dibujable dibujable) {
		int x, y;
		Dibujable contenido;
		
		x = posicion.getX();
		y = posicion.getY();
		
		if (mapa[x][y] == null)
			mapa[x][y] = (Elementos) dibujable;
		else {
			contenido = mapa[x][y];
			if (contenido.contieneFondo())
				dibujable.setFondo(contenido);
			else if((contenido.getNombreImagen().equals("TELETRANSPORTEAZUL")) ||
					(contenido.getNombreImagen().equals("TELETRANSPORTEROJO")) )
				dibujable.setFondo(contenido);
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
