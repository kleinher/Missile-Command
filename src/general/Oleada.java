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
	static boolean[] VectorDeMisilesCrucerosPorNivel = new boolean[16];
	private LinkedList<Enemigo> ListaDeOleadasPorNivel;

	public boolean hayEnemigos() { // Determina si hay enemigos en la lista de oleadas para agregar
		if (ListaDeOleadasPorNivel.isEmpty())
			return false;
		else
			return true;
	}

	private LinkedList<Enemigo> CrearListaDeOleadasPorNivel(int nivelAct) {
		Random aleatorio = new Random();
		int oleada = 1;
		Integer numeroDeEnemigosPorNivel = (12+aleatorio.nextInt(6)); /* Genera Un numero Random de enemigos para cada nivel de 12 a17*/
		while (numeroDeEnemigosPorNivel > 0) {
			int numeroDeEnemigosPorOleada = (aleatorio.nextInt(5));// Genera Un numero Random de enemigos para cada oleada de 0 a 4
			for (int i=numeroDeEnemigosPorOleada; i == 0; i--) { 
				/* Genera Un numero Random de enemigos para cada oleada de 0 a 4*/
				MisilBalistico MB = new MisilBalistico();		//
				ListaDeOleadasPorNivel.add(MB);
				numeroDeEnemigosPorNivel--;
				oleada++;
			}
			if (oleada == 4)
				if (VectorDeMisilesCrucerosPorNivel[nivelAct]) {
					MisilCruceroInteligente MCI = new MisilCruceroInteligente();
					ListaDeOleadasPorNivel.add(MCI);
				}else {
					MisilCrucero MC = new MisilCrucero();
					ListaDeOleadasPorNivel.add(MC);
				}
			ListaDeOleadasPorNivel.add(null);
			// reinicio el hashset
		}

	}

	private static void DeterminarArregloDeMisiles() {
		VectorDeMisilesCrucerosPorNivel[3] = true;
		VectorDeMisilesCrucerosPorNivel[4] = true;
		VectorDeMisilesCrucerosPorNivel[7] = true;
		VectorDeMisilesCrucerosPorNivel[8] = true;
		VectorDeMisilesCrucerosPorNivel[11] = true;
		VectorDeMisilesCrucerosPorNivel[12] = true;
		VectorDeMisilesCrucerosPorNivel[15] = true;
		VectorDeMisilesCrucerosPorNivel[16] = true;

	}

	private void instanciarMisil() {

	}
}
