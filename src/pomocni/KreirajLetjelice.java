package pomocni;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

import aplikacija.Main;
import letjelice.BespilotnaLetjelica;
import letjelice.Bombarder;
import letjelice.Letjelica;
import letjelice.Lovac;
import letjelice.ProtivPozarniAvion;
import letjelice.ProtivPozarniHelikopter;
import letjelice.PutnickiAvion;
import letjelice.PutnickiHelikopter;
import letjelice.TransportniAvion;
import letjelice.TransportniHelikopter;
import osobe.Pilot;
import osobe.Putnik;
import osobe.Osoba;

public class KreirajLetjelice {

	public static Integer strana = 0, pozicija = 0;
	public static Letjelica stranaLetjelica;

	public static Integer brzina = 0;

	public KreirajLetjelice() {
		/*
		 * Integer broj = KreirajRakete.vratiBrojLetjelicaIzConfiga(); while
		 * (Main.brojLetjelica != broj) { ubaciLetjelicuUMapu(); }
		 */
	}

	public static boolean stranaLet = false;

	// f-ja koja pravi letjelice(i strane i domace)
	public static void ubaciLetjelicuUMapu() {
		if (!stranaLet) {
			Random randZaOdabirLetjelice = new Random();
			Random randZaOdabirStrane = new Random();
			Random randZaOdabirPozicije = new Random();
			Integer slucajanBrojLetjelice = randZaOdabirLetjelice.nextInt(9);
			Letjelica l = null;
			Pilot p1 = new Pilot("Marko", "Markovic", true);
			Putnik p2 = new Putnik("Ivan", "Ivanovic", "A100");
			Putnik p3 = new Putnik("Mirko", "Mirkovic", "B100");
			Putnik p4 = new Putnik("Mitar", "Mitrovic", "C100");
			List<Osoba> osoba = new ArrayList<Osoba>();
			List<Osoba> osoba2 = new ArrayList<Osoba>();
			List<Osoba> osoba3 = new ArrayList<Osoba>();
			osoba.add(p1);
			osoba.add(p2);
			osoba.add(p3);
			osoba.add(p4);
			osoba2.add(p1);
			osoba2.add(p2);
			osoba3.add(p1);
			if (slucajanBrojLetjelice == 0) {
				l = new TransportniAvion("An-225 Mriya", 11000, 800, osoba, "najveci avion na svijetu", 600000);
			} else if (slucajanBrojLetjelice == 1) {
				l = new PutnickiAvion("Boing 747", 13000, 950, osoba, "najveci putnicki avion", 400, 350000);
			} else if (slucajanBrojLetjelice == 2) {
				// moze imati 2 clana posade,zato nova lista osoba
				l = new ProtivPozarniAvion("Canadair CL-215", 13000, 290, osoba2,
						"namijenjen za ispustanje  vodenih bombi", 5500);
			} else if ((slucajanBrojLetjelice == 3)) {
				l = new Bombarder("B-2 Spirit", 11000, 950, osoba2, "smanjene uocljivosti");

			} else if (slucajanBrojLetjelice == 4) {
				// osoba3 jer ima samo pilot
				l = new Lovac("Grumman F6F ", 11000, 600, osoba3, "povecana sigurnost pilota");
			} else if (slucajanBrojLetjelice == 5) {
				l = new TransportniHelikopter("CH-47 Chinook", 5500, 300, osoba2, "dvostruki motor i tandemski rotori",
						22000);
			} else if (slucajanBrojLetjelice == 6) {
				l = new PutnickiHelikopter("Mil Mi-26", 13000, 300, osoba, "najveci helikopter na svijetu", 80);

			} else if (slucajanBrojLetjelice == 7) {
				l = new ProtivPozarniHelikopter("Kamov Ka-32", 25, 250, osoba2, "prilagodljiv za skoro sve namjene",
						4000);
			} else {
				l = new BespilotnaLetjelica("MQ-1 Predator", 7500, 300, null, "izvidjanje i napad na ciljeve");
			}

			l.strana = randZaOdabirStrane.nextInt(4);
			l.pozicija = randZaOdabirPozicije.nextInt(100);
			odabirStraneIPozicije(l.strana, l.pozicija, l);
			Main.brojLetjelica++;
			l.start();

			// kreiraj stranu letjelicu i dodaj 2 domace letjelice da ju prate i pokusaju
			// unistiti
			if (Main.parametar > 0 && !stranaLet) {
				// kreira se strana letjelica
				
				l.oznaka = "SL";
				stranaLetjelica = l;
				Letjelica.daLiTrebaSkretati = true;
				// pravi fajl u koji upisuje info o stranoj letjelici
				SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy_hh_mm_ss");
				String imeFajla = sdf.format(new Date());
				File f = new File("events" + File.separator + imeFajla);
				try {
					PrintWriter pw = new PrintWriter(f);
					pw.print(l + "#" + l.getModel() + "#" + l.getKarakteristike() + "#" + l.getBrzinaLeta() + "#"
							+ l.getVisinaLeta());
					pw.close();
				} catch (FileNotFoundException e) {
					LoggerKlasa.getLogger().log(Level.INFO, e.toString(), e);
				}

				// nit koja pravi 2 domace letjelice
				Thread t = new Thread() {
					@Override
					public void run() {

						// bira se brzina za domace letjelice
						Random rand = new Random();
						brzina = (rand.nextInt(3) + 1) * 1000;
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							LoggerKlasa.getLogger().log(Level.INFO, e.toString(), e);
						}
						// pravi se prva domaca letjelica
						Lovac l1 = new Lovac("Grumman F6F ", stranaLetjelica.getVisinaLeta(), 600, osoba3, "povecana sigurnost pilota");
						l1.oznaka = "DL";
						// ovo je za provjeru ako treba da ide nasuprot strane
						// if((stranaLetjelica.strana == 0) || (stranaLetjelica.strana == 1))
						// l1.strana = stranaLetjelica.strana + 2;
						// else
						// l1.strana = stranaLetjelica.strana - 2;

						l1.setBrzinaLeta(brzina);
						l1.strana = stranaLetjelica.strana;
						l1.pozicija = stranaLetjelica.pozicija + 1;
						if(l1.pozicija>99)
							l1.pozicija=99;
						if (l1.strana == 0) {
							l1.trenutnaPozicijaI = 0;
							l1.trenutnaPozicijaJ = l1.pozicija;
						}
						if (l1.strana == 1) {
							l1.trenutnaPozicijaI = l1.pozicija;
							l1.trenutnaPozicijaJ = 0;
						}
						if (l1.strana == 2) {
							l1.trenutnaPozicijaI = 99;
							l1.trenutnaPozicijaJ = l1.pozicija;
						}
						if (l1.strana == 3) {
							l1.trenutnaPozicijaI = l1.pozicija;
							l1.trenutnaPozicijaJ = 99;
						}
						l1.start();
						// pravi se druga domaca letjelica
						Lovac l2 = new Lovac("Grumman F6F ", stranaLetjelica.getVisinaLeta(), 600, osoba3, "povecana sigurnost pilota");
						l2.oznaka = "DL";
						// if((stranaLetjelica.strana == 0) || (stranaLetjelica.strana == 1))
						// l2.strana = stranaLetjelica.strana + 2;
						// else
						// l2.strana = stranaLetjelica.strana - 2;
						
						l2.setBrzinaLeta(brzina);
						l2.strana = stranaLetjelica.strana;
						l2.pozicija = stranaLetjelica.pozicija - 1;
						if(l2.pozicija<0)
							l2.pozicija=0;
						if (l2.strana == 0) {
							l2.trenutnaPozicijaI = 0;
							l2.trenutnaPozicijaJ = l2.pozicija;
						}
						if (l2.strana == 1) {
							l2.trenutnaPozicijaI = l2.pozicija;
							l2.trenutnaPozicijaJ = 0;
						}
						if (l2.strana == 2) {
							l2.trenutnaPozicijaI = 99;
							l2.trenutnaPozicijaJ = l2.pozicija;
						}
						if (l2.strana == 3) {
							l2.trenutnaPozicijaI = l2.pozicija;
							l2.trenutnaPozicijaJ = 99;
						}
						l2.start();

					}
				};
				stranaLet = true;
				t.start();
			}
		}
	}

	// f-ja koja bira pocetnu stranu i poziciju i upisuje letjelicu u mapu na njenu pocetnu poziciju
	public static void odabirStraneIPozicije(Integer strana, Integer pozicija, Letjelica l) {

		if (strana == 0) {

			MatricaMape.matricaMape[0][pozicija] = l;
			l.trenutnaPozicijaI = 0;
			l.trenutnaPozicijaJ = pozicija;
			MatricaMape.upisLetjeliceUMapu(MatricaMape.matricaMape);

		}
		if (strana == 1) {

			MatricaMape.matricaMape[pozicija][0] = l;
			l.trenutnaPozicijaJ = 0;
			l.trenutnaPozicijaI = pozicija;
			MatricaMape.upisLetjeliceUMapu(MatricaMape.matricaMape);
		}

		if (strana == 2) {

			MatricaMape.matricaMape[99][pozicija] = l;
			l.trenutnaPozicijaI = 99;
			l.trenutnaPozicijaJ = pozicija;
			MatricaMape.upisLetjeliceUMapu(MatricaMape.matricaMape);

		}
		if (strana == 3) {
			MatricaMape.matricaMape[pozicija][99] = l;
			l.trenutnaPozicijaI = pozicija;
			l.trenutnaPozicijaJ = 99;
			MatricaMape.upisLetjeliceUMapu(MatricaMape.matricaMape);
		}
	}
}
