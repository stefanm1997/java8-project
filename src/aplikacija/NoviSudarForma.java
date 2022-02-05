package aplikacija;

import java.io.File;
import java.util.logging.Level;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pomocni.LoggerKlasa;

public class NoviSudarForma {

	public void otvoriSudare(String fajl, int visina, int sirina) {
		try {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader
					.load(new File("src" + File.separator + "ekran" + File.separator + fajl).toURI().toURL());
			Scene scene = new Scene(root, visina, sirina);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Sudari");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			LoggerKlasa.getLogger().log(Level.INFO, e.toString(), e);
		}

	}

}
