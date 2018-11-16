package modelo.enemigos;
import java.io.LineNumberInputStream;
import java.util.Iterator;

import java.util.LinkedList;
import java.util.Random;

import modelo.Aliados.Explosion;
import modelo.general.Posicion;
import modelo.gestores.GestorDeEstructuras;
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
/**
 * Creo un nuevo misil (o varios) con un nuevo objetivo que parte desde la posicion 180
 * 
 * 
 * @return LinkedList<Enemigo> (Retorno una lista para poder agregar a la lista de enemigos en pantalla una vez termino de iterarla)
 */
	public LinkedList<Enemigo> clonarMisil(){
		LinkedList<Enemigo> l= new LinkedList<Enemigo>();
		Random aleatorio = new Random(System.currentTimeMillis());
		int intAletorio = aleatorio.nextInt(3);
		if(intAletorio>0) {
		MisilBalistico enemigoClonado= new MisilBalistico();
		enemigoClonado.determinarObjetivo();
		enemigoClonado.posicionInicial.actualizarPosicion(this.posicionActual.getPosicionX(),180);
		enemigoClonado.posicionActual.actualizarPosicion(enemigoClonado.posicionInicial.getPosicionX(),180);
		enemigoClonado.determinarEstela();
		enemigoClonado.determinarDesplazamiento(GestorDeNivel.getGestorDeNivel().getEstructuras().getVelocidad());
		GestorDeNivel.getGestorDeNivel().getEstructuras().getEstelasEnPantalla().add(enemigoClonado.getEstela().getListaDeEstelas());
		l.add(enemigoClonado);
		}
		return l;
	}
	/**Muy similar al metodo anterior pero en este caso se clona a partir de un Bombardero con ligeros cambios
	 * 
	 * 
	 * @return LinkedList<Enemigo> (Retorno una lista para poder agregar a la lista de enemigos en pantalla una vez termino de iterarla)
	 */
	public LinkedList<Enemigo> ClonarBombardero(){
		LinkedList<Enemigo> l= new LinkedList<Enemigo>();
		MisilBalistico enemigoClonado= new MisilBalistico();
		enemigoClonado.determinarObjetivo();
		enemigoClonado.posicionInicial.actualizarPosicion(this.posicionActual.getPosicionX(),this.posicionActual.getPosicionY());
		enemigoClonado.posicionActual.actualizarPosicion(this.posicionActual.getPosicionX(),this.posicionActual.getPosicionY());
		enemigoClonado.determinarEstela();
		enemigoClonado.determinarDesplazamiento(GestorDeNivel.getGestorDeNivel().getEstructuras().getVelocidad());
		GestorDeNivel.getGestorDeNivel().getEstructuras().getEstelasEnPantalla().add(enemigoClonado.getEstela().getListaDeEstelas());
		l.add(enemigoClonado);
		return l;
	}
}
