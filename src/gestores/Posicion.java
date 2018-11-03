package gestores;
/*Posicion general de todos los objetos */
public class Posicion {
	private int posicionX;
	private int posicionY;
	
	public Posicion(){
		this.posicionX=0;
		this.posicionY=0;
	}
	public Posicion(Posicion pos){
		this.posicionX=pos.getPosicionX();
		this.posicionY=pos.getPosicionY();
	}
	public void actualizarPosicion(int x, int y){
		this.posicionX = x;
		this.posicionY = y;
	}
	public void actualizarPosicion(Posicion pos){
		this.posicionX=pos.getPosicionX();
		this.posicionY=pos.getPosicionY();
	}

	public Integer getPosicionX() {
		return posicionX;
	}
	public Integer getPosicionY() {
		return posicionY;
	}
	public Posicion(Integer x, Integer y){
		this.posicionX=x;
		this.posicionY=x;
	}
	
	/*Metodo equals para determinar si dos posiciones son iguales*/
	 public boolean equals (Object pos) {
		 Posicion pos1=(Posicion) pos;
         if ((this.posicionX==pos1.getPosicionX()) && (this.posicionY==pos1.getPosicionY())) {
              return true; 
         } 
         else{ 
        	 return false; 
        }
  }
}
