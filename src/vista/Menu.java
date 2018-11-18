package vista;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import controlador.Controlador;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Menu extends Application {
	Stage VentanaMenu;
	Pane raiz;
	Scene escena;
	@Override
	public void start(Stage primaryStage) throws Exception {
		VentanaMenu=primaryStage;
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
			VBox menuConfig = new VBox(10);
			VBox menuReglas = new VBox(10);
			VBox menuComoJugar= new VBox(10);
			VBox menuHistoria= new VBox(10);
			VBox menuTiposDeEnemigos= new VBox(10);
			
			
			menuPpal.setTranslateX(50);
			menuPpal.setTranslateY(100);

			menuConfig.setTranslateX(50);
			menuConfig.setTranslateY(100);

			menuReglas.setTranslateX(50);
			menuReglas.setTranslateY(100);
			

			menuConfig.setTranslateX(50);
			menuReglas.setTranslateX(100);
			
			final int offset = 400;
			BotonDeMenu btnJugar = new BotonDeMenu("JUGAR");
			btnJugar.setOnMouseClicked(event -> {
				FadeTransition ft = new FadeTransition(Duration.seconds(0.5), this);
				ft.setFromValue(1);
				ft.setToValue(0);
				ft.setOnFinished(evt -> setVisible(false));
				ft.play();
					VentanaMenu.close();
					Controlador empezar=new Controlador();
					
					
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

			BotonDeMenu btnNivelInicial = new BotonDeMenu("NIVEL INICIAL");
			
			BotonDeMenu btnCantJugadores = new BotonDeMenu("TABLA DE RANKING");
			
			BotonDeMenu btnHistoria= new BotonDeMenu("HISTORIA");
			btnHistoria.setOnMouseClicked(event -> {
				getChildren().add(menuHistoria);

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
			BotonDeMenu btnComoJugar= new BotonDeMenu("COMO JUGAR");
			btnComoJugar.setOnMouseClicked(event -> {
				getChildren().add(menuComoJugar);

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
			BotonDeMenu btnTiposDeEnemigos= new BotonDeMenu("TIPOS DE ENEMIGOS");
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
			
			menuPpal.getChildren().addAll(btnJugar, btnReglas, btnOptions, btnSalir);
			menuConfig.getChildren().addAll( btnNivelInicial, btnCantJugadores,btnVolver0);
			
			menuReglas.getChildren().addAll(btnHistoria,btnComoJugar,btnTiposDeEnemigos,btnVolver1);
			Rectangle bg = new Rectangle(800, 600);
			bg.setFill(Color.GREY);
			bg.setOpacity(0.4);

			getChildren().addAll(bg, menuPpal);
		}
	}

	// StackPane funciona como una Stack, una cosa se apila sobre la otra,
	class BotonDeMenu extends StackPane {
		private Text texto;

		// constructor
		public BotonDeMenu(String nombre) {
			texto = new Text(nombre);
			texto.setFont(texto.getFont().font(20));
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

	public static void main(String[] args) {
		launch(args);
	}
	
}
