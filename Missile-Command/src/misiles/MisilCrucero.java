package misiles;

import clasesPadres.Misiles;
import taller2.grafico.Dibujable;
import taller2.grafico.InformacionDibujable;

/*Implementa lo mismo que misiles*/
public class MisilCrucero extends Misiles implements Dibujable{
	public MisilCrucero() {
		super();
		this.puntos=125;
	}

	@Override
	public InformacionDibujable getInformacionDibujable() {
		InformacionDibujable info = new InformacionDibujable(this.posicionActual.getPosicionX(),this.posicionActual.getPosicionY() , 'C');
		return info;
	}

}
