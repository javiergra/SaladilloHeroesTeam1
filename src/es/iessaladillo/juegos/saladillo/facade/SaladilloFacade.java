package es.iessaladillo.juegos.saladillo.facade;


import imagenes.ImprimirMapa;
import imagenes.JPanelConFondo;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import es.iessaladillo.juegos.saladillo.Acciones.*;
import es.iessaladillo.juegos.saladillo.controller.*;
import es.iessaladillo.juegos.saladillo.model.delegate.SaladilloFacadeDelegate;
import es.iessaladillo.juegos.saladillo.music.MP3;
import es.iessaladillo.juegos.saladillo.util.*;

public class SaladilloFacade implements SaladilloFacadeDelegate {

	private Mapa mapaInicial = new Mapa();
	private Mapa mapa = new Mapa();
	private Entidad[] arrayentidades;
	private byte nivelACargar = 1;
	
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

	public Entidad[] getArrayentidades() {
		return arrayentidades;
	}

	public void setArrayentidades(Entidad[] arrayentidades) {
		this.arrayentidades = arrayentidades;
	}

	public byte getNivelACargar() {
		return nivelACargar;
	}

	public void setNivelACargar(byte nivelACargar) {
		this.nivelACargar = nivelACargar;
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
		Accion accion=new DiamantesEnMapaAccion(mapa);
		return ((Integer) accion.execute()).intValue();
	}

	@Override
	public ConjuntoPosiciones posicionesAActualizar() {
		Accion accion = new PosicionesAActualizarAccion(mapa);
		return (ConjuntoPosiciones) accion.execute();
	}

	
}
