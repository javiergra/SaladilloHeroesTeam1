package es.iessaladillo.juegos.saladillo.Acciones;

import es.iessaladillo.juegos.saladillo.controller.Mapa;


public class DiamantesEnMapaAccion implements Accion{
	
	int diamantes;
	
	public DiamantesEnMapaAccion(Mapa mapa) {
		this.diamantes = mapa.getDiamantesEnMapa();
	}

	@Override
	public Object execute() {
		return diamantes;
	}

}
