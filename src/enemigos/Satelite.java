package enemigos;

import taller2.grafico.InformacionDibujable;

/*Implementa lo mismo que misiles*/
public class Satelite extends Bombardero{

	
	public InformacionDibujable getInformacionDibujable() {
		InformacionDibujable dib = new InformacionDibujable(this.getPosicionActual().getPosicionX(),
				this.getPosicionActual().getPosicionY(), 'S');
		return dib;
	}
}
