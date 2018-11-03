package gestores;

import java.util.Iterator;
import java.util.LinkedList;

import clasesPadres.Enemigo;
import misiles.Explosion;
import misiles.MisilAntibalistico;

/**
 * Singleton
 * 
 * @author eze96
 *
 */
public class GestorMisiles {
	private static final GestorMisiles GestorMisiles = new GestorMisiles();

	public static GestorMisiles GetGestorMisiles() {
		return GestorMisiles;
	}

	private GestorMisiles() {

	}
	/**
	 * Este metodo lanza una oleada de enemigos
	 * @param auxOleada	>Lista con los enemigos Restantes ??<<<<<<
	 * @param EnemigosEnPantalla > Lista con Enemigos en pantalla
	 */
	public void lanzarEnemigos() {
		LinkedList<Enemigo> auxOleada = GestorEstructuras.getGestorEstructuras().getEnemigosEnEspera().poll();
		LinkedList<Enemigo> EnemigosEnPantalla = GestorEstructuras.getGestorEstructuras().getEnemigosEnPantalla();
		// Agrego los enemigos a la lista enemigosEnPantalla
		for (Iterator<Enemigo> i = auxOleada.iterator(); i.hasNext();) {
			EnemigosEnPantalla.add(i.next());
		}

	}

	/**
	 * ---ActualizarPosiciones--- Este metodo actualiza las posiciones de todos los
	 * objetos del juego
	 * 
	 */
	public void actualizarPosiciones() {

		// Actualiza posiciones de los de los enemigos
		for (Iterator<Enemigo> i = GestorEstructuras.getGestorEstructuras().getEnemigosEnPantalla().iterator(); i
				.hasNext();) {
			Enemigo enemigo = i.next();
			enemigo.mover();
			if (enemigo.alcanzoObjetivo()) {
				i.remove();
			}
		}
		LinkedList<Enemigo> aliadosAEliminar = new LinkedList<Enemigo>();
		LinkedList<Explosion> explosionesAgregar = new LinkedList<Explosion>();
		
		// Actualiza la posicion de los misiles aliados
		for (Iterator<MisilAntibalistico> i = GestorEstructuras.getGestorEstructuras().getMisilesAliadosEnPantalla()
				.iterator(); i.hasNext();) {
			MisilAntibalistico misil = i.next();
			misil.mover();
			if(misil.alcanzoObjetivo()) {
				i.remove();
				misil.destruccion(explosionesAgregar, aliadosAEliminar);
			}
			
			GestorEstructuras.getGestorEstructuras().getExplosionesEnPantalla().addAll(explosionesAgregar);
			GestorEstructuras.getGestorEstructuras().getMisilesAliadosEnPantalla().removeAll(aliadosAEliminar);
		}
	}
}
