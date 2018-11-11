package modelo.gestores;

import java.awt.Graphics;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JPanel;

import modelo.Aliados.Base;
import modelo.enemigos.Enemigo;
import modelo.enemigos.MisilBalistico;

/*Parte Grafica*/
public class Pantalla extends JPanel{
	
	@Override
	public void paint(Graphics g) {
		super.paintComponent(g);
		GestorDeEstructuras estructuras = GestorDeNivel.getGestorDeNivel().getEstructuras();
		DibujarEnemigos(estructuras.EnemigosEnPantalla,g);
		DibujarBases(estructuras.Bases,g);
	
			
		}
	private void DibujarBases(Base[] bases, Graphics g) {
			g.setColor(java.awt.Color.GREEN);
			g.fillRect(bases[1].getPosicion().getPosicionX()-10,bases[1].getPosicion().getPosicionY(), 20, 5);
			g.fillRect(bases[2].getPosicion().getPosicionX()-10,bases[2].getPosicion().getPosicionY(), 20, 5);
			g.fillRect(bases[3].getPosicion().getPosicionX()-10,bases[3].getPosicion().getPosicionY(), 20, 5);
			g.setColor(java.awt.Color.BLUE);
			
		}
	private void DibujarEnemigos(LinkedList<Enemigo> EnemigosEnPantalla, Graphics g){
	
		for (Iterator<Enemigo> i = EnemigosEnPantalla.iterator(); i.hasNext();) {
			Enemigo enemigo = i.next();
			if(enemigo instanceof MisilBalistico) {
				g.setColor(java.awt.Color.RED);
				g.fillOval(enemigo.getPosicionActual().getPosicionX(),enemigo.getPosicionActual().getPosicionY(),4,4);
				
			}
	}
}
}


