package puntajes;

import java.util.Scanner;

import gestores.GestorDeNivel;
import gestores.GestorEstructuras;

public class PuntajeJugador extends TablaDePuntajes{
	private static Integer score;
	private String nombre;

	//
	private void contarPuntajes(PuntajeJugador puntajeJugador) {
		int contadorCiudades = 0;
		int contadorBases = 0;

		for (int i = 1; i < 7; i++) {
			if (GestorEstructuras.getGestorEstructuras().getCiudades()[i].estaViva()) {
				contadorCiudades++;
			}
		}
		for (int i = 1; i < 4; i++) {
			contadorBases += GestorEstructuras.getGestorEstructuras().getBases()[i].getCantMisiles();
		}
		puntajeJugador.CalcularPuntajePorNivel(GestorDeNivel.getGestorDeNivel().getNivelActual(), contadorBases, contadorCiudades);

	}


	private static int [] VDePuntajes= new int[4];

	public static void actualizarScore(int puntos) {
		score+=puntos;
	}
	public PuntajeJugador() {
		InicializarTablaDePuntajePorCadaEnemigo();
		this.score = 0;
		//this.nombre = leerNombre();
	}
	public  Integer getScore() {
		return score;
	}
	/*LEER NOMBRE JUGADOR
	 * Solicita en pantalla que se carga un nuevo jugador
	 * */
	public String leerNombre(){

		Scanner in = new Scanner(System.in);
		String nombre = new String();
		
		System.out.println("Jugador: ");
		nombre = in.nextLine();
		in.close();
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
	
	public void CalcularPuntajePorNivel(int NivelAct, int misilesAliadosSinU, int ciuVi) {
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
		this.score += (MultiplicadorPorNivel*((VDePuntajes[2]*misilesAliadosSinU)+(VDePuntajes[3]*ciuVi)));
	}
	public String getNombre() {
		return nombre;
	}
	
	public static int[] getVDePuntajes() {
		return VDePuntajes;
	}
	public static void setVDePuntajes(int[] vDePuntajes) {
		VDePuntajes = vDePuntajes;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
