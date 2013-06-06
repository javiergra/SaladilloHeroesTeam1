package es.iessaladillo.juegos.saladillo.util;

public enum Teletransporte {
	
	TELETRANSPORTEROJO, TELETRANSPORTEAZUL;

	public static boolean isTeletransporte (String nombreImagen){
		 boolean devuelto;
		 try{
			 Teletransporte teletransporte = Teletransporte.valueOf(nombreImagen);
			 devuelto=true;
		 }catch (IllegalArgumentException e){
			 devuelto=false;
		 }
		 return devuelto;
	 }
}
