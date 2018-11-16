package modelo.enemigos;
import java.util.Random;
import modelo.gestores.GestorDeNivel;

public class Bombardero extends Enemigo{
	
	public Bombardero() {
		super();
		this.puntos=150;
		determinarRecorrido();
	}
	
	public void mover() {
		if(this.posicionInicial.getPosicionX()==525)
			this.posicionActual.actualizarPosicion((int)this.posicionActual.getPosicionX()-3,(int)this.posicionActual.getPosicionY());
		else
			this.posicionActual.actualizarPosicion((int)(this.posicionActual.getPosicionX()+3), (int)(this.posicionActual.getPosicionY()));
		}
	
	/*Determina la posicion inicial del borbardero, y su posicion de destino*/
	public void determinarRecorrido() {
		int AparicionEnX;
		int AparicionEnY;
		Random aleatorio = new Random();
		if (GestorDeNivel.getGestorDeNivel().getNivelActual()<5) {
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
		this.posicionInicial.actualizarPosicion(AparicionEnX, AparicionEnY);
		this.posicionActual.actualizarPosicion(AparicionEnX, AparicionEnY);

		/* En Base al inicio determina la posicion de destino */
		if (AparicionEnX == 525) {
			this.posicionObjetivo.actualizarPosicion(0, AparicionEnY);
		} else {
			this.posicionObjetivo.actualizarPosicion(525, AparicionEnY);
		}
	}

	public void desaparecer() {
		
		
	}

}
