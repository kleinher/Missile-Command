package enemigos;

import java.util.Random;

import general.Juego;

public class Bombardero extends Enemigo{
	
	public void mover() {
	}
	public void destruccion() {
	}
	
	
	public void determinarRecorrido() {
		int AparicionEnX;
		int AparicionEnY;
		Random aleatorio = new Random();
		if(Juego.getNivelActual()<5){	
			AparicionEnY = (aleatorio.nextInt(161));// Genera Un numero Random de enemigos para cada oleada de 0 a 4
		}
		else
		{	/*Determina Una posicion ranmdom (mas Abajo) que la anterior para aumentar la dificultad*/
			AparicionEnY = (160+aleatorio.nextInt(160));
		}
		/*Genera un random de 0 a 1 para determinar el lado en el que sale(Coordenada en x)*/
			AparicionEnX = (aleatorio.nextInt(2))*525;
		this.PosicionInicial.actualizarPosicion(AparicionEnX,AparicionEnY);
		
		if(AparicionEnX==525) {
			this.PosicionObjetivo.actualizarPosicion(0, AparicionEnY);
		}
		else {
			this.PosicionObjetivo.actualizarPosicion(525, AparicionEnY);
		}
			
		}
		
}
