package general;

import java.util.Iterator;
import java.util.LinkedList;
import enemigos.*;

public class Nivel {
	//Variables de juego
	private int velocidad;
	private boolean Perdio;
	
	//Variables enemigas
	private LinkedList<Enemigo> enemigosEnPantalla;		/* Enemigos Mostrados y procesados durante el nivel*/
	private LinkedList<Enemigo> enemigosEnEspera;				/* Enemigos en espera (Oleada) por ser procesados y aparecer en el nivel*/
	
	//Variables aliadas
	private LinkedList<MisilAntibalistico> ListaMisilesAntibalisticos;
	private Ciudad Ciudades[];
	private Base Bases[];
	private static Posicion[] vectorDePosicionesDeEstructurasAliadas;

	
	public Nivel() { /* limpia pantalla,reponer misiles, reconstruye bases */
		Ciudad ciudades[] = new Ciudad[7];
		Base bases[]= new Base[4];
		
		//Crea la lista de enemigos del nivel
		LinkedList<Enemigo> Enemigos = new LinkedList<Enemigo>(); 
		Oleada.CrearListaDeOleadasPorNivel(Juego.getNivelActual(), Enemigos);
		
		//Instancia las nueve ciudades
		Ciudad.InstanciarCiudades(ciudades);
		
		//Instancia las tres bases
		Base.InstanciarBases(bases);
	}

	/*LOOP NIVEL
	 * En este método se va a iterar todo el nivel, básicamente es el método principal del juego
	 */
	public void loopDelNivel() 
		throws InterruptedException{
		// Mientras hayan queden ciudades en pie. Mientras hayan enemigos
		while (!enemigosEnEspera.isEmpty())
		{
			this.actualizarPosiciones();
			Colisiones.comprobarColision(this);
			//dibujar();
			Thread.sleep(1000/60);
			//pausa entre una oleada y la siguiente
		}
	    if(!Ciudad.hayCiudades(Ciudades)) {
	    	this.Perdio = true;
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
		for(Iterator<Enemigo> i = enemigosEnPantalla.iterator(); i.hasNext();) 
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
	
	public 

	public boolean getPerdio() {
		return this.Perdio;
	}

}
