package general;

/**
 * Esta Clase Representa cada una de las 6 Ciudades del juego, posee un metodo
 * estatico (InstanciarCiudades) que intancia dichas ciudades
 * 
 * @author eze96
 *
 */
public class Ciudad {
	private Posicion posicion = new Posicion();
	private boolean EstaViva;

	public Ciudad() {

	}

	/**
	 * Metodo Constructor de Ciudades
	 * 
	 * @param posX
	 *            Determina la posicion en X de la ciudad, las ciudades tienen una
	 *            distancia entre ellas de DistanciaEntreCiudad
	 * @param posY
	 *            Determina la posicion en Y de la ciudad, todas tienen la misma
	 *            posicion
	 */
	public Ciudad(int posX, int posY) {
		this();
		this.posicion.actualizarPosicion(posX, posY);

	}

	/**
	 * Este metodo instancia las Seis ciudades del vector de ciudades
	 * y setea sus posiciones en la pantalla
	 * 
	 * @param ciudades
	 */
	public static void InstanciarCiudades(Ciudad[] ciudades) {
		int posX = 95;
		int posY = 450;
		int DistanciaEntreCiudades = 55;

		// En este FOR se setean las posiciones en la pantalla de todas las ciudades
		for (int i = 1; i < ciudades.length; i++) {
			ciudades[i] = new Ciudad(posX, posY);

			// Aumento la posicion para la ciudad siguiente
			posX += DistanciaEntreCiudades;

			/*
			 * Cuando se pasa la tercera ciudad hay una en la mitad, por lo tanto se suma
			 * una distancia mas para que no se superponga
			 */
			if (i == 3) {
				posX += DistanciaEntreCiudades;
			}
		}
	}

	/**
	 * Este Metodo estatico es compartido por las 6 instancias de ciudades Se
	 * encarga de dar informacion sobre la existencia de las ciudades
	 * 
	 * @param ciudades
	 * @return Retorna un booleano con la existencia o no de ciudades
	 */
	public static boolean hayCiudades(Ciudad[] ciudades) {
		int indice = 0;
		boolean hayCiudad = false;

		// Recorro el vector de ciudades para comprobar si hay ciudades en pie
		while (indice <= ciudades.length && !hayCiudad) {

			// En caso de que se encuentre una ciudad viva actualizo
			if (ciudades[indice].EstaViva) {
				hayCiudad = true;
			}
			indice++;
		}
		return hayCiudad;
	}

	/**
	 * 
	 * @return Retorna el estado de existencia de una ciudad en particular
	 */
	public boolean estaViva() {
		return EstaViva;
	}

	public void destruccion() {

		// TODO Auto-generated method stub

	}

	public Posicion getPosicion() {
		return posicion;
	}

}
