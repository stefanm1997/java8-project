package kontroleri;

import java.io.File;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import pomocni.DetektujSudar;

public class SudarKontroler {

	@FXML
	private ListView<String> list;

	@FXML
	private TextArea area;

	private File fajl;

	@FXML
	void initialize() {

		fajl = new File("serijal");
		String[] fajlovi = fajl.list();
		list.setItems(FXCollections.observableArrayList(fajlovi));

	}

	@FXML
	void izaberiSudar(MouseEvent event) {

		String dogadjaj = list.getSelectionModel().getSelectedItem();
		if (dogadjaj != null) {

			File f = new File(fajl, dogadjaj);
			DetektujSudar ds = DetektujSudar.deserijalizujSudar(f);
			area.setText("Opis sudara: " + ds.getOpis() + "\n" + "Pozicija sudara: [" + ds.getTrenutnaPozicijaI() + "]["
					+ ds.getTrenutnaPozicijaJ() + "]\n Vrijeme sudara: " + ds.datum);

		}
	}

}
