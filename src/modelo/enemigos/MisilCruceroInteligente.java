package modelo.enemigos;

import taller2.grafico.Dibujable;
import taller2.grafico.InformacionDibujable;

/*Implementa lo mismo que misiles*/
public class MisilCruceroInteligente extends Misiles{
	public MisilCruceroInteligente() {
		super();
		this.puntos = 125;
	}
	/**
	 * 
	 * @param esquivarIzquierda
	 */
	public void esquivar(boolean esquivarIzquierda) {
		if (esquivarIzquierda) {
			this.posicionActual.actualizarPosicion(this.posicionActual.getPosicionX() - 4,
					this.posicionActual.getPosicionY());
		} else
			this.posicionActual.actualizarPosicion(this.posicionActual.getPosicionX() + 4,
					this.posicionActual.getPosicionY());
	}


	// Aqui recalcular la recta que determina objetivos, que va a ser diferente
}