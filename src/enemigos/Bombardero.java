package enemigos;

import java.util.Random;

import general.Juego;

public class Bombardero extends Enemigo {

	public void mover() {
		if(this.PosicionInicial.getPosicionX()==525)
			this.PosicionActual.actualizarPosicion(this.PosicionActual.getPosicionX()-1,this.PosicionActual.getPosicionY());
		else 
			this.PosicionActual.actualizarPosicion(this.PosicionActual.getPosicionX()+1, this.PosicionActual.getPosicionY());
	}
	public void destruccion() {
		
	}
	public void determinarRecorrido() {
		int AparicionEnX;
		int AparicionEnY;
		Random aleatorio = new Random();
		if (Juego.getNivelActual()<5) {
			/*Determina una posicion random de */
			AparicionEnY = (40+aleatorio.nextInt(121));
		} else {/*
				 * Determina Una posicion random (mas Abajo) que la anterior para aumentar la
				 * dificultad
				 */
			AparicionEnY = (160 + aleatorio.nextInt(160));
		}
		/*
		 * Genera un random de 0 a 1 para determinar el lado en el que sale(Coordenada x) izquierda o derecha de la pantalla eso lo seteo tanto en la posicion de inicio, como en la actual
		 */
		AparicionEnX = (aleatorio.nextInt(2)) * 525;
		this.PosicionInicial.actualizarPosicion(AparicionEnX, AparicionEnY);
		this.PosicionActual.actualizarPosicion(AparicionEnX, AparicionEnY);

		/* En Base al inicio determina la posicion de destino */
		if (AparicionEnX == 525) {
			this.PosicionObjetivo.actualizarPosicion(0, AparicionEnY);
		} else {
			this.PosicionObjetivo.actualizarPosicion(525, AparicionEnY);
		}
	}

}
