package es.iessaladillo.juegos.saladillo.controller;
//Esta clase esta mal.XQ? Y por qu�? Pueba Oscar Moreno

import java.util.ArrayList;
import java.util.Iterator;

import es.iessaladillo.juegos.saladillo.util.*;

public class Mapa implements MapaInterface{
	Dibujable[][] mapa=new Dibujable[14][14];
	
	public void ponerMapa(){
	ArrayList<Entidad> entidad = null;
	
	}
	
	@Override
	public Dibujable obtenerPosicion(Posicion posicion) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Posicion getPosicionHeroe() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void ponerElemento(Posicion posicion, Dibujable dibujable) {
		int x, y;
		Dibujable contenido;
		
		x = posicion.getX();
		y = posicion.getY();
		
		if (mapa[x][y] == null)
			mapa[x][y] = dibujable;
		else {
			contenido = mapa[x][y];
			if (contenido.contieneFondo())
				dibujable.setFondo(contenido);
			else if((contenido.getNombreImagen().equals("TELETRANSPORTEAZUL")) ||
					(contenido.getNombreImagen().equals("TELETRANSPORTEROJO")) )
				dibujable.setFondo(contenido);
		}
			
	}
}
