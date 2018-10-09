package general;

import java.util.LinkedList;

public class Nivel {
	private Integer numeroDeOleada;
	private Double velocidad;
	private LinkedList Enemigos = new LinkedList ();
	private LinkedList Aliados = new LinkedList ();
	private Ciudad ciudades[] = new Ciudad[8];
	private Base bases[]= new Base[2];
	
	
	
	public Nivel(Integer nivelActual) { /* limpia pantalla,reponer misiles, reconstruye bases */
		for (int i=0;i<9;i++) {
			ciudades[i]= new Ciudad(); 
		}
		for (int i=0;i<3;i++) {
			bases[i]= new Base();
		}
	}

	public Nivel() {
		

	}

	public void loopDelNivel() 
		throws InterruptedException{
		while (Oleada.hayEnemigos() && !Ciudades.hayCiudades) // IMPLEMENTAR
		{
			Thread.sleep(1000 / 60);
			Colisiones.comprobarColision(this);
			this.ActualizarPosiciones();
			//pausa entre una oleada y la siguiente
			
		}
		PuntajeJugador.actualizarTablaDePuntajes(); // PARAMETROS
		// MostrarPuntaje (ParteGrafica)

	}

	private void ActualizarPosiciones() {
		//Actualizar posiciones de los misiles aliados, y de los enemigos
	}

}
