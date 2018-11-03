package estructurasAliadas;

import java.util.LinkedList;

import gestores.GestorEstructuras;
import gestores.Posicion;
import misiles.MisilAntibalistico;
import taller2.grafico.Dibujable;
import taller2.grafico.InformacionDibujable;

/**
 * Esta clase representa a cada una de las 3 bases que se necesitan en el juego
 * Posee un metodo estatico (InstanciarBases) que instancia dichas bases
 * 
 * @author LosPi
 *
 */
public class Base implements Dibujable{
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
	 * ---DISPARAR--- 
	 * Este metodo Estatico primero determina el objetivo (est� "Harcodeado")
	 * y luego agrega los misiles a la lista de misiles en pantalla
	 * 
	 * @param base >> La base desde la que se dispara
	 * @param MisilesAliadosEnPantalla >> El misil que se va a disparar
	 */
	public static void Disparar() {
		Base base=GestorEstructuras.getGestorEstructuras().getBases()[1];
		int posX = 40, posY = 240;
		if(!base.listaMisilesAntibalisticos.isEmpty()) {
		
		for (int i = 1; i <= 3; i++) {
			MisilAntibalistico aux = base.listaMisilesAntibalisticos.poll();
			aux.determinarObjetivo(posX, posY);
			GestorEstructuras.getGestorEstructuras().getMisilesAliadosEnPantalla().add(aux);
			posX += 55;
		}
		}
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

	@Override
	public InformacionDibujable getInformacionDibujable() {
		InformacionDibujable info = new InformacionDibujable(this.posicion.getPosicionX(),this.posicion.getPosicionY() , 'B');
		return info;
	}
}
