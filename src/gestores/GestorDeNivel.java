package gestores;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import estructurasAliadas.Base;
import estructurasAliadas.Ciudad;
import gestores.Colisiones;
import gestores.GestorEstructuras;
import misiles.Explosion;
import misiles.MisilAntibalistico;
import puntajes.PuntajeJugador;
import taller2.grafico.Dibujable;
import taller2.modelo.Graficador;
import clasesPadres.Enemigo;

/**
 * Gestor de nivel se instancia en su misma clase para ser Singleton
 */
/**
 * ---GESTOR DE NIVEL--- ___CLASE SINGLETON___ Esta clase se encarga de las
 * principales tareas, como arbitrar los niveles, la dificultd, y llevar el
 * control de el estado de la partida en cada tic del juego, delegando en la
 * clase 'Colisones' el recuento de 'muertes' y 'destrucciones'
 * 
 * @author LosPi
 *
 */

public class GestorDeNivel {

	// Delay para la clase graficador
	final private int delayMilis = 100;

	static GestorDeNivel GestorDeNivel = new GestorDeNivel();
	// Variables de juego
	private int Dificultad;
	private boolean Perdio;
	private int NivelActual;
	private PuntajeJugador puntajeJugador;

	List<? extends Dibujable> listaDibujables;

	/**
	 * ---CONSTRUCTOR GESTOR DE NIVEL---- Se inicializan las Ciudades y Bases con
	 * sus datos iniciales
	 */
	private GestorDeNivel() {
		// GestorEstructuras.
		this.NivelActual = 1;
		this.puntajeJugador = new PuntajeJugador();

		this.Perdio = false;
		this.Dificultad = 15;
	}

	/**
	 * ---GESTIONAR NIVEL--- Funcion: Modifica la instancia nivel(de Juego) cada ves
	 * que comienza un nuevo nivel
	 */
	public void gestionarNivel() {

		// Incrementa la dificultad cuando aumenta un nivel
		this.Dificultad += 5;
		// REVIVIR CIUDAD EN CASO DE
		this.NivelActual++;
	}

	/**
	 * -------------LOOP NIVEL-----------
	 * 
	 * En este metodo se va a iterar todo el nivel, basicamente es el metodo
	 * principal del juego
	 * 
	 * Lanza las oleadas de enemigos por cada nivel, realiza el cambio de dificultad
	 * conforme se avanza en los niveles
	 * 
	 * @throws InterruptedException
	 */
	public void loopDelNivel(PuntajeJugador puntajeJugador) throws InterruptedException {

		int tics = 0;

		// Lanzo la primer oleada de enemigos
		GestorMisiles.GetGestorMisiles().lanzarEnemigos();
		// LANZAR MISILES ALIADOS HARDCODEADOS

		Base.Disparar();
		Base.Disparar();
		Base.Disparar();

		// Mientras hayan enemigos
		while (!enemigosEnEspera.isEmpty()) {
			Graficador.refrescarTopDown(ActualizarListaDibujables(), delayMilis);
			// Cuando pasa 1 segundo, lanzo otra oleada de enemigos
			if (tics == 30) {
				// Lanzo una nueva oleada de enemigos
				GestorMisiles.GetGestorMisiles().lanzarEnemigos();
				tics = 0;
			}

			GestorMisiles.GetGestorMisiles().actualizarPosiciones();
			Colisiones.comprobarColision(enemigosEnPantalla, explosionesEnPantalla, ciudades, bases);
			// dibujar();
			// Thread.sleep(1000 / Dificultad);
			tics++;
		}
		if (!Ciudad.hayCiudades(ciudades)) {
			this.Perdio = true;
		}
		// contarPuntajes(puntajeJugador);

	}

	/**
	 * METODO QUE ACTUALIZA EN CADA TIC LA LISTA DE LOS ELEMENTOS DIBUJABLES
	 * 
	 * @return devuelve una lista de elementos dibujables
	 */
	private List<Dibujable> ActualizarListaDibujables() {

		/* Creo una nueva lista con todo lo que sea dibujable */
		List<Dibujable> listaDibujables = new LinkedList<Dibujable>();

		/* Agrego los enemigos que están en pantalla */
		for (Iterator<Enemigo> i = enemigosEnPantalla.iterator(); i.hasNext();) {
			listaDibujables.add(i.next());
		}
		/* Agrego las explosiones que estan en pantalla */
		for (Iterator<Explosion> i = explosionesEnPantalla.iterator(); i.hasNext();) {
			listaDibujables.add(i.next());
		}
		/* Agrego los misiles aliados que estan en pantalla */
		for (Iterator<MisilAntibalistico> i = misilesAliadosEnPantalla.iterator(); i.hasNext();) {
			listaDibujables.add(i.next());
		}
		/* Agrego las ciudades y las bases */
		for (int i = 1; i < bases.length; i++) {
			if (bases[i].isEstaViva())
				listaDibujables.add(bases[i]);
		}
		for (int i = 1; i < ciudades.length; i++) {
			if (ciudades[i].estaViva())
				listaDibujables.add(ciudades[i]);
		}
		return listaDibujables;
	}

		// ACTUALIZAR EXPLOSIONES

		// ACTUALIZAR ESTELA
	

	/**
	 * 
	 * @return Devuelve un booleano si perdio o no, en funi�n del estado de la
	 *         partida
	 */
	public boolean Perdio() {
		return this.Perdio;
	}

	/**
	 * ---getGestorDeNivel--- Al ser singleton la clase este metodo se utiliza para
	 * obtener la instancia
	 * 
	 * @return Devuelve La UNICA instancia de Gestor de niveles
	 */
	public static GestorDeNivel getGestorDeNivel() {
		return GestorDeNivel;
	}

	/**
	 * Getter Nivel Actual
	 * 
	 * @return Devuelve el nivel actual
	 */
	public int getNivelActual() {
		return NivelActual;
	}

	public PuntajeJugador getPuntajeJugador() {
		return puntajeJugador;
	}

	// Obtengo aqui las estructuras necesarias Del Gestor De Estructuras
	// para mejorar la LEGIBILIDAD
	private LinkedList<LinkedList<Enemigo>> enemigosEnEspera = GestorEstructuras.getGestorEstructuras().getEnemigosEnEspera();
	private LinkedList<Enemigo> enemigosEnPantalla = GestorEstructuras.getGestorEstructuras().getEnemigosEnPantalla();
	private LinkedList<Explosion> explosionesEnPantalla = GestorEstructuras.getGestorEstructuras().getExplosionesEnPantalla();
	private Ciudad[] ciudades = GestorEstructuras.getGestorEstructuras().getCiudades();
	private Base[] bases = GestorEstructuras.getGestorEstructuras().getBases();
	private LinkedList<MisilAntibalistico> misilesAliadosEnPantalla = GestorEstructuras.getGestorEstructuras().getMisilesAliadosEnPantalla();

}