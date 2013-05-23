package es.iessaladillo.juegos.saladillo.Acciones;

import es.iessaladillo.juegos.saladillo.controller.Mapa;
import es.iessaladillo.juegos.saladillo.util.Entidad;


public class CargarMapaAccion implements Accion{
	
	Entidad[] entidades;
	
	public CargarMapaAccion(Entidad[] entidades){
		this.entidades = entidades;
	}

	public  Object execute() {
		return new Mapa(entidades);
	}

	public static void Saludo(){
		System.out.println("Hola");
	}
}