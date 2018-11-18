package controlador;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import modelo.Aliados.Base;

public class AdapterDeMiraTeclado extends KeyAdapter {
	 @Override
	 public void keyPressed(KeyEvent e){
		 if(e.getKeyCode() == e.VK_1) {
			 Point p = Controlador.Juego.pantalla.getMousePosition();
			 Base.Disparar(p.x, p.y, 1);
		 }
		 if(e.getKeyCode() == e.VK_2) {
			 Point p = Controlador.Juego.pantalla.getMousePosition();
			 Base.Disparar(p.x, p.y, 2);
		 }
		 if(e.getKeyCode() == e.VK_3) {
			 Point p = Controlador.Juego.pantalla.getMousePosition();
			 Base.Disparar(p.x, p.y, 3);
		 }
	 }
}
