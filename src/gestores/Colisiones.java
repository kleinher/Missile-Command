package gestores;

import java.util.Iterator;
import java.util.LinkedList;

import Aliados.Base;
import Aliados.Ciudad;
import Aliados.Explosion;
import Aliados.MisilAntibalistico;
import enemigos.Bombardero;
import enemigos.Enemigo;
import enemigos.MisilBalistico;
import enemigos.MisilCruceroInteligente;
import general.Estela;
import general.Posicion;

/**
 * Esta clase, a traves de sus metodos estaticos, se encarga de checkear en cada
 * 'tic' la existencia de colisiones, ya sea entre enemigos y areas de
 * explosi�n, como tambien entre los misiles enemigos y las ciudades/bases
 * 
 * @author UnPobreDesgraciado
 *
 */
public class Colisiones {
	private static int misilesEnemigosDestr = 0;
	private static int bombarderosDestr = 0;
	private static int misilesIntDestr = 0;

	/**
	 * ---COMPROBAR COLISIONES--- Este metodo recorre la lista de enemigos, y
	 * chequea si alguno intersecta con alguna explosion, de ser asi se lo informa
	 * invocando a su metodo Destruccion()
	 * 
	 * @param enemigos
	 *            >> Lista de enemigos
	 * @param listaExplocionesEnPantalla
	 *            >>Lista con las explosiones en Pantalla
	 * @param ciudades
	 *            >> Array de ciudades
	 * @param bases
	 *            >> Array de bases
	 */
	public static void comprobarColision(LinkedList<Enemigo> enemigos, LinkedList<Explosion> listaExplocionesEnPantalla,
			Ciudad[] ciudades, Base[] bases, LinkedList<MisilAntibalistico> misilesAliadosEnPantalla) {

		LinkedList<Enemigo> enemigosAEliminar = new LinkedList<Enemigo>();
		LinkedList<Explosion> explosionesAgregar = new LinkedList<Explosion>();
		// Recorro la lista de enemigos en pantalla
		for (Iterator<Enemigo> i = enemigos.iterator(); i.hasNext();) {
			Enemigo enemigoAct = i.next();
			boolean explotoEnemigo = false;
			enemigoAct.getEstelaDeMisil().agregarPuntoALaEstela(enemigoAct.getPosicionActual());
			// Primero busco colisiones con missiles antibalisticos
			for (Iterator<Explosion> j = listaExplocionesEnPantalla.iterator(); j.hasNext();) {
				Explosion explosionActual = j.next();
				Estela.ComprobarColisionDeExplosionConEstelas(explosionActual.getPosicionActual(),explosionActual.getRadio());
				if (colisionEnemigosConExplosiones(explosionActual, enemigoAct)) {

					//Contabilizo segun que enemigo fue destruido
					if (enemigoAct instanceof MisilCruceroInteligente) {
						misilesIntDestr++;
					}
					if (enemigoAct instanceof MisilBalistico) {
						misilesIntDestr++;
					}
					if (enemigoAct instanceof Bombardero) {
						bombarderosDestr++;
					}
					// Cuando enemigo colisiona con explosion destruyo enemigo
					enemigoAct.destruccion(explosionesAgregar, enemigosAEliminar);
					explotoEnemigo = true;
				}
			}

			// Si el enemigo ya colisiono con una explosion no me preocupo
			if (!explotoEnemigo) {

				// Por cada enemigo verifico si llego a alguna de las bases o de las ciudades
				if (enemigoAct.getPosicionActual().equals(enemigoAct.getPosicionObjetivo())) {
					
					//Comprobamos si es un bombardero para que desaparezca al final de la pantalla y no explote
					if (enemigoAct instanceof Bombardero) {
						enemigoAct.borrarEnemigoSinExplotar(enemigoAct,enemigosAEliminar);
					}
					else {
					// Si hay colision, destruyo base/ciudad y el misil enemigo
					destruirObjetivo(enemigoAct.getPosicionActual(), ciudades, bases);
					enemigoAct.destruccion(explosionesAgregar, enemigosAEliminar);
					i.remove();
				}
			}
		}
	}
		listaExplocionesEnPantalla.addAll(explosionesAgregar);
		enemigos.removeAll(enemigosAEliminar);

	}

	/**
	 * ---Destruir Objetivo--- Chequea si un misil enemigo llego a su destino, de
	 * ser asi, invoca el metodo para su inminente destruccion
	 * 
	 * @param posicionActual
	 *            >> Coordenadas de la direccion actual
	 * @param ciudades
	 *            >>Array de ciudades
	 * @param bases
	 *            >> Array de Bases
	 */
	private static void destruirObjetivo(Posicion posicionActual, Ciudad[] ciudades, Base[] bases) {

		for (int i = 1; i < ciudades.length; i++) {
			if (ciudades[i].getPosicion().equals(posicionActual)) {
				ciudades[i].destruccion();
				return;
			}
		}
		for (int i = 1; i < bases.length; i++) {
			if (bases[i].getPosicion().equals(posicionActual)) {
				bases[i].destruccion();
			}
		}
	}

	/**
	 * ---colisionesEnemigosConExplosiones---Metodo colaborador de
	 * comprobarColisiones(), que chequea si el enemigo esta adentro del rango de
	 * explosion
	 * 
	 * @param explosionActual
	 *            >>Explosion actual
	 * @param enemigoAct
	 *            >>Enemigo actual
	 * @return Devuelve verdadero si hay colision, falso en caso contrario
	 */
	private static boolean colisionEnemigosConExplosiones(Explosion explosionActual, Enemigo enemigoAct) {
		/*
		 * Si la distancia entre los dos puntos es menor al radio de explosion del
		 * enemigo aliado => HAY COLISION , si se produce una colision, determino que
		 * enemigos es con un instanceof para poder calcular el puntajer de su
		 * destruccion
		 */
		if (enemigoAct instanceof MisilCruceroInteligente)
			// Si se trata de un misil inteligente determina si va a ser necesario esquivar
			return barrerRadar(explosionActual, enemigoAct);

		else {
			int x1 = enemigoAct.getPosicionActual().getPosicionX();
			int y1 = enemigoAct.getPosicionActual().getPosicionY();

			int x2 = explosionActual.getPosicionActual().getPosicionX();
			int y2 = explosionActual.getPosicionActual().getPosicionY();

			boolean hayColision = ((Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow(y2 - y1, 2))) < explosionActual
					.getRadio());

			return hayColision;
		}
	}

	/**
	 * determina si el enemigo esta en el rango de explosion ampliado, si es asi
	 * esquiva, sino devuelve true(mensaje de destruccion)
	 * 
	 * @param explosionActual
	 * @param enemigoAct
	 * @return
	 */
	private static boolean barrerRadar(Explosion explosionActual, Enemigo enemigoAct) {
		// retorna true si hay que eliminar el misil inteligente
		int x1 = enemigoAct.getPosicionActual().getPosicionX();
		int y1 = enemigoAct.getPosicionActual().getPosicionY();

		int x2 = explosionActual.getPosicionActual().getPosicionX();
		int y2 = explosionActual.getPosicionActual().getPosicionY();
		if ((Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow(y2 - y1, 2))) < explosionActual.getRadio()) {
			return true;
		} else if ((Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow(y2 - y1, 2))) < explosionActual.getRadio() * 2) {
			boolean esquivarIzquierda;
			if (x2 > x1)
				esquivarIzquierda = true;
			else
				esquivarIzquierda = false;
			((MisilCruceroInteligente) enemigoAct).esquivar(esquivarIzquierda);
			return false;
		}

		return false;

	}

	public static int getMisilesEnemigosDestr() {
		return misilesEnemigosDestr;
	}

	public static int getBombarderosDestr() {
		return bombarderosDestr;
	}

	public static int getMisilesIntDestr() {
		return misilesIntDestr;
	}

}
