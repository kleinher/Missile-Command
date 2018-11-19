package modelo.Aliados;

import java.util.Iterator;

import java.util.LinkedList;

import modelo.general.Posicion;

public class Explosion{
	private Posicion posicionActual;
	private double radio;
	private int contartick=0;
	public Explosion() {
		posicionActual = new Posicion();
		this.radio=0.5;
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
			if(ExplAct.contartick<50)
				ExplAct.radio+=(1);
			else
				ExplAct.radio-=(1);
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

	public double getRadio() {
		return radio;
	}

	public void setRadio(int radio) {
		this.radio = radio;
	}


	
	
	
}
