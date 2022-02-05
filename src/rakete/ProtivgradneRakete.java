package rakete;

import interfejsi.GadjanjeOblaka;

public class ProtivgradneRakete extends Raketa implements GadjanjeOblaka {

	public ProtivgradneRakete() {
		super();
	}

	public ProtivgradneRakete(Integer dometRakete, Integer visinaLetenja) {
		super(dometRakete, visinaLetenja);
		this.oznaka = "PR";
	}

	@Override
	public String toString() {
		return super.toString();
	}

}
