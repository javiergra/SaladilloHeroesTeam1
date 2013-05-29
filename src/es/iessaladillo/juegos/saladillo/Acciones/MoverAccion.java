package es.iessaladillo.juegos.saladillo.Acciones;

import es.iessaladillo.juegos.saladillo.controller.Mapa;
import es.iessaladillo.juegos.saladillo.controller.Movimiento;
import es.iessaladillo.juegos.saladillo.util.Direccion;

public class MoverAccion implements Accion{
	Mapa mapa;
	Direccion direccion;
	
	public MoverAccion(Mapa mapa, Direccion direccion){
		this.mapa = mapa;
		this.direccion = direccion;
	}
	

	public  Object execute() {
		Movimiento tuTurno = new Movimiento(mapa, direccion);
		return tuTurno.siguienteMovimiento();
	}

}
