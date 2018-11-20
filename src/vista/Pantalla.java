package vista;

import java.awt.Color;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import modelo.Aliados.Base;
import modelo.Aliados.Ciudad;
import modelo.Aliados.Explosion;
import modelo.Aliados.MisilAntibalistico;
import modelo.enemigos.Avion;
import modelo.enemigos.Enemigo;
import modelo.enemigos.MisilBalistico;
import modelo.enemigos.MisilCrucero;
import modelo.enemigos.MisilCruceroInteligente;
import modelo.enemigos.Satelite;
import modelo.general.Posicion;
import modelo.gestores.GestorDeEstructuras;
import modelo.gestores.GestorDeNivel;

/*Parte Grafica*/
public class Pantalla extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7900737902395308470L;

	/**
	 * Se Encarga de Dibujar todos los elementos del juego en la pantalla
	 * 
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		DibujarImagenFondo(g);
		setOpaque(true);
		setBackground(java.awt.Color.BLACK);
		setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		GestorDeEstructuras estructuras = GestorDeNivel.getGestorDeNivel().getEstructuras();
		DibujarEnemigos(GestorDeNivel.getGestorDeNivel().getEstructuras().getEnemigosEnPantalla(), g);
		DibujarBases(GestorDeNivel.getGestorDeNivel().getEstructuras().getBases(), g);
		DibujarCiudades(GestorDeNivel.getGestorDeNivel().getEstructuras().getCiudades(), g);
		DibujarMisilesAliados(GestorDeNivel.getGestorDeNivel().getEstructuras().getMisilesAliadosEnPantalla(), g);
		DibujarExplociones(GestorDeNivel.getGestorDeNivel().getEstructuras().getExplosionesEnPantalla(), g);
		DibujarEstela(GestorDeNivel.getGestorDeNivel().getEstructuras().getEstelasEnPantalla(), g);
		DibujarImprimirNivel(estructuras.getNivelActual(), g);
	}

	private void DibujarImagenFondo(Graphics g) {
		Image imagen = null;
		imagen = ImportarImagen(g, "imagenes/fondo.jpg");
		g.drawImage(imagen, 0, 0, null);
	}

	private Image ImportarImagen(Graphics g, String imgFileName) {
		Image img = null;
		URL imgUrl = getClass().getClassLoader().getResource(imgFileName);
		if (imgUrl == null) {
			System.err.println("No se encuntra el archivo: " + imgFileName);
		} else {
			try {
				img = ImageIO.read(imgUrl);
			} catch (IOException ex) {
				ex.printStackTrace();
			}

		}
		return img;
	}

	@SuppressWarnings("unused")
	private void DibujarScore(Integer score, Graphics g) {
		g.drawString(score.toString(), 50, 50);

	}

	private void DibujarImprimirNivel(int nivelActual, Graphics g) {
		g.setColor(java.awt.Color.WHITE);
		g.drawString("Nivel Actual:" + String.valueOf(nivelActual - 1), 5, 20);
	}

	private void DibujarEstela(LinkedList<LinkedList<Posicion>> estelasEnPantalla, Graphics g) {
		try {
			for (Iterator<LinkedList<Posicion>> i = estelasEnPantalla.iterator(); i.hasNext();) {
				LinkedList<Posicion> estelaDeMisil = i.next();
				for (Iterator<Posicion> j = estelaDeMisil.iterator(); j.hasNext();) {
					Posicion PosEstelaAct = j.next();
					g.setColor(java.awt.Color.RED);
					g.fillRect((int) PosEstelaAct.getPosicionX() + 3, (int) PosEstelaAct.getPosicionY(), 1, 1);
				}
			}
		} catch (ConcurrentModificationException e) {
			// no quiero que haga nada
		}
	}

	/**
	 * Dibuja los Misiles Aliados de color CYAN
	 * 
	 * @param misilesAliadosEnPantalla
	 * @param g
	 */
	private void DibujarMisilesAliados(LinkedList<MisilAntibalistico> misilesAliadosEnPantalla, Graphics g) {

		for (Iterator<MisilAntibalistico> i = misilesAliadosEnPantalla.iterator(); i.hasNext();) {
			Enemigo MisilAliado = i.next();
			g.setColor(java.awt.Color.RED);
			g.fillRect((int) MisilAliado.getPosicionActual().getPosicionX(),
					(int) MisilAliado.getPosicionActual().getPosicionY(), 3, 8);
		}
	}

	private void DibujarExplociones(LinkedList<Explosion> explosionesEnPantalla, Graphics g) {
		try {
		if (explosionesEnPantalla != null) {
			Color[] colores = new Color[3];
			colores[0] = java.awt.Color.CYAN;
			colores[1] = java.awt.Color.white;
			colores[2] = java.awt.Color.LIGHT_GRAY;
			Random random = new Random();
			int randomNumber;
			for (Iterator<Explosion> i = explosionesEnPantalla.iterator(); i.hasNext();) {
				randomNumber = random.nextInt(3);
				Explosion explosion = i.next();
				g.setColor(colores[randomNumber]);
				g.fillOval((int) explosion.getPosicionActual().getPosicionX() - ((int) explosion.getRadio() / 2),
						(int) explosion.getPosicionActual().getPosicionY() - ((int) explosion.getRadio() / 2),
						(int) explosion.getRadio(), (int) explosion.getRadio());
			}
		}
		}catch(ConcurrentModificationException e) {
			//No hacer nada
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
		Image img = ImportarImagen(g, "imagenes/bases.jpg");
		for (int i = 1; i < ciudad.length; i++) {
			if (ciudad[i].estaViva()) {
				g.drawImage(img, (int) ciudad[i].getPosicion().getPosicionX(),
						(int) ciudad[i].getPosicion().getPosicionY(), null);
				// g.setColor(java.awt.Color.WHITE);
				// g.fillRect((int) ciudad[i].getPosicion().getPosicionX() - 15,
				// (int) ciudad[i].getPosicion().getPosicionY() - 10, 12, 5);
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
		for (int i = 1; i < bases.length; i++) {
			if (bases[i].isEstaViva()) {
				String string = String.format("imagenes/misilesEnBase/%d.png", bases[i].getCantMisiles());
				Image img = ImportarImagen(g, string);
				g.drawImage(img, (int) bases[i].getPosicion().getPosicionX() - 28,
						(int) bases[i].getPosicion().getPosicionY() - 10, null);
			}

		}
	}

	/**
	 * Dibuja los enemigos en pantalla
	 * 
	 * Rojo:Misiles Balisticos(Puntos) Rosa:Aviones(Ovalos) Magenta:Satelites(Ovalos
	 * grandes) Cyan:Misiles Cruceros(Cuadrados) Naranja:Misiles Cruceros
	 * Inteligentes(cuadrados)
	 * 
	 * @param EnemigosEnPantalla
	 * @param g
	 */
	private synchronized void DibujarEnemigos(LinkedList<Enemigo> EnemigosEnPantalla, Graphics g) {
		try {
		for (Iterator<Enemigo> i = EnemigosEnPantalla.iterator(); i.hasNext();) {
			Enemigo enemigo = i.next();
			if (enemigo instanceof MisilBalistico) {
				Color[] colores = new Color[3];
				colores[0] = java.awt.Color.RED;
				colores[1] = java.awt.Color.white;
				colores[2] = java.awt.Color.GREEN;
				Random random = new Random();
				Color colorRandom = colores[random.nextInt(3)];
				g.setColor(colorRandom);
				g.fillOval((int) enemigo.getPosicionActual().getPosicionX(),
						(int) enemigo.getPosicionActual().getPosicionY(), 6, 6);
			}
			if (enemigo instanceof Avion) {
				if (enemigo.getPosicionInicial().getPosicionX() == 0) {
					Image img = ImportarImagen(g, "imagenes/AvionDerecha.png");
					g.drawImage(img, (int) enemigo.getPosicionActual().getPosicionX(),
							(int) enemigo.getPosicionActual().getPosicionY(), null);
				} else {
					Image img = ImportarImagen(g, "imagenes/Avion.png");
					g.drawImage(img, (int) enemigo.getPosicionActual().getPosicionX(),
							(int) enemigo.getPosicionActual().getPosicionY(), null);
				}
			}
			if (enemigo instanceof Satelite) {
				if (enemigo.getPosicionInicial().getPosicionX() == 0) {
					Image img = ImportarImagen(g, "imagenes/bombarderoDerecha.png");
					g.drawImage(img, (int) enemigo.getPosicionActual().getPosicionX() - 7,
							(int) enemigo.getPosicionActual().getPosicionY() - 7, null);
				} else {
					Image img = ImportarImagen(g, "imagenes/bombarderoIzquierda.png");
					g.drawImage(img, (int) enemigo.getPosicionActual().getPosicionX() - 7,
							(int) enemigo.getPosicionActual().getPosicionY() - 7, null);
				}
			}
			if (enemigo instanceof MisilCrucero) {
				g.setColor(java.awt.Color.CYAN);
				g.fillRect((int) enemigo.getPosicionActual().getPosicionX(),
						(int) enemigo.getPosicionActual().getPosicionY(), 5, 5);
			}
			if (enemigo instanceof MisilCruceroInteligente) {
				g.setColor(java.awt.Color.ORANGE);
				g.fillRect((int) enemigo.getPosicionActual().getPosicionX(),
						(int) enemigo.getPosicionActual().getPosicionY(), 5, 5);
			}
		}
		}catch( ConcurrentModificationException e) {
			// no quiero que haga nada
		}
		
	}
}
