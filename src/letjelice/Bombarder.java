package letjelice;

import java.util.List;

import interfejsi.Bombardovanje;
import osobe.Osoba;

public class Bombarder extends Letjelica implements Bombardovanje {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Bombarder() {
		super();
	}

	public Bombarder(String model, Integer visinaLeta, Integer brzinaLeta, List<Osoba> osoba, String karakteristike) {
		super(model, visinaLeta, brzinaLeta, osoba, karakteristike);
		this.oznaka = "B";
	}

	@Override
	public String toString() {
		return super.toString();
	}

}
