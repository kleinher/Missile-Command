package general;

import java.util.LinkedList;

import enemigos.Enemigo;

/**
 * --Corrección para Reentrega-- Se agrega esta nueva clase GestorEstructuras.
 * 
 * Esta Clase inicializa y contiene las estructuras de datos que se necesitan
 * para el juego Es Singleton, de manera que quien quiera usarla obtiene la
 * unica instancia existente para obtener las estructuras del juego
 * 
 * package general;
 * 
 * @author eze96
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

	public static GestorEstructuras getGestorEstructuras() {
		return GestorEstructuras;
	}

	/////// Getters y Setters
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
