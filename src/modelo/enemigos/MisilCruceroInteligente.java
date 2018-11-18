package modelo.enemigos;

import modelo.gestores.GestorDeNivel;

/*Implementa lo mismo que misiles*/
public class MisilCruceroInteligente extends Misiles {

	public MisilCruceroInteligente(double velocidad) {

		super(velocidad);
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
		} else {
			this.posicionActual.actualizarPosicion(this.posicionActual.getPosicionX() + 4,
					this.posicionActual.getPosicionY());
		}
		this.determinarDesplazamiento(GestorDeNivel.getGestorDeNivel().getEstructuras().getVelocidad());
	}

	// Aqui recalcular la recta que determina objetivos, que va a ser diferente
}