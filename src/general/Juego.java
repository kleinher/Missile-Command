package general;

public class Juego {
	private static Juego juego = new Juego();
	private static int nivelActual;
	private static TablaDePuntajes tablaDePuntajes;
	private static PuntajeJugador puntaje;
	
	/*CONSTRUCTOR
	*
	* e inicializan todas las variables 
	* 
	* 
	*/
	private Juego() {
		nivelActual = new Integer(1);
		//tablaDePuntajes = archivo?
		puntaje = new PuntajeJugador();
	}


	/*METODO MAIN
	*
	*aca pasa todo lo bueno 
	*
	*/
	public static void main(String args[]) throws InterruptedException {
		Nivel nivel = new Nivel();
		while(nivel.getPerdio()) {
			nivel = new Nivel();
			nivel.loopDelNivel();
		}
		terminarJuego();
	}
	
	/*METODO TERMINAR
	 * Pensado para guardar todo lo que se tenga que guardar
	 * Imprimir game over
	 */
	public static void terminarJuego() {
		/* Guarda los puntajes en la tabla de puntajes */
		tablaDePuntajes.actualizarTablaDePuntajes();
		/* Imprimer Game Over */
		System.out.println("Game Over prro");
	}
	

	public static int getNivelActual() {
		return nivelActual;
	}
	
	public void Pausar() {
		
	}
	public void Salir() {
		
	}
	
	
}
