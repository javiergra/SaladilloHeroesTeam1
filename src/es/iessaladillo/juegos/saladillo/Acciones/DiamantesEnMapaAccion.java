package es.iessaladillo.juegos.saladillo.Acciones;

public class DiamantesEnMapaAccion implements Accion{
	
	int diamantes;
	
	public DiamantesEnMapaAccion(int diamantes) {
		this.diamantes = diamantes;
	}

	@Override
	public Object execute() {
		return diamantes;
	}
//Prueba para Oscar
}
