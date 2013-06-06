package es.iessaladillo.juegos.saladillo.controller;

import java.util.ArrayList;

import es.iessaladillo.juegos.saladillo.util.Entidad;

public class ImprimirMapa {

	
	public static void enConsola(Mapa mapa){
		int i, j;
		Elementos elemento;
		
		for (i = 0; i < 14; i++){
			for (j = 0; j < 14; j++){
				elemento = mapa.mapa[i][j];
				if (elemento.getTipoImagen().equals("Heroe"))
					System.out.print("HE");
				else if (elemento.getTipoImagen().equals("Background"))
					System.out.print("__");
				else if (elemento.getTipoImagen().equals("ForegroundFijo"))
					System.out.print("Fi");
				else if (elemento.getTipoImagen().equals("ForegroundMovil"))
					System.out.print("()");
				else if (elemento.getTipoImagen().equals("Diamante"))
					System.out.print("[]");
				else if (elemento.getNombreImagen().equals("TeletransporteAzul"))
					System.out.print("TA");
				else if (elemento.getNombreImagen().equals("TeletransporteRojo"))
					System.out.print("TR");
			}
			System.out.println();
		}
		System.out.println();
		
	}
	
	public static Entidad[] convertirAArray(ArrayList<Entidad> entidades){
		int i;
		Entidad[] miArray = new Entidad[entidades.size()];
		
		for (i = 0; i < entidades.size(); i++){
			miArray[i] = entidades.get(i);
		}
		return miArray;
		
	}
}
