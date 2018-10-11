package general;

import java.util.Iterator;
import java.util.LinkedList;

import enemigos.Enemigo;

public class Colisiones {

	/*
	 * recibo lista de enemigos, de ListaMisilesAntibalisticos, de bases, y de
	 * ciudades
	 */
	public static void comprobarColision(LinkedList<Enemigo> enemigos,
			LinkedList<MisilAntibalistico> listaMisilesAntibalisticos, Ciudad[] ciudades, Base[] bases) {
		/* Recorro los ListaMisilesAntibalisticos que estan en pantalla */
		for (Iterator<MisilAntibalistico> i = listaMisilesAntibalisticos.iterator(); i.hasNext();) {
			MisilAntibalistico AliadoAct = i.next();
			/* filtro los que estan en estado de explosion */
			if (AliadoAct.isExploto())
				/*
				 * Recorro los enemigos y pregunto si algun enemigo esta dentro del radio de
				 * explosion
				 */
				while (enemigos.iterator().hasNext()) {
					Enemigo EnemigoAct = enemigos.iterator().next();
					if (choque(AliadoAct.getPosicionActual(), AliadoAct.getArea(), EnemigoAct.getPosicionActual())) {
						/* si colisionaron, envio mensaje a cada uno avisando */
						EnemigoAct.destruccion();
						AliadoAct.destruccion();
					}

				}

		}
		/* Ahora verifico si algun enemigo llgo a la base */
		for (Iterator<Enemigo> i = enemigos.iterator(); i.hasNext();) {
			Enemigo EnemigoAct = i.next();

			/* Por cada enemigo verifico si llego a alguna de las bases o de las ciudades */
			for (int j = 0; j < ciudades.length; j++) {
				if (EnemigoAct.getPosicionActual().equals(ciudades[j].getPosicion())) {
					ciudades[j].destruccion();
				}
			}
			for (int j = 0; j < ciudades.length; j++) {
				if (EnemigoAct.getPosicionActual().equals(ciudades[j].getPosicion())) {
					ciudades[j].destruccion();
				}
			}

		}
	}

	private static boolean choque(Posicion posAliado, AreaDeExplosion areaExpAliada, Posicion posEnemigo) {
		/*
		 * Si la distancia entre los dos puntos es menor al radio de explosion del
		 * enemigo aliado => HAY COLISION
		 */
		boolean hayColision = ((Math.sqrt(Math.pow(posAliado.getPosicionX() - posEnemigo.getPosicionX(), 2)
				+ Math.pow(posAliado.getPosicionY() - posEnemigo.getPosicionY(), 2)) < areaExpAliada.getRadio()));

		if (hayColision)
			return true;
		else
			return false;
	}
}
