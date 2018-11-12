package modelo.gestores;

import java.util.LinkedList;

import controlador.Controlador;
import modelo.Aliados.Base;
import modelo.Aliados.Ciudad;
import modelo.Aliados.Explosion;
import modelo.enemigos.*;
import modelo.usuario.PuntajeJugador;
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
	private int tics;
	private boolean Perdio;
	public GestorDeEstructuras getEstructuras() {
		return estructuras;
	}

	private int NivelActual;
	private GestorDeEstructuras estructuras;
	// Delay para la clase graficador
	final private int delayMilis = 40;

	static GestorDeNivel GestorDeNivel = new GestorDeNivel();

	// private static Posicion[] vectorDePosicionesDeEstructurasAliadas;
	// Esto que esta aca arriba ya estaba asi cuando llegue jaja

	/**
	 * ---CONSTRUCTOR GESTOR DE NIVEL---- Se inicializan las Ciudades y Bases con
	 * sus datos iniciales
	 */
	private GestorDeNivel() {
		tics=0;
		Misiles.DeterminarPosicionesDeLasbases();
		Oleada.DeterminarArregloDeMisiles();
		estructuras = new GestorDeEstructuras();
	}

	/**
	 * -------------LOOP NIVEL-----------
	 * 
	 * En este metodo se va a iterar todo el nivel, basicamente es el metodo
	 * principal del juego
	 * 
	 * Lanza las oleadas de enemigos por cada nivel, realiza el cambio de dificultad
	 * conforme se avanza en los niveles, y dibuja los objetos dibujables en la pantalla
	 * 
	 * @throws InterruptedException
	 */
	public void loopDelNivel() throws InterruptedException {
		if(estructuras.EnemigosEnEspera==null) {
			estructuras.gestionarEstructuras();
		}
		//Cuando termine el nivel
		if((estructuras.EnemigosEnEspera.isEmpty())) {
			//Actualizo puntaje al final del nivel
			PuntajeJugador.ActualizarPuntaje(this.NivelActual, estructuras.Ciudades, estructuras.Bases);
			
			//En este gestionar de estructuras se estan actualizando 
			//todas las estructuras para un nivel en especifico
			estructuras.gestionarEstructuras();
		}
		// Mientras hayan enemigos(No termino el nivel)
		else {
			// Cuando pasa 1 segundo, lanzo otra oleada de enemigos
			if (tics == 20) {
				// Lanzo una nueva oleada de enemigos
				Enemigo.lanzarEnemigos(estructuras.EnemigosEnEspera.poll(), estructuras.EnemigosEnPantalla);
				tics = 0;
			}
			//actualizo el tamanio de las explosiones
			actualizarTamanioDeExplosiones(estructuras.explosionesEnPantalla);
			//Grafica
			Graficador.refrescarTopDown(estructuras.ActualizarListaDibujables(), delayMilis);
			//actualiza posiciones
			estructuras.actualizarPosiciones();
			//comprueba colisiones
			Colisiones.comprobarColision(estructuras.EnemigosEnPantalla, estructuras.explosionesEnPantalla,
					estructuras.Ciudades, estructuras.Bases, estructuras.MisilesAliadosEnPantalla);
			tics++;
			if (!Ciudad.hayCiudades(estructuras.Ciudades)) {
				Perdio=true;
			}
		}
	}

	private void actualizarTamanioDeExplosiones(LinkedList<Explosion> explosionesEnPantalla) {
		LinkedList<Explosion> ListaDeExplosionesAeliminar=Explosion.determinarTamanio(estructuras.explosionesEnPantalla);
		estructuras.explosionesEnPantalla.removeAll(ListaDeExplosionesAeliminar);		
	}

	/**
	 * METODO MAIN- Donde sucede la magia
	 * 
	 * @param args
	 * @throws InterruptedException
	 */

	public void modelar() throws InterruptedException {
		PuntajeJugador puntaje = new PuntajeJugador();
		this.loopDelNivel();
		PuntajeJugador.ActualizarPuntaje(this.NivelActual, this.estructuras.Ciudades, this.estructuras.Bases); 
		// TablaDePuntajes.actualizarTablaDePuntajes(nivel.getPuntajeJugador().getScore(),
		// nivel.getPuntajeJugador().getNombre());
	}

	/**
	 * 
	 * @return Devuelve un booleano si perdio o no, en funi�n del estado de la
	 *         partida
	 */

	/**
	 * ---getGestorDeNivel--- Al ser singleton la clase este metodo se utiliza para
	 * obtener la instancia
	 * 
	 * @return Devuelve La UNICA instancia de Gestor de niveles
	 */
	public static GestorDeNivel getGestorDeNivel() {

		return GestorDeNivel;
	}

	public boolean Perdio() {

		return this.Perdio;
	}

	/**
	 * METODO TERMINAR En teoria, guarda todo lo que se tenga que guardar, como los
	 * puntajes. Imprime 'GameOver', o un blue Screen para asustar al usuario
	 * desprevenido
	 */
	public void terminarJuego() {
		/* Guarda los puntajes en la tabla de puntajes */ /* Falta Pasarle todos los argumentos que necesita */
		// tablaDePuntajes.actualizarTablaDePuntajes();
		/* Imprimer Game Over */
		System.out.println("Game Over");
	}

	public int getNivelActual() {
		return this.NivelActual;
	}

}
