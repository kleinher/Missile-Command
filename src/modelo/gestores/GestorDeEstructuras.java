package modelo.gestores;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import modelo.Aliados.Base;
import modelo.Aliados.Ciudad;
import modelo.Aliados.Explosion;
import modelo.Aliados.MisilAntibalistico;
import modelo.enemigos.Enemigo;
import modelo.general.Posicion;
import taller2.grafico.Dibujable;
/**
 * --Correcci�n para Reentrega-- Se agrega esta nueva clase GestorEstructuras.
 * Tiene una doble funcionalidad, la de contenedor de estructuras, para que la Clase Gestor Nivel no deba rehacer su
 * Esta Clase inicializa y contiene las estructuras de datos que se necesitan
 * para el juego
 * 
 * package general;
 * 
 * @author T
 *
 */
public class GestorDeEstructuras {
	// Variables de juego
		private int Velocidad;
		private int NivelActual;
		List<? extends Dibujable> listaDibujables;


		//Lista de todas las estelas
		LinkedList<LinkedList<Posicion>> EstelasEnPantalla;

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
			this.Velocidad=2;
			this.MisilesAliadosEnPantalla= new LinkedList<MisilAntibalistico>();
			this.EnemigosEnPantalla = new LinkedList<Enemigo>();
			this.EstelasEnPantalla =new LinkedList<LinkedList<Posicion>>();
			
			// Instancia las nueve ciudades
			this.Ciudades=new Ciudad[7];
			Ciudad.InstanciarCiudades(this.Ciudades);

			// Instancia las tres bases
			this.Bases=new Base[4];
			Base.InstanciarBases(this.Bases, this.Velocidad);
		}
		/**
		 * ---GESTIONAR Estructuras--- Funcion: Modifica la instancia nivel(de Juego) cada ves
		 * que comienza un nuevo nivel
		 */

		public void gestionarEstructuras() {
			// Crea la lista de enemigos del nivel
			this.EnemigosEnEspera = new LinkedList<LinkedList<Enemigo>>();
			
			
			explosionesEnPantalla=new LinkedList<Explosion>();
			MisilesAliadosEnPantalla=new LinkedList<MisilAntibalistico>();
			
			// Incrementa la dificultad cuando aumenta un nivel
			this.Velocidad*=1.5;
			
			// REVIVIR CIUDAD EN CASO DE
			this.NivelActual++;
			
			Oleada.CrearListaDeOleadasPorNivel(EnemigosEnEspera, NivelActual,EstelasEnPantalla,Velocidad);
		}

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
		public int getVelocidad() {
			return Velocidad;
		}
		/**
		 * @return Devuelve el nivel actual
		 */
		public int getNivelActual() {

			return NivelActual;
		}
		public Base[] getBases() {
			return Bases;
		}
		public LinkedList<MisilAntibalistico> getMisilesAliadosEnPantalla(){
			return MisilesAliadosEnPantalla;
		}
		public LinkedList<LinkedList<Enemigo>> getEnemigosEnEspera() {
			return EnemigosEnEspera;
		}
}
