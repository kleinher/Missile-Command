package enemigos;

public abstract class Misiles extends Enemigo{
	/*
	 *MOVER 
	 *Este método va a calcular el desplazamiento del misil balistico interplanetario(Enemigo)
	 *
	 */
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
	public void DeterminarObjetivo(){
		int PosicionObjetivox;
		int PosicionObjetivoy;
	}
}
