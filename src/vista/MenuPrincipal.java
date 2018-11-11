package vista;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JFrame {
	Dimension tamanioPantalla = Toolkit.getDefaultToolkit().getScreenSize();
	int alturaPantalla = tamanioPantalla.height;
	int anchoPantalla = tamanioPantalla.width;
	int anchoFrame = 525;
	int altoFrame = 480;
	private static final long serialVersionUID = 1L;

	JPanel panelPrincipal;
	JLabel titulo = new JLabel("MISSLE COMMAND", SwingConstants.CENTER);
	JButton botonJugar = new JButton("jugar");
	JButton botonPuntajes = new JButton("Puntajes");;
	JButton botonReglas = new JButton("Reglas");
	JButton botonSalir = new JButton("Salir");
	JButton botonConfig = new JButton("Config");
//CONSTRUCTOR
	public MenuPrincipal() {
		// Posicion y tamanio del frame
		this.setBounds(((alturaPantalla / 2) - (anchoFrame / 2)), ((anchoPantalla / 2) - (altoFrame / 2)), anchoFrame,
				altoFrame);
		// lo hago visible
		this.setVisible(true);
		// Que no se pueda dimensionar
		this.setResizable(false);
		/*
		 * "setDefaultCloseOperation" define el comportamiento cuando haces click en la
		 * X de salida, dependiendo del parámetro se cierra,se hace invisible,etc
		 */
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// PanelPrincipal
		this.panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new GridBagLayout());
		inicializarLayout();
		this.add(panelPrincipal);
	}

	private void inicializarLayout() {
		GridBagConstraints info = new GridBagConstraints();
		info.weightx = 1.0;
		info.weighty = 1.0;

		// Inicializo el titulo
		info.gridx = 0; // El área del titulo empieza en la columna cero.
		info.gridy = 1; // El área del titulo empieza en la fila uno
		info.gridwidth = 3; // El área del titulo ocupa 3 columnas.
		info.gridheight = 1; // El área del titulo ocupa 1 fila.
		this.titulo.setFont(new Font("Monospaced", Font.BOLD, 45));
		info.fill = GridBagConstraints.BOTH;
		this.panelPrincipal.add(this.titulo, info);
		// Inicializo el boton Reglas
		info.gridx = 0;
		info.gridy = 2;
		info.gridwidth = 1;
		info.gridheight = 1;
		this.botonReglas.setFont(new Font("Monospaced", Font.BOLD, 30));
		this.panelPrincipal.add(this.botonReglas, info);
		// Inicializo el boton Jugar
		info.gridx = 1;
		info.gridy = 2;
		info.gridwidth = 1;
		info.gridheight = 1;
		this.botonJugar.setFont(new Font("Monospaced", Font.BOLD, 30));
		this.panelPrincipal.add(this.botonJugar, info);
		// Inicializo el boton Puntajes
		info.gridx = 2;
		info.gridy = 2;
		info.gridwidth = 1;
		info.gridheight = 1;
		this.botonPuntajes.setFont(new Font("Monospaced", Font.BOLD, 30));
		this.panelPrincipal.add(this.botonPuntajes, info);
		// Inicializo el boton Salir
		info.gridx = 0;
		info.gridy = 3;
		info.gridwidth = 3;
		info.gridheight = 1;
		this.botonSalir.setFont(new Font("Monospaced", Font.BOLD, 30));
		this.panelPrincipal.add(this.botonSalir, info);
		// Inicializo el boton Config
		info.weightx = 0;
		info.weighty = 0;
		info.gridx = 2;
		info.gridy = 0;
		info.gridwidth = 1;
		info.gridheight = 1;
		this.botonConfig.setFont(new Font("Monospaced", Font.BOLD, 15));
		this.panelPrincipal.add(this.botonConfig, info);

	}
}
