package es.iessaladillo.juegos.saladillo.controller;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import es.iessaladillo.juegos.saladillo.util.Direccion;
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
	
	public static Direccion elegirDireccion(){
		byte opcionMenu;
		Direccion direccion = null;
		
		Object opcion = JOptionPane.showInputDialog(
				null,"¿Hacia dónde movemos el Héroe?","MENÚ",JOptionPane.QUESTION_MESSAGE,
				null,  // null para icono defecto
				new Object[] { "1. Arriba", "2. Abajo", "3. Izquierda", "4. Derecha" }, "1. Arriba");
				opcionMenu = Byte.parseByte(((String)opcion).substring(0, 1));
				
					switch (opcionMenu) { 		
					case 1:
						direccion = Direccion.UP;
						break;
					case 2:	
						direccion = Direccion.DOWN;
						break;
					case 3:
						direccion = Direccion.LEFT;
						break;
					case 4:	
						direccion = Direccion.RIGHT;
						break;
					}
					
		return direccion;
		
	}
}
