package general;

public class Juego {
	private static TablaDePuntajes tablaDePuntajes;
	
	/*CONSTRUCTOR
	*
	* se inicializan todas las variables 
	* 
	* 
	*/

	/*METODO MAIN
	*
	*aca pasa todo lo bueno 
	*
	*/
	public static void main(String args[]) throws InterruptedException {
		PuntajeJugador puntajeJugador = PuntajeJugador.getPuntajeJugador();
		GestorDeNivel nivel = GestorDeNivel.getGestorDeNivel();
		Oleada.DeterminarArregloDeMisiles();
		while(!nivel.Perdio()) 
		{
			nivel.gestionarNivel();
			nivel.loopDelNivel();
			//PuntajeJugador.actualizarTablaDePuntajes(); // PARAMETROS
			
		}
		terminarJuego();
		TablaDePuntajes.actualizarTablaDePuntajes(PuntajeJugador.getScore(), PuntajeJugador.getNombre());
	}
	/*METODO TERMINAR
	 * Pensado para guardar todo lo que se tenga que guardar
	 * Imprimir game over
	 */
	public static void terminarJuego() {
		/* Guarda los puntajes en la tabla de puntajes */ /*Falta Pasarle todos los argumentos que necesita*/
		//tablaDePuntajes.actualizarTablaDePuntajes();
		/* Imprimer Game Over */
		System.out.println("Game Over");
	}

}
