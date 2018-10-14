package general;

/**
 * Esta clase Representa al menu inicial del juego, que permite comenzar un
 * juego nuevo, asi como consultar la tabla de puntajes, etc
 * 
 * @author LosPi
 *
 */
public class Juego {
	private static TablaDePuntajes tablaDePuntajes;

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
		PuntajeJugador puntajeJugador = PuntajeJugador.getPuntajeJugador();
		GestorDeNivel nivel = GestorDeNivel.getGestorDeNivel();
		Oleada.DeterminarArregloDeMisiles();
		while (!nivel.Perdio()) {
			nivel.gestionarNivel();
			nivel.loopDelNivel();
			// PuntajeJugador.actualizarTablaDePuntajes(); // PARAMETROS

		}
		terminarJuego();
		TablaDePuntajes.actualizarTablaDePuntajes(PuntajeJugador.getScore(), PuntajeJugador.getNombre());
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
