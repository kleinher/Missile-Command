package modelo.usuario;

import java.io.Serializable;

public class InformacionJugador implements Serializable,Comparable<InformacionJugador>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7274265027354244411L;
	String posicionRank;
	String nombreRank;
	int  puntajeRank;
	double tiempoJugado;

	public InformacionJugador(String posicion,String nombre, int puntaje, double tiempo) {
		this.posicionRank=posicion;
		this.puntajeRank=puntaje;
		this.nombreRank=nombre;
		this.tiempoJugado=tiempo;
		
	}

	@Override
	public int compareTo(InformacionJugador o) {
		if(puntajeRank>o.puntajeRank) {
			return -1;
		}
		if(puntajeRank<o.puntajeRank) {
			return 1;
		}
		return 0;
	}
}
