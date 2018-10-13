package enemigos;

import java.util.Random;
import general.Posicion;
public abstract class Misiles extends Enemigo{ 
	private static Posicion[] posicionDeLasBasesYCiudades= new Posicion[8];
	
	Misiles (){
		determinarObjetivo();
		determinarInicio();
	}

	/*Este metodo va a calcular el desplazamiento del misil balistico interplanetario(Enemigo) */
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
	/*Determina El objetivo de cada misil de manera aleatoria*/
	public void determinarObjetivo() {
		int posicionGeneralObjetivo;
		int posicionObjetivoX;
		int posicionObjetivoY;
		Random aleatorio = new Random();
		posicionGeneralObjetivo = (aleatorio.nextInt(10));
		posicionObjetivoX= posicionDeLasBasesYCiudades[posicionGeneralObjetivo].getPosicionX();
		posicionObjetivoY=posicionDeLasBasesYCiudades[posicionGeneralObjetivo].getPosicionY();
		this.PosicionObjetivo.actualizarPosicion(posicionObjetivoX, posicionObjetivoY);	
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
	/* Determina la posicion de las bases y las ciudades para facilitar el determinarObjetivo de cada misil*/
	private void DeterminarPosicionesDeLasbases() {
		int posX=40;
		int posY=450;
		for(int i=1;i<=9;i++) {
			posicionDeLasBasesYCiudades[i].actualizarPosicion(posX,posY);
			posX+=55;
		}
			
	}
	
}
