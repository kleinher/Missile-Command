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
	
	// Determina si hay enemigos en la lista de oleadas para agregar
	public boolean hayEnemigos() { 
		if (ListaDeOleadasPorNivel.isEmpty())
			return false;
		else
			return true;
	}

	private void CrearListaDeOleadasPorNivel(LinkedList<Enemigo> Enemigos) {
		Random aleatorio = new Random();
		int oleada = 1;
		
		/* Genera un numero Random de enemigos para cada nivel de 12 a 17*/
		Integer numeroDeEnemigosPorNivel = (12+aleatorio.nextInt(6)); 
		while (numeroDeEnemigosPorNivel > 0) {
			
			// Genera Un numero Random de enemigos para cada oleada de 0 a 4
			int numeroDeEnemigosPorOleada = (aleatorio.nextInt(5));
			for (int i=numeroDeEnemigosPorOleada; i == 0; i--) { 
				
				/* Genera Un numero Random de enemigos para cada oleada de 0 a 4*/
				MisilBalistico MB = new MisilBalistico();		
				ListaDeOleadasPorNivel.add(MB);
				numeroDeEnemigosPorNivel--;
				oleada++;
			}
			if (oleada == 4)
				if (VectorDeMisilesCrucerosPorNivel[Juego.getNivelActual()]) {
					MisilCruceroInteligente MCI = new MisilCruceroInteligente();
					ListaDeOleadasPorNivel.add(MCI);
				}
				else 
				{
					MisilCrucero MC = new MisilCrucero();
					ListaDeOleadasPorNivel.add(MC);
				}
			ListaDeOleadasPorNivel.add(null);
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
}
