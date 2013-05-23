package es.iessaladillo.juegos.saladillo.controller;


public class Elementos implements Dibujable, Cloneable{

	private int X;
	private int Y;
	private String nombreImagen;
	private String tipoImagen;
	private Dibujable fondo;
	
	Elementos(byte X, byte Y, String nombreImagen, String tipoImagen, Dibujable fondo){
		this.X = X;
		this.Y = Y;
		this.nombreImagen = nombreImagen;
		this.tipoImagen = tipoImagen;
		this.fondo = fondo;
	}
	
	public int getX() {
		return X;
	}

	public void setX(int x) {
		X = x;
	}

	public int getY() {
		return Y;
	}

	public void setY(int y) {
		Y = y;
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
			elemento.fondo = (Dibujable) fondo.clone();
			return elemento;
			
		}catch(CloneNotSupportedException cnse){
			return null;
		}
	}


}
