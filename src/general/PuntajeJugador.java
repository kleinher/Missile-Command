package general;

import java.util.Scanner;

public class PuntajeJugador extends TablaDePuntajes{
	//Clase que implemente Singleton
	private static PuntajeJugador puntajeJugador=new PuntajeJugador();
	private Integer score;
	private String nombre;
	
	private PuntajeJugador() {
		this.score = 0;
		this.nombre = leerNombre();
	}
	public static PuntajeJugador getPuntajeJugador() {
		return puntajeJugador;
	}
	

	public static void actualizarScore(int puntos) {
		PuntajeJugador.getPuntajeJugador().score+=puntos;
	}
	/*LEER NOMBRE JUGADOR
	 * Solicita en pantalla que se carga un nuevo jugador
	 * */
	public String leerNombre(){

		Scanner in = new Scanner(System.in);
		String nombre = new String();
		
		System.out.println("Jugador: ");
		nombre = in.nextLine();
		return nombre;
	}
}
