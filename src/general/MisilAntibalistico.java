package general;


import enemigos.Misiles;
import taller2.grafico.Dibujable;
import taller2.grafico.InformacionDibujable;

public class MisilAntibalistico extends Misiles implements Dibujable{

/*error!!!!! Fijense si saben como se soluciona **************************/
	
		MisilAntibalistico(Posicion pos){
			super();
			determinarInicio(pos);
		}

	/*variable que dice que exploto o no*/
	private boolean exploto;
	public boolean isExploto() {
		return exploto;
	}

	public void setExploto(boolean exploto) {
		this.exploto = exploto;
	}
	public Explosion getArea() {
		return Area;
	}
	public void setArea(Explosion area) {
		Area = area;
	}
	private Explosion Area;


	@Override
	public void destruccion() {
		// TODO Auto-generated method stub
	}
	
	//Determina la posicion inicial de cada misil, seteandolo en la posicion inicial de cada base
	public void determinarInicio(Posicion pos) {
		this.posicionInicial.actualizarPosicion(pos.getPosicionX(),pos.getPosicionY());
		this.posicionActual.actualizarPosicion(pos.getPosicionX(),pos.getPosicionY());
	}
	
	public void determinarObjetivo(int X,int Y){
		this.posicionObjetivo.actualizarPosicion(X, Y);
	}

	@Override
	public InformacionDibujable getInformacionDibujable() {
		InformacionDibujable info = new InformacionDibujable(this.posicionActual.getPosicionX(),this.posicionActual.getPosicionY() , '+');
		return info;
	}

	public boolean alcanzoObjetivo() {
		if(this.posicionActual.getPosicionY() < this.posicionObjetivo.getPosicionY())
		{
			return true;
		}
		if(this.posicionInicial.getPosicionX() > this.posicionObjetivo.getPosicionX())
		{
			if(this.posicionActual.getPosicionX() < this.posicionObjetivo.getPosicionX()){
				return true;
			}
		}
		else{
			if(this.posicionInicial.getPosicionX() > this.posicionObjetivo.getPosicionX()){
				if(this.posicionActual.getPosicionX() > this.posicionObjetivo.getPosicionX()){
					return true;
				}
			}
		}
		if(this.posicionActual.equals(this.posicionObjetivo));
		return false;
	}
}