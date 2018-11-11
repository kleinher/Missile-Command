package modelo.general;

import taller2.grafico.Dibujable;
import taller2.grafico.InformacionDibujable;

/*Posicion general de todos los objetos */
public class Posicion implements Dibujable {
	private int posicionX;
	private int posicionY;

	public Posicion() {
		this.posicionX = 0;
		this.posicionY = 0;
	}

	public Posicion(Posicion pos) {
		this.posicionX = pos.getPosicionX();
		this.posicionY = pos.getPosicionY();
	}

	public void actualizarPosicion(int x, int y) {
		this.posicionX = x;
		this.posicionY = y;
	}

	public void actualizarPosicion(Posicion pos) {
		this.posicionX = pos.getPosicionX();
		this.posicionY = pos.getPosicionY();
	}

	public Integer getPosicionX() {
		return posicionX;
	}

	public Integer getPosicionY() {
		return posicionY;
	}

	public Posicion(Integer x, Integer y) {
		this.posicionX = x;
		this.posicionY = x;
	}

	/**
	 * Metodo equals para determinar si dos posiciones son iguales
	 */
	/*
	 * Se utiliza el valor 10, ya que es aproximadamente el error que se obtiene de
	 * trabajar las posiciones con double
	 */
	public boolean equals(Posicion pos) {
		if (((Math.sqrt(Math.pow((pos.getPosicionX() - this.posicionX), 2)
				+ Math.pow(this.posicionY - pos.getPosicionY(), 2))) < 10))
			return true;
		else
			return false;
	}
	public InformacionDibujable getInformacionDibujable() {
		InformacionDibujable info = new InformacionDibujable(this.posicionX,
				this.posicionY, '#');
		return info;
	}
	/*
	 * if ((this.posicionX==pos.getPosicionX()) &&
	 * (this.posicionY==pos.getPosicionY())) { return true; } else{ return false; }
	 */
}
