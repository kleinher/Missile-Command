package general;

public class Base {
	private int cantMisiles;
	private Posicion posicion;
	private boolean viva;

	/*INSTANCIAR CIUDADES
	 * este metodo setea todas las posiciones de las 3 Bases en la pantalla
	 * posX: determina la posicion en X de la base, las ciudades tienen una distancia entre ellas de DistanciaEntreBases
	 * posY: determina la posicion en Y de la base, todas tienen la misma posicion
	 * */
	
	public Base() {
		this.posicion=new Posicion();
	}
	public Base(int posX, int posY) {
		this();
		this.posicion.actualizarPosicion(posX, posY);
	}
	public static void InstanciarBases(Base[] bases) {
		//Se instancian las 6 ciudades del vector de ciudades
		int posX=40;
		int posY=450;
		int DistanciaEntreBases=220;
		
		//En este FOR se setean las posiciones en la pantalla de todas las ciudades
		for(int i = 1; i < bases.length;i++) {
			bases[i] = new Base(posX,posY);
			
			//Aumento la posicion para la ciudad siguiente
			posX += DistanciaEntreBases;
			
			//Cuando se pasa la tercera ciudad hay una en la mitad, por lo tanto se suma una distancia mas para que no se superponga
			if(i == 3) {
				posX += DistanciaEntreBases;
			}
		}
		
	}

	public Posicion getPosicion() {
		return posicion;
	}



}
