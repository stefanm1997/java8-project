package rakete;

import java.util.Random;
import java.util.logging.Level;

import pomocni.KreirajRakete;
import pomocni.LoggerKlasa;
import pomocni.MatricaMape;

public abstract class Raketa extends Thread {

	private Integer dometRakete, visinaLetenja, brzinaLetenja;
	public String oznaka;
	public Integer strana, pozicija;

	public Raketa() {
		super();
	}

	public Raketa(Integer dometRakete, Integer visinaLetenja) {
		super();
		Random rand = new Random();
		Integer slucajanBroj = rand.nextInt(3) + 1;
		this.dometRakete = dometRakete;
		this.visinaLetenja = visinaLetenja;
		brzinaLetenja = slucajanBroj;
		this.oznaka = "RK";
	}

	public Integer getDometRakete() {
		return dometRakete;
	}

	public void setDometRakete(Integer dometRakete) {
		this.dometRakete = dometRakete;
	}

	public Integer getVisinaLetenja() {
		return visinaLetenja;
	}

	public void setVisinaLetenja(Integer visinaLetenja) {
		this.visinaLetenja = visinaLetenja;
	}

	public Integer getBrzinaLetenja() {
		return brzinaLetenja;
	}

	public void setBrzinaLetenja(Integer brzinaLetenja) {
		this.brzinaLetenja = brzinaLetenja;
	}

	@Override
	public String toString() {
		return " " + oznaka + " ";
	}

	@Override
	public void run() {

		strana = KreirajRakete.strana;
		pozicija = KreirajRakete.pozicija;
		Random rand = new Random();
		brzinaLetenja = (rand.nextInt(3) + 1) * 1000;
		if (strana == 0) {
			for (int i = 0; i < 100; i++) {
				try {
					Thread.sleep(brzinaLetenja);
				} catch (InterruptedException e) {
					LoggerKlasa.getLogger().log(Level.INFO, e.toString(), e);
				}
				MatricaMape.matricaMape[i][pozicija] = this;
				if (i != 0)
					MatricaMape.matricaMape[i - 1][pozicija] = null;

			}
			MatricaMape.matricaMape[99][pozicija] = null;
		}
		if (strana == 1) {
			for (int j = 0; j < 100; j++) {
				try {
					Thread.sleep(brzinaLetenja);
				} catch (InterruptedException e) {
					LoggerKlasa.getLogger().log(Level.INFO, e.toString(), e);
				}
				MatricaMape.matricaMape[pozicija][j] = this;
				if (j != 0)
					MatricaMape.matricaMape[pozicija][j - 1] = null;

			}
			MatricaMape.matricaMape[pozicija][99] = null;
		}
		if (strana == 2) {
			for (int i = 99; i >= 0; i--) {
				try {
					Thread.sleep(brzinaLetenja);
				} catch (InterruptedException e) {
					LoggerKlasa.getLogger().log(Level.INFO, e.toString(), e);
				}
				MatricaMape.matricaMape[i][pozicija] = this;
				if (i != 99)
					MatricaMape.matricaMape[i + 1][pozicija] = null;

			}
			MatricaMape.matricaMape[0][pozicija] = null;
		}
		if (strana == 3) {
			for (int j = 99; j >= 0; j--) {
				try {
					Thread.sleep(brzinaLetenja);
				} catch (InterruptedException e) {
					LoggerKlasa.getLogger().log(Level.INFO, e.toString(), e);
				}
				MatricaMape.matricaMape[pozicija][j] = this;
				if (j != 99)
					MatricaMape.matricaMape[pozicija][j + 1] = null;

			}
			MatricaMape.matricaMape[pozicija][0] = null;
		}
	}
}
