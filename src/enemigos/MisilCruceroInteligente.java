package enemigos;

import taller2.grafico.Dibujable;
import taller2.grafico.InformacionDibujable;

/*Implementa lo mismo que misiles*/
public class MisilCruceroInteligente extends Misiles implements Dibujable{
	public MisilCruceroInteligente() {
		super();
		this.puntos=125;
	}

	@Override
	public InformacionDibujable getInformacionDibujable() {
		InformacionDibujable info = new InformacionDibujable(this.posicionActual.getPosicionX(),this.posicionActual.getPosicionY() , 'O');
		return info;
	}
}
