package general;

public class Base {
	private int cantMisiles;
	private Posicion pos;
	private boolean viva;

	public int getCantMisiles() {
		return cantMisiles;
	}

	public void setCantMisiles(int cantMisiles) {
		this.cantMisiles = cantMisiles;
	}

	public Posicion getpos() {
		return pos;
	}

	public void setpos(Posicion pos) {
		this.pos= pos;
	}

	public boolean isviva() {
		if (this.cantMisiles == 0)
			return false;
		else
			return true;
	}
}
