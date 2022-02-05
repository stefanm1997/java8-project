package letjelice;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import kontroleri.PocetnaKontroler;
import osobe.*;
import pomocni.DetektujSudar;
import pomocni.KreirajLetjelice;
import pomocni.LoggerKlasa;
import pomocni.MatricaMape;

public abstract class Letjelica extends Thread implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String model;
	private static Integer id = 0;
	private Integer idLetjelice, visinaLeta, brzinaLeta;
	private HashMap<Integer, String> karakteristike = new HashMap<Integer, String>();
	private List<Osoba> osoba = new ArrayList<Osoba>();
	public String oznaka;
	public Integer strana, pozicija, smjer;
	public boolean daLiSamUnistena = false;
	public Integer pozicijaUnistenjaI = 0, pozicijaUnistenjaJ = 0;
	public Integer trenutnaPozicijaI = 0, trenutnaPozicijaJ = 0;
	public static boolean daLiTrebaSkretati = false;
	public Integer novaPozicija;
	public boolean skrenulaJe = true;

	public Letjelica() {
		super();
	}

	public Letjelica(String model, Integer visinaLeta, Integer brzinaLeta, List<Osoba> osoba, String karakteristike) {
		super();
		this.model = model;
		this.visinaLeta = visinaLeta;
		//this.visinaLeta = 100;
		this.brzinaLeta = brzinaLeta;
		idLetjelice = id++;
		this.karakteristike.put(idLetjelice, karakteristike);
		this.osoba = osoba;
		this.oznaka = "LT";

	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getIdLetjelice() {
		return idLetjelice;
	}

	public void setIdLetjelice(Integer idLetjelice) {
		this.idLetjelice = idLetjelice;
	}

	public Integer getVisinaLeta() {
		return visinaLeta;
	}

	public void setVisinaLeta(Integer visinaLeta) {
		this.visinaLeta = visinaLeta;
	}

	public Integer getBrzinaLeta() {
		return brzinaLeta;
	}

	public void setBrzinaLeta(Integer brzinaLeta) {
		this.brzinaLeta = brzinaLeta;
	}

	public HashMap<Integer, String> getKarakteristike() {
		return karakteristike;
	}

	public void setKarakteristike(HashMap<Integer, String> karakteristike) {
		this.karakteristike = karakteristike;
	}

	public List<Osoba> getOsoba() {
		return osoba;
	}

	public void setOsoba(List<Osoba> osoba) {
		this.osoba = osoba;
	}

	@Override
	public String toString() {
		return " " + oznaka + " ";
	}

	public void kretanjeLetjelica(Integer strana) {

		// bira se na slucajan naci brzina svake letjelice(izmedju 1 i 3 s)
		Random rand = new Random();
		brzinaLeta = (rand.nextInt(3) + 1) * 1000;

		for (int k = 0; k < 100; k++) {
			if ((daLiTrebaSkretati && !"SL".equals(oznaka) && !"DL".equals(oznaka) && skrenulaJe)
					|| (PocetnaKontroler.zaustavi && !"SL".equals(oznaka) && !"DL".equals(oznaka))) {
				strana = izracunajNajkracuPutanju(strana, trenutnaPozicijaI, trenutnaPozicijaJ);
				skrenulaJe = false;
			}

			try {

				if ("DL".equals(this.oznaka)) {

					Thread.sleep(KreirajLetjelice.brzina);
					// Thread.sleep(500);
				} else {

					Thread.sleep(brzinaLeta);
					// Thread.sleep(750);
				}

			} catch (InterruptedException e) {
				LoggerKlasa.getLogger().log(Level.INFO, e.toString(), e);
			}

			if (strana == 0) {
				{
					trenutnaPozicijaI++;
					if (trenutnaPozicijaI < 0 || trenutnaPozicijaI > 99 || trenutnaPozicijaJ < 0
							|| trenutnaPozicijaJ > 99) {
						daLiSamUnistena = true;
						MatricaMape.matricaMape[99][trenutnaPozicijaJ] = null;
						System.out.println("Letjelica je izasla sa strane 0");
						if (trenutnaPozicijaI > 99)
							trenutnaPozicijaI = 99;
						break;
					}
					// provjera sudara,ako se sudare tad se te 2 letjelice brisu
					if (MatricaMape.matricaMape[trenutnaPozicijaI][trenutnaPozicijaJ] != null) {
						if (MatricaMape.matricaMape[trenutnaPozicijaI][trenutnaPozicijaJ] instanceof Letjelica) {
							Letjelica l = (Letjelica) MatricaMape.matricaMape[trenutnaPozicijaI][trenutnaPozicijaJ];
							if (getVisinaLeta() == l.getVisinaLeta()) {
								l.daLiSamUnistena = true;
								daLiSamUnistena = true;
								MatricaMape.matricaMape[trenutnaPozicijaI][trenutnaPozicijaJ] = null;
								MatricaMape.matricaMape[trenutnaPozicijaI - 1][trenutnaPozicijaJ] = null;
								Platform.runLater(new Runnable() {
									@Override
									public void run() {
										new Alert(AlertType.INFORMATION,
												"Letjelica " + model + " i letjelica " + l.model
														+ " su se sudarili na poziciji:  [" + trenutnaPozicijaI + "]"
														+ "[" + trenutnaPozicijaJ + "]").showAndWait();

									}
								});
								DetektujSudar ds = new DetektujSudar(
										"Letjelica " + model + " i letjelica " + l.model + " su se sudarili",
										trenutnaPozicijaI, trenutnaPozicijaJ, this, l);
								DetektujSudar.serijalizujSudar(ds);
							}
						}
					}

					// ako nije unistena nek se krece kroz matricu
					if (!daLiSamUnistena)
						MatricaMape.matricaMape[trenutnaPozicijaI][trenutnaPozicijaJ] = this;

					// brise stranu letjelicu kad se sretne sa domacom
					if (KreirajLetjelice.stranaLet) {
						if ("SL".equals(oznaka)) {
							if ((!daLiSamUnistena && ((trenutnaPozicijaJ + 1 < 100) && (trenutnaPozicijaJ - 1 >= 0)
									&& ((MatricaMape.matricaMape[trenutnaPozicijaI][trenutnaPozicijaJ + 1] != null)
											|| (MatricaMape.matricaMape[trenutnaPozicijaI][trenutnaPozicijaJ
													- 1] != null))))
									|| ((trenutnaPozicijaI + 1 < 100) && (trenutnaPozicijaJ + 1 < 100)
											&& (trenutnaPozicijaI - 1 >= 0) && (trenutnaPozicijaJ - 1 >= 0))
											&& (((MatricaMape.matricaMape[trenutnaPozicijaI - 1][trenutnaPozicijaJ
													+ 1] != null)
													|| (MatricaMape.matricaMape[trenutnaPozicijaI - 1][trenutnaPozicijaJ
															- 1] != null)))) {

								daLiSamUnistena = true;
								pozicijaUnistenjaI = trenutnaPozicijaI;
								pozicijaUnistenjaJ = trenutnaPozicijaJ;
								System.out.println("Domace letjelice su unistile stranu letjelicu!");
							}

						}
					}
					// brisi letjelicu sa prethodne pozicije
					MatricaMape.matricaMape[trenutnaPozicijaI - 1][trenutnaPozicijaJ] = null;
				}

			}

			if (strana == 1) {
				{
					trenutnaPozicijaJ++;
					if (trenutnaPozicijaI < 0 || trenutnaPozicijaI > 99 || trenutnaPozicijaJ < 0
							|| trenutnaPozicijaJ > 99) {
						daLiSamUnistena = true;
						MatricaMape.matricaMape[trenutnaPozicijaI][99] = null;
						System.out.println("Letjelica je izasla sa strane 1");
						if (trenutnaPozicijaJ > 99)
							trenutnaPozicijaJ = 99;
						break;
					}

					if (MatricaMape.matricaMape[trenutnaPozicijaI][trenutnaPozicijaJ] != null) {
						if (MatricaMape.matricaMape[trenutnaPozicijaI][trenutnaPozicijaJ] instanceof Letjelica) {
							Letjelica l = (Letjelica) MatricaMape.matricaMape[trenutnaPozicijaI][trenutnaPozicijaJ];
							if (getVisinaLeta() == l.getVisinaLeta()) {
								l.daLiSamUnistena = true;
								daLiSamUnistena = true;
								MatricaMape.matricaMape[trenutnaPozicijaI][trenutnaPozicijaJ] = null;
								MatricaMape.matricaMape[trenutnaPozicijaI][trenutnaPozicijaJ - 1] = null;

								Platform.runLater(new Runnable() {
									@Override
									public void run() {
										new Alert(AlertType.INFORMATION,
												"Letjelica " + model + " i letjelica " + l.model
														+ " su se sudarili na poziciji:  [" + trenutnaPozicijaI + "]"
														+ "[" + trenutnaPozicijaJ + "]").showAndWait();

									}
								});

								DetektujSudar ds = new DetektujSudar(
										"Letjelica " + model + " i letjelica " + l.model + " su se sudarili",
										trenutnaPozicijaI, trenutnaPozicijaJ, this, l);
								DetektujSudar.serijalizujSudar(ds);
							}
						}
					}
					if (!daLiSamUnistena)
						MatricaMape.matricaMape[trenutnaPozicijaI][trenutnaPozicijaJ] = this;

					if (KreirajLetjelice.stranaLet) {

						if ("SL".equals(oznaka)) {
							if (((!daLiSamUnistena && ((trenutnaPozicijaI + 1) < 100) && ((trenutnaPozicijaI - 1) >= 0)
									&& ((MatricaMape.matricaMape[trenutnaPozicijaI + 1][trenutnaPozicijaJ] != null)
											|| (MatricaMape.matricaMape[trenutnaPozicijaI
													- 1][trenutnaPozicijaJ] != null))))
									|| ((((trenutnaPozicijaJ + 1) < 100) && (trenutnaPozicijaI + 1) < 100)
											&& ((trenutnaPozicijaI - 1) >= 0) && ((trenutnaPozicijaJ - 1) >= 0)
											&& ((MatricaMape.matricaMape[trenutnaPozicijaI + 1][trenutnaPozicijaJ
													- 1] != null)
													|| (MatricaMape.matricaMape[trenutnaPozicijaI - 1][trenutnaPozicijaJ
															- 1] != null)))) {
								daLiSamUnistena = true;
								pozicijaUnistenjaI = trenutnaPozicijaI;
								pozicijaUnistenjaJ = trenutnaPozicijaJ;
								System.out.println("Domace letjelice su unistile stranu letjelicu!");

							}
						}
					}
					MatricaMape.matricaMape[trenutnaPozicijaI][trenutnaPozicijaJ - 1] = null;

				}

			}
			if (strana == 2) {

				{
					trenutnaPozicijaI--;
					if (trenutnaPozicijaI < 0 || trenutnaPozicijaI > 99 || trenutnaPozicijaJ < 0
							|| trenutnaPozicijaJ > 99) {
						daLiSamUnistena = true;
						MatricaMape.matricaMape[0][trenutnaPozicijaJ] = null;
						System.out.println("Letjelica je izasla sa strane 2");
						if (trenutnaPozicijaI < 0)
							trenutnaPozicijaI = 0;
						break;
					}

					if (MatricaMape.matricaMape[trenutnaPozicijaI][trenutnaPozicijaJ] != null) {
						if (MatricaMape.matricaMape[trenutnaPozicijaI][trenutnaPozicijaJ] instanceof Letjelica) {
							Letjelica l = (Letjelica) MatricaMape.matricaMape[trenutnaPozicijaI][trenutnaPozicijaJ];
							if (getVisinaLeta() == l.getVisinaLeta()) {
								l.daLiSamUnistena = true;
								daLiSamUnistena = true;
								MatricaMape.matricaMape[trenutnaPozicijaI][trenutnaPozicijaJ] = null;
								MatricaMape.matricaMape[trenutnaPozicijaI + 1][trenutnaPozicijaJ] = null;

								Platform.runLater(new Runnable() {
									@Override
									public void run() {
										new Alert(AlertType.INFORMATION,
												"Letjelica " + model + " i letjelica " + l.model
														+ " su se sudarili na poziciji:  [" + trenutnaPozicijaI + "]"
														+ "[" + trenutnaPozicijaJ + "]").showAndWait();

									}
								});
								DetektujSudar ds = new DetektujSudar(
										"Letjelica " + model + " i letjelica " + l.model + " su se sudarili",
										trenutnaPozicijaI, trenutnaPozicijaJ, this, l);
								DetektujSudar.serijalizujSudar(ds);
							}
						}
					}
					if (!daLiSamUnistena)
						MatricaMape.matricaMape[trenutnaPozicijaI][trenutnaPozicijaJ] = this;

					if (KreirajLetjelice.stranaLet) {

						if ("SL".equals(oznaka)) {
							if (((!daLiSamUnistena && (((trenutnaPozicijaJ + 1) < 100) && (trenutnaPozicijaJ - 1) >= 0)
									&& ((MatricaMape.matricaMape[trenutnaPozicijaI][trenutnaPozicijaJ + 1] != null)
											|| (MatricaMape.matricaMape[trenutnaPozicijaI][trenutnaPozicijaJ
													- 1] != null))))
									|| (((trenutnaPozicijaI - 1) >= 0) && ((trenutnaPozicijaI + 1) < 100)
											&& ((trenutnaPozicijaJ + 1) < 100) && (trenutnaPozicijaJ - 1) >= 0)
											&& ((MatricaMape.matricaMape[trenutnaPozicijaI + 1][trenutnaPozicijaJ
													+ 1] != null)
													|| (MatricaMape.matricaMape[trenutnaPozicijaI + 1][trenutnaPozicijaJ
															- 1] != null))) {

								daLiSamUnistena = true;
								pozicijaUnistenjaI = trenutnaPozicijaI;
								pozicijaUnistenjaJ = trenutnaPozicijaJ;
								System.out.println("Domace letjelice su unistile stranu letjelicu!");

							}
						}
					}
					MatricaMape.matricaMape[trenutnaPozicijaI + 1][trenutnaPozicijaJ] = null;

				}

			}
			if (strana == 3) {

				{
					trenutnaPozicijaJ--;
					if (trenutnaPozicijaI < 0 || trenutnaPozicijaI > 99 || trenutnaPozicijaJ < 0
							|| trenutnaPozicijaJ > 99) {
						daLiSamUnistena = true;
						MatricaMape.matricaMape[trenutnaPozicijaI][0] = null;
						System.out.println("Letjelica je izasla sa strane 3");
						if (trenutnaPozicijaJ < 0)
							trenutnaPozicijaJ = 0;
						break;
					}

					if (MatricaMape.matricaMape[trenutnaPozicijaI][trenutnaPozicijaJ] != null) {
						if (MatricaMape.matricaMape[trenutnaPozicijaI][trenutnaPozicijaJ] instanceof Letjelica) {
							Letjelica l = (Letjelica) MatricaMape.matricaMape[trenutnaPozicijaI][trenutnaPozicijaJ];
							if (getVisinaLeta() == l.getVisinaLeta()) {
								l.daLiSamUnistena = true;
								daLiSamUnistena = true;
								MatricaMape.matricaMape[trenutnaPozicijaI][trenutnaPozicijaJ] = null;
								MatricaMape.matricaMape[trenutnaPozicijaI][trenutnaPozicijaJ + 1] = null;

								Platform.runLater(new Runnable() {
									@Override
									public void run() {
										new Alert(AlertType.INFORMATION,
												"Letjelica " + model + " i letjelica " + l.model
														+ " su se sudarili na poziciji:  [" + trenutnaPozicijaI + "]"
														+ "[" + trenutnaPozicijaJ + "]").showAndWait();

									}
								});

								DetektujSudar ds = new DetektujSudar(
										"Letjelica " + model + " i letjelica " + l.model + " su se sudarili",
										trenutnaPozicijaI, trenutnaPozicijaJ, this, l);
								DetektujSudar.serijalizujSudar(ds);

							}
						}
					}
					if (!daLiSamUnistena)
						MatricaMape.matricaMape[trenutnaPozicijaI][trenutnaPozicijaJ] = this;

					if (KreirajLetjelice.stranaLet) {

						if ("SL".equals(oznaka)) {
							if ((!daLiSamUnistena && ((((trenutnaPozicijaI - 1) >= 0) && ((trenutnaPozicijaI + 1) < 100)
									&& ((MatricaMape.matricaMape[trenutnaPozicijaI + 1][trenutnaPozicijaJ] != null)
											|| (MatricaMape.matricaMape[trenutnaPozicijaI
													- 1][trenutnaPozicijaJ]) != null))))
									|| (((trenutnaPozicijaJ - 1) >= 0) && ((trenutnaPozicijaI + 1) < 100)
											&& ((trenutnaPozicijaI - 1) >= 0) && ((trenutnaPozicijaJ + 1) < 100)
											&& ((MatricaMape.matricaMape[trenutnaPozicijaI + 1][trenutnaPozicijaJ
													+ 1] != null)
													|| (MatricaMape.matricaMape[trenutnaPozicijaI - 1][trenutnaPozicijaJ
															+ 1] != null)))) {
								daLiSamUnistena = true;
								pozicijaUnistenjaI = trenutnaPozicijaI;
								pozicijaUnistenjaJ = trenutnaPozicijaJ;
								System.out.println("Domace letjelice su unistile stranu letjelicu!");
							}
						}
					}
					MatricaMape.matricaMape[trenutnaPozicijaI][trenutnaPozicijaJ + 1] = null;

				}

			}
			// brise stranu letjelicu iz mape kad se unisti
			if (daLiSamUnistena)
				MatricaMape.matricaMape[pozicijaUnistenjaI][pozicijaUnistenjaJ] = null;
			if (daLiSamUnistena)
				break;
		}

	}

	public Integer izracunajNajkracuPutanju(Integer strana, Integer pozicijaI, Integer pozicijaJ) {
		Integer smjer = 0, pozicija1 = 0, pozicija2 = 0;
		if (strana == 0) {
			if (trenutnaPozicijaJ <= 50) {
				pozicija1 = trenutnaPozicijaI;
				pozicija2 = 99 - trenutnaPozicijaI;
				Integer manji = Math.min(pozicija1, pozicija2);
				if (manji == pozicija1)
					smjer = 3;
				else
					smjer = 0;
			} else {
				pozicija1 = trenutnaPozicijaI;
				pozicija2 = 99 - trenutnaPozicijaI;
				Integer manji = Math.min(pozicija1, pozicija2);
				if (manji == pozicija1)
					smjer = 1;
				else
					smjer = 0;
			}
		}

		if (strana == 1) {
			if (trenutnaPozicijaI <= 50) {
				pozicija1 = trenutnaPozicijaJ;
				pozicija2 = 99 - trenutnaPozicijaJ;
				Integer manji = Math.min(pozicija1, pozicija2);
				if (manji == pozicija1)
					smjer = 2;
				else
					smjer = 1;
			} else {
				pozicija1 = trenutnaPozicijaJ;
				pozicija2 = 99 - trenutnaPozicijaJ;
				Integer manji = Math.min(pozicija1, pozicija2);
				if (manji == pozicija1)
					smjer = 0;
				else
					smjer = 1;
			}
		}

		if (strana == 2) {
			if (trenutnaPozicijaJ <= 50) {
				pozicija1 = trenutnaPozicijaI;
				pozicija2 = 99 - trenutnaPozicijaI;
				Integer manji = Math.min(pozicija1, pozicija2);
				if (manji == pozicija1)
					smjer = 2;
				else
					smjer = 3;
			} else {
				pozicija1 = trenutnaPozicijaI;
				pozicija2 = 99 - trenutnaPozicijaI;
				Integer manji = Math.min(pozicija1, pozicija2);
				if (manji == pozicija1)
					smjer = 2;
				else
					smjer = 1;
			}
		}
		if (strana == 3) {
			if (trenutnaPozicijaI <= 50) {
				pozicija1 = trenutnaPozicijaJ;
				pozicija2 = 99 - trenutnaPozicijaJ;
				Integer manji = Math.min(pozicija1, pozicija2);
				if (manji == pozicija1)
					smjer = 3;
				else
					smjer = 2;
			} else {
				pozicija1 = trenutnaPozicijaJ;
				pozicija2 = 99 - trenutnaPozicijaJ;
				Integer manji = Math.min(pozicija1, pozicija2);
				if (manji == pozicija1)
					smjer = 3;
				else
					smjer = 0;
			}
		}
		return smjer;
	}

	@Override
	public void run() {

		System.out.println(Thread.currentThread().getName());
		kretanjeLetjelica(strana);

	}

}
