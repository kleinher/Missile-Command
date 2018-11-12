package modelo.general;

import java.util.LinkedList;

import taller2.grafico.Dibujable;
import taller2.grafico.InformacionDibujable;

/* Parte Grafica*/
/**
 * La clase estela representa la estela de cada misil enemigo y aliado
 * 
 * 
 * @author Nicol
 *
 */

public class Estela   {
	private LinkedList<Posicion> listaDeEstelas;

	public Estela() {
		this.listaDeEstelas = new LinkedList<Posicion>();
	}
	/*
	 * Utilizo una lista de Posiciones, que serian puntos que se graficaran en la
	 * pantalla, agregandose puntos despues de cada movimiento de los misiles
	 */

	public LinkedList<Posicion> getListaDeEstelas() {
		return listaDeEstelas;
	}

	/*
	 * Agrega al final el ultimo punto que es la ultima posicion que se movio el
	 * misil
	 */
	public void agregarPuntoALaEstela(Posicion pos) {
		this.listaDeEstelas.addLast(pos);
	}

	/* Elimina la estela completa */
	public void eliminarEstela() {
		listaDeEstelas.clear();
		// Preguntar si cuando se hace un clear de la lista de puntos se debe eliminar
		// tambien de la lista de estelas en pantalla
	}

	/*
	 * Elimina los puntos determinados de la estela que se encuntran en un area de
	 * explosion
	 */
	public void eliminarPuntosDeLaEstela(LinkedList<Posicion> listaDePuntosAEliminar) {
		listaDeEstelas.removeAll(listaDePuntosAEliminar);
	}

	public static void ComprobarColisionDeExplosionConEstelas(Posicion posicionActual, int radio) {

	}



	/*************************
	 * cada misil va a tener una instancia de estela, cuando se instancie un misil
	 * esta comienza vacia
	 */

	/*************************
	 * En pantalla se debera recorrer toda la lista de misiles y graficar toda la
	 * lista de estelas de cada misil
	 */

	/*************************
	 * En el metodo mover hay que llamar al metodo agregarPuntoALaEstela para que
	 * este agregue al final el punto
	 */

	/*************************
	 * En el metodo colosiones hay que determinar si hubo colision de la explosion
	 * con los puntos de la estela y de ser asi, agregarlos a una lista y pasarsela
	 * para que los elimine
	 * 
	 * /*************************
	 */
}

/****************************
 * 
 * /* para el caso que el enemigo sea un bombardero se debe eliminarlo de la
 * lista de enemigos en pantalla sin provocar un explosion
 */
