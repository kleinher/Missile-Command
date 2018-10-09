package general;

public class Nivel {
	private Integer numeroDeOleada;
	private Double velocidad;
	private Integer[] numeroDeEnemigos;
	private Base[] bases;

	public Nivel(Integer nivelActual) {
		// TODO Auto-generated constructor stub
	}

	public Nivel() {
		// TODO Auto-generated constructor stub
	}

	public void loopDelNivel() {
		while (Oleada.hayEnemigos()) // IMPLEMENTAR
			try{
				Thread.sleep(1000 / 60);
			}
		catch(Exception e){
			System.err.println(e);
		}
		this.Actualizar();
		Colisiones.comprobarColision();
	}

	private void Actualizar() {

	}

}
