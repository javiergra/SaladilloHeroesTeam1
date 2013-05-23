package es.iessaladillo.juegos.saladillo.controller;


import es.iessaladillo.juegos.saladillo.util.*;

public class Mapa implements MapaInterface, Cloneable{
	Elementos[][] mapa=new Elementos[14][14];
	Entidad[] mapaFromEntidades=new Entidad[196];
	

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
		Posicion posicion = new Posicion(0,0);
		Elementos elemento;
		
		for (int x=0; x<mapa.length; x++){
			for (int y=0; y <mapa[0].length; y++){
				elemento = mapa[x][y];
				if (elemento.getNombreImagen().equals("Heroe")){
					posicion.setX(x);
					posicion.setY(y);
					x = mapa.length;
					y = mapa[0].length;
				}
				
			}
		}
		return posicion;
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
