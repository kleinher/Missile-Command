package vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

public class Reglas extends JFrame {
	private static final long serialVersionUID = 1L;
	Dimension tamanioPantalla = Toolkit.getDefaultToolkit().getScreenSize();
	int alturaPantalla = tamanioPantalla.height;
	int anchoPantalla = tamanioPantalla.width;
	int anchoFrame = 525;
	int altoFrame = 480;
	
	JPanel panelPrincipal;
	JLabel titulo = new JLabel("REGAS DEL JUEGO", SwingConstants.CENTER);
	JSplitPane split;
	//private Imagen img = new Imagen();
	private JTree arbol;
	JTextArea areaDeTexto = new JTextArea();
	JScrollPane scroll;

	// CONSTRUCTOR
	public Reglas() {
		// Posicion y tamanio del frame
		this.setBounds(((alturaPantalla / 2) - (anchoFrame / 2)), ((anchoPantalla / 2) - (altoFrame / 2)), anchoFrame,
				altoFrame);
		// lo hago visible
		this.setVisible(true);
		// Que no se pueda dimensionar
		this.setResizable(false);
		this.panelPrincipal=new JPanel();
		this.panelPrincipal.setLayout(new GridBagLayout());
		inicializarLayout();

		this.add(panelPrincipal);
	}

	private void inicializarLayout() {
		GridBagConstraints info = new GridBagConstraints();
		info.weightx = 1.0;
		info.weighty = 1.0;

		// Inicializo el titulo
		info.gridx = 0; // El área del titulo empieza en la columna cero.
		info.gridy = 0; // El área del titulo empieza en la fila cero
		info.gridwidth = 2; // El área del titulo ocupa 2 columnas.
		info.gridheight = 1; // El área del titulo ocupa 1 fila.
		this.titulo.setFont(new Font("Monospaced", Font.BOLD, 45));
		info.fill = GridBagConstraints.BOTH;
		
		this.panelPrincipal.add(this.titulo, info);
		
		// Inicializo la imagen
		/*
		info.gridx = 0;
		info.gridy = 1;
		this.panelPrincipal.add(this.img, info);
		this.panelPrincipal.repaint();
		*/
		// Inicializo el JTree
		inicializarJTree();
		// Inicializar el JTextArea
		inicializarAreaDeTexto();

		// Inicializo el JSplit
		info.gridx = 0; // El área del split empieza en la columna cero.
		info.gridy = 2; // El área del split empieza en la fila cero
		inicializarJSplit();
		this.panelPrincipal.add(split);

	}

	private void inicializarAreaDeTexto() {
		this.areaDeTexto = new JTextArea("   ", 20, 10);
		this.areaDeTexto.setPreferredSize(new Dimension(10, 100));
		this.scroll = new JScrollPane(this.areaDeTexto);
		
	}

	private void inicializarJSplit() {
		this.split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, this.arbol, this.arbol);

	}

	private void inicializarJTree() {
		DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("El Juego");
		DefaultMutableTreeNode historia = new DefaultMutableTreeNode("Historia del Juego");
		DefaultMutableTreeNode enemigos = new DefaultMutableTreeNode("Los Enemigos");
		raiz.add(historia);
		raiz.add(enemigos);
		this.arbol = new JTree(raiz);
		// add(arbol);
	}
}
