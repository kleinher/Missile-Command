package enemigos;
import java.util.Iterator;
import java.util.LinkedList;

import general.Posicion;
public abstract class Enemigo {
	protected Posicion PosicionInicial;
	protected Posicion PosicionObjetivo;
	protected Posicion PosicionActual;
	protected int Velocidad=1;
	//protected img Imagen;
	
	/*Variables Abstractas que van a implementar tanto bombardero como Misiles*/

	public abstract void mover();
	public abstract void destruccion();
	
	public static void lanzarEnemigos(LinkedList<Enemigo> auxOleada, LinkedList<Enemigo> EnemigosEnPantalla) {
		//Agrego los enemigos a la lista enemigosEnPantalla
		for(Iterator<Enemigo> i= auxOleada.iterator(); i.hasNext();) {
			EnemigosEnPantalla.add(i.next());
		}
	}
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
