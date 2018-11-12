package controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import modelo.Aliados.Base;


public class AdapterDeMira extends MouseAdapter{
	@Override
	public void mouseClicked(MouseEvent ev) {
		Base.Disparar(ev.getX(), ev.getY());
	}
	
}
