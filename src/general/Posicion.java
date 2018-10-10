package general;

public class Posicion {
	private Integer x;
	private Integer y;
	
	public void actualizarPosicion(Integer x, Integer y){
		this.x = x;
		this.y = y;
	}
	public Posicion(){
		
	}
	public Posicion(Integer x, Integer y){
		this.x=x;
		this.y=y;
	}
}
