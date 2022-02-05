package letjelice;

import java.util.List;

import interfejsi.GasenjeHelikopterom;
import osobe.Osoba;

public class ProtivPozarniHelikopter extends Letjelica implements GasenjeHelikopterom {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer kolicinaVode;

	public ProtivPozarniHelikopter() {
		super();
	}

	public ProtivPozarniHelikopter(String model, Integer visinaLeta, Integer brzinaLeta, List<Osoba> osoba,
			String karakteristike, Integer kolicinaVode) {
		super(model, visinaLeta, brzinaLeta, osoba, karakteristike);
		this.kolicinaVode = kolicinaVode;
		this.oznaka = "ZH";
	}

	public Integer getKolicinaVode() {
		return kolicinaVode;
	}

	public void setKolicinaVode(Integer kolicinaVode) {
		this.kolicinaVode = kolicinaVode;
	}

	@Override
	public String toString() {
		return super.toString();
	}

}
