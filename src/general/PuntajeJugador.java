package general;

import java.util.Scanner;

public class PuntajeJugador extends TablaDePuntajes{
	private Integer score;
	private static String nombre;

	public Integer getScore() {
		return score;
	}
	public String getNombre() {
		return nombre;
	}

	private static int [] VDePuntajes= new int[3];
	
	public PuntajeJugador() {
		InicializarTablaDePuntajePorCadaEnemigo();
		this.score = 0;
		//this.nombre = leerNombre();
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
	
	private void InicializarTablaDePuntajePorCadaEnemigo() {
		
		//Puntaje por matar un Misil Basico
		VDePuntajes[0]=25;
		//Puntaje por matar un Misil Crucero
		VDePuntajes[1]=125;
		//Puntaje por misil aliado sin usar
		VDePuntajes[2]=5;
		//Puntaje por ciudades vivas
		VDePuntajes[3]=100;
	}
	
	public void CalcularPuntajePorNivel(int NivelAct, int cantMisDest, int cantMisCruDest, int misilesAliadosSinU, int ciuVi) {
		int MultiplicadorPorNivel=1;
		if(NivelAct>2) {
			switch (NivelAct) {
				case 3:MultiplicadorPorNivel=2;
				break;
				case 4:MultiplicadorPorNivel=2;
				break;
				case 5:MultiplicadorPorNivel=3;
				break;
				case 6:MultiplicadorPorNivel=3;
				break;
				case 7:MultiplicadorPorNivel=4;
				break;
				case 8:MultiplicadorPorNivel=4;
				break;
				case 9:MultiplicadorPorNivel=5;
				break;
				case 10:MultiplicadorPorNivel=5;
				break;
				default:MultiplicadorPorNivel=6;
				break;
				}
		}
		score += (MultiplicadorPorNivel*((VDePuntajes[0]*cantMisDest)+(VDePuntajes[1]*cantMisCruDest)+(VDePuntajes[2]*misilesAliadosSinU)+(VDePuntajes[3]*ciuVi)));
	}
}
