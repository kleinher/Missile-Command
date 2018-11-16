package Ventanas;



import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

import controlador.AdapterDeMiraMouse;
import controlador.AdapterDeMiraTeclado;
import modelo.gestores.GestorDeNivel;
import modelo.gestores.Pantalla;

public class Juego extends JFrame {
	//Variable de el JPanel "pantalla"
	public Pantalla pantalla;
	
	public void Controlar() {
		Timer timer;
		timer = new Timer();
		//Variable de el JPanel "pantalla"
		 pantalla = new Pantalla();
		add(pantalla);
		pantalla.addMouseListener(new AdapterDeMiraMouse());
		pantalla.addKeyListener(new AdapterDeMiraTeclado());
		//configuracion para que funcionen las teclas 1,2,3
		pantalla.setFocusable(true);
		pantalla.requestFocusInWindow();
		//Configuracion del Frame principal
		setTitle("Missile Comand");
		setSize(525, 525);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		// Timer task que se va a ejecturas cada determinados tics
		TimerTask task = new TimerTask() {
			GestorDeNivel juego = GestorDeNivel.getGestorDeNivel();
			
			// En el run esta el loop en donde se actualiza y pinta en cada tic.
			@Override
			public void run() {

				if ((!juego.Perdio()) && (juego.getNivelActual() != 17)) {
					// Aca se actualiza todo
					this.modelar();

					// ACA SE PINTA EL GRAFICO
					//Graficador.refrescarTopDown(juego.getEstructuras().ActualizarListaDibujables(), 200);
					this.pintar();
					
				}
				else{
					juego.terminarJuego();
					timer.cancel();
				}
			}
			
			private void pintar() {
				//pantalla.setOpaque(true);
				//pantalla.setBackground(java.awt.Color.BLACK);
				//pantalla.paint(pantalla.getGraphics());
				repaint();
			}

			public void modelar() {
					// ACA SE ACTUALIZA EL MODELO
					try {
						juego.modelar();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			}
		
		};
		timer.schedule(task,2,16);
	}
	
}
