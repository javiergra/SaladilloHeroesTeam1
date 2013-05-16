package es.iessaladillo.juegos.saladillo.controller;
//Esta clase esta mal.

import java.util.ArrayList;
import java.util.Iterator;

import es.iessaladillo.juegos.saladillo.util.*;

public class Mapa implements MapaInterface{
	Dibujable[][] mapa=new Dibujable[14][14];
	
	public void ponerMapa(){
	ArrayList<Entidad> entidad = null;
	Elementos elemento = null;
	Dibujable element;
	element=elemento;
	Iterator iterador=entidad.iterator();
	while (iterador.hasNext()){
		Posicion posicion= new Posicion (entidad.iterator().next().getX(),entidad.iterator().next().getY());
		ponerElemento(posicion, element);
	}
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
		// TODO Auto-generated method stub
		
	}
}
