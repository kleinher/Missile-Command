package general;

import java.util.Iterator;
import java.util.LinkedList;
import enemigos.*;

public class GestorDeNivel {
	//Variables de juego
	private int Dificultad;
	private boolean Perdio;
	private int NumeroDeNivel;
	
	//Variables enemigas
	private LinkedList<Enemigo> EnemigosEnPantalla;		/* Enemigos Mostrados y procesados durante el nivel*/
	private LinkedList<LinkedList<Enemigo>> EnemigosEnEspera;		/* Enemigos en espera (Oleada) por ser procesados y aparecer en el nivel*/

	//Variables aliadas
	private LinkedList<MisilAntibalistico> listaMisilesAntibalisticos;
	private Ciudad Ciudades[];
	private Base Bases[];
	// private static Posicion[] vectorDePosicionesDeEstructurasAliadas;

	/*---CONSTRUCTOR GESTOR DE NIVEL----
	 * Se inicializan las Ciudades y Bases con sus datos iniciales 
	 * */
	public GestorDeNivel() { /* limpia pantalla,reponer misiles, reconstruye bases */

		
		//Instancia las nueve ciudades
		Ciudad.InstanciarCiudades(this.Ciudades);
		
		//Instancia las tres bases
		Base.InstanciarBases(this.Bases);
		
		this.Perdio = false;
		//
		this.Dificultad = 15;
				
	}
	
	/*---GESTIONAR NIVEL---
	 *Funcion: Modifica la instancia nivel(de Juego) cada ves que comienza un nuevo nivel
	 * */
	public void gestionarNivel(){
		
		//Crea la lista de enemigos del nivel
		Oleada.CrearListaDeOleadasPorNivel(EnemigosEnEspera,NumeroDeNivel);
		
		//Incrementa la dificultad cuando aumenta un nivel
		this.Dificultad += 5;
		
		//REVIVIR CIUDAD EN CASO DE 
		
	}

	/*LOOP NIVEL
	 * En este método se va a iterar todo el nivel, básicamente es el método principal del juego
	 */
	public void loopDelNivel() 
		throws InterruptedException{
		int tics = 0;
		
		//Lanzo la primer oleada de enemigos
		Enemigo.lanzarEnemigos(EnemigosEnEspera.poll(), EnemigosEnPantalla);
		// LANZAR MISILES ALIADOS HARDCODEADOS
		
		// Mientras hayan enemigos
		while (!EnemigosEnEspera.isEmpty())
		{
			
			//Cuando pasa 1 segundo, lanzo otra oleada de enemigos
			if(tics==Dificultad) {
				//Lanzo una nueva oleada de enemigos
				Enemigo.lanzarEnemigos(EnemigosEnEspera.poll(), EnemigosEnPantalla);
				tics=0;
			}
			
			this.actualizarPosiciones();
			Colisiones.comprobarColision(EnemigosEnPantalla,listaMisilesAntibalisticos,Ciudades,Bases);
			//dibujar();
			Thread.sleep(1000/Dificultad);
			tics++;
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
		for(Iterator<Enemigo> i = EnemigosEnPantalla.iterator(); i.hasNext();) 
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
		
		//ACTUALIZAR EXPLOCIONES
		
		//ACTUALIZAR ESTELA
	}
	public boolean Perdio() {
		return this.Perdio;
	}
}

