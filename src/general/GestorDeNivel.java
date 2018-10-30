package general;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import enemigos.*;
import taller2.grafico.Dibujable;
import taller2.modelo.Graficador;

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
	final private int delayMilis = 1000;

	private static GestorDeNivel GestorDeNivel = new GestorDeNivel();
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

		// int tics = 0;

		// Lanzo la primer oleada de enemigos
		Enemigo.lanzarEnemigos(GestorEstructuras.getGestorEstructuras().getEnemigosEnEspera().poll(),
				GestorEstructuras.getGestorEstructuras().getEnemigosEnPantalla());

		// LANZAR MISILES ALIADOS HARDCODEADOS
		Base.Disparar(GestorEstructuras.getGestorEstructuras().getBases()[1],
				GestorEstructuras.getGestorEstructuras().getMisilesAliadosEnPantalla());
		Base.Disparar(GestorEstructuras.getGestorEstructuras().getBases()[1],
				GestorEstructuras.getGestorEstructuras().getMisilesAliadosEnPantalla());
		Base.Disparar(GestorEstructuras.getGestorEstructuras().getBases()[1],
				GestorEstructuras.getGestorEstructuras().getMisilesAliadosEnPantalla());

		// Mientras hayan enemigos
		while (!GestorEstructuras.getGestorEstructuras().getEnemigosEnEspera().isEmpty()) {
			Graficador.refrescarTopDown(ActualizarListaDibujables(), delayMilis);
			// Cuando pasa 1 segundo, lanzo otra oleada de enemigos
			if (true) {
				// Lanzo una nueva oleada de enemigos
				Enemigo.lanzarEnemigos(GestorEstructuras.getGestorEstructuras().getEnemigosEnEspera().poll(),
						GestorEstructuras.getGestorEstructuras().getEnemigosEnPantalla());
				// tics = 0;
			}

			this.actualizarPosiciones();
			Colisiones.comprobarColision(GestorEstructuras.getGestorEstructuras().getEnemigosEnPantalla(),
					GestorEstructuras.getGestorEstructuras().getExplosionesEnPantalla(), GestorEstructuras.getGestorEstructuras().getCiudades(), GestorEstructuras.getGestorEstructuras().getBases());
			// dibujar();
			// Thread.sleep(1000 / Dificultad);
			// tics++;
		}
		if (!Ciudad.hayCiudades(GestorEstructuras.getGestorEstructuras().getCiudades())) {
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
		for (Iterator<Enemigo> i = GestorEstructuras.getGestorEstructuras().getEnemigosEnPantalla().iterator(); i
				.hasNext();) {
			listaDibujables.add(i.next());
		}
		/* Agrego las explosiones que estan en pantalla */
		for (Iterator<Explosion> i = GestorEstructuras.getGestorEstructuras().getExplosionesEnPantalla().iterator(); i.hasNext();) {
			listaDibujables.add(i.next());
		}
		/* Agrego los misiles aliados que estan en pantalla */
		for (Iterator<MisilAntibalistico> i = GestorEstructuras.getGestorEstructuras().getMisilesAliadosEnPantalla()
				.iterator(); i.hasNext();) {
			listaDibujables.add(i.next());
		}
		/* Agrego las ciudades y las bases */
		for (int i = 1; i < GestorEstructuras.getGestorEstructuras().getBases().length; i++) {
			if (GestorEstructuras.getGestorEstructuras().getBases()[i].isEstaViva())
				listaDibujables.add(GestorEstructuras.getGestorEstructuras().getBases()[i]);
		}
		for (int i = 1; i < GestorEstructuras.getGestorEstructuras().getCiudades().length; i++) {
			if (GestorEstructuras.getGestorEstructuras().getCiudades()[i].estaViva())
				listaDibujables.add(GestorEstructuras.getGestorEstructuras().getCiudades()[i]);
		}
		return listaDibujables;
	}

	//
	private void contarPuntajes(PuntajeJugador puntajeJugador) {
		int contadorCiudades = 0;
		int contadorBases = 0;

		for (int i = 1; i < 7; i++) {
			if (GestorEstructuras.getGestorEstructuras().getCiudades()[i].estaViva()) {
				contadorCiudades++;
			}
		}
		for (int i = 1; i < 4; i++) {
			contadorBases += GestorEstructuras.getGestorEstructuras().getBases()[i].getCantMisiles();
		}
		puntajeJugador.CalcularPuntajePorNivel(NivelActual, contadorBases, contadorCiudades);

	}

	/**
	 * ---ActualizarPosiciones--- Este metodo actualiza las posiciones de todos los
	 * objetos del juego
	 * 
	 */
	private void actualizarPosiciones() {

		// Actualiza posiciones de los de los enemigos
		for (Iterator<Enemigo> i = GestorEstructuras.getGestorEstructuras().getEnemigosEnPantalla().iterator(); i
				.hasNext();) {
			Enemigo enemigo = i.next();
			enemigo.mover();
		}

		// Actualiza la posicion de los misiles aliados
		for (Iterator<MisilAntibalistico> i = GestorEstructuras.getGestorEstructuras().getMisilesAliadosEnPantalla()
				.iterator(); i.hasNext();) {
			MisilAntibalistico misil = i.next();
			misil.mover();
		}

		// ACTUALIZAR EXPLOSIONES

		// ACTUALIZAR ESTELA
	}

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
	
	/** Getter Nivel Actual
	 * @return Devuelve el nivel actual
	 */
	public int getNivelActual() {
		return NivelActual;
	}

	public PuntajeJugador getPuntajeJugador() {
		return puntajeJugador;
	}
}
