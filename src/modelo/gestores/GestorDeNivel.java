package modelo.gestores;

import java.util.LinkedList;

import javax.swing.JOptionPane;

import modelo.Aliados.Ciudad;
import modelo.Aliados.Explosion;
import modelo.enemigos.*;
import modelo.usuario.InformacionJugador;
import modelo.usuario.PuntajeJugador;

/**
 * Gestor de nivel se instancia en su misma clase para ser Singleton
 */
/**
 * ---GESTOR DE NIVEL--- ___CLASE SINGLETON___ Esta clase se encarga de las
 * principales tareas, como arbitrar los niveles, la dificultd, y llevar el
 * control de el estado de la partida en cada tic del juego, delegando en la
 * clase 'Colisones' el recuento de 'muertes' y 'destrucciones'
 * 
 * @author LosPi
 *
 */

public class GestorDeNivel {
	private int tics;
	private boolean Perdio;
	public GestorDeEstructuras getEstructuras() {
		return estructuras;
	}

	private GestorDeEstructuras estructuras;

	static GestorDeNivel GestorDeNivel = new GestorDeNivel();

	// private static Posicion[] vectorDePosicionesDeEstructurasAliadas;
	// Esto que esta aca arriba ya estaba asi cuando llegue jaja

	/**
	 * ---CONSTRUCTOR GESTOR DE NIVEL---- Se inicializan las Ciudades y Bases con
	 * sus datos iniciales
	 */
	private GestorDeNivel() {
		tics=0;
		Misiles.DeterminarPosicionesDeLasbases();
		Oleada.DeterminarArregloDeMisiles();
		estructuras = new GestorDeEstructuras(0);
	}

	/**
	 * -------------LOOP NIVEL-----------
	 * 
	 * En este metodo se va a iterar todo el nivel, basicamente es el metodo
	 * principal del juego
	 * 
	 * Lanza las oleadas de enemigos por cada nivel, realiza el cambio de dificultad
	 * conforme se avanza en los niveles, y dibuja los objetos dibujables en la pantalla
	 * 
	 * @throws InterruptedException
	 */
	public void modelar() throws InterruptedException {
		if(estructuras.EnemigosEnEspera==null) {
			estructuras.gestionarEstructuras();
		}
		//Cuando termine el nivel
		if(estructuras.EnemigosEnEspera.isEmpty() & estructuras.EnemigosEnPantalla.isEmpty() & estructuras.explosionesEnPantalla.isEmpty()) {
			//Actualizo puntaje al final del nivel
			PuntajeJugador.ActualizarPuntaje(estructuras.getNivelActual(), estructuras.Ciudades, estructuras.Bases);
			JOptionPane.showMessageDialog(null, "		�Pasate de nivel! \n" + "Tu puntaje actual es: " + PuntajeJugador.getScore());
			//En este gestionar de estructuras se estan actualizando 
			//todas las estructuras para un nivel en especifico
			estructuras.gestionarEstructuras();
			tics=0;
			System.out.println("Cambio de nivel");
		}
		// Mientras hayan enemigos(No termino el nivel)
		else {
			// Cuando pasa 1 segundo, lanzo otra oleada de enemigos
			if (tics==200 & !estructuras.EnemigosEnEspera.isEmpty() ) {
				// Lanzo una nueva oleada de enemigos
				Enemigo.lanzarEnemigos(estructuras.EnemigosEnEspera.poll(), estructuras.EnemigosEnPantalla);
				tics = 0;
			}
			//actualizo el tamanio de las explosiones
			actualizarTamanioDeExplosiones(estructuras.explosionesEnPantalla);
			//actualiza posiciones
			estructuras.actualizarPosiciones();
			//comprueba colisiones
			Colisiones.comprobarColision(estructuras.EnemigosEnPantalla, estructuras.explosionesEnPantalla,
					estructuras.Ciudades, estructuras.Bases, estructuras.MisilesAliadosEnPantalla);
			tics++;
			if (!Ciudad.hayCiudades(estructuras.Ciudades)) {
				Perdio=true;
			}
		}
	}

	private void actualizarTamanioDeExplosiones(LinkedList<Explosion> explosionesEnPantalla) {
		LinkedList<Explosion> ListaDeExplosionesAeliminar=Explosion.determinarTamanio(estructuras.explosionesEnPantalla);
		estructuras.explosionesEnPantalla.removeAll(ListaDeExplosionesAeliminar);		
	}
	/**
	 * 
	 * @return Devuelve un booleano si perdio o no, en funi�n del estado de la
	 *         partida
	 */

	/**
	 * ---getGestorDeNivel--- Al ser singleton la clase este metodo se utiliza para
	 * obtener la instancia
	 * 
	 * @return Devuelve La UNICA instancia de Gestor de niveles
	 */
	public static GestorDeNivel getGestorDeNivel() {

		return GestorDeNivel;
	}

	public boolean Perdio() {

		return this.Perdio;
	}

	/**
	 * METODO TERMINAR En teoria, guarda todo lo que se tenga que guardar, como los
	 * puntajes. Imprime 'GameOver', o un blue Screen para asustar al usuario
	 * desprevenido
	 */
	@SuppressWarnings("static-access")
	public void terminarJuego() {
		/* Guarda los puntajes en la tabla de puntajes */ /* Falta Pasarle todos los argumentos que necesita */
		// tablaDePuntajes.actualizarTablaDePuntajes();
		/* Imprimer Game Over */
		
		JOptionPane.showMessageDialog(null, "		!GAME OVER!");
		InformacionJugador Info = new InformacionJugador("1", "Pepe",2156, 45.5);
		PuntajeJugador.MostrarYActualizarPuntaje(GestorDeNivel.getGestorDeNivel().getEstructuras().getListaDePuntajes(), Info, 5);
	}

}
