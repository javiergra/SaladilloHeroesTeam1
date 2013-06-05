package es.iessaladillo.juegos.saladillo.util;

public enum ForegroundFijo {
	
	ARBOL, CASA, EDIFICIO, EDIFICIO2, MATORRAL, ROCASUELO;

	 public boolean isForegroundFijo (String nombreImagen){
		 boolean devuelto;
		 try{
			 ForegroundFijo fijo = ForegroundFijo.valueOf(nombreImagen);
			 devuelto=true;
		 }catch (IllegalArgumentException e){
			 devuelto=false;
		 }
		 return devuelto;
	 }
}
