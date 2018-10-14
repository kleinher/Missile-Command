package enemigos;

import taller2.grafico.InformacionDibujable;

public class Avion extends Bombardero{

	
	public InformacionDibujable getInformacionDibujable() {
		InformacionDibujable dib = new InformacionDibujable(this.getPosicionActual().getPosicionX(),
				this.getPosicionActual().getPosicionY(), 'A');
		return dib;
	}
}
