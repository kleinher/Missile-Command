package controlador;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

import modelo.gestores.GestorDeNivel;
import modelo.gestores.Pantalla;

public class Controlador extends JPanel {

	public void Controlar() {
		Timer timer;
		timer = new Timer();
		
		TimerTask task = new TimerTask() {
			Pantalla pantalla = new Pantalla();
			@Override
			public void run()
			{
				pantalla.pintar();
			}
		};
		
	}

}
