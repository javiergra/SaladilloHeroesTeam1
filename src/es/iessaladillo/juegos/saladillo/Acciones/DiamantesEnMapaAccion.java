package es.iessaladillo.juegos.saladillo.Acciones;

import es.iessaladillo.juegos.saladillo.controller.Mapa;

public class DiamantesEnMapaAccion implements Accion{
	
	int diamantes;
	
	public DiamantesEnMapaAccion() {
		this.diamantes = Mapa.diamantes;
	}

	@Override
	public Object execute() {
		return diamantes;
	}

}
