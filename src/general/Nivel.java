package general;

import java.util.Iterator;
import java.util.LinkedList;
import enemigos.*;

public class Nivel {
	//Variables de juego
	private int velocidad;
	private boolean Perdio;
	
	//Variables enemigas
	private LinkedList<Enemigo> EnemigosEnPantalla;

	public void setEnemigosEnPantalla(LinkedList<Enemigo> enemigosEnPantalla) {
		LinkedList<Enemigo> EnemigosEnPantalla = enemigosEnPantalla ;

	private LinkedList<Enemigo> enemigosEnPantalla;		/* Enemigos Mostrados y procesados durante el nivel*/
	private LinkedList<LinkedList<Enemigo>> enemigosEnEspera;		/* Enemigos en espera (Oleada) por ser procesados y aparecer en el nivel*/

	
	//Variables aliadas
	private LinkedList<MisilAntibalistico> listaMisilesAntibalisticos;
	private Ciudad Ciudades[];
	private Base Bases[];
	private static Posicion[] vectorDePosicionesDeEstructurasAliadas;

	
	public Nivel() { /* limpia pantalla,reponer misiles, reconstruye bases */
		Ciudad ciudades[] = new Ciudad[7];
		Base bases[]= new Base[4];
		
		
		//Instancia las nueve ciudades
		Ciudad.InstanciarCiudades(ciudades);
		
		//Instancia las tres bases
		Base.InstanciarBases(bases);
		
		//Crea la lista de enemigos del nivel
		Oleada.CrearListaDeOleadasPorNivel(enemigosEnEspera);
				
	}

	/*LOOP NIVEL
	 * En este método se va a iterar todo el nivel, básicamente es el método principal del juego
	 */
	public void loopDelNivel(int velocidad) 
		throws InterruptedException{
		int tics = 0;
		
		//Lanzo la primer oleada de enemigos
		lanzarEnemigos(enemigosEnEspera.poll());
		
		// Mientras hayan enemigos
		while (!enemigosEnEspera.isEmpty())
		{
			
			//Cuando pasa 1 segundo, lanzo otra oleada de enemigos
			if(tics==velocidad) {
				//Lanzo una nueva oleada de enemigos
				lanzarEnemigos(enemigosEnEspera.poll());
				tics=0;
			}
			
			this.actualizarPosiciones();
			Colisiones.comprobarColision(EnemigosEnPantalla,listaMisilesAntibalisticos,Ciudades,Bases);
			//dibujar();
			Thread.sleep(1000/velocidad);
			tics++;
		}
	    if(!Ciudad.hayCiudades(Ciudades)) {
	    	this.Perdio = true;
	    }
		PuntajeJugador.actualizarTablaDePuntajes(); // PARAMETROS
		// MostrarPuntaje (ParteGrafica)

	}
	
	private void lanzarEnemigos(LinkedList<Enemigo> auxOleada) {
		
		//Agrego los enemigos a la lista enemigosEnPantalla
		for(Iterator<Enemigo> i= auxOleada.iterator(); i.hasNext();) {
			this.enemigosEnPantalla.add(i.next());
		}
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
		for(Iterator<MisilAntibalistico> i = listaMisilesAntibalisticos.iterator(); i.hasNext();) 
		{
			MisilAntibalistico misil = i.next();
			misil.mover();
		}
	}

}
