package enemigos;

import java.util.Random;

import general.Posicion;

public abstract class Misiles extends Enemigo{
	/*
	 *MOVER 
	 *Este método va a calcular el desplazamiento del misil balistico interplanetario(Enemigo)
	 *
	 */
	public Misiles () {
	}
	public void mover(){
		double pendiente;
		double distancia;
		int distanciaX;
		int distanciaY;
		int movimientoX;
		int movimientoY;
		
		//Calculo de los catetos x e y(desplazamiento en x e y)
		distanciaX = this.PosicionInicial.getPosicionX() - this.PosicionObjetivo.getPosicionX();
		distanciaY = this.PosicionInicial.getPosicionY() - this.PosicionObjetivo.getPosicionY();
		
		//Calculo de la hipotenusa(distancia)
		distancia = Math.sqrt(Math.pow(distanciaX, 2) + Math.pow(distanciaY, 2));
		
		//Calculo de la pendiente de la hipotenusa(distancia)
		pendiente = distanciaX/distanciaY;
		
		//Calculo del movimiento en X
		movimientoX= (int)Math.sqrt(Math.pow(distancia, 2)/(1+Math.pow(pendiente,2)));
		movimientoY= (int)pendiente*movimientoX;
		
		//En base a la direccion se elige el signo correcto
		if(distanciaX<0) 
		{
			movimientoX = ((-1)*movimientoX);
		}
		//Actualiza la posicion 
		this.PosicionActual.actualizarPosicion(this.PosicionActual.getPosicionX()+movimientoX,
											   this.PosicionActual.getPosicionY()+movimientoY);
	}
	public Posicion determinarObjetivo(Posicion[] vectorPosicionesAliados) {
		int numeroDeObjetivo = 0;
		Posicion Objetivo = new Posicion();
		Random aleatorio = new Random();
		numeroDeObjetivo = 1+(aleatorio.nextInt(10));
		Objetivo.actualizarPosicion(vectorPosicionesAliados[numeroDeObjetivo].getPosicionX(), vectorPosicionesAliados[numeroDeObjetivo].getPosicionY());
		
		return Objetivo;
	}
	/*Determina el inicio comenzando siempre en el inicio de la pantalla en la coordenada 'y', y de manera completamente aleatoria en la coordenada 'x'  */
	public void determinarInicio() {
		int aparicionEnX;
		int aparicionEnY=0;
		Random aleatorio = new Random();
		aparicionEnX= (aleatorio.nextInt(525));
		this.PosicionInicial.actualizarPosicion(aparicionEnX, aparicionEnY);
		this.PosicionActual.actualizarPosicion(aparicionEnX, aparicionEnY);
	}
	public void destruccion(){
		
	}
}
