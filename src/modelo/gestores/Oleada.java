package modelo.gestores;

import java.util.LinkedList;
import java.util.Random;

import modelo.enemigos.Avion;
import modelo.enemigos.Enemigo;
import modelo.enemigos.MisilBalistico;
import modelo.enemigos.MisilCrucero;
import modelo.enemigos.MisilCruceroInteligente;
import modelo.enemigos.Satelite;
import modelo.general.Posicion;

/**
 * Esta clase representa a cada oleada dentro de la lista de Oleadas de cada
 * nivel
 * 
 * @author LosPi
 *
 */
public class Oleada {
	static boolean[] VectorDeMisilesCrucerosPorNivel = new boolean[17];
	private LinkedList<Enemigo> ListaDeOleadasPorNivel;

	/**
	 * Este metodo determina si hay enemigos en la liste de oleadas para agregarlos
	 * 
	 * @return Devuelve Verdadero si hay enemigos, Negativo en caso contrario
	 */
	public boolean hayEnemigos() {
		if (ListaDeOleadasPorNivel.isEmpty())
			return false;
		else
			return true;
	}

	/*
	 * CrearListaDeOleadasPorNivel Metodo estatico de la clase Oleada Parametro
	 * Enemigos: cada nodo de esta lista es una oleada.
	 */
	/**
	 * ---CREAR LISTA DE OLEADAS POR NIVEL--- Metodo estatico que crea la lista de
	 * Oleadas
	 * 
	 * @param Enemigos
	 *            >>Cada nodo de esta lista es una oleada
	 * @param NumeroDeNivel
	 *            >>Numero de nivel
	 */
	public static void CrearListaDeOleadasPorNivel(LinkedList<LinkedList<Enemigo>> Enemigos, int NumeroDeNivel, LinkedList<LinkedList<Posicion>> ListaDeEstelas) {
		Random aleatorio = new Random();
		int oleada = 1;

		// Genera un numero Random de enemigos para cada nivel de 12 a 17
		Integer numeroDeEnemigosPorNivel = (12 + aleatorio.nextInt(6));

		// Adentro de este loop se agregan todos los enemigos a la lista Enemigos
		while (numeroDeEnemigosPorNivel > 0) {
			oleada++;
			// oleadaEnemigos es una oleada
			LinkedList<Enemigo> oleadaEnemigos = new LinkedList<Enemigo>();

			// Genera Un numero Random de enemigos para cada oleada de 0 a 4
			int numeroDeEnemigosPorOleada = (aleatorio.nextInt(5));
			for (int i = numeroDeEnemigosPorOleada; i != 0; i--) {

				/* Genera Un numero Random de enemigos para cada oleada de 0 a 4 */
				MisilBalistico MB = new MisilBalistico();
				ListaDeEstelas.add(MB.getEstela().getListaDeEstelas());
				oleadaEnemigos.add(MB);
				numeroDeEnemigosPorNivel--;
			}

			// En todos los niveles se van a agregar los Misil Cruceros Inteligentes y los
			// Misil cruceros apartir de la oleada 4
			if (oleada == 4) {
				if (VectorDeMisilesCrucerosPorNivel[NumeroDeNivel]) {
					MisilCruceroInteligente MCI = new MisilCruceroInteligente();
					oleadaEnemigos.add(MCI);
				} else {
					MisilCrucero MC = new MisilCrucero();
					oleadaEnemigos.add(MC);
				}
			}

			/*
			 * Se agregan a la oleada de enemigos 2 bombarderos por nivel, siendo
			 * aleatoriamente seleccionado si es un Satelite o un Avion utilizando un random
			 * que devuelva 0 o 1 para determinar que bombardero sera el que se agregue a la
			 * oleada
			 */

			if ((oleada == 3) || (oleada == 7)) {
				int numeroDeBombarderosPorOleada = (aleatorio.nextInt(1));
				if (numeroDeBombarderosPorOleada == 1) {
					Avion A = new Avion();
					oleadaEnemigos.add(A);
				} else {
					Satelite S = new Satelite();
					oleadaEnemigos.add(S);
				}
			}

			// Agrego la oleada a la lista de oleadas
			Enemigos.add(oleadaEnemigos);
		}

	}

	/**
	 * Vector de ayuda para Determinar en que niveles va a aparecer el
	 * misilcrucerointeligente y el misilcrucerotonto
	 */
	public static void DeterminarArregloDeMisiles() {
		VectorDeMisilesCrucerosPorNivel[3] = true;
		VectorDeMisilesCrucerosPorNivel[4] = true;
		VectorDeMisilesCrucerosPorNivel[7] = true;
		VectorDeMisilesCrucerosPorNivel[8] = true;
		VectorDeMisilesCrucerosPorNivel[11] = true;
		VectorDeMisilesCrucerosPorNivel[12] = true;
		VectorDeMisilesCrucerosPorNivel[15] = true;
		VectorDeMisilesCrucerosPorNivel[16] = true;
	}
}
