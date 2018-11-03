package bombarderos;

import clasesPadres.Bombardero;
import taller2.grafico.Dibujable;
import taller2.grafico.InformacionDibujable;

public class Avion extends Bombardero implements Dibujable{
	@Override
	public InformacionDibujable getInformacionDibujable() {
		InformacionDibujable info = new InformacionDibujable(this.posicionActual.getPosicionX(),this.posicionActual.getPosicionY() , '>');
		return info;
	}
}
