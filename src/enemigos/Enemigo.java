package enemigos;
import java.util.Iterator;
import java.util.LinkedList;
import general.Explosion;
import general.GestorDeNivel;
import general.Posicion;
public abstract class Enemigo {
	protected int puntos;
	protected Posicion posicionInicial;
	protected Posicion posicionObjetivo;
	protected Posicion posicionActual;
	protected int Velocidad=1;
	//protected img Imagen;
	
	/*Variables Abstractas que van a implementar tanto bombardero como Misiles*/

	public abstract void mover();

	public void destruccion(LinkedList<Explosion> listaExplocionesEnPantalla,
							LinkedList<Enemigo> enemigos)
	{
		GestorDeNivel.getGestorDeNivel().getPuntajeJugador().actualizarScore(25);
		//Elimino el misil de la pantalla
		enemigos.remove(this);
		Explosion nuevaExplosion = new Explosion();
		nuevaExplosion.getPosicionActual().actualizarPosicion(this.posicionActual);
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
	}

}
