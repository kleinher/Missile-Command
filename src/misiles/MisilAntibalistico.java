package misiles;


import clasesPadres.Misiles;
import gestores.Posicion;
import taller2.grafico.Dibujable;
import taller2.grafico.InformacionDibujable;

public class MisilAntibalistico extends Misiles implements Dibujable{

		public MisilAntibalistico(Posicion pos){
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
}