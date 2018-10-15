package general;

import taller2.grafico.Dibujable;
import taller2.grafico.InformacionDibujable;

public class Explosion implements Dibujable{
	private Posicion posicionActual;
	private int radio;
	public Explosion() {
		posicionActual = new Posicion();
		this.radio=20;
	}
	public Explosion(Posicion pos){
		this.posicionActual=new Posicion(pos);
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
	@Override
	public InformacionDibujable getInformacionDibujable() {
		InformacionDibujable info = new InformacionDibujable(this.posicionActual.getPosicionX(),this.posicionActual.getPosicionY() , 'E');
		return info;
	}
	
	
	
}
