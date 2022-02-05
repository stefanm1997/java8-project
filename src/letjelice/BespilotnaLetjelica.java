package letjelice;

import java.util.List;

import interfejsi.SnimanjeTerena;
import osobe.Osoba;

public class BespilotnaLetjelica extends Letjelica implements SnimanjeTerena {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BespilotnaLetjelica() {
		super();
	}

	public BespilotnaLetjelica(String model, Integer visinaLeta, Integer brzinaLeta, List<Osoba> osoba,
			String karakteristike) {
		super(model, visinaLeta, brzinaLeta, null, karakteristike);
		this.oznaka = "BL";
	}

	@Override
	public String toString() {
		return super.toString();
	}

}
