package controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import modelo.Aliados.Base;


public class AdapterDeMiraMouse extends MouseAdapter{
	@Override
	public void mousePressed(MouseEvent ev) {
		Base.Disparar(ev.getX(), ev.getY());
	}
	
}
