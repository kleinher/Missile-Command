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
	//Delay para la clase graficador
	final private int delayMilis=1000;
	
	static GestorDeNivel GestorDeNivel = new GestorDeNivel();
	// Variables de juego
	private int Dificultad;
	private boolean Perdio;
	private int NivelActual;
	private PuntajeJugador  puntajeJugador;

	List<? extends Dibujable> listaDibujables;

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
		this.NivelActual=1;
		this.puntajeJugador = new PuntajeJugador();
		this.MisilesAliadosEnPantalla= new LinkedList<MisilAntibalistico>();
		this.EnemigosEnPantalla = new LinkedList<Enemigo>();
		
		// Instancia las nueve ciudades
		this.Ciudades=new Ciudad[7];
		Ciudad.InstanciarCiudades(this.Ciudades);

		// Instancia las tres bases
		this.Bases=new Base[4];
		Base.InstanciarBases(this.Bases);
		this.Perdio = false;
		this.Dificultad = 15;
	}

	/**
	 * ---GESTIONAR NIVEL--- Funcion: Modifica la instancia nivel(de Juego) cada ves
	 * que comienza un nuevo nivel
	 */
	public void gestionarNivel() {
		// Crea la lista de enemigos del nivel
		this.EnemigosEnEspera = new LinkedList<LinkedList<Enemigo>>();
		Oleada.CrearListaDeOleadasPorNivel(EnemigosEnEspera, NivelActual);
		explosionesEnPantalla=new LinkedList<Explosion>();
		MisilesAliadosEnPantalla=new LinkedList<MisilAntibalistico>();
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
	public void loopDelNivel(PuntajeJugador puntajeJugador)throws InterruptedException {

		//int tics = 0;

		// Lanzo la primer oleada de enemigos
		Enemigo.lanzarEnemigos(EnemigosEnEspera.poll(), EnemigosEnPantalla);
		
		// LANZAR MISILES ALIADOS HARDCODEADOS
		Base.Disparar(Bases[1],MisilesAliadosEnPantalla);
		Base.Disparar(Bases[2],MisilesAliadosEnPantalla);
		Base.Disparar(Bases[3],MisilesAliadosEnPantalla);
		
		// Mientras hayan enemigos
		while (!EnemigosEnEspera.isEmpty()) {
			Graficador.refrescarTopDown(ActualizarListaDibujables(), delayMilis);
			// Cuando pasa 1 segundo, lanzo otra oleada de enemigos
			if (true) {
				// Lanzo una nueva oleada de enemigos
				Enemigo.lanzarEnemigos(EnemigosEnEspera.poll(), EnemigosEnPantalla);
				//tics = 0;
			}

			this.actualizarPosiciones();
			Colisiones.comprobarColision(EnemigosEnPantalla, explosionesEnPantalla, Ciudades, Bases);
			// dibujar();
			//Thread.sleep(1000 / Dificultad);
			//tics++;
			
		}
		if (!Ciudad.hayCiudades(Ciudades)) {
			this.Perdio = true;
		}
		 //contarPuntajes(puntajeJugador);
		
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
		for (Iterator<Enemigo> i = EnemigosEnPantalla.iterator(); i.hasNext();) {
			listaDibujables.add(i.next());
		}
		/* Agrego las explosiones que estan en pantalla */
		for (Iterator<Explosion> i = explosionesEnPantalla.iterator(); i.hasNext();) {
			listaDibujables.add(i.next());
		}
		/* Agrego los misiles aliados que estan en pantalla */
		for (Iterator<MisilAntibalistico> i = MisilesAliadosEnPantalla.iterator(); i.hasNext();) {
			listaDibujables.add(i.next());
		}
		/* Agrego las ciudades y las bases*/
		for(int i=1;i<Bases.length;i++) {
			if(Bases[i].isEstaViva())
				listaDibujables.add(Bases[i]);
		}
		for(int i=1;i<Ciudades.length;i++) {
			if(Ciudades[i].estaViva())
				listaDibujables.add(Ciudades[i]);
		}
		return listaDibujables;
	}

//

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
