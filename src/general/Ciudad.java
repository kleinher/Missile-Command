package general;

public class Ciudad {
	private boolean hayCiudades;
	private boolean EstaViva;
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

}
