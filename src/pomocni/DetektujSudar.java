package pomocni;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;

import letjelice.Letjelica;

public class DetektujSudar implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String opis;
	public Date datum;
	private Integer trenutnaPozicijaI = 0, trenutnaPozicijaJ = 0;
	public Letjelica l1, l2;
	public static Integer brojac = 0;

	public DetektujSudar(String opis, Integer trenutnaPozicijaI, Integer trenutnaPozicijaJ, Letjelica l1,
			Letjelica l2) {
		super();
		this.opis = opis;
		this.datum = new Date();
		this.trenutnaPozicijaI = trenutnaPozicijaI;
		this.trenutnaPozicijaJ = trenutnaPozicijaJ;
		this.l1 = l1;
		this.l2 = l2;
	}

	public static void serijalizujSudar(DetektujSudar ds) {
		try {

			FileOutputStream fis = new FileOutputStream(
					"serijal" + File.separator + "serijalizovanSudar" + (brojac++) + ".ser");
			ObjectOutputStream out = new ObjectOutputStream(fis);
			out.writeObject(ds);
			out.close();
			fis.close();
		} catch (FileNotFoundException e) {
			LoggerKlasa.getLogger().log(Level.INFO, e.toString(), e);
		} catch (IOException e) {
			LoggerKlasa.getLogger().log(Level.INFO, e.toString(), e);
		}
	}

	public static DetektujSudar deserijalizujSudar(File f) {

		DetektujSudar ds;
		try {
			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream in = new ObjectInputStream(fis);
			ds = (DetektujSudar) in.readObject();
			in.close();
			fis.close();
			return ds;
		} catch (FileNotFoundException e) {
			LoggerKlasa.getLogger().log(Level.INFO, e.toString(), e);
		} catch (IOException e) {
			LoggerKlasa.getLogger().log(Level.INFO, e.toString(), e);
		} catch (ClassNotFoundException e) {
			LoggerKlasa.getLogger().log(Level.INFO, e.toString(), e);
		}
		return null;
	}

	public static void brisiSveSerijalizovaneSudare() {

		File fajl = new File("serijal");
		File[] niz = fajl.listFiles();
		for (File f : niz) {
			f.delete();
		}
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Integer getTrenutnaPozicijaI() {
		return trenutnaPozicijaI;
	}

	public void setTrenutnaPozicijaI(Integer trenutnaPozicijaI) {
		this.trenutnaPozicijaI = trenutnaPozicijaI;
	}

	public Integer getTrenutnaPozicijaJ() {
		return trenutnaPozicijaJ;
	}

	public void setTrenutnaPozicijaJ(Integer trenutnaPozicijaJ) {
		this.trenutnaPozicijaJ = trenutnaPozicijaJ;
	}

}
