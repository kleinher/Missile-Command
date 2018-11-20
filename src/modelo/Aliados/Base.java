package modelo.Aliados;


import java.util.LinkedList;

import modelo.general.Posicion;
import modelo.gestores.GestorDeEstructuras;
import modelo.gestores.GestorDeNivel;

/**
 * Esta clase representa a cada una de las 3 bases que se necesitan en el juego
 * Posee un metodo estatico (InstanciarBases) que instancia dichas bases
 * 
 * @author LosPi
 *
 */
public class Base {
	private Posicion posicion;
	private LinkedList<MisilAntibalistico> listaMisilesAntibalisticos;
	private boolean estaViva;

	public Base() {
		this.posicion = new Posicion();
		this.estaViva = true;
		this.listaMisilesAntibalisticos = new LinkedList<MisilAntibalistico>();
	}

	/**
	 * Metodo constructor de Base
	 * 
	 * @param posX Determina la posicion en X de la base, las ciudades tienen una
	 *             distancia entre ellas de DistanciaEntreBases determina la
	 *             posicion en Y de la base, todas tienen la misma posicion
	 * @param posY Es la coordenada Y en la pantalla
	 */
	public Base(int posX, int posY) {
		this();
		this.posicion.actualizarPosicion(posX, posY);
	}

	/**
	 * Este metodo estatico Crea las 3 Instancias necesarias y setea sus posiciones
	 * en la pantalla
	 * 
	 * @param bases Vector de 3 bases
	 */
	public static void InstanciarBases(Base[] bases, double velocidad) {
		// Se instancian las 3 bases del vector de ciudades
		bases[1] = new Base(50, 438);
		bases[2] = new Base(260, 438);
		bases[3] = new Base(500, 438);
		for (int j = 0; j < 15; j++) {
			bases[1].listaMisilesAntibalisticos.add(new MisilAntibalistico(bases[1].posicion, velocidad));
			bases[2].listaMisilesAntibalisticos.add(new MisilAntibalistico(bases[2].posicion, velocidad));
			bases[3].listaMisilesAntibalisticos.add(new MisilAntibalistico(bases[3].posicion, velocidad));
		}
	}

	public Posicion getPosicion() {
		return posicion;
	}

	public Integer getCantMisiles() {
		return this.listaMisilesAntibalisticos.size();
	}

	/*
	 * Primero le determino el objetivo, en este caso lo "HardCodeamos" para
	 * testear, y luego agrego los misiles a la lista de misiles en pantalla
	 */
	/**
	 * ---DISPARAR sin teclado--- Este disparar recibe una posicion a disparar y
	 * elige la base mas cercana En este se dispara cuando no se determina
	 * explicitamente la base
	 * 
	 * @param posX posicion a disparar en X
	 * @param posY posicion a disparar en Y
	 */
	public static void Disparar(int posX, int posY) {
		if (posY < 385) {
			GestorDeEstructuras estructura = GestorDeNivel.getGestorDeNivel().getEstructuras();
			Base[] base = estructura.getBases();
			LinkedList<MisilAntibalistico> MisilesAliadosEnPantalla = estructura.getMisilesAliadosEnPantalla();

			int numeroDeBase = buscarBaseMasCercana(posX, base);

			if (numeroDeBase != 0) {
				lanzarElMisilAlInfinitoYMasAlla(MisilesAliadosEnPantalla, base[numeroDeBase], posX, posY);
			}
		}
	}

	/**
	 * ---DISPARAR con teclado--- Este disparar recibe una posicion a disparar y
	 * elige la base mas cercana En este se dispara cuando no se determina
	 * explicitamente la base
	 * 
	 * @param posX posicion a disparar en X
	 * @param posY posicion a disparar en Y
	 */
	public static void Disparar(int posX, int posY, int numeroDeBase) {
		if (posY < 385) {
			GestorDeEstructuras estructura = GestorDeNivel.getGestorDeNivel().getEstructuras();
			Base[] base = estructura.getBases();
			if (base[numeroDeBase].isEstaViva()) {
				LinkedList<MisilAntibalistico> MisilesAliadosEnPantalla = estructura.getMisilesAliadosEnPantalla();
				lanzarElMisilAlInfinitoYMasAlla(MisilesAliadosEnPantalla, base[numeroDeBase], posX, posY);
			}
		}
	}

	private static void lanzarElMisilAlInfinitoYMasAlla(LinkedList<MisilAntibalistico> MisilesAliadosEnPantalla,
			Base base, int posX, int posY) {
		if (!base.listaMisilesAntibalisticos.isEmpty()) {
			MisilAntibalistico aux = base.listaMisilesAntibalisticos.poll();
			aux.determinarObjetivo(posX, posY);
			aux.determinarDesplazamiento(GestorDeNivel.getGestorDeNivel().getEstructuras().getVelocidad() + 6);
			MisilesAliadosEnPantalla.add(aux);
		}
	}

	/*
	 * --Este método digno de Programacion I lo que hace es buscar la base más
	 * cercana --Lo que tiene de bueno es que comprueba si la base sigue viva. --En
	 * caso de que no exista ninguna base va a devolver un 0. --
	 */
	private static int buscarBaseMasCercana(double posX, Base[] bases) {
		double distancia = 9999;
		int base = 0;
		for (int i = 1; i <= 3; i++) {
			double auxDistancia = Math.abs(bases[i].getPosicion().getPosicionX() - posX);
			if ((auxDistancia < distancia) & bases[i].isEstaViva() & !bases[i].listaMisilesAntibalisticos.isEmpty()) {
				distancia = auxDistancia;
				base = i;
			}
		}
		return base;
	}

	public void destruccion() {
		this.setEstaViva(false);
	}

	public boolean isEstaViva() {
		return estaViva;
	}

	public void setEstaViva(boolean estaViva) {
		this.estaViva = estaViva;
	}

}
