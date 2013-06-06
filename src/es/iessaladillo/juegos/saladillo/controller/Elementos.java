package es.iessaladillo.juegos.saladillo.controller;


public class Elementos implements Dibujable, Cloneable{

	private String nombreImagen;
	private String tipoImagen;
	
	public String getTipoImagen() {
		return tipoImagen;
	}


	public void setNombreImagen(String nombreImagen) {
		this.nombreImagen = nombreImagen;
	}


	public void setTipoImagen(String tipoImagen) {
		this.tipoImagen = tipoImagen;
	}

	private Dibujable fondo;
	
	Elementos(String nombreImagen, String tipoImagen, Dibujable fondo){

		this.nombreImagen = nombreImagen;
		this.tipoImagen = tipoImagen;
		this.fondo = fondo;
	}
	

	public Elementos() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public String getNombreImagen() {
	
		return nombreImagen;
	}

	@Override
	public boolean contieneFondo() {
		boolean contenido = false;
	
		if (fondo != null)
			contenido = true;
	
		return contenido;
	}

	@Override
	public Dibujable getFondo() {
	
		return fondo;
	}

	@Override
	public void setFondo(Dibujable dibujable) {
		fondo = dibujable;
	
	}

	@Override
	public String getNombreClase() {

		return tipoImagen;
	}
	
	public Object clone() {
		try{
			
			Elementos elemento = (Elementos) super.clone();
			if (elemento.fondo != null)
				elemento.fondo = (Dibujable) fondo.clone();
			return elemento;
			
		}catch(CloneNotSupportedException cnse){
			return null;
		}
	}


}
