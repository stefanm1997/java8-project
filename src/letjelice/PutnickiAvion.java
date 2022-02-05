package letjelice;

import java.util.List;

import interfejsi.PrevozAvionom;
import osobe.Osoba;

public class PutnickiAvion extends Letjelica implements PrevozAvionom{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer brojSjedista, maksimalnaTezinaTereta;

	public PutnickiAvion() {
		super();
	}

	public PutnickiAvion(String model, Integer visinaLeta, Integer brzinaLeta, List<Osoba> osoba, String karakteristike,
			Integer brojSjedista, Integer maksimalnaTezinaTereta) {
		super(model, visinaLeta, brzinaLeta, osoba, karakteristike);
		this.brojSjedista = brojSjedista;
		this.maksimalnaTezinaTereta = maksimalnaTezinaTereta;
		this.oznaka = "PA";
	}

	public Integer getBrojSjedista() {
		return brojSjedista;
	}

	public void setBrojSjedista(Integer brojSjedista) {
		this.brojSjedista = brojSjedista;
	}

	public Integer getMaksimalnaTezinaTereta() {
		return maksimalnaTezinaTereta;
	}

	public void setMaksimalnaTezinaTereta(Integer maksimalnaTezinaTereta) {
		this.maksimalnaTezinaTereta = maksimalnaTezinaTereta;
	}

	@Override
	public String toString() {
		return super.toString();
	}

}
