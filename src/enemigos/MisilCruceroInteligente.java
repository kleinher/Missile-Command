package enemigos;

import taller2.grafico.Dibujable;
import taller2.grafico.InformacionDibujable;

/*Implementa lo mismo que misiles*/
public class MisilCruceroInteligente extends Misiles implements Dibujable {
	public MisilCruceroInteligente() {
		super();
		this.puntos = 125;
	}

	@Override
	public InformacionDibujable getInformacionDibujable() {
		InformacionDibujable info = new InformacionDibujable(this.posicionActual.getPosicionX(),this.posicionActual.getPosicionY() , 'O');
		return info;

	}

	/**
	 * 
	 * @param esquivarIzquierda
	 */
	public void esquivar(boolean esquivarIzquierda) {
		if (esquivarIzquierda) {
			this.posicionActual.actualizarPosicion(this.posicionActual.getPosicionX() - 2,
					this.posicionActual.getPosicionY());
		} else
			this.posicionActual.actualizarPosicion(this.posicionActual.getPosicionX() + 2,
					this.posicionActual.getPosicionY());
	}

	// Aqui recalcular la recta que determina objetivos, que va a ser diferente
}