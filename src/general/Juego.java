package general;

public class Juego {
	private static TablaDePuntajes tablaDePuntajes;
	
	/*CONSTRUCTOR
	*
	* e inicializan todas las variables 
	* 
	* 
	*/
	private Juego() {
		//tablaDePuntajes = archivo?
	}


	/*METODO MAIN
	*
	*aca pasa todo lo bueno 
	*
	*/
	public static void main(String args[]) throws InterruptedException {
		GestorDeNivel nivel = GestorDeNivel.getGestorDeNivel();
		while(!nivel.Perdio()) 
		{
			nivel.gestionarNivel();
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
		System.out.println("Game Over");
	}

}
