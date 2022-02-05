package osobe;

public class Pilot extends Osoba {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean licencaZaLetenje;

	public Pilot() {
		super();
	}

	public Pilot(String ime, String prezime, boolean licencaZaLetenje) {
		super(ime, prezime);
		this.licencaZaLetenje = licencaZaLetenje;
	}

	public boolean isLicencaZaLetenje() {
		return licencaZaLetenje;
	}

	public void setLicencaZaLetenje(boolean licencaZaLetenje) {
		this.licencaZaLetenje = licencaZaLetenje;
	}

	@Override
	public String toString() {
		return super.toString() + " Pilot [licencaZaLetenje=" + licencaZaLetenje + "]";
	}

}
