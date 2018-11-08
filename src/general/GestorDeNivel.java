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
	private boolean Perdio;
	private int NivelActual;
	public GestorDeEstructuras estructuras;
	//Delay para la clase graficador
	final private int delayMilis=50;
	static GestorDeNivel GestorDeNivel = new GestorDeNivel();

	// private static Posicion[] vectorDePosicionesDeEstructurasAliadas;
	// Esto que esta aca arriba ya estaba asi cuando llegue jaja

	/**
	 * ---CONSTRUCTOR GESTOR DE NIVEL---- Se inicializan las Ciudades y Bases con
	 * sus datos iniciales
	 */
	private GestorDeNivel() {
		estructuras = new GestorDeEstructuras();
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
		estructuras.gestionarEstructuras();
		this.NivelActual = estructuras.getNivelActual();
		boolean Perdio = false;
		int tics = 0;

		// Lanzo la primer oleada de enemigos
		Enemigo.lanzarEnemigos(estructuras.EnemigosEnEspera.poll(), estructuras.EnemigosEnPantalla);
		
		// LANZAR MISILES ALIADOS HARDCODEADOS

		Base.Disparar(estructuras.Bases[1],estructuras.MisilesAliadosEnPantalla,40);
		Base.Disparar(estructuras.Bases[2],estructuras.MisilesAliadosEnPantalla,195);
		Base.Disparar(estructuras.Bases[3],estructuras.MisilesAliadosEnPantalla,360);
		
		// Mientras hayan enemigos
		while (!estructuras.EnemigosEnEspera.isEmpty()) {
			Graficador.refrescarTopDown(estructuras.ActualizarListaDibujables(), delayMilis);
			// Cuando pasa 1 segundo, lanzo otra oleada de enemigos
			if (tics == 30) {
				// Lanzo una nueva oleada de enemigos
				Enemigo.lanzarEnemigos(estructuras.EnemigosEnEspera.poll(), estructuras.EnemigosEnPantalla);
				tics = 0;
			}

			estructuras.actualizarPosiciones();
			Colisiones.comprobarColision(estructuras.EnemigosEnPantalla, 
					estructuras.explosionesEnPantalla,
					estructuras.Ciudades, estructuras.Bases,
					estructuras.MisilesAliadosEnPantalla);
			// dibujar();
			//Thread.sleep(1000 / Dificultad);
			tics++;
			
		}
		if (!Ciudad.hayCiudades(estructuras.Ciudades)) {
			Perdio = true;
		}
		 //contarPuntajes(puntajeJugador);
		
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
		// TODO Auto-generated method stub
		return this.Perdio;
	}



	public int getNivelActual() {
		// TODO Auto-generated method stub
		return this.NivelActual;
	}

}
