package clasesPadres;

import java.util.Random;

import gestores.Posicion;

public abstract class Misiles extends Enemigo {
	private static Posicion[] posicionDeLasBasesYCiudades;

	public Misiles() {
		determinarObjetivo();
		determinarInicio();
	}

	/*
	 * Este metodo va a calcular el desplazamiento del misil balistico
	 * interplanetario(Enemigo)
	 */
	public void mover() {
		double pendiente;
		double distancia;
		int distanciaX;
		int distanciaY;
		int movimientoX;
		int movimientoY;

		// Calculo de los catetos x e y(desplazamiento en x e y)
		distanciaX = this.posicionInicial.getPosicionX() - this.posicionObjetivo.getPosicionX();
		distanciaY = this.posicionInicial.getPosicionY() - this.posicionObjetivo.getPosicionY();

		// Calculo de la hipotenusa(distancia)
		distancia = Math.sqrt(Math.pow(distanciaX, 2) + Math.pow(distanciaY, 2));

		// Calculo de la pendiente de la hipotenusa(distancia)
		pendiente = distanciaX / distanciaY;

		// Calculo del movimiento en X
		movimientoX = (int) Math.sqrt(Math.pow(distancia, 2) / (1 + Math.pow(pendiente, 2)));
		movimientoY = (int) pendiente * movimientoX;

		// En base a la direccion se elige el signo correcto
		if (distanciaX < 0) {
			movimientoX = ((-1) * movimientoX);
		}
		// Actualiza la posicion
		this.posicionActual.actualizarPosicion(this.posicionActual.getPosicionX() + movimientoX,
				this.posicionActual.getPosicionY() + movimientoY);
	}

	/**
	 * Determina El objetivo de cada misil de manera aleatoria
	 */
	public void determinarObjetivo() {
		int posicionGeneralObjetivo;
		int posicionObjetivoX;
		int posicionObjetivoY;
		Random aleatorio = new Random();
		posicionGeneralObjetivo = 1 + (aleatorio.nextInt(9));

		posicionObjetivoX = posicionDeLasBasesYCiudades[posicionGeneralObjetivo].getPosicionX();
		posicionObjetivoY = posicionDeLasBasesYCiudades[posicionGeneralObjetivo].getPosicionY();
		this.posicionObjetivo.actualizarPosicion(posicionObjetivoX, posicionObjetivoY);
	}

	/*
	 * Determina el inicio comenzando siempre en el inicio de la pantalla en la
	 * coordenada 'y', y de manera completamente aleatoria en la coordenada 'x'
	 */
	public void determinarInicio() {
		int aparicionEnX;
		int aparicionEnY = 0;
		Random aleatorio = new Random();
		aparicionEnX = (aleatorio.nextInt(525));
		this.posicionInicial.actualizarPosicion(aparicionEnX, aparicionEnY);
		this.posicionActual.actualizarPosicion(aparicionEnX, aparicionEnY);
	}


	/*
	 * Determina la posicion de las bases y las ciudades para facilitar el
	 * determinarObjetivo de cada misil
	 */
	public static void DeterminarPosicionesDeLasbases() {
		int posX = 40;
		int posY = 450;
		posicionDeLasBasesYCiudades = new Posicion[10];
		for (int i = 1; i <= 9; i++) {
			posicionDeLasBasesYCiudades[i] = new Posicion();
			posicionDeLasBasesYCiudades[i].actualizarPosicion(posX, posY);
			posX += 55;
		}

	}

}