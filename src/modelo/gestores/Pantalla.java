package modelo.gestores;

import java.awt.Graphics;
import java.util.Iterator;

import javax.swing.JPanel;

import modelo.enemigos.Enemigo;
import modelo.enemigos.MisilBalistico;

/*Parte Grafica*/
public class Pantalla extends JPanel{
	@Override
	public void paint(Graphics g) {
		GestorDeEstructuras estructuras = GestorDeNivel.getGestorDeNivel().getEstructuras();
		for (Iterator<Enemigo> i = estructuras.EnemigosEnPantalla.iterator(); i.hasNext();) {
			Enemigo enemigo = i.next();
			if(enemigo instanceof MisilBalistico) {
				g.fillOval(enemigo.getPosicionActual().getPosicionX(),enemigo.getPosicionActual().getPosicionY() ,10, 10);
			}
			
		}
	}

}
