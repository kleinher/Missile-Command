package general;

import java.util.Iterator;
import java.util.LinkedList;

import enemigos.Enemigo;

public class Colisiones {

	/*--Comprobar Colision---
	 * Parametros: Enemigos, Misiles aliados, Ciudades, Bases
	 */
	public static void comprobarColision(LinkedList<Enemigo> enemigos,
			LinkedList<Explosion> listaExplocionesEnPantalla, Ciudad[] ciudades, Base[] bases) {

		// Recorro la lista de enemigos en pantalla
		for (Iterator<Enemigo> i = enemigos.iterator(); i.hasNext();) {
			Enemigo enemigoAct = i.next();
			boolean explotoEnemigo = false;

			// Primero busco colisiones con misiles antibalisticos
			for (Iterator<Explosion> j = listaExplocionesEnPantalla.iterator(); j.hasNext();) {
				Explosion explosionActual = j.next();
				if (colisionEnemigosConExplosiones(explosionActual, enemigoAct)) {
					// Cuando enemigo colisiona con explosion destruyo enemigo
					enemigoAct.destruccion(listaExplocionesEnPantalla,enemigos);
					explotoEnemigo = true;
				}
			}
			
			//Si el enemigo ya colisiono con una explosion no me preocupo
			if (!explotoEnemigo) {
				
				// Por cada enemigo verifico si llego a alguna de las bases o de las ciudades
				if (enemigoAct.getPosicionActual().equals(enemigoAct.getPosicionObjetivo())) {
					
					//Si hay colision, destruyo base/ciudad y el misil enemigo
					destruirObjetivo(enemigoAct.getPosicionActual(), ciudades, bases);
					enemigoAct.destruccion(listaExplocionesEnPantalla,enemigos);
				}
			}
		}
	}

	/*---Destruir Objetivo---
	 * Cuando un misil enemigo llega a su objetivo, se destruye el objetivo
	 * */
	private static void destruirObjetivo(Posicion posicionActual, Ciudad[] ciudades, Base[] bases) {
		
		for(int i=1; i<ciudades.length;i++) {
			if(ciudades[i].getPosicion().equals(posicionActual)) {
				ciudades[i].destruccion();
				return;
			}
		}
		for(int i=1;i<bases.length;i++) {
			if(bases[i].getPosicion().equals(posicionActual)) {
				bases[i].destruccion();
			}
		}
	}

	private static boolean colisionEnemigosConExplosiones(Explosion explosionActual, Enemigo enemigoAct) {
		/*
		 * Si la distancia entre los dos puntos es menor al radio de explosion del
		 * enemigo aliado => HAY COLISION
		 */
		int x1 = enemigoAct.getPosicionActual().getPosicionX();
		int y1 = enemigoAct.getPosicionActual().getPosicionY();

		int x2 = explosionActual.getPosicionActual().getPosicionX();
		int y2 = explosionActual.getPosicionActual().getPosicionY();

		boolean hayColision = ((Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow(y2 - y1, 2))) < explosionActual.getRadio());

		return hayColision;
	}
}
