package general;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;
import java.util.Vector;

// Inicio1= de 0 a 175 / Inicio2 de 175 a 350 / inicio 3 de 350 a 525
import enemigos.Enemigo;
import enemigos.MisilBalistico;
import enemigos.MisilCruceroTonto;
import enemigos.MisilCruceroInteligente;

public class Oleada {
	static boolean[] VectorDeMisilesCrucerosPorNivel = new boolean[16];
	private LinkedList<Enemigo> ListaDeOleadasPorNivel;
	
	// Determina si hay enemigos en la lista de Oleadas para agregar
	public boolean hayEnemigos() { 

		if (ListaDeOleadasPorNivel.isEmpty())
			return false;
		else
			return true;
	}

	public static void CrearListaDeOleadasPorNivel(int nivelAct, LinkedList<Enemigo> ListaDeOleadasPorNivel) {
		Random aleatorio = new Random();
		int NumeroDeOleada = 1;
		DeterminarArregloDeMisiles();
		/* Genera Un numero Random de enemigos para cada nivel de 12 a 17*/
		Integer NumeroDeEnemigosPorNivel = (12 + aleatorio.nextInt((17 + 1) - 12)); 
		
		while (NumeroDeEnemigosPorNivel > 0) {
			// Genera Un numero Random de enemigos para cada Oleada de 0 a 4
			int numeroDeEnemigosPorOleada = (aleatorio.nextInt(5));
			
			//Agrega los Misiles Balisticos Interplanetaros a la lista
			for (int i=numeroDeEnemigosPorOleada; i == 0; i--) {
				MisilBalistico MB = new MisilBalistico();
				ListaDeOleadasPorNivel.add(MB);
				NumeroDeEnemigosPorNivel--;
			}
			//Despues de cargar los misiles, aumento el numero de oleada, para no agregar al nivel
			NumeroDeOleada++;
			
			//Solo apartir de la oleada 4 van a agregarse misiles crusero tonto y misiles crucero inteligente
			if (NumeroDeOleada == 4) {
				
				//Agrega Misil Crucero Inteligente en los niveles 3,4,7,8,11,12,15,16
				if (VectorDeMisilesCrucerosPorNivel[nivelAct])
				{
					MisilCruceroInteligente MCI = new MisilCruceroInteligente();
					ListaDeOleadasPorNivel.add(MCI);
				}
				
				//Agrega Misil Crucero Tonto en los niveles 1,2,5,6,9,10,13,14
				else 
				{
					MisilCruceroTonto MCT = new MisilCruceroTonto();
					ListaDeOleadasPorNivel.add(MCT);
				}
			}
			
			//Apilar un null a la lista para delimitar las oleadas
			ListaDeOleadasPorNivel.add(null);
		}
	}

	/*DETERMINAR ARREGLO MISILES
	 *Llena el vector de niveles con los booleanos que representan Cruceros tonto e Inteligentes
	 * */
	private static void DeterminarArregloDeMisiles() {
		VectorDeMisilesCrucerosPorNivel[3] = true;
		VectorDeMisilesCrucerosPorNivel[5] = true;
		VectorDeMisilesCrucerosPorNivel[7] = true;
		VectorDeMisilesCrucerosPorNivel[8] = true;
		VectorDeMisilesCrucerosPorNivel[10] = true;
		VectorDeMisilesCrucerosPorNivel[11] = true;
		VectorDeMisilesCrucerosPorNivel[14] = true;
		VectorDeMisilesCrucerosPorNivel[15] = true;
	}

}
