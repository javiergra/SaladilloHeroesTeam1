package es.iessaladillo.juegos.saladillo.controller;

import java.util.ArrayList;

public class Elementos implements Dibujable{

ArrayList<Dibujable> elementos=new ArrayList<Dibujable>();

@Override
public String getNombreImagen() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public boolean contieneFondo() {
	// TODO Auto-generated method stub
	return false;
}

@Override
public Dibujable getFondo() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public void setFondo(Dibujable dibujable) {
	// TODO Auto-generated method stub
	
}

@Override
public String getNombreClase() {
	// TODO Auto-generated method stub
	return null;
}
public Object clone() {
	return null;
}


}
