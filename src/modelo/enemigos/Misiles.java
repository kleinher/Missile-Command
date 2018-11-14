package modelo.enemigos;
import java.util.Random;
import modelo.general.Estela;
import modelo.general.Posicion;

/**Modela todos los misiles del juego
 * 
 * @author Nicol
 *
 */
public abstract class Misiles extends Enemigo{ 
	private static Posicion[] posicionDeLasBasesYCiudades;
	private Estela estela;
	public Misiles (){
		this.determinarObjetivo();
		this.determinarInicio();
		this.determinarEstela();
	}

	private void determinarEstela() {
		this.estela = new Estela();
	}
	
	public Estela getEstela() {
		return estela;
	}

	/*Este metodo va a calcular el desplazamiento del misil balistico interplanetario(Enemigo) */
	public void mover(int velocidad){
		double pendiente;
		int distanciaX;
		int distanciaY;
		double movimientoX;
		double movimientoY;
		

		//Calculo de los catetos x e y(desplazamiento en x e y)
		distanciaX = this.posicionObjetivo.getPosicionX() - this.posicionActual.getPosicionX();
		distanciaY = this.posicionObjetivo.getPosicionY() - this.posicionActual.getPosicionY();
		
		//Calculo de la pendiente de la hipotenusa(distancia)
		if(distanciaX != 0) {
			pendiente = (double)distanciaY/(double)distanciaX;
		}
		else {
			if(this.posicionInicial.getPosicionY() > this.posicionObjetivo.getPosicionY())
				pendiente = -1;
			else
				pendiente = 1;
		}
		//Calculo del movimiento en X
		movimientoX= Math.sqrt(Math.pow(velocidad, 2)/(1+Math.pow(pendiente,2)));
		//En base a la direccion se elige el signo correcto
				if(distanciaX<0) 
				{
					movimientoX = ((-1)*movimientoX);
				}
		movimientoY= (pendiente*movimientoX);
		
		//Agrego puntos a la estela
		this.estela.agregarPuntoALaEstela(posicionActual);
		
		//Actualiza la posicion 
		this.posicionActual.actualizarPosicion((int)(this.posicionActual.getPosicionX()+movimientoX),
											   (int)(this.posicionActual.getPosicionY()+movimientoY));

	}
	 
	/*Determina El objetivo de cada misil de manera aleatoria*/
		
	public void determinarObjetivo() {
		int posicionGeneralObjetivo;
		int posicionObjetivoX;
		int posicionObjetivoY;
		Random aleatorio = new Random();
		posicionGeneralObjetivo = 1+(aleatorio.nextInt(9));

		posicionObjetivoX = posicionDeLasBasesYCiudades[posicionGeneralObjetivo].getPosicionX();
		posicionObjetivoY = posicionDeLasBasesYCiudades[posicionGeneralObjetivo].getPosicionY();
		this.posicionObjetivo.actualizarPosicion(posicionObjetivoX, posicionObjetivoY);	
	}
	
	/*Determina el inicio comenzando siempre en el inicio de la pantalla en la coordenada 'y', y de manera completamente aleatoria en la coordenada 'x'  */
	public void determinarInicio() {
		int aparicionEnX;
		int aparicionEnY=0;
		Random aleatorio = new Random();
		aparicionEnX= (aleatorio.nextInt(525));
		this.posicionInicial.actualizarPosicion(aparicionEnX, aparicionEnY);
		this.posicionActual.actualizarPosicion(aparicionEnX, aparicionEnY);
	}
	public void destruccion(){

		
	}
	/* Determina la posicion de las bases y las ciudades para facilitar el determinarObjetivo de cada misil*/
	public static void DeterminarPosicionesDeLasbases() {
		int posX=40;
		int posY=450;
		posicionDeLasBasesYCiudades = new Posicion[10];
		for(int i=1;i<=9;i++) {
			posicionDeLasBasesYCiudades[i]=new Posicion();
			posicionDeLasBasesYCiudades[i].actualizarPosicion(posX,posY);
			posX+=55;
		}
			
	}
	
}
