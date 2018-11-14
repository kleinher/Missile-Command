package modelo.gestores;

import java.awt.Graphics;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JPanel;

import modelo.Aliados.Base;
import modelo.Aliados.Ciudad;
import modelo.Aliados.MisilAntibalistico;
import modelo.enemigos.Avion;
import modelo.enemigos.Enemigo;
import modelo.enemigos.MisilBalistico;
import modelo.enemigos.MisilCrucero;
import modelo.enemigos.MisilCruceroInteligente;
import modelo.enemigos.Satelite;

/*Parte Grafica*/
public class Pantalla extends JPanel{
	/**
	 * Se Encarga de Dibujar todos los elementos del juego en la pantalla
	 * 
	 */
	@Override
	public void paint(Graphics g) {
		super.paintComponent(g);
		GestorDeEstructuras estructuras = GestorDeNivel.getGestorDeNivel().getEstructuras();
		DibujarEnemigos(estructuras.EnemigosEnPantalla,g);
		DibujarBases(estructuras.Bases,g);
		DibujarCiudades(estructuras.Ciudades,g);
		DibujarMisilesAliados(estructuras.MisilesAliadosEnPantalla,g);
		}
	
	/**
	 * Dibuja los Misiles Aliados de color AZUL
	 * 
	 * @param misilesAliadosEnPantalla
	 * @param g
	 */
	private void DibujarMisilesAliados(LinkedList<MisilAntibalistico> misilesAliadosEnPantalla, Graphics g) {
		
		for (Iterator<MisilAntibalistico> i = misilesAliadosEnPantalla.iterator(); i.hasNext();) {
			Enemigo MisilAliado = i.next();
			g.setColor(java.awt.Color.BLUE);
			g.fillOval(MisilAliado.getPosicionActual().getPosicionX(),MisilAliado.getPosicionActual().getPosicionY(),4,4);
		}
				
		
		
	}

	/**
	 * Dibuja las ciudades de color BLANCO
	 * 
	 * 
	 * @param ciudad
	 * @param g
	 */
	private void DibujarCiudades(Ciudad[] ciudad, Graphics g) {
		
		for(int i=1;i<ciudad.length;i++) {
			if(ciudad[i].estaViva()) {
				g.setColor(java.awt.Color.WHITE);
				g.fillRect(ciudad[i].getPosicion().getPosicionX()-15, ciudad[i].getPosicion().getPosicionY()-10, 12, 5);
			}
		}
		
	}

	/**
	 * Dibuja Las Bases y los misiles que dispone
	 * 
	 * Las bases de color VERDE y los misiles que dispone AZULES
	 * 
	 * 
	 * @param bases
	 * @param g
	 */
	private void DibujarBases(Base[] bases, Graphics g) {
			
			for(int i=1;i<bases.length;i++) {
				g.setColor(java.awt.Color.GREEN);
				g.fillRect(bases[i].getPosicion().getPosicionX()-15,bases[1].getPosicion().getPosicionY()-10, 20, 5);
				int espacio=-35;
				for(int j=0;j<bases[i].getCantMisiles();j++) {
					if(bases[i].isEstaViva()){
					g.setColor(java.awt.Color.BLUE);
					g.fillRect(bases[i].getPosicion().getPosicionX()+espacio, bases[i].getPosicion().getPosicionY()+5, 2, 7);
					espacio+=4;
				}
			}
			}
		}
	/**
	 * Dibuja los enemigos en pantalla
	 * 
	 * Rojo:Misiles Balisticos
	 * Rosa:Aviones
	 * Magenta:Satelites
	 * Cyan:Misiles Cruceros
	 * Naranja:Misiles Cruceros Inteligentes
	 * 
	 * @param EnemigosEnPantalla
	 * @param g
	 */
	private void DibujarEnemigos(LinkedList<Enemigo> EnemigosEnPantalla, Graphics g){
	
		for (Iterator<Enemigo> i = EnemigosEnPantalla.iterator(); i.hasNext();) {
			Enemigo enemigo = i.next();
			if(enemigo instanceof MisilBalistico) {
				g.setColor(java.awt.Color.RED);
				g.fillOval(enemigo.getPosicionActual().getPosicionX(),enemigo.getPosicionActual().getPosicionY(),4,4);
			}
			if(enemigo instanceof Avion) {
				g.setColor(java.awt.Color.PINK);
				g.fillOval(enemigo.getPosicionActual().getPosicionX(),enemigo.getPosicionActual().getPosicionY(),6,4);
			}
			if(enemigo instanceof Satelite) {
				g.setColor(java.awt.Color.MAGENTA);
				g.fillOval(enemigo.getPosicionActual().getPosicionX(),enemigo.getPosicionActual().getPosicionY(),8,6);
			}
			if(enemigo instanceof MisilCrucero) {
				g.setColor(java.awt.Color.CYAN);
				g.fillRect(enemigo.getPosicionActual().getPosicionX(),enemigo.getPosicionActual().getPosicionY(),5,5);
			}
			if(enemigo instanceof MisilCruceroInteligente) {
				g.setColor(java.awt.Color.ORANGE);
				g.fillRect(enemigo.getPosicionActual().getPosicionX(),enemigo.getPosicionActual().getPosicionY(),5,5);
			}
	}
}
}


