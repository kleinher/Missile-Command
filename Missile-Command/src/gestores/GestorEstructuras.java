package gestores;

import java.util.LinkedList;

import clasesPadres.Enemigo;
import estructurasAliadas.Base;
import estructurasAliadas.Ciudad;
import misiles.Explosion;
import misiles.MisilAntibalistico;

/**
 * --Corrección para Reentrega-- Se agrega esta nueva clase GestorEstructuras.
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

public class GestorEstructuras {

	private static final GestorEstructuras GestorEstructuras = new GestorEstructuras();

	// Variables enemigas

	/* Lista de Enemigos Mostrados y procesados durante el nivel */
	private LinkedList<Enemigo> EnemigosEnPantalla;
	/*
	 * Lista de Enemigos en espera (Oleada) por ser procesados y aparecer en el
	 * nivel
	 */
	private LinkedList<LinkedList<Enemigo>> EnemigosEnEspera;

	// Variables aliadas
	private LinkedList<MisilAntibalistico> MisilesAliadosEnPantalla;
	private LinkedList<Explosion> explosionesEnPantalla;
	private Ciudad Ciudades[];
	private Base Bases[];

	/**
	 * Constructor que Inicializa las Estructuras del juego
	 */
	private GestorEstructuras() {

		this.MisilesAliadosEnPantalla = new LinkedList<MisilAntibalistico>();
		this.EnemigosEnPantalla = new LinkedList<Enemigo>();
		// Instancia las nueve ciudades
		this.Ciudades = new Ciudad[7];
		Ciudad.InstanciarCiudades(this.Ciudades);
		// Instancia las tres bases
		this.Bases = new Base[4];
		Base.InstanciarBases(this.Bases);
		// Crea la lista de enemigos del nivel
		this.EnemigosEnEspera = new LinkedList<LinkedList<Enemigo>>();
		Oleada.CrearListaDeOleadasPorNivel(EnemigosEnEspera, GestorDeNivel.getGestorDeNivel().getNivelActual());
		explosionesEnPantalla = new LinkedList<Explosion>();
		MisilesAliadosEnPantalla = new LinkedList<MisilAntibalistico>();
	}
	
	/**
	 * Vector que utiliza oleada para determinar en que niveles apareceran misiles
	 * tontos e inteligentes
	 */
	static boolean[] VectorDeMisilesCrucerosPorNivel = new boolean[17];
	

	/**
	 * Inicializa el vector que determina los misiles por nivel
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

	public static GestorEstructuras getGestorEstructuras() {
		return GestorEstructuras;
	}
	
	

	/////// Getters y Setters
	
	public static boolean[] getVectorDeMisilesCrucerosPorNivel() {
		return VectorDeMisilesCrucerosPorNivel;
	}
	
	public LinkedList<Enemigo> getEnemigosEnPantalla() {
		return EnemigosEnPantalla;
	}

	public void setEnemigosEnPantalla(LinkedList<Enemigo> enemigosEnPantalla) {
		EnemigosEnPantalla = enemigosEnPantalla;
	}

	public LinkedList<LinkedList<Enemigo>> getEnemigosEnEspera() {
		return EnemigosEnEspera;
	}

	public void setEnemigosEnEspera(LinkedList<LinkedList<Enemigo>> enemigosEnEspera) {
		EnemigosEnEspera = enemigosEnEspera;
	}

	public LinkedList<MisilAntibalistico> getMisilesAliadosEnPantalla() {
		return MisilesAliadosEnPantalla;
	}

	public void setMisilesAliadosEnPantalla(LinkedList<MisilAntibalistico> misilesAliadosEnPantalla) {
		MisilesAliadosEnPantalla = misilesAliadosEnPantalla;
	}

	public LinkedList<Explosion> getExplosionesEnPantalla() {
		return explosionesEnPantalla;
	}

	public void setExplosionesEnPantalla(LinkedList<Explosion> explosionesEnPantalla) {
		this.explosionesEnPantalla = explosionesEnPantalla;
	}

	public Ciudad[] getCiudades() {
		return Ciudades;
	}

	public void setCiudades(Ciudad[] ciudades) {
		Ciudades = ciudades;
	}

	public Base[] getBases() {
		return Bases;
	}

	public void setBases(Base[] bases) {
		Bases = bases;
	}
}
