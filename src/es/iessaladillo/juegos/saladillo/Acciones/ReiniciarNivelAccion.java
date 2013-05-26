package es.iessaladillo.juegos.saladillo.Acciones;

import es.iessaladillo.juegos.saladillo.controller.Mapa;
import es.iessaladillo.juegos.saladillo.facade.SaladilloFacade;

public class ReiniciarNivelAccion implements Accion{
	Mapa mapa;
	public ReiniciarNivelAccion(Mapa mapa) {
		this.mapa = SaladilloFacade.mapaInicial;
	}
	public  Object execute() {
		return mapa;
	}

}
