package pomocni;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;

import aplikacija.Main;
import rakete.ProtivgradneRakete;
import rakete.Raketa;
import rakete.VojnaRaketa;

public class KreirajRakete {

	public static Integer strana, pozicija;

	public KreirajRakete() {
		/*
		 * Integer broj = vratiBrojLetjelicaIzConfiga(); while (Main.brojLetjelica !=
		 * broj) { ubaciRaketuUMapu(); }
		 */
	}

	public static Integer vratiBrojLetjelicaIzConfiga() {
		Integer brojLetjelica = 0;
		Properties prop = new Properties();
		try {
			FileInputStream in = new FileInputStream("fajlovi" + File.separator + "config.properties");
			prop.load(in);
			brojLetjelica = Integer.valueOf(prop.getProperty("brojLetjelica"));
			in.close();

		} catch (IOException e) {
			LoggerKlasa.getLogger().log(Level.INFO, e.toString(), e);
		}
		return brojLetjelica;
	}

	public static Integer vratiVrijemeKreiranjaLetjelicaIzConfiga() {
		Integer vrijemeKreiranjaLetjelica = 0;
		Properties prop = new Properties();
		try {
			FileInputStream in = new FileInputStream("fajlovi" + File.separator + "config.properties");
			prop.load(in);
			vrijemeKreiranjaLetjelica = Integer.valueOf(prop.getProperty("vrijemeKreiranjaLetjelica"));
			in.close();

		} catch (IOException e) {
			LoggerKlasa.getLogger().log(Level.INFO, e.toString(), e);
		}
		return vrijemeKreiranjaLetjelica;
	}
	
	public static Integer vratiVrijemeAzuriranjaFajlaIzConfiga() {
		Integer vrijemeAzuriranja = 0;
		Properties prop = new Properties();
		try {
			FileInputStream in = new FileInputStream("fajlovi" + File.separator + "radar.properties");
			prop.load(in);
			vrijemeAzuriranja = Integer.valueOf(prop.getProperty("vrijemeAzuriranjaMape"));
			in.close();

		} catch (IOException e) {
			LoggerKlasa.getLogger().log(Level.INFO, e.toString(), e);
		}
		return vrijemeAzuriranja;
	}

	public static Integer vratiParametarZaStraneLetjelice() {
		Integer parametarZaStraneLetjelice = 0;
		Properties prop = new Properties();
		try {
			FileInputStream in = new FileInputStream("fajlovi" + File.separator + "config.properties");
			prop.load(in);
			parametarZaStraneLetjelice = Integer.valueOf(prop.getProperty("parametarZaStraneLetjelice"));
			in.close();

		} catch (IOException e) {
			LoggerKlasa.getLogger().log(Level.INFO, e.toString(), e);
		}
		return parametarZaStraneLetjelice;
	}

	public static void ubaciRaketuUMapu() {
		if(!KreirajLetjelice.stranaLet) {
		Random randZaOdabirRakete = new Random();
		Random randZaOdabirStrane = new Random();
		Random randZaOdabirPozicije = new Random();
		Integer slucajanBrojRakete = randZaOdabirRakete.nextInt(2);
		Raketa r = null;
		if (slucajanBrojRakete == 0) {
			r = new VojnaRaketa(15000, 10000);
			r.start();
		} else {
			r = new ProtivgradneRakete(5000, 400);
			r.start();
		}
		Integer slucajanBrojStrane = randZaOdabirStrane.nextInt(4);
		Integer slucajanBrojPozicije = randZaOdabirPozicije.nextInt(100);
		odabirStraneIPozicije(slucajanBrojStrane, slucajanBrojPozicije, r);
		Main.brojLetjelica++;
		}
	}

	public static void odabirStraneIPozicije(Integer strana, Integer pozicija, Raketa r) {

		KreirajRakete.strana = strana;
		KreirajRakete.pozicija = pozicija;

		if (strana == 0) {

			MatricaMape.matricaMape[0][pozicija] = r;
			MatricaMape.upisLetjeliceUMapu(MatricaMape.matricaMape);

		}
		if (strana == 1) {

			MatricaMape.matricaMape[pozicija][0] = r;
			MatricaMape.upisLetjeliceUMapu(MatricaMape.matricaMape);
		}

		if (strana == 2) {

			MatricaMape.matricaMape[99][pozicija] = r;
			MatricaMape.upisLetjeliceUMapu(MatricaMape.matricaMape);

		}
		if (strana == 3) {
			MatricaMape.matricaMape[pozicija][99] = r;
			MatricaMape.upisLetjeliceUMapu(MatricaMape.matricaMape);
		}

	}

}
