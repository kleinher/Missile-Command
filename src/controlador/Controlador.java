package controlador;

import Ventanas.Juego;

public class Controlador {
	public static Juego Juego;
	public static void main(String args[]) {
		Juego = new Juego();
		Juego.Controlar();
	}
	
}
