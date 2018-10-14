package enemigos;

import taller2.grafico.InformacionDibujable;

/*Implementa lo mismo que misiles*/
public class MisilCrucero extends Misiles{
	public MisilCrucero() {
		super();
		this.puntos=125;
	}
	public InformacionDibujable getInformacionDibujable() {
		InformacionDibujable dib = new InformacionDibujable(this.getPosicionActual().getPosicionX(),
				this.getPosicionActual().getPosicionY(), 'C');
		return dib;
	}
}
