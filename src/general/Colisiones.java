package general;

import java.util.Iterator;
import java.util.LinkedList;

import enemigos.Enemigo;

public class Colisiones {

	/*--Comprobar Colision---
	 * Parametros: Enemigos, Misiles aliados, Ciudades, Bases
	 */
	public static void comprobarColision(LinkedList<Enemigo> enemigos,
					LinkedList<Explosiones> listaExplocionesEnPantalla,
													Ciudad[] ciudades,
													Base[] bases) {

		// Recorro la lista de enemigos en pantalla
		for (Iterator<Enemigo> i = enemigos.iterator(); i.hasNext();) {
			Enemigo enemigoAct = i.next();
			
			//Primero busco colisiones con misiles antibalisticos
			for (Iterator<Explosiones> j = listaExplocionesEnPantalla.iterator(); j.hasNext();) {
				Explosiones explosionActual = j.next();
				if (colisionEnemigosConExplosiones(explosionActual, enemigoAct)){
					//Cuando enemigo colisiona con explosion destruyo enemigo
					enemigoAct.destruccion();
					continue;
				}
			}
			//Por cada enemigo verifico si llego a alguna de las bases o de las ciudades
			for (int j = 0; j < ciudades.length; j++) {
				if (enemigoAct.getPosicionActual().equals(ciudades[j].getPosicion())) {
					ciudades[j].destruccion();
				}
			}
			
			for (int j = 0; j < ciudades.length; j++) {
				if (enemigoAct.getPosicionActual().equals(ciudades[j].getPosicion())) {
					ciudades[j].destruccion();
				}
			}
		}
	}

	private static boolean colisionEnemigosConExplosiones(Explosiones explosionActual,Enemigo enemigoAct) {
		/*Si la distancia entre los dos puntos es menor al radio de explosion del
		 *enemigo aliado => HAY COLISION
		 */
		int x1=enemigoAct.getPosicionActual().getPosicionX();
		int y1=enemigoAct.getPosicionActual().getPosicionY();
		
		int x2=explosionActual.getPosicionActual().getPosicionX();
		int y2=explosionActual.getPosicionActual().getPosicionY();
		
		boolean hayColision = ((Math.sqrt(Math.pow((x2 - x1), 2)
				+ Math.pow(y2 - y1, 2))) < explosionActual.getRadio());

		
		return hayColision;
	}
}
