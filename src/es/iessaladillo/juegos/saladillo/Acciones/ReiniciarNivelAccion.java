package es.iessaladillo.juegos.saladillo.Acciones;

import es.iessaladillo.juegos.saladillo.controller.Mapa;

public class ReiniciarNivelAccion implements Accion{
	Mapa mapa;
	public ReiniciarNivelAccion(Mapa mapaInicial) {
		mapa = mapaInicial;
	}
	public  Object execute() {
		return mapa;
	}

}
