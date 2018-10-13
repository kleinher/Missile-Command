package general;

import java.util.LinkedList;
import java.util.Random;
import java.util.Set;
import java.util.Vector;

// Inicio1= de 0 a 175 / Inicio2 de 175 a 350 / inicio 3 de 350 a 525
import enemigos.Enemigo;
import enemigos.MisilBalistico;
import enemigos.MisilCrucero;
import enemigos.MisilCruceroInteligente;

public class Oleada {
	static boolean[] VectorDeMisilesCrucerosPorNivel = new boolean[17];
	private LinkedList<Enemigo> ListaDeOleadasPorNivel;
	
	// Determina si hay enemigos en la lista de oleadas para agregar
	public boolean hayEnemigos() { 
		if (ListaDeOleadasPorNivel.isEmpty())
			return false;
		else
			return true;
	}

	/*CrearListaDeOleadasPorNivel
	 * Metodo estatico de la clase Oleada
	 * Parametro Enemigos: cada nodo de esta lista es una oleada.
	 * */
	public static void CrearListaDeOleadasPorNivel(LinkedList<LinkedList<Enemigo>> Enemigos, int NumeroDeNivel) {
		Random aleatorio = new Random();
		int oleada = 1;
		
		// Genera un numero Random de enemigos para cada nivel de 12 a 17
		Integer numeroDeEnemigosPorNivel = (12+aleatorio.nextInt(6)); 
		
		//Adentro de este loop se agregan todos los enemigos a la lista Enemigos
		while (numeroDeEnemigosPorNivel > 0) {
			oleada++;
			//oleadaEnemigos es una oleada
			LinkedList<Enemigo> oleadaEnemigos = new LinkedList<Enemigo>();
			
			// Genera Un numero Random de enemigos para cada oleada de 0 a 4
			int numeroDeEnemigosPorOleada = (aleatorio.nextInt(5));
			for (int i=numeroDeEnemigosPorOleada; i != 0; i--) { 
				
				/* Genera Un numero Random de enemigos para cada oleada de 0 a 4*/
				MisilBalistico MB = new MisilBalistico();		
				oleadaEnemigos.add(MB);
				numeroDeEnemigosPorNivel--;
			}

			//En todos los niveles se van a agregar los Misil Cruceros Inteligentes y los Misil cruceros apartir de la oleada 4
			if (oleada == 4) {
				if (VectorDeMisilesCrucerosPorNivel[NumeroDeNivel]) {
					MisilCruceroInteligente MCI = new MisilCruceroInteligente();
					oleadaEnemigos.add(MCI);
				}
				else 
				{
					MisilCrucero MC = new MisilCrucero();
					oleadaEnemigos.add(MC);
				}
			}
			
			//Agrego la oleada a la lista de oleadas
			Enemigos.add(oleadaEnemigos);
		}

	}

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
