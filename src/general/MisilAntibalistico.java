package general;


import enemigos.Misiles;

public class MisilAntibalistico extends Misiles{

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
	public AreaDeExplosion getArea() {
		return Area;
	}
	public void setArea(AreaDeExplosion area) {
		Area = area;
	}
	private AreaDeExplosion Area;

	public void mover() {
		//implementar mover porque es inverso al resto de los misiles
		
	}

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
}