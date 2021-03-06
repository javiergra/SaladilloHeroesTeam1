package es.iessaladillo.juegos.saladillo.util;

public enum Background {

	 CAMINOARENA, CAMINOARENACOMBINADOD,CAMINOARENACOMBINADOL,CAMINOARENACOMBINADOR,CAMINOARENACOMBINADOU,
	 CAMINOARENACURVALD, CAMINOARENACURVALU, CAMINOARENACURVARU, CAMINOARENACURVARD, CAMINOARENAGRAVILLACURVARU,
	 CAMINOARENAGRAVILLACURVARD, CAMINOARENAGRAVILLACURVALU, CAMINOARENAGRAVILLACURVALD, CAMINOARENAHORIZONTAL, 
	 CAMINOARENAVERTICAL, CARRETERA, CESPED, CESPEDCOMBINADOD, CESPEDCOMBINADOL, CESPEDCOMBINADOR, 
	 CESPEDCOMBINADOU, CESPEDCURVALD, CESPEDCURVALU, CESPEDCURVARD, CESPEDCURVARU, CESPEDHORIZONTAL, 
	 CESPEDSENDERO, CESPEDSENDEROCURVALD, CESPEDSENDEROCURVALU, CESPEDSENDEROCURVARD, CESPEDSENDEROCURVARU, 
	 CESPEDSENDEROHORIZONTAL, CESPEDSENDEROVERTICAL, CESPEDVERTICAL, CAMINOARENACESPEDCURVALD, 
	 CAMINOARENACESPEDCURVALU, CAMINOARENACESPEDCURVARD, CAMINOARENACESPEDCURVARU, CAMINOARENAGRAVILLALD, 
	 CAMINOARENAGRAVILLALU, CAMINOARENAGRAVILLARD, CAMINOARENAGRAVILLARU;

	 public static boolean isBackground (String nombreImagen){
		 boolean devuelto;
		 try{
			 Background background = Background.valueOf(nombreImagen);
			 devuelto=true;
		 }catch (IllegalArgumentException e){
			 devuelto=false;
		 }
		 return devuelto;
	 }
}
