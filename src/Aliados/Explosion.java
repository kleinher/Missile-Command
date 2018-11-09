package Aliados;

import java.util.Iterator;
import java.util.LinkedList;

import general.Posicion;
import taller2.grafico.Dibujable;
import taller2.grafico.InformacionDibujable;

public class Explosion implements Dibujable{
	private Posicion posicionActual;
	private int radio;
	private int contartick=0;
	public Explosion() {
		posicionActual = new Posicion();
		this.radio=1;
	}
	/**
	 * Determina el tamanio del radio y cuando debe desaparecer la explosion
	 * @param explosionesEnPantalla
	 * @return
	 */
	public static LinkedList<Explosion> determinarTamanio(LinkedList<Explosion> explosionesEnPantalla) {
		LinkedList<Explosion> explosionesAEliminar=new LinkedList<Explosion>();
		for (Iterator<Explosion> j = explosionesEnPantalla.iterator(); j.hasNext();) {
				Explosion ExplAct=j.next();
			if(ExplAct.contartick<5)
				ExplAct.radio+=(5);
			else
				ExplAct.radio-=(5);
			if(ExplAct.radio<0)
				explosionesAEliminar.add(ExplAct);
			ExplAct.contartick++;

		}
		return explosionesAEliminar;
	}
	public Explosion(Posicion pos){
		this.posicionActual=new Posicion(pos);
		this.radio=1;
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

	@Override
	public InformacionDibujable getInformacionDibujable() {
		InformacionDibujable info = new InformacionDibujable(this.posicionActual.getPosicionX(),this.posicionActual.getPosicionY() , 'E');
		return info;
	}
	
	
	
}
