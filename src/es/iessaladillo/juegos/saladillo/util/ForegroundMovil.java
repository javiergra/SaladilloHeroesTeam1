package es.iessaladillo.juegos.saladillo.util;

public enum ForegroundMovil {
	
   CAJA , BOLAPIEDRA, PELOTAAZUL, PELOTANARANJA, PELOTAVERDE, PELOTAROJA;

   public boolean isForegroundMovil (String nombreImagen){
		 boolean devuelto;
		 try{
			 ForegroundMovil movil = ForegroundMovil.valueOf(nombreImagen);
			 devuelto=true;
		 }catch (IllegalArgumentException e){
			 devuelto=false;
		 }
		 return devuelto;
	 }
}
