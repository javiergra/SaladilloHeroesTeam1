package es.iessaladillo.juegos.saladillo.facade;

import java.util.ArrayList;
import java.util.Arrays;

import es.iessaladillo.juegos.saladillo.Acciones.*;
import es.iessaladillo.juegos.saladillo.controller.*;
import es.iessaladillo.juegos.saladillo.model.delegate.SaladilloFacadeDelegate;
import es.iessaladillo.juegos.saladillo.util.*;

public class SaladilloFacade implements SaladilloFacadeDelegate {

	private Mapa mapaInicial = new Mapa();
	private Mapa mapa = new Mapa();
	
	public Mapa getMapaInicial() {
		return mapaInicial;
	}

	public void setMapaInicial(Mapa mapaInicial) {
		this.mapaInicial = mapaInicial;
	}

	public Mapa getMapa() {
		return mapa;
	}

	public void setMapa(Mapa mapa) {
		this.mapa = mapa;
	}

	@Override
	public MapaInterface mapaFromEntidades(Entidad[] entidades) {
		Accion accion=new MapaFromEntidadesAccion(entidades);
		return (MapaInterface) accion.execute();
	}

	@Override
	public void cargarMapa(Entidad[] entidades) {
		mapa=(Mapa) mapaFromEntidades(entidades);
		mapaInicial=(Mapa) mapa.clone();
		
	}

	@Override
	public void cargarMapa(MapaInterface mapa) {
		mapa=(Mapa) mapa;
		mapaInicial=(Mapa) ((Mapa)mapa).clone();
	}

	@Override
	public MapaInterface mover(Direccion direccion) {
		Accion accion = new MoverAccion(mapa, direccion);
		return (MapaInterface) accion.execute();
	}

	@Override
	public MapaInterface reiniciarNivel() {
		Accion accion = new ReiniciarNivelAccion(mapaInicial);
		return (MapaInterface) accion.execute();
	}

	@Override
	public Posicion getPosicionHeroe() {
		Accion accion = new GetPosicionHeroeAccion(mapa);
		return (Posicion) accion.execute();
	}

	@Override
	public int diamantesEnMapa() {
		Accion accion=new DiamantesEnMapaAccion();
		return ((Integer) accion.execute()).intValue();
	}

	@Override
	public ConjuntoPosiciones posicionesAActualizar() {
		Accion accion = new PosicionesAActualizarAccion(mapa);
		return (ConjuntoPosiciones) accion.execute();
	}

	public static void main (String[] args){
		int i=0,x,y;
		Direccion direccion;

		Entidad temporal;
		Entidad[] arrayentidades;
		
		ArrayList<Entidad> entidades =CargadorNiveles.cargarNivel("src/1.lvl");

		arrayentidades = ImprimirMapa.convertirAArray(entidades);
		
		SaladilloFacade fachada = new SaladilloFacade();
		fachada.setMapa( (Mapa) fachada.mapaFromEntidades(arrayentidades) );
		ImprimirMapa.enConsola(fachada.getMapa());
		direccion = ImprimirMapa.elegirDireccion();
		Movimiento movemos = new Movimiento(fachada.getMapa(), direccion);
		fachada.setMapa( (Mapa) movemos.siguienteMovimiento() );
	}
}
