package letjelice;

import java.util.List;

import interfejsi.GadjanjeCiljeva;
import osobe.Osoba;

public class Lovac extends Letjelica implements GadjanjeCiljeva {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Lovac() {
		super();
	}

	public Lovac(String model, Integer visinaLeta, Integer brzinaLeta, List<Osoba> osoba, String karakteristike) {
		super(model, visinaLeta, brzinaLeta, osoba, karakteristike);
		this.oznaka = "L";
	}

	@Override
	public String toString() {
		return super.toString();
	}

}
