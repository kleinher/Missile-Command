package general;

import java.util.Scanner;

public class PuntajeJugador extends TablaDePuntajes{
	private Integer score;
	private String nombre;
	public PuntajeJugador() {
		this.score = 0;
		this.nombre = leerNombre();
	}
	
	
	
	
	
	
	/*LEER NOMBRE JUGADOR
	 * Solicita en pantalla que se carga un nuevo jugador
	 * */
	public String leerNombre() {
		Scanner in = new Scanner(System.in);
		String nombre = new String();
		
		System.out.println("Jugador: ");
		nombre = in.nextLine();
		return nombre;
	}
}
