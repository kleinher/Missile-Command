package general;

import java.util.Iterator;
import java.util.LinkedList;
import enemigos.*;
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

	static GestorDeNivel GestorDeNivel = new GestorDeNivel();
	// Variables de juego
	private int Dificultad;
	private boolean Perdio;
	private int NivelActual;
	private PuntajeJugador  puntajeJugador;



	/**
	 * @return Devuelve el nivel actual
	 */
	public int getNivelActual() {
		return NivelActual;
	}

	// Variables enemigas
	/* Lista de Enemigos Mostrados y procesados durante el nivel */
	private LinkedList<Enemigo> EnemigosEnPantalla;
	/*
	 * Lista de Enemigos en espera (Oleada) por ser procesados y aparecer en el
	 * nivel
	 */
	private LinkedList<LinkedList<Enemigo>> EnemigosEnEspera;

	// Variables aliadas
	private LinkedList<MisilAntibalistico> MisilesAliadosEnPantalla;
	private LinkedList<Explosion> explosionesEnPantalla;
	private Ciudad Ciudades[];
	private Base Bases[];

	// private static Posicion[] vectorDePosicionesDeEstructurasAliadas;
	// Esto que esta aca arriba ya estaba asi cuando llegue jaja

	/**
	 * ---CONSTRUCTOR GESTOR DE NIVEL---- Se inicializan las Ciudades y Bases con
	 * sus datos iniciales
	 */
	private GestorDeNivel() {

		this.puntajeJugador = new PuntajeJugador();
		
		this.EnemigosEnPantalla = new LinkedList<Enemigo>();
		
		// Instancia las nueve ciudades
		this.Ciudades=new Ciudad[7];
		Ciudad.InstanciarCiudades(this.Ciudades);

		// Instancia las tres bases
		this.Bases=new Base[4];
		Base.InstanciarBases(this.Bases);

		this.Perdio = false;
		//
		this.Dificultad = 15;

	}

	/**
	 * ---GESTIONAR NIVEL--- Funcion: Modifica la instancia nivel(de Juego) cada ves
	 * que comienza un nuevo nivel
	 */
	public void gestionarNivel() {

		this.NivelActual++;
		
		// Crea la lista de enemigos del nivel
		this.EnemigosEnEspera = new LinkedList<LinkedList<Enemigo>>();
		Oleada.CrearListaDeOleadasPorNivel(EnemigosEnEspera, NivelActual);
		explosionesEnPantalla=new LinkedList<Explosion>();
		MisilesAliadosEnPantalla=new LinkedList<MisilAntibalistico>();
		// Incrementa la dificultad cuando aumenta un nivel
		this.Dificultad += 5;

		// REVIVIR CIUDAD EN CASO DE

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
	public void loopDelNivel(PuntajeJugador puntajeJugador)
			throws InterruptedException {
		int tics = 0;

		// Lanzo la primer oleada de enemigos
		Enemigo.lanzarEnemigos(EnemigosEnEspera.poll(), EnemigosEnPantalla);
		// LANZAR MISILES ALIADOS HARDCODEADOS

		// Mientras hayan enemigos
		while (!EnemigosEnEspera.isEmpty()) {

			// Cuando pasa 1 segundo, lanzo otra oleada de enemigos
			if (tics == Dificultad) {
				// Lanzo una nueva oleada de enemigos
				Enemigo.lanzarEnemigos(EnemigosEnEspera.poll(), EnemigosEnPantalla);
				tics = 0;
			}

			this.actualizarPosiciones();
			Colisiones.comprobarColision(EnemigosEnPantalla, explosionesEnPantalla, Ciudades, Bases);
			// dibujar();
			Thread.sleep(1000 / Dificultad);
			tics++;
		}
		if (!Ciudad.hayCiudades(Ciudades)) {
			this.Perdio = true;
		}
		 //contarPuntajes(puntajeJugador);
		
	}

	private void contarPuntajes(PuntajeJugador puntajeJugador) {
		int contadorCiudades=0;
		int contadorBases=0;
		
		for(int i = 1; i<7;i++) {
			if(Ciudades[i].estaViva()) {
				contadorCiudades++;
			}
		}
		for(int i = 1; i<4;i++) {
			contadorBases+=Bases[i].getCantMisiles();
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
		for (Iterator<Enemigo> i = EnemigosEnPantalla.iterator(); i.hasNext();) {
			Enemigo enemigo = i.next();
			enemigo.mover();
		}

		// Actualiza la posicion de los misiles aliados
		for (Iterator<MisilAntibalistico> i = MisilesAliadosEnPantalla.iterator(); i.hasNext();) {
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
	public PuntajeJugador getPuntajeJugador() {
		return puntajeJugador;
	}
}
