package general;

public class Posicion {
	private Integer x;
	private Integer y;
	public Posicion actualizarPosicion(Integer x, Integer y){
		Posicion aux=new Posicion(x,y);
		return aux;
	}
	public Posicion(){
		
	}
	public Posicion(Integer x, Integer y){
		this.x=x;
		this.y=y;
	}
}
