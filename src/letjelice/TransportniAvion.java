package letjelice;

import java.util.List;

import interfejsi.TeretAvionom;
import osobe.Osoba;

public class TransportniAvion extends Letjelica implements TeretAvionom {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer maksimalnaTezinaTereta;

	public TransportniAvion() {
		super();
	}

	public TransportniAvion(String model, Integer visinaLeta, Integer brzinaLeta, List<Osoba> osoba,
			String karakteristike, Integer maksimalnalnaTezinaTereta) {
		super(model, visinaLeta, brzinaLeta, osoba, karakteristike);
		this.maksimalnaTezinaTereta = maksimalnalnaTezinaTereta;
		this.oznaka = "TA";
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
