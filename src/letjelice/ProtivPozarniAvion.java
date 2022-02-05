package letjelice;

import java.util.List;

import interfejsi.GasenjeAvionom;
import osobe.Osoba;

public class ProtivPozarniAvion extends Letjelica implements GasenjeAvionom {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer kolicinaVode;

	public ProtivPozarniAvion() {
		super();
	}

	public ProtivPozarniAvion(String model, Integer visinaLeta, Integer brzinaLeta, List<Osoba> osoba,
			String karakteristike, Integer kolicinaVode) {
		super(model, visinaLeta, brzinaLeta, osoba, karakteristike);
		this.kolicinaVode = kolicinaVode;
		this.oznaka = "ZA";
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
