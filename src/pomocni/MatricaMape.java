package pomocni;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;

import interfejsi.Bombardovanje;
import interfejsi.GadjanjeCiljeva;
import interfejsi.GadjanjeOblaka;
import interfejsi.GasenjeAvionom;
import interfejsi.GasenjeHelikopterom;
import interfejsi.LovljenjeMete;
import interfejsi.PrevozAvionom;
import interfejsi.PrevozHelikopterom;
import interfejsi.SnimanjeTerena;
import interfejsi.TeretAvionom;
import interfejsi.TeretHelikopterom;
import kontroleri.PocetnaKontroler;

public class MatricaMape {

	public static Object[][] matricaMape = new Object[100][100];

	public static synchronized void upisLetjeliceUMapu(Object[][] o) {
		String s = "";
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (o[i][j] instanceof GadjanjeOblaka)
					s += o[i][j].toString();
				else if (o[i][j] instanceof LovljenjeMete)
					s += o[i][j].toString();
				else if (o[i][j] instanceof TeretAvionom)
					s += o[i][j].toString();
				else if (o[i][j] instanceof PrevozAvionom)
					s += o[i][j].toString();
				else if (o[i][j] instanceof GasenjeAvionom)
					s += o[i][j].toString();
				else if (o[i][j] instanceof Bombardovanje)
					s += o[i][j].toString();
				else if (o[i][j] instanceof GadjanjeCiljeva)
					s += o[i][j].toString();
				else if (o[i][j] instanceof TeretHelikopterom)
					s += o[i][j].toString();
				else if (o[i][j] instanceof PrevozHelikopterom)
					s += o[i][j].toString();
				else if (o[i][j] instanceof GasenjeHelikopterom)
					s += o[i][j].toString();
				else if (o[i][j] instanceof SnimanjeTerena)
					s += o[i][j].toString();
				else
					s += "    ";
			}
			s += "\n";
		}

		synchronized (PocetnaKontroler.lock) {

			File f = new File("fajlovi" + File.separator + "map.txt");
			try {
				PrintWriter pw = new PrintWriter(f);
				pw.print(s);
				pw.close();
			} catch (FileNotFoundException e) {
				LoggerKlasa.getLogger().log(Level.INFO, e.toString(), e);
			}

		}
	}

}
