package aplikacija;

import java.io.File;
import java.util.logging.Level;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import kontroleri.PocetnaKontroler;
import pomocni.DetektujSudar;
import pomocni.KreirajLetjelice;
import pomocni.KreirajRakete;
import pomocni.LoggerKlasa;
import pomocni.ZipFajlova;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {

	public static Integer brojLetjelica = 0;
	public static Integer parametar = 0;

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(
					new File("src" + File.separator + "ekran" + File.separator + "PocetnaForma.fxml").toURI().toURL());
			Scene scene = new Scene(root, 600, 600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Pocetni ekran");
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					primaryStage.show();
				}
			});
		} catch (Exception e) {
			LoggerKlasa.getLogger().log(Level.INFO, e.toString(), e);

		}
	}

	public static void main(String[] args) {

		// prati config fajl za parametar koji je namijenjen za strane letjelice
		Thread pracenjeParametra = new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						LoggerKlasa.getLogger().log(Level.INFO, e.toString(), e);
					}
					parametar = KreirajRakete.vratiParametarZaStraneLetjelice();

				}
			}
		};
		pracenjeParametra.setDaemon(true);
		pracenjeParametra.start();
		// Random randZaOdabirLetjelica = new Random();
		Integer broj = KreirajRakete.vratiBrojLetjelicaIzConfiga();
		Integer vrijemeKreiranjaLetjelica = KreirajRakete.vratiVrijemeKreiranjaLetjelicaIzConfiga();
		Thread kreiranjeObjekata = new Thread() {
			@Override
			public void run() {
				while (Main.brojLetjelica < broj) {
					// kreira Rakete
					
					//  Integer slucajanBroj = randZaOdabirLetjelica.nextInt(10);
					 // if ((slucajanBroj == 2) || (slucajanBroj == 4) || (slucajanBroj == 6)) { 
					//	  try {
					//		  Thread.sleep(vrijemeKreiranjaLetjelica); 
					//  } catch (InterruptedException e) {
					//	  LoggerKlasa.getLogger().log(Level.INFO, e.toString(), e); 
					//  } KreirajRakete.ubaciRaketuUMapu(); 
					//  } else {
					 
					try {
						Thread.sleep(vrijemeKreiranjaLetjelica);
					} catch (InterruptedException e) {
						LoggerKlasa.getLogger().log(Level.INFO, e.toString(), e);
					}
					if (!PocetnaKontroler.zaustavi)
						KreirajLetjelice.ubaciLetjelicuUMapu();
				}
			}
			// }
		};
		kreiranjeObjekata.setDaemon(true);
		kreiranjeObjekata.start();
		Thread praviZip = new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(60000);
					} catch (InterruptedException e) {
						LoggerKlasa.getLogger().log(Level.INFO, e.toString(), e);
					}
					ZipFajlova.praviZipFajl();
				}
			};
		};
		praviZip.setDaemon(true);
		praviZip.start();
		launch(args);
		DetektujSudar.brisiSveSerijalizovaneSudare();
	}
}
