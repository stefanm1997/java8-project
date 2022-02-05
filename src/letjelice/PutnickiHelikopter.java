package letjelice;

import java.util.List;

import interfejsi.PrevozHelikopterom;
import osobe.Osoba;

public class PutnickiHelikopter extends Letjelica implements PrevozHelikopterom {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer brojSjedista;

	public PutnickiHelikopter() {
		super();
	}

	public PutnickiHelikopter(String model, Integer visinaLeta, Integer brzinaLeta, List<Osoba> osoba,
			String karakteristike, Integer brojSjedista) {
		super(model, visinaLeta, brzinaLeta, osoba, karakteristike);
		this.brojSjedista = brojSjedista;
		this.oznaka = "PH";
	}

	public Integer getBrojSjedista() {
		return brojSjedista;
	}

	public void setBrojSjedista(Integer brojSjedista) {
		this.brojSjedista = brojSjedista;
	}

	@Override
	public String toString() {
		return super.toString();
	}

}
