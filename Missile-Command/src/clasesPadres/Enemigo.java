package clasesPadres;
import java.util.LinkedList;

import gestores.GestorDeNivel;
import gestores.Posicion;
import misiles.Explosion;
import taller2.grafico.Dibujable;
public abstract class Enemigo implements Dibujable{
	protected int puntos;
	protected Posicion posicionInicial;
	protected Posicion posicionObjetivo;
	protected Posicion posicionActual;
	//protected img Imagen;
	
	/*Variables Abstractas que van a implementar tanto bombardero como Misiles*/

	public abstract void mover();
	public Enemigo () {
		this.puntos=25;
		this.posicionActual=new Posicion();
		this.posicionInicial= new Posicion();
		this.posicionObjetivo=new Posicion();
	}
	public void destruccion(LinkedList<Explosion> listaExplocionesEnPantalla,
							LinkedList<Enemigo> enemigosAEliminar)
	{
		//sumo el puntaje por destruir misiles enemigos en tiempo real
		GestorDeNivel.getGestorDeNivel().getPuntajeJugador().actualizarScore(this.puntos);
		
		//Elimino el misil de la pantalla
		enemigosAEliminar.remove(this);
		
		//creo una nueva explosion y la agrego a la lsita de explosiones en pantalla
		Explosion nuevaExplosion = new Explosion(this.posicionActual);
		listaExplocionesEnPantalla.add(nuevaExplosion);
	}

	public boolean alcanzoObjetivo() {
		if(this.posicionActual.getPosicionY() > this.posicionObjetivo.getPosicionY())
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

	
	public Posicion getPosicionInicial() {
		return posicionInicial;
	}
	public void setPosicionInicial(Posicion posicionInicial) {

		this.posicionInicial = posicionInicial;
	}
	public Posicion getPosicionObjetivo() {
		return posicionObjetivo;
	}
	public void setPosicionObjetivo(Posicion posicionObjetivo) {

		this.posicionObjetivo = posicionObjetivo;
	}
	public Posicion getPosicionActual() {
		return posicionActual;
	}
	public void setPosicionActual(Posicion posicionActual) {

		this.posicionActual = posicionActual;
	}

}
