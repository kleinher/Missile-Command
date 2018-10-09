package general;

public class Nivel {
	private Integer numeroDeOleada;
	private Double velocidad;
	private Integer[] numeroDeEnemigos;
	private Base[] bases;

	public Nivel(Integer nivelActual) { /*limpia pantalla,reponer misiles, reconstruye bases*/  
	
	}

	public Nivel() {
		// TODO Auto-generated constructor stub
	}

	public void loopDelNivel() {
		while (Oleada.hayEnemigos()&& !Colisiones.Hayciudades()) // IMPLEMENTAR
			try{
				Thread.sleep(1000 / 60);
				this.ActualizarPosiciones();
				Colisiones.comprobarColision(this);
				
			}
		catch(Exception e){
			System.err.println(e);
		}
		PuntajeJugador.actualizarTablaDePuntajes(); //PARAMETROS
		
	}

	private void ActualizarPosiciones() {

	}

}
