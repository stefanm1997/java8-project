package letjelice;

import java.util.List;

import interfejsi.TeretHelikopterom;
import osobe.Osoba;

public class TransportniHelikopter extends Letjelica implements TeretHelikopterom {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer maksimalnaTezinaTereta;

	public TransportniHelikopter() {
		super();
	}

	public TransportniHelikopter(String model, Integer visinaLeta, Integer brzinaLeta, List<Osoba> osoba,
			String karakteristike, Integer maksimalnaTezinaTereta) {
		super(model, visinaLeta, brzinaLeta, osoba, karakteristike);
		this.maksimalnaTezinaTereta = maksimalnaTezinaTereta;
		this.oznaka = "TH";
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
