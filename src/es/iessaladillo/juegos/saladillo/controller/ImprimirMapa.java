package es.iessaladillo.juegos.saladillo.controller;

public class ImprimirMapa {

	
	public static void enConsola(Mapa mapa){
		int i, j;
		Elementos elemento;
		
		for (i = 0; i < 14; i++){
			for (j = 0; j < 14; j++){
				elemento = mapa.mapa[i][j];
				if (elemento.getTipoImagen().equals("HEROE"))
					System.out.print("H");
				else if (elemento.getTipoImagen().equals("BACKGROUND"))
					System.out.print("_");
				else if (elemento.getTipoImagen().equals("FOREGROUNDFIJO"))
					System.out.print("F");
				else if (elemento.getTipoImagen().equals("FOREGROUNDMOVIL"))
					System.out.print("()");
			}
			System.out.println();
		}
		System.out.println();
		
	}
}
