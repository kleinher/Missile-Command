package vista;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import controlador.Controlador;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
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

public class Menu extends Application {
	Text texto;
	ScrollPane scroll;
	Controlador juego;
	Stage VentanaMenu;
	Pane raiz;
	Scene escena;

	@Override
	public void start(Stage primaryStage) throws Exception {
		VentanaMenu = primaryStage;
		this.raiz = new Pane();
		raiz.setPrefSize(525, 480);
		try {
			InputStream is = Files.newInputStream(Paths.get("res/imagenes/fondo.png"));
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

			/*
			 * ACLARACION: QUISE HACER UN METODO QUE INICIALICE TODOS LOS BOTONES PARA
			 * MEJORAR LA LEGIBILIDAD, PERO ME TIRABA ERRORES EN EJECUCION EN EL VBOX
			 * 'menuPpal', QUE DEBUGGEANDO NO PUDE RESOLVER
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
				VentanaMenu.close();
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

			BotonDeMenu btnCantJugadores = new BotonDeMenu("TABLA DE RANKING");

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

			// Icono icono0 = new Icono("res/imagenes/MBIParaMenu.png");
			// Text nombre0= new Text("Misil Balistico Interplanetario");
			// Icono icono1 = new Icono("src/imagenes/bombarderoDerecha.png");
			// Text nombre1 = new Text("Satelite");
			// Icono icono2 = new Icono("src/imagenes/Avion.png");
			// Text nombre2 = new Text("Avion");
			// Icono icono3 = new Icono("res/imagenes/MisilTontoParaMenu.png");
			// Text nombre3 = new Text("Misil Crucero Tonto");
			// Icono icono4 = new Icono("res/imagenes/InteligenteParaMenu.png");
			// Text nombre4 = new Text("Misil Crucero Inteligente");
			Marco marco0 = new Marco("res/imagenes/MBIParaMenu.png", "Satelite");
			Marco marco1 = new Marco("src/imagenes/bombarderoDerecha.png", "Misil Balistico Interplanetario");
			Marco marco2 = new Marco("src/imagenes/Avion.png", "Avion");
			Marco marco3 = new Marco("res/imagenes/MisilTontoParaMenu.png", "Misil Crucero Tonto");
			Marco marco4 = new Marco("res/imagenes/InteligenteParaMenu.png", "Misil Crucero Inteligente");

			menuPpal.getChildren().addAll(btnJugar, btnReglas, btnOptions, btnSalir);
			menuConfig.getChildren().addAll(btnNivelInicial, btnCantJugadores, btnVolver0);
			menuReglas.getChildren().addAll(btnHistoria, btnComoJugar, btnTiposDeEnemigos, btnVolver1);
			menuHistoria.getChildren().addAll(btnVolver2);
			menuComoJugar.getChildren().addAll(btnVolver3);
			menuTiposDeEnemigos.getChildren().addAll(btnVolver4, marco0, marco1, marco2, marco3, marco4);
			Rectangle bg = new Rectangle(800, 600);
			bg.setFill(Color.GREY);
			bg.setOpacity(0.4);

			this.getChildren().addAll(bg, menuPpal);

		}

		private String dameHistoria() {
			String Historia = " Missile Command es un juego de arcade de 1980 de Atari. \r\n"
					+ "Es un juego muy popular que combina diversión con un mensaje sobre\r\n"
					+ "los peligros de la guerra.\r\n"
					+ "Originalmente  llamado ‘Armageddon', Missile Command fue diseñado\r\n"
					+ "durante la época de la “guerra fría”\r\n" + "entre Estados Unidos y Rusia. \r\n" + "\r\n"
					+ " La idea de desarrollar Missile Command comenzó con una historia\r\n"
					+ "en una revista sobre satélites que capturó la atención del\r\n"
					+ "presidente de Atari, quien pasó la nota a Lyle Rains. \r\n"
					+ "Rains le pidió a Dave Theurer que dirija los esfuerzos en crear\r\n"
					+ "este clásico juego lleno de acción. \r\n"
					+ "La “regla principal” para los desarrolladores fue que éste sería\r\n"
					+ "enteramente un juego defensivo, no un juego ofensivo.\r\n" + "\r\n"
					+ " Missile Command le da al jugador el control sobre 3 silos de\r\n"
					+ "misiles que protegen 6 ciudades en tierra. \r\n"
					+ "Cada silo tiene 10 antimisiles, y el jugador decidía\r\n"
					+ "desde que silo dispararía el próximo antimisil.\r\n"
					+ "El objetivo de cada antimisil era indicado con un cursor que era \r\n"
					+ "posicionado mediante una pequeña bola que rotaba y luego\r\n"
					+ "con 3 botones en la consola de juego se elegìa que silo disparaba\r\n" + "el antimisil.\r\n"
					+ "\r\n" + " Es interesante notar que el juego no menciona una época o lugar,\r\n"
					+ "aunque el diseño original se refiere a 6 ciudades \r\n"
					+ "de la costa oeste de Estados Unidos: \r\n"
					+ "Eureka, San Francisco, San Luis Obispo, Santa Barbara, \r\n" + "Los Angeles, y San Diego, \r\n"
					+ "las cuales forman parte del “anillo de fuego” y que en esa época \r\n"
					+ "se pensaba serían las ciudades más vulnerables en caso de\r\n"
					+ "un ataque nuclear soviético, y que de hecho fueron rodeadas por \r\n"
					+ "silos de misiles de defensa. \r\n"
					+ "Sin embargo, el manual del Atari 2600 indica una historia\r\n"
					+ "enteramente de ficción para el juego, la cual se presenta aquí:\r\n" + "\r\n"
					+ "Aliens del planeta Krytol han comenzado un ataque sobre el \r\n"
					+ "planeta Zardon. Los Krytolianos son guerreros que buscan destruir\r\n"
					+ "y aprovecharse el planeta Zardon. Zardon es el último de los\r\n"
					+ "planetas pacíficos. Los Zardonianos son personas hábiles y \r\n"
					+ "trabajadoras, ellos mismos construyeron sus ciudades y son ricas \r\n"
					+ "en recursos. Además, Zardon es realmente un planeta libre de \r\n" + "crímenes y violencia.\r\n"
					+ "\r\n" + "Zardon ha construido un sistema de defensa poderoso. \r\n"
					+ "Varias bases de misiles antibalìsticos han sido establecidas \r\n"
					+ "dentro de las ciudades de Zardon. Los Zardonianos están listos \r\n"
					+ "para un ataque, y estàn preparados para pelear -si es necesario- \r\n"
					+ "para salvar sus ciudades.\r\n" + "\r\n"
					+ "Como comandante de la base, es tu responsabilidad proteger y \r\n"
					+ "defender las 6 ciudades del planeta de Zardon. Los Krytolianos \r\n"
					+ "han empezado a disparar misiles balìsticos interplanetarios, \r\n"
					+ "están apuntando a tus ciudades y hacia tus 3 bases de misiles: \r\n"
					+ "Alpha, Delta y Omega. Tu única defensa es devolver el fuego con \r\n"
					+ "misiles antibalìsticos. Pero, ¡cuidado!, los Krytolianos son \r\n"
					+ "astutos, también tienen misiles crucero. Los misiles crucero se \r\n"
					+ "ven como satélites, pero son tan dañinos como \r\n"
					+ "los misiles balísticos interplanetarios.\r\n" + "\r\n"
					+ "Deberás usar tus misiles antibalísticos (MABs) para detener al \r\n"
					+ "enemigo, antes que tu alegre y armonioso planeta sea destruido.\r\n" + "";

			return Historia;
		}

		private String dameComoJugar() {
			String ComoJugar = "El objetivo del juego es defender tus ciudades y bases de misiles, \r\n"
					+ "El enemigo dispara misiles balísticos interplanetarios (MBI) y \r\n"
					+ "misiles crucero, ambos tienen como objetivo destruir tus ciudades \r\n"
					+ "y bases militares. Hay 2 tipos de misil crucero: misiles cruceros \r\n"
					+ "tontos (que caen en línea recta) y misiles crucero inteligentes, \r\n"
					+ "los cuales tratarán de evadir tus misiles antibalísticos (MABs). \r\n" + "\r\n"
					+ "El enemigo ataca en una serie de oleadas que puede variar en el \r\n"
					+ "número de misiles balísticos interplanetarios que atacan. \r\n"
					+ "Cada oleada de misiles se mueve más rápido que la oleada anterior. \r\n"
					+ "Mientras más rápida es la oleada, \r\n" + "más difícil es defender las ciudades. \r\n"
					+ "Por este motivo, mientras más rápida es la oleada, \r\n"
					+ "más alto será el puntaje ganado. \r\n" + "\r\n"
					+ "Con cada oleada, tenés 30 MABs para defenderte, \r\n"
					+ "10 en cada uno de los silos, los cuales están ubicados en la \r\n"
					+ "parte inferior de la pantalla, a la izquierda, al centro \r\n"
					+ "y a la derecha. Una vez que has disparado los 30 MABs,\r\n"
					+ " estarás indefenso hasta que comience una nueva oleada.\r\n" + "\r\n"
					+ "Cada vez que un MAB llega a su destino o alcanza algún misil \r\n"
					+ "enemigo ocurre una explosión. La onda expansiva de esta explosión \r\n"
					+ "puede ocasionar que otros misiles balísticos interplanetarios o \r\n"
					+ "crucero que se encuentran en la zona de impacto también sean \r\n"
					+ "destruidos. Si la explosión del MAB no alcanza la cabeza del misil, \r\n"
					+ "éste seguirá con su trayectoria, sólo se verá afectada la estela que \r\n"
					+ "deja a su paso..\r\n" + "\r\n"
					+ "Debes tener en cuenta que hay un umbral (línea horizontal imaginaria) \r\n"
					+ "en el campo de juego, debajo de la cual no podrás disparar misiles. \r\n"
					+ "De esta manera se protege a los silos \r\n" + "y las ciudades de la autodestrucción.\r\n"
					+ "\r\n" + "Si logras sobrevivir a la oleada, pasarás al siguiente nivel, \r\n"
					+ "si no logras sobrevivir a la oleada, \r\n"
					+ "tendrás sólo otra oportunidad para volver a defenderte.\r\n" + "\r\n"
					+ "El juego finaliza cuando todas las ciudades son destruidas.\r\n" + "";
			return ComoJugar;
		}

	}

	// StackPane funciona como una Stack, una cosa se apila sobre la otra,
	/**
	 * Clase Que representa Un boton
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

		public Marco(String path, String nombre) {
			texto = new Text(nombre);
			texto.getFont();
			texto.setFont(Font.font(20));
			texto.setFill(Color.WHITE);

			// rectangulo que encuadre a El Enemigo con su nombre
			Rectangle marco = new Rectangle(400, 30);
			marco.setOpacity(0.6);// Transparencia
			marco.setFill(javafx.scene.paint.Color.BLACK);
			try {
				InputStream is = Files.newInputStream(Paths.get(path));
				Image imagen = new Image(is);
				is.close();
				ImageView vista = new ImageView(imagen);
				vista.setFitHeight(40);
				vista.setFitWidth(40);
				setRotate(-0.5);
				
				getChildren().addAll(texto, vista);
				
			} catch (IOException ex) {
				System.out.println("error al cargar el icono");
			}

			// marco.setEffect(new GaussianBlur(3.5));// efecto blur

		}

	}

	public static void main(String[] args) {
		launch(args);
	}

}
