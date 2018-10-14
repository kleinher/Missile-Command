package enemigos;

import taller2.grafico.InformacionDibujable;

/*Implementa lo mismo que misiles*/
public class MisilBalistico extends Misiles {
	
	
	
	
	public InformacionDibujable getInformacionDibujable() {
		InformacionDibujable dib = new InformacionDibujable(this.getPosicionActual().getPosicionX(),
				this.getPosicionActual().getPosicionY(), 'M');
		return dib;
	}
}
