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

	public Base[] getBases() {
		return bases;
	}

	public void setBases(Base[] bases) {
		this.bases = bases;
	}

	public boolean getPerdio() {
		// TODO Auto-generated method stub
		return false;
	}

	public void jugar() {
		// TODO Auto-generated method stub

	}
}
