package general;

import java.util.LinkedList;

import enemigos.Misiles;

public class MisilAntibalistico extends Misiles{
/*error!!!!! Fijense si saben como se soluciona **************************/
	
		MisilAntibalistico(Posicion pos){
		determinarInicio(pos);
	}

	private boolean exploto;
	public boolean isExploto() {
		return exploto;
	}

	public void setExploto(boolean exploto) {
		this.exploto = exploto;
	}
	public AreaDeExplosion getArea() {
		return Area;
	}
	public void setArea(AreaDeExplosion area) {
		Area = area;
	}
	private AreaDeExplosion Area;
	
	@Override
	public void destruccion() {
		// TODO Auto-generated method stub
	}
	
	//Determina la posicion inicial de cada misil, seteandolo en la posicion inicial de cada base
	public void determinarInicio(Posicion pos) {
		this.PosicionInicial.actualizarPosicion(pos.getPosicionX(),pos.getPosicionY());
		this.PosicionActual.actualizarPosicion(pos.getPosicionX(),pos.getPosicionY());
	}
	
	public void determinarObjetivo(int X,int Y){
		this.PosicionObjetivo.actualizarPosicion(X, Y);
	}
}