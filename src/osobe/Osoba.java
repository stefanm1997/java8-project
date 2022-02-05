package osobe;

import java.io.Serializable;

public abstract class Osoba implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ime, prezime;

	public Osoba() {
		super();
	}

	public Osoba(String ime, String prezime) {
		super();
		this.ime = ime;
		this.prezime = prezime;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	@Override
	public String toString() {
		return "Osoba [ime=" + ime + ", prezime=" + prezime + "]";
	}

}
