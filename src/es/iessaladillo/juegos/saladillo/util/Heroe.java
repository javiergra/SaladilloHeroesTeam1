package es.iessaladillo.juegos.saladillo.util;

public enum Heroe {
	HEROE;

	public static boolean isHeroe (String nombreImagen){
		 boolean devuelto;
		 try{
			 Heroe heroe = Heroe.valueOf(nombreImagen);
			 devuelto=true;
		 }catch (IllegalArgumentException e){
			 devuelto=false;
		 }
		 return devuelto;
	 }
}
