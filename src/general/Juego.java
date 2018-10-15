package general;

import enemigos.Misiles;

/**
 * Esta clase Representa al menu inicial del juego, que permite comenzar un
 * juego nuevo, asi como consultar la tabla de puntajes, etc
 * 
 * @author LosPi
 *
 */
public class Juego {
	private static PuntajeJugador puntajeJugador= new PuntajeJugador();
	/**
	 * CONSTRUCTOR
	 *
	 * se inicializan todas las variables
	 * 
	 * 
	 */
	// ACA VA EL CONSTRUCTOR MAN

	/**
	 * METODO MAIN- Donde sucede la magia
	 * 
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String args[]) throws InterruptedException {
		GestorDeNivel nivel = GestorDeNivel.getGestorDeNivel();
		Oleada.DeterminarArregloDeMisiles();
		Misiles.DeterminarPosicionesDeLasbases();
		
		while (!nivel.Perdio()) {
			nivel.gestionarNivel();
			nivel.loopDelNivel(Juego.puntajeJugador);
			// PuntajeJugador.actualizarTablaDePuntajes(); // PARAMETROS

		}
		terminarJuego();
		TablaDePuntajes.actualizarTablaDePuntajes(nivel.getPuntajeJugador().getScore(), nivel.getPuntajeJugador().getNombre());
	}

	/**
	 * METODO TERMINAR En teoria, guarda todo lo que se tenga que guardar, como los
	 * puntajes. Imprime 'GameOver', o un blue Screen para asustar al usuario
	 * desprevenido
	 */
	public static void terminarJuego() {
		/* Guarda los puntajes en la tabla de puntajes */ /* Falta Pasarle todos los argumentos que necesita */
		// tablaDePuntajes.actualizarTablaDePuntajes();
		/* Imprimer Game Over */
		System.out.println("Game Over");
	}

}
