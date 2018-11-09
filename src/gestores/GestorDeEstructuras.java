package gestores;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import Aliados.Base;
import Aliados.Ciudad;
import Aliados.Explosion;
import Aliados.MisilAntibalistico;
import enemigos.Enemigo;
import general.Posicion;
import taller2.grafico.Dibujable;
import usuario.PuntajeJugador;
/**
 * --Correcci�n para Reentrega-- Se agrega esta nueva clase GestorEstructuras.
 * Tiene una doble funcionalidad, la de contenedor de estructuras, para que la Clase Gestor Nivel no deba rehacer su
 * Esta Clase inicializa y contiene las estructuras de datos que se necesitan
 * para el juego Es Singleton, de manera que quien quiera usarla obtiene la
 * unica instancia existente para obtener las estructuras del juego
 * 
 * package general;
 * 
 * @author T
 *
 */
public class GestorDeEstructuras {
	// Variables de juego
		private int Dificultad;
		private boolean Perdio;
		private int NivelActual;
		private PuntajeJugador  puntajeJugador;
		
		List<? extends Dibujable> listaDibujables;


		//Lista de todas las estelas
		LinkedList<Posicion> EstalasEnPantalla;

		// Variables enemigas
		/* Lista de Enemigos Mostrados y procesados durante el nivel */
		LinkedList<Enemigo> EnemigosEnPantalla;
		/*
		 * Lista de Enemigos en espera (Oleada) por ser procesados y aparecer en el
		 * nivel
		 */
		LinkedList<LinkedList<Enemigo>> EnemigosEnEspera;

		// Variables aliadas
		LinkedList<MisilAntibalistico> MisilesAliadosEnPantalla;
		LinkedList<Explosion> explosionesEnPantalla;
		Ciudad Ciudades[];
		Base Bases[];
		
		public GestorDeEstructuras() {
			this.NivelActual=1;
			this.puntajeJugador = new PuntajeJugador();
			this.MisilesAliadosEnPantalla= new LinkedList<MisilAntibalistico>();
			this.EnemigosEnPantalla = new LinkedList<Enemigo>();
			this.EstalasEnPantalla =new LinkedList<Posicion>();
			
			// Instancia las nueve ciudades
			this.Ciudades=new Ciudad[7];
			Ciudad.InstanciarCiudades(this.Ciudades);

			// Instancia las tres bases
			this.Bases=new Base[4];
			Base.InstanciarBases(this.Bases);
			this.Perdio = false;
			this.Dificultad = 15;
		}
		/**
		 * ---GESTIONAR Estructuras--- Funcion: Modifica la instancia nivel(de Juego) cada ves
		 * que comienza un nuevo nivel
		 */

		public void gestionarEstructuras() {
			// Crea la lista de enemigos del nivel
			this.EnemigosEnEspera = new LinkedList<LinkedList<Enemigo>>();
			Oleada.CrearListaDeOleadasPorNivel(EnemigosEnEspera, NivelActual);
			explosionesEnPantalla=new LinkedList<Explosion>();
			MisilesAliadosEnPantalla=new LinkedList<MisilAntibalistico>();
			// Incrementa la dificultad cuando aumenta un nivel
			this.Dificultad += 5;
			// REVIVIR CIUDAD EN CASO DE
			this.NivelActual++;
		}
		
		/**
		 * METODO QUE ACTUALIZA EN CADA TIC LA LISTA DE LOS ELEMENTOS DIBUJABLES

		 * 
		 * @return devuelve una lista de elementos dibujables
		 */
		public List<Dibujable> ActualizarListaDibujables() {
			
			/* Creo una nueva lista con todo lo que sea dibujable */
			List<Dibujable> listaDibujables = new LinkedList<Dibujable>();

			/* Agrego los enemigos que están en pantalla */
			for (Iterator<Enemigo> i = EnemigosEnPantalla.iterator(); i.hasNext();) {
				listaDibujables.add(i.next());
			}
			/* Agrego las explosiones que estan en pantalla */
			for (Iterator<Explosion> i = explosionesEnPantalla.iterator(); i.hasNext();) {
				listaDibujables.add(i.next());
			}
			/* Agrego los misiles aliados que estan en pantalla */
			for (Iterator<MisilAntibalistico> i = MisilesAliadosEnPantalla.iterator(); i.hasNext();) {
				listaDibujables.add(i.next());
			}
			/* Agrego las ciudades y las bases*/
			for(int i=1;i<Bases.length;i++) {
				//if(Bases[i].isEstaViva())
					listaDibujables.add(Bases[i]);
			}
			for(int i=1;i<Ciudades.length;i++) {
				if(Ciudades[i].estaViva())
					listaDibujables.add(Ciudades[i]);
			}
			return listaDibujables;
		}
		//



		/**
		 * ---ActualizarPosiciones--- Este metodo actualiza las posiciones de todos los
		 * objetos del juego
		 * 
		 */
		public void actualizarPosiciones() {
			// Actualiza posiciones de los de los enemigos
			for (Iterator<Enemigo> i = EnemigosEnPantalla.iterator(); i.hasNext();) {
				Enemigo enemigo = i.next();
				enemigo.mover();
				if(enemigo.alcanzoObjetivo()) {
					i.remove();
				}
			}
			
			LinkedList<Enemigo> aliadosAEliminar = new LinkedList<Enemigo>();
			LinkedList<Explosion> explosionesAgregar = new LinkedList<Explosion>();
			// Actualiza la posicion de los misiles aliados
			for (Iterator<MisilAntibalistico> i = MisilesAliadosEnPantalla.iterator(); i.hasNext();) {
				MisilAntibalistico misil = i.next();
				misil.mover();
				if(misil.alcanzoObjetivo()) {
					i.remove();
					misil.destruccion(explosionesAgregar, aliadosAEliminar);
				}
			}
			explosionesEnPantalla.addAll(explosionesAgregar);
			MisilesAliadosEnPantalla.removeAll(aliadosAEliminar);

			// ACTUALIZAR EXPLOSIONES
			
			// ACTUALIZAR ESTELA
		}
		/**
		 * @return Devuelve el nivel actual
		 */
		public int getNivelActual() {

			return NivelActual;
		}
}
