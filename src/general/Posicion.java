package general;
/*Posicion general de todos los objetos */
public class Posicion {
	private int posicionX;
	private int posicionY;
	
	public void actualizarPosicion(int x, int y){
		this.posicionX = posicionX;
		this.posicionY = posicionY;
	}
	public Posicion(){
		
	}
	public Integer getPosicionX() {
		return posicionX;
	}
	public Integer getPosicionY() {
		return posicionY;
	}
	public Posicion(Integer x, Integer y){
		this.posicionX=posicionX;
		this.posicionY=posicionY;
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
