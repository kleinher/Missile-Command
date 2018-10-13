package general;

import enemigos.Misiles;

public class MisilAntibalistico extends Misiles{
	/*variable que dice que exploto o no*/
	private boolean exploto;
	public boolean isExploto() {
		return exploto;
	}
	public void setExploto(boolean exploto) {
		this.exploto = exploto;
	}
	public AreaDeExplosion getArea() {
		return Area;
	}
	public void setArea(AreaDeExplosion area) {
		Area = area;
	}
	private AreaDeExplosion Area;


	@Override
	public void destruccion() {
		// TODO Auto-generated method stub
		
	}
}
