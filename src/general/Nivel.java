package general;

import java.util.Iterator;
import java.util.LinkedList;
import enemigos.*;

public class Nivel {
	private Integer numeroDeOleada;
	private Long velocidad;
	private LinkedList<Enemigo> Enemigos;
	private LinkedList<MisilAntibalistico> ListaMisilesAntibalisticos;
	private Ciudad ciudades[];
	private Base bases[];
	
	
	public Nivel(Integer nivelActual) { /* limpia pantalla,reponer misiles, reconstruye bases */
		LinkedList Aliados = new LinkedList ();
		Ciudad ciudades[] = new Ciudad[8];
		Base bases[]= new Base[2];
		//Instancia las nueve ciudades
		for (int i=0;i<9;i++) {
			ciudades[i]= new Ciudad(); 
		}
		//Instancia las tres bases
		for (int i=0;i<3;i++) {
			bases[i]= new Base();
		}
	}

	public Nivel() {

	}
	/*Loop del nivel
	 * En este método se va a iterar todo el nivel, básicamente es el método principal del juego
	 */
	public void loopDelNivel() 
		throws InterruptedException{
		Integer segundos=new Integer(0);
		while (Oleada.hayEnemigos()) // Mientras hayan enemigos && Queden
		{
			Thread.sleep(1000/60);
			Colisiones.comprobarColision(this);
			this.actualizarPosiciones();
			//pausa entre una oleada y la siguiente
		}
		PuntajeJugador.actualizarTablaDePuntajes(); // PARAMETROS
		// MostrarPuntaje (ParteGrafica)

	}
	
	/*ActualizarPosiciones
	 * Este método actualiza las posiciones de todos los objetos del juego
	 * 
	*/
	private void actualizarPosiciones() {
		//Actualiza posiciones de los de los enemigos
		for(Iterator<Enemigo> i = Enemigos.iterator(); i.hasNext();) {
			Enemigo enemigo = i.next();
			enemigo.mover();
		}
		//Actualiza la posición de los misiles aliados
		for(Iterator<MisilAntibalistico> i = ListaMisilesAntibalisticos.iterator(); i.hasNext();) {
			MisilAntibalistico misil = i.next();
			misil.mover();
		}
	}

}
