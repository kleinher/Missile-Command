package controlador;

import vista.Juego;

public class Controlador {
	public static Juego Juego;
	public Controlador() {
		Juego = new Juego();
		Juego.dibujarPantalla();
	}
	
}