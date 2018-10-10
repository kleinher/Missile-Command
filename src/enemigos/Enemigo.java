package enemigos;
import general.Posicion;
public abstract class Enemigo {
	protected Posicion PosicionInicial;
	protected Posicion PosicionObjetivo;
	
	public abstract void mover();
	
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
	
		
}
