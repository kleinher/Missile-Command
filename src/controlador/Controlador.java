package controlador;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

import modelo.gestores.GestorDeNivel;
import modelo.gestores.Pantalla;
import taller2.modelo.Graficador;

public class Controlador extends JPanel {
	public static void main(String args[]) {
		Controlador controlador = new Controlador();
		controlador.Controlar();
	}

	public void Controlar() {
		Timer timer;
		timer = new Timer();

		// Timer task que se va a ejecturas cada determinados tics
		TimerTask task = new TimerTask() {
			Pantalla pantalla = new Pantalla();
			GestorDeNivel juego = GestorDeNivel.getGestorDeNivel();

			// En el run esta el loop en donde se actualiza y pinta en cada tic.
			@Override
			public void run() {
				if ((!juego.Perdio()) && (juego.getNivelActual() != 17)) {
					// Aca se actualiza todo
					this.modelar();

					// ACA SE PINTA EL GRAFICO
					Graficador.refrescarTopDown(juego.getEstructuras().ActualizarListaDibujables(), 200);
					pantalla.pintar();
					
				}
				else{
					juego.terminarJuego();
					timer.cancel();
				}
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
		timer.schedule(task, 10,200);
	}
	
}
