package general;

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
}
