package modelo.enemigos;
import java.util.Iterator;

import java.util.LinkedList;

import modelo.Aliados.Explosion;
import modelo.general.Posicion;
import modelo.gestores.GestorDeNivel;
public abstract class Enemigo{
	protected int puntos;
	protected Posicion posicionInicial;
	protected Posicion posicionObjetivo;
	protected Posicion posicionActual;
	protected double movimientoX;
	protected double movimientoY;
	//protected img Imagen; 
	
	/*Variables Abstractas que van a implementar tanto bombardero como Misiles*/
	public abstract void mover();
	
	public Enemigo () {
		this.puntos=25;
		this.posicionActual=new Posicion();
		this.posicionInicial= new Posicion();
		this.posicionObjetivo=new Posicion();
	}
	/**
	 * 
	 * @param explosionesAgregar
	 * @param enemigosAEliminar
	 */
	public void destruccion(LinkedList<Explosion> explosionesAgregar,
							LinkedList<Enemigo> enemigosAEliminar)
	{
		//Elimino el misil de la pantalla
		enemigosAEliminar.add(this);
		
		//Elimino las estelas de pantalla
		if ((this) instanceof Misiles) {
			((Misiles) this).getEstela().eliminarEstela();
		}
		
		//creo una nueva explosion y la agrego a la lsita de explosiones en pantalla
		Explosion nuevaExplosion = new Explosion(this.posicionActual);
		explosionesAgregar.add(nuevaExplosion);
	}
	
	public void borrarEnemigoSinExplotar(Enemigo e,LinkedList<Enemigo> enemigosAEliminar) {
		enemigosAEliminar.add(e);
	}

	/**
	 * Este metodo lanza una oleada de enemigos
	 * @param auxOleada	>Lista con los enemigos Restantes ??<<<<<<
	 * @param EnemigosEnPantalla > Lista con Enemigos en pantalla
	 */
	public static void lanzarEnemigos(LinkedList<Enemigo> auxOleada, LinkedList<Enemigo> EnemigosEnPantalla) {
		//Agrego los enemigos a la lista enemigosEnPantalla
		for(Iterator<Enemigo> i= auxOleada.iterator(); i.hasNext();) {
			EnemigosEnPantalla.add(i.next());
		}
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
	}/**Retorna true si se alcanzo el objetivo
		
	 */
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

	public LinkedList<Enemigo> clonar(){
		LinkedList<Enemigo> l= new LinkedList<Enemigo>();
		MisilBalistico enemigoClonado= new MisilBalistico(GestorDeNivel.getGestorDeNivel().getEstructuras().getVelocidad());
		enemigoClonado.posicionActual.actualizarPosicion(this.posicionActual.getPosicionX(),this.posicionActual.getPosicionY());
		l.add(enemigoClonado);
		return l;
		
		
	}
}
