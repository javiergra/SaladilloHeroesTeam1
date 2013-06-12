package imagenes;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import es.iessaladillo.juegos.saladillo.controller.Dibujable;
import es.iessaladillo.juegos.saladillo.controller.Elementos;
import es.iessaladillo.juegos.saladillo.controller.Grafico;
import es.iessaladillo.juegos.saladillo.controller.Mapa;
import es.iessaladillo.juegos.saladillo.util.Direccion;
import es.iessaladillo.juegos.saladillo.util.Entidad;
import es.iessaladillo.juegos.saladillo.util.Posicion;

public class ImprimirMapa {

	
	public static void enConsola(Mapa mapa){
		int i, j;
		Elementos elemento;
		
		for (i = 0; i < 14; i++){
			for (j = 0; j < 14; j++){
				elemento = (Elementos) mapa.obtenerPosicion(new Posicion(i, j));
				if (elemento.getTipoImagen().equals("Heroe"))
					System.out.print("HE");
				else if (elemento.getTipoImagen().equals("Background"))
					System.out.print("__");
				else if (elemento.getTipoImagen().equals("ForegroundFijo"))
					System.out.print("Fi");
				else if (elemento.getTipoImagen().equals("ForegroundMovil"))
					System.out.print("()");
				else if (elemento.getTipoImagen().equals("Diamante"))
					System.out.print("[]");
				else if (elemento.getNombreImagen().equals("TeletransporteAzul"))
					System.out.print("TA");
				else if (elemento.getNombreImagen().equals("TeletransporteRojo"))
					System.out.print("TR");
			}
			System.out.println();
		}
		System.out.println();
		
	}
	
	public static Entidad[] convertirAArray(ArrayList<Entidad> entidades){
		int i;
		Entidad[] miArray = new Entidad[entidades.size()];
		
		for (i = 0; i < entidades.size(); i++){
			miArray[i] = entidades.get(i);
		}
		return miArray;
		
	}
	
	public static Direccion elegirDireccion() throws NullPointerException{
		byte opcionMenu=0;
		Direccion direccion = null;
		
		Object opcion = JOptionPane.showInputDialog(
				null,"¿Hacia dónde movemos el Héroe?","MENÚ",JOptionPane.QUESTION_MESSAGE,
				null,  // null para icono defecto
				new Object[] { "1. Arriba", "2. Abajo", "3. Izquierda", "4. Derecha" }, "1. Arriba");
				
		opcionMenu = Byte.parseByte(((String)opcion).substring(0, 1));
		
					switch (opcionMenu) { 		
					case 1:
						direccion = Direccion.UP;
						break;
					case 2:	
						direccion = Direccion.DOWN;
						break;
					case 3:
						direccion = Direccion.LEFT;
						break;
					case 4:	
						direccion = Direccion.RIGHT;
						break;
					}
					
		return direccion;
		
	}

	
	public static Posicion nuevoTeletransporte(Mapa mapa, Posicion posicion, String nombreAR){
		int i, j;
		Elementos elemento;
		ArrayList<Posicion> listaTele = new ArrayList<Posicion>();
		Posicion posicionNueva;
		
		for (i = 0; i < 14; i++){
			for (j = 0; j < 14; j++){
				elemento = (Elementos) mapa.obtenerPosicion(new Posicion(i, j));
				if (elemento.getNombreImagen().equals(nombreAR) && i != posicion.getX() && j != posicion.getY()){
					posicionNueva = new Posicion(i, j);
					listaTele.add(posicionNueva);
				}
			}
		}
		i=(int)(Math.random()*listaTele.size());
		posicionNueva = listaTele.get(i);		
		
		return posicionNueva;
		
	}
	
	public static Grafico convertirAGrafico(Dibujable elemento){
		String nombreImagen;
		Grafico grafico = new Grafico();
		Image imagen;
		
		try{

			nombreImagen = "src/imagenes/" + elemento.getNombreImagen() + ".png";
			 imagen = ImageIO.read(new File(nombreImagen) );
			 
			 if (elemento.contieneFondo())
				 grafico.setImagenFondo( convertirAGrafico(elemento.getFondo()) );
			 
			 grafico.setImagenPrincipal(imagen);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return grafico;
		
	}
	
}
