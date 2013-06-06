package es.iessaladillo.juegos.saladillo.util;

public enum Diamante {
	DIAMANTE;

	public static boolean isDiamante (String nombreImagen){
		 boolean devuelto;
		 try{
			 Diamante diamante = Diamante.valueOf(nombreImagen);
			 devuelto=true;
		 }catch (IllegalArgumentException e){
			 devuelto=false;
		 }
		 return devuelto;
	 }
}
