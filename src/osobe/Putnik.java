package osobe;

public class Putnik extends Osoba {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String brojPasosa;

	public Putnik() {
		super();
	}

	public Putnik(String ime, String prezime, String brojPasosa) {
		super(ime, prezime);
		this.brojPasosa = brojPasosa;
	}

	public String getBrojPasosa() {
		return brojPasosa;
	}

	public void setBrojPasosa(String brojPasosa) {
		this.brojPasosa = brojPasosa;
	}

	@Override
	public String toString() {
		return super.toString() + " Putnik [brojPasosa=" + brojPasosa + "]";
	}

}
