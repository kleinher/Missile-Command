package general;

public class Posicion {
	private int posicionX;
	private int posicionY;
	//HACER EL METODO EQUALS
	public void actualizarPosicion(int x, int y){
		this.posicionX = x;
		this.posicionY = y;
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
}
