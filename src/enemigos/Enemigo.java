package enemigos;
import general.Posicion;
public abstract class Enemigo {
	protected Posicion PosicionInicial;
	protected Posicion PosicionObjetivo;
	protected Posicion PosicionActual;
	protected int Velocidad=1;
	//protected img Imagen;
	

	public abstract void mover();
	public abstract void destruccion();
	
	public Posicion getPosicionInicial() {
		return PosicionInicial;
	}
	public void setPosicionInicial(Posicion posicionInicial) {
		PosicionInicial = posicionInicial;
	}
	public Posicion getPosicionObjetivo() {
		return PosicionObjetivo;
	}
	public void setPosicionObjetivo(Posicion posicionObjetivo) {
		PosicionObjetivo = posicionObjetivo;
	}
	public Posicion getPosicionActual() {
		return PosicionActual;
	}
	public void setPosicionActual(Posicion posicionActual) {
		PosicionActual = posicionActual;
	}

}
