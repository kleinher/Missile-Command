package vista;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JOptionPane;

import controlador.Controlador;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import modelo.gestores.GestorDeNivel;

import modelo.usuario.PuntajeJugador;

/**
 * Menu Principal desde donde se puede Jugar, configurar, ver las reglas del
 * juego
 * 
 * @author eze96
 *
 */
public class Menu extends Application {
	Text texto;
	ScrollPane scroll;
	Controlador juego;
	public static Stage VentanaMenu;
	Pane raiz;
	Scene escena;

	@Override
	public void start(Stage primaryStage) throws Exception {
		VentanaMenu = primaryStage;
		this.raiz = new Pane();
		raiz.setPrefSize(525, 480);
		try {
			InputStream is = Files.newInputStream(Paths.get("src/imagenes/fondo1.png"));
			Image imagen = new Image(is);
			is.close();
			ImageView vista = new ImageView(imagen);
			vista.setFitHeight(480);
			vista.setFitWidth(525);
			MenuJuego menu = new MenuJuego();
			raiz.getChildren().addAll(vista, menu);
			escena = new Scene(raiz);
			VentanaMenu.setScene(escena);
			VentanaMenu.show();
		} catch (IOException ex) {
			System.out.println("error al cargar la imagen");

		}
	}

	private class MenuJuego extends Parent {

		public MenuJuego() {

			VBox menuPpal = new VBox(10);
			menuPpal.setTranslateX(50);
			menuPpal.setTranslateY(100);
			//
			VBox menuConfig = new VBox(10);
			menuConfig.setTranslateX(50);
			menuConfig.setTranslateY(100);
			//
			VBox menuReglas = new VBox(10);
			menuReglas.setTranslateX(50);
			menuReglas.setTranslateY(100);
			//
			VBox menuComoJugar = new VBox(10);
			menuComoJugar.setTranslateX(50);
			menuComoJugar.setTranslateY(100);
			//
			VBox menuHistoria = new VBox(10);
			menuHistoria.setTranslateX(50);
			menuHistoria.setTranslateY(100);
			//
			VBox menuTiposDeEnemigos = new VBox(10);
			menuTiposDeEnemigos.setTranslateX(50);
			menuTiposDeEnemigos.setTranslateY(100);

			final int offset = 100;

			/*Aclarci�n para la correcci�n:
			 * Para mejorar la legibilidad, quise hacer un m�todo que inicialice los
			 * botones, pero esto tiraba muchos errores en ejecuci�n, que por falta de
			 * conocimiento en JavaFx no pude resolver, por lo que los botones se incializan
			 * aqu� mismo funcionando correctamente.
			 */

			// INICIALIZO BOTONES

			/**
			 * BOTON DE INICIO DE JUEGO
			 */
			BotonDeMenu btnJugar = new BotonDeMenu("JUGAR");
			btnJugar.setOnMouseClicked(event -> {
				FadeTransition ft = new FadeTransition(Duration.seconds(0.5), this);
				ft.setFromValue(1);
				ft.setToValue(0);
				ft.setOnFinished(evt -> setVisible(false));
				ft.play();
				VentanaMenu.hide();
				Controlador controlador = new Controlador();

			});

			BotonDeMenu btnReglas = new BotonDeMenu("REGLAS");
			btnReglas.setOnMouseClicked(event -> {
				getChildren().add(menuReglas);

				TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menuPpal);
				tt.setToX(menuPpal.getTranslateX() - offset);

				TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menuReglas);
				tt1.setToX(menuPpal.getTranslateX());

				tt.play();
				tt1.play();

				tt.setOnFinished(evt -> {
					getChildren().remove(menuPpal);
				});
			});
			BotonDeMenu btnOptions = new BotonDeMenu("CONFIGURACION");
			btnOptions.setOnMouseClicked(event -> {
				getChildren().add(menuConfig);

				TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menuPpal);
				tt.setToX(menuPpal.getTranslateX() - offset);

				TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menuConfig);
				tt1.setToX(menuPpal.getTranslateX());

				tt.play();
				tt1.play();

				tt.setOnFinished(evt -> {
					getChildren().remove(menuPpal);
				});
			});

			BotonDeMenu btnSalir = new BotonDeMenu("SALIR");
			btnSalir.setOnMouseClicked(event -> {
				System.exit(0);
			});

			BotonDeMenu btnNivelInicial = new BotonDeMenu("NIVEL INICIAL");
			btnNivelInicial.setOnMouseClicked(event -> {

				boolean valorcorrecto = false;
				while (!valorcorrecto) {
					try {
						Integer nivel = Integer
								.parseInt(JOptionPane.showInputDialog("Ingrese el Nivel desde el que quiere iniciar"));
						if (nivel < 17 & nivel >= 0) {
							GestorDeNivel.getGestorDeNivel().getEstructuras().setNivelActual(nivel);
							valorcorrecto = true;
						} else {
							JOptionPane.showMessageDialog(null, "Ingrese un Nivel Valido");
						}
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Ingrese un Nivel Valido");
					}
				}
			});
			BotonDeMenu btnCantJugadores = new BotonDeMenu("TABLA DE RANKING");
			btnCantJugadores.setOnMouseClicked(event -> {
				PuntajeJugador p = new PuntajeJugador();
				p.MostrarTablaEnPantalla(GestorDeNivel.getGestorDeNivel().getEstructuras().getListaDePuntajes(), 5);

			});

			BotonDeMenu btnHistoria = new BotonDeMenu("HISTORIA");
			btnHistoria.setOnMouseClicked(event -> {
				getChildren().add(menuHistoria);
				scroll = new ScrollPane();
				scroll.setPrefSize(400, 300);
				scroll.setFitToWidth(true);
				scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

				texto = new Text(dameHistoria());
				texto.setOpacity(1);
				scroll.setContent(texto);
				scroll.setOpacity(0.8);
				menuHistoria.getChildren().addAll(scroll);
				TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menuReglas);
				tt.setToX(menuReglas.getTranslateX() - offset);

				TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menuHistoria);
				tt1.setToX(menuReglas.getTranslateX());

				tt.play();
				tt1.play();

				tt.setOnFinished(evt -> {
					getChildren().remove(menuReglas);

				});
			});
			BotonDeMenu btnComoJugar = new BotonDeMenu("COMO JUGAR");
			btnComoJugar.setOnMouseClicked(event -> {
				getChildren().add(menuComoJugar);
				scroll = new ScrollPane();
				scroll.setPrefSize(400, 300);
				scroll.setFitToWidth(true);
				scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

				texto = new Text(dameComoJugar());
				texto.setOpacity(1);
				scroll.setContent(texto);
				scroll.setOpacity(0.8);
				menuComoJugar.getChildren().addAll(scroll);
				TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menuReglas);
				tt.setToX(menuReglas.getTranslateX() - offset);

				TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menuComoJugar);
				tt1.setToX(menuReglas.getTranslateX());

				tt.play();
				tt1.play();

				tt.setOnFinished(evt -> {
					getChildren().remove(menuReglas);
				});
			});
			BotonDeMenu btnTiposDeEnemigos = new BotonDeMenu("TIPOS DE ENEMIGOS");
			btnTiposDeEnemigos.setOnMouseClicked(event -> {
				getChildren().add(menuTiposDeEnemigos);

				TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menuReglas);
				tt.setToX(menuReglas.getTranslateX() - offset);

				TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menuTiposDeEnemigos);
				tt1.setToX(menuReglas.getTranslateX());

				tt.play();
				tt1.play();

				tt.setOnFinished(evt -> {
					getChildren().remove(menuReglas);
				});
			});

			/**
			 * Boton volver desde menuConfig a menuPpal
			 */
			BotonDeMenu btnVolver0 = new BotonDeMenu("VOLVER");
			btnVolver0.setOnMouseClicked(event -> {
				getChildren().add(menuPpal);

				TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menuConfig);
				tt.setToX(menuConfig.getTranslateX() + offset);

				TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menuPpal);
				tt1.setToX(menuConfig.getTranslateX());

				tt.play();
				tt1.play();

				tt.setOnFinished(evt -> {
					getChildren().remove(menuConfig);
				});
			});
			/**
			 * boton Volver desde menu Reglas a menuPpal
			 */
			BotonDeMenu btnVolver1 = new BotonDeMenu("VOLVER");
			btnVolver1.setOnMouseClicked(event -> {
				getChildren().add(menuPpal);

				TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menuReglas);
				tt.setToX(menuReglas.getTranslateX() + offset);

				TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menuPpal);
				tt1.setToX(menuReglas.getTranslateX());

				tt.play();
				tt1.play();

				tt.setOnFinished(evt -> {
					getChildren().remove(menuReglas);
				});
			});
			/**
			 * BotonVolver de Historia a Reglas
			 */
			BotonDeMenu btnVolver2 = new BotonDeMenu("VOLVER");
			btnVolver2.setOnMouseClicked(event -> {
				getChildren().add(menuReglas);
				menuHistoria.getChildren().remove(scroll);
				TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menuHistoria);
				tt.setToX(menuHistoria.getTranslateX() + offset);

				TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menuReglas);
				tt1.setToX(menuHistoria.getTranslateX());

				tt.play();
				tt1.play();

				tt.setOnFinished(evt -> {
					getChildren().remove(menuHistoria);
				});
			});
			/**
			 * BotonVolver de ComoJugar a reglas
			 */
			BotonDeMenu btnVolver3 = new BotonDeMenu("VOLVER");
			btnVolver3.setOnMouseClicked(event -> {
				getChildren().add(menuReglas);
				menuComoJugar.getChildren().remove(scroll);
				TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menuComoJugar);
				tt.setToX(menuComoJugar.getTranslateX() + offset);

				TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menuReglas);
				tt1.setToX(menuComoJugar.getTranslateX());

				tt.play();
				tt1.play();

				tt.setOnFinished(evt -> {
					getChildren().remove(menuComoJugar);
				});
			});
			/**
			 * BotonVolver de TiposDeEnemigos a reglas
			 */
			BotonDeMenu btnVolver4 = new BotonDeMenu("VOLVER");
			btnVolver4.setOnMouseClicked(event -> {
				getChildren().add(menuReglas);

				TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menuTiposDeEnemigos);
				tt.setToX(menuTiposDeEnemigos.getTranslateX() + offset);

				TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menuReglas);
				tt1.setToX(menuTiposDeEnemigos.getTranslateX());

				tt.play();
				tt1.play();

				tt.setOnFinished(evt -> {
					getChildren().remove(menuTiposDeEnemigos);
				});
			});

			Icono icono0 = new Icono("src/imagenes/MBIParaMenu.png");
			// Text nombre0= new Text("Misil Balistico Interplanetario");
			Icono icono1 = new Icono("src/imagenes/bombarderoDerecha.png");
			// Text nombre1 = new Text("Satelite");
			Icono icono2 = new Icono("src/imagenes/Avion.png");
			// Text nombre2 = new Text("Avion");
			Icono icono3 = new Icono("src/imagenes/MisilTontoParaMenu.png");
			// Text nombre3 = new Text("Misil Crucero Tonto");
			Icono icono4 = new Icono("src/imagenes/InteligenteParaMenu.png");
			// Text nombre4 = new Text("Misil Crucero Inteligente");
			Marco marco0 = new Marco("Satelite");
			Marco marco1 = new Marco("Misil Balistico Interplanetario");
			Marco marco2 = new Marco("Avion");
			Marco marco3 = new Marco("Misil Crucero Tonto");
			Marco marco4 = new Marco("Misil Crucero Inteligente");

			menuPpal.getChildren().addAll(btnJugar, btnReglas, btnOptions, btnSalir);
			menuConfig.getChildren().addAll(btnNivelInicial, btnCantJugadores, btnVolver0);
			menuReglas.getChildren().addAll(btnHistoria, btnComoJugar, btnTiposDeEnemigos, btnVolver1);
			menuHistoria.getChildren().addAll(btnVolver2);
			menuComoJugar.getChildren().addAll(btnVolver3);
			menuTiposDeEnemigos.getChildren().addAll(btnVolver4, icono0, marco0, icono1, marco1, icono2, marco2, icono3,
					marco3, icono4, marco4);
			Rectangle bg = new Rectangle(800, 600);
			bg.setFill(Color.GREY);
			bg.setOpacity(0.4);

			this.getChildren().addAll(bg, menuPpal);

		}

		private String dameHistoria() {
			String Historia = " Missile Command es un juego de arcade de 1980 de Atari. \r\n"
					+ "Es un juego muy popular que combina diversi�n con un mensaje sobre\r\n"
					+ "los peligros de la guerra.\r\n"
					+ "Originalmente  llamado �Armageddon', Missile Command fue dise�ado\r\n"
					+ "durante la �poca de la �guerra fr�a�\r\n" + "entre Estados Unidos y Rusia. \r\n" + "\r\n"
					+ " La idea de desarrollar Missile Command comenz� con una historia\r\n"
					+ "en una revista sobre sat�lites que captur� la atenci�n del\r\n"
					+ "presidente de Atari, quien pas� la nota a Lyle Rains. \r\n"
					+ "Rains le pidi� a Dave Theurer que dirija los esfuerzos en crear\r\n"
					+ "este cl�sico juego lleno de acci�n. \r\n"
					+ "La �regla principal� para los desarrolladores fue que �ste ser�a\r\n"
					+ "enteramente un juego defensivo, no un juego ofensivo.\r\n" + "\r\n"
					+ " Missile Command le da al jugador el control sobre 3 silos de\r\n"
					+ "misiles que protegen 6 ciudades en tierra. \r\n"
					+ "Cada silo tiene 10 antimisiles, y el jugador decid�a\r\n"
					+ "desde que silo disparar�a el pr�ximo antimisil.\r\n"
					+ "El objetivo de cada antimisil era indicado con un cursor que era \r\n"
					+ "posicionado mediante una peque�a bola que rotaba y luego\r\n"
					+ "con 3 botones en la consola de juego se eleg�a que silo disparaba\r\n" + "el antimisil.\r\n"
					+ "\r\n" + " Es interesante notar que el juego no menciona una �poca o lugar,\r\n"
					+ "aunque el dise�o original se refiere a 6 ciudades \r\n"
					+ "de la costa oeste de Estados Unidos: \r\n"
					+ "Eureka, San Francisco, San Luis Obispo, Santa Barbara, \r\n" + "Los Angeles, y San Diego, \r\n"
					+ "las cuales forman parte del �anillo de fuego� y que en esa �poca \r\n"
					+ "se pensaba ser�an las ciudades m�s vulnerables en caso de\r\n"
					+ "un ataque nuclear sovi�tico, y que de hecho fueron rodeadas por \r\n"
					+ "silos de misiles de defensa. \r\n"
					+ "Sin embargo, el manual del Atari 2600 indica una historia\r\n"
					+ "enteramente de ficci�n para el juego, la cual se presenta aqu�:\r\n" + "\r\n"
					+ "Aliens del planeta Krytol han comenzado un ataque sobre el \r\n"
					+ "planeta Zardon. Los Krytolianos son guerreros que buscan destruir\r\n"
					+ "y aprovecharse el planeta Zardon. Zardon es el �ltimo de los\r\n"
					+ "planetas pac�ficos. Los Zardonianos son personas h�biles y \r\n"
					+ "trabajadoras, ellos mismos construyeron sus ciudades y son ricas \r\n"
					+ "en recursos. Adem�s, Zardon es realmente un planeta libre de \r\n" + "cr�menes y violencia.\r\n"
					+ "\r\n" + "Zardon ha construido un sistema de defensa poderoso. \r\n"
					+ "Varias bases de misiles antibal�sticos han sido establecidas \r\n"
					+ "dentro de las ciudades de Zardon. Los Zardonianos est�n listos \r\n"
					+ "para un ataque, y est�n preparados para pelear -si es necesario- \r\n"
					+ "para salvar sus ciudades.\r\n" + "\r\n"
					+ "Como comandante de la base, es tu responsabilidad proteger y \r\n"
					+ "defender las 6 ciudades del planeta de Zardon. Los Krytolianos \r\n"
					+ "han empezado a disparar misiles bal�sticos interplanetarios, \r\n"
					+ "est�n apuntando a tus ciudades y hacia tus 3 bases de misiles: \r\n"
					+ "Alpha, Delta y Omega. Tu �nica defensa es devolver el fuego con \r\n"
					+ "misiles antibal�sticos. Pero, �cuidado!, los Krytolianos son \r\n"
					+ "astutos, tambi�n tienen misiles crucero. Los misiles crucero se \r\n"
					+ "ven como sat�lites, pero son tan da�inos como \r\n"
					+ "los misiles bal�sticos interplanetarios.\r\n" + "\r\n"
					+ "Deber�s usar tus misiles antibal�sticos (MABs) para detener al \r\n"
					+ "enemigo, antes que tu alegre y armonioso planeta sea destruido.\r\n" + "";

			return Historia;
		}

		private String dameComoJugar() {
			String ComoJugar = "El objetivo del juego es defender tus ciudades y bases de misiles, \r\n"
					+ "El enemigo dispara misiles bal�sticos interplanetarios (MBI) y \r\n"
					+ "misiles crucero, ambos tienen como objetivo destruir tus ciudades \r\n"
					+ "y bases militares. Hay 2 tipos de misil crucero: misiles cruceros \r\n"
					+ "tontos (que caen en l�nea recta) y misiles crucero inteligentes, \r\n"
					+ "los cuales tratar�n de evadir tus misiles antibal�sticos (MABs). \r\n" + "\r\n"
					+ "El enemigo ataca en una serie de oleadas que puede variar en el \r\n"
					+ "n�mero de misiles bal�sticos interplanetarios que atacan. \r\n"
					+ "Cada oleada de misiles se mueve m�s r�pido que la oleada anterior. \r\n"
					+ "Mientras m�s r�pida es la oleada, \r\n" + "m�s dif�cil es defender las ciudades. \r\n"
					+ "Por este motivo, mientras m�s r�pida es la oleada, \r\n"
					+ "m�s alto ser� el puntaje ganado. \r\n" + "\r\n"
					+ "Con cada oleada, ten�s 30 MABs para defenderte, \r\n"
					+ "10 en cada uno de los silos, los cuales est�n ubicados en la \r\n"
					+ "parte inferior de la pantalla, a la izquierda, al centro \r\n"
					+ "y a la derecha. Una vez que has disparado los 30 MABs,\r\n"
					+ " estar�s indefenso hasta que comience una nueva oleada.\r\n" + "\r\n"
					+ "Cada vez que un MAB llega a su destino o alcanza alg�n misil \r\n"
					+ "enemigo ocurre una explosi�n. La onda expansiva de esta explosi�n \r\n"
					+ "puede ocasionar que otros misiles bal�sticos interplanetarios o \r\n"
					+ "crucero que se encuentran en la zona de impacto tambi�n sean \r\n"
					+ "destruidos. Si la explosi�n del MAB no alcanza la cabeza del misil, \r\n"
					+ "�ste seguir� con su trayectoria, s�lo se ver� afectada la estela que \r\n"
					+ "deja a su paso..\r\n" + "\r\n"
					+ "Debes tener en cuenta que hay un umbral (l�nea horizontal imaginaria) \r\n"
					+ "en el campo de juego, debajo de la cual no podr�s disparar misiles. \r\n"
					+ "De esta manera se protege a los silos \r\n" + "y las ciudades de la autodestrucci�n.\r\n"
					+ "\r\n" + "Si logras sobrevivir a la oleada, pasar�s al siguiente nivel, \r\n"
					+ "si no logras sobrevivir a la oleada, \r\n"
					+ "tendr�s s�lo otra oportunidad para volver a defenderte.\r\n" + "\r\n"
					+ "El juego finaliza cuando todas las ciudades son destruidas.\r\n" + "";
			return ComoJugar;
		}

	}

	public static void mostrarEscena() {
		VentanaMenu.show();
	}

	// ( StackPane funciona como una Stack, una cosa se apila sobre la otra),

	/**
	 * Cada instancia representa un texto embebido en un marco que representa un
	 * bot�n del men�.
	 * 
	 * @author eze96
	 *
	 */
	private class BotonDeMenu extends StackPane {
		private Text texto;

		// constructor
		public BotonDeMenu(String nombre) {
			texto = new Text(nombre);
			texto.getFont();
			texto.setFont(Font.font(20));
			texto.setFill(Color.WHITE);

			// rectangulo que encuadre a la opcion
			Rectangle marco = new Rectangle(250, 30);
			marco.setOpacity(0.6);// Transparencia
			marco.setFill(javafx.scene.paint.Color.BLACK);
			marco.setEffect(new GaussianBlur(3.5));// efecto blur

			this.setAlignment(Pos.CENTER_LEFT);
			setRotate(-0.5);
			getChildren().addAll(marco, texto);
			// efecto para que cuando pase el mouse por el boton se desplaze
			setOnMouseEntered(event -> {
				marco.setTranslateX(10);
				texto.setTranslateX(10);
				marco.setFill(Color.WHITE);
				texto.setFill(Color.BLACK);
			});
			// efecto para que cuando saque el mouse del boton vuelva a estar como antes
			setOnMouseExited(event -> {
				marco.setTranslateX(0);
				texto.setTranslateX(0);
				marco.setFill(Color.BLACK);
				texto.setFill(Color.WHITE);
			});
			DropShadow drop = new DropShadow(50, Color.WHITE);
			drop.setInput(new Glow());
			setOnMousePressed(event -> setEffect(drop));
			setOnMouseReleased(event -> setEffect(null));
		}

	}

	private class Marco extends StackPane {

		public Marco(String nombre) {
			texto = new Text(nombre);
			texto.getFont();
			texto.setFont(Font.font(15));
			texto.setFill(Color.WHITE);

			// rectangulo que encuadre a El Enemigo con su nombre
			Rectangle marco = new Rectangle(400, 20);
			marco.setOpacity(0.3);// Transparencia
			marco.setFill(javafx.scene.paint.Color.BLACK);

			this.getChildren().addAll(marco, texto);

			// marco.setEffect(new GaussianBlur(3.5));// efecto blur

		}

	}

	/**
	 * Cada instancia contiene un �cono que sirve para la descripcion de enemigos en
	 * el menu
	 * 
	 * @author eze96
	 *
	 */
	private class Icono extends StackPane {
		public Icono(String path) {
			try {
				InputStream is = Files.newInputStream(Paths.get(path));
				Image imagen = new Image(is);
				is.close();
				ImageView vista = new ImageView(imagen);
				vista.setFitHeight(30);
				vista.setFitWidth(30);
				setRotate(-0.5);
				this.getChildren().addAll(vista);
			} catch (IOException ex) {
				System.out.println("error al cargar el icono");
			}

		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
