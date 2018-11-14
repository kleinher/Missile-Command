package modelo.Aliados;

import java.util.LinkedList;

import modelo.general.Posicion;
import modelo.gestores.GestorDeEstructuras;
import modelo.gestores.GestorDeNivel;
import taller2.grafico.Dibujable;
import taller2.grafico.InformacionDibujable;

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
		this.estaViva=true;
		this.listaMisilesAntibalisticos=new LinkedList<MisilAntibalistico>();
	}

	/**
	 * Metodo constructor de Base
	 * 
	 * @param posX
	 *            Determina la posicion en X de la base, las ciudades tienen una
	 *            distancia entre ellas de DistanciaEntreBases determina la posicion
	 *            en Y de la base, todas tienen la misma posicion
	 * @param posY
	 *            Es la coordenada Y en la pantalla
	 */
	public Base(int posX, int posY) {
		this();
		this.posicion.actualizarPosicion(posX, posY);
	}

	/**
	 * Este metodo estatico Crea las 3 Instancias necesarias y setea sus posiciones
	 * en la pantalla
	 * 
	 * @param bases
	 *            Vector de 3 bases
	 */
	public static void InstanciarBases(Base[] bases) {
		// Se instancian las 3 bases del vector de ciudades
		int posX = 40;
		int posY = 450;
		int DistanciaEntreBases = 220;

		// En este FOR se setean las posiciones en la pantalla de todas las ciudades
		for (int i = 1; i < bases.length; i++) {
			bases[i] = new Base(posX, posY);
			// Aumento la posicion para la ciudad siguiente
			posX += DistanciaEntreBases;

			/*
			 * Cuando se pasa la tercera ciudad hay una en la mitad, por lo tanto se suma
			 * una distancia mas para que no se superponga
			 */
			if (i == 3)
				posX += DistanciaEntreBases;

			// Agrego 15 MisilesAntibalisticos a la lista de misiles antibalisticos de cada
			// base
			for (int j = 0; j < 15; j++) {
				bases[i].listaMisilesAntibalisticos.add(new MisilAntibalistico(bases[i].posicion));
			}
		}

	}

	public Posicion getPosicion() {
		return posicion;
	}

	public int getCantMisiles() {
		return this.listaMisilesAntibalisticos.size();
	}

	/*
	 * Primero le determino el objetivo, en este caso lo "HardCodeamos" para
	 * testear, y luego agrego los misiles a la lista de misiles en pantalla
	 */
	/**
	 * ---DISPARAR sin teclado--- 
	 * Este disparar recibe una posicion a disparar y elige la base mas cercana
	 * En este se dispara cuando no se determina explicitamente la base
	 * @param posX posicion a disparar en X
	 * @param posY posicion a disparar en Y
	 */
	public static void Disparar(int posX, int posY) {
		GestorDeEstructuras estructura = GestorDeNivel.getGestorDeNivel().getEstructuras();
		Base[] base = estructura.getBases();
		LinkedList<MisilAntibalistico> MisilesAliadosEnPantalla = estructura.getMisilesAliadosEnPantalla();
		
		int numeroDeBase = buscarBaseMasCercana(posX,base);
		
		if(numeroDeBase != 0) {
				lanzarElMisilAlInfinitoYMasAlla(MisilesAliadosEnPantalla,base[numeroDeBase], posX, posY);
		}
	}
	/**
	 * ---DISPARAR con teclado--- 
	 * Este disparar recibe una posicion a disparar y elige la base mas cercana
	 * En este se dispara cuando no se determina explicitamente la base
	 * @param posX posicion a disparar en X
	 * @param posY posicion a disparar en Y
	 */
	public static void Disparar(int posX, int posY, int numeroDeBase) {
		GestorDeEstructuras estructura = GestorDeNivel.getGestorDeNivel().getEstructuras();
		Base[] base = estructura.getBases();
		LinkedList<MisilAntibalistico> MisilesAliadosEnPantalla = estructura.getMisilesAliadosEnPantalla();
		lanzarElMisilAlInfinitoYMasAlla(MisilesAliadosEnPantalla,base[numeroDeBase], posX, posY);
	}
	private static void lanzarElMisilAlInfinitoYMasAlla(LinkedList<MisilAntibalistico> MisilesAliadosEnPantalla,
														Base base,int posX, int posY) {
		MisilAntibalistico aux = base.listaMisilesAntibalisticos.poll();
		aux.determinarObjetivo(posX, posY);
		MisilesAliadosEnPantalla.add(aux);
	}
	
	/* --Este método digno de Programacion I lo que hace es buscar la base más cercana
	 * --Lo que tiene de bueno es que comprueba si la base sigue viva.
	 * --En caso de que no exista ninguna base va a devolver un 0.
	 * --
	 * */
	private static int buscarBaseMasCercana(double posX,Base[] bases) {
		double distancia = 9999;
		int base=0;
		for(int i=1; i<=3;i++) {
			double auxDistancia = Math.abs(bases[i].getPosicion().getPosicionX() - posX);
			if((auxDistancia < distancia)
				& bases[i].isEstaViva() & !bases[i].listaMisilesAntibalisticos.isEmpty()) {
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
