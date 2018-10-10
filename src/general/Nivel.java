package general;

import java.util.Iterator;
import java.util.LinkedList;
import enemigos.*;

public class Nivel {
	private Integer NumeroDeNivel;
	private Long velocidad;
	private LinkedList<Enemigo> EnemigosEnPantalla;
	private LinkedList<MisilAntibalistico> ListaMisilesAntibalisticos;
	private Ciudad ciudades[];
	private Base bases[];
	
	
	public Nivel(Integer nivelActual) { /* limpia pantalla,reponer misiles, reconstruye bases */
		LinkedList Aliados = new LinkedList();
		Ciudad ciudades[] = new Ciudad[7];
		Base bases[]= new Base[2];
		
		//Crea la lista de enemigos del nivel
		LinkedList<Enemigo> Enemigos = new LinkedList<Enemigo>(); 
		Oleada.CrearListaDeOleadasPorNivel(this.NumeroDeNivel, Enemigos);
		//Instancia las nueve ciudades
		Ciudad.InstanciarCuidades(ciudades);
		
		//Instancia las tres bases
		Base.InstanciarBases(bases);
	}

	public Nivel() {

	}
	/*LOOP NIVEL
	 * En este método se va a iterar todo el nivel, básicamente es el método principal del juego
	 */
	public void loopDelNivel() 
		throws InterruptedException{
		Integer segundos=new Integer(0);
		
		// Mientras hayan queden ciudades en pie
		while (Ciudad.hayCiudades(ciudades))
		{
			// Mientras hayan enemigos
			while (!Enemigos.isEmpty())
			{
			this.actualizarPosiciones();
			Colisiones.comprobarColision(this);
			//dibujar();
			Thread.sleep(1000/60);
			//pausa entre una oleada y la siguiente
			}
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
		for(Iterator<Enemigo> i = EnemigosEnPantalla.iterator(); i.hasNext();) 
		{
			Enemigo enemigo = i.next();
			enemigo.mover();
		}
		
		//Actualiza la posición de los misiles aliados
		for(Iterator<MisilAntibalistico> i = ListaMisilesAntibalisticos.iterator(); i.hasNext();) 
		{
			MisilAntibalistico misil = i.next();
			misil.mover();
		}
	}

}
