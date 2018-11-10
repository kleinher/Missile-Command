package modelo.enemigos;

import taller2.grafico.Dibujable;
import taller2.grafico.InformacionDibujable;

/*Implementa lo mismo que misiles*/
public class MisilBalistico extends Misiles implements Dibujable{
	@Override
	public InformacionDibujable getInformacionDibujable() {
		InformacionDibujable info = new InformacionDibujable(this.posicionActual.getPosicionX(),this.posicionActual.getPosicionY() , '*');
		return info;
	}
	public MisilBalistico() {
		super();
	}
}
