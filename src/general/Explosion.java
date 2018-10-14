package general;

public class Explosion {
	private Posicion posicionActual;
	private int radio;
	public Explosion() {
		posicionActual = new Posicion();
		this.radio=20;
	}
	public Posicion getPosicionActual() {
		return posicionActual;
	}

	public void setPosicionActual(Posicion posicionActual) {
		this.posicionActual = posicionActual;
	}

	public int getRadio() {
		return radio;
	}

	public void setRadio(int radio) {
		this.radio = radio;
	}

	public void destruccion() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
