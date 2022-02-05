package kontroleri;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;

import aplikacija.NoviDogadjajForma;
import aplikacija.NoviSudarForma;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import pomocni.KreirajLetjelice;
import pomocni.KreirajRakete;
import pomocni.LoggerKlasa;
import pomocni.MatricaMape;

public class PocetnaKontroler {

	@FXML
	private Button dugme1;

	@FXML
	private Button dugme2;

	@FXML
	private Button dugme3;

	@FXML
	private TextArea area;

	@FXML
	private Label lab;

	public static boolean zaustavi = false;

	@FXML
	void zaustaviSaobracaj() {

		zaustavi = true;
	}

	@FXML
	void pokreniSaobracaj() {

		zaustavi = false;
	}

	@FXML
	void prikazSvihDogadjaja() {

		NoviDogadjajForma forma = new NoviDogadjajForma();
		forma.otvoriDogadjaje("DogadjajForma.fxml", 600, 400);
	}

	@FXML
	void prikazSvihSudara() {

		NoviSudarForma forma = new NoviSudarForma();
		forma.otvoriSudare("SudarForma.fxml", 600, 400);
	}

	@FXML
	private void initialize() {
		// vraca vrijeme azuriranja mape iz radar.config-a
		Integer vrijemeAzuriranja = KreirajRakete.vratiVrijemeAzuriranjaFajlaIzConfiga();

		// nit koja upisuje letjelice u mapu(vrsi simulaciju leta)
		Thread t1 = new Thread() {
			@Override
			public void run() {

				try {
					sleep(300);
				} catch (InterruptedException e1) {
					LoggerKlasa.getLogger().log(Level.INFO, e1.toString(), e1);
				}
				while (true) {
					MatricaMape.upisLetjeliceUMapu(MatricaMape.matricaMape);
					try {
						sleep(vrijemeAzuriranja);
					} catch (InterruptedException e) {

						LoggerKlasa.getLogger().log(Level.INFO, e.toString(), e);
					}
				}
			}
		};
		t1.setDaemon(true);
		t1.start();

		// vrti se tred i prati da li ce uci strana letjelica u vazdusni prostor,ako
		// udje upisuje se u labelu
		Thread pomocna = new Thread() {
			public void run() {

				while (!KreirajLetjelice.stranaLet) {

					try {
						Thread.sleep(1000);

					} catch (InterruptedException e) {
						LoggerKlasa.getLogger().log(Level.INFO, e.toString(), e);
					}
				}
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						lab.setText("Usla je " + KreirajLetjelice.stranaLetjelica.getModel()
								+ " letjelica u vazdusni prostor sa strane "+KreirajLetjelice.stranaLetjelica.strana);
					}
				});

			};
		};
		pomocna.setDaemon(true);
		pomocna.start();

		// nit koja cita iz mape letjelice i upisuje ih u text areu
		Thread t2 = new Thread() {
			@Override
			public void run() {
				try {
					sleep(200);
				} catch (InterruptedException e1) {
					LoggerKlasa.getLogger().log(Level.INFO, e1.toString(), e1);
				}
				while (true) {
					dodajUAreu();
					try {
						sleep(vrijemeAzuriranja);
					} catch (InterruptedException e) {
						LoggerKlasa.getLogger().log(Level.INFO, e.toString(), e);
					}
				}
			}
		};
		t2.setDaemon(true);
		t2.start();
	}

	public static Object lock = new Object();
	private String upis = "";

	// funkcija koja cita letjelice iz mape i upisuje ih u text areu
	public void dodajUAreu() {

		synchronized (PocetnaKontroler.lock) {

			File f = new File("fajlovi" + File.separator + "map.txt");
			try {
				BufferedReader br = new BufferedReader(new FileReader(f));
				String s = "";
				upis = "";
				while ((s = br.readLine()) != null) {

					upis += s + "\n";

				}

				br.close();
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						area.setText(upis);

					}
				});

			} catch (FileNotFoundException e) {
				LoggerKlasa.getLogger().log(Level.INFO, e.toString(), e);
			} catch (IOException e) {
				LoggerKlasa.getLogger().log(Level.INFO, e.toString(), e);
			}
		}
	}

}
