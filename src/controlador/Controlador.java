package controlador;

import Ventanas.Juego;

public class Controlador {
	public static Juego Juego;
	public Controlador() {
		Juego = new Juego();
		Juego.dibujarPantalla();
	}
	
}