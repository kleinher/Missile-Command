package modelo.gestores;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import modelo.Aliados.Base;
import modelo.Aliados.Ciudad;
import modelo.Aliados.Explosion;
import modelo.Aliados.MisilAntibalistico;
import modelo.enemigos.Bombardero;
import modelo.enemigos.Enemigo;
import modelo.enemigos.MisilBalistico;
import modelo.enemigos.Misiles;
import modelo.general.Posicion;
import modelo.usuario.InformacionJugador;
import modelo.usuario.PuntajeJugador;
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
		private double Velocidad;
		private LinkedList<InformacionJugador> ListaDePuntajes;
		private int NivelActual;
		public void setNivelActual(int nivelActual) {
			NivelActual = nivelActual;
		}
		List<? extends Dibujable> listaDibujables;
		
		//variables Usadas para guardar y cargar la tabla de puntajes
		ObjectOutputStream salida=null;
		ObjectInputStream entrada=null;


		//Lista de todas las estelas
		LinkedList<LinkedList<Posicion>> EstelasEnPantalla;

		public LinkedList<InformacionJugador> getListaDePuntajes() {
			return ListaDePuntajes;
		}
		public void setBases(Base[] bases) {
			Bases = bases;
		}
		// Variables enemigas
		/* Lista de Enemigos Mostrados y procesados durante el nivel */
		LinkedList<Enemigo> EnemigosEnPantalla;
		public Ciudad[] getCiudades() {
			return Ciudades;
		}
		public LinkedList<Explosion> getExplosionesEnPantalla() {
			return explosionesEnPantalla;
		}
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
			PuntajeJugador.InicializarTablaDePuntajePorCadaEnemigo();
			this.NivelActual=1;
			this.Velocidad=1.1;
			this.MisilesAliadosEnPantalla= new LinkedList<MisilAntibalistico>();
			this.EnemigosEnPantalla = new LinkedList<Enemigo>();
			this.EstelasEnPantalla =new LinkedList<LinkedList<Posicion>>();
			
			//Lista que carga desde memoria con los puntajes anteriormente persistidos
			ListaDePuntajes=obtenerLista();
			// Instancia las nueve ciudades
			this.Ciudades=new Ciudad[7];
			Ciudad.InstanciarCiudades(this.Ciudades);

			// Instancia las tres bases
			this.Bases=new Base[4];
			Base.InstanciarBases(this.Bases, this.Velocidad);
		}
		/**
--
		 * ---GESTIONAR Estructuras--- Funcion: Modifica la instancia nivel(de Juego) cada ves
		 * que comienza un nuevo nivel
		 */

		public void gestionarEstructuras() {
			// Crea la lista de enemigos del nivel
			this.EnemigosEnEspera = new LinkedList<LinkedList<Enemigo>>();
			this.Bases=new Base[4];
			Base.InstanciarBases(this.Bases, this.Velocidad);
			this.EstelasEnPantalla =new LinkedList<LinkedList<Posicion>>();
			explosionesEnPantalla=new LinkedList<Explosion>();
			MisilesAliadosEnPantalla=new LinkedList<MisilAntibalistico>();
			
			// Incrementa la dificultad cuando aumenta un nivel
			this.Velocidad*=1.2;
			
			// REVIVIR CIUDAD EN CASO DE
			this.NivelActual++;
			
			Oleada.CrearListaDeOleadasPorNivel(EnemigosEnEspera, NivelActual,EstelasEnPantalla,Velocidad);
		}

		/**
		 * ---ActualizarPosiciones--- Este metodo actualiza las posiciones de todos los
		 * enemigos del juego y se encarga de clonar Misiles de ser necesario
		 * 
		 */
		public void actualizarPosiciones() {
			LinkedList<Enemigo> ListaDeEnemigosAClonar = new LinkedList<Enemigo>();
			// Actualiza posiciones de los de los enemigos
			for (Iterator<Enemigo> i = EnemigosEnPantalla.iterator(); i.hasNext();) {
				Enemigo enemigo = i.next();
				enemigo.mover();
					if((enemigo instanceof Bombardero)&&(((int)enemigo.getPosicionActual().getPosicionX()==150)||((int)enemigo.getPosicionActual().getPosicionX()==350))){
						ListaDeEnemigosAClonar=enemigo.ClonarBombardero();
					}
					if((enemigo instanceof MisilBalistico) &&((int)enemigo.getPosicionActual().getPosicionY()==180)){
						ListaDeEnemigosAClonar=enemigo.clonarMisil();
					}
				if(enemigo.alcanzoObjetivo()) { 
					i.remove();
				}	
			}
				if(!ListaDeEnemigosAClonar.isEmpty()){
					EnemigosEnPantalla.addAll(ListaDeEnemigosAClonar);
					ListaDeEnemigosAClonar.clear();
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
		public double getVelocidad() {
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
		public LinkedList<Enemigo> getEnemigosEnPantalla() {
			return EnemigosEnPantalla;
		}
		public LinkedList<LinkedList<Posicion>> getEstelasEnPantalla() {
			return EstelasEnPantalla;
		}
		
		
		
		
		
		
		
		private void guardaLista(LinkedList<InformacionJugador> listaDePuntajes) {
			try {
				String home = System.getProperty("user.home");
				salida = new ObjectOutputStream(new FileOutputStream(home+"Tabla de Puntaje"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	        try {
				salida.writeObject(listaDePuntajes);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		private LinkedList<InformacionJugador> obtenerLista() {
	        
	    	LinkedList<InformacionJugador> lista =  new LinkedList<InformacionJugador>();
	        try {
	        entrada = new ObjectInputStream(new FileInputStream("Puntajes/ranklist.dat"));
	        }catch (EOFException e){
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return lista;
	    }
		 public void ResetearTabla(LinkedList<InformacionJugador> lista) {
			 lista.clear();
		 }
}
