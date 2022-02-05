package kontroleri;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;

import javafx.collections.FXCollections;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import pomocni.LoggerKlasa;

public class DogadjajKontroler {

	@FXML
	private ListView<String> list;

	@FXML
	private TextArea area;

	private File fajl;

	@FXML
	void initialize() {

		fajl = new File("events");
		String[] fajlovi = fajl.list();
		list.setItems(FXCollections.observableArrayList(fajlovi));
	}

	@FXML
	void izaberiDogadjaj(MouseEvent event) {

		String dogadjaj = list.getSelectionModel().getSelectedItem();
		if (dogadjaj != null) {
			File f = new File(fajl, dogadjaj);

			try (FileInputStream in = new FileInputStream(f)) {

				String sadrzaj = new String(in.readAllBytes());
				String[] split = sadrzaj.split("#");
				// area.setText(new String(in.readAllBytes()));
				area.setText("Oznaka letjelice: " + split[0] + "\n" + "Model letjelice: " + split[1] + "\n"
						+ "Karakteristike letjelice: " + split[2] + "\n" + "Brzina letjelice: " + split[3] + "\n"
						+ "Visina letjelice: " + split[4] + "\n");

			} catch (FileNotFoundException e) {
				LoggerKlasa.getLogger().log(Level.INFO, e.toString(), e);
			} catch (IOException e1) {
				LoggerKlasa.getLogger().log(Level.INFO, e1.toString(), e1);
			}
		}
	}
}
