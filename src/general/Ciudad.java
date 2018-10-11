package general;

public class Ciudad {
	private Posicion posicion;
	private boolean hayCiudades;
	private boolean EstaViva;
	
	/*INSTANCIAR CIUDADES
	 * este metodo setea todas las posiciones de las 6 ciudades en la pantalla
	 * posX: determina la posicion en X de la ciudad, las ciudades tienen una distancia entre ellas de DistanciaEntreCiudad
	 * posY: determina la posicion en Y de la ciudad, todas tienen la misma posicion
	 * */
	public static void InstanciarCiudades(Ciudad[] ciudades) {
		//Se instancian las 6 ciudades del vector de ciudades
		int posX=95;
		int posY=450;
		int DistanciaEntreCiudades=55;
		
		//En este FOR se setean las posiciones en la pantalla de todas las ciudades
		for(int i = 1; i <= ciudades.length;i++) {
			ciudades[i] = new Ciudad();
			ciudades[1].posicion.actualizarPosicion(posX, posY);
			
			//Aumento la posicion para la ciudad siguiente
			posX += DistanciaEntreCiudades;
			
			/*Cuando se pasa la tercera ciudad hay una en la mitad, por lo tanto se suma una distancia mas para que no se superponga*/
			if(i == 3) {
				posX += DistanciaEntreCiudades;
			}
		}
		
	}
	public static boolean hayCiudades(Ciudad[] ciudades) {
		int indice = 0; 
		boolean hayCiudad = false;
		
		//Recorro el vector de ciudades para comprobar si hay ciudades en pie
		while(indice <= ciudades.length && !hayCiudad) {
			
			//En caso de que se encuentre una ciudad viva actualizo
			if(ciudades[indice].EstaViva) {
				hayCiudad=true;
			}
			indice++;
		}
		return hayCiudad;
	}
	

	public boolean estaViva() {
		return EstaViva;
	}

	
	public void sethayCiudades(boolean hayCiudades) {
		
		this.hayCiudades = hayCiudades;
	}
	public void destruccion() {
		// TODO Auto-generated method stub
		
	}
	public Posicion getPosicion() {
		return posicion;
	}

}
