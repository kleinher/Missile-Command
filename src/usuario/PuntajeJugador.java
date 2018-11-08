package usuario;

import java.util.Scanner;

import Aliados.Base;
import Aliados.Ciudad;
import gestores.Colisiones;
import gestores.GestorDeNivel;

public class PuntajeJugador extends TablaDePuntajes{
	private Integer score;
	private String nombre;
	

	//
	public void ActualizarPuntaje(PuntajeJugador puntajeJugador,Ciudad[] Ciudades, Base[] Bases) {
		int contadorCiudades = 0;
		int contadorMisilesSinUxBases = 0;
		/* Cuento las ciudades vivas al terminar el nivel*/
		for (int i = 1; i < 7; i++) {
			if (Ciudades[i].estaViva()) {
				contadorCiudades++;
			}
		}
		/* Cuento los misiles sobrantes al terminar el nivel*/
		for (int i = 1; i < 4; i++) {
			contadorMisilesSinUxBases += Bases[i].getCantMisiles();
		}
		puntajeJugador.CalcularPuntajePorNivel(GestorDeNivel.getGestorDeNivel().getNivelActual(), contadorMisilesSinUxBases, contadorCiudades,Colisiones.getMisilesEnemigosDestr(),Colisiones.getMisilesIntDestr(),Colisiones.getBombarderosDestr());

	}


	private static int [] VDePuntajes= new int[5];

	public void actualizarScore(int puntos) {
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
		//Puntaje por Bombardero destruido
		VDePuntajes[4]=25;
	}
	
	private void CalcularPuntajePorNivel(int nivelAct, int misilesAliadosSinU, int ciuVi, int misilesBalisElim, int misilesIntDest, int bomDest) {
		int MultiplicadorPorNivel=1;
		if(nivelAct>2) {
			switch (nivelAct) {
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
		/* Calcula en base a los datos obtenidos por cada nivel sobre el desempeño del jugador multiplcandolo por el valor de puntaje de cada accion*/
		this.score += (MultiplicadorPorNivel*((VDePuntajes[0]*misilesBalisElim)+(VDePuntajes[1]*misilesIntDest)+(VDePuntajes[2]*misilesAliadosSinU)+(VDePuntajes[3]*ciuVi)+(VDePuntajes[4]*bomDest)));
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
