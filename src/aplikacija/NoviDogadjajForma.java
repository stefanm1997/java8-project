package aplikacija;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pomocni.LoggerKlasa;

import java.io.File;
import java.util.logging.Level;

public class NoviDogadjajForma {

	public void otvoriDogadjaje(String fajl, int visina, int sirina) {
		try {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader
					.load(new File("src" + File.separator + "ekran" + File.separator + fajl).toURI().toURL());
			Scene scene = new Scene(root, visina, sirina);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Dogadjaji");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			LoggerKlasa.getLogger().log(Level.INFO, e.toString(), e);
		}

	}

}
