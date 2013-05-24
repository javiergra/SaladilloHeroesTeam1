package es.iessaladillo.juegos.saladillo.Acciones;

import es.iessaladillo.juegos.saladillo.interfaz.util.VariablesGlobales;

public class DiamantesEnMapaAccion implements Accion{
	
	int diamantes;
	
	public DiamantesEnMapaAccion() {
		this.diamantes = VariablesGlobales.diamantes;
	}

	@Override
	public Object execute() {
		return diamantes;
	}

}
