package enemigos;

import taller2.grafico.InformacionDibujable;

/*Implementa lo mismo que misiles*/
public class MisilCruceroInteligente extends Misiles {
	public MisilCruceroInteligente() {
		super();
		this.puntos = 125;
	}

	public InformacionDibujable getInformacionDibujable() {
		InformacionDibujable dib = new InformacionDibujable(this.getPosicionActual().getPosicionX(),
				this.getPosicionActual().getPosicionY(), 'I');
		return dib;
	}
}
