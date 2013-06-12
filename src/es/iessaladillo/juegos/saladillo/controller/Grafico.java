package es.iessaladillo.juegos.saladillo.controller;

import java.awt.Image;

public class Grafico {
	
	private Image imagenPrincipal;
	private Grafico imagenFondo;
	
	public Grafico(){
		
	}
	
	public Grafico (Image imagenPrincipal){
		this.imagenPrincipal = imagenPrincipal;
		imagenFondo = null;
	}
	
	public Grafico (Image imagenPrincipal, Grafico imagenFondo){
		this.imagenPrincipal = imagenPrincipal;
		this.imagenFondo = imagenFondo;
	}

	public Image getImagenPrincipal() {
		return imagenPrincipal;
	}

	public void setImagenPrincipal(Image imagenPrincipal) {
		this.imagenPrincipal = imagenPrincipal;
	}

	public Grafico getImagenFondo() {
		return imagenFondo;
	}

	public void setImagenFondo(Grafico imagenFondo) {
		this.imagenFondo = imagenFondo;
	}
	
	
}
